package ru.art2000.androraider.model.analyzer.smali.types

import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.RangeAnalyzeStatus
import java.io.File

@Suppress("RedundantVisibilityModifier")
class SmaliClass() : FileAnalyzeResult, SmaliComponent {

    object Primitives {

        public val VOID = SmaliClass("V")
        public val INT = SmaliClass("I")
        public val LONG = SmaliClass("J")
        public val SHORT = SmaliClass("S")
        public val BYTE = SmaliClass("B")
        public val BOOLEAN = SmaliClass("Z")
        public val CHAR = SmaliClass("C")
        public val FLOAT = SmaliClass("F")
        public val DOUBLE = SmaliClass("D")

    }

    var name: String = ""

    val interfaces = mutableListOf<SmaliClass>()

    constructor(name: String,
                parentPackage: SmaliPackage? = null) : this() {
        this.name = name
        this.parentPackage = parentPackage
    }

    public var modifier = 0
        private set

    public fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    public override val fullname: String
        get() {

            if (isArray) {
                var array = parentClass?.fullname ?: ""
                repeat(arrayCount) { array = "[$array" }
                return array
            }

            var result = name
            var parent = parentPackage
            while (parent != null) {
                result = parent.name + "." + result
                parent = parent.parentPackage
            }
            return result
        }

    public val isPrimitive: Boolean
        get() {
            return when (fullname) {
                "I", "J", "S", "B", "C", "Z", "F", "D" -> true
                else -> false
            }
        }

    public val isVoid: Boolean
        get() = fullname == "V"

    var parentPackage: SmaliPackage? = null
        set(value) {
            if (field != value) {
                field?.classes?.remove(this)
                value?.classes?.removeIf {
                    it.name == name
                }
                value?.classes?.add(this)
                field = value
            }
        }

    var parentClass: SmaliClass? = null

    var associatedFile: File? = null

    val fields = mutableListOf<SmaliField>()
    val methods = mutableListOf<SmaliMethod>()

    val ranges = mutableListOf<RangeAnalyzeStatus>()

    var arrayCount = 0

    public val isArray: Boolean
        get() = arrayCount > 0

    public fun addField(smaliField: SmaliField) {
        smaliField.parentClass = this
        fields.add(smaliField)
    }

    public fun removeField(smaliField: SmaliField) {
        smaliField.parentClass = null
        fields.remove(smaliField)
    }

    public fun addMethod(smaliMethod: SmaliMethod) {
        smaliMethod.parentClass = this
        methods.add(smaliMethod)
    }

    public fun removeMethod(smaliMethod: SmaliMethod) {
        smaliMethod.parentClass = null
        methods.remove(smaliMethod)
    }

    public fun findField(name: String, typeClass: SmaliClass): SmaliField? {
        var res: SmaliField? = null

        var parent: SmaliClass? = this
        while (parent != null) {
            val tmp = parent.fields.find { field ->
                return@find field.name == name && field.type == typeClass
            }
            if (tmp != null) {
                res = tmp
                break
            }
            parent = parent.parentClass
        }

        return res
    }

    public fun findOrCreateField(name: String, type: String): SmaliField? {
        val prj = parentPackage?.project ?: return null

        val typeClass = prj.getOrCreateClass(type)!!

        return findField(name, typeClass) ?: SmaliField().apply {
            this.name = name
            this.type = typeClass
            parentClass = this@SmaliClass
        }
    }

    private fun findMethodInInterfaces(name: String, parameters: List<SmaliClass>, returnType: String): SmaliMethod? {
        var res: SmaliMethod? = null

        for (cl in interfaces) {
            var parent: SmaliClass? = cl
            while (parent != null) {
                val tmp = parent.methods.find { method ->
                    val parametersReal = method.parametersInternal

                    val firstPart = method.name == name
                            && parameters.size == parametersReal.size

                    if (!firstPart)
                        return@find false

                    for (i in parametersReal.indices) {
                        if (parametersReal[i].fullname != parameters[i].fullname)
                            return@find false
                    }

                    return@find true
                }
                if (tmp != null) {
                    res = tmp
                    break
                }
                parent = parent.parentClass
            }
            if (res != null) {
                break
            }
        }

        return res
    }

    private fun findMethod(name: String, parameters: List<SmaliClass>, returnType: String, scanLevel: Int = -1): SmaliMethod? {
        var lvl = scanLevel
        var res: SmaliMethod? = null

        val noBorder = scanLevel < 0

        var parent: SmaliClass? = this
        while (parent != null) {
            val tmp = parent.methods.find { method ->
                val parametersReal = method.parametersInternal

                val firstPart = method.name == name
                        && parameters.size == parametersReal.size

                if (!firstPart)
                    return@find false

                for (i in parametersReal.indices) {
                    if (parametersReal[i].fullname != parameters[i].fullname)
                        return@find false
                }

                return@find true
            }
            if (tmp != null || (lvl < 1 && !noBorder)) {
                res = tmp
                break
            }
            --lvl
            parent = parent.parentClass
        }

        return res ?: findMethodInInterfaces(name, parameters, returnType)
    }

    public fun findOrCreateMethod(name: String, parameters: List<String>, returnType: String, scanLevel: Int = -1): SmaliMethod? {
        val prj = parentPackage?.project ?: return null
        val requiredParameters = parameters.mapNotNull {
            prj.getOrCreateClass(it)
        }

        return findMethod(name, requiredParameters, returnType, scanLevel)
                ?: createMethod(name, requiredParameters, returnType)
    }

    private fun createMethod(name: String, parameters: List<SmaliClass>, returnType: String): SmaliMethod? {
        val prj = parentPackage?.project ?: return null
        return SmaliMethod(name, prj.getOrCreateClass(returnType)!!, this).also {
            it.parametersInternal.addAll(parameters)
        }
    }

    override val rangeStatuses: List<RangeAnalyzeStatus>
        get() = ranges

    override fun toString(): String {
        return fullname
    }

    override val file: File?
        get() = associatedFile

    override var textRange: IntRange = -1..0

    override fun markAsNotExisting() {
        textRange = -1..0
    }

    override fun exists(): Boolean {
        return (file != null && textRange.first >= 0) || isPrimitive || isVoid || (isArray && parentClass?.exists() == true)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SmaliClass

        if (fullname != other.fullname) return false
        if (associatedFile != other.associatedFile) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fullname.hashCode()
        result = 31 * result + (associatedFile?.hashCode() ?: 0)
        return result
    }
}
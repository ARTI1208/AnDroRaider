package ru.art2000.androraider.model.analyzer.smali.types

import javafx.scene.control.Separator
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.RangeAnalyzeStatus
import java.io.File
import java.util.*

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

    init {
//        println("Created class0 ${this} // ${Objects.hash(this)}")
    }

    constructor(name: String,
                parentPackage: SmaliPackage? = null) : this() {
        this.name = name
        this.parentPackage = parentPackage
//        println("Created class1 ${this} // ${Objects.hash(this)}")
    }

    constructor(file: File) : this() {
        this.associatedFile = file
        this.name = file.nameWithoutExtension
//        println("Created class2 ${this} // ${Objects.hash(this)}")
    }

    var modifier = 0

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

    public fun fullname(prefix: String = "", separator: String = "."): String {
        if (isArray) {
            var array = parentClass?.fullname ?: ""
            repeat(arrayCount) { array = "[$array" }
            return array
        }

        var result = name
        var parent = parentPackage
        while (parent != null) {
            result = parent.name + separator + result
            parent = parent.parentPackage
        }
        return prefix + result
    }

    val isPrimitive: Boolean
        get() {
            return when (fullname) {
                "I", "J", "S", "B", "C", "Z", "F", "D" -> true
                else -> false
            }
        }

    val isVoid: Boolean
        get() {
            return when (fullname) {
                "V" -> true
                else -> false
            }
        }

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
//        if (smaliMethod.name == "<init>" && fullname == "a.c.a") {
//            println("reeeeeeeem: ")
//        }
    }

    public fun findField(name: String, typeClass: SmaliClass): SmaliField? {
        var res: SmaliField? = null

        var parent: SmaliClass? = this
        while (parent!= null) {
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
//        println("find $name")

        val prj = parentPackage?.project ?: return null

        val typeClass = prj.getOrCreateClass(type)!!

        return findField(name, typeClass) ?: SmaliField().apply {
            this.name = name
            this.type = typeClass
            parentClass = this@SmaliClass
        }
    }

    public fun findMethodInInterfaces(name: String, parameters: List<SmaliClass>, returnType: String): SmaliMethod? {
        if (name == "get")
            println("finding $name in ${interfaces.size} in $fullname")

        var res: SmaliMethod? = null

        for (cl in interfaces) {
            if (name == "get")
                println("inter $name in ${interfaces.size} in ${cl.fullname}")
            var parent: SmaliClass? = this
            while (parent != null) {
                val tmp = parent.methods.find { method ->
                    val parametersReal = method.parametersInternal

                    val firstPart = method.name == name
                            && parameters.size == parametersReal.size

//                if (name == "<init>" && fullname == "a.c.a") {
//                    println("Cmp: $method")
//                }

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

    public fun findMethod(name: String, parameters: List<SmaliClass>, returnType: String, scanLevel: Int = -1): SmaliMethod? {
//        if (name == "<init>" && fullname == "a.c.a")
//            println("finding $name in ${methods.size}")
        var lvl= scanLevel
        var res: SmaliMethod? = null

        var parent: SmaliClass? = this
        while (parent!= null) {
            val tmp = parent.methods.find { method ->
                val parametersReal = method.parametersInternal

                val firstPart = method.name == name
                        && parameters.size == parametersReal.size

//                if (name == "<init>" && fullname == "a.c.a") {
//                    println("Cmp: $method")
//                }

                if (!firstPart)
                    return@find false

                for (i in parametersReal.indices) {
                    if (parametersReal[i].fullname != parameters[i].fullname)
                        return@find false
                }

                return@find true
            }
            if (tmp != null || lvl < 1) {
                res = tmp
                break
            }
            --lvl
            parent = parent.parentClass
        }

        if (fullname == "a.a" || fullname == "-\$Lambda\$S9HjrJh0nDg7IyU6wZdPArnZWRQ\$1") {
            println("found $name in $parent")
        }

        return res ?: findMethodInInterfaces(name, parameters, returnType)
    }

    public fun findOrCreateMethod(name: String, parameters: List<String>, returnType: String, scanLevel: Int = -1): SmaliMethod? {
        if (fullname == "a.a" || fullname == "-\$Lambda\$S9HjrJh0nDg7IyU6wZdPArnZWRQ\$1") {
            println("finding $name")
        }

        val prj = parentPackage?.project ?: return null
        val requiredParameters = parameters.mapNotNull {
            prj.getOrCreateClass(it)
        }

        return findMethod(name, requiredParameters, returnType, scanLevel) ?: createMethod(name, requiredParameters, returnType)
    }

    private fun createMethod(name: String, parameters: List<SmaliClass>, returnType: String): SmaliMethod? {
        val prj = parentPackage?.project ?: return null
        return SmaliMethod(name, prj.getOrCreateClass(returnType)!!, this).also {
            it.parametersInternal.addAll(parameters)
            if (fullname == "a.a" || fullname == "-\$Lambda\$S9HjrJh0nDg7IyU6wZdPArnZWRQ\$1") {
                println("create $name in $this")
            }
//            println("Created $it//${it.hashCode()}//in ${hashCode()}//${this}")
        }
    }

    override val rangeStatuses: List<RangeAnalyzeStatus>
        get() = ranges

    override fun toString(): String {
        return fullname
    }

    override val file: File?
        get() = associatedFile

    override val textRange: IntRange = 0..0

    override fun recheck(): SmaliComponent? {
        return if (file == null) null else this
    }

    override fun exists(): Boolean {
        return file != null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SmaliClass

        if (fullname != other.fullname) return false
        if (associatedFile != other.associatedFile) return false

        return true
    }

//    override fun hashCode(): Int {
//        var result = fullname.hashCode()
//        result = 31 * result + (associatedFile?.hashCode() ?: 0)
//        return result
//    }


}
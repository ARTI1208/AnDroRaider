package ru.art2000.androraider.model.analyzer.smali.types

import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.RangeAnalyzeStatus
import java.io.File

@Suppress("RedundantVisibilityModifier")
open class SmaliClass() : FileAnalyzeResult {

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

    constructor(name: String,
                parentPackage: SmaliPackage? = null) : this() {
        this.name = name
        this.parentPackage = parentPackage
    }

    constructor(file: File) : this() {
        this.associatedFile = file
        this.name = file.nameWithoutExtension
    }

    var modifier = 0

    public fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    public val fullname: String
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

    var name: String = ""

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

    override val rangeStatuses: List<RangeAnalyzeStatus>
        get() = ranges

    override fun toString(): String {
        return fullname
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
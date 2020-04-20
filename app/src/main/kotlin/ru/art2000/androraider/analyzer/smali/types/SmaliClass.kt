package ru.art2000.androraider.analyzer.smali.types

import ru.art2000.androraider.analyzer.FileAnalyzeResult
import ru.art2000.androraider.analyzer.RangeAnalyzeStatus
import ru.art2000.androraider.model.editor.Error
import java.io.File

@Suppress("RedundantVisibilityModifier")
class SmaliClass() : FileAnalyzeResult {

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

    var modifier = 0

    public fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    val fullname: String
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
                value?.classes?.add(this)
                field = value
            }
        }

    var parentClass: SmaliClass? = null

    var associatedFile: File? = null

    val fields = mutableListOf<SmaliField>()
    val methods = mutableListOf<SmaliMethod>()

    val errors = mutableListOf<Error>()

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
        get() = errors

    override fun toString(): String {
        return "SmaliClass(modifier=$modifier, name='$name', parentPackage=${parentPackage?.fullname}, parentClass=${parentClass?.fullname})"
    }
}
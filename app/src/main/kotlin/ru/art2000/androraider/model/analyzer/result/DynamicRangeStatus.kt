package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.model.analyzer.smali.types.SmaliField
import java.io.File

class DynamicRangeStatus(override val range: IntRange, private val clazz: SmaliClass?): RangeAnalyzeStatus, NavigableRange {

    private var currentStyle = mutableSetOf<String>()
    private var currentDescription = "Class $clazz"

    private fun update() {
        val prj = clazz?.parentPackage?.project ?: return

        currentDescription = if (prj.exists(clazz)) {
            currentStyle.clear()
            "Class $clazz in ${clazz.associatedFile}"
        } else {
            currentStyle.add("error")
            "Class $clazz not found"
        }
    }

    override val description: String
        get() {
            update()
            return currentDescription
        }

    override val style: Collection<String>
        get() {
            update()
            return currentStyle
        }

    override val file: File?
        get() = clazz?.associatedFile

    override val offset = 0
}

class DynamicRangeStatusField(override val range: IntRange, private val field: SmaliField?): RangeAnalyzeStatus, NavigableRange {

    private var currentStyle = mutableSetOf<String>()
    private var currentDescription = "Field $field"

    private fun update() {
        val prj = field?.parentClass?.parentPackage?.project ?: return

        currentDescription = if (prj.exists(field)) {
            currentStyle.clear()
            "Field $field in ${field.parentClass} at ${field.textRange}"
        } else {
            currentStyle.add("error")
            "Field $field not found"
        }
    }

    override val description: String
        get() {
            update()
            return currentDescription
        }

    override val style: Collection<String>
        get() {
            update()
            return currentStyle
        }

    override val file: File?
        get() = this.field?.parentClass?.associatedFile

    override val offset: Int
        get() = this.field?.textRange?.last ?: 0
}

class DynamicRangeStatusDef(override val range: IntRange, component: SmaliComponent?): RangeAnalyzeStatus, NavigableRange {

    private var currentStyle = mutableSetOf<String>()
    private var currentDescription = "$component"

    public var component: SmaliComponent? = component
        private set

    private fun update() {
//        component = component?.exists()
//        val ex = component?.file != null && component?.let { it.textRange.first >= 0 } ?: false
        currentDescription = if (component?.exists() == true) {
            currentStyle.clear()
            "$component in ${component?.file}//${component.hashCode()}"
        } else {
            currentStyle.add("error")
            "$component not found//${component.hashCode()}//${component?.textRange}"
        }
    }

    override val description: String
        get() {
            update()
            return currentDescription
        }

    override val style: Collection<String>
        get() {
            update()
            return currentStyle
        }

    override val file: File?
        get() = component?.file

    override val offset = component?.textRange?.last ?: 0

    override fun toString(): String {
        return "Dynamic: $component at $range, desc = $description"
    }
}
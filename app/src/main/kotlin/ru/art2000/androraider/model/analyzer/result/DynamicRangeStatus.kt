package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.model.analyzer.smali.types.SmaliField
import java.io.File

class DynamicRangeStatus(override val range: IntRange, component: SmaliComponent?): RangeAnalyzeStatus, NavigableRange {

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
        return "Dynamic: $component at $range, in parent = ${component?.textRange}"
    }
}
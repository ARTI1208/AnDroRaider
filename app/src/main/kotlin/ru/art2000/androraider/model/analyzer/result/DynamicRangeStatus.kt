package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import java.io.File

class DynamicRangeStatus(override val range: IntRange, component: SmaliComponent?) : RangeAnalyzeStatus, NavigableRange {

    private var currentStyle = mutableSetOf<String>()
    private var currentDescription = "$component"

    var component: SmaliComponent? = component
        private set

    private fun update() {
        currentDescription = if (component?.exists() == true) {
            currentStyle.clear()
            "$component in ${component?.file}"
        } else {
            currentStyle.add("error")
            "$component not found"
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

    override val offset: Int
        get() = component?.textRange?.last ?: 0

    override fun toString(): String {
        return description
    }
}
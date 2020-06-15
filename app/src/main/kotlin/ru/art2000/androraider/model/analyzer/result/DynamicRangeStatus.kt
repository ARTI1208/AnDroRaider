package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import java.io.File

class DynamicRangeStatus(override val range: IntRange, component: SmaliComponent, override val declaringFile: File) : RangeAnalyzeStatus, NavigableRange {

    private var currentStyle = mutableSetOf<String>()
    private var currentDescription = "$component"

    var component: SmaliComponent = component
        private set

    private fun update() {
        currentDescription = if (component.exists()) {
            currentStyle.clear()
            "$component in ${component.file}"
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

    override val fileToNavigate: File?
        get() = component.file

    override val offset: Int
        get() = component.textRange.last

    override fun toString(): String {
        return description
    }
}
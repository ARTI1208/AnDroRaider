package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import java.io.File

class DynamicRangeStatus(val range: IntRange, component: SmaliComponent, override val declaringFile: File) : RangeAnalyzeStatus, NavigableRange {

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

    override val rangeToStyle: List<Pair<IntRange, String>>
        get() {
            update()
            return currentStyle.let {
                if (it.isEmpty())
                    listOf(range to "")
                else
                    listOf(range to it.first())
            }
        }

    override val navigateDetails: List<FileNavigatePosition>
        get() {
            val f = component.file
            return if (f == null)
                listOf()
            else
                listOf(FileNavigatePosition(f, component.textRange.last, description))
        }

    override fun toString(): String {
        return description
    }
}
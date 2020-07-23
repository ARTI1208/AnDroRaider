package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import java.io.File

class SmaliAnalysisSegment(
        override val segmentRange: IntRange,
        val component: SmaliComponent,
        override val declaringFile: File
) : StyledSegment, DescriptiveSegment, FileSegment, NavigableSegment, HighlightableSegment {

    private var currentDescription = "$component"
    private var currentStyle = ""

    private fun update() {
        currentDescription = if (component.exists()) {
            currentStyle = ""
            "$component in ${component.file}"
        } else {
            currentStyle = "error"
            "$component not found"
        }
    }

    override val description: String
        get() {
            update()
            return currentDescription
        }

    override val style: String
        get() {
            update()
            return currentStyle
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

    override fun highlightOther(other: HighlightableSegment): Boolean {
        if (other !is SmaliAnalysisSegment) {
            return false
        }

        return component === other.component
    }
}
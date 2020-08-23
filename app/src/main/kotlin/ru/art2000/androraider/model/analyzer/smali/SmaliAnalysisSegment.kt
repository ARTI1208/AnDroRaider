package ru.art2000.androraider.model.analyzer.smali

import ru.art2000.androraider.model.analyzer.result.*
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import java.io.File

class SmaliAnalysisSegment(
        override val segmentRange: IntRange,
        val component: SmaliComponent,
        val link: FileLink?
) : StyledSegment, DescriptiveSegment, LinkSegment, HighlightableSegment {

    override val description: String = if (link == null)
        "$component not found"
    else
        "$component in ${link.file}"

    override val style: String = if (link == null) "error" else ""

    override val fileLinkDetails: List<FileLink> = if (link == null) emptyList() else listOf(link)

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
package ru.art2000.androraider.model.analyzer.android

import ru.art2000.androraider.model.analyzer.result.*
import java.io.File

class AndroidResourceSegment(
        private val resource: AndroidResource,
        override val fileLinkDetails: List<FileLink>,
        override val segmentRange: IntRange
) : LinkSegment, HighlightableSegment, DescriptiveSegment {

    override fun highlightOther(other: HighlightableSegment): Boolean {
        return other is AndroidResourceSegment && resource === other.resource
    }

    override val description: String = resource.toString()
}
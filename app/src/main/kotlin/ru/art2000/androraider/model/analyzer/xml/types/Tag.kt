package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.*
import java.io.File

class Tag(val document: Document, val name: String, styleRanges: List<IntRange>, tagNameRanges: List<IntRange>) {

    val styleSegments: List<StyledSegment>

    val nameSegments: List<TextSegment>

    init {
        styleSegments = styleRanges.map {
            SimpleFileAnalysisSegment(document.file, it, "tag")
        }

        nameSegments = tagNameRanges.map { TagNameSegment(this, it) }
    }


    val attributes = mutableListOf<Attribute>()

    fun createAttribute(startIndex: Int, schema: String?, name: String, value: String): Attribute {
        val attr = Attribute(this, startIndex, schema, name, value)
        attributes.add(attr)
        return attr
    }

    private class TagNameSegment(val tag: Tag, override val segmentRange: IntRange) : HighlightableSegment, NavigableSegment, FileSegment {

        override val navigateDetails: List<FileNavigatePosition>
            get() = listOf()

        override val declaringFile: File = tag.document.file

        override fun highlightOther(other: HighlightableSegment): Boolean {
            return other is TagNameSegment && tag === other.tag
        }
    }
}
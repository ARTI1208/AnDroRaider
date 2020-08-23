package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.*

class Tag(
        val document: Document,
        val name: String,
        styleRanges: List<IntRange>,
        tagNameRanges: List<IntRange>,
        val value: String = "",
        val parentTag: Tag? = null
) {

    val level: Int

    val styleSegments: List<StyledSegment>

    val nameSegments: List<TextSegment>

    init {
        level = (parentTag?.level?.plus(1)) ?: 0

        styleSegments = styleRanges.map { SimpleStyledSegment(it, "tag") }

        nameSegments = tagNameRanges.map { TagNameSegment(this, it) }
    }

    val subTags = mutableListOf<Tag>()

    val subTagsFlatten: List<Tag>
        get() {
            return (subTags + subTags.flatMap { it.subTagsFlatten })
        }

    val attributes = mutableListOf<Attribute>()

    fun createTag(name: String, styleRanges: List<IntRange>, nameRanges: List<IntRange>, value: String = ""): Tag {
        val tag = Tag(this.document, name, styleRanges, nameRanges, value, this)
        subTags.add(tag)
        return tag
    }

    fun createAttribute(startIndex: Int, schema: String?, name: String, value: String): Attribute {
        val attr = Attribute(this, startIndex, schema, name, value)
        attributes.add(attr)
        return attr
    }

    override fun toString(): String {
        return "Tag(name=$name, attrs=${attributes.joinToString(",", prefix = "[", postfix = "]")})"
    }

    private class TagNameSegment(val tag: Tag, override val segmentRange: IntRange) : HighlightableSegment, LinkSegment {

        override val fileLinkDetails: List<FileLink>
            get() = listOf()

        override fun highlightOther(other: HighlightableSegment): Boolean {
            return other is TagNameSegment && tag === other.tag
        }
    }
}
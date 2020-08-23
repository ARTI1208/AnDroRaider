package ru.art2000.androraider.model.analyzer.xml.types

class Document {

    val tags = mutableListOf<Tag>()

    val tagsFlatten: List<Tag>
        get() = (tags + tags.flatMap { it.subTagsFlatten })

    fun createTag(name: String, styleRanges: List<IntRange>, nameRanges: List<IntRange>, value: String = ""): Tag {
        val tag = Tag(this, name, styleRanges, nameRanges, value)
        tags.add(tag)
        return tag
    }

}
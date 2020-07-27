package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.TextSegment
import ru.art2000.androraider.model.apktool.addAll
import java.io.File

class Document(override val file: File): FileIndexingResult {

    override val textSegments: List<TextSegment>
        get() = mutableListOf<TextSegment>().apply {
            tags.forEach { tag ->
                getTagSegments(tag, this)
            }
        }

    val tags = mutableListOf<Tag>()

    val tagsFlatten: List<Tag>
        get() = (tags + tags.flatMap { it.subTagsFlatten })
//                .also {
//            println("orig: ${tags.size}; flatten: ${it.size}")
//        }

    private fun getTagSegments(tag: Tag, list: MutableList<TextSegment>) {
        list.addAll(tag.styleSegments)
        list.addAll(tag.nameSegments)
        tag.attributes.forEach { attr ->
            attr.schema?.also { list.add(it) }
            list.addAll(attr.name, attr.valueWithQuotes, attr.value)
        }
        tag.subTags.forEach { getTagSegments(it, list) }
    }

    fun createTag(name: String, styleRanges: List<IntRange>, nameRanges: List<IntRange>, value: String = ""): Tag {
        val tag = Tag(this, name, styleRanges, nameRanges, value)
        tags.add(tag)
        return tag
    }

}
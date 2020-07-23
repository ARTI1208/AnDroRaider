package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.TextSegment
import ru.art2000.androraider.model.apktool.addAll
import java.io.File

class Document(override val file: File): FileAnalyzeResult {

    override val textSegments: List<TextSegment>
        get() = mutableListOf<TextSegment>().apply {
            tags.forEach { tag ->
                addAll(tag.styleSegments)
                addAll(tag.nameSegments)
                tag.attributes.forEach { attr ->
                    attr.schema?.also { add(it) }
                    addAll(attr.name, attr.valueWithQuotes, attr.value)
                }
            }
        }

    val tags = mutableListOf<Tag>()

    fun createTag(name: String, styleRanges: List<IntRange>, nameRanges: List<IntRange>): Tag {
        val tag = Tag(this, name, styleRanges, nameRanges)
        tags.add(tag)
        return tag
    }

}
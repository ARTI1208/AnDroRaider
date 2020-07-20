package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.RangeAnalyzeStatus
import java.io.File

class Document(override val file: File): FileAnalyzeResult {

    override val rangeStatuses: List<RangeAnalyzeStatus>
        get() = mutableListOf<RangeAnalyzeStatus>().apply {
            addAll(tags)
            tags.forEach {
                addAll(it.attributes)
            }
        }

    val tags = mutableListOf<Tag>()

    fun createTag(name: String, styleRanges: List<IntRange>): Tag {
        val tag = Tag(this, name, styleRanges)
        tags.add(tag)
        return tag
    }

}
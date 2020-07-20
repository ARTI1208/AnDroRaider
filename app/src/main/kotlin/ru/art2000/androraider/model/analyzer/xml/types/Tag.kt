package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.RangeAnalyzeStatus

class Tag(val document: Document, val name: String, styleRanges: List<IntRange>) : RangeAnalyzeStatus {

    val attributes = mutableListOf<Attribute>()

    fun createAttribute(name: String, value: String, schema: String? = null, startIndex: Int): Attribute {
        val attr = Attribute(this, name, value, schema, startIndex)
        attributes.add(attr)
        return attr
    }

    override val description = "Tag"

    override val rangeToStyle = styleRanges.map { it to "tag" }

    override val declaringFile = document.file
}
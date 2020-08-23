package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.*

class Attribute(
        val tag: Tag,
        startIndex: Int,
        attrSchema: String?,
        attrName: String,
        attrValue: String
) {

    val schema: Schema?

    val name: Name

    val value: Value

    val valueWithQuotes: StyledSegment

    init {

        var i = startIndex
        schema = if (attrSchema != null) {
            val schemaRange = i..(i + attrSchema.length)
            i += attrSchema.length
            Schema(attrSchema, schemaRange)
        } else {
            null
        }

        val nameRange = i..(i + attrName.length)
        name = Name(attrName, nameRange)
        i += attrName.length + 1

        val valueWithQuotesRange = i..(i + attrValue.length)
        valueWithQuotes = ValueWrapper(valueWithQuotesRange)

        val valueRange = (valueWithQuotesRange.first + 1) until valueWithQuotesRange.last
        value = Value(attrValue.substring(1, attrValue.lastIndex), valueRange)
    }

    override fun toString(): String {
        return "${schema?.text?.plus(":") ?: ""}${name.text}=\"${value.text}\""
    }

    class Schema(
            val text: String,
            override val segmentRange: IntRange
    ) : StyledSegment, HighlightableSegment {

        override val style: String = "attr-schema"

        override val highlightStyle: String = "attr-schema-highlight"

        override fun highlightOther(other: HighlightableSegment): Boolean {
            if (other !is Schema)
                return false

            return text == other.text
        }
    }

    class Name(
            val text: String,
            override val segmentRange: IntRange
    ) : StyledSegment {

        override val style: String = "attr-name"
    }

    class ValueWrapper(
            override val segmentRange: IntRange
    ) : StyledSegment {

        override val style: String = "attr-value"
    }

    class Value(
            val text: String,
            override val segmentRange: IntRange
    ) : TextSegment, StyledSegment, HighlightableSegment, LinkSegment, DescriptiveSegment {

        var isErrorLink = false

        override var description: String = ""

        override val style: String
            get() = if (isErrorLink) "error" else ""

        override val fileLinkDetails: MutableList<FileLink> = mutableListOf()

        override fun highlightOther(other: HighlightableSegment): Boolean {
            if (other !is Value)
                return false

            return text == other.text
        }
    }

}
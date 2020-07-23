package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.*
import java.io.File

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
        val declaringFile = tag.document.file

        var i = startIndex
        schema = if (attrSchema != null) {
            val schemaRange = i..(i + attrSchema.length)
            i += attrSchema.length
            Schema(attrSchema, schemaRange, declaringFile)
        } else {
            null
        }

        val nameRange = i..(i + attrName.length)
        name = Name(attrName, nameRange, declaringFile)
        i += attrName.length + 1

        val valueWithQuotesRange = i..(i + attrValue.length)
        valueWithQuotes = ValueWrapper(valueWithQuotesRange, declaringFile)

        val valueRange = (valueWithQuotesRange.first + 1) until valueWithQuotesRange.last
        value = Value(attrValue.substring(1, attrValue.lastIndex), valueRange, declaringFile)
    }

    class Schema(
            val text: String,
            override val segmentRange: IntRange,
            override val declaringFile: File
    ) : StyledSegment, FileSegment, HighlightableSegment {

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
            override val segmentRange: IntRange,
            override val declaringFile: File
    ) : StyledSegment, FileSegment {

        override val style: String = "attr-name"
    }

    class ValueWrapper(
            override val segmentRange: IntRange,
            override val declaringFile: File
    ) : StyledSegment, FileSegment {

        override val style: String = "attr-value"
    }

    class Value(
            val text: String,
            override val segmentRange: IntRange,
            override val declaringFile: File
    ) : TextSegment, FileSegment, HighlightableSegment, NavigableSegment {

        override val navigateDetails: List<FileNavigatePosition>
            get() = emptyList()

        override fun highlightOther(other: HighlightableSegment): Boolean {
            if (other !is Value)
                return false

            return text == other.text
        }
    }

}
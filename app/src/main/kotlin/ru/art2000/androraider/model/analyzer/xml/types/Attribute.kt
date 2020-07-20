package ru.art2000.androraider.model.analyzer.xml.types

import ru.art2000.androraider.model.analyzer.result.RangeAnalyzeStatus
import java.io.File

class Attribute(val tag: Tag, val name: String, val value: String, val schema: String? = null, startIndex: Int) : RangeAnalyzeStatus {

    override val description = "Attribute $name"

    override val rangeToStyle = mutableListOf<Pair<IntRange, String>>()

    override val declaringFile: File
        get() = tag.document.file

    init {
        var i = startIndex
        if (schema != null) {
            rangeToStyle.add(i..(i + schema.length) to "attr-schema")
            i += schema.length
        }

        rangeToStyle.add(i..(i + name.length) to "attr-name")
        i += name.length + 1

        rangeToStyle.add(i..(i + value.length) to "attr-value")
    }

}
package ru.art2000.androraider.model.analyzer.result

import java.io.File

class RangeStatusBase(range: IntRange,
                      override val description: String,
                      rangeStyle: String,
                      override val declaringFile: File) : RangeAnalyzeStatus {

    override val rangeToStyle = listOf(range to rangeStyle)
}
package ru.art2000.androraider.model.analyzer.result

import java.io.File

interface RangeAnalyzeStatus {

    val description: String

    val rangeToStyle: List<Pair<IntRange, String>>

    val declaringFile: File
}
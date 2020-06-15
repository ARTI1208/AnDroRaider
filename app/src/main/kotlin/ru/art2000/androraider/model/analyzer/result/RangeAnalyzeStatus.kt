package ru.art2000.androraider.model.analyzer.result

import java.io.File

interface RangeAnalyzeStatus {

    val range: IntRange

    val description: String

    val style: Collection<String>

    val declaringFile: File
}
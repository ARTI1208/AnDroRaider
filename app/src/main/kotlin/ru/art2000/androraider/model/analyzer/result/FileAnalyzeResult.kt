package ru.art2000.androraider.model.analyzer.result

import java.io.File

interface FileAnalyzeResult {

    val file: File?

    val rangeStatuses: List<RangeAnalyzeStatus>
}
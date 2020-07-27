package ru.art2000.androraider.model.analyzer.result

import java.io.File

interface FileAnalyzerResult {

    val file: File

    val textSegments: List<TextSegment>
}
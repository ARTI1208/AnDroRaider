package ru.art2000.androraider.model.analyzer.result

import javafx.beans.Observable
import java.io.File

interface FileAnalyzeResult : TextAnalyzeResult {

    val file: File
}

class SimpleFileAnalyzeResult(
        override val file: File,
        override val textSegments: List<TextSegment>,
        override val dependencies: List<Observable> = emptyList()
) : FileAnalyzeResult
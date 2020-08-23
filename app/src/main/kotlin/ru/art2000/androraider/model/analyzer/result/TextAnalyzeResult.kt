package ru.art2000.androraider.model.analyzer.result

import javafx.beans.Observable

interface TextAnalyzeResult {

    val textSegments: List<TextSegment>

    val dependencies: List<Observable>
}

class SimpleTextAnalyzeResult(
        override val textSegments: List<TextSegment>,
        override val dependencies: List<Observable> = emptyList()
) : TextAnalyzeResult
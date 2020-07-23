package ru.art2000.androraider.model.analyzer.result

interface HighlightableSegment : TextSegment {

    val highlightStyle: String
        get() = "default-highlight"

    // Whether other highlightable should be highlighted when this one does
    fun highlightOther(other: HighlightableSegment) = equals(other)
}
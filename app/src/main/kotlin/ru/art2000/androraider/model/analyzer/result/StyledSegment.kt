package ru.art2000.androraider.model.analyzer.result

interface StyledSegment : TextSegment {

    val style: String
}

class SimpleStyledSegment(override val segmentRange: IntRange, override val style: String) : StyledSegment
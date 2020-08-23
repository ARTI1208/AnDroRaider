package ru.art2000.androraider.model.analyzer.result

data class DescriptiveAnalysisSegment(
        override val segmentRange: IntRange,
        override val style: String,
        override val description: String
) : StyledSegment, DescriptiveSegment
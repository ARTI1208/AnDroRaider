package ru.art2000.androraider.model.analyzer.result

import java.io.File

data class SimpleFileAnalysisSegment(
        override val declaringFile: File,
        override val segmentRange: IntRange,
        override val style: String
) : StyledSegment, FileSegment
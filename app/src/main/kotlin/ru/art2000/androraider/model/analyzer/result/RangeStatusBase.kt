package ru.art2000.androraider.model.analyzer.result

import java.io.File

class RangeStatusBase(override val range: IntRange,
                      override val description: String,
                      override val style: Collection<String>,
                      override val declaringFile: File) : RangeAnalyzeStatus
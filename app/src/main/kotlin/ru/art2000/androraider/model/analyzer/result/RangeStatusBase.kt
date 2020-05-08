package ru.art2000.androraider.model.analyzer.result

class RangeStatusBase(override val range: IntRange,
                      override val description: String,
                      override val style: Collection<String>) : RangeAnalyzeStatus
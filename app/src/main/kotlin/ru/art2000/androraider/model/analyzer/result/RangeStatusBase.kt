package ru.art2000.androraider.model.analyzer.result

class RangeStatusBase(override val range: IntRange,
                      description: String,
                      override val style: Collection<String>) : RangeAnalyzeStatus {

    override val description: String = "$description at $range"

}
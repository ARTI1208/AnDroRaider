package ru.art2000.androraider.model.analyzer.result

class RangeStatusBaseV2(override val range: IntRange,
                        text: String,
                        description: String,
                        override val style: Collection<String>) : RangeAnalyzeStatus {

    override val description: String = "$description|||$text|||$range"
}
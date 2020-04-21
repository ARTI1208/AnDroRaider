package ru.art2000.androraider.model.analyzer.result

interface RangeAnalyzeStatus {

    val range: IntRange

    val description: String

    val style : Collection<String>

}
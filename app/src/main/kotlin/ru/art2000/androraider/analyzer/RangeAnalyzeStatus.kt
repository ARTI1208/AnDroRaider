package ru.art2000.androraider.analyzer

interface RangeAnalyzeStatus {

    val range: IntRange

    val description: String

    val style : Collection<String>

}
package ru.art2000.androraider.model.analyzer.result

interface NavigableSegment : TextSegment {

    val navigateDetails: List<FileNavigatePosition>
}
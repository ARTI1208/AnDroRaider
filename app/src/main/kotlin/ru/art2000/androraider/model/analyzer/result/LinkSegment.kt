package ru.art2000.androraider.model.analyzer.result

interface LinkSegment : TextSegment {

    val fileLinkDetails: List<FileLink>
}
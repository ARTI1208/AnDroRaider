package ru.art2000.androraider.model.analyzer.android

import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.TextSegment
import java.io.File

class AndroidResourceHolder(override val file: File?) : FileIndexingResult {

    val resourceSegments: MutableList<AndroidResourceSegment> = mutableListOf()

    override val textSegments: List<TextSegment>
        get() = resourceSegments

}
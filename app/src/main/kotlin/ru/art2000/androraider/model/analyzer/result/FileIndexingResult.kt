package ru.art2000.androraider.model.analyzer.result

import java.io.File

interface FileIndexingResult {

    val file: File

    val links: List<Pair<Any, FileLink>>
}

public class SimpleFileIndexingResult(
        override val file: File,
        override val links: List<Pair<Any, FileLink>> = emptyList(),
) : FileIndexingResult
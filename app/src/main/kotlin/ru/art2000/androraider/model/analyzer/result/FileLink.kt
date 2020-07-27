package ru.art2000.androraider.model.analyzer.result

import java.io.File

interface FileLink {

    val file: File

    val offset: Int

    val description: String
}

class SimpleFileLink(override val file: File, override val offset: Int = 0, override val description: String = "") : FileLink
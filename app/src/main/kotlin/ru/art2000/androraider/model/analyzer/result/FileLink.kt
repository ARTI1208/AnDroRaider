package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.editor.CodeDataProvider
import ru.art2000.androraider.model.editor.FileDataProvider
import java.io.File

interface FileLink : Link {

    val file: File

    override val tabName: String
        get() = file.name
}

data class SimpleFileLink(override val file: File, override val offset: Int = 0, override val description: String = "") : FileLink {

    override val data: CodeDataProvider by lazy { FileDataProvider(file, offset) }
}
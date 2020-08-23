package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.editor.CodeDataProvider

interface Link {

    val data: CodeDataProvider

    val tabName: String

    val offset: Int

    val description: String
}
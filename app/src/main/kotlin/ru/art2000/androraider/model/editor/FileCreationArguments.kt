package ru.art2000.androraider.model.editor

data class FileCreationArguments(val isDirectory: Boolean, val visibleName: String, val fileExtension: String) {

    companion object {
        fun folder(visibleName: String) = FileCreationArguments(true, visibleName, "")

        fun simpleFile(visibleName: String) = FileCreationArguments(false, visibleName, "")
    }
}
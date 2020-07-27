package ru.art2000.androraider.presenter.editor

import ru.art2000.androraider.model.analyzer.android.AndroidAppProject
import ru.art2000.androraider.model.editor.FileCreationArguments
import ru.art2000.androraider.arch.IPresenter
import ru.art2000.androraider.utils.isSubFile
import java.io.File

class FileManagerPresenter : IPresenter {

    fun getFileCreationOptions(project: AndroidAppProject?, folder: File): List<FileCreationArguments> {
        val list = mutableListOf(
                FileCreationArguments.folder("Folder"),
                FileCreationArguments.simpleFile("File")
        )

        if (project == null)
            return list

        if (project.smaliFolders.any { folder.isSubFile(it) }) {
            list.add(FileCreationArguments(false, "Smali", "smali"))
        }

        val resFolder = project.projectFolder.toPath().resolve("res").toFile()
        if (folder.isSubFile(resFolder)) {
            list.add(FileCreationArguments(false, "XML", "xml"))
        }

        return list
    }
}
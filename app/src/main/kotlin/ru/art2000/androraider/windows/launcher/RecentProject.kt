package ru.art2000.androraider.windows.launcher

import javafx.collections.ObservableList
import java.io.File

class RecentProject {

    val appFile: File

    var projectName: String
        private set(value) {
            field = if (value.contains('.'))
                value.substring(0, value.lastIndexOf('.'))
            else
                value
        }

    var projectLocation: String

    constructor(file: File) {
        appFile = file
        projectName = file.name
        projectLocation = file.absolutePath
    }

    constructor(path: String) : this(File(path))

    companion object {
        @JvmStatic
        fun getArray(paths: ObservableList<String>): ArrayList<RecentProject> {
            val list = ArrayList<RecentProject>()
            for (s in paths)
                list.add(RecentProject(s))
            return list
        }
    }
}

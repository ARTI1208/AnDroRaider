package ru.art2000.androraider.model.launcher

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RecentProject

        if (projectLocation != other.projectLocation) return false

        return true
    }

    override fun hashCode(): Int {
        return projectLocation.hashCode()
    }
}

package ru.art2000.androraider.model.analyzer.extensions

import java.io.File

interface FileFilter {

    fun isSuitableFile(file: File): Boolean

}
package ru.art2000.androraider.model.editor

import java.io.File

// Provides essential methods for CodeArea
interface CodeAreaContext {


    fun openFile(file: File, offset: Int)
}
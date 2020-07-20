package ru.art2000.androraider.model.analyzer.result

import java.io.File

data class FileNavigatePosition(val file: File, val offset: Int, val description: String = "")
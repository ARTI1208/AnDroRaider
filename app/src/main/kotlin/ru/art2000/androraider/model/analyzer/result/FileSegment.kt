package ru.art2000.androraider.model.analyzer.result

import java.io.File

interface FileSegment : TextSegment {

    val declaringFile: File
}
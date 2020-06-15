package ru.art2000.androraider.model.analyzer.result

import java.io.File

interface NavigableRange {

    val fileToNavigate: File?

    val offset: Int
}
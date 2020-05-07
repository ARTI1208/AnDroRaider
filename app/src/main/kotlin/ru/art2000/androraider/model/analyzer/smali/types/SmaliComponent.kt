package ru.art2000.androraider.model.analyzer.smali.types

import java.io.File

interface SmaliComponent {
    val file: File?
    val textRange: IntRange
    val fullname: String

    // null if not exists, other value if exists or found in parent
    fun exists(): SmaliComponent?
}
package ru.art2000.androraider.model.analyzer.smali.types

import java.io.File

interface SmaliComponent {

    val file: File?
    val textRange: IntRange
    val fullname: String

    fun markAsNotExisting()
    fun exists(): Boolean
}
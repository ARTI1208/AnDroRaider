package ru.art2000.androraider.model.analyzer.result

import io.reactivex.Observable
import java.io.File

interface Project {

    val projectFolder: File

    fun indexProject(): Observable<out FileAnalyzeResult>

    fun canAnalyzeFile(file: File): Boolean

    fun analyzeFile(file: File, withRanges: Boolean = true): FileAnalyzeResult

    fun getAnalyzeResult(file: File): FileAnalyzeResult?
}
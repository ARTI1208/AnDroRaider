package ru.art2000.androraider.model.analyzer.result

import io.reactivex.Observable
import org.reactfx.collection.LiveList
import java.io.File

interface Project {

    val projectFolder: File

    val errorList: LiveList<FileSegment>

    fun indexProject(): Observable<out FileIndexingResult>

    fun canAnalyzeFile(file: File): Boolean

    fun analyzeFile(file: File, withRanges: Boolean = true): FileIndexingResult

    fun getAnalyzeResult(file: File): FileIndexingResult?
}
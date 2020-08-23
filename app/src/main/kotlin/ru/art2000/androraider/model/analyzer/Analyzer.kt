package ru.art2000.androraider.model.analyzer

import io.reactivex.Observable
import ru.art2000.androraider.model.analyzer.result.*
import java.io.File

interface Analyzer<in P : Project, in S : AnalyzerSettings> {

    fun analyzeText(project: P, settings: S, text: String): TextAnalyzeResult

    fun analyzeFile(project: P, settings: S, file: File): FileAnalyzeResult

    fun analyzeDirectory(project: P, settings: S, directory: File): Observable<out FileAnalyzeResult>

    fun analyzeProject(project: P, settings: S): Observable<out FileAnalyzeResult>
}
package ru.art2000.androraider.model.analyzer

import io.reactivex.Observable
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.Project
import java.io.File

interface Analyzer<in P : Project, in S : AnalyzerSettings, out FIR : FileIndexingResult> {

    /*
     * Be aware of fact that code text area always uses \n as line terminator
     */
    fun analyzeFile(project: P, settings: S, file: File): FIR

    fun analyzeDirectory(project: P, settings: S, directory: File): Observable<out FIR>

    fun analyzeProject(project: P, settings: S): Observable<out FIR>
}
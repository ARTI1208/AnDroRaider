package ru.art2000.androraider.model.analyzer

import io.reactivex.Observable
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import java.io.File

interface Indexer<FAR : FileAnalyzeResult> {

    /*
    Has a return type since we analyze file fully and sequentially
     */
    fun analyzeFile(project: ProjectAnalyzeResult, file: File): FAR

    /*
    Return RxJava Observable so that user can provide it's own event listeners
    */
    fun analyzeFilesInDir(project: ProjectAnalyzeResult, directory: File): Observable<FAR>

    fun indexProject(project: ProjectAnalyzeResult): Observable<FAR>
}
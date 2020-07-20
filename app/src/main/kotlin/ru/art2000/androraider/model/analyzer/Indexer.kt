package ru.art2000.androraider.model.analyzer

import io.reactivex.Observable
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import java.io.File

interface Indexer<FAR : FileAnalyzeResult, S : IndexerSettings> {

    /*
     * Has a return type since we analyze file fully and sequentially.
     * Should be aware of fact that code text area always uses \n as line terminator
     */
    fun analyzeFile(project: ProjectAnalyzeResult, file: File, settings: S): FAR

    /*
    Return RxJava Observable so that user can provide it's own event listeners
    */
    fun analyzeFilesInDir(project: ProjectAnalyzeResult, directory: File, settings: S): Observable<FAR>

    fun indexProject(project: ProjectAnalyzeResult, settings: S): Observable<FAR>
}
package ru.art2000.androraider.model.analyzer

import io.reactivex.Observable
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.Project
import java.io.File

interface Indexer<in P : Project> {

    /*
     * Be aware of fact that code text area always uses \n as line terminator
     */
    fun indexFile(project: P, file: File): FileIndexingResult

    fun indexDirectory(project: P, directory: File): Observable<out FileIndexingResult>

    fun indexProject(project: P,): Observable<out FileIndexingResult>
}
package ru.art2000.androraider.model.analyzer.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.Project

interface SimplifiedIndexer<in P: Project> : FilteringIndexer<P>, ProjectDirectoryProvider<P> {

    override fun indexProject(project: P): Flow<FileIndexingResult> {
        return project.examinedDirectories.map { indexDirectory(project, it) }.merge()
    }
}
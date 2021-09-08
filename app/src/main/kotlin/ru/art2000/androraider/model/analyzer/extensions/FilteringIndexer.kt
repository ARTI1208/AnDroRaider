package ru.art2000.androraider.model.analyzer.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.art2000.androraider.model.analyzer.Indexer
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.Project
import java.io.File

interface FilteringIndexer<in P: Project> : Indexer<P>, FileFilter {

    override fun indexDirectory(project: P, directory: File): Flow<FileIndexingResult> {
        return flow {
            directory.walk().filter(::isSuitableFile).forEach { file ->
                emit(indexFile(project, file))
            }
        }
    }
}
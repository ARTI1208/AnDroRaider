package ru.art2000.androraider.model.analyzer.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.art2000.androraider.model.analyzer.Analyzer
import ru.art2000.androraider.model.analyzer.AnalyzerSettings
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.Project
import java.io.File

interface FilteringAnalyzer<in P: Project, in S : AnalyzerSettings> : Analyzer<P, S>, FileFilter {

    override fun analyzeDirectory(project: P, settings: S, directory: File): Flow<FileAnalyzeResult> {
        return flow {
            directory.walk().filter(::isSuitableFile).forEach { file ->
                emit(analyzeFile(project, settings, file))
            }
        }
    }

}
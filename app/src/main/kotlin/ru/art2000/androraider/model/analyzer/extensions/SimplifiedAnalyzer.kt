package ru.art2000.androraider.model.analyzer.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import ru.art2000.androraider.model.analyzer.AnalyzerSettings
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.Project

interface SimplifiedAnalyzer<in P : Project, in S : AnalyzerSettings> : FilteringAnalyzer<P, S>, ProjectDirectoryProvider<P> {

    override fun analyzeProject(project: P, settings: S): Flow<FileAnalyzeResult> {
        return project.examinedDirectories.map { analyzeDirectory(project, settings, it) }.merge()
    }

}
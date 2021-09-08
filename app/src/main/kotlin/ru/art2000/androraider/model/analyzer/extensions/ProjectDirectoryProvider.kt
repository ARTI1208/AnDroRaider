package ru.art2000.androraider.model.analyzer.extensions

import ru.art2000.androraider.model.analyzer.result.Project
import java.io.File

interface ProjectDirectoryProvider<in P: Project> {

    val P.examinedDirectories: Iterable<File>

}
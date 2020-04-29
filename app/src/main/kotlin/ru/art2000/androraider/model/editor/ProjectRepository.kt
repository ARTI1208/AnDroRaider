package ru.art2000.androraider.model.editor

import javafx.scene.Node
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import java.io.File

private val projectForWindow = mutableMapOf<Int, ProjectAnalyzeResult>()

public fun getOrInitProject(window: Window, baseFolder: File): ProjectAnalyzeResult {
    var project = getProjectForWindow(window)

    if (project == null) {
        project = ProjectAnalyzeResult(baseFolder)
        projectForWindow[window.hashCode()] = project
    }

    return project
}

public fun getOrInitProject(node: Node, baseFolder: File): ProjectAnalyzeResult? {
    if (node.scene?.window == null)
        return null

    return getOrInitProject(node.scene.window, baseFolder)
}

public fun getProjectForWindow(window: Window): ProjectAnalyzeResult? {
    var currentWindow: Window? = window
    while (currentWindow != null) {
        val project = projectForWindow[currentWindow.hashCode()]
        if (project == null) {
            if (currentWindow is Stage) {
                currentWindow = currentWindow.owner
                continue
            }

            break
        } else {
            return project
        }
    }

    return null
}

public fun getProjectForNode(node: Node): ProjectAnalyzeResult? {
    if (node.scene?.window == null)
        return null

    return getProjectForWindow(node.scene.window)
}
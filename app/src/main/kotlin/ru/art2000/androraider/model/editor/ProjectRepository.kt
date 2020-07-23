package ru.art2000.androraider.model.editor

import javafx.scene.Node
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.model.analyzer.result.AndroidAppProject
import java.io.File

private val projectForWindow = mutableMapOf<Int, AndroidAppProject>()

public fun getOrInitProject(window: Window, baseFolder: File): AndroidAppProject {
    var project = getProjectForWindow(window)

    if (project == null) {
        project = AndroidAppProject(baseFolder)
        projectForWindow[window.hashCode()] = project
    }

    return project
}

public fun getProjectForWindow(window: Window): AndroidAppProject? {
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

public fun getProjectForNode(node: Node): AndroidAppProject? {
    if (node.scene?.window == null)
        return null

    return getProjectForWindow(node.scene.window)
}

public fun removeProject(window: Window) {
    projectForWindow.remove(window.hashCode())
}
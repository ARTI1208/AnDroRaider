package ru.art2000.androraider.view.editor.classes

import javafx.scene.control.TreeItem
import javafx.stage.Stage
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.model.analyzer.smali.types.SmaliMethod
import ru.art2000.androraider.model.analyzer.smali.types.SmaliPackage
import ru.art2000.androraider.view.BaseScene

class SmaliDataViewer(private val project: ProjectAnalyzeResult): Stage(), IDataViewerController by SmaliDataViewerController() {



    init {
        icons.add(App.LOGO)
        title = "Project Data Viewer"
        scene = BaseScene(root, 900.0, 600.0)

//        project.packages.forEach { it.parentPackage = project.rootPackage }
//        project.rootPackage.subpackages.addAll(project.packages)

        treeView.setCellFactory { SmaliDataTreeCell() }

        treeView.root = TreeItem(project.rootPackage)
        project.packages.mapNotNull { it.recheck() }.sortedBy { it.fullname }.forEach {
            val newItem = TreeItem(it)
            treeView.root.children.add(newItem)
            addChildren(newItem)
        }
    }

    private fun addChildren(item: TreeItem<SmaliComponent>) {
        val component = item.value
        if (component is SmaliPackage) {
            component.subpackages.mapNotNull { it.recheck() }.sortedBy { it.fullname }.forEach {
                val newItem = TreeItem<SmaliComponent>(it)
                item.children.add(newItem)
                addChildren(newItem)
            }
            component.classes.mapNotNull { it.recheck() }.sortedBy { it.fullname }.forEach {
                val newItem = TreeItem<SmaliComponent>(it)
                item.children.add(newItem)
                addChildren(newItem)
            }
        }
        if (component is SmaliClass) {
            component.methods.mapNotNull { it.recheck() }.sortedBy { it.fullname }.forEach {
                val newItem = TreeItem<SmaliComponent>(it)
                item.children.add(newItem)
                addChildren(newItem)
            }
//            component.classes.mapNotNull { it.recheck() }.sortedBy { it.fullname }.forEach {
//                val newItem = TreeItem<SmaliComponent>(it)
//                item.children.add(newItem)
//                addChildren(newItem)
//            }
        }
    }

}
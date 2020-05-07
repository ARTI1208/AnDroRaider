package ru.art2000.androraider.view.editor.classes

import javafx.scene.control.TreeView
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.mvp.IController

interface IDataViewerController: IController {

    val treeView: TreeView<SmaliComponent>
}
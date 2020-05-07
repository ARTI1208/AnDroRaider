package ru.art2000.androraider.view.editor.classes

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.TreeView
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.utils.getLayout

class SmaliDataViewerController: IDataViewerController {
    @FXML override lateinit var treeView: TreeView<SmaliComponent>


    override val root: Parent

    override val layoutFile = "class_viewer.fxml"

    init {
        val loader = javaClass.getLayout(layoutFile)
        loader.setController(this)
        root = loader.load()
    }
}
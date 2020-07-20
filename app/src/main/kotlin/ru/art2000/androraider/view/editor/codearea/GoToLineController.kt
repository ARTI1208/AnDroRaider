package ru.art2000.androraider.view.editor.codearea

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.TextField
import ru.art2000.androraider.utils.getLayout

class GoToLineController : IGoToLineController {

    @FXML override lateinit var hintLabel: Label
    @FXML override lateinit var textField: TextField


    override val root: Parent
    override val layoutFile = "goto_line_dialog.fxml"

    init {
        val loader = javaClass.getLayout(layoutFile)
        loader.setController(this)
        root = loader.load()
    }
}
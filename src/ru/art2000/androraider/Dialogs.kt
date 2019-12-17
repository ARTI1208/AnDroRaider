package ru.art2000.androraider

import javafx.geometry.Insets
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.stage.Window

@Suppress("SameParameterValue")
public fun showErrorMessage(title: String, message: String, owner: Window?) {
    val errorDialog = Dialog<Unit>()
    errorDialog.title = title
    val dialogPane = DialogPane()
    val mainLabel = Label(message)
    val dialogBox = HBox()
    dialogBox.children.addAll(mainLabel)
    dialogBox.padding = Insets(0.0, 0.0, 20.0, 0.0)

    dialogPane.content = dialogBox
    dialogPane.padding = Insets(10.0, 10.0, 10.0, 10.0)
    errorDialog.dialogPane = dialogPane
    errorDialog.initOwner(owner)
    errorDialog.dialogPane.buttonTypes.addAll(ButtonType.OK)
    errorDialog.showAndWait()
}
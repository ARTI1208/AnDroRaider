package ru.art2000.androraider

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Window

@Suppress("SameParameterValue", "RedundantVisibilityModifier")
public fun showErrorMessage(title: String, message: String, owner: Window?) {
    val errorDialog = Dialog<Unit>()
    errorDialog.title = title
    val mainLabel = Label(message)

    errorDialog.dialogPane = getBaseDialogPane(mainLabel)
    errorDialog.initOwner(owner)
    errorDialog.dialogPane.buttonTypes.addAll(ButtonType.OK)
    errorDialog.showAndWait()
}

@Suppress("RedundantVisibilityModifier")
public fun getBaseDialogPane(vararg node: Node): DialogPane {

    val dialogBox = VBox()
    dialogBox.children.addAll(node)
    dialogBox.padding = Insets(0.0, 0.0, 20.0, 0.0)

    val dialogPane = DialogPane()
    dialogPane.content = dialogBox
    dialogPane.padding = Insets(10.0, 10.0, 10.0, 10.0)

    return dialogPane
}
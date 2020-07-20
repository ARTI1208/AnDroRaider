package ru.art2000.androraider.view.dialogs

import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.model.App

@Suppress("SameParameterValue", "RedundantVisibilityModifier")
public fun showErrorMessage(title: String, message: String, owner: Window?) {
    val mainLabel = Label(message)

    val errorDialog = getBaseDialog<Unit>(mainLabel)
    errorDialog.title = title
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

public fun <R> getBaseDialog(vararg node: Node): Dialog<R> {
    return Dialog<R>().apply {
        icons.add(App.LOGO)
        dialogPane = getBaseDialogPane(*node)
    }
}

public val Dialog<*>.icons: ObservableList<Image>
    get() = (dialogPane.scene.window as Stage).icons
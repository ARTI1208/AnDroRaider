package ru.art2000.androraider.view.dialogs

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser
import ru.art2000.androraider.model.apktool.ApktoolCommand
import ru.art2000.androraider.utils.getLayout
import ru.art2000.androraider.utils.goThrough
import java.io.File

class RecompileDialog(baseFolder: File) : Dialog<List<ApktoolCommand>>() {

    init {
        val dialog = this
        val pane = DialogPane()
        val cont = javaClass.getLayout("recompile_options.fxml").load<VBox>()
        val group = ToggleGroup()
        val builtinFramework = cont.lookup("#builtinFrame") as RadioButton
        val customFramework = cont.lookup("#customFrame") as RadioButton
        val customFrameworkPath = cont.lookup("#-p") as TextField
        val frameworkPathBox = cont.lookup("#framePathBox") as Pane
        builtinFramework.toggleGroup = group
        customFramework.toggleGroup = group
        builtinFramework.selectedProperty().addListener { _, _, newValue ->
            run {
                frameworkPathBox.isDisable = newValue
            }
        }
        builtinFramework.isSelected = true
        val folderPath = cont.lookup("#path") as TextField
        val folderPathSelector = cont.lookup("#pathSelector") as Button
        val framePathSelector = cont.lookup("#customFramePathSelector") as Button
        folderPathSelector.onAction = EventHandler {
            DirectoryChooser()
                    .showDialog(dialog.owner)
                    ?.absolutePath
                    ?.also {
                        folderPath.text = it
                        folderPath.tooltip.text = it
                    }
        }
        framePathSelector.onAction = EventHandler {
            DirectoryChooser()
                    .showDialog(dialog.owner)
                    ?.absolutePath
                    ?.also {
                        customFrameworkPath.text = it
                        customFrameworkPath.tooltip.text = it
                    }
        }

        folderPath.text = baseFolder.parent
        folderPath.tooltip = Tooltip(folderPath.text)
        val fileName = cont.lookup("#fileName") as TextField
        fileName.text = baseFolder.name
        fileName.tooltip = Tooltip(fileName.text)
        cont.padding = Insets(0.0, 0.0, 20.0, 0.0)
        pane.content = cont
        pane.padding = Insets(10.0, 10.0, 10.0, 10.0)
        dialog.title = "Recompile options"
        dialog.dialogPane = pane
        val recompileButton = ButtonType("Recompile", ButtonBar.ButtonData.OK_DONE)
        pane.buttonTypes.addAll(
                recompileButton,
                ButtonType.CANCEL)
        val selectedOptions = ArrayList<ApktoolCommand>()
        dialog.setResultConverter {
            return@setResultConverter if (it == recompileButton) {
                cont.goThrough(selectedOptions)

                val filePath = folderPath.text + File.separator +
                        if (fileName.text.endsWith(".apk")) fileName.text else (fileName.text + ".apk")

                selectedOptions.add(ApktoolCommand(ApktoolCommand.General.OUTPUT, filePath))

                if (customFramework.isSelected)
                    selectedOptions.add(ApktoolCommand(
                            ApktoolCommand.General.FRAMEWORK_FOLDER_PATH,
                            customFrameworkPath.text))

                selectedOptions
            } else emptyList()
        }
    }
}
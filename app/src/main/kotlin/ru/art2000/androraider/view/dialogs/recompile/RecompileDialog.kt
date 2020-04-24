package ru.art2000.androraider.view.dialogs.recompile

import javafx.event.EventHandler
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.stage.DirectoryChooser
import ru.art2000.androraider.model.apktool.ApktoolCommand
import java.io.File

class RecompileDialog(private val baseFolder: File) : Dialog<List<ApktoolCommand>>(),
        IRecompilationController by RecompileDialogController() {

    init {
        title = "Recompile options"

        dialogPane = DialogPane()
        dialogPane.content = root

        val recompileButton = ButtonType("Recompile", ButtonBar.ButtonData.OK_DONE)
        dialogPane.buttonTypes.addAll(recompileButton, ButtonType.CANCEL)

        setResultConverter { buttonType ->
            return@setResultConverter if (buttonType === recompileButton) {
                val flagCheckBoxes = listOf(forceRebuildCheckBox)

                val selectedOptions = flagCheckBoxes.mapNotNull {
                    if (it.isSelected) ApktoolCommand(it.id) else null
                }.toMutableList()

                val filePath = filePathField.text + File.separator +
                        if (fileNameField.text.endsWith(".apk")) fileNameField.text else (fileNameField.text + ".apk")

                selectedOptions.add(ApktoolCommand.General.output(filePath))

                if (customFrameRadio.isSelected)
                    selectedOptions.add(ApktoolCommand.General.frameworkFolder(customFramePathField.text))

                selectedOptions
            } else emptyList()
        }

        setup()
    }

    private fun setup() {

        filePathFieldSelector.onAction = EventHandler {
            DirectoryChooser()
                    .showDialog(owner)
                    ?.absolutePath
                    ?.also { filePathField.text = it }
        }

        customFramePathSelector.onAction = EventHandler {
            DirectoryChooser()
                    .showDialog(owner)
                    ?.absolutePath
                    ?.also { customFramePathField.text = it }
        }

        fileNameField.text = baseFolder.name
        filePathField.text = baseFolder.parent
    }
}
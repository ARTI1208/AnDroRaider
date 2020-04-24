package ru.art2000.androraider.view.dialogs.decompile

import javafx.event.EventHandler
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.stage.DirectoryChooser
import ru.art2000.androraider.model.apktool.ApktoolCommand
import java.io.File

class DecompileDialog(val app: File) : Dialog<Pair<List<ApktoolCommand>, File>?>(),
        IDecompilationController by DecompileDialogController() {

    init {
        title = "Decompile options"

        dialogPane = DialogPane()
        dialogPane.content = root

        val decompileButton = ButtonType("Decompile", ButtonBar.ButtonData.OK_DONE)
        dialogPane.buttonTypes.addAll(decompileButton, ButtonType.CANCEL)

        setResultConverter { button ->
            return@setResultConverter if (button === decompileButton) {

                val flagCheckBoxes = listOf(noCodeDecompile, noResDecompile, overrideIfExistsCheckBox)

                val selectedOptions = flagCheckBoxes.mapNotNull {
                    if (it.isSelected) ApktoolCommand(it.id) else null
                }.toMutableList()

                val resultPath = projectPathField.text + File.separator + folderNameField.text
                selectedOptions.add(ApktoolCommand.General.output(resultPath))

                if (customFrameRadio.isSelected)
                    selectedOptions.add(ApktoolCommand.General.frameworkFolder(customFramePathField.text))

                selectedOptions to File(resultPath)
            } else null
        }

        setup()
    }

    private fun setup() {

        projectPathFieldSelector.onAction = EventHandler {
            val pathChooser = DirectoryChooser()
            val chosen = pathChooser.showDialog(owner)
            projectPathField.text = chosen.absolutePath
        }

        customFramePathSelector.onAction = EventHandler {
            val pathChooser = DirectoryChooser()
            val chosen = pathChooser.showDialog(owner)
            customFramePathField.text = chosen.absolutePath
        }

        projectPathField.text = app.parent
        folderNameField.text = app.nameWithoutExtension
    }
}
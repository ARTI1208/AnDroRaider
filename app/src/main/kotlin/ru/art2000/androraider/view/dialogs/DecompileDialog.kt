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

class DecompileDialog(val app: File) : Dialog<Pair<List<ApktoolCommand>, File>?>() {

    init {
        val pane = DialogPane()
        val cont = javaClass.getLayout("decompile_options.fxml").load<VBox>()
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
            val pathChooser = DirectoryChooser()
            val chosen = pathChooser.showDialog(owner)
            folderPath.text = chosen.absolutePath
        }
        framePathSelector.onAction = EventHandler {
            val pathChooser = DirectoryChooser()
            val chosen = pathChooser.showDialog(owner)
            customFrameworkPath.text = chosen.absolutePath
        }
        folderPath.text = app.parent
        val folderName = cont.lookup("#folderName") as TextField
        folderName.text = app.nameWithoutExtension
        cont.padding = Insets(0.0, 0.0, 20.0, 0.0)
        pane.content = cont
        pane.padding = Insets(10.0, 10.0, 10.0, 10.0)
        title = "Decompile options"
        dialogPane = pane
        val decompileButton = ButtonType("Decompile", ButtonBar.ButtonData.OK_DONE)
        pane.buttonTypes.addAll(
                decompileButton,
                ButtonType.CANCEL)
        val selectedOptions = ArrayList<ApktoolCommand>()
        setResultConverter { button ->
            return@setResultConverter if (button == decompileButton) {
                cont.goThrough(selectedOptions)
                val resultPath = folderPath.text + File.separator + folderName.text
                println(resultPath)
                selectedOptions.add(ApktoolCommand(
                        ApktoolCommand.General.OUTPUT, resultPath))
                if (customFramework.isSelected)
                    selectedOptions.add(ApktoolCommand(
                            ApktoolCommand.General.FRAMEWORK_FOLDER_PATH,
                            customFrameworkPath.text))

                selectedOptions to File(resultPath)
            } else null
        }
    }
}
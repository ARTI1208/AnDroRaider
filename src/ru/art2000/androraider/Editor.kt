package ru.art2000.androraider

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import javafx.stage.Window
import java.io.File
import java.io.IOException
import java.nio.file.Files

class Editor @Throws(IOException::class)
constructor(project: File) : Window() {

    companion object {
        @JvmStatic
        lateinit var baseFolder: File
    }

    var editorStage = Stage()

    init {
        baseFolder = if (project.isDirectory) project else project.parentFile
        val loader = FXMLLoader(javaClass.getResource(LoadUtils.getLayout("editor.fxml")))
        loader.setController(EditorLayoutController())
        val root = loader.load<Parent>()
        editorStage.icons.add(LoadUtils.getDrawable("logo.png"))
        editorStage.title = "${baseFolder.name} - Project Editor"
        editorStage.scene = Scene(root, 900.0, 600.0)
    }

    public override fun show() {
        editorStage.show()
    }

    inner class EditorLayoutController {
        @FXML
        lateinit var filesList: ListView<String>
        @FXML
        lateinit var homeButton: Button
        @FXML
        lateinit var upButton: Button
        @FXML
        lateinit var editorArea: TextArea
        @FXML
        lateinit var upBar: HBox
        @FXML
        lateinit var recompile: MenuItem
        @FXML
        lateinit var home: MenuItem
        @FXML
        lateinit var settings: MenuItem

        private var currentFolder = baseFolder

        private var currentEditingFile: File? = null
            set(value) {
                isFileChanged = true
                field = value
            }

        private var isFileChanged = false

        @Suppress("unused")
        fun initialize() {
            settings.onAction = EventHandler {
                Settings(editorStage).show()
            }
            recompile.onAction = EventHandler {
                val dialog = Dialog<Unit>()
                val pane = DialogPane()
                val cont = FXMLLoader(
                        javaClass.getResource(LoadUtils.getLayout("recompile_options.fxml"))).load<VBox>()
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
                    val chosen = pathChooser.showDialog(dialog.owner)
                    folderPath.text = chosen.absolutePath
                    folderPath.tooltip = Tooltip(folderPath.text)
                }
                framePathSelector.onAction = EventHandler {
                    val pathChooser = DirectoryChooser()
                    val chosen = pathChooser.showDialog(dialog.owner)
                    customFrameworkPath.text = chosen.absolutePath
                    customFrameworkPath.tooltip = Tooltip(customFrameworkPath.text)
                }

                folderPath.text = baseFolder.parent
                folderPath.tooltip = Tooltip(folderPath.text)
                val fileName = cont.lookup("#fileName") as TextField
                fileName.text = baseFolder.name
                fileName.tooltip = Tooltip(fileName.text)
                pane.content = cont
                pane.padding = Insets(10.0, 10.0, 0.0, 10.0)
                dialog.title = "Recompile options"
                dialog.initOwner(editorStage)
                dialog.dialogPane = pane
                val recompileButton = ButtonType("Recompile", ButtonBar.ButtonData.OK_DONE)
                pane.buttonTypes.addAll(
                        recompileButton,
                        ButtonType.CANCEL)
                val selectedOptions = ArrayList<ApktoolCommand>()
                dialog.setResultConverter {
                    if (it == recompileButton) {
                        cont.goThrough(selectedOptions)
                        selectedOptions.add(ApktoolCommand(
                                ApktoolCommand.General.OUTPUT, folderPath.text + "/" +
                                if (fileName.text.endsWith(".apk")) fileName.text else fileName.text + ".apk"))
                        if (customFramework.isSelected)
                            selectedOptions.add(ApktoolCommand(
                                    ApktoolCommand.General.FRAMEWORK_FOLDER_PATH,
                                    customFrameworkPath.text))
                        for (cmd in selectedOptions) {
                            System.out.println(cmd.tag)
                        }

                    }
                }
                dialog.showAndWait()
                if (selectedOptions.isNotEmpty())
                    ApkToolUtils.recompile(baseFolder, *selectedOptions.toTypedArray())
            }
            home.onAction = EventHandler {
                editorStage.close()
                Launcher().start(Stage())
            }
            updateDirContent(currentFolder)
            homeButton.onAction = EventHandler {
                updateDirContent(baseFolder)
            }
            upButton.onAction = EventHandler {
                if (currentFolder != baseFolder)
                    updateDirContent(currentFolder.parentFile)
            }
            filesList.prefHeightProperty().bind(editorStage.heightProperty().multiply(1.0))
            filesList.prefWidthProperty().bind(upBar.widthProperty().multiply(1.0))
            editorArea.prefWidthProperty().bind(editorStage.widthProperty().subtract(upBar.widthProperty()))
            filesList.onMouseClicked = EventHandler {
                val newFile = File(currentFolder.absolutePath + "/" + filesList.selectionModel.selectedItem)
                if (it.button === MouseButton.PRIMARY
                        && it.clickCount == 2) {
                    if (newFile.isDirectory) {
                        filesList.items.clear()
                        updateDirContent(newFile)
                    } else {
                        if (TypeDetector.isTextFile(newFile.name)) {
                            currentEditingFile = newFile
                            editorArea.text = String(Files.readAllBytes(newFile.toPath()))
                        }
                    }
                }
            }
            filesList.onKeyPressed = EventHandler {
                if (it.code == KeyCode.ENTER) {
                    val newFile = File(currentFolder.absolutePath + "/" + filesList.selectionModel.selectedItem)
                    if (newFile.isDirectory) {
                        filesList.items.clear()
                        updateDirContent(newFile)
                    } else {
                        if (TypeDetector.isTextFile(newFile.name)) {
                            currentEditingFile = newFile
                            editorArea.text = String(Files.readAllBytes(newFile.toPath()))
                        }
                    }
                }
                if (it.code == KeyCode.ESCAPE) {
                    if (currentFolder != baseFolder)
                        updateDirContent(currentFolder.parentFile)
                }
            }
            editorArea.textProperty().addListener { _, oldValue, newValue ->
                if (isFileChanged) {
                    isFileChanged = false
                    return@addListener
                }
                if (currentEditingFile != null && oldValue.isNotEmpty()) {
                    Files.write(currentEditingFile?.toPath(), newValue.toByteArray())
                }
            }
        }

        private fun updateDirContent(file: File) {
            filesList.items.clear()
            for (f in file.listFiles().filter { item -> item.isDirectory && !item.isHidden })
                filesList.items.add(f.name)
            for (f in file.listFiles().filter { item -> item.isFile && !item.isHidden })
                filesList.items.add(f.name)
            currentFolder = file
            filesList.selectionModel.select(0)
            editorStage.title = "${currentFolder.absolutePath.removePrefix(baseFolder.parent + "\\")} - Project Editor"
        }

    }
}

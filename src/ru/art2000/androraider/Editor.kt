package ru.art2000.androraider

import VectorTools.Main
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.*
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import javafx.stage.Window
import java.io.File
import java.io.IOException
import kotlin.collections.ArrayList


class Editor @Throws(IOException::class)
constructor(project: File) : Window() {

    val baseFolder: File = if (project.isDirectory) project else project.parentFile

    var editorStage = Stage()

    init {
        val loader = FXMLLoader(javaClass.getResource(LoadUtils.getLayout("editor.fxml")))
        loader.setController(EditorLayoutController())
        val root = loader.load<Parent>()
        editorStage.apply {
            icons.add(LoadUtils.getDrawable("logo.png"))
            title = "${baseFolder.name} - Project Editor"
            scene = Scene(root, 900.0, 600.0)
            scene.stylesheets.add(
                    this@Editor.javaClass.getResource(LoadUtils.getStyle("code.css")).toExternalForm())
        }
    }

    public override fun show() {
        editorStage.show()
    }

    inner class EditorLayoutController {
        // Menu/File
        @FXML
        lateinit var home: MenuItem
        @FXML
        lateinit var settings: MenuItem
        @FXML
        lateinit var recompile: MenuItem

        // Menu/Search
        @FXML
        lateinit var searchMenu: Menu
        @FXML
        lateinit var search: CustomMenuItem

        // Menu/Misc
        @FXML
        lateinit var examineFile: MenuItem

        // Main area
        @FXML
        lateinit var fileManagerView: FileManagerView
        @FXML
        lateinit var editorArea: CodeEditorArea

        lateinit var searchField: TextField

        @Suppress("unused")
        fun initialize() {

            editorStage.title = "${baseFolder.name} - Project Editor"
            editorStage.onShown = EventHandler {
                fileManagerView.requestFocus()
            }

            editorArea.prefWidthProperty().bind(editorStage.widthProperty().subtract(fileManagerView.prefWidthProperty()))
            editorArea.prefHeightProperty().bind(editorStage.heightProperty().multiply(1.0))
            fileManagerView.prefHeightProperty().bind(editorStage.heightProperty().multiply(1.0))

            setupMenu()
            setupCodeArea()
            setupFileExplorerView()
        }

        private fun createFileInfoDialog() {
            val fileInfoDialog = Dialog<Unit>()
            fileInfoDialog.title = getFileRelativePath(editorArea.observableCurrentEditingFile.value, baseFolder) ?:
                    "No file is currently editing"
            val dialogPane = DialogPane()
            val typeLabelTitle = Label("Type:")
            val typeLabelValue = Label(editorArea.observableCurrentEditingFile.value?.extension ?:
            "No file or extension")
            val typeBox = HBox()
            typeBox.children.addAll(typeLabelTitle, typeLabelValue)
            typeBox.spacing = 40.0
            val dialogBox = VBox()
            dialogBox.children.add(typeBox)
            if (TypeDetector.Image.isVectorDrawable(editorArea.observableCurrentEditingFile.value)) {
                val vectorImageLabelTitle = Label("Edit Vector Image:")
                val vectorImageButtonValue = Button("Edit...")
                vectorImageButtonValue.onAction = EventHandler {
                    //                    VectorTools.Main.launch(currentEditingFile?.path)
                    println(Main.openWindow())
                }
                val vectorImageBox = HBox()
                vectorImageBox.spacing = 40.0
                vectorImageBox.children.addAll(vectorImageLabelTitle, vectorImageButtonValue)
                dialogBox.children.add(vectorImageBox)
            }

            dialogPane.content = dialogBox
            dialogPane.padding = Insets(10.0, 10.0, 10.0, 10.0)
            fileInfoDialog.dialogPane = dialogPane
            fileInfoDialog.initOwner(editorStage)
            fileInfoDialog.dialogPane.buttonTypes.add(ButtonType.OK)
            fileInfoDialog.show()
        }

        private fun setupCodeArea() {
            editorArea.editorWindow = this@Editor
            editorArea.onKeyPressed = EventHandler {
                if (it.isControlDown && it.code == KeyCode.F) {
                    searchMenu.show()
                    searchField.requestFocus()
                    searchField.positionCaret(searchField.text.length)
                }
            }
            editorArea.observableCurrentEditingFile.addListener { _, _, fileNew ->
                if (fileNew != null)
                    editorStage.title = "${getFileRelativePath(fileNew, baseFolder)} - Project Editor"
            }
        }

        private fun setupMenu() {
            // Menu/File
            home.parentMenu.isMnemonicParsing = true
            home.onAction = EventHandler {
                editorStage.close()
                Launcher().start(Stage())
            }
            settings.onAction = EventHandler {
                Settings(editorStage).show()
            }
            recompile.accelerator = KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN)
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
                            println(cmd.tag)
                        }
                    }
                }
                dialog.showAndWait()
                if (selectedOptions.isNotEmpty()) {
                    val apk = ApkToolUtils.recompile(baseFolder, *selectedOptions.toTypedArray())
                    if (apk == null) {
                        val errorDialog = Dialog<Unit>()
                        errorDialog.initOwner(editorStage)
                        errorDialog.contentText = "An error occurred while recompiling"
                        errorDialog.dialogPane.buttonTypes.add(ButtonType.OK)
                        errorDialog.show()
                    }
                }
            }

            // Menu/Search
            searchMenu.isMnemonicParsing = true
            searchMenu.onShown = EventHandler {
                searchField.requestFocus()
                searchField.positionCaret(searchField.text.length)
                searchField.deselect()
            }
            search.apply {
                isHideOnClick = false
                styleClass.add("custom-menu-item")
                val mainBox = VBox()
                searchField = TextField()
                searchField.textProperty().addListener { _, _, now ->
                    editorArea.findAll(now)
                }
                searchField.onKeyPressed = EventHandler {
                    if (it.code == KeyCode.ENTER) {
                        editorArea.findNext()
                    }
                }
                searchField.promptText = "Type here..."
                val subBox = HBox()
                mainBox.spacing = 5.0
                subBox.alignment = Pos.BASELINE_CENTER
                subBox.spacing = 2.0
                subBox.background = Background(BackgroundFill(Paint.valueOf("#dedede"), CornerRadii.EMPTY, Insets.EMPTY))
                val prev = Button("Prev")
                prev.prefWidth = 100.0
                prev.onAction = EventHandler {
                    editorArea.findPrevious()
                }
                val next = Button("Next")
                next.prefWidth = 100.0
                next.onAction = EventHandler {
                    editorArea.findNext()
                }
                subBox.children.addAll(prev, next)
                mainBox.children.addAll(searchField, subBox)
                content = mainBox
                content.hoverProperty().addListener { _, _, now ->
                    if (now) {
                        searchField.requestFocus()
                        searchField.positionCaret(searchField.text.length)
                        searchField.deselect()
                    }
                }
            }

            // Menu/Misc
            examineFile.parentMenu.isMnemonicParsing = true
            examineFile.onAction = EventHandler {
                createFileInfoDialog()
            }
        }

        private fun setupFileExplorerView() {
            fileManagerView.setupWithBaseFolder(baseFolder)
            fileManagerView.onFileSelectedListeners.add(object : FileManagerView.FileSelectedListener{
                override fun fileSelected(oldFile: File?, newFile: File) {
                    if (TypeDetector.isTextFile(newFile.name)) {
                        editorArea.edit(newFile)
                        editorArea.requestFocus()
                    }
                }
            })
        }
    }
}

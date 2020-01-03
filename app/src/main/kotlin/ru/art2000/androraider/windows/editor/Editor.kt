package ru.art2000.androraider.windows.editor

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.*
import ru.art2000.androraider.analyzer.SmaliAnalyzer
import ru.art2000.androraider.apktool.ApkToolUtils
import ru.art2000.androraider.apktool.ApktoolCommand
import ru.art2000.androraider.windows.launcher.Launcher
import ru.art2000.androraider.utils.*
import ru.art2000.androraider.windows.Settings
import java.io.File
import java.io.IOException
import java.util.function.Consumer


class Editor @Throws(IOException::class)
constructor(project: File) : Window() {

    val baseFolder: File = if (project.isDirectory) project else project.parentFile

    var editorStage = Stage()
    var vectorImageEditorStage : Stage? = null

    private val smaliAnalyzer = SmaliAnalyzer(baseFolder)

    init {
        val loader = javaClass.getLayout("editor.fxml")
        loader.setController(EditorLayoutController())
        val root = loader.load<Parent>()

        editorStage.apply {
            icons.add(this@Editor.javaClass.getDrawable("logo.png"))
            title = "${baseFolder.name} - Project Editor"
            scene = Scene(root, 900.0, 600.0)
            scene.stylesheets.add(this@Editor.javaClass.getStyle("code.css"))
        }
    }

    public override fun show() {
        editorStage.show()
        generateProjectIndex()
    }

    private fun generateProjectIndex() {
        val indexingDialog = Dialog<Unit>()
        indexingDialog.title = "Indexing"
        indexingDialog.width = 400.0
        val mainLabel = Label("Starting indexing...")
        indexingDialog.dialogPane = getBaseDialogPane(mainLabel)
        indexingDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)
        (indexingDialog.dialogPane.lookupButton(ButtonType.CLOSE) as Button).isDisable = true

        smaliAnalyzer.onProjectAnalyzeEnded.add (Runnable {
            Platform.runLater {
                indexingDialog.close()
            }
        })

        smaliAnalyzer.onFileAnalyzeStarted.add(Consumer {
            Platform.runLater {
                mainLabel.text = "Indexing ${it.name}..."
            }
        })

        Thread {
            smaliAnalyzer.generateMap()
        }.start()

        indexingDialog.showAndWait()
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

        private lateinit var searchField: TextField

        private val searchMapping: HashMap<Searchable<String?>, String?> = HashMap()

        @Suppress("UNCHECKED_CAST")
        val currentSearchable: Searchable<String?>?
            get() {
                val focusedNode = editorStage.scene.focusOwner
                println(focusedNode)
                if (focusedNode is Searchable<*> && focusedNode.currentSearchValue is String?) {
                    return focusedNode as Searchable<String?>
                }

                return null
            }

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
            fileInfoDialog.title = getFileRelativePath(editorArea.observableCurrentEditingFile.value, baseFolder)
                    ?: "No file is currently editing"
            val typeLabelTitle = Label("Type:")
            val typeLabelValue = Label(editorArea.observableCurrentEditingFile.value?.extension
                    ?: "No file or extension")
            val typeBox = HBox()
            typeBox.children.addAll(typeLabelTitle, typeLabelValue)
            typeBox.spacing = 40.0

            val dialogPane = getBaseDialogPane(typeBox)

            if (TypeDetector.Image.isVectorDrawable(editorArea.observableCurrentEditingFile.value) && false) {
                val vectorImageLabelTitle = Label("Edit Vector Image:")
                val vectorImageButtonValue = Button("Edit...")
                vectorImageButtonValue.onAction = EventHandler {

//                    if (vectorImageEditorStage == null) {
//                        vectorImageEditorStage = Main.openWindow()
//                    } else {
//                        vectorImageEditorStage?.toFront().also {
//                            if (it == null)
//                                vectorImageEditorStage = null
//                        }
//                    }
                }
                val vectorImageBox = HBox()
                vectorImageBox.spacing = 40.0
                vectorImageBox.children.addAll(vectorImageLabelTitle, vectorImageButtonValue)
                (dialogPane.content as Pane).children.add(vectorImageBox)
            }

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
            recompile.accelerator = KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN)
            recompile.onAction = EventHandler {
                val dialog = Dialog<Unit>()
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
            editorStage.setOnShown {
                editorStage.scene.accelerators[KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN)] = Runnable {
                    searchMenu.show()
                }
            }
            searchMenu.isMnemonicParsing = true

            search.apply {
                isHideOnClick = false
                styleClass.add("custom-menu-item")
                val mainBox = VBox()
                searchField = TextField()
                searchField.textProperty().addListener { _, _, now ->
                    currentSearchable
                            ?.also {
                                searchMapping[it] = now
                            }
                            ?.findAll(now)
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
                    currentSearchable?.findPrevious()
                }
                val next = Button("Next")
                next.prefWidth = 100.0
                next.onAction = EventHandler {
                    currentSearchable?.findNext()
                }

                searchField.onKeyPressed = EventHandler {
                    if (it.code == KeyCode.ENTER) {
                        when {
                            it.isShiftDown && !prev.isDisable -> currentSearchable?.findPrevious()
                            !next.isDisable -> currentSearchable?.findNext()
                        }
                    }
                }

                subBox.children.addAll(prev, next)
                val searchScopeLabel = Label()
                searchScopeLabel.labelFor = searchField

                searchMenu.onShown = EventHandler {
                    searchField.requestFocus()
                    val searchScope = currentSearchable
                    searchScopeLabel.text = searchScope?.javaClass?.simpleName
                    if (searchScopeLabel.text == null) {
                        searchScopeLabel.text = "Unknown search scope"
                        searchField.isDisable = true
                        prev.isDisable = true
                        next.isDisable = true
                    } else {
                        searchField.isDisable = false
                        prev.isDisable = false
                        next.isDisable = false
                    }
                    searchField.text = searchMapping[searchScope] ?: ""
                    searchField.positionCaret(searchField.text.length)
                    searchField.deselect()
                }

                mainBox.children.addAll(searchScopeLabel, searchField, subBox)
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
            fileManagerView.onFileSelectedListeners.add(object : FileManagerView.FileSelectedListener {
                override fun fileSelected(oldFile: File?, newFile: File) {
                    if (TypeDetector.isTextFile(newFile.name)) {
//                        smaliAnalyzer.getAccessibleMethods(newFile)
//                        smaliAnalyzer.scanFile(newFile, true)
                        editorArea.edit(newFile)
                        editorArea.requestFocus()
                    }
                }
            })
        }
    }
}
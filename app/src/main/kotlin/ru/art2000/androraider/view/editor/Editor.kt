package ru.art2000.androraider.view.editor

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
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
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.analyzer.smali.SmaliAnalyzer
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.apktool.ApkToolUtils
import ru.art2000.androraider.model.editor.project.DirectoryObserver
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.getFileRelativePath
import ru.art2000.androraider.utils.getLayout
import ru.art2000.androraider.utils.getStyle
import ru.art2000.androraider.view.dialogs.RecompileDialog
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.launcher.Launcher
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.io.IOException
import java.io.PrintStream
import java.nio.file.StandardWatchEventKinds
import java.util.function.Consumer


class Editor @Throws(IOException::class)
constructor(project: File, vararg runnables: Runnable) : Window() {

    val baseFolder: File

    val projectObserver: DirectoryObserver

    var editorStage = Stage()

//    var vectorImageEditorStage: Stage? = null

    private val smaliAnalyzer: SmaliAnalyzer

    private lateinit var controller: EditorLayoutController

    private val onLoadRunnables = mutableListOf(*runnables)

    private val loadingLabel = Label()

    private val loadingDialog = getBaseDialog<Unit>(loadingLabel)

    init {
        if (!project.exists())
            println("creation " + project.mkdirs())

        println("constr ${project.isDirectory}")

        baseFolder = if (project.isDirectory) project else project.parentFile
        smaliAnalyzer = SmaliAnalyzer(baseFolder)
        projectObserver = DirectoryObserver(baseFolder)
        editorStage.apply {
            icons.add(App.LOGO)
            title = "${baseFolder.name} - Project Editor"
        }
    }

    public override fun show() {
        val loader = javaClass.getLayout("editor.fxml")

        controller = EditorLayoutController()
        loader.setController(controller)

        val root = loader.load<Parent>()

        editorStage.apply {
            scene = Scene(root, 900.0, 600.0)
            scene.stylesheets.add(this@Editor.javaClass.getStyle("code.css"))
            onHiding = EventHandler {
                controller.dispose()
            }
        }

        editorStage.show()
        showLoadingDialog()
    }

    private fun showLoadingDialog() {
        loadingDialog.title = "Loading..."
        loadingDialog.width = 400.0
        loadingLabel.text = "Loading project..."
        loadingDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)
        (loadingDialog.dialogPane.lookupButton(ButtonType.CLOSE) as Button).isDisable = true

        loadingDialog.show()
    }

    private fun generateProjectIndex(): Observable<SmaliClass> {
        loadingLabel.text = "Indexing smali..."

        return smaliAnalyzer
                .analyzeFilesInDir(smaliAnalyzer.filesRootDir)
                .doOnNext {
                    loadingLabel.text = "Indexing ${it.associatedFile?.name ?: "unknown file"}..."
//                    println("Indexing ${it.associatedFile?.name ?: "unknown file"}...")
                }.doOnComplete {
                    loadingDialog.hide()
                }
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

        @FXML
        lateinit var console: ConsoleView

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
            setupConsoleView()
            setupFileExplorerView()

            onSetupFinished()
        }

        fun dispose() {
            projectObserver.stop()
        }

        private fun onSetupFinished() {
            Observable
                    .fromIterable(onLoadRunnables)
                    .subscribeOn(Schedulers.io())
                    .doOnNext { it.run() }
                    .observeOn(JavaFxScheduler.platform())
                    .doOnComplete {
                        generateProjectIndex()
                                .doOnSubscribe {
                                    console.writeln("SmaliAnalyzer", "Analyze started")
                                }
                                .doOnComplete {
                                    console.writeln("SmaliAnalyzer", "Analyze ended")
                                    fileManagerView.updateFileList()
                                    projectObserver.start()
                                }
                                .subscribe()
                    }.subscribe()

        }

        private fun createFileInfoDialog() {
            val typeLabelTitle = Label("Type:")
            val typeLabelValue = Label(editorArea.observableCurrentEditingFile.value?.extension
                    ?: "No file or extension")
            val typeBox = HBox()
            typeBox.children.addAll(typeLabelTitle, typeLabelValue)
            typeBox.spacing = 40.0

            val fileInfoDialog = getBaseDialog<Unit>(typeBox)
            fileInfoDialog.title = getFileRelativePath(editorArea.observableCurrentEditingFile.value, baseFolder)
                    ?: "No file is currently editing"

            @Suppress("SimplifyBooleanWithConstants") // not meaningful now
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
                (fileInfoDialog.dialogPane.content as Pane).children.add(vectorImageBox)
            }

            fileInfoDialog.initOwner(editorStage)
            fileInfoDialog.dialogPane.buttonTypes.add(ButtonType.OK)
            fileInfoDialog.show()
        }

        private fun setupCodeArea() {
            editorArea.onKeyPressed = EventHandler {
                if (it.isControlDown && it.code == KeyCode.F) {
                    searchMenu.show()
                    searchField.requestFocus()
                    searchField.positionCaret(searchField.text.length)
                }
            }
            editorArea.observableCurrentEditingFile.addListener { _, _, fileNew ->
                if (fileNew != null) {
                    editorStage.title = "${getFileRelativePath(fileNew, baseFolder)} - Project Editor"
                    if (fileNew.extension == "smali")
                        editorArea.currentSmaliClass = smaliAnalyzer.analyzeFile(fileNew)
                } else {
                    editorArea.currentSmaliClass = null
                }
            }
            editorArea.beforeFileWrittenListeners.add(Consumer {
                projectObserver.stop()
            })
            editorArea.afterFileWrittenListeners.add(Consumer {
                projectObserver.resume()
            })

            editorArea.syntaxAnalyzer = smaliAnalyzer

//            editorArea.onInputListeners.add(Consumer {
//                if (it == null) {
//                    editorArea.currentSmaliClass = null
//                } else {
//                    editorArea.currentSmaliClass = smaliAnalyzer.analyzeFile(it)
//                }
//            })

            projectObserver.addListener { file, kind ->
                if (file.isDirectory)
                    return@addListener

                if (file.absolutePath == editorArea.currentEditingFile?.absolutePath) {
                    when (kind) {
                        StandardWatchEventKinds.ENTRY_DELETE -> editorArea.edit(null)
                        StandardWatchEventKinds.ENTRY_MODIFY -> editorArea.edit(file, true)
                    }
                }
            }
        }

        private fun setupConsoleView() {
            App.currentStreamOutput = console
            projectObserver.addListener { file, kind ->
                Platform.runLater {
                    console.writeln("ProjectObserver", "File ${getFileRelativePath(file, baseFolder)} was $kind")
                }
            }
            System.setOut(PrintStream(console.getOutputStream()))
//            System.setErr(PrintStream(console.getErrorStream()))
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
                val dialog = RecompileDialog(baseFolder)

                dialog.initOwner(editorStage)

                val selectedOptions = dialog.showAndWait().get()

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
                        editorArea.edit(newFile)
                        editorArea.requestFocus()
                    }
                }
            })
            projectObserver.addListener { _, _ ->
                fileManagerView.updateFileList()
            }
        }
    }
}

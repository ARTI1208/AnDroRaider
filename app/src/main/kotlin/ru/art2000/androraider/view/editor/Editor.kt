package ru.art2000.androraider.view.editor

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.event.EventHandler
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
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.apktool.ApkToolUtils
import ru.art2000.androraider.presenter.editor.EditorPresenter
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.getFileRelativePath
import ru.art2000.androraider.utils.getLayout
import ru.art2000.androraider.utils.getStyle
import ru.art2000.androraider.view.View
import ru.art2000.androraider.view.dialogs.RecompileDialog
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.launcher.Launcher
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.io.IOException
import java.io.PrintStream
import java.nio.file.StandardWatchEventKinds


class Editor @Throws(IOException::class)
constructor(val projectFolder: File, vararg runnables: Runnable) : Window(), View {

    var editorStage = Stage()

    private var controller = EditorController()

    private val onLoadRunnables = mutableListOf(*runnables)

    private val loadingLabel = Label()

    private val loadingDialog = getBaseDialog<Unit>(loadingLabel)

    override val presenter: EditorPresenter

    private val searchMapping: HashMap<Searchable<String?>, String?> = HashMap()

    private val project : ProjectAnalyzeResult

    init {
        if (!projectFolder.exists())
            projectFolder.mkdirs()

        project = ProjectAnalyzeResult(projectFolder)
        presenter = EditorPresenter(projectFolder)

        editorStage.apply {
            icons.add(App.LOGO)
            title = "${projectFolder.name} - Project Editor"
        }

        val loader = javaClass.getLayout("editor.fxml")

        loader.setController(controller)

        val root = loader.load<Parent>()

        editorStage.apply {
            scene = Scene(root, 900.0, 600.0)
            scene.stylesheets.add(this@Editor.javaClass.getStyle("code.css"))
            onHiding = EventHandler {
                presenter.dispose()
            }
        }


        editorStage.title = "${projectFolder.name} - Project Editor"
        editorStage.onShown = EventHandler {
            controller.fileManagerView.requestFocus()
        }

        controller.apply {
            editorArea.prefWidthProperty().bind(editorStage.widthProperty().subtract(fileManagerView.prefWidthProperty()))
            editorArea.prefHeightProperty().bind(editorStage.heightProperty().multiply(1.0))
            fileManagerView.prefHeightProperty().bind(editorStage.heightProperty().multiply(1.0))
        }

        setupConsoleView()
        setupCodeArea()
        setupFileExplorerView()
        setupMenu()
    }

    public override fun show() {
        editorStage.show()
        showLoadingDialog()
        onSetupFinished()
    }

    private fun showLoadingDialog() {
        loadingDialog.title = "Loading..."
        loadingDialog.width = 400.0
        loadingLabel.text = "Loading project..."
        loadingDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)
        (loadingDialog.dialogPane.lookupButton(ButtonType.CLOSE) as Button).isDisable = true

        loadingDialog.show()
    }

    private fun generateProjectIndex(): Observable<out FileAnalyzeResult> {
        loadingLabel.text = "Indexing project..."

        return project.indexProject()
    }

    @Suppress("UNCHECKED_CAST")
    val currentSearchable: Searchable<String?>?
        get() {
            val focusedNode = editorStage.scene.focusOwner
            if (focusedNode is Searchable<*> && focusedNode.currentSearchValue is String?) {
                return focusedNode as Searchable<String?>
            }

            return null
        }

    private fun onSetupFinished() {
        val onLoadObservable = Observable
                .fromIterable(onLoadRunnables)
                .subscribeOn(Schedulers.io())
                .doOnNext { it.run() }

        val projectIndexerObservable = generateProjectIndex()
                .observeOn(JavaFxScheduler.platform())
                .doOnNext {
                    loadingLabel.text = "Indexing $it..."
                }.doOnSubscribe {
                    App.currentStreamOutput.writeln("ProjectAnalyzer", "Analyze started")
                }
                .doOnComplete {
                    App.currentStreamOutput.writeln("ProjectAnalyzer", "Analyze ended")

                    loadingDialog.close()

                    controller.fileManagerView.updateFileList()
                    presenter.projectObserver.start()
                }

        Observable.concat(onLoadObservable, projectIndexerObservable).subscribe()
    }

    private fun setupCodeArea() {
        controller.apply {
            editorArea.project = project
        }
    }

    private fun setupFileExplorerView() {
        controller.apply {
            fileManagerView.setupWithProject(project)

            fileManagerView.onFileSelectedListeners.add(object : FileManagerView.FileSelectedListener {
                override fun fileSelected(oldFile: File?, newFile: File) {
                    if (TypeDetector.isTextFile(newFile.name)) {
                        presenter.openFile(newFile)
                        editorArea.edit(newFile)
                        editorArea.requestFocus()
                    }
                }
            })

            presenter.projectObserver.addListener { _, kind ->
                if (kind == StandardWatchEventKinds.ENTRY_CREATE
                        || kind == StandardWatchEventKinds.ENTRY_DELETE) {

                    fileManagerView.updateFileList()
                }
            }
        }
    }

    private fun setupConsoleView() {
        controller.apply {
            App.currentStreamOutput = console
            System.setOut(PrintStream(console.getOutputStream()))
//            System.setErr(PrintStream(console.getErrorStream()))
        }
    }

    private fun setupMenu() {
        controller.apply {
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
                val dialog = RecompileDialog(projectFolder)

                dialog.initOwner(editorStage)

                val selectedOptions = dialog.showAndWait().get()

                if (selectedOptions.isNotEmpty()) {
                    val apk = ApkToolUtils.recompile(projectFolder, *selectedOptions.toTypedArray())
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
    }

    private fun createFileInfoDialog() {
        controller.apply {
            val typeLabelTitle = Label("Type:")
            val typeLabelValue = Label(editorArea.observableCurrentEditingFile.value?.extension
                    ?: "No file or extension")
            val typeBox = HBox()
            typeBox.children.addAll(typeLabelTitle, typeLabelValue)
            typeBox.spacing = 40.0

            val fileInfoDialog = getBaseDialog<Unit>(typeBox)
            fileInfoDialog.title = getFileRelativePath(editorArea.observableCurrentEditingFile.value, projectFolder)
                    ?: "No file is currently editing"

            fileInfoDialog.initOwner(editorStage)
            fileInfoDialog.dialogPane.buttonTypes.add(ButtonType.OK)
            fileInfoDialog.show()
        }
    }
}

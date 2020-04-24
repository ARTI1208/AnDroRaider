package ru.art2000.androraider.view.editor

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.HBox
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.apktool.ApkToolUtils
import ru.art2000.androraider.presenter.editor.EditorPresenter
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.getFileRelativePath
import ru.art2000.androraider.utils.getStyle
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.dialogs.recompile.RecompileDialog
import ru.art2000.androraider.view.dialogs.showErrorMessage
import ru.art2000.androraider.view.launcher.Launcher
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.io.IOException
import java.io.PrintStream
import java.nio.file.StandardWatchEventKinds


class Editor @Throws(IOException::class)
constructor(private val projectFolder: File, vararg runnables: Runnable) :
        Window(),
        IEditorView,
        IEditorController by EditorController() {

    private var editorStage = Stage()

    private val onLoadRunnables = mutableListOf(*runnables)

    private val loadingLabel = Label()

    private val loadingDialog = getBaseDialog<Unit>(loadingLabel)

    override val presenter: EditorPresenter

    private val project: ProjectAnalyzeResult

    init {
        if (!projectFolder.exists())
            projectFolder.mkdirs()

        project = ProjectAnalyzeResult(projectFolder)
        presenter = EditorPresenter(projectFolder)

        editorStage.apply {
            icons.add(App.LOGO)
            title = "${projectFolder.name} - Project Editor"

            scene = Scene(root, 900.0, 600.0)
            onHiding = EventHandler {
                presenter.dispose()
            }
        }

        editorStage.onShown = EventHandler {
            fileManagerView.requestFocus()
        }

        editorArea.prefWidthProperty().bind(editorStage.widthProperty().subtract(fileManagerView.prefWidthProperty()))
        fileManagerView.prefHeightProperty().bind(editorStage.heightProperty())

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

                    fileManagerView.updateFileList()
                    presenter.projectObserver.start()
                }

        Observable.concat(onLoadObservable, projectIndexerObservable).subscribe()
    }

    private fun setupCodeArea() {
        editorArea.project = project
    }

    private fun setupFileExplorerView() {
        fileManagerView.setupWithProject(project)

        fileManagerView.onFileSelectedListeners.add { _, newFile ->
            if (TypeDetector.isTextFile(newFile.name)) {
                presenter.openFile(newFile)
                editorArea.edit(newFile)
                editorArea.requestFocus()
            }
        }

        presenter.projectObserver.addListener { _, kind ->
            if (kind == StandardWatchEventKinds.ENTRY_CREATE
                    || kind == StandardWatchEventKinds.ENTRY_DELETE) {

                fileManagerView.updateFileList()
            }
        }
    }

    private fun setupConsoleView() {
        App.currentStreamOutput = console
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
            val dialog = RecompileDialog(projectFolder)
            dialog.initOwner(editorStage)
            val selectedOptions = dialog.showAndWait().get()
            if (selectedOptions.isNotEmpty()) {
                ApkToolUtils.recompile(projectFolder, *selectedOptions.toTypedArray())
                        ?: showErrorMessage(
                                "Recompile error",
                                "An error occurred while recompiling",
                                editorStage)
            }
        }

        // Menu/Search
        searchMenu.isMnemonicParsing = true
        searchMenu.onShown = EventHandler {
            search.searchField.requestFocus()
        }

        editorStage.setOnShown {
            editorStage.scene.accelerators[KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN)] = Runnable {
                searchMenu.show()
            }
        }

        editorStage.scene.focusOwnerProperty().addListener { _, _, newValue ->
            @Suppress("UNCHECKED_CAST")
            search.currentSearchable = newValue as? Searchable<String>
        }

        // Menu/Misc
        examineFile.parentMenu.isMnemonicParsing = true
        examineFile.onAction = EventHandler {
            createFileInfoDialog()
        }
    }

    private fun createFileInfoDialog() {
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

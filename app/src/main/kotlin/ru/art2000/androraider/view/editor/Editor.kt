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
import javafx.stage.WindowEvent
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.analyzer.smali.types.SmaliPackage
import ru.art2000.androraider.model.apktool.ApkToolUtils
import ru.art2000.androraider.model.editor.getOrInitProject
import ru.art2000.androraider.presenter.editor.EditorPresenter
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.relativeTo
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.dialogs.recompile.RecompileDialog
import ru.art2000.androraider.view.dialogs.showErrorMessage
import ru.art2000.androraider.view.launcher.Launcher
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.io.IOException
import java.nio.file.StandardWatchEventKinds
import ru.art2000.androraider.model.io.println
import ru.art2000.androraider.model.io.registerStreamOutput
import ru.art2000.androraider.model.io.unregisterStreamOutput

@Suppress("ReactiveStreamsUnusedPublisher")
class Editor @Throws(IOException::class)
constructor(private val projectFolder: File, vararg runnables: Runnable) :
        Stage(),
        IEditorView,
        IEditorController by EditorController() {

    private val onLoadRunnables = mutableListOf(*runnables)

    private val loadingLabel = Label()

    private val loadingDialog = getBaseDialog<Unit>(loadingLabel)

    override val presenter: EditorPresenter

    init {
        if (!projectFolder.exists())
            projectFolder.mkdirs()

        icons.add(App.LOGO)
        title = "${projectFolder.name} - Project Editor"
        scene = Scene(root, 900.0, 600.0)
        presenter = EditorPresenter(this, projectFolder)

        addEventHandler(WindowEvent.WINDOW_SHOWN) {
            registerStreamOutput(this, console)
            showLoadingDialog()
            onSetupFinished()
            fileManagerView.requestFocus()
        }

        addEventHandler(WindowEvent.WINDOW_HIDDEN) {
            unregisterStreamOutput(this)
            presenter.dispose()
        }

        editorArea.prefWidthProperty().bind(widthProperty().subtract(fileManagerView.prefWidthProperty()))
        fileManagerView.prefHeightProperty().bind(heightProperty())

        setupFileExplorerView()
        setupMenu()
    }

    private fun showLoadingDialog() {
        loadingDialog.title = "Loading..."
        loadingDialog.width = 400.0
        loadingLabel.text = "Loading project..."
        loadingDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)
        (loadingDialog.dialogPane.lookupButton(ButtonType.CLOSE) as Button).isDisable = true

        loadingDialog.show()
    }

    private fun onSetupFinished() {
        val onLoadObservable = Observable
                .fromIterable(onLoadRunnables)
                .subscribeOn(Schedulers.io())
                .doOnNext { it.run() }

        val projectIndexerObservable = (presenter.generateProjectIndex() ?: Observable.empty())
                .observeOn(JavaFxScheduler.platform())
                .doOnNext {
                    loadingLabel.text = "Indexing $it..."
                }.doOnSubscribe {
                    println(this, "ProjectAnalyzer", "Analyze started")
                }
                .doOnComplete {
                    println(this,"ProjectAnalyzer", "Analyze ended")
                    loadingDialog.close()
                    fileManagerView.updateFileList()
                    presenter.projectObserver.start()
                }

        loadingLabel.text = "Indexing project..."
        Observable.concat(onLoadObservable, projectIndexerObservable).subscribe()
    }

    private fun setupFileExplorerView() {
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

    private fun setupMenu() {
        // Menu/File
        home.parentMenu.isMnemonicParsing = true
        home.onAction = EventHandler {
            hide()
            Launcher().show()
        }
        settings.onAction = EventHandler {
            Settings(this).show()
        }
        recompile.accelerator = KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN)
        recompile.onAction = EventHandler {
            val dialog = RecompileDialog(projectFolder)
            dialog.initOwner(this)
            val selectedOptions = dialog.showAndWait().get()
            if (selectedOptions.isNotEmpty()) {
                ApkToolUtils.recompile(projectFolder, *selectedOptions.toTypedArray())
                        ?: showErrorMessage(
                                "Recompile error",
                                "An error occurred while recompiling",
                                this)
            }
        }

        // Menu/Search
        searchMenu.isMnemonicParsing = true
        searchMenu.onShown = EventHandler {
            search.searchField.requestFocus()
        }

        addEventHandler(WindowEvent.WINDOW_SHOWN) {
            scene.accelerators[KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN)] = Runnable {
                searchMenu.show()
            }
        }

        scene.focusOwnerProperty().addListener { _, _, newValue ->
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
        val typeLabelValue = Label(editorArea.currentEditingFile?.extension
                ?: "No file or extension")
        val typeBox = HBox()
        typeBox.children.addAll(typeLabelTitle, typeLabelValue)
        typeBox.spacing = 40.0

        val fileInfoDialog = getBaseDialog<Unit>(typeBox)
        fileInfoDialog.title = editorArea.currentEditingFile.relativeTo(projectFolder)
                ?: "No file is currently editing"

        fileInfoDialog.initOwner(this)
        fileInfoDialog.dialogPane.buttonTypes.add(ButtonType.OK)
        fileInfoDialog.show()
    }
}

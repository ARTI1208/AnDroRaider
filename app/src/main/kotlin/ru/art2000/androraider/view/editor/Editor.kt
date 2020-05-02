package ru.art2000.androraider.view.editor

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.WindowEvent
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.apktool.ApkToolUtils
import ru.art2000.androraider.model.io.StreamOutput
import ru.art2000.androraider.model.io.println
import ru.art2000.androraider.model.io.registerStreamOutput
import ru.art2000.androraider.model.io.unregisterStreamOutput
import ru.art2000.androraider.presenter.editor.EditorPresenter
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.dialogs.recompile.RecompileDialog
import ru.art2000.androraider.view.dialogs.showErrorMessage
import ru.art2000.androraider.view.launcher.Launcher
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.io.IOException
import java.nio.file.StandardWatchEventKinds
import java.util.function.Consumer
import kotlin.concurrent.thread

@Suppress("ReactiveStreamsUnusedPublisher")
class Editor @Throws(IOException::class)
constructor(private val projectFolder: File, vararg runnables: Consumer<StreamOutput>) :
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

        codeEditorContainer.background = Background(BackgroundFill(Color.GRAY, null, null))

        editorArea.prefWidthProperty().bind(widthProperty().subtract(fileManagerView.prefWidthProperty()))
        editorArea.prefHeightProperty().bind(codeEditorContainer.heightProperty().subtract(editorTabPane.heightProperty()))
        fileManagerView.prefHeightProperty().bind(heightProperty())

        setupTabs()
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
                .doOnNext { it.accept(console) }

        val projectIndexerObservable = (presenter.generateProjectIndex() ?: Observable.empty())
                .observeOn(JavaFxScheduler.platform())
                .doOnNext {
                    loadingLabel.text = "Indexing $it..."
                }.doOnSubscribe {
                    println(this, "ProjectAnalyzer", "Analyze started")
                }
                .doOnComplete {
                    println(this, "ProjectAnalyzer", "Analyze ended")
                    loadingDialog.close()
                    fileManagerView.updateFileList()
                    presenter.projectObserver.start()
                }

        loadingLabel.text = "Indexing project..."
        Observable.concat(onLoadObservable, projectIndexerObservable).subscribe()
    }

    private fun setupTabs() {
        editorTabPane.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            editorArea.edit(newValue.userData as File?)
        }

        editorTabPane.tabs.addListener { c: ListChangeListener.Change<out Tab>? ->
            if (editorTabPane.tabs.isEmpty()) {
                editorTabPane.isVisible = false
                editorArea.isVisible = false
                editorArea.edit(null)
            } else {
                editorTabPane.isVisible = true
                editorArea.isVisible = true
            }
        }
        editorTabPane.tabClosingPolicy = TabPane.TabClosingPolicy.ALL_TABS
        editorArea.isVisible = false
    }

    private fun setupFileExplorerView() {
        fileManagerView.onFileSelectedListeners.add { _, newFile ->
            if (TypeDetector.isTextFile(newFile.name)) {
                val indexedTab = editorTabPane.tabs.withIndex().find { it.value.userData == newFile }
                val position = if (indexedTab == null) {
                    val newTab = Tab(newFile.name)
                    newTab.userData = newFile
                    val pos = editorTabPane.selectionModel.selectedIndex + 1
                    editorTabPane.tabs.add(pos, newTab)
                    pos
                } else {
                    indexedTab.index
                }

                editorTabPane.selectionModel.select(position)
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
                thread {
                    ApkToolUtils.recompile(projectFolder, *selectedOptions.toTypedArray(), output = console)
                            ?: showErrorMessage(
                                    "Recompile error",
                                    "An error occurred while recompiling",
                                    this)
                }
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
        fileInfoDialog.title = editorArea.currentEditingFile?.relativeTo(projectFolder)?.path
                ?: "No file is currently editing"

        fileInfoDialog.initOwner(this)
        fileInfoDialog.dialogPane.buttonTypes.add(ButtonType.OK)
        fileInfoDialog.show()
    }
}

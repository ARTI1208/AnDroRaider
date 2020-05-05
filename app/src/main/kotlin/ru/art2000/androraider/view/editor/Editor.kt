package ru.art2000.androraider.view.editor

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.application.Platform
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.HBox
import javafx.scene.layout.Region
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.WindowEvent
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.apktool.ApkToolUtils
import ru.art2000.androraider.model.editor.getOrInitProject
import ru.art2000.androraider.model.editor.getProjectForWindow
import ru.art2000.androraider.model.io.StreamOutput
import ru.art2000.androraider.model.io.println
import ru.art2000.androraider.model.io.registerStreamOutput
import ru.art2000.androraider.model.io.unregisterStreamOutput
import ru.art2000.androraider.presenter.editor.EditorPresenter
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.view.BaseScene
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.dialogs.recompile.RecompileDialog
import ru.art2000.androraider.view.dialogs.showErrorMessage
import ru.art2000.androraider.view.editor.codearea.CodeEditorArea
import ru.art2000.androraider.view.editor.codearea.CodeEditorScrollPane
import ru.art2000.androraider.view.launcher.Launcher
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.io.IOException
import java.nio.file.StandardWatchEventKinds
import java.util.*
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
        icons.add(App.LOGO)
        title = "Project Editor"
        scene = BaseScene(root, 900.0, 600.0)

        presenter = EditorPresenter(this, projectFolder)

        addEventHandler(WindowEvent.WINDOW_SHOWN) {
            registerStreamOutput(this, console)
            showLoadingDialog()
            loadProject()
        }

        addEventHandler(WindowEvent.WINDOW_HIDDEN) {
            unregisterStreamOutput(this)
            presenter.dispose()
        }

        codeEditorContainer.background = Background(BackgroundFill(Color.GRAY, null, null))

        editorTabPane.prefHeightProperty().bind(codeEditorContainer.heightProperty())
        editorTabPane.prefWidthProperty().bind(widthProperty().subtract(fileManagerView.prefWidthProperty()))

        fileManagerView.prefHeightProperty().bind(heightProperty())
    }

    private fun showLoadingDialog() {
        loadingDialog.title = "Loading..."
        loadingDialog.width = 400.0
        loadingLabel.text = "Loading project..."
        loadingDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)
        (loadingDialog.dialogPane.lookupButton(ButtonType.CLOSE) as Button).isDisable = true

        loadingDialog.show()
    }

    private fun onLoad() {
        if (!projectFolder.exists())
            projectFolder.mkdirs()

        loadingLabel.text = "Indexing project..."
        getOrInitProject(this, projectFolder)
        (presenter.generateProjectIndex() ?: Observable.empty<SmaliClass>())
                .observeOn(JavaFxScheduler.platform())
                .doOnNext {
                    loadingLabel.text = "Indexing $it..."
                }.doOnSubscribe {
                    println(this, "ProjectAnalyzer", "Analyze started at ${Date()}")
                }
                .doOnComplete {
                    println(this, "ProjectAnalyzer", "Analyze ended at ${Date()}")
                    loadingDialog.close()
                    presenter.startFileObserver()
                }.subscribe()

        title = "${projectFolder.name} - Project Editor"

        setupMenu()
        setupFileExplorerView()
        setupTabs()

        fileManagerView.requestFocus()
    }

    private fun loadProject() {
        loadingLabel.text = "Loading project..."
        Observable
                .fromIterable(onLoadRunnables)
                .subscribeOn(Schedulers.io())
                .doOnNext { it.accept(console) }
                .observeOn(JavaFxScheduler.platform())
                .doOnComplete { onLoad() }
                .subscribe()
    }

    @Suppress("RedundantLambdaArrow")
    private fun setupTabs() {
        editorTabPane.selectionModel.selectedIndexProperty().addListener { _, _, newValue ->
            if (newValue != null)
                presenter.updateTabHistory(newValue as Int)
        }

        editorTabPane.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
//            val timer = Timer()
//            timer.schedule(object : TimerTask() {
//                override fun run() {
//                    Platform.runLater {
//                        newValue?.content?.requestFocus()
//                        timer.cancel()
//                        timer.purge()
//                    }
//                }
//            }, 25)

            Platform.runLater {
                newValue?.content?.requestFocus()
            }
        }

        editorTabPane.eventDispatcher = null

        addEventHandler(KeyEvent.KEY_PRESSED) {
            if (it.isShortcutDown && it.code == KeyCode.TAB) {
                if (presenter.openedFilesOrder.size > 1)
                    editorTabPane.selectionModel.select(presenter.openedFilesOrder[1])

                it.consume()
            }
        }

        editorTabPane.tabs.addListener { c: ListChangeListener.Change<out Tab> ->
            while (c.next()) {
                if (c.wasRemoved()) {
                    (c.from..c.to).forEach { presenter.removeTabFromHistory(it) }
                }
            }

            editorTabPane.isVisible = editorTabPane.tabs.isNotEmpty()
        }

        editorTabPane.tabClosingPolicy = TabPane.TabClosingPolicy.ALL_TABS
    }

    private fun setupFileExplorerView() {
        fileManagerView.onFileSelectedListeners.add { _, newFile ->
            if (TypeDetector.isTextFile(newFile.name)) {
                val indexedTab = editorTabPane.tabs.withIndex().find { it.value.userData == newFile }
                val position = if (indexedTab == null) {
                    val newTab = Tab(newFile.name)
                    newTab.userData = newFile
                    newTab.content = CodeEditorScrollPane(CodeEditorArea(newFile))
                    val pos = editorTabPane.selectionModel.selectedIndex + 1
                    editorTabPane.tabs.add(pos, newTab)
                    pos
                } else {
                    indexedTab.index
                }

                editorTabPane.selectionModel.select(position)
            }
        }

        presenter.addFileListener { file, kind ->
            when(kind) {
                StandardWatchEventKinds.ENTRY_DELETE -> fileManagerView.removeBranch(file)
                StandardWatchEventKinds.ENTRY_CREATE -> fileManagerView.addBranch(file)
            }
        }
        fileManagerView.updateFileList()
    }

    private fun setupMenu() {
        // Menu/File
        home.onAction = EventHandler {
            hide()
            Launcher().show()
        }
        settings.onAction = EventHandler {
            Settings(this).show()
        }
        recompile.accelerator = KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN)
        recompile.onAction = EventHandler {
            val dialog = RecompileDialog(projectFolder, getProjectForWindow(this)?.projectSettings)
            dialog.initOwner(this)
            val selectedOptions = dialog.showAndWait().get()
            if (selectedOptions.isNotEmpty()) {
                thread {
//                    presenter.stopObservation()
                    ApkToolUtils.recompile(projectFolder, *selectedOptions.toTypedArray(), output = console)
                            ?: showErrorMessage(
                                    "Recompile error",
                                    "An error occurred while recompiling",
                                    this)
//                    presenter.observe()
                }
            }
        }

        // Menu/Search
        searchMenu.accelerator = KeyCodeCombination(KeyCode.F, KeyCombination.SHORTCUT_DOWN)
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
        miscMenu.isVisible = false
        examineFile.onAction = EventHandler {
            createFileInfoDialog()
        }
    }

    private fun createFileInfoDialog() {
        val file = ((editorTabPane.selectionModel.selectedItem?.content as Region?)
                ?.childrenUnmodifiable?.firstOrNull() as CodeEditorArea?)?.currentEditingFile

        val typeLabelTitle = Label("Type:")
        val typeLabelValue = Label(file?.extension ?: "No file or extension")
        val typeBox = HBox()
        typeBox.children.addAll(typeLabelTitle, typeLabelValue)
        typeBox.spacing = 40.0

        val fileInfoDialog = getBaseDialog<Unit>(typeBox)
        fileInfoDialog.title = file?.relativeTo(projectFolder)?.path ?: "No file is currently editing"

        fileInfoDialog.initOwner(this)
        fileInfoDialog.dialogPane.buttonTypes.add(ButtonType.OK)
        fileInfoDialog.show()
    }
}

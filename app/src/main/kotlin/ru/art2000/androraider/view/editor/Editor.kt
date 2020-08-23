package ru.art2000.androraider.view.editor

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.application.Platform
import javafx.beans.property.Property
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.*
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.HBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.WindowEvent
import org.reactfx.Subscription
import org.reactfx.value.Var
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.analyzer.result.FileLink
import ru.art2000.androraider.model.analyzer.result.Link
import ru.art2000.androraider.model.analyzer.result.SimpleFileLink
import ru.art2000.androraider.model.apktool.ApkToolUtils
import ru.art2000.androraider.model.editor.CodeDataProvider
import ru.art2000.androraider.model.editor.CodeEditorStatusBarDataProvider
import ru.art2000.androraider.model.editor.FileDataProvider
import ru.art2000.androraider.model.editor.file.FileEditData
import ru.art2000.androraider.model.io.StreamOutput
import ru.art2000.androraider.model.io.println
import ru.art2000.androraider.model.io.registerStreamOutput
import ru.art2000.androraider.model.io.unregisterStreamOutput
import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.presenter.editor.EditorPresenter
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.connect
import ru.art2000.androraider.view.BaseScene
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.dialogs.projectsettings.ProjectSettingsDialog
import ru.art2000.androraider.view.dialogs.recompile.RecompileDialog
import ru.art2000.androraider.view.dialogs.showErrorMessage
import ru.art2000.androraider.view.editor.code.CodeEditorArea
import ru.art2000.androraider.view.editor.code.EditorTabContent
import ru.art2000.androraider.view.launcher.Launcher
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.io.IOException
import java.nio.file.StandardWatchEventKinds
import java.util.*
import java.util.function.Consumer
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

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

        editorTabPane.tabs.addListener { change: ListChangeListener.Change<out Tab> ->
            while (change.next()) {
                change.removed.forEach {
                    (it.content as? EditorTabContent)?.dispose()
                }
            }
        }

        loadingDialog.title = "Loading..."
        loadingDialog.width = 400.0
        loadingDialog.dialogPane.prefWidth = 400.0
        loadingDialog.dialogPane.minWidthProperty().bind(loadingDialog.dialogPane.prefWidthProperty())
        loadingLabel.text = "Loading project..."
        loadingDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)
        (loadingDialog.dialogPane.lookupButton(ButtonType.CLOSE) as Button).isDisable = true

        codeEditorContainer.background = Background(BackgroundFill(Color.GRAY, null, null))

        editorTabPane.prefHeightProperty().bind(codeEditorContainer.heightProperty())
        editorTabPane.prefWidthProperty().bind(widthProperty().subtract(fileManagerView.prefWidthProperty()))
        editorTabPane.selectionModel.selectedItemProperty().addListener { _, oldValue, newValue ->

            val fileEditData = newValue?.userData as? FileEditData

            title = if (fileEditData == null) {
                statusBar.removeDataProvider(FileEditData::class.java)
                statusBar.setStatus("Closed")
                "${projectFolder.name} - Project Editor"
            } else {
                statusBar.addDataProvider(fileEditData)
                statusBar.setStatus("Opened ${fileEditData.file.name}")
                "${fileEditData.file.absolutePath} - Project Editor"
            }

            if (oldValue != null && newValue != null) {
                getTabCodeEditor(newValue).apply {
//                    updateHighlighting()
                }
            }
        }

        presenter.projectObserver.addListener { file, kind ->
            if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                editorTabPane.tabs.removeIf {
                    (it.userData as? FileEditData)?.file == file
                }
            }
        }

        fileManagerView.prefHeightProperty().bind(heightProperty())

        try {
            val t = File("addsdssf").readText()
            println("NotExistingFileText: $t")
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun getTabCodeEditor(tab: Tab): CodeEditorArea {
        return (tab.content as EditorTabContent).codeEditorArea
    }

    private fun showLoadingDialog() {
//        if (!loadingDialog.isShowing) {
//            loadingDialog.show()
//        }
    }

    private fun runIndexing() {
        statusBar.setStatus("Indexing project...")
//        loadingLabel.text = "Indexing project..."
        presenter.setupProject()
                .observeOn(JavaFxScheduler.platform())
                .doOnSubscribe {
                    println(this, "ProjectAnalyzer", "Analyze started at ${Date()}")
                }
                .doOnComplete {
                    println(this, "ProjectAnalyzer", "Analyze ended at ${Date()}")
                    statusBar.setStatus("Project indexing finished at ${Date()}")
//                    loadingDialog.close()
                    presenter.startFileObserver()
                }.subscribe({
//                    loadingLabel.text = "Indexing $it..."
                }, {
                    println("Error1: ")
//                    it.printStackTrace()
                })
    }

    private fun onLoad() {
        if (!projectFolder.exists())
            projectFolder.mkdirs()

        if (!onSettingsUpdate(presenter.project.projectSettings)) {
            runIndexing()
        }

        title = "${projectFolder.name} - Project Editor"

        setupMenu()
        setupFileExplorerView()
        setupTabs()

        fileManagerView.requestFocus()
    }

    private fun loadProject() {
        loadingLabel.text = "Loading1 project..."
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

    private fun openLink(link: Link) {
        println("OpeningLink: ${Date()}")
        val time = measureTimeMillis {
            if (TypeDetector.isTextFile(link.tabName)) {
                val indexedTab = editorTabPane.tabs.withIndex().find {
                    it.value.userData == link.data
                }
                val position = if (indexedTab == null) {
                    val newTab = Tab(link.tabName)

                    newTab.userData = link.data
                    newTab.content = EditorTabContent(link.data, ::openLink).apply {

                        val sub: Property<Subscription?> = SimpleObjectProperty(null)

                        sub.value = codeEditorArea.textProperty().connect { _, oldValue, newValue ->
                            println("AreaMove: 1; ${newValue.length}")
                            if (newValue.isNotEmpty() && oldValue != newValue) {
                                println("AreaMove: 2")
                                codeEditorArea.moveToAndPlaceLineInCenter(link.offset)
                                sub.value?.unsubscribe()
                            }
                        }

                        val file = (link as? FileLink)?.file

                        val statusBarDataProvider =
                                CodeEditorStatusBarDataProvider(codeEditorArea, file, presenter.project)

                        statusBar.addDataProvider(statusBarDataProvider)
                    }
                    val pos = editorTabPane.selectionModel.selectedIndex + 1
                    editorTabPane.tabs.add(pos, newTab)
                    pos
                } else {
                    val selectedTab = editorTabPane.tabs[indexedTab.index]
                    getTabCodeEditor(selectedTab).apply {
                        moveToAndPlaceLineInCenter(caretPosition)
                    }
                    indexedTab.index
                }

                editorTabPane.selectionModel.select(position)
            }
        }

        println("TabOpeningTime=$time")
    }

    private fun setupFileExplorerView() {
        fileManagerView.onFileSelectedListeners.add { newFile ->
            openLink(SimpleFileLink(newFile))
        }

        presenter.addFileListener { file, kind ->
            when (kind) {
                StandardWatchEventKinds.ENTRY_DELETE -> fileManagerView.removeBranch(file)
                StandardWatchEventKinds.ENTRY_CREATE -> fileManagerView.addBranch(file)
            }
        }
        fileManagerView.updateFileList()
    }

    private fun onSettingsUpdate(settings: PreferenceManager): Boolean {
        val frameworkFolder = settings.getStringArray(ProjectSettingsDialog.KEY_DECOMPILED_FRAMEWORK_PATH)
                .map { File(it) }
                .filter { it.isDirectory && it.exists() }

        frameworkFolder.forEach {
            presenter.project.addDependencyFolder(it)
        }

        if (frameworkFolder.isNotEmpty()) {
            showLoadingDialog()
            runIndexing()
        }

        return frameworkFolder.isNotEmpty()
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
        projectSettings.onAction = EventHandler {
            val settings = presenter.project.projectSettings
            ProjectSettingsDialog(settings).showAndWait().ifPresent {
                onSettingsUpdate(settings)
            }
        }
        recompile.accelerator = KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN)
        recompile.onAction = EventHandler {
            val dialog = RecompileDialog(projectFolder, presenter.project.projectSettings)
            dialog.initOwner(this)
            val selectedOptions = dialog.showAndWait().get()
            if (selectedOptions.isNotEmpty()) {
                thread {
                    val settings = presenter.project.projectSettings

                    ApkToolUtils.recompile(settings, projectFolder, *selectedOptions.toTypedArray(), output = console)
                            ?: Platform.runLater {
                                showErrorMessage(
                                        "Recompile error",
                                        "An error occurred while recompiling",
                                        this)
                            }
                }
            }
        }

        // Menu/Misc
        miscMenu.isVisible = false
        examineFile.onAction = EventHandler {
            createFileInfoDialog()
        }
    }

    private fun createFileInfoDialog() {
        val file = (editorTabPane.selectionModel.selectedItem?.userData as? FileEditData)
                ?.file

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

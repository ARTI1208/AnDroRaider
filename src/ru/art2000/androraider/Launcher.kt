package ru.art2000.androraider

import javafx.application.Application
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Launcher : Application() {

    companion object {
        const val APP_VERSION = "0.1"
        const val APP_NAME = "AnDroRaider"
        const val RELEASE_TYPE = "BETA"
        const val RECENTS_TAG = "recent_projects"

        private var items = FXCollections.observableArrayList<String>()

        fun addToRecents(path: String) {
            items.apply {
                addAll()
            }
            if (items.contains(path)) {
                items.remove(path)
                items.add(0, path)
                Settings.putStringArray(RECENTS_TAG, items)
            } else {
                items.add(0, path)
                Settings.putStringArray(RECENTS_TAG, items)
            }
        }

        fun removeFromRecents(path: String) {
            if (items.contains(File(path).absolutePath)) {
                items.remove(path)
                Settings.putStringArray(RECENTS_TAG, items)
            }
        }

        fun main(args: Array<String>) {
            launch(Launcher::class.java, *args)
        }

        lateinit var stage: Stage
    }

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        stage = primaryStage
        val loader = FXMLLoader(javaClass.getResource(LoadUtils.getLayout("launcher.fxml")))
        loader.setController(LaunchLayoutController())
        val root = loader.load<Parent>()
        primaryStage.title = APP_NAME
        primaryStage.icons.add(LoadUtils.getDrawable("logo.png"))
        primaryStage.scene = Scene(root, 900.0, 600.0)
        primaryStage.isResizable = false
        primaryStage.show()
    }

    class LaunchLayoutController {
        @FXML
        private lateinit var appNameText: Text
        @FXML
        private lateinit var appInfoText: Text
        @FXML
        private lateinit var recentsListView: ListView<RecentProject>
        @FXML
        private lateinit var appLogoImageView: ImageView
        @FXML
        private lateinit var newProjectButton: Button
        @FXML
        private lateinit var openProjectButton: Button
        @FXML
        private lateinit var settingsButton: Button
        @FXML
        private lateinit var root: HBox

        private fun removeFromRecents(item: RecentProject) {
            if (recentsListView.items.contains(item)) {
                recentsListView.items.remove(item)
                items.remove(item.projectLocation)
                Settings.putStringArray(RECENTS_TAG, items)
            }
        }

        @Suppress("unused")
        fun initialize() {
            appNameText.text = APP_NAME
            appNameText.font = Font(40.0)
            appInfoText.text = "$RELEASE_TYPE $APP_VERSION"
            appInfoText.font = Font(20.0)
            appInfoText.fill = Color.valueOf("#666666")
            newProjectButton.text = "Decompile apk"
            settingsButton.onAction = EventHandler {
                Settings(stage).show()
            }
            newProjectButton.setOnAction {
                val chooser = FileChooser()
                chooser.extensionFilters.add(FileChooser.ExtensionFilter("Android app package", "*.apk"))
                if (recentsListView.items.size != 0) {
                    val parent = recentsListView.items[0].appFile.parentFile
                    if (parent.isDirectory)
                        chooser.initialDirectory = parent
                }
                val app = chooser.showOpenDialog(root.scene.window) ?: return@setOnAction
                val appPath = app.absolutePath
                if (items == null)
                    items = FXCollections.observableArrayList<String>()
                if (items.contains(appPath)) {
                    items.remove(appPath)
                    items.add(0, appPath)
                    recentsListView.items.setAll(RecentProject.getArray(items))
                    Settings.putStringArray(RECENTS_TAG, items)
                } else {
                    val dialog = Dialog<Unit>()
                    dialog.initOwner(stage)
                    val pane = DialogPane()
                    val cont = FXMLLoader(javaClass.getResource(LoadUtils.getLayout("decompile_options.fxml"))).load<VBox>()
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
                    }
                    framePathSelector.onAction = EventHandler {
                        val pathChooser = DirectoryChooser()
                        val chosen = pathChooser.showDialog(dialog.owner)
                        customFrameworkPath.text = chosen.absolutePath
                    }
                    folderPath.text = app.parent
                    val folderName = cont.lookup("#folderName") as TextField
                    folderName.text = app.nameWithoutExtension
                    pane.content = cont
                    pane.padding = Insets(10.0, 10.0, 0.0, 10.0)
                    dialog.title = "Decompile options"
                    dialog.dialogPane = pane
                    val decompileButton = ButtonType("Decompile", ButtonBar.ButtonData.OK_DONE)
                    pane.buttonTypes.addAll(
                            decompileButton,
                            ButtonType.CANCEL)
                    val selectedOptions = ArrayList<ApktoolCommand>()
                    var resultPath: String
                    dialog.setResultConverter { button ->
                        if (button == decompileButton) {
                            cont.goThrough(selectedOptions)
                            resultPath = folderPath.text + File.separator + folderName.text
                            selectedOptions.add(ApktoolCommand(
                                    ApktoolCommand.General.OUTPUT, resultPath))
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
                    if (selectedOptions.isEmpty())
                        return@setOnAction
                    val folder = ApkToolUtils.decompile(app, *selectedOptions.toTypedArray())
                    if (folder == null){
                        val errorDialog = Dialog<Unit>()
                        errorDialog.initOwner(stage)
                        errorDialog.contentText = "An error occurred while decompiling"
                        errorDialog.dialogPane.buttonTypes.add(ButtonType.OK)
                        errorDialog.show()
                    } else{
                        addToRecents(folder.absolutePath)
                        stage.hide()
                    }
                }
            }
            openProjectButton.text = "Open project"
            openProjectButton.setOnAction {
                val project = openProject()
                if (project != null) {
                    addToRecents(project.absolutePath)
                    root.scene.window.hide()
                    Editor(project).show()
                }
            }
            recentsListView.setCellFactory { RecentsListItem() }
            recentsListView.onMouseClicked = EventHandler {
                if (it.target !is Button && it.clickCount == 2) {
                    openRecentProject()
                }
            }
            recentsListView.onKeyPressed = EventHandler {
                if (it.code != KeyCode.ENTER || !recentsListView.isFocused)
                    return@EventHandler
                openRecentProject()
            }
            appLogoImageView.image = LoadUtils.getDrawable("logo.png")
            items = Settings.getStringArray(RECENTS_TAG, items)
            items.removeIf { s -> s.isBlank() }
            for (s in items) {
                recentsListView.items.add(RecentProject(File(s)))
            }
            recentsListView.selectionModel.select(0)
        }

        private fun openRecentProject() {
            val projectToOpen = recentsListView.selectionModel.selectedItem.appFile
            if (projectToOpen.exists()) {
                root.scene.window.hide()
                Editor(projectToOpen).show()
            } else {
                val d = Dialog<Unit>()
                val pane = DialogPane()
                val warning = Text("This project doesn't exist! Do you want to remove it?")
                pane.content = warning
                pane.padding = Insets(10.0, 10.0, 0.0, 10.0)
                val remove = ButtonType("Remove", ButtonBar.ButtonData.NEXT_FORWARD)
                pane.buttonTypes.addAll(
                        remove,
                        ButtonType.CANCEL)
                d.dialogPane = pane
                d.title = "Project doesn't exist"
                d.setResultConverter { clickedButton ->
                    if (clickedButton == remove)
                        removeFromRecents(recentsListView.selectionModel.selectedItem)
                }
                d.showAndWait()
            }
        }

        private fun openProject(): File? {
            var dir: File? = null
            while (dir == null) {
                val chooser = DirectoryChooser()
                val chosen = chooser.showDialog(root.scene.window) ?: return null
                val contains = Objects.requireNonNull(chosen.list { _, name1 -> name1 == "apktool.yml" }).isNotEmpty()
                if (contains)
                    dir = chosen
                else {
                    val error = Dialog<File>()
                    val pane = DialogPane()
                    val warning = Text("This folder is not a project folder")
                    pane.content = warning
                    pane.padding = Insets(10.0, 10.0, 0.0, 10.0)
                    val reselect = ButtonType("Reselect", ButtonBar.ButtonData.NEXT_FORWARD)
                    pane.buttonTypes.addAll(
                            reselect,
                            ButtonType.CANCEL)
                    error.dialogPane = pane
                    error.title = "Not a project"
                    error.setResultConverter {
                        if (it == reselect)
                            openProject()
                        else
                            null
                    }
                    error.showAndWait()
                    val ret: File?
                    ret = error.result
                    return ret
                }
            }
            return dir
        }
    }

}

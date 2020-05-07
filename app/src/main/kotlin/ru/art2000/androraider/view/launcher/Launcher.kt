package ru.art2000.androraider.view.launcher

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import javafx.stage.Stage
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.apktool.ApkToolUtils
import ru.art2000.androraider.model.launcher.RecentProject
import ru.art2000.androraider.presenter.launcher.LauncherPresenter
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import ru.art2000.androraider.utils.FILE_CHOOSER_APK_FILTER
import ru.art2000.androraider.utils.FILE_CHOOSER_JAR_FILTER
import ru.art2000.androraider.view.BaseScene
import ru.art2000.androraider.view.dialogs.decompile.DecompileDialog
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.editor.Editor
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.util.function.Consumer

class Launcher : Stage(), ILauncherView, ILauncherController by LauncherController() {

    override val presenter = LauncherPresenter()

    init {
        setupApplicationInfo()
        setupRecentsListView()
        setupButtons()

        title = App.NAME
        icons.add(App.LOGO)
        scene = BaseScene(root, 900.0, 600.0)
        isResizable = false
    }

    private fun openRecentProject() {
        val projectToOpen = recentsListView.selectionModel.selectedItem
        if (projectToOpen.appFile.exists()) {
            presenter.openProject(projectToOpen)
            hide()
            Editor(projectToOpen.appFile).show()
        } else {
            val d = getBaseDialog<Unit>(Text("This project doesn't exist! Do you want to remove it?"))

            d.title = "Project doesn't exist"

            val remove = ButtonType("Remove", ButtonBar.ButtonData.NEXT_FORWARD)
            d.dialogPane.buttonTypes.addAll(remove, ButtonType.CANCEL)

            d.setResultConverter { clickedButton ->
                if (clickedButton == remove)
                    recentsListView.items.remove(projectToOpen)
            }

            d.showAndWait()
        }
    }

    private fun selectProjectFolder(): File? {
        var dir: File? = null
        while (dir == null) {
            val chooser = DirectoryChooser()
            val chosen = chooser.showDialog(root.scene.window) ?: return null
            val contains = !chosen.list { _, filename -> filename == "apktool.yml" }.isNullOrEmpty()
            if (contains)
                dir = chosen
            else {
                val error = getBaseDialog<File?>(Text("This folder is not a project folder"))

                error.title = "Not a project"

                val reselect = ButtonType("Reselect", ButtonBar.ButtonData.NEXT_FORWARD)
                error.dialogPane.buttonTypes.addAll(reselect, ButtonType.CANCEL)

                error.setResultConverter {
                    if (it == reselect)
                        selectProjectFolder()
                    else
                        null
                }
                error.showAndWait()

                return error.result
            }
        }
        return dir
    }

    private fun setupApplicationInfo() {
        appNameText.text = App.NAME
        appNameText.font = Font(40.0)
        appInfoText.text = "${App.RELEASE_TYPE} ${App.VERSION}"
        appInfoText.font = Font(20.0)
        appInfoText.fill = Color.valueOf("#666666")

        appLogoImageView.image = App.LOGO
    }

    private fun setupButtons() {
        newProjectButton.text = "Decompile apk"
        settingsButton.onAction = EventHandler {
            Settings(this).show()
        }

        newProjectButton.onAction = EventHandler {
            val chooser = FileChooser()
            chooser.extensionFilters.addAll(FILE_CHOOSER_APK_FILTER, FILE_CHOOSER_JAR_FILTER)
            if (recentsListView.items.isNotEmpty()) {
                val parent = recentsListView.items.first().appFile.parentFile
                if (parent.isDirectory)
                    chooser.initialDirectory = parent
            }
            val app = chooser.showOpenDialog(root.scene.window) ?: return@EventHandler

            val dialog = DecompileDialog(app, SettingsPresenter.prefs)
            dialog.initOwner(this)
            dialog.showAndWait()

            if (dialog.result != null) {
                val (selectedOptions, folder) = dialog.result!!

                presenter.openProject(RecentProject(folder))

                hide()
                Editor(folder, Consumer {
                    ApkToolUtils.decompile(SettingsPresenter.prefs, app, *selectedOptions.toTypedArray(), output = it)
                }).show()
            }
        }

        openProjectButton.text = "Open project"
        openProjectButton.onAction = EventHandler {
            val project = selectProjectFolder()
            if (project != null) {
                presenter.openProject(RecentProject(project))
                root.scene.window.hide()
                Editor(project).show()
            }
        }
    }

    private fun setupRecentsListView() {
        recentsListView.setCellFactory { RecentsListItem() }
        recentsListView.items = presenter.recentsItems

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

        recentsListView.selectionModel.select(0)
    }
}

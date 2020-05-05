package ru.art2000.androraider.view.settings

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import ru.art2000.androraider.view.BaseScene
import java.io.File

class Settings(owner: Window) : Stage(), ISettingsView, ISettingsController by SettingsController() {

    override val presenter = SettingsPresenter()

    init {
        title = "Settings"
        scene = BaseScene(root, 900.0, 600.0)
        initOwner(owner)

        setupApktoolSettings()
        setupClearButton()

        frameworkPath.textProperty().bindBidirectional(presenter.frameworkPathProperty)
        frameworkFolderPathSelectButton.onAction = EventHandler {
            val pathChooser = DirectoryChooser()
            if (!frameworkPath.text.isNullOrEmpty()) {
                pathChooser.initialDirectory = File(frameworkPath.text).parentFile
            }
            val chosen = pathChooser.showDialog(owner) ?: return@EventHandler
            frameworkPath.text = chosen.absolutePath
        }
        frameworkFilePathSelectButton.onAction = EventHandler {
            val pathChooser = FileChooser()
            pathChooser.extensionFilters.add(FileChooser.ExtensionFilter("Android app package", "*.apk"))
            if (!frameworkPath.text.isNullOrEmpty()) {
                pathChooser.initialDirectory = File(frameworkPath.text).parentFile
            }
            val chosen = pathChooser.showOpenDialog(root.scene.window) ?: return@EventHandler
            frameworkPath.text = chosen.absolutePath
        }
    }

    private fun setupApktoolSettings() {
        apktoolPath.textProperty().bindBidirectional(presenter.apktoolPathProperty)

        apktoolPathSelectButton.onAction = EventHandler {
            val chooser = FileChooser()
            chooser.extensionFilters.add(FileChooser.ExtensionFilter("Executable JAR", "*.jar"))
            val jar = chooser.showOpenDialog(this) ?: return@EventHandler
            apktoolPath.text = jar.absolutePath
        }
    }

    private fun setupClearButton() {
        clearDataButton.onAction = EventHandler {
            presenter.clearData()
        }
    }
}
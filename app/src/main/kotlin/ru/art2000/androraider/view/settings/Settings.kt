package ru.art2000.androraider.view.settings

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import ru.art2000.androraider.view.BaseScene

class Settings(owner: Window) : Stage(), ISettingsView, ISettingsController by SettingsController() {

    override val presenter = SettingsPresenter()

    init {
        title = "Settings"
        scene = BaseScene(root, 900.0, 600.0)
        initOwner(owner)

        setupApktoolSettings()
        setupClearButton()
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
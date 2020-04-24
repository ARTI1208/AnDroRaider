package ru.art2000.androraider.view.settings

import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.presenter.settings.SettingsPresenter

class Settings(owner: Window) : Window(), ISettingsView, ISettingsController by SettingsController() {

    private val settingsStage = Stage()

    override val presenter = SettingsPresenter()

    init {
        settingsStage.title = "Settings"
        settingsStage.scene = Scene(root, 900.0, 600.0)
        settingsStage.initOwner(owner)


        setupApktoolSettings()
        setupClearButton()
    }

    public override fun show() {
        settingsStage.show()
    }

    private fun setupApktoolSettings() {
        apktoolPath.textProperty().bindBidirectional(presenter.apktoolPathProperty)

        apktoolPathSelectButton.onAction = EventHandler {
            val chooser = FileChooser()
            chooser.extensionFilters.add(FileChooser.ExtensionFilter("Executable JAR", "*.jar"))
            val jar = chooser.showOpenDialog(settingsStage) ?: return@EventHandler
            apktoolPath.text = jar.absolutePath
        }
    }

    private fun setupClearButton() {
        clearDataButton.onAction = EventHandler {
            presenter.clearData()
        }
    }
}
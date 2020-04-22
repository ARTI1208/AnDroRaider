package ru.art2000.androraider.view.settings

import javafx.event.EventHandler
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import ru.art2000.androraider.utils.getLayout
import ru.art2000.androraider.view.View

class Settings(owner: Window) : Window(), View {

    private val settingsStage = Stage()

    private val settingsController: SettingsController

    override val presenter = SettingsPresenter()

    init {
        val loader = javaClass.getLayout("settings.fxml")

        settingsController = SettingsController()

        loader.setController(settingsController)
        val root = loader.load<Parent>()
        settingsStage.title = "Settings"
        settingsStage.scene = Scene(root, 900.0, 600.0)
        settingsStage.initOwner(owner)


        setupApktoolSettings()
        setupFrameworkSettings()
        setupClearButton()
    }

    public override fun show() {
        settingsStage.show()
    }

    private fun setupApktoolSettings() {
        settingsController.apply {

            apktoolPath.textProperty().bindBidirectional(presenter.apktoolPathProperty)

            apktoolPathSelectButton.onAction = EventHandler {
                val chooser = FileChooser()
                chooser.extensionFilters.add(FileChooser.ExtensionFilter("Executable JAR", "*.jar"))
                val jar = chooser.showOpenDialog(settingsStage) ?: return@EventHandler
                apktoolPath.text = jar.absolutePath
            }
        }
    }

    private fun setupFrameworkSettings() {
        settingsController.apply {
            frameworkFileRB.toggleGroup = frameworkTypeGroup
            frameworkFolderRB.toggleGroup = frameworkTypeGroup

            frameworkSmartFolderRB.toggleGroup = frameworkFolderTypeGroup
            frameworkStaticFolderRB.toggleGroup = frameworkFolderTypeGroup

            frameworkSmartFolderRB.disableProperty().bind(frameworkFileRB.selectedProperty())
            frameworkStaticFolderRB.disableProperty().bind(frameworkFileRB.selectedProperty())
            frameworkFolderPathSelectButton.disableProperty().bind(frameworkFileRB.selectedProperty())
            frameworkFilePathSelectButton.disableProperty().bind(frameworkFileRB.selectedProperty().not())
            frameworkFolderPath.disableProperty().bind(frameworkFileRB.selectedProperty())
            frameworkFilePath.disableProperty().bind(frameworkFileRB.selectedProperty().not())

            frameworkFileRB.isSelected = true
        }
    }

    private fun setupClearButton() {
        settingsController.apply {
            clearDataButton.onAction = EventHandler {
                presenter.clearData()
            }
        }
    }
}
package ru.art2000.androraider.view.dialogs.projectsettings

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.TextField
import ru.art2000.androraider.utils.getLayout

class ProjectSettingsController: IProjectSettingsController {

    @FXML override lateinit var frameworkFolderPath: TextField
    @FXML override lateinit var frameworkFolderPathSelector: Button

    override val root: Parent
    override val layoutFile = "project_settings.fxml"

    init {
        val loader = javaClass.getLayout(layoutFile)
        loader.setController(this)
        root = loader.load()
    }
}
package ru.art2000.androraider.view.settings

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import ru.art2000.androraider.utils.getLayout

class SettingsController : ISettingsController {

    @FXML
    override lateinit var javaSourcesPathSelectButton: Button
    @FXML
    override lateinit var apktoolPathSelectButton: Button
    @FXML
    override lateinit var frameworkFilePathSelectButton: Button
    @FXML
    override lateinit var frameworkFolderPathSelectButton: Button

    @FXML
    override lateinit var clearDataButton: Button

    @FXML
    override lateinit var javaSourcesPath: TextField
    @FXML
    override lateinit var apktoolPath: TextField
    @FXML
    override lateinit var frameworkFolderPath: TextField
    @FXML
    override lateinit var frameworkFilePath: TextField
    @FXML
    override lateinit var frameworkFileRB: RadioButton
    @FXML
    override lateinit var frameworkFolderRB: RadioButton
    @FXML
    override lateinit var frameworkSmartFolderRB: RadioButton
    @FXML
    override lateinit var frameworkStaticFolderRB: RadioButton

    override val frameworkTypeGroup = ToggleGroup()
    override val frameworkFolderTypeGroup = ToggleGroup()

    override val root: Parent
    override val layoutFile = "settings.fxml"

    init {
        val loader = javaClass.getLayout(layoutFile)
        loader.setController(this)
        root = loader.load()
    }
}
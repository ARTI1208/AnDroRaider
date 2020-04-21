package ru.art2000.androraider.view.settings

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup

class SettingsController {

    @FXML
    lateinit var javaSourcesPathSelectButton: Button
    @FXML
    lateinit var apktoolPathSelectButton: Button
    @FXML
    lateinit var frameworkFilePathSelectButton: Button
    @FXML
    lateinit var frameworkFolderPathSelectButton: Button

    @FXML
    lateinit var clearDataButton: Button

    @FXML
    lateinit var javaSourcesPath: TextField
    @FXML
    lateinit var apktoolPath: TextField
    @FXML
    lateinit var frameworkFolderPath: TextField
    @FXML
    lateinit var frameworkFilePath: TextField
    @FXML
    lateinit var frameworkFileRB: RadioButton
    @FXML
    lateinit var frameworkFolderRB: RadioButton
    @FXML
    lateinit var frameworkSmartFolderRB: RadioButton
    @FXML
    lateinit var frameworkStaticFolderRB: RadioButton

    val frameworkTypeGroup = ToggleGroup()
    val frameworkFolderTypeGroup = ToggleGroup()
}
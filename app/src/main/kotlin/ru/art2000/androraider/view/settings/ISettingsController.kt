package ru.art2000.androraider.view.settings

import javafx.scene.control.Button
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import ru.art2000.androraider.mvp.IController

interface ISettingsController : IController {

    var javaSourcesPathSelectButton: Button

    var apktoolPathSelectButton: Button

    var frameworkFilePathSelectButton: Button

    var frameworkFolderPathSelectButton: Button


    var clearDataButton: Button


    var javaSourcesPath: TextField

    var apktoolPath: TextField

    var frameworkFolderPath: TextField

    var frameworkFilePath: TextField

    var frameworkFileRB: RadioButton

    var frameworkFolderRB: RadioButton

    var frameworkSmartFolderRB: RadioButton

    var frameworkStaticFolderRB: RadioButton

    val frameworkTypeGroup: ToggleGroup
    val frameworkFolderTypeGroup: ToggleGroup
}
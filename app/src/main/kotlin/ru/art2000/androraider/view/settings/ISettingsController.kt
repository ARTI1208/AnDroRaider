package ru.art2000.androraider.view.settings

import javafx.scene.control.Button
import javafx.scene.control.TextField
import ru.art2000.androraider.arch.IController

interface ISettingsController : IController {

    var javaSourcesPathSelectButton: Button

    var apktoolPathSelectButton: Button

    var frameworkFilePathSelectButton: Button

    var frameworkFolderPathSelectButton: Button


    var clearDataButton: Button


    var javaSourcesPath: TextField

    var apktoolPath: TextField

    var frameworkPath: TextField
}
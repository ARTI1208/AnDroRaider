package ru.art2000.androraider.view.dialogs.projectsettings

import javafx.scene.control.Button
import javafx.scene.control.TextField
import ru.art2000.androraider.mvp.IController

interface IProjectSettingsController: IController {

    val frameworkFolderPath: TextField
    val frameworkFolderPathSelector: Button
}
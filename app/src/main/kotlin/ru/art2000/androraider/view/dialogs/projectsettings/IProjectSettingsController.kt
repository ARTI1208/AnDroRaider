package ru.art2000.androraider.view.dialogs.projectsettings

import javafx.scene.control.Button
import javafx.scene.control.ListView
import ru.art2000.androraider.arch.IController

interface IProjectSettingsController: IController {

    val frameworkFolderPathSelector: Button
    val frameworkFolderPathRemover: Button
    val listView: ListView<String>
}
package ru.art2000.androraider.view.dialogs.projectsettings

import javafx.scene.control.Button
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import ru.art2000.androraider.mvp.IController
import java.io.File

interface IProjectSettingsController: IController {

    val frameworkFolderPathSelector: Button
    val frameworkFolderPathRemover: Button
    val listView: ListView<String>
}
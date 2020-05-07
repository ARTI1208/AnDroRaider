package ru.art2000.androraider.view.dialogs.projectsettings

import javafx.event.EventHandler
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.stage.DirectoryChooser
import ru.art2000.androraider.model.settings.PreferenceManager
import java.io.File

class ProjectSettingsDialog(private val settings: PreferenceManager) : Dialog<Unit?>(), IProjectSettingsController by ProjectSettingsController() {

    companion object {
        public const val KEY_DECOMPILED_FRAMEWORK_PATH = "decompiled_framework_path"
    }

    init {
        title = "Project settings"

        dialogPane = DialogPane()
        dialogPane.content = root
        dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)

        setResultConverter {
            return@setResultConverter if (it == ButtonType.CANCEL) null else Unit
        }

        setup()
        loadFromSettings()
        addListeners()
    }

    private fun setup() {
        frameworkFolderPathSelector.onAction = EventHandler {
            DirectoryChooser()
                    .apply { initialDirectory = File(frameworkFolderPath.text).parentFile }
                    .showDialog(owner)
                    ?.absolutePath
                    ?.also { frameworkFolderPath.text = it }
        }
    }

    private fun loadFromSettings() {
        frameworkFolderPath.text = settings.getString(KEY_DECOMPILED_FRAMEWORK_PATH)
    }

    private fun addListeners() {
        frameworkFolderPath.textProperty().addListener { _, _, newValue ->
            settings.putString(KEY_DECOMPILED_FRAMEWORK_PATH, newValue)

            frameworkFolderPath.styleClass.remove("error")
            if (!File(newValue).exists())
                frameworkFolderPath.styleClass.add("error")
        }
    }
}
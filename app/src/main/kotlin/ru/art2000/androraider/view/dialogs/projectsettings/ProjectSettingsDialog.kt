package ru.art2000.androraider.view.dialogs.projectsettings

import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.stage.DirectoryChooser
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.view.dialogs.icons
import java.io.File

class ProjectSettingsDialog(private val settings: PreferenceManager) : Dialog<Unit?>(), IProjectSettingsController by ProjectSettingsController() {

    companion object {
        public const val KEY_DECOMPILED_FRAMEWORK_PATH = "decompiled_framework_path"
    }

    init {
        title = "Project settings"
        icons.add(App.LOGO)

        dialogPane = DialogPane()
        dialogPane.content = root
        dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CANCEL)

        setResultConverter {
            return@setResultConverter if (it == ButtonType.CANCEL) null else Unit
        }

        setup()
        loadFromSettings()
        frameworkFolderPathRemover.disableProperty().bind(listView.selectionModel.selectedItemProperty().isNull)
    }

    private fun setup() {
        frameworkFolderPathSelector.onAction = EventHandler {
            DirectoryChooser()
                    .apply {
                        val path = if (listView.selectionModel.selectedItem != null)
                            listView.selectionModel.selectedItem
                        else
                            listView.items.firstOrNull() ?: return@apply

                        initialDirectory = File(path).parentFile
                    }
                    .showDialog(owner)
                    ?.also { addDependency(it) }
        }

        frameworkFolderPathRemover.onAction = EventHandler {
            listView.selectionModel.selectedItem?.also {
                removeDependency(it)
            }
        }
    }


    private fun addDependency(file: File) {
        listView.items.add(file.absolutePath)
        settings.putStringArray(KEY_DECOMPILED_FRAMEWORK_PATH, listView.items)
    }

    private fun removeDependency(path: String) {
        listView.items.remove(path)
        settings.putStringArray(KEY_DECOMPILED_FRAMEWORK_PATH, listView.items)
    }

    private fun loadFromSettings() {
        listView.items.addAll(settings.getStringArray(KEY_DECOMPILED_FRAMEWORK_PATH))
    }

}
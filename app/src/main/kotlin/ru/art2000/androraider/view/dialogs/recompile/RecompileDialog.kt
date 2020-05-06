package ru.art2000.androraider.view.dialogs.recompile

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.ButtonBar
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import ru.art2000.androraider.model.apktool.ApktoolCommand
import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import ru.art2000.androraider.utils.FILE_CHOOSER_APK_FILTER
import ru.art2000.androraider.utils.FILE_CHOOSER_JAR_FILTER
import java.io.File

class RecompileDialog(private val baseFolder: File, private val settings: PreferenceManager? = null) : Dialog<List<ApktoolCommand>>(),
        IRecompilationController by RecompileDialogController() {

    companion object {
        private const val KEY_USE_CUSTOM_FRAMEWORK = "use_custom_framework"
        private const val KEY_FILE_NAME = "file_name"
        private const val KEY_FILE_PATH = "file_path"
        private const val KEY_FORCE_REBUILD = "force_rebuild"
        private const val KEY_APKTOOL_PATH = SettingsPresenter.KEY_APKTOOL_PATH
        private const val KEY_FRAMEWORK_PATH = SettingsPresenter.KEY_FRAMEWORK_PATH
    }

    private val recompileButton : Node

    private val fileTextFields = listOf(apktoolPathField, customFramePathField, filePathField)

    init {
        title = "Recompile options"

        dialogPane = DialogPane()
        dialogPane.content = root

        val recompileButtonType = ButtonType("Recompile", ButtonBar.ButtonData.OK_DONE)
        dialogPane.buttonTypes.addAll(recompileButtonType, ButtonType.CANCEL)
        recompileButton = dialogPane.lookupButton(recompileButtonType)

        setResultConverter { buttonType ->
            return@setResultConverter if (buttonType === recompileButtonType) {
                val flagCheckBoxes = listOf(forceRebuildCheckBox)

                val selectedOptions = flagCheckBoxes.mapNotNull {
                    if (it.isSelected) ApktoolCommand(it.id) else null
                }.toMutableList()

                val filePath = filePathField.text + File.separator +
                        if (fileNameField.text.endsWith(".apk")) fileNameField.text else (fileNameField.text + ".apk")

                selectedOptions.add(ApktoolCommand.General.output(filePath))

                if (customFrameRadio.isSelected)
                    selectedOptions.add(ApktoolCommand.General.frameworkFolder(customFramePathField.text))

                selectedOptions
            } else emptyList()
        }

        setup()
        loadFromSettings()
        addListeners()
    }

    private fun ensureFilesAvailable() {
        recompileButton.isDisable = fileTextFields.any { it.styleClass.contains("error") }
    }

    private fun setup() {
        filePathFieldSelector.onAction = EventHandler {
            DirectoryChooser()
                    .apply { initialDirectory = File(filePathField.text).parentFile }
                    .showDialog(owner)
                    ?.absolutePath
                    ?.also { filePathField.text = it }
        }

        customFramePathSelector.onAction = EventHandler {
            DirectoryChooser()
                    .apply { initialDirectory = File(customFramePathField.text).parentFile }
                    .showDialog(owner)
                    ?.absolutePath
                    ?.also { customFramePathField.text = it }
        }

        apktoolPathFileSelector.onAction = EventHandler {
            FileChooser()
                    .apply {
                        initialDirectory = File(apktoolPathField.text).parentFile
                        extensionFilters.add(FILE_CHOOSER_JAR_FILTER)
                    }
                    .showOpenDialog(owner)
                    ?.absolutePath
                    ?.also { apktoolPathField.text = it }
        }

        customFramePathFileSelector.onAction = EventHandler {
            FileChooser()
                    .apply {
                        initialDirectory = File(customFramePathField.text).parentFile
                        extensionFilters.add(FILE_CHOOSER_APK_FILTER)
                    }
                    .showOpenDialog(owner)
                    ?.absolutePath
                    ?.also { customFramePathField.text = it }
        }

        fileNameField.text = baseFolder.name
        filePathField.text = baseFolder.parent
    }

    private fun loadFromSettings() {
        settings ?: return

        apktoolPathField.text = settings.getString(KEY_APKTOOL_PATH)
        customFramePathField.text = settings.getString(KEY_FRAMEWORK_PATH)
        customFrameRadio.isSelected = settings.getBoolean(KEY_USE_CUSTOM_FRAMEWORK)
        fileNameField.text = settings.getString(KEY_FILE_NAME, baseFolder.name)
        filePathField.text = settings.getString(KEY_FILE_PATH, baseFolder.parent)
        forceRebuildCheckBox.isSelected = settings.getBoolean(KEY_FORCE_REBUILD)
    }

    private fun addListeners() {
        settings ?: return

        customFramePathField.textProperty().addListener { _, _, newValue ->
            settings.putString(KEY_FRAMEWORK_PATH, newValue)

            customFramePathField.styleClass.remove("error")
            if (!File(newValue).exists())
                customFramePathField.styleClass.add("error")
            ensureFilesAvailable()
        }

        customFrameRadio.selectedProperty().addListener { _, _, newValue ->
            settings.putBoolean(KEY_USE_CUSTOM_FRAMEWORK, newValue)
        }

        fileNameField.textProperty().addListener { _, _, newValue ->
            settings.putString(KEY_FILE_NAME, newValue)
        }

        filePathField.textProperty().addListener { _, _, newValue ->
            settings.putString(KEY_FILE_PATH, newValue)

            filePathField.styleClass.remove("error")
            if (!File(newValue).exists())
                filePathField.styleClass.add("error")
            ensureFilesAvailable()
        }

        forceRebuildCheckBox.selectedProperty().addListener { _, _, newValue ->
            settings.putBoolean(KEY_FORCE_REBUILD, newValue)
        }

        apktoolPathField.textProperty().addListener { _, _, newValue ->
            settings.putString(KEY_APKTOOL_PATH, newValue)

            apktoolPathField.styleClass.remove("error")
            if (!File(newValue).exists())
                apktoolPathField.styleClass.add("error")
            ensureFilesAvailable()
        }
    }
}
package ru.art2000.androraider.view.dialogs.decompile

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.control.*
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import ru.art2000.androraider.model.apktool.ApktoolCommand
import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import ru.art2000.androraider.utils.FILE_CHOOSER_APK_FILTER
import ru.art2000.androraider.utils.FILE_CHOOSER_JAR_FILTER
import java.io.File

class DecompileDialog(private val app: File, private val settings: PreferenceManager? = null) : Dialog<Pair<List<ApktoolCommand>, File>?>(),
        IDecompilationController by DecompileDialogController() {

    companion object {
        private const val KEY_USE_CUSTOM_FRAMEWORK = "use_custom_framework"
        private const val KEY_OVERRIDE = "override"
        private const val KEY_APKTOOL_PATH = SettingsPresenter.KEY_APKTOOL_PATH
        private const val KEY_FRAMEWORK_PATH = SettingsPresenter.KEY_FRAMEWORK_PATH
    }

    private val decompileButton: Node

    private val fileTextFields = listOf(apktoolPathField, customFramePathField, projectPathField)

    init {
        title = "Decompile options"

        dialogPane = DialogPane()
        dialogPane.content = root

        val decompileButtonType = ButtonType("Decompile", ButtonBar.ButtonData.OK_DONE)
        dialogPane.buttonTypes.addAll(decompileButtonType, ButtonType.CANCEL)
        decompileButton = dialogPane.lookupButton(decompileButtonType)

        setResultConverter { button ->
            return@setResultConverter if (button === decompileButtonType) {

                val flagCheckBoxes = listOf(noCodeDecompile, noResDecompile, overrideIfExistsCheckBox)

                val selectedOptions = flagCheckBoxes.mapNotNull {
                    if (it.isSelected) ApktoolCommand(it.id) else null
                }.toMutableList()

                val resultPath = projectPathField.text + File.separator + folderNameField.text
                selectedOptions.add(ApktoolCommand.General.output(resultPath))

                if (customFrameRadio.isSelected)
                    selectedOptions.add(ApktoolCommand.General.frameworkFolder(customFramePathField.text))

                selectedOptions to File(resultPath)
            } else null
        }

        setup()
        addListeners()
        loadFromSettings()
    }

    private fun setup() {

        projectPathFieldSelector.onAction = EventHandler {
            val pathChooser = DirectoryChooser()
            val chosen = pathChooser.showDialog(owner)
            projectPathField.text = chosen.absolutePath
        }

        customFramePathSelector.onAction = EventHandler {
            val pathChooser = DirectoryChooser()
            val chosen = pathChooser.showDialog(owner)
            customFramePathField.text = chosen.absolutePath
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

        folderNameField.text = app.nameWithoutExtension
        projectPathField.text = app.parent
    }

    private fun loadFromSettings() {
        settings ?: return

        apktoolPathField.text = settings.getString(KEY_APKTOOL_PATH)
        customFramePathField.text = settings.getString(KEY_FRAMEWORK_PATH)
        customFrameRadio.isSelected = settings.getBoolean(KEY_USE_CUSTOM_FRAMEWORK)
        overrideIfExistsCheckBox.isSelected = settings.getBoolean(KEY_OVERRIDE)
    }

    private fun ensureFilesAvailable() {
        decompileButton.isDisable = fileTextFields.any { it.styleClass.contains("error") }
    }

    private fun addListeners() {
        customFramePathField.textProperty().addListener { _, _, newValue ->
            customFramePathField.styleClass.remove("error")
            if (!File(newValue).exists())
                customFramePathField.styleClass.add("error")
            ensureFilesAvailable()
        }

        projectPathField.textProperty().addListener { _, _, newValue ->
            projectPathField.styleClass.remove("error")
            if (!File(newValue).exists())
                projectPathField.styleClass.add("error")
            ensureFilesAvailable()
        }

        apktoolPathField.textProperty().addListener { _, _, newValue ->
            apktoolPathField.styleClass.remove("error")
            if (!File(newValue).exists())
                apktoolPathField.styleClass.add("error")
            ensureFilesAvailable()
        }
    }
}
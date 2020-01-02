package ru.art2000.androraider.windows

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.Window
import ru.art2000.androraider.windows.launcher.Launcher
import ru.art2000.androraider.utils.getLayout
import java.util.prefs.Preferences

class Settings(owner: Window) : Window() {

    companion object {

        @JvmStatic
        private val prefs = Preferences.userNodeForPackage(this::class.java)

        @JvmStatic
        val APKTOOL_PATH = "apktool_path"

        @JvmStatic
        fun getString(key: String, default: String? = null): String? {
            return prefs.get(key, default)
        }

        @JvmStatic
        fun putString(key: String, value: String) {
            prefs.put(key, value)
        }

        @JvmStatic
        fun putStringArray(key: String, array: ObservableList<String>) {
            val stringBuilder = StringBuilder()
            for ((i, s) in array.withIndex()) {
                stringBuilder.append(s)
                if (i != array.size - 1)
                    stringBuilder.append("|")
            }
            prefs.put(key, stringBuilder.toString())
        }

        @JvmStatic
        fun getStringArray(key: String, defaultArray: ObservableList<String>): ObservableList<String> {
            val array = prefs.get(key, "")
            return if (array != "") FXCollections.observableArrayList(array.split("|")) else defaultArray
        }

    }

    var settingsStage = Stage()

    init {
        val loader = javaClass.getLayout("settings.fxml")
        loader.setController(Controller())
        val root = loader.load<Parent>()
//        settingsStage.icons.add(LoadUtils.getDrawable("logo.png"))
        settingsStage.title = "Settings"
        settingsStage.scene = Scene(root, 900.0, 600.0)
        settingsStage.initOwner(owner)
    }

    public override fun show() {
        settingsStage.show()
    }

    inner class Controller {

        @FXML
        private lateinit var javaSourcesPathSelectButton: Button
        @FXML
        private lateinit var apktoolPathSelectButton: Button
        @FXML
        private lateinit var frameworkFilePathSelectButton: Button
        @FXML
        private lateinit var frameworkFolderPathSelectButton: Button

        @FXML
        private lateinit var clearDataButton: Button

        @FXML
        private lateinit var javaSourcesPath: TextField
        @FXML
        private lateinit var apktoolPath: TextField
        @FXML
        private lateinit var frameworkFolderPath: TextField
        @FXML
        private lateinit var frameworkFilePath: TextField
        @FXML
        private lateinit var frameworkFileRB: RadioButton
        @FXML
        private lateinit var frameworkFolderRB: RadioButton
        @FXML
        private lateinit var frameworkSmartFolderRB: RadioButton
        @FXML
        private lateinit var frameworkStaticFolderRB: RadioButton

        private var frameworkTypeGroup = ToggleGroup()
        private var frameworkFolderTypeGroup = ToggleGroup()

        @Suppress("unused")
        fun initialize() {

            frameworkFileRB.toggleGroup = frameworkTypeGroup
            frameworkFolderRB.toggleGroup = frameworkTypeGroup

            frameworkSmartFolderRB.toggleGroup = frameworkFolderTypeGroup
            frameworkStaticFolderRB.toggleGroup = frameworkFolderTypeGroup

            frameworkFileRB.selectedProperty().addListener { _, _, newValue ->
                run {
                    if (newValue) {
                        frameworkSmartFolderRB.isDisable = true
                        frameworkStaticFolderRB.isDisable = true
                        frameworkFolderPathSelectButton.isDisable = true
                        frameworkFilePathSelectButton.isDisable = false
                        frameworkFolderPath.isDisable = true
                        frameworkFilePath.isDisable = false
                    } else {
                        frameworkSmartFolderRB.isDisable = false
                        frameworkStaticFolderRB.isDisable = false
                        frameworkFolderPathSelectButton.isDisable = false
                        frameworkFilePathSelectButton.isDisable = true
                        frameworkFolderPath.isDisable = false
                        frameworkFilePath.isDisable = true
                    }
                }
            }
            frameworkFileRB.isSelected = true

            apktoolPath.text = getString("apktool_path")
                    ?: ""
            apktoolPathSelectButton.onAction = EventHandler {
                val chooser = FileChooser()
                chooser.extensionFilters.add(FileChooser.ExtensionFilter("Executable JAR", "*.jar"))
                val jar = chooser.showOpenDialog(settingsStage) ?: return@EventHandler
                apktoolPath.text = jar.absolutePath
                putString("apktool_path", apktoolPath.text)
            }

            apktoolPath.text = getString("apktool_path")
                    ?: ""
            apktoolPathSelectButton.onAction = EventHandler {
                val chooser = FileChooser()
                chooser.extensionFilters.add(FileChooser.ExtensionFilter("Executable JAR", "*.jar"))
                val jar = chooser.showOpenDialog(settingsStage) ?: return@EventHandler
                apktoolPath.text = jar.absolutePath
                putString("apktool_path", apktoolPath.text)
            }

            clearDataButton.onAction = EventHandler {
                prefs.remove(Launcher.RECENTS_TAG)
                prefs.remove(APKTOOL_PATH)
            }

        }
    }
}
package ru.art2000.androraider

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import javafx.scene.control.ToggleGroup
import javafx.stage.FileChooser
import javafx.stage.Stage
import javafx.stage.Window
import java.util.prefs.Preferences

class Settings(owner : Window) : Window() {

    companion object {

        @JvmStatic
        private val prefs = Preferences.userNodeForPackage(this::class.java)

        @JvmStatic
        fun getString(key: String, default: String? = null): String?{
            return prefs.get(key, default)
        }

        fun putString(key : String, value: String){
            prefs.put(key, value)
        }

    }

    public var settingsStage = Stage()

    init {
        val loader = FXMLLoader(javaClass.getResource(LoadUtils.getLayout("settings.fxml")))
        loader.setController(Controller())
        val root = loader.load<Parent>()
        settingsStage.icons.add(LoadUtils.getDrawable("logo.png"))
        settingsStage.title = "Settings"
        settingsStage.scene = Scene(root, 900.0, 600.0)
        settingsStage.initOwner(owner)
    }

    public override fun show() {
        settingsStage.show()
    }

    inner class Controller {

        @FXML
        private lateinit var apktoolPathSelectButton : Button
        @FXML
        private lateinit var frameworkFilePathSelectButton : Button
        @FXML
        private lateinit var frameworkFolderPathSelectButton : Button
        @FXML
        private lateinit var apktoolPath : TextField
        @FXML
        private lateinit var frameworkFolderPath : TextField
        @FXML
        private lateinit var frameworkFilePath : TextField
        @FXML
        private lateinit var frameworkFileRB : RadioButton
        @FXML
        private lateinit var frameworkFolderRB : RadioButton
        @FXML
        private lateinit var frameworkSmartFolderRB : RadioButton
        @FXML
        private lateinit var frameworkStaticFolderRB : RadioButton

        private var frameworkTypeGroup = ToggleGroup()
        private var frameworkFolderTypeGroup = ToggleGroup()

        @Suppress("unused")
        fun initialize(){

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

            apktoolPath.text = Settings.getString("apktool_path") ?: ""
            apktoolPathSelectButton.onAction = EventHandler {
                val chooser = FileChooser()
                chooser.extensionFilters.add(FileChooser.ExtensionFilter("Executable JAR", "*.jar"))
                val jar = chooser.showOpenDialog(settingsStage) ?: return@EventHandler
                apktoolPath.text = jar.absolutePath
                putString("apktool_path", apktoolPath.text)
            }
        }
    }
}
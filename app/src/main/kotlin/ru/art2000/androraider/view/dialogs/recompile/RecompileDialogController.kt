package ru.art2000.androraider.view.dialogs.recompile

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import ru.art2000.androraider.utils.getLayout

class RecompileDialogController : IRecompilationController {

    @FXML override lateinit var apktoolPathField: TextField
    @FXML override lateinit var apktoolPathFileSelector: Button

    @FXML override lateinit var builtinFrameRadio: RadioButton

    @FXML override lateinit var customFrameRadio: RadioButton
    @FXML override lateinit var customFramePathField: TextField
    @FXML override lateinit var customFramePathSelector: Button
    @FXML override lateinit var customFramePathFileSelector: Button

    @FXML override lateinit var fileNameField: TextField
    @FXML override lateinit var filePathField: TextField
    @FXML override lateinit var filePathFieldSelector: Button

    @FXML override lateinit var forceRebuildCheckBox: CheckBox

    override val layoutFile = "recompile_options.fxml"
    override val root: Parent
    init {
        val loader = javaClass.getLayout(layoutFile)
        loader.setController(this)
        root = loader.load()
    }
}
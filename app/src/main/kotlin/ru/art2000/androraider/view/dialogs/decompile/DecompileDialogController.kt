package ru.art2000.androraider.view.dialogs.decompile

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.*
import ru.art2000.androraider.utils.getLayout

class DecompileDialogController : IDecompilationController {

    @FXML
    override lateinit var noCodeDecompile: CheckBox
    @FXML
    override lateinit var noResDecompile: CheckBox
    @FXML
    override lateinit var builtinFrameRadio: RadioButton
    @FXML
    override lateinit var customFrameRadio: RadioButton
    @FXML
    override lateinit var customFramePathField: TextField
    @FXML
    override lateinit var customFramePathSelector: Button
    @FXML
    override lateinit var folderNameField: TextField
    @FXML
    override lateinit var projectPathField: TextField
    @FXML
    override lateinit var projectPathFieldSelector: Button
    @FXML
    override lateinit var overrideIfExistsCheckBox: CheckBox

    override val layoutFile = "decompile_options.fxml"

    override val root: Parent

    init {
        val loader = javaClass.getLayout(layoutFile)
        loader.setController(this)
        root = loader.load()
    }
}
package ru.art2000.androraider.view.editor

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.*
import ru.art2000.androraider.mvp.BaseController
import ru.art2000.androraider.utils.getLayout
import ru.art2000.androraider.view.editor.menu.search.SearchMenuItem

class EditorController : BaseController(), IEditorController {

    @FXML
    override lateinit var home: MenuItem

    @FXML
    override lateinit var settings: MenuItem

    @FXML
    override lateinit var recompile: MenuItem

    // Menu/Search
    @FXML
    override lateinit var searchMenu: Menu

    @FXML
    override lateinit var search: SearchMenuItem

    // Menu/Misc
    @FXML
    override lateinit var examineFile: MenuItem

    // Main area
    @FXML
    override lateinit var fileManagerView: FileManagerView

    @FXML
    override lateinit var editorArea: CodeEditorArea

    @FXML
    override lateinit var console: ConsoleView

    override val layoutFile = "editor.fxml"
}
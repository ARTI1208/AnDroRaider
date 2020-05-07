package ru.art2000.androraider.view.editor

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.VBox
import ru.art2000.androraider.mvp.BaseController
import ru.art2000.androraider.view.editor.filemanager.FileManagerView
import ru.art2000.androraider.view.editor.menu.search.SearchMenuItem

class EditorController : BaseController(), IEditorController {

    @FXML
    override lateinit var home: MenuItem

    @FXML
    override lateinit var settings: MenuItem

    @FXML
    override lateinit var projectSettings: MenuItem

    @FXML
    override lateinit var dataViewer: MenuItem

    @FXML
    override lateinit var recompile: MenuItem

    // Menu/Search
    @FXML
    override lateinit var searchMenu: Menu

    @FXML
    override lateinit var search: SearchMenuItem

    // Menu/Misc
    @FXML
    override lateinit var miscMenu: Menu

    @FXML
    override lateinit var examineFile: MenuItem

    // Main area
    @FXML
    override lateinit var fileManagerView: FileManagerView

    @FXML
    override lateinit var console: ConsoleView

    @FXML
    override lateinit var editorTabPane: TabPane

    @FXML
    override lateinit var codeEditorContainer: VBox

    override val layoutFile = "editor.fxml"
}
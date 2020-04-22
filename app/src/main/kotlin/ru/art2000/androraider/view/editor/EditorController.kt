package ru.art2000.androraider.view.editor

import io.reactivex.disposables.Disposable
import javafx.fxml.FXML
import javafx.scene.control.CustomMenuItem
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.control.TextField

class EditorController {

    @FXML
    lateinit var home: MenuItem

    @FXML
    lateinit var settings: MenuItem

    @FXML
    lateinit var recompile: MenuItem

    // Menu/Search
    @FXML
    lateinit var searchMenu: Menu

    @FXML
    lateinit var search: CustomMenuItem

    // Menu/Misc
    @FXML
    lateinit var examineFile: MenuItem

    // Main area
    @FXML
    lateinit var fileManagerView: FileManagerView

    @FXML
    lateinit var editorArea: CodeEditorArea

    @FXML
    lateinit var console: ConsoleView

    var searchField = TextField()
}
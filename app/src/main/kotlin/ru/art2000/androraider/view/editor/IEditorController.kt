package ru.art2000.androraider.view.editor

import javafx.scene.Parent
import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.control.TabPane
import javafx.scene.layout.VBox
import ru.art2000.androraider.mvp.IController
import ru.art2000.androraider.view.editor.menu.search.SearchMenuItem

interface IEditorController : IController {

    val home: MenuItem

    val settings: MenuItem

    val recompile: MenuItem

    // Menu/Search
    val searchMenu: Menu

    val search: SearchMenuItem

    // Menu/Misc
    val examineFile: MenuItem

    // Main area
    val fileManagerView: FileManagerView

    val editorArea: CodeEditorArea

    val console: ConsoleView

    val editorTabPane: TabPane

    val codeEditorContainer: VBox
}
package ru.art2000.androraider.view.editor

import javafx.scene.control.Menu
import javafx.scene.control.MenuItem
import javafx.scene.control.TabPane
import javafx.scene.layout.VBox
import ru.art2000.androraider.mvp.IController
import ru.art2000.androraider.view.editor.filemanager.FileManagerView
import ru.art2000.androraider.view.editor.menu.search.SearchMenuItem

interface IEditorController : IController {

    val home: MenuItem

    val settings: MenuItem

    val projectSettings: MenuItem

    val dataViewer: MenuItem

    val recompile: MenuItem

    // Menu/Search
    val searchMenu: Menu

    val search: SearchMenuItem

    // Menu/Misc
    val miscMenu: Menu

    val examineFile: MenuItem

    // Main area
    val fileManagerView: FileManagerView

    val console: ConsoleView

    val editorTabPane: TabPane

    val codeEditorContainer: VBox
}
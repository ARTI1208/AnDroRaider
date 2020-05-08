package ru.art2000.androraider.view.editor.filemanager

import javafx.event.EventHandler
import javafx.fxml.Initializable
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.TextAlignment
import org.fxmisc.richtext.StyleClassedTextField
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.autoWidth
import ru.art2000.androraider.utils.getDrawable
import ru.art2000.androraider.view.editor.Searchable
import java.io.File
import java.net.URL
import java.util.*

class FileManagerTreeListItem(private val searchable: Searchable<String>) : TreeCell<File>(), Initializable {

    private val fileContextMenu = ContextMenu()

    private val textField = StyleClassedTextField().apply {
        isEditable = false
        isMouseTransparent = true
        background = null
        styleClass.setAll("editor-file-manager-item-label")
        alignment = TextAlignment.JUSTIFY
        prefHeight = DEFAULT_SIZE
        minHeight = prefHeight
        maxHeight = prefHeight
    }

    private val l = HBox().apply {
        alignment = Pos.CENTER_LEFT
        spacing = 5.0
    }

    private val icon = ImageView().apply { styleClass.add("icon") }

    companion object {

        private val FOLDER_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("folder.png")

        private val TEXT_FILE_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("txt.png")

        private val UNKNOWN_FILE_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("unknown.png")

        val ARROW_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("arrow.png")

        const val DEFAULT_SIZE = 26.0
    }

    init {
        val close = ImageView(ARROW_ICON)
        close.fitWidth = DEFAULT_SIZE - 8
        close.fitHeight = DEFAULT_SIZE - 8
        disclosureNode = close

        val menuItemCreate = MenuItem("Create")
        menuItemCreate.onAction = EventHandler {
            val parentTreeView = treeView
            if (parentTreeView is FileManagerView) {
                parentTreeView.onTreeItemCreate(treeItem)
            }
        }

        val menuItemRename = MenuItem("Rename")
        menuItemRename.onAction = EventHandler {
            val parentTreeView = treeView
            if (parentTreeView is FileManagerView) {
                parentTreeView.onTreeItemRename(treeItem)
            }
        }

        val menuItemDelete = MenuItem("Delete")
        menuItemDelete.onAction = EventHandler {
            val parentTreeView = treeView
            if (parentTreeView is FileManagerView) {
                parentTreeView.onTreeItemDelete(treeItem)
            }
        }

        fileContextMenu.items.addAll(
                menuItemCreate,
                menuItemRename,
                menuItemDelete
        )

        styleClass.add("file-manager-item")

        searchable.currentSearchValueProperty.addListener { _, _, newValue ->
            updateHighlighting(newValue, textField.text)
        }
        textField.minWidthProperty().bind(textField.prefWidthProperty())
        textField.textProperty().addListener { _, _, newValue ->
            updateHighlighting(searchable.currentSearchValue, newValue)
        }

        textField.autoWidth()

        l.children.addAll(icon, textField)
        prefHeight = DEFAULT_SIZE
        minHeight = prefHeight
        maxHeight = prefHeight

//        HBox.setHgrow(textField, Priority.ALWAYS)
//        l.alignment = Pos.CENTER_LEFT
//        l.spacing = 5.0
    }

    private fun updateHighlighting(toFind: String?, text: String?) {
        if (text == null)
            return

        textField.setStyle(0, text.length, listOf("selected-item-text"))

        if (toFind.isNullOrEmpty())
            return

        Regex(toFind.toLowerCase()).findAll(text.toLowerCase()).forEach {
            textField.setStyle(it.range.first, it.range.last + 1, listOf("search"))
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        maxWidth = Control.USE_PREF_SIZE
    }

    override fun updateItem(item: File?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty || item == null) {
            contextMenu = null
            graphic = null
            text = null
            textField.replaceText("")
            contentDisplay = ContentDisplay.TEXT_ONLY
            return
        }

        contextMenu = fileContextMenu
        icon.image = when {
            item.isDirectory -> FOLDER_ICON
            TypeDetector.Text.listContains(item.extension) -> TEXT_FILE_ICON
            else -> UNKNOWN_FILE_ICON
        }
        textField.replaceText(item.name)
        textField.prefHeight = DEFAULT_SIZE
//        textField.prefWidth = width

        icon.fitWidth = DEFAULT_SIZE
        icon.fitHeight = DEFAULT_SIZE
        text = item.name
        graphic = l
        contentDisplay = ContentDisplay.GRAPHIC_ONLY

        if (treeItem?.isExpanded == true)
            disclosureNode.rotate = 0.0
        else
            disclosureNode.rotate = -90.0
    }
}
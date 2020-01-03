package ru.art2000.androraider.windows.editor

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.image.ImageView
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.getDrawable
import java.io.File
import java.net.URL
import java.util.*

class FileManagerTreeListItem : TreeCell<File>(), Initializable {

    private val fileContextMenu = ContextMenu()

    companion object {

        private val FOLDER_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("folder.png")

        private val TEXT_FILE_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("txt.png")

        private val UNKNOWN_FILE_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("unknown.png")
    }

    init {
        val close = ImageView(javaClass.getDrawable("arrow.png"))
        close.fitWidth = 20.0
        close.fitHeight = 20.0
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
            contentDisplay = ContentDisplay.TEXT_ONLY
            return
        }

        contextMenu = fileContextMenu

        val icon = ImageView(when {
            item.isDirectory -> FOLDER_ICON
            TypeDetector.Text.listContains(item.extension) -> TEXT_FILE_ICON
            else -> UNKNOWN_FILE_ICON
        })

        icon.fitWidth = 20.0
        icon.fitHeight = 20.0
        text = item.name
        graphic = icon
        contentDisplay = ContentDisplay.LEFT

        if (treeItem?.isExpanded == true)
            disclosureNode.rotate = 0.0
        else
            disclosureNode.rotate = -90.0
    }
}
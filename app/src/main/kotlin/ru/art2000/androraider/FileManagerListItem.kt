package ru.art2000.androraider

import javafx.fxml.Initializable
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Control
import javafx.scene.control.TreeCell
import javafx.scene.image.ImageView
import java.io.File
import java.net.URL
import java.util.*

class FileManagerTreeListItem : TreeCell<File?>(), Initializable {

    init {
        val close = ImageView(javaClass.getDrawable("arrow.png"))
        close.fitWidth = 20.0
        close.fitHeight = 20.0
        disclosureNode = close
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        maxWidth = Control.USE_PREF_SIZE
    }

    override fun updateItem(item: File?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty || item == null) {
            graphic = null
            text = null
            contentDisplay = ContentDisplay.TEXT_ONLY
            return
        }

        val icon = ImageView(when {
            item.isDirectory -> javaClass.getDrawable("folder.png")
            TypeDetector.Text.listContains(item.extension) -> javaClass.getDrawable("txt.png")
            else -> javaClass.getDrawable("unknown.png")
        })
        icon.fitWidth = 20.0
        icon.fitHeight = 20.0
        text = item.name
        graphic = icon
        contentDisplay = ContentDisplay.LEFT

        treeItem.expandedProperty()

        if (treeItem?.isExpanded == true)
            disclosureNode.rotate = 0.0
        else
            disclosureNode.rotate = -90.0
    }

}
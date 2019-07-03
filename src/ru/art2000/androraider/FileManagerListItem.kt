package ru.art2000.androraider

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Control
import javafx.scene.control.ListCell
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Text
import java.io.File
import java.net.URL
import java.util.*

class FileManagerListItem : ListCell<File?>(), Initializable {

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        maxWidth = Control.USE_PREF_SIZE
    }

    override fun updateItem(item: File?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty) {
            text = null
            contentDisplay = ContentDisplay.TEXT_ONLY
            return
        }

        if (item == null) {
            return
        }
        val icon = ImageView(when {
            item.isDirectory -> LoadUtils.getDrawable("folder.png")
            TypeDetector.Text.listContains(item.extension) -> LoadUtils.getDrawable("txt.png")
            else -> LoadUtils.getDrawable("unknown.png")
        })
        icon.fitWidth = 20.0
        icon.fitHeight = 20.0
        text = item.name
        graphic = icon
        contentDisplay = ContentDisplay.LEFT
    }

}
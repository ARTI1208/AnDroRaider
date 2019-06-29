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

    @FXML
    lateinit var pane: AnchorPane
    @FXML
    lateinit var fileName: Text
    @FXML
    lateinit var fileIcon: ImageView

    private var fxmlLoader: FXMLLoader? = null

    init {
        fxmlLoader = FXMLLoader(javaClass.getResource(LoadUtils.getLayout("filemanager_list_item.fxml")))
        fxmlLoader?.setController(this)
        try {
            pane = fxmlLoader!!.load()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

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

        fileName.text = item.name
        when {
            item.isDirectory -> fileIcon.image = LoadUtils.getDrawable("folder.png")
            TypeDetector.Text.listContains(item.extension) -> fileIcon.image = LoadUtils.getDrawable("txt.png")
            else -> fileIcon.image = LoadUtils.getDrawable("unknown.png")
        }
        text = null
        graphic = pane
        contentDisplay = ContentDisplay.GRAPHIC_ONLY
    }

}
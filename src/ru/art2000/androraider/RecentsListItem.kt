package ru.art2000.androraider

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Control
import javafx.scene.control.ListCell
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Text
import java.net.URL
import java.util.*
import java.util.logging.Logger

class RecentsListItem : ListCell<RecentProject>(), Initializable {

    @FXML
    lateinit var pane: AnchorPane
    @FXML
    lateinit var projectName: Text
    @FXML
    lateinit var projectLocation: Text
    @FXML
    lateinit var removeButton: Button

    private var fxmlLoader: FXMLLoader? = null

    init {
        fxmlLoader = FXMLLoader(javaClass.getResource(LoadUtils.getLayout("recents_list_item.fxml")))
        fxmlLoader?.setController(this)
        try {
            pane = fxmlLoader!!.load()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        removeButton.setOnAction {
            Launcher.removeFromRecents(projectLocation.text)
            listView.items.remove(item)
        }
        try {
            prefWidthProperty().bind(listView.widthProperty().subtract(20))
        } catch (e : java.lang.Exception){
//            throw java.lang.Exception("Cannot bind ListView width")
            System.out.println("Cannot bind ListView width")
        }
        maxWidth = Control.USE_PREF_SIZE
    }

    override fun updateItem(item: RecentProject?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty) {
            text = null
            contentDisplay = ContentDisplay.TEXT_ONLY
        } else {
            projectName.text = item?.projectName
            projectLocation.text = item?.projectLocation
            text = null
            graphic = pane
            contentDisplay = ContentDisplay.GRAPHIC_ONLY
        }
    }

}
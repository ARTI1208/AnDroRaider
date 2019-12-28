package ru.art2000.androraider

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Control
import javafx.scene.control.ListCell
import javafx.scene.effect.Blend
import javafx.scene.effect.BlendMode
import javafx.scene.effect.ColorInput
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import java.net.URL
import java.util.*


class RecentsListItem : ListCell<RecentProject>(), Initializable {

    @FXML
    lateinit var pane: AnchorPane
    @FXML
    lateinit var projectName: Text
    @FXML
    lateinit var projectLocation: Text
    @FXML
    lateinit var removeButton: Button

    private val defaultCrossColor: Color = Color.valueOf("#33A3F8")

    private var fxmlLoader: FXMLLoader? = null

    init {
        fxmlLoader = FXMLLoader(javaClass.getResource("/layout/recents_list_item.fxml"))
        fxmlLoader?.setController(this)
        try {
            pane = fxmlLoader!!.load()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        removeButton.onAction = EventHandler {
            Launcher.removeFromRecents(projectLocation.text)
            listView.items.remove(item)
        }
        maxWidth = Control.USE_PREF_SIZE
        println("cross = ${javaClass.getDrawable("cross.png") == null}")

        val imageView = ImageView(javaClass.getDrawable("cross.png"))
        imageView.fitHeight = 20.0
        imageView.fitWidth = 20.0
        imageView.isVisible = false

        imageView.effect = Blend(
                BlendMode.SRC_ATOP,
                null,
                ColorInput(
                        0.0,
                        0.0,
                        imageView.image.width,
                        imageView.image.height,
                        defaultCrossColor
                )
        )

        removeButton.graphic = imageView
        removeButton.styleClass.clear()
        removeButton.hoverProperty().addListener { _, _, now ->
            val color = if (now) {
                if (isSelected) Color.WHITE else Color.BLACK
            } else
                defaultCrossColor

            val tint = Blend(
                    BlendMode.SRC_ATOP,
                    null, ColorInput(
                    0.0,
                    0.0,
                    imageView.image.width,
                    imageView.image.height,
                    color))
            imageView.effect = tint
        }
        projectName.style = "-fx-fill: black; -fx-font-weight: bold;"
        projectLocation.style = "-fx-fill: black"
        selectedProperty().addListener { _, _, now ->
            if (now) {
                projectName.style = "-fx-fill: white; -fx-font-weight: bold;"
                projectLocation.style = "-fx-fill: white"
            } else {
                projectName.style = "-fx-fill: black; -fx-font-weight: bold;"
                projectLocation.style = "-fx-fill: black"
            }
        }

        hoverProperty().addListener { _, _, isHovered ->
            imageView.isVisible = isHovered
        }
    }

    override fun updateItem(item: RecentProject?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty) {
            text = null
            contentDisplay = ContentDisplay.TEXT_ONLY
        } else {
            projectName.text = item?.projectName
            projectLocation.text = item?.projectLocation
            text = item?.projectName + "\n" + item?.projectLocation
            graphic = pane
            contentDisplay = ContentDisplay.GRAPHIC_ONLY
        }
    }

}
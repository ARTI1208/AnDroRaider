package ru.art2000.androraider.view.launcher

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.effect.Blend
import javafx.scene.effect.BlendMode
import javafx.scene.effect.ColorInput
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.text.Text
import ru.art2000.androraider.model.launcher.RecentProject
import ru.art2000.androraider.utils.getDrawable
import ru.art2000.androraider.utils.getLayout
import java.net.URL
import java.util.*


class RecentsListItem : ListCell<RecentProject>(), Initializable {

    @FXML
    lateinit var pane: AnchorPane
    @FXML
    lateinit var projectName: Label
    @FXML
    lateinit var projectLocation: Label
    @FXML
    lateinit var removeButton: Button

    private val defaultCrossColor: Color = Color.valueOf("#33A3F8")

    companion object {
        val crossImage: Image? by lazy {
            RecentsListItem::class.java.getDrawable("cross.png")
        }
    }

    private var fxmlLoader: FXMLLoader? = null

    init {
        fxmlLoader = javaClass.getLayout("recents_list_item.fxml")
        fxmlLoader?.setController(this)
        try {
            pane = fxmlLoader!!.load()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        prefWidth = 0.0
        projectName.prefWidthProperty().bind(widthProperty().subtract(10))
        projectLocation.prefWidthProperty().bind(widthProperty().subtract(10))
        projectName.textOverrun = OverrunStyle.CENTER_ELLIPSIS
        projectLocation.textOverrun = OverrunStyle.LEADING_ELLIPSIS
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        removeButton.onAction = EventHandler {
            listView.items.remove(item)
        }

        val imageView = ImageView(crossImage)
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
//        prefWidthProperty().bind(listView.widthProperty().subtract(20));
//        setMaxWidth(Control.USE_PREF_SIZE);
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
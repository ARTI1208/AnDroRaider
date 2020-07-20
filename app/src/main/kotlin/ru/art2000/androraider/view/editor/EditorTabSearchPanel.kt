package ru.art2000.androraider.view.editor

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.value.ObservableDoubleValue
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import ru.art2000.androraider.model.editor.SearchData
import ru.art2000.androraider.utils.Visibility
import ru.art2000.androraider.utils.getDrawable
import ru.art2000.androraider.utils.visibility
import tornadofx.button
import tornadofx.hbox
import tornadofx.imageview

class EditorTabSearchPanel(val searchable: Searchable<String>, val searchData: SearchData) : BorderPane() {

    private val panelHeight = 30.0

    private val searchField = TextField()

    val managedHeightProperty: ObservableDoubleValue

    init {

        val managedHeightImpl = SimpleDoubleProperty()
        managedHeightProperty = managedHeightImpl

        heightProperty().addListener { _, _, newValue ->
            if (isManaged) {
                managedHeightImpl.value = newValue.toDouble()
            }
        }

        managedProperty().addListener { _, _, newValue ->
            if (newValue) {
                managedHeightImpl.value = height
            } else {
                managedHeightImpl.value = 0.0
            }
        }

        background = Background(BackgroundFill(Color.LIGHTGRAY, null, null))

        searchField.prefHeight = panelHeight
        searchField.prefWidth = 300.0

        searchField.addEventFilter(KeyEvent.KEY_PRESSED) {
            if (it.code == KeyCode.ENTER) {
                if (it.isShiftDown) {
                    searchable.findPrevious()
                } else {
                    searchable.findNext()
                }
            } else if (it.code == KeyCode.F && it.isShortcutDown) {
                searchField.selectAll()
            }
        }

        searchData.searchStringProperty.bindBidirectional(searchField.textProperty())

        searchField.textProperty().addListener { _, _, newValue ->
            searchable.find(newValue ?: "")
        }

        val prevButton = button(text = "Prev") {
            setOnAction { searchable.findPrevious() }
            prefHeight = panelHeight
        }

        val nextButton = button(text = "Next") {
            setOnAction { searchable.findNext() }
            prefHeight = panelHeight
        }

        val buttonImage = javaClass.getDrawable("cross.png")?.let {
            imageview(it) {
                fitHeight = panelHeight / 2
                fitWidth = panelHeight / 2
            }
        }

        val closeButton = button(graphic = buttonImage) {
            setOnAction { hide() }
            prefHeight = panelHeight
        }

        prefHeight = panelHeight

        left = hbox {
            children += listOf(searchField, prevButton, nextButton)
        }
        right = closeButton
    }

    public fun show() {
        visibility = Visibility.VISIBLE
        searchField.requestFocus()
        searchField.selectAll()
    }

    public fun hide() {
        visibility = Visibility.GONE
    }
}
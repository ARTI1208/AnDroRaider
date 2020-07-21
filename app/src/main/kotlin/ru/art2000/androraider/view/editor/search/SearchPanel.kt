package ru.art2000.androraider.view.editor.search

import javafx.beans.property.Property
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableDoubleValue
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.paint.Color
import ru.art2000.androraider.utils.Visibility
import ru.art2000.androraider.utils.getDrawable
import ru.art2000.androraider.utils.visibility
import ru.art2000.androraider.model.editor.Searchable
import tornadofx.button
import tornadofx.hbox
import tornadofx.imageview
import tornadofx.getValue
import tornadofx.setValue

class SearchPanel(initialSearchable: Searchable<String>? = null) : BorderPane() {

    val searchableProperty: Property<Searchable<String>> = SimpleObjectProperty()

    var searchable: Searchable<String>? by searchableProperty

    private val panelHeight = 30.0

    private val searchField = TextField()

    val managedHeightProperty: ObservableDoubleValue

    init {
        searchable = initialSearchable
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
                    searchable?.findPrevious()
                } else {
                    searchable?.findNext()
                }
            } else if (it.code == KeyCode.F && it.isShortcutDown) {
                searchField.selectAll()
            }
        }

        searchField.textProperty().addListener { _, _, newValue ->
            searchable?.find(newValue ?: "")
        }

        val prevButton = button(text = "Prev") {
            setOnAction { searchable?.findPrevious() }
            prefHeight = panelHeight
        }

        val nextButton = button(text = "Next") {
            setOnAction { searchable?.findNext() }
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

        addEventHandler(KeyEvent.KEY_PRESSED) {
            if (it.code == KeyCode.ESCAPE) {
                hide()
            }
        }
    }

    public fun show() {
        visibility = Visibility.VISIBLE
        searchField.requestFocus()
        searchField.selectAll()
        searchable?.find(searchField.text)
    }

    public fun hide() {
        visibility = Visibility.GONE
        searchable?.clearSearch()
    }
}
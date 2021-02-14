package ru.art2000.androraider.view.editor.search

import javafx.beans.property.Property
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Region
import javafx.scene.layout.VBox
import ru.art2000.androraider.model.editor.Searchable
import ru.art2000.androraider.utils.getValue
import ru.art2000.androraider.utils.setValue

class SearchableNodeWrapper(initialContent: Region? = null, initialSearchable: Searchable<String>? = null): VBox() {

    val searchableProperty: Property<Searchable<String>> = SimpleObjectProperty()
    var searchable: Searchable<String>? by searchableProperty

    val contentProperty: Property<Region> = SimpleObjectProperty()
    var content: Region? by contentProperty

    private val searchBox = SearchPanel().apply { hide() }

    private val modifiableChildren: ObservableList<Node>
        get() = super.getChildren()

    override fun getChildren(): ObservableList<Node> {
        return super.getChildrenUnmodifiable()
    }

    private val searchEventHandler = { keyEvent: KeyEvent ->
        if (keyEvent.code == KeyCode.F && keyEvent.isShortcutDown) {
            searchBox.show()
        }
    }

    init {
        searchBox.searchableProperty.bind(searchableProperty)
        modifiableChildren += searchBox

        contentProperty.addListener { _, oldValue, newValue ->
            if (oldValue != null) {
                modifiableChildren -= oldValue
                oldValue.prefHeightProperty().unbind()
                oldValue.removeEventHandler(KeyEvent.KEY_PRESSED, searchEventHandler)
            }

            if (newValue != null) {
                modifiableChildren += newValue
                newValue.prefHeightProperty().unbind()
                newValue.prefHeightProperty().bind(heightProperty().subtract(searchBox.managedHeightProperty))
                newValue.addEventHandler(KeyEvent.KEY_PRESSED, searchEventHandler)
            }
        }

        content = initialContent
        searchable = initialSearchable
    }


}
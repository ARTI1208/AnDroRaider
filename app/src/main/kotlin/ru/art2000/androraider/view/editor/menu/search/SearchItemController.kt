package ru.art2000.androraider.view.editor.menu.search

import javafx.beans.property.ObjectPropertyBase
import javafx.beans.property.Property
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import ru.art2000.androraider.mvp.BaseController
import ru.art2000.androraider.view.editor.Searchable

class SearchItemController : BaseController(), ISearchItemController {

    @FXML
    override lateinit var searchScopeLabel: Label

    @FXML
    override lateinit var searchField: TextField

    @FXML
    override lateinit var findPreviousButton: Button

    @FXML
    override lateinit var findNextButton: Button

    @FXML
    override val layoutFile = "search_menu_item.fxml"

    override val currentSearchableProperty = object : ObjectPropertyBase<Searchable<String>?>() {
        override fun getName(): String {
            return "currentSearchable"
        }

        override fun getBean(): Any {
            return this@SearchItemController
        }
    }

    override var currentSearchable: Searchable<String>?
        get() {
            return currentSearchableProperty.value
        }
        set(value) {
            currentSearchableProperty.value = value
        }
}
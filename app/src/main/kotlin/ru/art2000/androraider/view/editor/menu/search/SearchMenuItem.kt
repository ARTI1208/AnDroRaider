package ru.art2000.androraider.view.editor.menu.search

import javafx.event.EventHandler
import javafx.scene.control.CustomMenuItem
import javafx.scene.input.KeyCode
import ru.art2000.androraider.utils.bind
import ru.art2000.androraider.view.editor.Searchable

class SearchMenuItem : CustomMenuItem(), ISearchItemController by SearchItemController() {

    private val searchMapping: HashMap<Searchable<String>, String> = HashMap()

    init {
        isHideOnClick = false
        content = root

        searchScopeLabel.textProperty().bind(currentSearchableProperty) {
            it?.javaClass?.simpleName ?: "No searchable nodes focused"
        }

        searchField.textProperty().addListener { _, _, newValue ->
            val s = currentSearchable
            if (newValue != null && s != null) {
                searchMapping[s] = newValue
            }
        }

        searchField.disableProperty().bind(currentSearchableProperty) { it == null }
        findPreviousButton.disableProperty().bind(currentSearchableProperty) { it == null }
        findNextButton.disableProperty().bind(currentSearchableProperty) { it == null }

        searchField.textProperty().addListener { _, _, now ->
            if (now != null) {
                currentSearchable
                        ?.also { searchMapping[it] = now }
                        ?.findAll(now)
            }
        }
//        (root as Region).background = Background(BackgroundFill(Paint.valueOf("#dedede"), CornerRadii.EMPTY, Insets.EMPTY))
        findPreviousButton.onAction = EventHandler {
            currentSearchable?.findPrevious()
        }

        findNextButton.onAction = EventHandler {
            currentSearchable?.findNext()
        }

        searchField.focusedProperty().addListener { _, _, newValue ->
            if (newValue && currentSearchable != null) {
                searchField.positionCaret(searchField.text?.length ?: 0)
            }
        }

        currentSearchableProperty.addListener { _, _, newValue ->
            if (newValue != null) {
                searchField.text = searchMapping.getOrDefault(newValue, "")
                searchField.positionCaret(searchField.text.length)
            } else {
                searchField.text = null
            }
        }

        searchField.onKeyPressed = EventHandler {
            if (it.code == KeyCode.ENTER) {
                when {
                    it.isShiftDown && !findPreviousButton.isDisable -> currentSearchable?.findPrevious()
                    !findNextButton.isDisable -> currentSearchable?.findNext()
                }
            }
        }
    }


}
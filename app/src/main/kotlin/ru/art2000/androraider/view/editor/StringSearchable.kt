package ru.art2000.androraider.view.editor

import javafx.beans.property.StringProperty

interface StringSearchable : Searchable<String> {

    override val currentSearchValueProperty: StringProperty

    override fun clearSearch() {
        currentSearchValue = ""
    }
}
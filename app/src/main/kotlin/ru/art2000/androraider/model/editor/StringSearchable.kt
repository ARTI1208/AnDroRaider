package ru.art2000.androraider.model.editor

import javafx.beans.property.StringProperty

interface StringSearchable : Searchable<String> {

    override val currentSearchValueProperty: StringProperty

    override fun clearSearch() {
        currentSearchValue = ""
    }
}
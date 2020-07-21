package ru.art2000.androraider.model.editor

import javafx.beans.property.Property

interface Searchable<T> {

    var currentSearchValue: T
        get() = currentSearchValueProperty.value
        set(value) { currentSearchValueProperty.value = value }

    val currentSearchValueProperty: Property<T>

    fun find(valueToFind: T) {
        currentSearchValue = valueToFind
    }

    fun findAll(valueToFind: T) {
        currentSearchValue = valueToFind
    }

    fun findNext()

    fun findPrevious()

    fun clearSearch()
}
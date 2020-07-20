package ru.art2000.androraider.view.editor

import javafx.beans.property.Property

interface Searchable<T> {

    var currentSearchValue: T
        get() = currentSearchValueProperty.value
        set(value) { currentSearchValueProperty.value = value }

    val currentSearchValueProperty: Property<T>

    fun find(valueToFind: T)

    fun findAll(valueToFind: T)

    fun findNext()

    fun findPrevious()
}
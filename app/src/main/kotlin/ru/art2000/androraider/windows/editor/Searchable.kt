package ru.art2000.androraider.windows.editor

interface Searchable<T> {

    var currentSearchValue: T

    fun find(valueToFind: T)

    fun findAll(valueToFind: T)

    fun findNext()

    fun findPrevious()
}
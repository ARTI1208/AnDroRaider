package ru.art2000.androraider

interface Searchable<T> {

    var currentSearchValue: T

    fun find(valueToFind: T)

    fun findAll(valueToFind: T)

    fun findNext()

    fun findPrevious()
}
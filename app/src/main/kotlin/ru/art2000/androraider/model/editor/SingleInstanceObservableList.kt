package ru.art2000.androraider.model.editor

import javafx.collections.ObservableList

class SingleInstanceObservableList<E>(private val observableList: ObservableList<E>) : ObservableList<E> by observableList {

    override fun add(index: Int, element: E) {
        if (element !in this)
            observableList.add(index, element)
    }

    override fun add(element: E): Boolean {
        return if (element !in this)
            observableList.add(element)
        else
            false
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        return observableList.addAll(index, elements.filter { it !in observableList })
    }

    override fun addAll(elements: Collection<E>): Boolean {
        return observableList.addAll(elements.filter { it !in observableList })
    }
}
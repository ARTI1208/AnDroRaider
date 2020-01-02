package ru.art2000.androraider.windows.editor

import java.util.function.Consumer

class SearchSpanList() : ArrayList<IntRange>() {

    var searchString: String? = null
        set(value) {
            list.clear()
            field = value
        }
    private val list = ArrayList<IntRange>()

    constructor(searchString: String) : this() {
        this.searchString = searchString
    }

    override fun forEach(action: Consumer<in IntRange>?) {
        list.forEach(action)
    }

    override fun clear() {
        list.clear()
        searchString = null
    }

    override fun add(element: IntRange): Boolean {
        println("Adding $element")
        return list.add(element)
    }

    override fun contains(element: IntRange): Boolean {
        return list.contains(element)
    }

    override fun containsAll(elements: Collection<IntRange>): Boolean {
        return list.containsAll(elements)
    }

    override fun get(index: Int): IntRange {
        return list[index]
    }

    override fun indexOf(element: IntRange): Int {
        return list.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    override val size: Int
        get() = list.size

}
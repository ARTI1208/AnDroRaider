package ru.art2000.androraider.utils

import org.reactfx.Subscription
import org.reactfx.collection.ListChangeAccumulator
import org.reactfx.collection.LiveListBase
import org.reactfx.collection.ProperLiveList
import kotlin.collections.ArrayList

@Suppress("unused")
class MyLiveArrayList<E> private constructor(c: ArrayList<E>) : LiveListBase<E>() {
    private var list: ArrayList<E>

    init {
        list = c
    }

    constructor() : this(ArrayList())

    constructor(c: Collection<E>) : this(ArrayList(c))

    @SafeVarargs
    constructor(vararg initialElements: E) : this(ArrayList(listOf(*initialElements)))

    override operator fun get(index: Int): E {
        return list[index]
    }

    override operator fun set(index: Int, element: E): E {
        val replaced = list.set(index, element)
        fireElemReplacement(index, replaced)
        return replaced
    }

    override fun setAll(c: Collection<E>): Boolean {
        val removed: List<E> = list
        list = ArrayList(c)
        fireContentReplacement(removed)
        return true
    }

    @SafeVarargs
    override fun setAll(vararg elems: E): Boolean {
        return setAll(listOf(*elems))
    }

    override fun add(index: Int, element: E) {
        list.add(index, element)
        fireElemInsertion(index)
    }

    override fun add(element: E): Boolean {
        add(size, element)
        return true
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        list.addAll(index, elements)
        fireRangeInsertion(index, elements.size)
        return false
    }

    override fun addAll(elements: Collection<E>): Boolean {
        return addAll(size, elements)
    }

    @SafeVarargs
    override fun addAll(vararg elems: E): Boolean {
        return addAll(listOf(*elems))
    }

    override fun remove(from: Int, to: Int) {
        val sublist = list.subList(from, to)
        val removed: List<E> = ArrayList(sublist)
        sublist.clear()
        fireRemoveRange(from, removed)
    }

    override fun remove(element: E): Boolean {
        val i = list.indexOf(element)
        return if (i != -1) {
            removeAt(i)
            true
        } else {
            false
        }
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        val acc = ListChangeAccumulator<E>()
        for (o in elements) {
            val i = list.indexOf(o)
            if (i != -1) {
                val removed = list.removeAt(i)
                acc.add(ProperLiveList.elemRemoval(i, removed))
            }
        }
        return if (acc.isEmpty) {
            false
        } else {
            notifyObservers(acc.fetch())
            true
        }
    }

    @SafeVarargs
    override fun removeAll(vararg elems: E): Boolean {
        return removeAll(listOf(*elems))
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        val acc = ListChangeAccumulator<E>()
        for (i in size - 1 downTo 0) {
            val elem = list[i]
            if (!elements.contains(elem)) {
                list.removeAt(i)
                acc.add(ProperLiveList.elemRemoval(i, elem))
            }
        }
        return if (acc.isEmpty) {
            false
        } else {
            notifyObservers(acc.fetch())
            true
        }
    }

    @SafeVarargs
    override fun retainAll(vararg elems: E): Boolean {
        return retainAll(listOf(*elems))
    }

    override fun clear() {
        setAll(emptyList())
    }

    override fun observeInputs(): Subscription? {
        return Subscription.EMPTY
    }

    override val size: Int
        get() = list.size

    override fun removeAt(index: Int): E {
        val removed = list.removeAt(index)
        fireElemRemoval(index, removed)
        return removed
    }

    override fun iterator(): MutableIterator<E> {
        return list.iterator()
    }

    override fun listIterator(): MutableListIterator<E> {
        return list.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<E> {
        return list.listIterator(index)
    }
}
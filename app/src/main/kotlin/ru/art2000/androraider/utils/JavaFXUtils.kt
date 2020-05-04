package ru.art2000.androraider.utils

import javafx.beans.property.Property
import javafx.beans.value.ObservableValue
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.IndexRange
import org.fxmisc.flowless.VirtualFlow

fun <T, E> Property<T>.bind(observable: ObservableValue<E>, converter: (e: E) -> T) {
    observable.addListener { _, _, newValue ->
        value = converter(newValue)
    }
    value = converter(observable.value)
}

operator fun IndexRange.contains(int: Int): Boolean {
    return int in start..end
}

public fun Parent.walk(f : (node: Node) -> Unit) {
    childrenUnmodifiable.forEach {
        if (it is Parent) {
            it.walk(f)
        }
        f(it)
    }
}
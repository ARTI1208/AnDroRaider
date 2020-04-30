package ru.art2000.androraider.utils

import javafx.beans.property.Property
import javafx.beans.value.ObservableValue

fun <T, E> Property<T>.bind(observable: ObservableValue<E>, converter: (e: E) -> T) {
    observable.addListener { _, _, newValue ->
        value = converter(newValue)
    }
    value = converter(observable.value)
}
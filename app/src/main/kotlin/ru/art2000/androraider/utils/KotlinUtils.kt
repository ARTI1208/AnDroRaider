@file:Suppress("unused")

package ru.art2000.androraider.utils

import javafx.beans.property.Property
import javafx.beans.value.ObservableValue
import kotlin.reflect.KProperty

public operator fun IntRange.component1(): Int = first

public operator fun IntRange.component2(): Int = last

public inline fun <reified T> Iterable<*>.findOfType(predicate: (T) -> Boolean): T? {
    return find {
        it is T && predicate(it)
    } as T?
}

public inline fun <reified T> Iterable<*>.findOfType(): T? {
    return find {
        it is T
    } as T?
}

public operator fun <T> ObservableValue<T>.getValue(thisRef: Any, property: KProperty<*>): T = value
public operator fun <T> Property<T>.setValue(thisRef: Any, property: KProperty<*>, value: T?) = setValue(value)
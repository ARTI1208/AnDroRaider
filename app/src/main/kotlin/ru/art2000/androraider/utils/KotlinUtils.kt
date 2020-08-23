@file:Suppress("unused")

package ru.art2000.androraider.utils

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


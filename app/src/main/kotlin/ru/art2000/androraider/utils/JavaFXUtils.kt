package ru.art2000.androraider.utils

import javafx.beans.property.Property
import javafx.beans.value.ObservableValue
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.IndexRange
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
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

fun KeyEvent.toKeyCodeCombination(): KeyCodeCombination? {
    if (code.isModifierKey)
        return null

    val modifiers = mutableListOf<KeyCombination.Modifier>()

    if (isShortcutDown)
        modifiers.add(KeyCombination.SHORTCUT_DOWN)

    if (isAltDown)
        modifiers.add(KeyCombination.ALT_DOWN)

    // use SHORTCUT_DOWN instead
//    if (isControlDown)
//        modifiers.add(KeyCombination.CONTROL_DOWN)

    if (isMetaDown)
        modifiers.add(KeyCombination.META_DOWN)

    if (isShiftDown)
        modifiers.add(KeyCombination.SHIFT_DOWN)

//    println(this)

    return KeyCodeCombination(code, *modifiers.toTypedArray())
}

public fun Parent.walk(f : (node: Node) -> Unit) {
    childrenUnmodifiable.forEach {
        if (it is Parent) {
            it.walk(f)
        }
        f(it)
    }
}
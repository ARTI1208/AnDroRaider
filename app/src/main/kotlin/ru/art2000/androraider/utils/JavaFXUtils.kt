package ru.art2000.androraider.utils

import javafx.application.Platform
import javafx.beans.property.Property
import javafx.beans.value.ObservableValue
import javafx.geometry.Bounds
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.IndexRange
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent

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
    if (code.isModifierKey || code == KeyCode.UNDEFINED)
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

public fun Parent.walk(f: (node: Node) -> Unit) {
    childrenUnmodifiable.forEach {
        if (it is Parent) {
            it.walk(f)
        }
        f(it)
    }
}

public val Node.boundsInScreen: Bounds
    get() = localToScreen(boundsInLocal)


private class SimpleListCell<T>(private val mapper: (item: T?, empty: Boolean, cell: ListCell<T>) -> Unit) : ListCell<T>() {
    override fun updateItem(item: T, empty: Boolean) {
        super.updateItem(item, empty)
        mapper(item, empty, this)
    }
}

public fun <T> ListView<T>.setItemCellFactory(mapper: (item: T?, empty: Boolean, cell: ListCell<T>) -> Unit) {
    setCellFactory { SimpleListCell(mapper) }
}

public fun checkedRunLater(action: () -> Unit) {
    if (Platform.isFxApplicationThread()) {
        action()
    } else {
        Platform.runLater(action)
    }
}
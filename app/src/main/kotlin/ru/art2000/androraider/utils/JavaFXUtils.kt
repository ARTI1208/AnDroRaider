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

fun <T> Property<T>.bindOnFXThread(observable: ObservableValue<T>) {

    fun set(v: T) {

        if (Platform.isFxApplicationThread()) {
            value = v
        } else {
            Platform.runLater {
                value = v
            }
        }
    }

    observable.addListener { _, _, newValue ->
        set(newValue)
    }
    set(observable.value)
}

fun <T, E> Property<T>.bindOnFXThread(observable: ObservableValue<E>, converter: (e: E) -> T) {

    fun set(v: E) {

        if (Platform.isFxApplicationThread()) {
            value = converter(v)
        } else {
            Platform.runLater {
                value = converter(v)
            }
        }
    }

    observable.addListener { _, _, newValue ->
        set(newValue)
    }
    set(observable.value)
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

public enum class Visibility(val visible: Boolean, val managed: Boolean) {
    VISIBLE(true, true),
    INVISIBLE(false, true),
    UNMANAGED(true, false),
    GONE(false, false);
}

public var Node.visibility : Visibility
    get() {
        return Visibility.values().first {
            it.visible == isVisible && it.managed == isManaged
        }
    }
    set(value) {
        isVisible = value.visible
        isManaged = value.managed
    }
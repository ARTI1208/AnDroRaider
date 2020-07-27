package ru.art2000.androraider.model.editor.file

import javafx.beans.property.ReadOnlyBooleanWrapper
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.property.ReadOnlyStringWrapper
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.value.ObservableValue
import javafx.scene.Node
import javafx.scene.image.Image
import ru.art2000.androraider.model.editor.StatusBarElement
import java.util.function.Consumer

data class CaretPosition(val line: Int, val column: Int, override val action: Consumer<Node>? = null): StatusBarElement {
    override val icon: ObservableValue<Image?> = ReadOnlyObjectWrapper(null)

    override val displayedValue = ReadOnlyStringWrapper("${line + 1}:${column + 1}")

    override val description = "Caret position"

    override val active = SimpleBooleanProperty(true)

}
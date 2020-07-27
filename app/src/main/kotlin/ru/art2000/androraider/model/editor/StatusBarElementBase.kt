package ru.art2000.androraider.model.editor

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.scene.Node
import javafx.scene.image.Image
import java.util.function.Consumer

class StatusBarElementBase<T>(val element: T) : StatusBarElement {

    override val icon: ObservableValue<Image?> = SimpleObjectProperty(null)

    override val displayedValue = SimpleStringProperty(element.toString())

    override val description = element.toString()

    override val action: Consumer<Node>? = null

    override val active = SimpleBooleanProperty(true)

    override fun toString(): String {
        return element.toString()
    }

}
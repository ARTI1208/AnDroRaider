package ru.art2000.androraider.model.editor

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Node
import javafx.scene.image.Image
import org.reactfx.collection.LiveList
import ru.art2000.androraider.utils.bind
import java.util.function.Consumer

class ErrorIndicator(errorList: LiveList<*>) : StatusBarElement {

    override val icon = SimpleObjectProperty<Image?>(null)

    override val displayedValue = SimpleStringProperty("0")

    override val description: String = "Count of errors found by analyzer"

    override val action: Consumer<Node>? = null

    override val active = SimpleBooleanProperty(true)

    init {
        displayedValue.bind(errorList.sizeProperty()) {
            it.toString()
        }
    }

}
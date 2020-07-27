package ru.art2000.androraider.model.editor.file

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableStringValue
import javafx.beans.value.ObservableValue
import javafx.scene.Node
import javafx.scene.image.Image
import org.reactfx.value.Var
import ru.art2000.androraider.model.editor.StatusBarElement
import java.util.function.Consumer

public class IndentConfiguration(public val indent: IndentType, val count: Int): StatusBarElement {

    public enum class IndentType(val singleEl: String) {
        SPACE(" "),
        TAB("\t");
    }

    override val icon: ObservableValue<Image?> = SimpleObjectProperty(null)

    override val action: Consumer<Node>? = null

    override val active = SimpleBooleanProperty(true)

    override val displayedValue: ObservableStringValue

    init {
        val v = if (count == 1)
            "$count ${indent.name.toLowerCase()}"
        else
            "$count ${indent.name.toLowerCase()}s"

        displayedValue = SimpleStringProperty(v)
    }

    override val description = "Indent: ${displayedValue.value}"

    override fun toString(): String {
        return indent.singleEl.repeat(count)
    }

}
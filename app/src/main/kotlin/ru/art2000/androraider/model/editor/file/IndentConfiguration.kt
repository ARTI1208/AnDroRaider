package ru.art2000.androraider.model.editor.file

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

    override val icon: Image? = null

    override val action: Consumer<Node>? = null

    override val active = Var.newSimpleVar(true)

    override val displayedValue = if (count == 1)
        "$count ${indent.name.toLowerCase()}"
    else
        "$count ${indent.name.toLowerCase()}s"

    override val description = "Indent: $displayedValue"

    override fun toString(): String {
        return indent.singleEl.repeat(count)
    }

}
package ru.art2000.androraider.model.editor.file

import javafx.scene.Node
import javafx.scene.image.Image
import org.reactfx.value.Var
import ru.art2000.androraider.model.editor.StatusBarElement
import java.util.function.Consumer

data class CaretPosition(val line: Int, val column: Int, override val action: Consumer<Node>? = null): StatusBarElement {
    override val icon: Image? = null

    override val displayedValue = "${line + 1}:${column + 1}"

    override val description = "Caret position"

    override val active = Var.newSimpleVar(true)

}
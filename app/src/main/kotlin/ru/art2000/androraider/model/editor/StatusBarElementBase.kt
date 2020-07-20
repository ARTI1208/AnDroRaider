package ru.art2000.androraider.model.editor

import javafx.scene.Node
import javafx.scene.image.Image
import org.reactfx.value.Var
import java.util.function.Consumer

class StatusBarElementBase<T>(val element: T) : StatusBarElement {

    override val icon: Image? = null

    override val displayedValue = element.toString()

    override val description = displayedValue

    override val action: Consumer<Node>? = null

    override val active = Var.newSimpleVar(true)

    override fun toString(): String {
        return element.toString()
    }

}
package ru.art2000.androraider.model.editor

import javafx.scene.Node
import javafx.scene.image.Image
import org.reactfx.value.Var
import java.util.function.Consumer

interface StatusBarElement {

    val icon: Image?

    // Shown directly in status bar
    val displayedValue: String

    // Shown on hover
    val description: String

    // on click
    val action: Consumer<Node>?

    val active: Var<Boolean>
}
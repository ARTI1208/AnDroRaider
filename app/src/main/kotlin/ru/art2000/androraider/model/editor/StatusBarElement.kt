package ru.art2000.androraider.model.editor

import javafx.beans.property.BooleanProperty
import javafx.beans.value.ObservableBooleanValue
import javafx.beans.value.ObservableStringValue
import javafx.beans.value.ObservableValue
import javafx.scene.Node
import javafx.scene.image.Image
import java.util.function.Consumer

interface StatusBarElement {

    val icon: ObservableValue<Image?>

    // Shown directly in status bar
    val displayedValue: ObservableStringValue

    // Shown on hover
    val description: String

    // on click
    val action: Consumer<Node>?

    val active: BooleanProperty
}
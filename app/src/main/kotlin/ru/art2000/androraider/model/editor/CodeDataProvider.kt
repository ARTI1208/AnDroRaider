package ru.art2000.androraider.model.editor

import javafx.beans.property.Property
import javafx.beans.value.ObservableValue

interface CodeDataProvider {

    val textProperty: Property<String>

    var text: String

    val langProperty: ObservableValue<String>

    val lang: String

    val offset: Int

    fun dispose()
}
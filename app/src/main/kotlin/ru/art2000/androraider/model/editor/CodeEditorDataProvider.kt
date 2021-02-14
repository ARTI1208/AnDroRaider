package ru.art2000.androraider.model.editor

import javafx.beans.property.BooleanProperty
import javafx.beans.property.Property
import javafx.beans.value.ObservableValue
import ru.art2000.androraider.model.editor.file.LineSeparator

interface CodeEditorDataProvider {

    val currentLineProperty: ObservableValue<Int>

    val currentLine: Int

    val currentColumnProperty: ObservableValue<Int>

    val currentColumn: Int

    val lineSeparatorProperty: Property<LineSeparator>

    val editableProperty: BooleanProperty

    fun openGoToLineDialog()
}
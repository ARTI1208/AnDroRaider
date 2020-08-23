package ru.art2000.androraider.model.editor

import javafx.beans.value.ObservableValue
import ru.art2000.androraider.model.editor.file.IndentConfiguration

interface CodeEditingSettings {

    val indentConfigurationProperty: ObservableValue<IndentConfiguration>

    val indentConfiguration: IndentConfiguration



//    public val isEditableProperty: Var<Boolean> = Var.newSimpleVar(true)
//
//    public val isEditable: Boolean
}
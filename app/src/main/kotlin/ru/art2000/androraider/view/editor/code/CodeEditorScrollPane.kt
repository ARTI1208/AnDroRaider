package ru.art2000.androraider.view.editor.code

import org.fxmisc.flowless.VirtualizedScrollPane

class CodeEditorScrollPane(private val area: CodeEditorArea): VirtualizedScrollPane<CodeEditorArea>(area) {

    init {
        focusedProperty().addListener { _, _, newValue ->
            if (newValue) {
                area.requestFocus()
            }
        }
    }
}
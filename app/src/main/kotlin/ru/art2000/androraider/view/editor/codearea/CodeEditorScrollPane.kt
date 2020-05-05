package ru.art2000.androraider.view.editor.codearea

import org.fxmisc.flowless.VirtualizedScrollPane
import ru.art2000.androraider.view.editor.codearea.CodeEditorArea

class CodeEditorScrollPane(private val area: CodeEditorArea): VirtualizedScrollPane<CodeEditorArea>(area) {

    init {
        focusedProperty().addListener { _, _, newValue ->
            if (newValue) {
                area.requestFocus()
            }
        }
    }
}
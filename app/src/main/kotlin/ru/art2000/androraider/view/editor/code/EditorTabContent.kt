package ru.art2000.androraider.view.editor.code

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import ru.art2000.androraider.model.analyzer.result.NavigableSegment
import ru.art2000.androraider.model.editor.file.FileEditData
import ru.art2000.androraider.view.editor.search.SearchableNodeWrapper
import java.io.File

class EditorTabContent(val data: FileEditData, val openFile: (File, Int) -> Unit): VBox() {

    val codeEditorArea = CodeEditorArea(data)

    init {
        background = Background(BackgroundFill(Color.GREEN, null, null))

        focusedProperty().addListener { _, _, newValue ->
            if (newValue)
                codeEditorArea.requestFocus()
        }

        codeEditorArea.apply {
            keyListeners[KeyCodeCombination(KeyCode.B, KeyCombination.SHORTCUT_DOWN)] = {
                if (it.navigateDetails.size == 1) {
                    it.navigateDetails.first().apply {
                        openFile(this.file, this.offset)
                    }
                }
            }
            edit(data.file, Runnable {
                moveToAndPlaceLineInCenter(caretPosition)
            })
        }

        val areaScrollPane = CodeEditorScrollPane(codeEditorArea)

        children += SearchableNodeWrapper(areaScrollPane, codeEditorArea).also { wrapper ->
            wrapper.prefHeightProperty().bind(heightProperty())
        }
    }

}
package ru.art2000.androraider.view.editor

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import ru.art2000.androraider.model.analyzer.result.NavigableRange
import ru.art2000.androraider.model.editor.file.FileEditData
import ru.art2000.androraider.view.editor.codearea.CodeEditorArea
import ru.art2000.androraider.view.editor.codearea.CodeEditorScrollPane
import tornadofx.minus
import java.io.File

class EditorTabContent(val data: FileEditData, val openFile: (File, Int) -> Unit): VBox() {

    val codeEditorArea = CodeEditorArea(data)

    private val searchBox: EditorTabSearchPanel

    init {
        background = Background(BackgroundFill(Color.GREEN, null, null))

        searchBox = EditorTabSearchPanel(codeEditorArea, data.searchData)

        focusedProperty().addListener { _, _, newValue ->
            if (newValue)
                codeEditorArea.requestFocus()
        }

        codeEditorArea.apply {
            keyListeners[KeyCodeCombination(KeyCode.B, KeyCombination.SHORTCUT_DOWN)] = {
                if (it is NavigableRange) {
                    if (it.navigateDetails.size == 1) {
                        it.navigateDetails.first().apply {
                            openFile(this.file, this.offset)
                        }
                    }
                }
            }
            addEventHandler(KeyEvent.KEY_PRESSED) {
                if (it.isShortcutDown && it.code == KeyCode.F) {
                    searchBox.show()
                }
            }
            edit(data.file, Runnable {
                moveToAndPlaceLineInCenter(caretPosition)
            })
        }

        val areaScrollPane = CodeEditorScrollPane(codeEditorArea)

        searchBox.hide()

        children += searchBox
        children += areaScrollPane

        areaScrollPane.prefHeightProperty().bind(heightProperty().minus(searchBox.managedHeightProperty))
    }

}
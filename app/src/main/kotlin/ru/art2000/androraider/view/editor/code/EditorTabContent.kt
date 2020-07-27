package ru.art2000.androraider.view.editor.code

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import ru.art2000.androraider.model.editor.file.FileEditData
import ru.art2000.androraider.view.editor.search.SearchableNodeWrapper
import java.io.File

@Suppress("NON_EXHAUSTIVE_WHEN")
class EditorTabContent(val data: FileEditData, val openFile: (File, Int) -> Boolean, caretPosition: Int = 0): VBox() {

    val codeEditorArea = CodeEditorArea(data)

    init {

        println("New tab cont")

        background = Background(BackgroundFill(Color.GREEN, null, null))

        focusedProperty().addListener { _, _, newValue ->
            if (newValue)
                codeEditorArea.requestFocus()
        }

        codeEditorArea.apply {
            keyListeners[KeyCodeCombination(KeyCode.B, KeyCombination.SHORTCUT_DOWN)] = {
                println("details count: ${it.fileLinkDetails.size}")
                if (it.fileLinkDetails.isNotEmpty()) {

                    if (it.fileLinkDetails.size == 1) {
                        val link = it.fileLinkDetails.first()
                        openFile(link.file, link.offset)
                    } else {
                        val linkSelectPopup = LinkSelectionPopup(it.fileLinkDetails) { link ->
                            openFile(link.file, link.offset)
                        }

                        val areaSegmentStart = toAreaPosition(it.segmentRange.first)
                        val optBounds = getCharacterBoundsOnScreen(areaSegmentStart, areaSegmentStart + 1)
                        if (optBounds.isPresent) {
                            val bounds = optBounds.get()
                            linkSelectPopup.anchorX = bounds.minX
                            linkSelectPopup.anchorY = bounds.maxY
                        }

                        linkSelectPopup.show(scene.window)
                    }
                }
            }
            edit(data.file, Runnable {
                val adjustedCaretPosition = toAreaPosition(caretPosition)
                println("Caret pos: $caretPosition, adjusted: $adjustedCaretPosition")
                moveToAndPlaceLineInCenter(adjustedCaretPosition)
            })
        }

        val areaScrollPane = CodeEditorScrollPane(codeEditorArea)

        children += SearchableNodeWrapper(areaScrollPane, codeEditorArea).also { wrapper ->
            wrapper.prefHeightProperty().bind(heightProperty())
        }
    }

}
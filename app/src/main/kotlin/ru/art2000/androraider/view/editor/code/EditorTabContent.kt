package ru.art2000.androraider.view.editor.code

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyCombination
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import ru.art2000.androraider.model.analyzer.result.Link
import ru.art2000.androraider.model.editor.CodeDataProvider
import ru.art2000.androraider.model.editor.CodeEditingSettings
import ru.art2000.androraider.model.editor.file.IndentConfiguration
import ru.art2000.androraider.view.editor.search.SearchableNodeWrapper
import ru.art2000.androraider.utils.getValue

class EditorTabContent(dataProvider: CodeDataProvider, openLink: (Link) -> Unit): VBox() {

    companion object {
        private val settings = object : CodeEditingSettings {
            override val indentConfigurationProperty: ObservableValue<IndentConfiguration>
                = SimpleObjectProperty(IndentConfiguration(IndentConfiguration.IndentType.SPACE, 4))

            override val indentConfiguration: IndentConfiguration by indentConfigurationProperty
        }
    }

    val codeEditorArea = CodeEditorArea(dataProvider, settings)

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

                        openLink(link)
                    } else {
                        val linkSelectPopup = LinkSelectionPopup(it.fileLinkDetails, openLink)

                        val areaSegmentStart = it.segmentRange.first
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
        }

        val areaScrollPane = CodeEditorScrollPane(codeEditorArea)

        children += SearchableNodeWrapper(areaScrollPane, codeEditorArea).also { wrapper ->
            wrapper.prefHeightProperty().bind(heightProperty())
        }
    }

    public fun dispose() {
        codeEditorArea.dispose()
    }
}
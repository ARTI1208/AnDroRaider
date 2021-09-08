package ru.art2000.androraider.view.editor.console

import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.control.ScrollPane
import org.fxmisc.flowless.VirtualizedScrollPane
import org.fxmisc.richtext.Caret
import ru.art2000.androraider.model.io.StreamOutput
import ru.art2000.androraider.view.editor.search.SearchableNodeWrapper
import ru.art2000.androraider.view.extensions.SearchableTextArea
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ConsoleView : ScrollPane(), StreamOutput {

    private val textArea = SearchableTextArea().apply { isEditable = false; showCaret = Caret.CaretVisibility.ON }

    init {
        val areaParent = VirtualizedScrollPane(textArea).apply {
            focusedProperty().addListener { _, _, newValue ->
                if (newValue)
                    textArea.requestFocus()
            }
        }

        content = SearchableNodeWrapper(areaParent, textArea)

        textArea.padding = Insets(5.0)
        isFitToHeight = true
        isFitToWidth = true
    }

    private fun writelnImpl(tag: String, string: String) {
        val selection = textArea.selection
        val caretPosition = textArea.caretPosition
        val shouldScrollAfterAppend = textArea.caretPosition == textArea.text.length
        textArea.appendText("$tag: $string\n")
        if (shouldScrollAfterAppend) {
            textArea.scrollYToPixel(Double.MAX_VALUE)
        } else {
            textArea.moveTo(caretPosition)
            if (selection.length > 0)
                textArea.selectRange(selection.start, selection.end)
        }
//        updateSearchIndex(currentSearchValue)

    }

    override fun writeln(tag: String, string: String) {
        if (Platform.isFxApplicationThread()) {
            writelnImpl(tag, string)
        } else {
            Platform.runLater {
                writelnImpl(tag, string)
            }
        }
    }

    override fun startOutput(tag: String, vararg inputStream: InputStream) {
        inputStream.forEach {
            Thread {
                try {
                    BufferedReader(InputStreamReader(it)).use { r ->
                        r.lineSequence().forEach {
                            writeln(tag, it)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }

    override fun stopOutput(vararg inputStream: InputStream) {

    }

    private fun updateSearchIndex(valueToFind: String) {
//        currentSearchValue = valueToFind.toLowerCase()
//        searchSpanList.searchString = currentSearchValue
//        if (currentSearchValue.isNotEmpty()) {
//            val pattern = Pattern.compile("(?<SEARCH>$currentSearchValue)")
//            val searchMatcher = pattern.matcher(textArea.text.toLowerCase())
//            if (pattern.pattern().isNotEmpty()) {
//                while (searchMatcher.find()) {
//                    searchSpanList.add(IntRange(searchMatcher.start(), searchMatcher.end()))
//                    textArea.setStyle(searchMatcher.start(), searchMatcher.end(), listOf("search"))
//                }
//            }
//        }
    }
}
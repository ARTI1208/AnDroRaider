package ru.art2000.androraider.view.editor

import io.reactivex.Single
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.application.Platform
import javafx.beans.property.StringProperty
import javafx.beans.property.StringPropertyBase
import javafx.geometry.Insets
import javafx.scene.control.ScrollPane
import org.fxmisc.flowless.VirtualizedScrollPane
import org.fxmisc.richtext.Caret
import org.fxmisc.richtext.StyleClassedTextArea
import org.fxmisc.richtext.model.ReadOnlyStyledDocument
import ru.art2000.androraider.model.editor.SearchSpanList
import ru.art2000.androraider.model.io.StreamOutput
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.regex.Pattern

class ConsoleView : ScrollPane(), StreamOutput, Searchable<String> {

    private val textArea = StyleClassedTextArea().apply { isEditable = false; showCaret = Caret.CaretVisibility.ON }
    private val searchSpanList = SearchSpanList()

    override val currentSearchValueProperty: StringProperty = object : StringPropertyBase("") {

        override fun getName() = "currentSearchValue"

        override fun getBean() = this
    }

    init {
        content = VirtualizedScrollPane(textArea).apply {
            focusedProperty().addListener { _, _, newValue ->
                if (newValue)
                    textArea.requestFocus()
            }
        }
        textArea.padding = Insets(5.0)
        isFitToHeight = true
        isFitToWidth = true

        textArea.focusedProperty().addListener { _, _, newValue ->
            if (newValue) {
                requestFocus()
            }
        }
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
        updateSearchIndex(currentSearchValue)

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

    override fun find(valueToFind: String) {
        findAll(valueToFind)
    }

    private fun updateSearchIndex(valueToFind: String) {
        currentSearchValue = valueToFind.toLowerCase()
        searchSpanList.searchString = currentSearchValue
        if (currentSearchValue.isNotEmpty()) {
            val pattern = Pattern.compile("(?<SEARCH>$currentSearchValue)")
            val searchMatcher = pattern.matcher(textArea.text.toLowerCase())
            if (pattern.pattern().isNotEmpty()) {
                while (searchMatcher.find()) {
                    searchSpanList.add(IntRange(searchMatcher.start(), searchMatcher.end()))
                    textArea.setStyle(searchMatcher.start(), searchMatcher.end(), listOf("search"))
                }
            }
        }
    }

    override fun findAll(valueToFind: String) {
        currentSearchValue = valueToFind
        Single.fromCallable {
            var b = textArea.createMultiChange()
            var ch = false
            searchSpanList.searchString = currentSearchValue
            if (currentSearchValue.isNotEmpty()) {
                val pattern = Pattern.compile(Pattern.quote(currentSearchValue.toLowerCase()))
                val searchMatcher = pattern.matcher(textArea.text.toLowerCase())
                if (pattern.pattern().isNotEmpty()) {
                    while (searchMatcher.find()) {
                        searchSpanList.add(IntRange(searchMatcher.start(), searchMatcher.end()))
                        val doc = ReadOnlyStyledDocument.fromString(
                                textArea.getText(searchMatcher.start(), searchMatcher.end()),
                                textArea.getParagraphStyleForInsertionAt(searchMatcher.start() + 1),
                                mutableListOf("search"),
                                textArea.segOps
                        )


                        ch = true
                        b = b.replaceAbsolutely(searchMatcher.start(), searchMatcher.end(), doc)
                    }
                }
            }
            ch to b
        }.subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe { (hasChanges, builder) ->
                    textArea.clearStyle(0, textArea.length)
                    if (hasChanges) {
                        builder.commit()
                    }
                }
    }

    private fun selectSearchRange(new: Int) {
        searchSpanList.currentPosition = new

        if (new in 0..searchSpanList.lastIndex) {
            textArea.clearStyle(searchSpanList[new].first, searchSpanList[new].last)
            textArea.setStyle(searchSpanList[new].first, searchSpanList[new].last, listOf("search"))
            textArea.moveTo(searchSpanList[new].last)
        }
    }

    override fun findNext() {
        if (searchSpanList.isEmpty())
            return

        val newPos = if (searchSpanList.currentPosition < 0) {
            searchSpanList.withIndex().find { it.value.last >= textArea.caretPosition }?.index ?: 0
        } else
            (searchSpanList.currentPosition + 1) % searchSpanList.size

        selectSearchRange(newPos)
    }

    override fun findPrevious() {
        if (searchSpanList.isEmpty())
            return

        val newPos = if (searchSpanList.currentPosition < 0)
            searchSpanList.withIndex().findLast { it.value.last <= textArea.caretPosition }?.index
                    ?: searchSpanList.lastIndex
        else
            (searchSpanList.size + searchSpanList.currentPosition - 1) % searchSpanList.size

        selectSearchRange(newPos)
    }
}
package ru.art2000.androraider.view.extensions

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import org.fxmisc.richtext.StyleClassedTextArea
import org.fxmisc.richtext.model.ReadOnlyStyledDocument
import ru.art2000.androraider.model.editor.SearchSpanList
import ru.art2000.androraider.model.editor.StringSearchable
import java.util.regex.Pattern

class SearchableTextArea : StyleClassedTextArea(), StringSearchable {

    private val searchSpanList = SearchSpanList()

    override val currentSearchValueProperty: StringProperty = SimpleStringProperty("")

    override fun findNext() {
        if (searchSpanList.isEmpty()) return

        val newPos = if (searchSpanList.currentPosition < 0) {
            searchSpanList.withIndex().find { it.value.last >= caretPosition }?.index ?: 0
        } else
            (searchSpanList.currentPosition + 1) % searchSpanList.size

        selectSearchRange(newPos)
    }

    override fun findPrevious() {
        if (searchSpanList.isEmpty()) return

        val newPos = if (searchSpanList.currentPosition < 0)
            searchSpanList.withIndex().findLast { it.value.last <= caretPosition }?.index
                ?: searchSpanList.lastIndex
        else
            (searchSpanList.size + searchSpanList.currentPosition - 1) % searchSpanList.size

        selectSearchRange(newPos)
    }

    init {
        currentSearchValueProperty.addListener { _, _, newValue ->
//            Single.fromCallable {
//                var b = createMultiChange()
//                var ch = false
//                searchSpanList.searchString = newValue
//                if (newValue.isNotEmpty()) {
//                    val pattern = Pattern.compile(Pattern.quote(newValue.toLowerCase()))
//                    val searchMatcher = pattern.matcher(text.toLowerCase())
//                    if (pattern.pattern().isNotEmpty()) {
//                        while (searchMatcher.find()) {
//                            searchSpanList.add(IntRange(searchMatcher.start(), searchMatcher.end()))
//                            val doc = ReadOnlyStyledDocument.fromString(
//                                getText(searchMatcher.start(), searchMatcher.end()),
//                                getParagraphStyleForInsertionAt(searchMatcher.start() + 1),
//                                mutableListOf("search"),
//                                segOps
//                            )
//
//
//                            ch = true
//                            b = b.replaceAbsolutely(searchMatcher.start(), searchMatcher.end(), doc)
//                        }
//                    }
//                }
//                ch to b
//            }.subscribeOn(Schedulers.computation())
//                .observeOn(JavaFxScheduler.platform())
//                .subscribe { (hasChanges, builder) ->
//                    clearStyle(0, length)
//                    if (hasChanges) {
//                        builder.commit()
//                    }
//                }
        }
    }

    private fun selectSearchRange(new: Int) {
        searchSpanList.currentPosition = new

        if (new in 0..searchSpanList.lastIndex) {
            clearStyle(searchSpanList[new].first, searchSpanList[new].last)
            setStyle(searchSpanList[new].first, searchSpanList[new].last, listOf("search"))
            moveTo(searchSpanList[new].last)
        }
    }

}
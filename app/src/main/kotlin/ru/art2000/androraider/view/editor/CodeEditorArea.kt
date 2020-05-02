package ru.art2000.androraider.view.editor

import io.reactivex.Single
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.ObjectPropertyBase
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.ScrollEvent
import javafx.stage.Popup
import org.fxmisc.richtext.Caret
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory
import org.fxmisc.richtext.event.MouseOverTextEvent
import ru.art2000.androraider.model.editor.SearchSpanList
import ru.art2000.androraider.model.editor.getProjectForNode
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.getStyle
import java.io.File
import java.nio.file.Files
import java.time.Duration
import java.util.regex.Pattern

@Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate")
class CodeEditorArea() : CodeArea(), Searchable<String> {

    constructor(file: File) : this() {
        edit(file)
    }

    val currentEditingFileProperty = object : ObjectPropertyBase<File?>() {
        override fun getName(): String {
            return "currentEditingFile"
        }

        override fun getBean(): Any {
            return this@CodeEditorArea
        }
    }

    var currentEditingFile: File?
        get() = currentEditingFileProperty.value
        set(value) = currentEditingFileProperty.setValue(value)

    override var currentSearchValue: String = ""

    private var currentSearchCursor = -1
        set(value) {
            if (value >= 0 && value < searchSpanList.size) {
                field = value
                showCaret = Caret.CaretVisibility.ON
                displaceCaret(searchSpanList[value].last)
                scrollYToPixel(currentParagraph * 20.0)
            } else {
                field = -1
            }
        }

    private val searchSpanList = SearchSpanList()


    init {
        paragraphGraphicFactory = LineNumberFactory.get(this)

        stylesheets.add(javaClass.getStyle("code.css"))

        multiPlainChanges()
                .successionEnds(Duration.ofMillis(200))
                .subscribe {
                    currentEditingFile?.also { Files.write(it.toPath(), text.toByteArray()) }
                    updateHighlighting()
                }

        val popup = Popup()
        val popupMsg = Label()
        popupMsg.styleClass.add("popup")
        popup.content.add(popupMsg)

        mouseOverTextDelay = Duration.ofSeconds(1)
        addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_BEGIN) { e ->
            val chIdx = e.characterIndex
            val pos = e.screenPosition

            val smaliCopy = getProjectForNode(this)?.fileToClassMapping?.get(currentEditingFile)
            if (smaliCopy != null) {
                val error = smaliCopy.ranges.find {
                    return@find chIdx in it.range
                }

                if (error != null) {
                    popupMsg.text = error.description
                    popup.show(this, pos.x, pos.y + 10)
                    return@addEventHandler
                }
            }
        }
        addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_END) { popup.hide() }
        addEventHandler(ScrollEvent.SCROLL) { popup.hide() }

        addEventHandler(KeyEvent.KEY_PRESSED) {
            if (it.code == KeyCode.TAB && !it.isShortcutDown) {
                // assume tab was already inserted
                replaceText(caretPosition - 1, caretPosition, " ".repeat(4))
                return@addEventHandler
            }

            if (it.isShortcutDown && (it.code == KeyCode.SLASH || it.code == KeyCode.PERIOD)) {
                val refactorer = TypeDetector.getRefactoringRule(currentEditingFile?.extension)

                val isSingleLine = selection.start == selection.end
                val from = getLineStartIndex(selection.start)
                val to = if (isSingleLine) getLineEndIndex(selection.end) else getLineEndIndex(selection.end, true)

                val selectedText = text.substring(from, to)

                var caretPosition = caretPosition
                val newText = refactorer
                        .commentUncommentFragment(selectedText)
                        .joinToString("\n") { commentingResult ->
                            if (isSingleLine || caretPosition > from)
                                caretPosition += commentingResult.insertedBefore + commentingResult.insertedAfter

                            commentingResult.line
                        }

                replaceText(from, to, newText)
                moveTo(caretPosition)
            }
        }
    }

    fun getLineStartIndex(index: Int): Int {
        for (currentIndex in index downTo 1) {
            if (text[currentIndex - 1] == '\n')
                return currentIndex
        }

        return 0
    }

    fun getLineEndIndex(index: Int, checkPrevious: Boolean = false): Int {
        if (checkPrevious && index > 0 && text[index - 1] == '\n')
            return index - 1

        for (currentIndex in index until text.length) {
            if (text[currentIndex] == '\n')
                return currentIndex
        }

        return text.length
    }

    public fun edit(file: File?, onTextSet: Runnable = Runnable {}) {
        if (file?.isDirectory == true || file == currentEditingFile)
            return

        currentEditingFile = file

        Single
                .fromCallable {
                    if (file == null || !file.exists()) "" else String(Files.readAllBytes(file.toPath()))
                }.subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .doOnSuccess {
                    replaceText(it)
                    updateHighlighting()
                    moveTo(0)
                    scrollYToPixel(0.0)
                    undoManager.forgetHistory()
                    onTextSet.run()
                }.subscribe()
    }

    public override fun find(valueToFind: String) {
        findAll(valueToFind)
    }

    public override fun findAll(valueToFind: String) {
        currentSearchValue = valueToFind
        updateHighlighting()
        currentSearchCursor = 0
    }

    public override fun findNext() {
        if (searchSpanList.size == 0 || currentSearchValue.isEmpty()) {
            currentSearchCursor = -1
            return
        }

        currentSearchCursor = (currentSearchCursor + 1) % searchSpanList.size
        displaceCaret(searchSpanList[currentSearchCursor].last)
    }

    public override fun findPrevious() {
        val size = searchSpanList.size
        if (size == 0 || currentSearchValue.isEmpty()) {
            currentSearchCursor = -1
            searchSpanList.searchString
            return
        }

        currentSearchCursor = (size + currentSearchCursor - 1) % size
        displaceCaret(searchSpanList[currentSearchCursor].last)
    }

    private fun updateHighlighting() {
        val file = currentEditingFile ?: return

        Single
                .fromCallable {
                    getProjectForNode(this)?.analyzeFile(file)
                }.subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .doOnSuccess { result ->
                    if (result == null)
                        return@doOnSuccess

                    clearStyle(0, text.length)

                    result.rangeStatuses.forEach { status ->
                        try {
                            setStyle(status.range.first, status.range.last + 1, status.style)
                        } catch (e: Exception) {
                            println("Error: ${status.style}")
                            e.printStackTrace()
                        }
                    }

                    searchSpanList.searchString = currentSearchValue
                    if (currentSearchValue.isNotEmpty()) {
                        val pattern = Pattern.compile("(?<SEARCH>$currentSearchValue)")
                        val searchMatcher = pattern.matcher(text)
                        if (pattern.pattern().isNotEmpty()) {
                            while (searchMatcher.find()) {
                                searchSpanList.add(IntRange(searchMatcher.start(), searchMatcher.end()))
                                setStyle(searchMatcher.start(), searchMatcher.end(), listOf("search"))
                            }
                        }
                    }
                }
                .subscribe()
    }
}
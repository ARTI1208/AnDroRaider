package ru.art2000.androraider.view.editor.codearea

import io.reactivex.Single
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.ObjectPropertyBase
import javafx.scene.control.Control
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyEvent
import javafx.scene.input.ScrollEvent
import javafx.stage.Popup
import org.fxmisc.richtext.Caret
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.event.MouseOverTextEvent
import org.fxmisc.richtext.model.TwoDimensional
import ru.art2000.androraider.model.analyzer.result.NavigableRange
import ru.art2000.androraider.model.analyzer.result.RangeAnalyzeStatus
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.editor.SearchSpanList
import ru.art2000.androraider.model.editor.getProjectForNode
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.getStyle
import java.io.File
import java.nio.file.Files
import java.time.Duration
import java.util.regex.Pattern
import ru.art2000.androraider.model.io.println
import ru.art2000.androraider.utils.toKeyCodeCombination
import ru.art2000.androraider.view.editor.Searchable
import kotlin.math.max

@Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate")
class CodeEditorArea() : CodeArea(), Searchable<String> {

    constructor(file: File, runnable: Runnable = Runnable{}) : this() {
        edit(file, runnable)
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

    override val currentSearchValueProperty: ObjectPropertyBase<String> = object : ObjectPropertyBase<String>("") {
        override fun getName(): String {
            return "currentSearchValue"
        }

        override fun getBean(): Any {
            return this
        }

    }

    val keyListeners = mutableMapOf<KeyCodeCombination, (RangeAnalyzeStatus) -> Unit>()

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
    private var isSearching = false

    init {
//        paragraphGraphicFactory = LineNumberFactory.get(this)
        paragraphGraphicFactory = CodeEditorLineNumber(this)

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
            isSearching = false
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

        addEventHandler(KeyEvent.KEY_PRESSED) { event ->
            isSearching = false
            println(event.toKeyCodeCombination())
            keyListeners[event.toKeyCodeCombination()]?.also { listener ->
                println("CombFound")
                val prj = getProjectForNode(this)
                prj?.fileToClassMapping?.get(currentEditingFile)?.also { clazz ->
                    val pos = caretPosition
                    println("CurClassFound")
                    clazz.ranges.find { pos in it.range }?.apply {
                        println("RangeFound: $description")
                        listener.invoke(this)
                    }
                }
            }


            if (event.code == KeyCode.TAB && !event.isShortcutDown) {
                // assume tab was already inserted
                replaceText(caretPosition - 1, caretPosition, " ".repeat(4))
                return@addEventHandler
            }

            if (event.isShortcutDown && event.code == KeyCode.B) {
                val prj = getProjectForNode(this)
                val clazz = prj?.fileToClassMapping?.get(currentEditingFile)
                if (clazz != null) {
                    val pos = caretPosition
                    val navigable = (clazz.ranges.find { pos in it.range && it is NavigableRange } as? NavigableRange)
                    if (navigable != null) {
                        //open
                        println("Open ${navigable.file} at ${navigable.offset}")
                    }
                }
            }

            if (event.isShortcutDown && (event.code == KeyCode.SLASH || event.code == KeyCode.PERIOD)) {
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
        styleClass.add("code-editor-area")
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
                    moveToAndPlaceLineInCenter(0)
                    undoManager.forgetHistory()
                    onTextSet.run()
                }.subscribe()
    }

    public override fun find(valueToFind: String) {
        findAll(valueToFind)
    }

    public override fun findAll(valueToFind: String) {
        isSearching = true
        currentSearchValue = valueToFind
        updateHighlighting()
        currentSearchCursor = 0
    }

    private fun selectSearchRange(previous: Int, new: Int) {
        if (!isSearching)
            return

        searchSpanList.currentPosition = new

//        if (previous in 0..searchSpanList.lastIndex) {
//            clearStyle(searchSpanList[previous].first, searchSpanList[previous].last)
//            setStyle(searchSpanList[previous].first, searchSpanList[previous].last, listOf("search"))
//        }

        if (new in 0..searchSpanList.lastIndex) {
//            clearStyle(searchSpanList[new].first, searchSpanList[new].last)
//            setStyle(searchSpanList[new].first, searchSpanList[new].last, listOf("search-current"))

            if (previous in 0..searchSpanList.lastIndex)
                moveToAndPlaceLineInCenter(searchSpanList[new].last, offsetToPosition(searchSpanList[previous].last, TwoDimensional.Bias.Backward).major)
            else
                moveToAndPlaceLineInCenter(searchSpanList[new].last)

            selectRange(searchSpanList[new].first, searchSpanList[new].last)
        }
    }

    public fun moveToAndPlaceLineInCenter(newPosition: Int, prevLine: Int? = null) {
        val previousPosition = caretPosition
        val previousLine = prevLine ?: currentParagraph
        println(this, "Position", newPosition.toString() + "|vs|" + previousPosition)
        moveTo(newPosition) // move to gather new line index
        val newLine = offsetToPosition(newPosition, TwoDimensional.Bias.Backward).major
//        moveTo(previousPosition) // move back to prevent bug with wrong scroll

        val lineHeight = (getParagraphGraphic(previousLine) as Control).also { println(this, "gr", it) }.height
        val visibleLinesCount = max((height / lineHeight).toInt(), visibleParagraphs.size)
        // so that our newLine will be in the center of screen (if it position allows)
        val lineToScroll = (newLine - visibleLinesCount / 2).let { if (it < 0) 0 else it }
        scrollYToPixel((lineHeight * lineToScroll))

        println(this, "Scroll", "$newLine|$lineHeight|$visibleLinesCount|${visibleParagraphs.size}")

//        moveTo(newPosition) // move caret to new position
        scrollYToPixel((lineHeight * lineToScroll))
    }

    public override fun findNext() {
        if (currentSearchValue.isEmpty() || searchSpanList.isEmpty()) {
            return
        }
        isSearching = true

        val newPos = if (searchSpanList.currentPosition < 0) {
            searchSpanList.withIndex().find { it.value.last >= caretPosition }?.index ?: 0
        } else
            (searchSpanList.currentPosition + 1) % searchSpanList.size

        selectSearchRange(searchSpanList.currentPosition, newPos)
    }

    public override fun findPrevious() {
        if (currentSearchValue.isEmpty()) {
            return
        }
        isSearching = true

        val newPos = if (searchSpanList.currentPosition < 0)
            searchSpanList.withIndex().findLast { it.value.last <= caretPosition }?.index ?: searchSpanList.lastIndex
        else
            (searchSpanList.size + searchSpanList.currentPosition - 1) % searchSpanList.size

        selectSearchRange(searchSpanList.currentPosition, newPos)
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

                    println("Methods")
                    if (result is SmaliClass) {
                        result.methods.forEach { println(it) }
                    }

                    result.rangeStatuses.forEach { status ->
//                        println("${status.range}:${status.style}:${status.description}")
                        try {
                            setStyle(status.range.first, status.range.last, status.style)
                        } catch (e: Exception) {
                            println("Error: ${status.style}")
                            e.printStackTrace()
                        }
                    }

                    searchSpanList.searchString = currentSearchValue
                    if (currentSearchValue.isNotEmpty()) {
                        var currentFound = false
                        val pattern = Pattern.compile("(?<SEARCH>$currentSearchValue)")
                        val searchMatcher = pattern.matcher(text)
                        if (pattern.pattern().isNotEmpty()) {
                            while (searchMatcher.find()) {
                                searchSpanList.add(IntRange(searchMatcher.start(), searchMatcher.end()))
                                if (!currentFound && searchMatcher.end() >= caretPosition) {
                                    currentFound = true
                                    selectSearchRange(-1, searchSpanList.size - 1)
                                }
//                                else {
                                setStyle(searchMatcher.start(), searchMatcher.end(), listOf("search"))
//                                }
                            }
                            if (!currentFound) {
                                selectSearchRange(-1, 0)
                            }
                        }
                    }
                }
                .subscribe()
    }
}
package ru.art2000.androraider.view.editor.code

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.*
import javafx.beans.value.ObservableValue
import javafx.scene.Cursor
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.input.*
import javafx.stage.Popup
import org.fxmisc.richtext.Caret
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.MultiChangeBuilder
import org.fxmisc.richtext.event.MouseOverTextEvent
import org.fxmisc.richtext.model.*
import org.reactfx.Subscription
import ru.art2000.androraider.model.analyzer.result.DescriptiveSegment
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.HighlightableSegment
import ru.art2000.androraider.model.analyzer.result.LinkSegment
import ru.art2000.androraider.model.analyzer.result.StyledSegment
import ru.art2000.androraider.model.editor.CodeAreaContext
import ru.art2000.androraider.model.editor.SearchSpanList
import ru.art2000.androraider.model.editor.file.CaretPosition
import ru.art2000.androraider.model.editor.file.FileEditData
import ru.art2000.androraider.model.editor.file.LineSeparator
import ru.art2000.androraider.model.editor.getProjectForNode
import ru.art2000.androraider.utils.*
import ru.art2000.androraider.model.editor.StringSearchable
import java.io.File
import java.nio.file.Files
import java.time.Duration
import java.util.concurrent.atomic.AtomicBoolean
import java.util.function.Consumer
import java.util.regex.Pattern
import tornadofx.getValue
import tornadofx.setValue
import java.util.*

@Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate")
class CodeEditorArea(
        val data: FileEditData
) : CodeArea(), StringSearchable {

    val contextProperty = SimpleObjectProperty<CodeAreaContext?>()
    var context by contextProperty

    val currentEditingFileProperty = SimpleObjectProperty<File?>()
    var currentEditingFile by currentEditingFileProperty

    override val currentSearchValueProperty: StringProperty = SimpleStringProperty("")

    val keyListeners = mutableMapOf<KeyCodeCombination, (LinkSegment) -> Unit>()

    private var isCtrlDown = false

    private var pointedLink: LinkSegment? = null

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

    private var fileSpecificSubscriptions = mutableListOf<Subscription>()

    private var highlightedNavigableRange: MutableList<HighlightableSegment> = mutableListOf()

    private val caretPositionListener: (ObservableValue<out Int>, Int, Int) -> Unit

    init {
        paragraphGraphicFactory = CodeEditorLineNumber(this)

        stylesheets.add(javaClass.getStyle("code.css"))

        multiPlainChanges()
                .successionEnds(Duration.ofMillis(200))
                .subscribe {
                    updateHighlighting()
                }

        currentSearchValueProperty.addListener { _, _, _ ->
            isSearching = true
            highlightSearch()
            currentSearchCursor = 0
        }

        val popup = Popup()
        val popupMsg = Label()
        popupMsg.styleClass.add("editor-popup")
        popup.content.add(popupMsg)

        mouseOverTextDelay = Duration.ofSeconds(1)

//        addEventHandler(MouseEvent.MOUSE_PRESSED) {
//            if (it.isPrimaryButtonDown) {
//                pointedLink?.also {
//
//                }
//            }
//        }

        addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_BEGIN) { e ->
            isSearching = false

            val file = currentEditingFile ?: return@addEventHandler

            val chIdx = toFilePosition(e.characterIndex)
            val pos = e.screenPosition

            val fileAnalyzeResult = getProjectForNode(this)?.getAnalyzeResult(file)
            if (fileAnalyzeResult != null) {
                if (false) {
//                if (isCtrlDown) {
                    val hoveredAnalyzeStatus = fileAnalyzeResult.textSegments.find { status ->
                        chIdx in status.segmentRange && status is LinkSegment
                    } as LinkSegment?

                    if (pointedLink != hoveredAnalyzeStatus) {
                        removePointedLinkHighlight()
                    }

                    if (hoveredAnalyzeStatus != null) {

                        cursor = Cursor.HAND

                        var b = createMultiChange()
                        b = addStyle(b, toAreaRange(hoveredAnalyzeStatus.segmentRange), "shortcut-highlight")
                        b.commit()

                        pointedLink = hoveredAnalyzeStatus
                        return@addEventHandler
                    }
                } else {
                    val hoveredAnalyzeStatus = fileAnalyzeResult.textSegments.find { status ->
                        chIdx in status.segmentRange && status is DescriptiveSegment
                    } as DescriptiveSegment?

                    if (hoveredAnalyzeStatus != null && hoveredAnalyzeStatus.description.isNotEmpty()) {
                        popupMsg.text = hoveredAnalyzeStatus.description
                        popup.show(this, pos.x, pos.y + 10)
                        return@addEventHandler
                    }
                }
            }
        }
        addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_END) { popup.hide() }
        addEventHandler(ScrollEvent.SCROLL) { popup.hide() }

        addEventHandler(KeyEvent.KEY_RELEASED) {
            isCtrlDown = it.isShortcutDown
            if (!isCtrlDown) {
                removePointedLinkHighlight()
            }
        }

        addEventHandler(KeyEvent.KEY_PRESSED) { event ->

            if (event.code == KeyCode.SHORTCUT || event.isShortcutDown)
                isCtrlDown = true

            isSearching = false
            keyListeners[event.toKeyCodeCombination()]?.also { listener ->
                println("Found listener")
                val file = currentEditingFile ?: return@also
                val prj = getProjectForNode(this) ?: return@also
                prj.getAnalyzeResult(file)?.also { result ->
                    println("Found file_analyze_result")
                    val pos = toFilePosition(caretPosition)
                    result.textSegments.find { status -> pos in status.segmentRange && status is LinkSegment }?.apply {
                        println("Found range")
                        listener.invoke(this as LinkSegment)
                    }
                }
            }

            if (event.code == KeyCode.TAB && !event.isShortcutDown) {
                // assume tab was already inserted
                replaceText(caretPosition - 1, caretPosition, data.indentConfiguration.toString())
                return@addEventHandler
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
                return@addEventHandler
            }

            if (event.isShortcutDown && event.code == KeyCode.G) {
                openGoToLineDialog()
                return@addEventHandler
            }
        }
        styleClass.add("code-editor-area")

        val moveCaret = Consumer<Node> {
            openGoToLineDialog()
        }

        data.position = CaretPosition(1, 1, moveCaret)

        caretColumnProperty().addListener { _, _, newValue ->
            data.position = CaretPosition(currentParagraph, newValue, moveCaret)
        }

        currentParagraphProperty().addListener { _, _, newValue ->
            data.position = CaretPosition(newValue, caretColumn, moveCaret)
        }

        editableProperty().bind(data.isEditableProperty)
        showCaretProperty().value = Caret.CaretVisibility.ON

        selectionProperty().addListener { _, _, _ ->
            if (!isSearching && searchSpanList.currentPosition >= 0) {
                searchSpanList.currentPosition = -1
            }
        }

        caretPositionListener = listener@ { _, _, newValue ->
            val newValueFile = toFilePosition(newValue)

            var changeBuilder = createMultiChange()
            var hasChanges = false

            val previousHighlight = highlightedNavigableRange
            if (previousHighlight.isNotEmpty()) {
                val invalid = previousHighlight.filter { newValueFile !in it.segmentRange }
                invalid.forEach {
                    changeBuilder = removeStyle(changeBuilder, toAreaRange(it.segmentRange), it.highlightStyle)
                    hasChanges = true
                }
                previousHighlight.removeAll(invalid)
            }

            val file = currentEditingFile ?: return@listener

            val smaliCopy = getProjectForNode(this)?.getAnalyzeResult(file)
            if (smaliCopy != null) {
                val caretPositionStatus = smaliCopy.textSegments.find { status ->
                    status is HighlightableSegment && newValueFile in status.segmentRange
                } as HighlightableSegment?

                caretPositionStatus?.apply {
                    smaliCopy.textSegments.forEach { segment ->
                        if (segment is HighlightableSegment && highlightOther(segment)) {
                            changeBuilder = addStyle(changeBuilder, toAreaRange(segment.segmentRange), segment.highlightStyle)
                            hasChanges = true
                            previousHighlight.add(segment)
                        }
                    }

                    changeBuilder = addStyle(changeBuilder, toAreaRange(segmentRange), highlightStyle)
                    hasChanges = true

                    previousHighlight.add(this)
                }
            }

            if (hasChanges) {
                changeBuilder.commit()
            }
        }

        currentSearchValueProperty.addListener { _, oldValue, newValue ->
            if (searchSpanList.currentPosition >= 0
                    && (oldValue.startsWith(newValue, true)
                            || newValue.startsWith(oldValue, true))) {
                moveTo(caretPosition + (newValue.length - oldValue.length))
            }
        }
    }

    private fun registerSaveOnEdit() {
        val saveOnChange = multiPlainChanges()
                .successionEnds(Duration.ofMillis(200))
                .subscribe {
                    save()
                }

        val lineSeparatorSubscription = data.lineSeparatorProperty.observeChanges { _, _, _ ->
            save()
        }

        fileSpecificSubscriptions.add(saveOnChange)
        fileSpecificSubscriptions.add(lineSeparatorSubscription)

        caretPositionProperty().addListener(caretPositionListener)
    }

    private fun unregisterSaveOnEdit() {
        fileSpecificSubscriptions.forEach { it.unsubscribe() }
        fileSpecificSubscriptions.clear()

        caretPositionProperty().removeListener(caretPositionListener)
    }

    private fun removePointedLinkHighlight() {
        pointedLink?.also {
            var b = createMultiChange()
            b = removeStyle(b, toAreaRange(it.segmentRange), "shortcut-highlight")
            b.commit()

            pointedLink = null
        }
        cursor = Cursor.TEXT
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

    private fun save() {
        currentEditingFile?.also {
            val writable = data.isEditable
            println("Try save ${it.name}: $writable")
            if (writable) {
                Files.write(it.toPath(),
                        getTextTrueTerminator().toByteArray(data.charset))
            }
        }
    }

    public fun edit(file: File?, onTextSet: Runnable = Runnable {}) {
        if (file?.isDirectory == true || file == currentEditingFile)
            return

        println("File load start")
        currentEditingFile = file

        Single
                .fromCallable {
                    if (file == null || !file.exists()) ("" to LineSeparator.LF) else {
                        val bytes = Files.readAllBytes(file.toPath())
                        val str = String(bytes)
                        (str to LineSeparator.fromSeparator(when {
                            str.contains("\r\n") -> "\r\n"
                            str.contains('\r') -> "\r"
                            else -> "\n"
                        }))
                    }
                }.subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .doOnSuccess {
                    unregisterSaveOnEdit()
                    replaceText(it.first)
                    data.lineSeparator = it.second
                    registerSaveOnEdit()
                    moveToAndPlaceLineInCenter(0)
                    undoManager.forgetHistory()

                    println("File load end")
                    onTextSet.run()
                }.subscribe()
    }

    private fun selectSearchRange(previous: Int, new: Int) {
        if (!isSearching)
            return

        searchSpanList.currentPosition = new


        if (new in 0..searchSpanList.lastIndex) {

            if (previous in 0..searchSpanList.lastIndex)
                moveToAndPlaceLineInCenter(searchSpanList[new].last)
            else
                moveToAndPlaceLineInCenter(searchSpanList[new].last)

            selectRange(searchSpanList[new].first, searchSpanList[new].last)
        }
    }

    private fun openGoToLineDialog() {
        val dialog = GoToLineDialog(data.position)

        val res = dialog.showAndWait()
        if (res.isEmpty) {
            return
        }

        moveTo(res.get().first, res.get().second)
        requestFollowCaret()
        requestFocus()
    }

    public fun moveToAndPlaceLineInCenter(newPosition: Int) {
        if (newPosition !in text.indices) {
            println("Error moving to $newPosition: not in range ${text.indices}")
            return
        }

        moveTo(newPosition)
        requestFollowCaret() // TODO scroll so new line is in area center
    }

    private fun findNext(includeCurrent: Boolean) {
        if (currentSearchValue.isEmpty() || searchSpanList.isEmpty()) {
            return
        }
        isSearching = true

        val newPos = searchSpanList.withIndex().find {
            if (includeCurrent)
                caretPosition <= it.value.last
            else
                caretPosition < it.value.last
        }?.index ?: 0

        selectSearchRange(searchSpanList.currentPosition, newPos)
    }

    public override fun findNext() {
        findNext(false)
    }

    public override fun findPrevious() {
        if (currentSearchValue.isEmpty()) {
            return
        }
        isSearching = true

        val newPos = searchSpanList.withIndex().findLast { it.value.last < caretPosition }?.index
                ?: searchSpanList.lastIndex

        selectSearchRange(searchSpanList.currentPosition, newPos)
    }

    private fun <T> computationSingle(f: () -> T): Single<T> {
        return Single.fromCallable {
            f()
        }.subscribeOn(Schedulers.computation())
    }

    private fun highlightSearch() {

        if (isUpdating.getAndSet(true))
            return

        computationSingle {
            var b = createMultiChange()
            var ch = searchSpanList.isNotEmpty()

            searchSpanList.forEach {
                b = removeStyle(b, it.first, it.last, "search")
            }

            searchSpanList.searchString = currentSearchValue
            if (currentSearchValue.isNotEmpty()) {
                val pattern = Pattern.compile(Pattern.quote(currentSearchValue.toLowerCase()))
                val searchMatcher = pattern.matcher(text.toLowerCase())
                if (pattern.pattern().isNotEmpty()) {
                    while (searchMatcher.find()) {
                        val start = searchMatcher.start()
                        val end = searchMatcher.end()

                        searchSpanList.add(IntRange(start, end))

                        ch = true
                        b = addStyle(b, start, end, "search")
                    }
                }
            }
            ch to b
        }.observeOn(JavaFxScheduler.platform())
                .subscribe { (hasChanges, builder) ->
                    if (hasChanges)
                        builder.commit()

                    isUpdating.set(false)
                    findNext(true)
                }

    }

    private val isUpdating = AtomicBoolean()

    public fun updateHighlighting() {
        val file = currentEditingFile ?: return

        if (isUpdating.getAndSet(true)) {
            return
        }

        val maybe = if (getProjectForNode(this)?.canAnalyzeFile(file) == true) {
            Maybe.fromCallable {
                getProjectForNode(this)?.analyzeFile(file)
            }.subscribeOn(Schedulers.io())
        } else
            Maybe.empty<FileIndexingResult>()

        maybe.observeOn(JavaFxScheduler.platform())
                .doOnSuccess { result ->
                    if (result == null)
                        return@doOnSuccess

                    computationSingle {
                        var b = createMultiChange()
                        var ch = false

                        println("Total segments: ${result.textSegments.size}")
                        var k = 0
                        result.textSegments.forEach { status ->
                            if (status is StyledSegment && status.style.isNotEmpty()) {
                                ch = true
                                ++k
                                val (from, to) = toAreaRange(status.segmentRange)
                                b = setStyle(b, from, to, status.style)
                            }
                        }
                        println("Styled segments: $k")

                        ch to b
                    }.observeOn(JavaFxScheduler.platform())
                            .subscribe { (hasChanges, builder) ->
                                clearStyle(0, length)
                                if (hasChanges)
                                    builder.commit()

                                isUpdating.set(false)
                                highlightSearch()
                            }
                }
                .subscribe()
    }

    private fun setStyle(
            changeBuilder: MultiChangeBuilder<Collection<String>, String, Collection<String>>,
            from: Int, to: Int,
            style: String
    ): MultiChangeBuilder<Collection<String>, String, Collection<String>> {
        return changeStyle(changeBuilder, from, to) { listOf(style) }
    }

    private fun setStyle(
            changeBuilder: MultiChangeBuilder<Collection<String>, String, Collection<String>>,
            from: Int, to: Int,
            style: Collection<String>
    ): MultiChangeBuilder<Collection<String>, String, Collection<String>> {
        return changeStyle(changeBuilder, from, to) { style }
    }

    private fun addStyle(
            changeBuilder: MultiChangeBuilder<Collection<String>, String, Collection<String>>,
            from: Int, to: Int,
            style: String
    ): MultiChangeBuilder<Collection<String>, String, Collection<String>> {
        return changeStyle(changeBuilder, from, to) {
            if (it.contains(style))
                it
            else
                ArrayList(it).apply { add(style) }
        }
    }

    private fun addStyle(
            changeBuilder: MultiChangeBuilder<Collection<String>, String, Collection<String>>,
            range: IntRange,
            style: String
    ): MultiChangeBuilder<Collection<String>, String, Collection<String>> {
        return addStyle(changeBuilder, range.first, range.last, style)
    }

    private fun removeStyle(
            changeBuilder: MultiChangeBuilder<Collection<String>, String, Collection<String>>,
            range: IntRange,
            style: String
    ): MultiChangeBuilder<Collection<String>, String, Collection<String>> {
        return removeStyle(changeBuilder, range.first, range.last, style)
    }

    private fun removeStyle(
            changeBuilder: MultiChangeBuilder<Collection<String>, String, Collection<String>>,
            from: Int, to: Int,
            style: String
    ): MultiChangeBuilder<Collection<String>, String, Collection<String>> {
        return changeStyle(changeBuilder, from, to) {
            ArrayList(it).apply { remove(style) }
        }
    }

    private fun changeStyle(
            changeBuilder: MultiChangeBuilder<Collection<String>, String, Collection<String>>,
            from: Int, to: Int,
            mapper: (Collection<String>) -> Collection<String>
    ): MultiChangeBuilder<Collection<String>, String, Collection<String>> {
        val d = SimpleEditableStyledDocument(
                content.subSequence(from, to)
                        as ReadOnlyStyledDocument<Collection<String>, String, Collection<String>>
        )

        val n = content.getStyleSpans(from, to).mapStyles(mapper)

        d.setStyleSpans(0, n)

        return changeBuilder.replaceAbsolutely(from, to, d)
    }

    private fun addStyle(
            range: IntRange,
            mapper: (Collection<String>) -> Collection<String>
    ): Replacement<Collection<String>, String, Collection<String>> {
        return changeStyle(range.first, range.last, mapper)
    }

    private fun addStyle(
            from: Int, to: Int,
            style: String
    ): Replacement<Collection<String>, String, Collection<String>> {
        val replacementDoc = SimpleEditableStyledDocument(
                ReadOnlyStyledDocument.from(content.subSequence(from, to))
        )

        val newStyleSpans = replacementDoc.getStyleSpans(0, to - from).mapStyles {
            ArrayList(it).apply {
                remove(style)
                add(style)
            }
        }

        replacementDoc.setStyleSpans(0, newStyleSpans)

        return Replacement(from, to, replacementDoc.snapshot())
    }

    private fun changeStyle(
            range: IntRange,
            mapper: (Collection<String>) -> Collection<String>
    ): Replacement<Collection<String>, String, Collection<String>> {
        return changeStyle(range.first, range.last, mapper)
    }

    private fun changeStyle(
            from: Int, to: Int,
            mapper: (Collection<String>) -> Collection<String>
    ): Replacement<Collection<String>, String, Collection<String>> {
        val replacementDoc = SimpleEditableStyledDocument(
                ReadOnlyStyledDocument.from(content.subSequence(from, to))
        )

        val newStyleSpans = replacementDoc.getStyleSpans(0, to - from).mapStyles(mapper)

        replacementDoc.setStyleSpans(0, newStyleSpans)

        return Replacement(from, to, replacementDoc.snapshot())
    }


    fun toFilePosition(position: Int): Int {
        return if (data.lineSeparator == LineSeparator.CRLF) {
            position + text.count('\n', to = position)
        } else {
            position
        }
    }

    fun toAreaPosition(position: Int): Int {
        return if (data.lineSeparator == LineSeparator.CRLF) {
            position - getTextTrueTerminator().count('\r', to = position)
        } else {
            position
        }
    }

    private fun toFileRange(range: IntRange): IntRange {
        return if (data.lineSeparator == LineSeparator.CRLF) {
            val txt = text
            val from = range.first + txt.count('\n', to = range.first)
            val to = range.last + txt.count('\n', to = range.last)
            IntRange(from, to)
        } else {
            range
        }
    }

    private fun toAreaRange(range: IntRange): IntRange {
        return if (data.lineSeparator == LineSeparator.CRLF) {
            val txt = getTextTrueTerminator()
            val from = range.first - txt.count('\r', to = range.first)
            val to = range.last - txt.count('\r', to = range.last)
            IntRange(from, to)
        } else {
            range
        }
    }

    public fun getTextTrueTerminator(): String {
        return if (data.lineSeparator == LineSeparator.LF)
            text
        else
            text.replace("\n", data.lineSeparator.separator)
    }

    public fun getLengthTrueTerminator(): Int {
        return if (data.lineSeparator.separator.length == 1)
            length
        else
            length + (data.lineSeparator.separator.length - 1) * text.count('\n')
    }
}
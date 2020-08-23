package ru.art2000.androraider.view.editor.code

import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.application.Platform
import javafx.beans.property.Property
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.beans.value.ObservableValue
import javafx.scene.Cursor
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyEvent
import javafx.scene.input.ScrollEvent
import javafx.scene.shape.Rectangle
import javafx.stage.Popup
import org.fxmisc.richtext.Caret
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.event.MouseOverTextEvent
import org.fxmisc.richtext.model.ReadOnlyStyledDocument
import org.fxmisc.richtext.model.StyledDocument
import org.reactfx.Subscription
import org.reactfx.value.Var
import ru.art2000.androraider.model.analyzer.AnalyzeMode
import ru.art2000.androraider.model.analyzer.result.*
import ru.art2000.androraider.model.editor.*
import ru.art2000.androraider.model.editor.file.LineSeparator
import ru.art2000.androraider.utils.*
import tornadofx.getValue
import tornadofx.setValue
import java.time.Duration
import java.util.concurrent.atomic.AtomicBoolean
import java.util.regex.Pattern
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

@Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate")
class CodeEditorArea(
        val dataProvider: CodeDataProvider,
        val settings: CodeEditingSettings
) : CodeArea(), StringSearchable {

    private var currentDisposable: Disposable? = null

    private var currentHighlightDisposable: Disposable? = null

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

    private var permanentSubscriptions = Subscription.EMPTY

    private var projectSpecificSubscriptions = Subscription.EMPTY

    private var highlightedNavigableRange: MutableList<HighlightableSegment> = mutableListOf()

    private val caretPositionListener: (ObservableValue<out Int>, Int, Int) -> Unit

    private val isStylingText = AtomicBoolean(false)
	
	
	
	
	
	
	
	
	
	

    private val observableSegments: Var<List<TextSegment>> = Var.newSimpleVar(emptyList())

    private val projectProperty = SimpleObjectProperty<Project?>()

    private var project: Project? by projectProperty

    public val lineSeparatorProperty: Property<LineSeparator> = SimpleObjectProperty(LineSeparator.LF)

    public var lineSeparator: LineSeparator by lineSeparatorProperty

    private var isFirstRun = true

    private val lineNumberBackground = Rectangle().also {
        it.heightProperty().bind(heightProperty())
        it.styleClass += "lineno"
    }


    init {
        paragraphGraphicFactory = CodeEditorLineNumber(this)

        stylesheets.add(javaClass.getStyle("code.css"))

        caretPositionListener = listener@ { _, _, newValue ->
            onNewCaretPosition(newValue)
        }

        caretPositionProperty().connect { _, _, newValue ->
            println("new caret pos: $newValue")
        }

        dataProvider.textProperty.connect { _, _, newValue ->
            if (newValue == getTextTrueTerminator()) {
                println("new text equals area text")
                return@connect
            }

//            println("new text for ${data.file}; o=${oldValue?.length}, n=${newValue?.length}")
            thread {
                unregisterSaveOnEdit()

                lineSeparator = when {
                    newValue.contains("\r\n") -> LineSeparator.CRLF
                    newValue.contains('\r') -> LineSeparator.CR
                    else -> LineSeparator.LF
                }

                Platform.runLater {
                    val prevPos = caretPosition

                    setText(newValue)
                    println("newAreaTxt")

                    if (isFirstRun) {
                        isFirstRun = false
//                        moveToAndPlaceLineInCenter(dataProvider.offset)
                    } else {
//                        moveTo(prevPos)
                    }

                    registerSaveOnEdit()
                }
            }
        }

        sceneProperty().addListener { _, _, _ ->
            project = getProjectForNode(this)
        }

        projectProperty.addListener { _, _, newValue ->
            projectSpecificSubscriptions.unsubscribe()
            projectSpecificSubscriptions = Subscription.EMPTY

            if (newValue == null)
                return@addListener

            projectSpecificSubscriptions += newValue.requestTextAnalyze(textProperty(), dataProvider.lang, AnalyzeMode.FULL) {
                val segments = it?.textSegments ?: emptyList()
//                println("Got new segments for file ${data.file}")
                observableSegments.value = segments
                applyTextSegmentsStyle(segments)
            }
        }

        multiPlainChanges().subscribe {
            observableSegments.value = emptyList()
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

        addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_BEGIN) { e ->
            isSearching = false

            val chIdx = e.characterIndex
            val pos = e.screenPosition

            if (false) {
//                if (isCtrlDown) {
                val hoveredAnalyzeStatus = observableSegments.value.findOfType<LinkSegment> { status ->
                    chIdx in status.segmentRange
                }

                if (pointedLink != hoveredAnalyzeStatus) {
                    removePointedLinkHighlight()
                }

                if (hoveredAnalyzeStatus != null) {
                    pointedLink = hoveredAnalyzeStatus
                    cursor = Cursor.HAND
                    addStyleClass(hoveredAnalyzeStatus.segmentRange, "shortcut-highlight")
                    return@addEventHandler
                }
            } else {
                val hoveredAnalyzeStatus = observableSegments.value.findOfType<DescriptiveSegment> { status ->
                    chIdx in status.segmentRange
                }

                if (hoveredAnalyzeStatus != null && hoveredAnalyzeStatus.description.isNotEmpty()) {
                    popupMsg.text = hoveredAnalyzeStatus.description
                    popup.show(this, pos.x, pos.y + 10)
                    return@addEventHandler
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
                val pos = caretPosition
                observableSegments.value.findOfType<LinkSegment> { status -> pos in status.segmentRange }?.apply {
                    println("Found range")
                    listener.invoke(this)
                }
            }

            if (event.code == KeyCode.TAB && !event.isShortcutDown) {
                // assume tab was already inserted
                replaceText(caretPosition - 1, caretPosition, settings.indentConfiguration.toString())
                return@addEventHandler
            }

            if (event.isShortcutDown && (event.code == KeyCode.SLASH || event.code == KeyCode.PERIOD)) {
                val refactorer = TypeDetector.getRefactoringRule(dataProvider.lang)

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

        showCaret = Caret.CaretVisibility.ON

        selectionProperty().addListener { _, _, _ ->
            if (!isSearching && searchSpanList.currentPosition >= 0) {
                searchSpanList.currentPosition = -1
            }
        }

//        registerSaveOnEdit()

        currentSearchValueProperty.addListener { _, oldValue, newValue ->
            if (searchSpanList.currentPosition >= 0
                    && (oldValue.startsWith(newValue, true)
                            || newValue.startsWith(oldValue, true))) {
                moveTo(caretPosition + (newValue.length - oldValue.length))
            }
        }
    }

    private fun onNewCaretPosition(position: Int) {
        val changes = mutableListOf<CodeReplacement>()

        val previousHighlight = highlightedNavigableRange
        if (previousHighlight.isNotEmpty()) {
            val invalid = previousHighlight.filter { position !in it.segmentRange }

            changes += invalid.map {
                removeStyleClassReplacement(it.segmentRange.first, it.segmentRange.last, it.highlightStyle)
            }

            previousHighlight.removeAll(invalid)
        }

        val segments = observableSegments.value
        val caretPositionStatus = segments.findOfType<HighlightableSegment> { status ->
            position in status.segmentRange
        }

        caretPositionStatus?.apply {
            segments.forEach { segment ->
                if (segment is HighlightableSegment && highlightOther(segment)) {
                    changes += addStyleClassReplacement(segment.segmentRange, segment.highlightStyle)
                    previousHighlight.add(segment)
                }
            }

            changes += addStyleClassReplacement(segmentRange, highlightStyle)

            previousHighlight.add(this)
        }

        content.replaceMulti(changes)
    }

    private fun registerSaveOnEdit() {
        println("reg")
        val saveOnChange = multiPlainChanges()
                .successionEnds(Duration.ofMillis(200))
                .subscribe {
                    println("text chng")
                    save()
                }

        val lineSeparatorSubscription = lineSeparatorProperty.observe { _, oldValue, _ ->
            if (oldValue != null) {
                save()
            }
        }

        fileSpecificSubscriptions.add(saveOnChange)
        fileSpecificSubscriptions.add(lineSeparatorSubscription)

        caretPositionProperty().addListener(caretPositionListener)
    }

    private fun unregisterSaveOnEdit() {
        println("unreg")

        fileSpecificSubscriptions.forEach { it.unsubscribe() }
        fileSpecificSubscriptions.clear()

        caretPositionProperty().removeListener(caretPositionListener)
    }

    private fun removePointedLinkHighlight() {
        pointedLink?.also {
            removeStyleClass(it.segmentRange.first, it.segmentRange.last, "shortcut-highlight")

            pointedLink = null
        }
        cursor = Cursor.TEXT
    }

    private fun applyTextSegmentsStyle(segments: List<TextSegment>) {
        currentHighlightDisposable?.dispose()
        currentHighlightDisposable = Maybe.fromCallable {
            val replacements = mutableListOf<CodeReplacement>()

            replacements += changeStyleReplacement(0, length) { emptyList() }

            println("Total segments: ${segments.size}")

            val time = measureTimeMillis {
                var k = 0
                segments.forEach { segment ->
                    if (segment is StyledSegment && segment.style.isNotEmpty()) {
                        ++k
                        val (from, to) = segment.segmentRange
                        replacements += setStyleClassReplacement(from, to, segment.style)
                    }
                }
                println("Styled segments: $k")
            }

            println("Style computing time millis: $time")

            replacements
        }.subscribeOn(Schedulers.computation())
                .observeOn(JavaFxScheduler.platform())
                .subscribe { replacements ->
                    val time = measureTimeMillis {
                        content.replaceMulti(replacements)
                    }

                    println("Style applying time millis: $time")
                    onNewCaretPosition(caretPosition)
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

    private fun save() {
        if (isEditable) {
            dataProvider.text = getTextTrueTerminator()
        }
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

    public fun openGoToLineDialog() {
        val dialog = GoToLineDialog(currentParagraph, caretColumn)

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

        if (isStylingText.getAndSet(true))
            return

        currentDisposable?.dispose()

        currentDisposable = computationSingle {
            val changes = mutableListOf<CodeReplacement>()

            changes += searchSpanList.map {
                removeStyleClassReplacement(it.first, it.last, "search")
            }

            searchSpanList.searchString = currentSearchValue
            if (currentSearchValue.isNotEmpty()) {
                val pattern = Pattern.compile(Pattern.quote(currentSearchValue.toLowerCase()))
                val searchMatcher = pattern.matcher(text.toLowerCase())
                if (pattern.pattern().isNotEmpty()) {
                    while (searchMatcher.find()) {
                        val start = searchMatcher.start()
                        val end = searchMatcher.end()

                        searchSpanList.add(start..end)

                        changes += addStyleClassReplacement(start, end, "search")
                    }
                }
            }
            changes
        }.observeOn(JavaFxScheduler.platform())
                .subscribe { replacements ->
                    content.replaceMulti(replacements)

                    isStylingText.set(false)
                    findNext(true)
                }

        currentHighlightDisposable = currentDisposable
    }

    public fun getTextTrueTerminator(): String {
        return if (lineSeparator == LineSeparator.LF)
            text
        else
            text.replace("\n", lineSeparator.separator)
    }

    private fun setText(text: String) {

        val doc = ReadOnlyStyledDocument.fromString(
                text, getParagraphStyleForInsertionAt(0), getTextStyleForInsertionAt(0), segOps
        )

        content.replace(0, length, doc)
    }

    //    public fun getLengthTrueTerminator(): Int {
//        return if (lineSeparator.separator.length == 1)
//            length
//        else
//            length + (lineSeparator.separator.length - 1) * text.count('\n')
//    }

    override fun layoutChildren() {

        try {

            if (children.firstOrNull() !== lineNumberBackground)
                children.add(0, lineNumberBackground)
            val index = visibleParToAllParIndex(0)
            val wd = getParagraphGraphic(index).prefWidth(-1.0)
            lineNumberBackground.width = wd
        } catch(_ : Exception) {}

        super.layoutChildren()
    }

    override fun dispose() {
        super.dispose()
        currentDisposable?.dispose()
        currentHighlightDisposable?.dispose()
        fileSpecificSubscriptions.forEach { it.unsubscribe() }
        projectSpecificSubscriptions.unsubscribe()
        permanentSubscriptions.unsubscribe()
        dataProvider.dispose()
    }
}
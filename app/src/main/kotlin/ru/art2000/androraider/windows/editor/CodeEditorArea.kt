package ru.art2000.androraider.windows.editor

import javafx.beans.InvalidationListener
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.control.Label
import javafx.stage.Popup
import org.fxmisc.richtext.Caret
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory
import org.fxmisc.richtext.event.MouseOverTextEvent
import org.fxmisc.richtext.model.StyleSpans
import org.fxmisc.richtext.model.StyleSpansBuilder
import ru.art2000.androraider.analyzer.types.SmaliClass
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.contains
import java.io.File
import java.nio.file.Files
import java.time.Duration
import java.util.*
import java.util.function.Consumer
import java.util.regex.Pattern
import kotlin.collections.ArrayList

@Suppress("RedundantVisibilityModifier")
class CodeEditorArea : CodeArea(), Searchable<String?> {

    val observableCurrentEditingFile = object : ObservableValue<File?> {
        override fun removeListener(p0: ChangeListener<in File?>?) {
            currentEditingFileChangeListeners.remove(p0)
        }

        override fun removeListener(p0: InvalidationListener?) {
            currentEditingFileInvalidationListeners.remove(p0)
        }

        override fun addListener(p0: InvalidationListener?) {
            currentEditingFileInvalidationListeners.add(p0)
        }

        override fun addListener(p0: ChangeListener<in File?>?) {
            currentEditingFileChangeListeners.add(p0)
        }

        override fun getValue(): File? = currentEditingFile
    }

    public var currentSmaliClass: SmaliClass? = null

    public var currentEditingFile: File? = null
        private set(value) {
            val previousValue = field
            field = value
            if (previousValue != value) {
                isFileChanged = true
                currentEditingFileChangeListeners.forEach {
                    it?.changed(observableCurrentEditingFile, previousValue, value)
                }

                if (value == null) {
                    currentEditingFileInvalidationListeners.forEach {
                        it?.invalidated(observableCurrentEditingFile)
                    }
                }
            }
        }

    private val currentEditingFileChangeListeners = mutableListOf<ChangeListener<in File?>?>()
    private val currentEditingFileInvalidationListeners = mutableListOf<InvalidationListener?>()

    val beforeFileWrittenListeners = mutableListOf<Consumer<File>>()
    val afterFileWrittenListeners = mutableListOf<Consumer<File>>()

    private var isFileChanged = false

    override var currentSearchValue: String? = null

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
        textProperty().addListener { _, oldValue, newValue ->
            if (isFileChanged) {
                isFileChanged = false
                return@addListener
            }
            if (currentEditingFile != null && oldValue.isNotEmpty()) {
                beforeFileWrittenListeners.forEach { it.accept(currentEditingFile!!) }
                Files.write(currentEditingFile!!.toPath(), newValue.toByteArray())
                afterFileWrittenListeners.forEach { it.accept(currentEditingFile!!) }
            }
        }
        multiPlainChanges()
                .successionEnds(Duration.ofMillis(100))
                .subscribe {
                    setStyleSpans(0, updateHighlighting())
                }

        val popup = Popup()
        val popupMsg = Label()
        popupMsg.style = "-fx-background-color: black;" +
                "-fx-text-fill: white;" +
                "-fx-padding: 5;"
        popup.content.add(popupMsg)

        mouseOverTextDelay = Duration.ofSeconds(1)
        addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_BEGIN) { e ->
            val chIdx = e.characterIndex
            val pos = e.screenPosition
            val pre = text.substring(0, chIdx).lastIndexOf(" ")
            val aft = text.substring(chIdx).indexOf(" ") + chIdx
            val sub = text.substring(pre + 1, aft)
            val pat = Pattern.compile("(?<LOCAL>v\\d+)")
            val mt = pat.matcher(sub)
            if (mt.find()) {

                val txt = mt.group("LOCAL").substring(1).toInt()
                val loc = text.substring(0, chIdx).lastIndexOf("locals") + "locals ".length
                val t = text.substring(loc).indexOf("\n") + loc
                val num = text.substring(loc, t).toInt()
                popupMsg.text = "Found local " + mt.group("LOCAL") + ".\n"
                if (txt < num)
                    popupMsg.text += "Total available locals : $num"
                else
                    popupMsg.text += "ERROR! Total available locals $num, but current is $txt!"
                popup.show(this, pos.x, pos.y + 10)
            }
        }
        addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_END) { popup.hide() }
    }

    public fun edit(file: File?, forceRead: Boolean = false) {
        if (file == null || !file.exists()) {
            currentEditingFile = null
            replaceText("")
            return
        }

        if (file.isDirectory)
            return

        if (file.absolutePath != currentEditingFile?.absolutePath || forceRead) {
            currentEditingFile = file
            replaceText(String(Files.readAllBytes(file.toPath())))
            setStyleSpans(0, updateHighlighting())
            displaceCaret(0)
        }
    }

    public override fun find(valueToFind: String?) {
        findAll(valueToFind)
    }

    public override fun findAll(valueToFind: String?) {
        currentSearchValue = valueToFind
        setStyleSpans(0, updateHighlighting())
        currentSearchCursor = 0
    }

    public override fun findNext() {
        if (searchSpanList.size == 0 || currentSearchValue.isNullOrEmpty()) {
            currentSearchCursor = -1
            return
        }

        currentSearchCursor = (currentSearchCursor + 1) % searchSpanList.size
        displaceCaret(searchSpanList[currentSearchCursor].last)
    }

    public override fun findPrevious() {
        val size = searchSpanList.size
        if (size == 0 || currentSearchValue.isNullOrEmpty()) {
            currentSearchCursor = -1
            searchSpanList.searchString
            return
        }

        currentSearchCursor = (size + currentSearchCursor - 1) % size
        displaceCaret(searchSpanList[currentSearchCursor].last)
    }

    private fun getSmaliHighlighting(pattern: Pattern, text: String): StyleSpansBuilder<Collection<String>> {
        val builder = StyleSpansBuilder<Collection<String>>()
        val matcher = pattern.matcher(text)
        var lastKwEnd = 0
        while (matcher.find()) {
            val styleClass = (when {
                matcher.contains("LOCAL") -> "local"
                matcher.contains("PARAM") -> "param"
                matcher.contains("CALL") -> "call"
                matcher.contains("NUMBER") -> "number"
                matcher.contains("KEYWORD") -> "keyword"
                matcher.contains("COMMENT") -> "comment"
                matcher.contains("BRACKET") -> "bracket"
                matcher.contains("STRING") -> "string"
                else -> return builder
            })
            builder.add(Collections.emptyList(), matcher.start() - lastKwEnd)
            builder.add(Collections.singleton(styleClass), matcher.end() - matcher.start())
            lastKwEnd = matcher.end()
        }
        builder.add(Collections.emptyList(), text.length - lastKwEnd)

        val errorStyle = listOf("error")
        val code = text
        lastKwEnd = 0
        val smaliClassCopy = currentSmaliClass
        if (smaliClassCopy == null) {
            builder.add(emptyList(), 0)
            return builder
        }
        smaliClassCopy.errors.forEach {
            builder.add(emptyList(), it.symbol.startIndex - lastKwEnd)
            builder.add(errorStyle, it.symbol.stopIndex - it.symbol.startIndex)
            lastKwEnd = it.symbol.stopIndex
        }
        builder.add(emptyList(), code.length - lastKwEnd)

        return builder
    }

    private fun getSimpleHighlighting(): StyleSpansBuilder<Collection<String>> {
        val builder = StyleSpansBuilder<Collection<String>>()
        builder.add(Collections.emptyList(), 0)
        return builder
    }

    private fun getErrorHighlighting(): StyleSpansBuilder<Collection<String>> {
        val builder = StyleSpansBuilder<Collection<String>>()
        val errorStyle = listOf("error")
        val code = text
        var segmentStart = 0
        val smaliClassCopy = currentSmaliClass
        if (smaliClassCopy == null) {
            builder.add(emptyList(), 0)
            println("NO SMALI")
            return builder
        }
        smaliClassCopy.errors.forEach {
//            println("Empty $segmentStart..${it.symbol.startIndex - 1}, error ${it.symbol.startIndex}..${it.symbol.stopIndex}")
            builder.add(emptyList(), it.symbol.startIndex - segmentStart)
            builder.add(errorStyle, it.symbol.stopIndex - it.symbol.startIndex + 1)
            segmentStart = it.symbol.stopIndex + 1
        }
        builder.add(emptyList(), code.length - segmentStart)
        return builder
    }

    private fun getSearchHighlighting(toSearch: String?, text: String): StyleSpans<Collection<String>> {
        searchSpanList.searchString = toSearch
        val pattern = Pattern.compile("(?<SEARCH>$toSearch)")
        val builder = StyleSpansBuilder<Collection<String>>()
        val matcher = pattern.matcher(text)
        var lastKwEnd = 0
        if (pattern.pattern().isNotEmpty()) {
            while (matcher.find()) {
                val styleClass = (when {
                    matcher.contains("SEARCH") -> "search"
                    else -> return builder.create()
                })
                builder.add(Collections.emptyList(), matcher.start() - lastKwEnd)
                builder.add(Collections.singleton(styleClass), matcher.end() - matcher.start())
                searchSpanList.add(IntRange(matcher.start(), matcher.end()))
                lastKwEnd = matcher.end()
            }
        }
        builder.add(Collections.emptyList(), text.length - lastKwEnd)
        return builder.create()
    }

    private fun updateHighlighting(): StyleSpans<Collection<String>> {
        clearStyle(0, text.length)
        val p = TypeDetector.getPatternForExtension(currentEditingFile?.extension)
        val pattern = Pattern.compile(p, Pattern.MULTILINE)
        println(pattern.pattern())

        var sp = when (currentEditingFile?.extension) {
//            "smali" -> getSmaliHighlighting(pattern, text)
            "smali" -> getErrorHighlighting()
            else -> getSimpleHighlighting()
        }.create()

        if (!currentSearchValue.isNullOrEmpty()) {
            sp = sp.overlay(getSearchHighlighting(currentSearchValue, text)) { first, second ->
                val list = ArrayList<String>()
                list.addAll(first)
                list.addAll(second)
                return@overlay list
            }
        }

        return sp
    }

}
package ru.art2000.androraider.view.editor

import io.reactivex.Single
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.ObjectPropertyBase
import javafx.scene.control.Label
import javafx.stage.Popup
import org.fxmisc.richtext.Caret
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory
import org.fxmisc.richtext.event.MouseOverTextEvent
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.editor.SearchSpanList
import ru.art2000.androraider.model.editor.getProjectForNode
import ru.art2000.androraider.utils.TypeDetector
import ru.art2000.androraider.utils.contains
import ru.art2000.androraider.utils.getStyle
import java.io.File
import java.nio.file.Files
import java.time.Duration
import java.util.regex.Pattern

@Suppress("RedundantVisibilityModifier", "MemberVisibilityCanBePrivate")
class CodeEditorArea : CodeArea(), Searchable<String> {

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

    public var currentSmaliClass: SmaliClass? = null

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

            val smaliCopy = currentSmaliClass
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
            Single
                    .fromCallable {
                        String(Files.readAllBytes(file.toPath()))
                    }.subscribeOn(Schedulers.io())
                    .observeOn(JavaFxScheduler.platform())
                    .doOnSuccess {
                        replaceText(it)
                        updateHighlighting()
                        displaceCaret(0)
                    }.subscribe()
        }
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
        Single
                .fromCallable {
                    getProjectForNode(this)?.analyzeFile(currentEditingFile!!)
                }.subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .doOnSuccess { result ->
                    if (result == null)
                        return@doOnSuccess

                    clearStyle(0, text.length)

                    val patternString = TypeDetector.getPatternForExtension(currentEditingFile?.extension)
                    val syntaxElementsMatcher = Pattern.compile(patternString).matcher(text)

                    syntaxElementsMatcher.results().forEach {


                        val styleClass = (when {
                            syntaxElementsMatcher.contains("LOCAL") -> "local"
                            syntaxElementsMatcher.contains("PARAM") -> "param"
                            syntaxElementsMatcher.contains("CALL") -> "call"
                            syntaxElementsMatcher.contains("NUMBER") -> "number"
                            syntaxElementsMatcher.contains("KEYWORD") -> "keyword"
                            syntaxElementsMatcher.contains("COMMENT") -> "comment"
                            syntaxElementsMatcher.contains("BRACKET") -> "bracket"
                            syntaxElementsMatcher.contains("STRING") -> "string"
                            else -> ""
                        })
                        setStyle(it.start(), it.end(), listOf(styleClass))
                    }

                    result.rangeStatuses.forEach { status ->
                        setStyle(status.range.first, status.range.last + 1, status.style)
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
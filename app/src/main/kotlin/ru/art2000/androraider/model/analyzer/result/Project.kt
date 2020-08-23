package ru.art2000.androraider.model.analyzer.result

import io.reactivex.Observable
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.ObservableMap
import javafx.collections.ObservableSet
import org.reactfx.Subscription
import org.reactfx.value.Val
import ru.art2000.androraider.model.analyzer.AnalyzeMode
import ru.art2000.androraider.utils.IdentityHashSet
import java.io.File

interface Project {

    val projectFolder: File

    val errorMap: ObservableMap<File, List<TextSegment>>

    val links: MutableMap<Any, ObservableSet<FileLink>>

    fun indexProject(): Observable<out FileIndexingResult>

    fun analyzeProject(): Observable<out FileAnalyzeResult>

    fun setupProject(): Observable<*> {
        return Observable.concat(indexProject(), analyzeProject())
    }

    fun requestFileAnalyze(file: File, mode: AnalyzeMode, callback: (FileAnalyzeResult?) -> Unit): Subscription

    fun requestTextAnalyze(
            text: String,
            lang: String,
            mode: AnalyzeMode,
            callback: (TextAnalyzeResult?) -> Unit,
    ): Subscription = requestTextAnalyze(Val.constant(text), lang, mode, callback)

    fun requestTextAnalyze(
            text: ObservableValue<String>,
            lang: String,
            mode: AnalyzeMode,
            callback: (TextAnalyzeResult?) -> Unit,
    ): Subscription

    fun getLinksFor(any: Any): ObservableSet<FileLink> = links.getOrPut(any) { FXCollections.observableSet(IdentityHashSet()) }
}
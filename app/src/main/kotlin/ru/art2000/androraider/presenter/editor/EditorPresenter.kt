package ru.art2000.androraider.presenter.editor

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javafx.stage.Window
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.AndroidAppProject
import ru.art2000.androraider.model.editor.getOrInitProject
import ru.art2000.androraider.model.editor.removeProject
import ru.art2000.androraider.model.io.DirectoryObserver
import ru.art2000.androraider.arch.IPresenter
import java.io.File
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchEvent
import kotlin.concurrent.thread

class EditorPresenter(private val window: Window, private val baseFolder: File) : IPresenter, Disposable {

    val openedFilesOrder = mutableListOf<Int>()

    fun updateTabHistory(selectedTabIndex: Int) {
        openedFilesOrder.remove(selectedTabIndex)
        openedFilesOrder.add(0, selectedTabIndex)
    }

    fun removeTabFromHistory(index: Int) {
        openedFilesOrder.remove(index)
        for (i in 0 until openedFilesOrder.size) {
            if (openedFilesOrder[i] > index) {
                openedFilesOrder[i]--
            }
        }
    }

    var disposed = false

    var projectObserver = DirectoryObserver(baseFolder)

    init {
        projectObserver.addListener { file, kind ->
            if (kind == StandardWatchEventKinds.ENTRY_MODIFY && project.canAnalyzeFile(file)) {
                thread {
                    project.analyzeFile(file, true)
                }
            }
        }
    }

    fun addFileListener(func: (File, WatchEvent.Kind<*>) -> Unit) {
        projectObserver.addListener(func)
    }

    fun startFileObserver() {
        projectObserver.start()
    }

    fun stopFileObserver() {
        projectObserver.stop()
    }

    fun generateProjectIndex() : Observable<out FileAnalyzeResult>? {
        return project.indexProject()
    }

    val project: AndroidAppProject by lazy { getOrInitProject(window, baseFolder) }

    override fun isDisposed() = disposed

    override fun dispose() {
        if (!disposed) {
            stopFileObserver()
            removeProject(window)
            disposed = true
        }
    }
}
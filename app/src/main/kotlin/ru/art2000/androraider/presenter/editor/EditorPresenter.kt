package ru.art2000.androraider.presenter.editor

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javafx.stage.Window
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.editor.getOrInitProject
import ru.art2000.androraider.model.editor.project.DirectoryObserver
import ru.art2000.androraider.mvp.IPresenter
import java.io.File

class EditorPresenter(private val window: Window, val baseFolder: File) : IPresenter, Disposable {

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

    val projectObserver = DirectoryObserver(baseFolder)

    fun generateProjectIndex() : Observable<out FileAnalyzeResult>? {
        return getOrInitProject(window, baseFolder).indexProject()
    }

    override fun isDisposed() = disposed

    override fun dispose() {
        if (!disposed) {

            disposed = true
        }
    }
}
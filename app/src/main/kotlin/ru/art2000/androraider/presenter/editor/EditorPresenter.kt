package ru.art2000.androraider.presenter.editor

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javafx.collections.FXCollections
import javafx.stage.Window
import ru.art2000.androraider.model.analyzer.result.FileAnalyzeResult
import ru.art2000.androraider.model.editor.getOrInitProject
import ru.art2000.androraider.model.editor.project.DirectoryObserver
import ru.art2000.androraider.mvp.IPresenter
import java.io.File

class EditorPresenter(private val window: Window, val baseFolder: File) : IPresenter, Disposable {

    var currentOpenedFile = -1
        private set

    private val _openedFiles = FXCollections.observableList<File>(mutableListOf())

    fun openFile(file: File) : Int {

        val indexedValue = openedFiles.withIndex().find { it.value == file }
        if (indexedValue == null) {
            _openedFiles.add(++currentOpenedFile, file)
        } else {
            currentOpenedFile = indexedValue.index
        }

        return currentOpenedFile
    }

    fun closeFile(i : Int) : Boolean {
        if (i < 0 || i > _openedFiles.lastIndex)
            return false

        return _openedFiles.removeAt(i) != null
    }

    public val openedFiles : List<File>
        get() {
            return _openedFiles.toList()
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
package ru.art2000.androraider.presenter.editor

import io.reactivex.disposables.Disposable
import javafx.collections.FXCollections
import ru.art2000.androraider.model.analyzer.SyntaxAnalyzer
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.editor.SingleInstanceObservableList
import ru.art2000.androraider.model.editor.project.DirectoryObserver
import ru.art2000.androraider.presenter.Presenter
import java.io.File

class EditorPresenter(val baseFolder: File) : Presenter, Disposable {

    val projectAnalyzeResult = ProjectAnalyzeResult(baseFolder)

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


    override fun isDisposed() = disposed

    override fun dispose() {
        if (!disposed) {

            disposed = true
        }
    }
}
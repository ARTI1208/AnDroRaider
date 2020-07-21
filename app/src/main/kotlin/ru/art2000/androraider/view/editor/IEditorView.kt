package ru.art2000.androraider.view.editor

import ru.art2000.androraider.arch.IView
import ru.art2000.androraider.presenter.editor.EditorPresenter

interface IEditorView : IView {

    override val presenter: EditorPresenter
}
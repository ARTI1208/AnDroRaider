package ru.art2000.androraider.model.refactoring

object DummyRefactoringRule : FileRefactoringRule {

    override fun isFragmentCommented(string: String) = false

    override fun commentUncommentFragment(string: String) = emptyList<CommentingResult>()
}
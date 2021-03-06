package ru.art2000.androraider.model.refactoring

object DummyRefactoringRule : RefactoringRule {

    override fun isFragmentCommented(string: String) = false

    override fun commentUncommentFragment(string: String) = listOf(CommentingResult(0, 0, string))
}
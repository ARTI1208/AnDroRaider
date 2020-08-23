package ru.art2000.androraider.model.refactoring

interface RefactoringRule {

    fun isFragmentCommented(string: String): Boolean

    fun commentUncommentFragment(string: String): List<CommentingResult>
}
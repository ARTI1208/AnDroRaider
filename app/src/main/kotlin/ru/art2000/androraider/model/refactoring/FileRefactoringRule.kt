package ru.art2000.androraider.model.refactoring

interface FileRefactoringRule {

    fun isFragmentCommented(string: String): Boolean

    fun commentUncommentFragment(string: String): List<CommentingResult>
}
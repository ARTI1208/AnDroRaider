package ru.art2000.androraider.model.refactoring

object SmaliRefactoringRule : FileRefactoringRule {

    private const val commentSign = "#"

    override fun isFragmentCommented(string: String): Boolean {
        return string.split('\n').all { it.trim().startsWith(commentSign) }
    }

    override fun commentUncommentFragment(string: String): List<CommentingResult> {
        return if (isFragmentCommented(string)) {
            string.split('\n').map {
                val deleted = (it.length - it.trim().length) + commentSign.length
                CommentingResult(-deleted, 0,  it.trim().drop(commentSign.length))
            }
        } else {
            string.split('\n').map {
                CommentingResult(commentSign.length, 0, "$commentSign$it")
            }
        }
    }
}
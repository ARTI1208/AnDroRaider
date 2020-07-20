package ru.art2000.androraider.model.analyzer.result

import org.antlr.v4.runtime.tree.ErrorNode
import ru.art2000.androraider.utils.textRange
import java.io.File

class Error(range: IntRange, override val description: String, override val declaringFile: File) : RangeAnalyzeStatus {

    companion object {
        fun from(errorNode: ErrorNode, file: File): Error {
            return Error(errorNode.textRange, "Error", file)
        }
    }

    override val rangeToStyle = listOf(range to "error")

}
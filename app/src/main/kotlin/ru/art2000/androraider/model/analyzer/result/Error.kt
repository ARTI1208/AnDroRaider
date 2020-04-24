package ru.art2000.androraider.model.analyzer.result

import org.antlr.v4.runtime.tree.ErrorNode

data class Error(override val range: IntRange, override val description: String) : RangeAnalyzeStatus {

    override val style: Collection<String>
        get() = listOf("error")

    companion object {
        fun from(errorNode: ErrorNode): Error {
            return Error(errorNode.symbol.startIndex..errorNode.symbol.stopIndex, "Hi")
        }
    }

}
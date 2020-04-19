package ru.art2000.androraider.model.editor

import org.antlr.v4.runtime.misc.Interval
import org.antlr.v4.runtime.tree.ErrorNode

data class Error(val interval: Interval, val description: String) {

    companion object {
        fun from(errorNode: ErrorNode): Error {
            return Error(Interval(errorNode.symbol.startIndex, errorNode.symbol.stopIndex), "Hi")
        }
    }

}
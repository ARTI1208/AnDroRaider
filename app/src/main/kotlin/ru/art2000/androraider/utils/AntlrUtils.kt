package ru.art2000.androraider.utils

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode

val ParserRuleContext.textRange: IntRange
    get() {
        return start.startIndex..stop.stopIndex
    }

val TerminalNode.textRange: IntRange
    get() {
        return symbol.startIndex..symbol.stopIndex
    }

public fun parseCompound(string: String): List<String> {
    val list = mutableListOf<String>()
    var tmpString = string
    while (tmpString.isNotEmpty()) {
        if (tmpString.startsWith('L')) {
            val i = tmpString.indexOf(';')

            list.add(tmpString.substring(0, i + 1))
            tmpString = tmpString.substring(i + 1)
        } else if (tmpString.startsWith('[')) {
            var i = 0
            f@ for (c in tmpString) {
                when (c) {
                    'Z', 'B', 'C', 'D', 'F', 'I', 'J', 'S' -> {
                        break@f
                    }
                    'L' -> {
                        i = tmpString.indexOf(';')
                        break@f
                    }
                    else -> i++
                }
            }
            list.add(tmpString.substring(0, i + 1))
            tmpString = tmpString.substring(i + 1)
        } else {
            list.add(tmpString[0].toString())
            tmpString = tmpString.substring(1)
        }

    }

    return list
}
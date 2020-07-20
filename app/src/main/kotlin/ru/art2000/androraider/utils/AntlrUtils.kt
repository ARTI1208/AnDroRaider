package ru.art2000.androraider.utils

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.TerminalNode

val ParserRuleContext.textRange: IntRange
    get() {
        return firstPos..lastPos
    }

val ParserRuleContext.firstPos: Int
    get() {
        return start.textRange.first
    }

val ParserRuleContext.lastPos: Int
    get() {
        return stop.textRange.last
    }

val TerminalNode.textRange: IntRange
    get() {
        return symbol.textRange
    }

val TerminalNode.firstPos: Int
    get() {
        return textRange.first
    }

val TerminalNode.lastPos: Int
    get() {
        return textRange.last
    }

val Token.textRange: IntRange
    get() {
        return startIndex..(stopIndex + 1)
    }

val Token.firstPos: Int
    get() {
        return textRange.first
    }

val Token.lastPos: Int
    get() {
        return textRange.last
    }


fun RuleContext.textWithSeparator(separator: String): String {
    if (childCount == 0) {
        return ""
    }

//    println("ChildCount: $childCount")

    val builder = StringBuilder()
    for (i in 0 until childCount) {
        val c = getChild(i)
        if (c is RuleContext) {
            if (i != 0)
                builder.append(separator)
            builder.append(c.textWithSeparator(separator))
        } else {
            builder.append(c.text)
        }
    }

    return builder.toString()
}

public fun parseCompound(string: String?): List<String> {
    if (string == null)
        return emptyList()

    val list = mutableListOf<String>()
    var tmpString: String = string
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
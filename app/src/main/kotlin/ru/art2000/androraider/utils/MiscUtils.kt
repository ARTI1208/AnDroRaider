package ru.art2000.androraider.utils

import javafx.scene.control.CheckBox
import javafx.scene.layout.Pane
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.misc.Interval
import ru.art2000.androraider.apktool.ApktoolCommand
import java.io.File
import java.util.regex.Matcher

fun Pane.goThrough(list: ArrayList<ApktoolCommand>) {
    for (ch in this.childrenUnmodifiable) {
        if (ch is Pane) {
            ch.goThrough(list)
        } else if (ch is CheckBox && ch.id.startsWith('-') && ch.isSelected) {
            list.add(ApktoolCommand(ch.id))
        }
    }
}

fun Matcher.contains(group: String): Boolean {
    return pattern().pattern().contains(group) && group(group) != null
}

public fun getFileRelativePath(file: File?, folder: File?): String? {
    if (file == null || folder == null)
        return null

    return file.absolutePath.removePrefix(folder.parent + "\\")
}

public val ParserRuleContext.textRange : IntRange
get() {
    return start.startIndex..stop.stopIndex
}

public operator fun Interval.contains(i : Int): Boolean {
    return i in a..b
}
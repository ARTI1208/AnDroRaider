package ru.art2000.androraider

import javafx.scene.control.CheckBox
import javafx.scene.layout.Pane
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

public fun getFileRelativePath(file: File?, folder: File): String? {
    return file?.absolutePath?.removePrefix(folder.parent + "\\")
}
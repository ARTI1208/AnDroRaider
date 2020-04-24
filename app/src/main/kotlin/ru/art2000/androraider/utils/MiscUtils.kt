package ru.art2000.androraider.utils

import javafx.beans.property.Property
import javafx.beans.value.ObservableValue
import javafx.scene.control.CheckBox
import javafx.scene.layout.Pane
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.misc.Interval
import ru.art2000.androraider.model.apktool.ApktoolCommand
import java.io.File
import java.util.regex.Matcher

public fun <T, E> Property<T>.bind(observable: ObservableValue<E>, converter: (e: E) -> T) {
    observable.addListener{_, _, newValue ->
        value = converter(newValue)
    }
    value = converter(observable.value)
}

fun Matcher.contains(group: String): Boolean {
    return pattern().pattern().contains(group) && group(group) != null
}

public fun getFileRelativePath(file: File?, folder: File?): String? {
    if (file == null || folder == null)
        return null

    return file.absolutePath.removePrefix(folder.parent + File.separator)
}

public val ParserRuleContext.textRange : IntRange
get() {
    return start.startIndex..stop.stopIndex
}

public operator fun Interval.contains(i : Int): Boolean {
    return i in a..b
}
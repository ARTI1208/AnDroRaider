package ru.art2000.androraider.utils

import javafx.collections.ObservableMap
import org.fxmisc.richtext.GenericStyledArea
import org.fxmisc.richtext.StyledTextField
import org.fxmisc.richtext.TextExt
import org.fxmisc.richtext.model.*
import org.reactfx.value.Val
import java.util.function.Supplier
import kotlin.collections.ArrayList
import kotlin.math.ceil

public fun StyledTextField<*, *>.computeContentWidth(): Double {
    val helper = TextExt(text)
    helper.styleClass.setAll(styleClass)
    helper.wrappingWidth = 0.0
    helper.lineSpacing = 0.0
    val d = helper.prefWidth(-1.0).coerceAtMost(0.0)
    helper.wrappingWidth = ceil(d)

    return ceil(helper.layoutBounds.width)*3
}

public fun StyledTextField<*, *>.autoWidth() {
    textProperty().addListener { _, _, _ ->
        prefWidth = computeContentWidth()
    }
}

typealias CodeReplacement = Replacement<Collection<String>, String, Collection<String>>

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.addStyleClassesReplacement(from: Int, to: Int, styles: Collection<String>) : CodeReplacement {
    return changeStyleReplacement(from, to) { oldStyles ->
        val onlyNewStyles = styles.filter { !oldStyles.contains(it) }

        if (onlyNewStyles.isEmpty())
            oldStyles
        else
            ArrayList(oldStyles).apply { addAll(onlyNewStyles) }
    }
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.addStyleClassReplacement(range: IntRange, style: String) : CodeReplacement {
    return addStyleClassesReplacement(range.first, range.last, listOf(style))
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.addStyleClassReplacement(from: Int, to: Int, style: String) : CodeReplacement {
    return addStyleClassesReplacement(from, to, listOf(style))
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.removeStyleClassesReplacement(from: Int, to: Int, styles: Collection<String>) : CodeReplacement {
    return changeStyleReplacement(from, to) {
        ArrayList(it).apply { removeAll(styles) }
    }
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.removeStyleClassReplacement(range: IntRange, style: String) : CodeReplacement {
    return removeStyleClassesReplacement(range.first, range.last, listOf(style))
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.removeStyleClassReplacement(from: Int, to: Int, style: String) : CodeReplacement {
    return removeStyleClassesReplacement(from, to, listOf(style))
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.setStyleClassReplacement(from: Int, to: Int, style: String) : CodeReplacement {
    return changeStyleReplacement(from, to) { listOf(style) }
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.changeStyleReplacement(from: Int, to: Int, mapper: (Collection<String>) -> Collection<String>) : CodeReplacement {
    val d = SimpleEditableStyledDocument(
            ReadOnlyStyledDocument.from(content.subSequence(from, to))
    )

    val n = content.getStyleSpans(from, to).mapStyles(mapper)

    d.setStyleSpans(0, n)

    return Replacement(from, to, d.snapshot())
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.addStyleClasses(from: Int = 0, to: Int = length, styles: Collection<String>) {
    return changeStyle(from, to) { oldStyles ->
        val onlyNewStyles = styles.filter { !oldStyles.contains(it) }

        if (onlyNewStyles.isEmpty())
            oldStyles
        else
            ArrayList(oldStyles).apply { addAll(onlyNewStyles) }
    }
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.addStyleClass(range: IntRange = 0..length, style: String) {
    return addStyleClasses(range.first, range.last, listOf(style))
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.addStyleClass(from: Int = 0, to: Int = length, style: String) {
    return addStyleClasses(from, to, listOf(style))
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.removeStyleClasses(from: Int = 0, to: Int = length, styles: Collection<String>) {
    return changeStyle(from, to) {
        ArrayList(it).apply { removeAll(styles) }
    }
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.removeStyleClass(range: IntRange = 0..length, style: String) {
    return removeStyleClasses(range.first, range.last, listOf(style))
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.removeStyleClass(from: Int = 0, to: Int = length, style: String) {
    return removeStyleClasses(from, to, listOf(style))
}

public fun GenericStyledArea<Collection<String>, String, Collection<String>>.changeStyle(from: Int = 0, to: Int = length, mapper: (Collection<String>) -> Collection<String>) {
    val replacement = SimpleEditableStyledDocument(
            ReadOnlyStyledDocument.from(content.subSequence(from, to))
    )

    val oldSpans = replacement.getStyleSpans(0, to - from)
    val newSpans = oldSpans.mapStyles(mapper)

    if (oldSpans != newSpans) {
        replacement.setStyleSpans(0, newSpans)
        replace(from, to, replacement)
    }
}

public val ObservableMap<*, *>.sizeProperty: Val<Int>
    get() = Val.create(Supplier { size }, this)

public fun <K, V> ObservableMap<K, V>.getObservableValue(key: K): Val<V?> {
    return Val.create(Supplier { get(key) }, this)
}

public fun <K, V> ObservableMap<K, V>.getObservableValueOrDefault(key: K, default: V): Val<V> {
    return Val.create(Supplier { get(key) ?: default }, this)
}

public fun <K, V> ObservableMap<K, V>.getObservableValueOrDefault(key: K, default: () -> V): Val<V> {
    return Val.create(Supplier { get(key) ?: default() }, this)
}

//public fun <PS, SEG, S> MultiChangeBuilder<PS, SEG, S>.changeStyle(
//        document: EditableStyledDocument<PS, SEG, S>,
//        from: Int, to: Int,
//        mapper: (S) -> S
//): MultiChangeBuilder<PS, SEG, S> {
//
//    val d = SimpleEditableStyledDocument(
//            document.subSequence(from, to)
//                    as ReadOnlyStyledDocument<Collection<String>, String, Collection<String>>
//    )
//
//    val n = document.getStyleSpans(from, to).mapStyles(mapper)
//
//    d.setStyleSpans(0, n)
//
//    return replaceAbsolutely(from, to, d)
//}

//private fun changeStyle(
//        changeBuilder: MultiChangeBuilder<Collection<String>, String, Collection<String>>,
//        from: Int, to: Int,
//        mapper: (Collection<String>) -> Collection<String>
//): MultiChangeBuilder<Collection<String>, String, Collection<String>> {
//    val d = SimpleEditableStyledDocument(
//            content.subSequence(from, to)
//                    as ReadOnlyStyledDocument<Collection<String>, String, Collection<String>>
//    )
//
//    val n = content.getStyleSpans(from, to).mapStyles(mapper)
//
//    d.setStyleSpans(0, n)
//
//    return changeBuilder.replaceAbsolutely(from, to, d)
//}
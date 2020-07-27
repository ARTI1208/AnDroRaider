package ru.art2000.androraider.utils

import org.fxmisc.richtext.MultiChangeBuilder
import org.fxmisc.richtext.StyledTextField
import org.fxmisc.richtext.TextExt
import org.fxmisc.richtext.model.*
import java.util.*
import java.util.regex.Pattern
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
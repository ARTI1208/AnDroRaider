package ru.art2000.androraider.utils

import org.fxmisc.richtext.StyledTextField
import org.fxmisc.richtext.TextExt
import org.fxmisc.richtext.model.Paragraph
import org.fxmisc.richtext.model.ReadOnlyStyledDocument
import org.fxmisc.richtext.model.TextOps
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
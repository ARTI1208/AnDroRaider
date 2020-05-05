package ru.art2000.androraider.utils

import javafx.scene.control.Label
import org.fxmisc.richtext.GenericStyledArea
import org.fxmisc.richtext.StyleClassedTextArea
import org.fxmisc.richtext.StyledTextField
import org.fxmisc.richtext.TextExt
import kotlin.math.ceil

public class StyledLabel : StyleClassedTextArea(){

}

public fun StyledTextField<*, *>.computeContentWidth(): Double {
    val helper = TextExt(text)
    helper.styleClass.setAll(styleClass)
    helper.wrappingWidth = 0.0
    helper.lineSpacing = 0.0
    val d = helper.prefWidth(-1.0).coerceAtMost(0.0)
    helper.wrappingWidth = ceil(d)

//    walk {
//        println(it)
//    }

//    println(helper.layoutBounds.width)
    return ceil(helper.layoutBounds.width)*3
}

public fun StyledTextField<*, *>.autoWidth() {
    textProperty().addListener { _, _, newValue ->
        prefWidth = computeContentWidth()
    }

//    multiRichChanges().addObserver {
//        prefWidth = computeContentWidth()
//    }
}
package ru.art2000.androraider.view.editor.code

import javafx.scene.Node
import javafx.scene.control.Label
import org.fxmisc.richtext.GenericStyledArea
import ru.art2000.androraider.utils.bind
import java.util.function.IntFunction

class CodeEditorLineNumber(val area: GenericStyledArea<*, *, *>) : IntFunction<Node> {

    override fun apply(value: Int): Node {
        val label = Label().apply { styleClass.add("lineno") }
        val text = (value + 1).toString()
        label.textProperty().bind(area.paragraphs.sizeProperty()) {
            val spaceCount = it.toString().length - text.length
            "${" ".repeat(spaceCount.coerceAtLeast(0))}$text"
        }
        return label
    }
}
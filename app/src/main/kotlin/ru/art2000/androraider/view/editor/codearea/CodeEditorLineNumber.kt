package ru.art2000.androraider.view.editor.codearea

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
            "${" ".repeat(it.toString().length - text.length)}$text"
        }
        return label
    }
}
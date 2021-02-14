package ru.art2000.androraider.model.editor.file

import javafx.beans.property.ReadOnlyStringWrapper
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.scene.Node
import javafx.scene.image.Image
import ru.art2000.androraider.model.editor.StatusBarElement
import java.util.function.Consumer

enum class LineSeparator(
    val separator: String,
    val platform: String
) {

    LF("\n", "Unix/macOS"),
    CRLF("\r\n", "Windows"),
    CR("\r", "Classic macOS");

    val separatorAsString = separator
        .replace("\r", """\r""")
        .replace("\n", """\n""")

    companion object {
        fun fromSeparator(separator: String): LineSeparator {
            return values().firstOrNull { it.separator == separator } ?: LF
        }
    }

}

class LineSeparatorElement(
    val separator: LineSeparator,
    override val action: Consumer<Node>? = null
) : StatusBarElement {

    override val icon: ObservableValue<Image?> = SimpleObjectProperty(null)

    override val displayedValue = ReadOnlyStringWrapper(separator.name)

    override val description = "Line separator: ${separator.separatorAsString}"

    override val active = SimpleBooleanProperty(true)
}
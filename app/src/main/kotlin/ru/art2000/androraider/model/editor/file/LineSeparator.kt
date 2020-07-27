package ru.art2000.androraider.model.editor.file

import javafx.beans.property.ReadOnlyStringWrapper
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.geometry.Bounds
import javafx.scene.Node
import javafx.scene.control.ContentDisplay
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.image.Image
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import org.reactfx.value.Var
import ru.art2000.androraider.model.editor.StatusBarElement
import ru.art2000.androraider.utils.boundsInScreen
import tornadofx.cellFormat
import tornadofx.fitToHeight
import tornadofx.uiComponent
import java.util.function.Consumer

public enum class LineSeparator(
        val separator: String,
        val platform: String) {

    LF("\n", "Unix/macOS"),
    CRLF("\r\n", "Windows"),
    CR("\r", "Classic macOS");

    public val separatorAsString = separator
            .replace("\r", """\r""")
            .replace("\n", """\n""")

    companion object {
        public fun fromSeparator(separator: String): LineSeparator {
            return values().firstOrNull { it.separator == separator } ?: LF
        }
    }

}

public class LineSeparatorElement(val separator: LineSeparator,
                                  override val action: Consumer<Node>? = null) : StatusBarElement {

    override val icon: ObservableValue<Image?> = SimpleObjectProperty(null)

    override val displayedValue = ReadOnlyStringWrapper(separator.name)

    override val description = "Line separator: ${separator.separatorAsString}"

    override val active = SimpleBooleanProperty(true)
}
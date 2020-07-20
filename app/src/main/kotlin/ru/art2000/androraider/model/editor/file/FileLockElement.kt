package ru.art2000.androraider.model.editor.file

import javafx.scene.Node
import javafx.scene.image.Image
import org.reactfx.value.Var
import ru.art2000.androraider.model.editor.StatusBarElement
import java.util.function.Consumer

class FileLockElement(val canWrite: Boolean = false, val isInProject: Boolean = true, override val action: Consumer<Node>? = null) : StatusBarElement {

    override val icon: Image? = null

    override val displayedValue: String
        get() = if (canWrite) "RW" else "RO"

    override val description: String
        get() = if (canWrite) "Make File Read-Only" else "Make File Writable"

    override val active = Var.newSimpleVar(isInProject)
}
package ru.art2000.androraider.view.editor.filemanager

import javafx.collections.MapChangeListener
import javafx.collections.ObservableMap
import javafx.event.EventHandler
import javafx.fxml.Initializable
import javafx.geometry.Pos
import javafx.scene.control.*
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.TextAlignment
import org.fxmisc.richtext.StyleClassedTextField
import ru.art2000.androraider.model.analyzer.result.TextSegment
import ru.art2000.androraider.utils.*
import java.io.File
import java.net.URL
import java.util.*
import kotlin.collections.HashMap

class FileManagerTreeListItem(
        private val fileManagerView: FileManagerView,
//        private val errorTree: ErrorTree
        private val errorMap: ObservableMap<File, List<TextSegment>>? = null
) : TreeCell<File>(), Initializable {

    private val fileContextMenu = ContextMenu()

    internal val textField = StyleClassedTextField().apply {
        isEditable = false
        isMouseTransparent = true
        background = null
        styleClass.setAll("editor-file-manager-item-label")
        alignment = TextAlignment.JUSTIFY
        prefHeight = DEFAULT_SIZE
        minHeight = prefHeight
        maxHeight = prefHeight
    }

    private val l = HBox().apply {
        alignment = Pos.CENTER_LEFT
        spacing = 5.0
    }

    private val icon = ImageView().apply { styleClass.add("icon") }

    companion object {

        private val FOLDER_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("folder.png")

        private val TEXT_FILE_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("txt.png")

        private val UNKNOWN_FILE_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("unknown.png")

        val ARROW_ICON = ::FileManagerTreeListItem.javaClass.getDrawable("arrow.png")

        const val DEFAULT_SIZE = 26.0
    }

    init {
        val close = ImageView(ARROW_ICON)
        close.fitWidth = DEFAULT_SIZE - 8
        close.fitHeight = DEFAULT_SIZE - 8
        disclosureNode = close

        val menuItemCreate = MenuItem("Create")
        menuItemCreate.onAction = EventHandler {
            fileManagerView.onTreeItemCreate(treeItem)
        }

        val menuItemRename = MenuItem("Rename")
        menuItemRename.onAction = EventHandler {
            fileManagerView.onTreeItemRename(treeItem)
        }

        val menuItemDelete = MenuItem("Delete")
        menuItemDelete.onAction = EventHandler {
            fileManagerView.onTreeItemDelete(treeItem)
        }

        fileContextMenu.items.addAll(
                menuItemCreate,
                menuItemRename,
                menuItemDelete
        )

        styleClass.add("file-manager-item")

        fileManagerView.currentSearchValueProperty.addListener { _, _, newValue ->
            updateHighlighting(newValue, textField.text)
        }
        textField.minWidthProperty().bind(textField.prefWidthProperty())
        textField.textProperty().addListener { _, _, newValue ->
            updateHighlighting(fileManagerView.currentSearchValue, newValue)
//            item?.also {
//                if (it == null) {
//                    updateStyle(false)
//                } else {
//                    updateStyle(errorTree.containsFile(it))
//                }
//            }
            checkError()
        }

        errorMap?.addListener { _: MapChangeListener.Change<out File, out List<TextSegment>> ->
            checkError()
        }

//        errorTree.observeNow(object : ObservableTree.TreeChangeListener<File> {
//            override fun <I : Tree.Item<File, I>> changed(change: ObservableTree.TreeChange<File, I>) {
//                if (change.item.value == item) {
//                    updateStyle(change.isAdded)
//                }
//            }
//        })

        textField.autoWidth()

        l.children.addAll(icon, textField)
        prefHeight = DEFAULT_SIZE
        minHeight = prefHeight
        maxHeight = prefHeight
    }

    private fun checkError() {
        val hasError = treeItem?.walk {
            errorMap?.get(it.value)?.isNotEmpty() == true
        } ?: false

        checkedRunLater {
            if (hasError)
                textField.addStyleClass(style = "file-error")
            else
                textField.removeStyleClass(style = "file-error")
        }
    }

    private fun updateStyle(hasError: Boolean) {
        checkedRunLater {
            if (hasError)
                textField.addStyleClass(style = "file-error")
            else
                textField.removeStyleClass(style = "file-error")
        }
    }

    private fun updateErrorHighlighting(map: Map<File, List<TextSegment>>?, cellFile: File?) {
        // TODO fix ConcurrentModificationException / implement more efficient algorithm
        val hasError = if (map != null && cellFile != null) {
            HashMap(map).any { it.key.startsWith(cellFile) && it.value.isNotEmpty() }
        } else
            false

        checkedRunLater {
            if (hasError)
                textField.addStyleClass(style = "file-error")
            else
                textField.removeStyleClass(style = "file-error")
        }
    }

    private fun updateHighlighting(toFind: String?, text: String?) {
        if (text == null)
            return

        textField.setStyle(0, text.length, listOf("selected-item-text"))

        if (toFind.isNullOrEmpty())
            return

        Regex(toFind, RegexOption.IGNORE_CASE).findAll(text).forEach {
            textField.setStyle(it.range.first, it.range.last + 1, listOf("search"))
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        maxWidth = Control.USE_PREF_SIZE
    }

    override fun updateItem(item: File?, empty: Boolean) {
        super.updateItem(item, empty)
        if (empty || item == null) {
            contextMenu = null
            graphic = null
            text = null
            textField.replaceText("")
            contentDisplay = ContentDisplay.TEXT_ONLY
            return
        }

        contextMenu = fileContextMenu
        icon.image = when {
            item.isDirectory -> FOLDER_ICON
            TypeDetector.Text.listContains(item.extension) -> TEXT_FILE_ICON
            else -> UNKNOWN_FILE_ICON
        }
        textField.replaceText(item.name)
        textField.prefHeight = DEFAULT_SIZE

        icon.fitWidth = DEFAULT_SIZE
        icon.fitHeight = DEFAULT_SIZE
        text = item.name
        graphic = l
        contentDisplay = ContentDisplay.GRAPHIC_ONLY

        if (treeItem?.isExpanded == true)
            disclosureNode.rotate = 0.0
        else
            disclosureNode.rotate = -90.0
    }
}
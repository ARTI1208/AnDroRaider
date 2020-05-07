package ru.art2000.androraider.view.editor.classes

import javafx.scene.control.ContentDisplay
import javafx.scene.control.TreeCell
import javafx.scene.image.ImageView
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.view.editor.filemanager.FileManagerTreeListItem

class SmaliDataTreeCell: TreeCell<SmaliComponent>() {

    init {
        val close = ImageView(FileManagerTreeListItem.ARROW_ICON)
        close.fitWidth = FileManagerTreeListItem.DEFAULT_SIZE - 8
        close.fitHeight = FileManagerTreeListItem.DEFAULT_SIZE - 8
        disclosureNode = close
    }

    override fun updateItem(item: SmaliComponent?, empty: Boolean) {
        super.updateItem(item, empty)

        contentDisplay = ContentDisplay.TEXT_ONLY
        if (item == null || empty) {
            text = null
            return
        }

        text = item.fullname

        if (treeItem?.isExpanded == true)
            disclosureNode.rotate = 0.0
        else
            disclosureNode.rotate = -90.0
    }
}
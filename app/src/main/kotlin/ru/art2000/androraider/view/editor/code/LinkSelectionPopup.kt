package ru.art2000.androraider.view.editor.code

import javafx.scene.control.ListView
import javafx.scene.control.PopupControl
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.Region
import javafx.stage.Popup
import javafx.stage.PopupWindow
import ru.art2000.androraider.model.analyzer.result.FileLink
import ru.art2000.androraider.model.analyzer.result.Link
import ru.art2000.androraider.utils.setItemCellFactory

@Suppress("NON_EXHAUSTIVE_WHEN")
class LinkSelectionPopup(links: List<Link>, openLink: (Link) -> Unit) : Popup() {

    init {
        isAutoHide = true

        val listView = ListView<Link>()
        listView.items.addAll(links)
        listView.setItemCellFactory { item, empty, cell ->
            if (item == null || empty) {
                cell.text = ""
                cell.prefHeight = .0
                cell.maxHeight = .0
                return@setItemCellFactory
            }
            cell.prefHeight = Region.USE_COMPUTED_SIZE

            cell.text = item.description
        }

        listView.selectionModel.select(0)

        val selectLink = {
            val selectedItem = listView.selectionModel.selectedItem
            selectedItem?.apply {
                openLink(this)
            }
            hide()
        }

        listView.addEventFilter(KeyEvent.KEY_PRESSED) { event ->
            when (event.code) {
                KeyCode.ENTER -> selectLink()
                KeyCode.ESCAPE -> hide()
            }
        }

        content.add(listView)
    }

}
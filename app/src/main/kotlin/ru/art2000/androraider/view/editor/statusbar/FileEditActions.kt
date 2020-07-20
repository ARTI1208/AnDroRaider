package ru.art2000.androraider.view.editor.statusbar

import javafx.beans.property.Property
import javafx.scene.Node
import javafx.scene.control.ContentDisplay
import javafx.scene.control.ListView
import javafx.scene.layout.VBox
import javafx.stage.Popup
import ru.art2000.androraider.model.editor.file.FileLockElement
import ru.art2000.androraider.model.editor.file.LineSeparator
import ru.art2000.androraider.utils.boundsInScreen
import ru.art2000.androraider.utils.setItemCellFactory
import java.io.File
import java.util.function.Consumer

public object FileEditActions {

    public fun getLineSeparatorAction(lineSeparatorProperty: Property<LineSeparator>,
                                      lineSeparator: LineSeparator): Consumer<Node> {
        return Consumer {
            val popup = Popup()

            popup.isAutoHide = true
            popup.isAutoFix = true

            val list = ListView<LineSeparator>()
            list.items.addAll(LineSeparator.values())
            list.prefHeight = list.items.size * 25.0

            list.setItemCellFactory { item, empty, cell ->
                if (item == null || empty) {
                    cell.text = ""
                    cell.contentDisplay = ContentDisplay.TEXT_ONLY
                    return@setItemCellFactory
                }

                cell.text = "${item.name} - ${item.platform} (${item.separatorAsString})"
                cell.isDisable = lineSeparator == item
            }

            list.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
                if (newValue != null) {
                    lineSeparatorProperty.value = newValue
                    popup.hide()
                }
            }

            val box = VBox()
            box.children += list

            popup.content += box

            val bounds = it.boundsInScreen

            popup.show(it.scene.window, bounds.minX, bounds.minY)

            popup.show(it.scene.window, bounds.minX, bounds.minY - box.height)
        }
    }

    public fun toggleReadOnly(file: File, fileLockProperty: Property<FileLockElement>) {
        val oldValue = fileLockProperty.value

        val newIsEditable = if (oldValue.isInProject) {
            if (oldValue.canWrite == file.canWrite()) {
                file.setWritable(!oldValue.canWrite) // try toggle
            }
            file.canWrite()
        } else {
            false
        }

        fileLockProperty.value = FileLockElement(newIsEditable, oldValue.isInProject, Consumer {
            toggleReadOnly(file, fileLockProperty)
        })
    }

}
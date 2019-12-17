package ru.art2000.androraider

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import javafx.scene.layout.HBox
import java.io.File

class FileManagerView : TreeView<File>() {

    @FunctionalInterface
    interface FileSelectedListener {
        fun fileSelected(oldFile: File?, newFile: File)
    }

    val onFileSelectedListeners = mutableListOf<FileSelectedListener>()

    val fileHistory = mutableListOf<File>()

    init {
        setCellFactory { FileManagerTreeListItem() }
        onMouseClicked = EventHandler {
            if (it.button === MouseButton.PRIMARY
                    && it.clickCount == 2) {
                onTreeItemClick(selectionModel.selectedItem, true)
            }
        }
        addEventHandler(KeyEvent.KEY_PRESSED) {
            if (it.code == KeyCode.SPACE) {
                onTreeItemClick(selectionModel.selectedItem)
                it.consume()
            }
        }

        onKeyPressed = EventHandler {
            @Suppress("NON_EXHAUSTIVE_WHEN")
            when (it.code) {
                KeyCode.ENTER -> onTreeItemClick(selectionModel.selectedItem)
                KeyCode.DELETE -> onTreeItemDelete(selectionModel.selectedItem)
                KeyCode.LEFT -> {
                    if (it.isAltDown)
                        selectionModel.selectedItem?.parent?.isExpanded = false
                }
                KeyCode.RIGHT -> {
                    if (it.isAltDown)
                        selectionModel.selectedItem?.isExpanded = true
                }
            }
        }
    }

    public fun setupWithBaseFolder(baseFolder: File) {
        root = TreeItem(baseFolder)
        addFileExplorerTreeItemChildren(root)
        selectionModel.select(0)
    }

    private fun onTreeItemClick(treeItem: TreeItem<File>?, byMouse: Boolean = false) {
        if (treeItem == null) {
            return
        }

        if (treeItem.value.isDirectory) {
            if (!byMouse) {
                treeItem.isExpanded = !treeItem.isExpanded
            }
        } else {
            onFileItemClick(treeItem.value)
        }
    }

    private fun onFileItemClick(file: File) {
        if (fileHistory.getOrNull(0) == file) {
            println("Same file ${file.absolutePath}")
            return
        }

        onFileSelectedListeners.forEach {
            it.fileSelected(fileHistory.getOrNull(0), file)
        }
        fileHistory.remove(file)
        fileHistory.add(0, file)
    }

    private fun onTreeItemDelete(treeItem: TreeItem<File>?) {
        if (treeItem == null)
            return

        val deleted = onFileItemDelete(treeItem.value)
        if (deleted) {
            treeItem.parent.children.remove(treeItem)
        }
    }

    private fun onFileItemDelete(file: File): Boolean {
        val deleteFileDialog = Dialog<Boolean>()
        val path = getFileRelativePath(file, root.value)
        deleteFileDialog.title = "Delete $path"
        val dialogPane = DialogPane()
        val toDeleteString = "${if (file.isDirectory) "directory" else "file"} $path"
        val mainLabel = Label("Are you sure you want delete $toDeleteString?")
        val dialogBox = HBox()
        dialogBox.children.addAll(mainLabel)
        dialogBox.padding = Insets(0.0, 0.0, 20.0, 0.0)

        dialogPane.content = dialogBox
        dialogPane.padding = Insets(10.0, 10.0, 10.0, 10.0)
        deleteFileDialog.dialogPane = dialogPane
        deleteFileDialog.initOwner(scene.window)
        deleteFileDialog.dialogPane.buttonTypes.addAll(ButtonType.CLOSE, ButtonType.OK)
        (deleteFileDialog.dialogPane.lookupButton(ButtonType.CLOSE) as Button).isDefaultButton = true

        deleteFileDialog.setResultConverter {
            if (it == ButtonType.OK) {
                try {
                    if (file.isDirectory) {
                        return@setResultConverter file.deleteRecursively()
                    } else {
                        return@setResultConverter file.delete()
                    }
                } catch (exception: SecurityException) {
                    showErrorMessage("Delete error", "Error deleting $toDeleteString", scene.window)
                }
            }

            return@setResultConverter false
        }
        return deleteFileDialog.showAndWait().get()
    }

    private fun addFileExplorerTreeItemChildren(treeItem: TreeItem<File>) {
        val allFiles = treeItem.value.listFiles() ?: return

        val dirs = mutableListOf<TreeItem<File>>()
        val files = mutableListOf<TreeItem<File>>()

        allFiles.forEach {
            if (it.isHidden)
                return@forEach

            val treeSubItem = TreeItem(it)

            if (it.isDirectory) {
                dirs.add(treeSubItem)
                addFileExplorerTreeItemChildren(treeSubItem)
            } else files.add(treeSubItem)
        }

        treeItem.children.setAll(dirs)
        treeItem.children.addAll(files)
    }
}
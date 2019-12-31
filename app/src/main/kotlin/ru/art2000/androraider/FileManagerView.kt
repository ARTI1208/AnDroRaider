package ru.art2000.androraider

import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import java.io.File
import java.util.*

@Suppress("RedundantVisibilityModifier")
class FileManagerView : TreeView<File>(), Searchable<String?> {

    @FunctionalInterface
    interface FileSelectedListener {
        fun fileSelected(oldFile: File?, newFile: File)
    }

    val onFileSelectedListeners = mutableListOf<FileSelectedListener>()

    private val fileHistory = mutableListOf<File>()

    private val searchResults = mutableListOf<File>()

    private val searchResultsAsTree = mutableListOf<File>()

    private lateinit var baseFolder: File

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

    public fun setupWithBaseFolder(baseFolder: File, expandFolders: Boolean = false) {
        this.baseFolder = baseFolder
        root = TreeItem(baseFolder)
        if (expandFolders)
            root.isExpanded = true

        addFileExplorerTreeItemChildren(root, expandFolders)
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
        val toDeleteString = "${if (file.isDirectory) "directory" else "file"} $path"
        val mainLabel = Label("Are you sure you want delete $toDeleteString?")

        deleteFileDialog.dialogPane = getBaseDialogPane(mainLabel)
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

    private fun addFileExplorerTreeItemChildren(treeItem: TreeItem<File>, expandFolders: Boolean = false) {
        val allFiles = treeItem.value.listFiles() ?: return

        val dirs = mutableListOf<TreeItem<File>>()
        val files = mutableListOf<TreeItem<File>>()

        allFiles.forEach {
            if (it.isHidden)
                return@forEach

            if (searchResultsAsTree.size == 0 || searchResultsAsTree.contains(it) || searchResultsAsTree.contains(it.parentFile)) {
                val treeSubItem = TreeItem(it)
                if (it.isDirectory) {
                    dirs.add(treeSubItem)
                    addFileExplorerTreeItemChildren(treeSubItem)
                    treeSubItem.isExpanded = expandFolders
                } else files.add(treeSubItem)
            }
        }

        treeItem.children.setAll(dirs)
        treeItem.children.addAll(files)
    }

    override var currentSearchValue: String? = null

    override fun find(valueToFind: String?) {
        findAll(valueToFind)
    }

    override fun findAll(valueToFind: String?) {
        searchResultsAsTree.clear()

        if (valueToFind == null || valueToFind.isEmpty()) {

            return
        }


        baseFolder.walk().forEach {

            if (it.name.toLowerCase().contains(valueToFind.toLowerCase())
                    || it.extension.toLowerCase().contains(valueToFind.toLowerCase())
                    || searchResultsAsTree.contains(it.parentFile)) {
                searchResultsAsTree.add(it)
            }
        }

        setupWithBaseFolder(baseFolder, true)
    }

    override fun findNext() {

    }

    override fun findPrevious() {

    }
}
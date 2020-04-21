package ru.art2000.androraider.view.editor

import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.dialogs.getBaseDialogPane
import ru.art2000.androraider.view.dialogs.showErrorMessage
import ru.art2000.androraider.utils.getFileRelativePath
import java.io.File
import java.nio.file.DirectoryNotEmptyException
import java.nio.file.Files

@Suppress("RedundantVisibilityModifier")
class FileManagerView : TreeView<File>(), Searchable<String?> {

    @FunctionalInterface
    interface FileSelectedListener {
        fun fileSelected(oldFile: File?, newFile: File)
    }

    val onFileSelectedListeners = mutableListOf<FileSelectedListener>()

    private val fileHistory = mutableListOf<File>()

    private val searchResults = mutableListOf<File>()

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
                KeyCode.N -> {
                    if (it.isShortcutDown) {
                        onTreeItemCreate(selectionModel.selectedItem)
                    }
                }
                KeyCode.F2 -> {
                    onTreeItemRename(selectionModel.selectedItem)
                }
            }
        }
    }

    public fun setupWithBaseFolder(baseFolder: File) {
        this.baseFolder = baseFolder
        updateFileList()
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

    fun onTreeItemCreate(treeItem: TreeItem<File>?) {
        if (treeItem == null)
            return

        val parentFolder = if (treeItem.value.isDirectory) treeItem.value else treeItem.value.parentFile

        val nameInput = TextField()
        nameInput.promptText = "Input folder name"

        val folderLabel = Label("Input folder name")
        folderLabel.labelFor = nameInput
        folderLabel.textOverrun = OverrunStyle.LEADING_ELLIPSIS

        val typeOptions = ListView<String>()
        typeOptions.items.addAll("Folder", "XML", "Smali", "File")
        typeOptions.fixedCellSize = 20.0
        typeOptions.prefHeight = typeOptions.fixedCellSize * typeOptions.items.size

        val creationDialog = getBaseDialog<Unit>(folderLabel, nameInput, typeOptions)
        creationDialog.title = "Creation dialog"
        creationDialog.width = 400.0
        creationDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)

        typeOptions.selectionModel.select(0)
        typeOptions.selectionModel.selectedIndexProperty().addListener { _, _, n ->
            val type = n.toInt()
            val name = getNewFile(type, nameInput.text, parentFolder)

            folderLabel.text = onInputOrTypeChanged(type, typeOptions.items[type], name)
            nameInput.promptText = folderLabel.text
        }

        typeOptions.setOnMouseClicked {
            nameInput.requestFocus()
        }

        nameInput.textProperty().addListener { _, _, newValue ->
            val type = typeOptions.selectionModel.selectedIndex
            val name = getNewFile(type, newValue, parentFolder)

            folderLabel.text = onInputOrTypeChanged(type, typeOptions.items[type], name)
            nameInput.promptText = folderLabel.text
        }

        typeOptions.onKeyPressed = EventHandler {
            if (it.code == KeyCode.ENTER) {
                if (nameInput.text.isNullOrEmpty()) {
                    return@EventHandler
                }

                var creationResult = false
                try {
                    val newName = getNewFile(
                            typeOptions.selectionModel.selectedIndex,
                            nameInput.text,
                            parentFolder
                    ) ?: return@EventHandler

                    creationResult = when (typeOptions.selectionModel.selectedIndex) {
                        0 -> File(newName).mkdirs() // folder
                        else -> File(newName).createNewFile() // file
                    }
                } catch (e: Exception) {

                } finally {
                    if (creationResult) {
                        creationDialog.close()
                    } else {
                        showErrorMessage("Creation failed",
                                "Creation of ${typeOptions.selectionModel.selectedItem.toLowerCase()} " +
                                        "${getNewFile(
                                                typeOptions.selectionModel.selectedIndex,
                                                nameInput.text,
                                                parentFolder
                                        )} failed",
                                scene.window
                        )
                    }
                }

            } else if (it.code == KeyCode.ESCAPE) {
                creationDialog.close()
            }
        }

        nameInput.onKeyPressed = EventHandler {
            if (it.code == KeyCode.UP) {
                if (typeOptions.selectionModel.selectedIndex != 0)
                    typeOptions.selectionModel.selectPrevious()
            } else if (it.code == KeyCode.DOWN) {
                if (typeOptions.selectionModel.selectedIndex != typeOptions.items.lastIndex)
                    typeOptions.selectionModel.selectNext()
            } else {
                typeOptions.onKeyPressed.handle(it)
            }
        }


        nameInput.requestFocus()

        creationDialog.showAndWait()
    }

    private fun onInputOrTypeChanged(typeIndex: Int, typeValue: String, name: String?): String {
        if (name.isNullOrEmpty()) {
            return "Input ${typeValue.toLowerCase()} name"
        }

        return when (typeIndex) {
            0 -> "Folder $name"
            else -> "File $name"
        } + " will be created"
    }

    private fun getNewFile(type: Int, name: String?, parentFolder: File): String? {
        if (name.isNullOrEmpty()) {
            return null
        }

        return when (type) {
            1 -> "${parentFolder.absolutePath}${File.separatorChar}${name}.xml"
            2 -> "${parentFolder.absolutePath}${File.separatorChar}${name}.smali"
            else -> "${parentFolder.absolutePath}${File.separatorChar}${name}"
        }
    }

    fun onTreeItemRename(treeItem: TreeItem<File>?) {
        if (treeItem == null)
            return

        val nameInput = TextField(treeItem.value.name)
        nameInput.promptText = "Input new name"

        val previousName = Label("Previous name: ${treeItem.value.name}")
        previousName.textOverrun = OverrunStyle.LEADING_ELLIPSIS

        val renamingDialog = getBaseDialog<Unit>(previousName, nameInput)
        renamingDialog.title = "Renaming dialog"
        renamingDialog.width = 400.0
        renamingDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)

        nameInput.onKeyPressed = EventHandler {
            if (it.code == KeyCode.ENTER) {
                if (nameInput.text.isNullOrEmpty()) {
                    return@EventHandler
                }

                var renameResult = true
                val originalPath = treeItem.value.toPath()
                val newPath = originalPath.parent.resolve(nameInput.text).toAbsolutePath()
                try {
                    // TODO renaming of non-empty directory
                    Files.move(originalPath, newPath)
                } catch (dirNotEmptyException: DirectoryNotEmptyException) {
                    // is not thrown dunno why
                    renameResult = false
                } catch (e: Exception) {
                    renameResult = false
                    e.printStackTrace()
                } finally {
                    if (renameResult) {
                        renamingDialog.close()
                    } else {
                        showErrorMessage("Rename failed",
                                "Rename of ${if (treeItem.value.isDirectory) "folder" else "file"} " +
                                        "${treeItem.value.name} failed",
                                scene.window
                        )
                    }
                }

            } else if (it.code == KeyCode.ESCAPE) {
                renamingDialog.close()
            }
        }

        nameInput.requestFocus()

        renamingDialog.showAndWait()
    }

    fun onTreeItemDelete(treeItem: TreeItem<File>?) {
        if (treeItem == null)
            return

        onFileItemDelete(treeItem.value)
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

    private fun saveStructure(): Map<String, Boolean> {
        val map = mutableMapOf<String, Boolean>()
        walkTree {
            map[it.value.absolutePath] = it.isExpanded
        }
        return map
    }

    private fun restoreStructure(map: Map<String, Boolean>) {
        walkTree {
            it.isExpanded = map[it.value.absolutePath] ?: false
        }
    }

    private fun <T> TreeView<T>.walkTree(func: (TreeItem<T>) -> Unit) {
        walkExpandableNode(root, func)
    }

    private fun <T> TreeView<T>.walkExpandableNode(node: TreeItem<T>?, func: (TreeItem<T>) -> Unit) {
        if (node == null)
            return

        func(node)
        node.children.forEach {
            walkExpandableNode(it, func)
        }
    }

    public fun updateFileList(fileToSelect: File? = null) {
        val structure = saveStructure()
        root = TreeItem(baseFolder)
        val itemToSelect = if (fileToSelect == null)
            addFileExplorerTreeItemChildren(root, selectionModel.selectedItem?.value)
        else
            addFileExplorerTreeItemChildren(root, fileToSelect)
        restoreStructure(structure)

        if (itemToSelect != null)
            selectionModel.select(itemToSelect)
        else {
            selectionModel.selectFirst()
            root.isExpanded = true
        }
    }

    private fun addFileExplorerTreeItemChildren(treeItem: TreeItem<File>, fileToSelect: File? = null): TreeItem<File>? {
        val allFiles = treeItem.value.listFiles() ?: return null

        var returnItem: TreeItem<File>? = null
        val dirs = mutableListOf<TreeItem<File>>()
        val files = mutableListOf<TreeItem<File>>()

        allFiles.forEach {
            if (it.isHidden)
                return@forEach

            if (searchResults.size == 0
                    || searchResults.contains(it)
                    || searchResults.contains(it.parentFile)) {
                val treeSubItem = TreeItem(it)

                if (it.absolutePath == fileToSelect?.absolutePath)
                    returnItem = treeSubItem

                if (it.isDirectory) {
                    dirs.add(treeSubItem)

                    if (returnItem == null)
                        returnItem = addFileExplorerTreeItemChildren(treeSubItem)
                    else
                        addFileExplorerTreeItemChildren(treeSubItem)
                } else files.add(treeSubItem)
            }
        }

        treeItem.children.setAll(dirs)
        treeItem.children.addAll(files)
        return returnItem
    }

    override var currentSearchValue: String? = null

    override fun find(valueToFind: String?) {
        findAll(valueToFind)
    }

    override fun findAll(valueToFind: String?) {
        searchResults.clear()

        if (valueToFind == null || valueToFind.isEmpty()) {

            return
        }


        baseFolder.walk().forEach {

            if (it.name.toLowerCase().contains(valueToFind.toLowerCase())
                    || it.extension.toLowerCase().contains(valueToFind.toLowerCase())
                    || searchResults.contains(it.parentFile)) {
                searchResults.add(it)
            }
        }

        setupWithBaseFolder(baseFolder)
    }

    override fun findNext() {

    }

    override fun findPrevious() {

    }


}
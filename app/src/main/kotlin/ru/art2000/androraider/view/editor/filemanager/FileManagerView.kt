package ru.art2000.androraider.view.editor.filemanager

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.event.EventHandler
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseButton
import ru.art2000.androraider.model.editor.FileCreationArguments
import ru.art2000.androraider.model.editor.getProjectForNode
import ru.art2000.androraider.presenter.editor.FileManagerPresenter
import ru.art2000.androraider.utils.getRawContent
import ru.art2000.androraider.utils.moveOrCopyDelete
import ru.art2000.androraider.view.dialogs.getBaseDialog
import ru.art2000.androraider.view.dialogs.getBaseDialogPane
import ru.art2000.androraider.view.dialogs.showErrorMessage
import java.io.File
import ru.art2000.androraider.utils.compareTo
import ru.art2000.androraider.model.editor.StringSearchable

typealias onFileSelected = (File) -> Unit

@Suppress("RedundantVisibilityModifier")
class FileManagerView : TreeView<File>(), StringSearchable {

    val onFileSelectedListeners = mutableListOf<onFileSelected>()

    private val presenter = FileManagerPresenter()

    override val currentSearchValueProperty: StringProperty = SimpleStringProperty("")

    init {
        setCellFactory { FileManagerTreeListItem(this) }
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

        sceneProperty().addListener { _, _, newValue ->
            newValue?.window?.also {
                updateFileList()
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
        onFileSelectedListeners.forEach {
            it(file)
        }
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

        val options = presenter.getFileCreationOptions(getProjectForNode(this), parentFolder)

        typeOptions.items.addAll(options.map { it.visibleName })
        typeOptions.fixedCellSize = 20.0
        typeOptions.prefHeight = typeOptions.fixedCellSize * typeOptions.items.size

        val creationDialog = getBaseDialog<Unit>(folderLabel, nameInput, typeOptions)
        creationDialog.title = "Creation dialog"
        creationDialog.width = 400.0
        creationDialog.dialogPane.buttonTypes.add(ButtonType.CLOSE)

        typeOptions.selectionModel.select(0)
        typeOptions.selectionModel.selectedIndexProperty().addListener { _, _, n ->
            val type = n.toInt()
            val name = getNewFile(options[type], nameInput.text, parentFolder)

            folderLabel.text = onInputOrTypeChanged(type, typeOptions.items[type], name)
            nameInput.promptText = folderLabel.text
        }

        typeOptions.setOnMouseClicked {
            nameInput.requestFocus()
        }

        nameInput.textProperty().addListener { _, _, newValue ->
            val type = typeOptions.selectionModel.selectedIndex
            val name = getNewFile(options[type], newValue, parentFolder)

            folderLabel.text = onInputOrTypeChanged(type, typeOptions.items[type], name)
            nameInput.promptText = folderLabel.text
        }

        typeOptions.onKeyPressed = EventHandler {
            if (it.code == KeyCode.ENTER) {
                if (nameInput.text.isNullOrEmpty()) {
                    return@EventHandler
                }

                val fileType = typeOptions.selectionModel.selectedIndex
                val selectedOption = options[fileType]

                val fileName = getNewFile(
                        selectedOption,
                        nameInput.text,
                        parentFolder
                ) ?: return@EventHandler

                var creationResult = false
                try {
                    creationResult = if (selectedOption.isDirectory) {
                        File(fileName).mkdirs()
                    } else {
                        File(fileName).createNewFile()
                    }
                } catch (e: Exception) {

                } finally {
                    if (creationResult) {
                        creationDialog.close()
                        if (selectedOption.fileExtension.isNotEmpty()) {
                            val content = javaClass.getRawContent("templates/${selectedOption.fileExtension}")
                            val file = File(fileName)
                            if (content.isNotEmpty()) {
                                file.writeBytes(content)
                            }
                            onFileItemClick(file)
                        }
                    } else {
                        showErrorMessage("Creation failed",
                                "Creation of ${typeOptions.selectionModel.selectedItem.toLowerCase()} " +
                                        "$fileName failed",
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

    private fun getNewFile(option: FileCreationArguments, name: String?, parentFolder: File): String? {
        if (name.isNullOrEmpty()) {
            return null
        }

        val nameWithoutExtension = "${parentFolder.absolutePath}${File.separatorChar}${name}"
        return if (option.fileExtension.isEmpty())
            nameWithoutExtension
        else
            "${nameWithoutExtension}.${option.fileExtension}"
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

                val originalFile = treeItem.value

                val newPath = originalFile.toPath().parent.resolve(nameInput.text).toAbsolutePath().toFile()

                val renameResult = originalFile.moveOrCopyDelete(newPath)

                if (renameResult) {
                    renamingDialog.close()
                } else {
                    showErrorMessage("Rename failed",
                            "Rename of ${if (originalFile.isDirectory) "folder" else "file"} " +
                                    "${originalFile.name} failed",
                            scene.window
                    )
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
        val path = file.relativeTo(root.value)
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

    public fun removeBranch(file: File) {
        var item: TreeItem<File>? = null
        walkTree {
            if (it.value == file)
                item = it
        }

        item?.parent?.children?.remove(item)
    }

    public fun addBranch(file: File) {
        val parent = file.parentFile ?: return
        var item: TreeItem<File>? = null
        walkTree {
            if (it.value == parent)
                item = it
        }

        val result = item ?: return

        val pos = result.children.binarySearch { it.value.compareTo(file, true) }

        if (pos < 0) {
            result.children.add(-pos - 1 , TreeItem(file))
        }
    }

    public fun updateFileList(fileToSelect: File? = null) {
        val project = getProjectForNode(this)

        if (project == null) {
            root = null
            return
        }

        val structure = saveStructure()

        root = TreeItem(project.projectFolder)
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
        val allFiles = treeItem.value.listFiles()?.sortedBy { it.name } ?: return null

        var returnItem: TreeItem<File>? = null
        val dirs = mutableListOf<TreeItem<File>>()
        val files = mutableListOf<TreeItem<File>>()

        allFiles.forEach {
            if (it.isHidden)
                return@forEach

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

        treeItem.children.setAll(dirs)
        treeItem.children.addAll(files)
        return returnItem
    }

    override fun find(valueToFind: String) {
        findAll(valueToFind)
    }

    override fun findAll(valueToFind: String) {
        currentSearchValue = valueToFind
    }

    override fun findNext() {

    }

    override fun findPrevious() {

    }
}
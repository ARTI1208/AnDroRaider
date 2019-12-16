package ru.art2000.androraider

import VectorTools.Main
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.*
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import javafx.stage.DirectoryChooser
import javafx.stage.Popup
import javafx.stage.Stage
import javafx.stage.Window
import org.fxmisc.richtext.Caret
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory
import org.fxmisc.richtext.event.MouseOverTextEvent
import org.fxmisc.richtext.model.StyleSpans
import org.fxmisc.richtext.model.StyleSpansBuilder
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.time.Duration
import java.time.Duration.ofMillis
import java.util.*
import java.util.Collections.emptyList
import java.util.regex.Pattern
import kotlin.collections.ArrayList


class Editor @Throws(IOException::class)
constructor(project: File) : Window() {

    companion object {
        @JvmStatic
        lateinit var baseFolder: File
    }

    var editorStage = Stage()

    init {
        baseFolder = if (project.isDirectory) project else project.parentFile
        val loader = FXMLLoader(javaClass.getResource(LoadUtils.getLayout("editor.fxml")))
        loader.setController(EditorLayoutController())
        val root = loader.load<Parent>()
        editorStage.apply {
            icons.add(LoadUtils.getDrawable("logo.png"))
            title = "${baseFolder.name} - Project Editor"
            scene = Scene(root, 900.0, 600.0)
            scene.stylesheets.add(
                    this@Editor.javaClass.getResource(LoadUtils.getStyle("code.css")).toExternalForm())
        }
    }

    public override fun show() {
        editorStage.show()
    }

    inner class EditorLayoutController {
        // Menu/File
        @FXML
        lateinit var home: MenuItem
        @FXML
        lateinit var settings: MenuItem
        @FXML
        lateinit var recompile: MenuItem

        // Menu/Search
        @FXML
        lateinit var searchMenu: Menu
        @FXML
        lateinit var search: CustomMenuItem

        // Menu/Misc
        @FXML
        lateinit var examineFile: MenuItem

        // Main area
        @FXML
        lateinit var filesTreeList: TreeView<File>
        @FXML
        lateinit var editorArea: CodeArea

        private var currentEditingFile: File? = null
            set(value) {
                if (value != null)
                    editorStage.title = "${getFileRelativePath(value)} - Project Editor"
                isFileChanged = true
                field = value
            }

        private var isFileChanged = false

        private var currentSearch: String? = null

        private lateinit var searchField: TextField

        private val searchSpanList = SearchSpanList()

        private var currentSearchCursor = -1
            set(value) {
                if (value >= 0 && value < searchSpanList.size) {
                    field = value
                    editorArea.showCaret = Caret.CaretVisibility.ON
                    editorArea.displaceCaret(searchSpanList[value].last)
                    editorArea.scrollYToPixel(editorArea.currentParagraph.toDouble() * 15)
                } else {
                    field = -1
                }
            }

        @Suppress("unused")
        fun initialize() {

            editorStage.title = "${baseFolder.name} - Project Editor"
            editorStage.onShown = EventHandler {
                filesTreeList.requestFocus()
            }

            editorArea.prefWidthProperty().bind(editorStage.widthProperty().subtract(filesTreeList.prefWidthProperty()))
            editorArea.prefHeightProperty().bind(editorStage.heightProperty().multiply(1.0))
            filesTreeList.prefHeightProperty().bind(editorStage.heightProperty().multiply(1.0))

            setupMenu()
            setupCodeArea()
            setupFileExplorerView()
        }

        private fun getFileRelativePath(file: File? = currentEditingFile): String? {
            return file?.absolutePath?.removePrefix(baseFolder.parent + "\\")
        }

        private fun onTreeItemClick(treeItem: TreeItem<File>?, byMouse: Boolean = false) {
            if (treeItem == null) {
                println("null exit")
                return
            }
//            println("Try open ${treeItem.value.name} $byMouse")
            if (treeItem.value.isDirectory) {
                if (!byMouse) {
                    treeItem.isExpanded = !treeItem.isExpanded
                    println("Set exp ${treeItem.isExpanded} for ${treeItem.value.name}")
                }
            } else {
                onFileItemClick(treeItem.value)
            }
        }

        private fun onFileItemClick(file: File) {
            if (TypeDetector.isTextFile(file.name)) {
                if (file.absolutePath != currentEditingFile?.absolutePath) {
                    editorArea.replaceText(String(Files.readAllBytes(file.toPath())))
                    editorArea.displaceCaret(0)
                    currentEditingFile = file
                }
                editorArea.requestFocus()
            }
            // TODO show message for unsupported types of files
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
            deleteFileDialog.title = "Delete ${getFileRelativePath(file)}"
            val dialogPane = DialogPane()
            val toDeleteString = "${if (file.isDirectory) "directory" else "file"} ${getFileRelativePath(file)}"
            val mainLabel = Label("Are you sure you want delete $toDeleteString?")
            val dialogBox = HBox()
            dialogBox.children.addAll(mainLabel)
            dialogBox.padding = Insets(0.0, 0.0, 20.0, 0.0)

            dialogPane.content = dialogBox
            dialogPane.padding = Insets(10.0, 10.0, 10.0, 10.0)
            deleteFileDialog.dialogPane = dialogPane
            deleteFileDialog.initOwner(editorStage)
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
                        showErrorMessage("Delete error", "Error deleting $toDeleteString")
                    }
                }

                return@setResultConverter false
            }
            return deleteFileDialog.showAndWait().get()
        }

        @Suppress("SameParameterValue")
        private fun showErrorMessage(title: String, message: String) {
            val errorDialog = Dialog<Unit>()
            errorDialog.title = title
            val dialogPane = DialogPane()
            val mainLabel = Label(message)
            val dialogBox = HBox()
            dialogBox.children.addAll(mainLabel)
            dialogBox.padding = Insets(0.0, 0.0, 20.0, 0.0)

            dialogPane.content = dialogBox
            dialogPane.padding = Insets(10.0, 10.0, 10.0, 10.0)
            errorDialog.dialogPane = dialogPane
            errorDialog.initOwner(editorStage)
            errorDialog.dialogPane.buttonTypes.addAll(ButtonType.OK)
            errorDialog.showAndWait()
        }

        private fun updateHighlighting(searchString: String? = currentSearch): StyleSpans<Collection<String>> {
            editorArea.clearStyle(0, editorArea.text.length)
            val p = TypeDetector.getPatternForExtension(currentEditingFile?.extension)
            val pattern = Pattern.compile(p, Pattern.MULTILINE)
            println(pattern.pattern())

            var sp = when (currentEditingFile?.extension) {
                "smali" -> getSmaliHighlighting(pattern)
                else -> getSimpleHighlighting()
            }.create()

            if (searchString != null && searchString.isNotEmpty()) {
                sp = sp.overlay(getSearchHighlighting(searchString)) { first, second ->
                    val list = ArrayList<String>()
                    list.addAll(first)
                    list.addAll(second)
                    return@overlay list
                }
            }

            return sp
        }

        private fun createFileInfoDialog() {
            val fileInfoDialog = Dialog<Unit>()
            fileInfoDialog.title = getFileRelativePath() ?: "No file is currently editing"
            val dialogPane = DialogPane()
            val typeLabelTitle = Label("Type:")
            val typeLabelValue = Label(currentEditingFile?.extension ?: "No file or extension")
            val typeBox = HBox()
            typeBox.children.addAll(typeLabelTitle, typeLabelValue)
            typeBox.spacing = 40.0
            val dialogBox = VBox()
            dialogBox.children.add(typeBox)
            if (TypeDetector.Image.isVectorDrawable(currentEditingFile)) {
                val vectorImageLabelTitle = Label("Edit Vector Image:")
                val vectorImageButtonValue = Button("Edit...")
                vectorImageButtonValue.onAction = EventHandler {
                    //                    VectorTools.Main.launch(currentEditingFile?.path)
                    println(Main.openWindow())
                }
                val vectorImageBox = HBox()
                vectorImageBox.spacing = 40.0
                vectorImageBox.children.addAll(vectorImageLabelTitle, vectorImageButtonValue)
                dialogBox.children.add(vectorImageBox)
            }

            dialogPane.content = dialogBox
            dialogPane.padding = Insets(10.0, 10.0, 10.0, 10.0)
            fileInfoDialog.dialogPane = dialogPane
            fileInfoDialog.initOwner(editorStage)
            fileInfoDialog.dialogPane.buttonTypes.add(ButtonType.OK)
            fileInfoDialog.show()
        }

        private fun getSearchHighlighting(toSearch: String?): StyleSpans<Collection<String>> {
            searchSpanList.searchString = toSearch
            val pattern = Pattern.compile("(?<SEARCH>$toSearch)")
            val builder = StyleSpansBuilder<Collection<String>>()
            val matcher = pattern.matcher(editorArea.text)
            var lastKwEnd = 0
            if (pattern.pattern().isNotEmpty()) {
                while (matcher.find()) {
                    val styleClass = (when {
                        matcher.contains("SEARCH") -> "search"
                        else -> return builder.create()
                    })
                    builder.add(emptyList(), matcher.start() - lastKwEnd)
                    builder.add(Collections.singleton(styleClass), matcher.end() - matcher.start())
                    searchSpanList.add(IntRange(matcher.start(), matcher.end()))
                    lastKwEnd = matcher.end()
                }
            }
            builder.add(emptyList(), editorArea.text.length - lastKwEnd)
            return builder.create()
        }

        private fun getSimpleHighlighting(): StyleSpansBuilder<Collection<String>> {
            val builder = StyleSpansBuilder<Collection<String>>()
            builder.add(emptyList(), 0)
            return builder
        }

        private fun getSmaliHighlighting(pattern: Pattern): StyleSpansBuilder<Collection<String>> {
            val builder = StyleSpansBuilder<Collection<String>>()
            val matcher = pattern.matcher(editorArea.text)
            var lastKwEnd = 0
            while (matcher.find()) {
                val styleClass = (when {
                    matcher.contains("LOCAL") -> "local"
                    matcher.contains("PARAM") -> "param"
                    matcher.contains("CALL") -> "call"
                    matcher.contains("NUMBER") -> "number"
                    matcher.contains("KEYWORD") -> "keyword"
                    matcher.contains("COMMENT") -> "comment"
                    matcher.contains("BRACKET") -> "bracket"
                    matcher.contains("STRING") -> "string"
                    else -> return builder
                })
                builder.add(emptyList(), matcher.start() - lastKwEnd)
                builder.add(Collections.singleton(styleClass), matcher.end() - matcher.start())
                lastKwEnd = matcher.end()
            }
            builder.add(emptyList(), editorArea.text.length - lastKwEnd)
            return builder
        }

        private fun setupCodeArea() {
            editorArea.apply {
                styleClass.add("text-area")
                onKeyPressed = EventHandler {
                    if (it.isControlDown && it.code == KeyCode.F) {
                        searchMenu.show()
                        searchField.requestFocus()
                        searchField.positionCaret(searchField.text.length)
                    }
                }
                paragraphGraphicFactory = LineNumberFactory.get(this)
                textProperty().addListener { _, oldValue, newValue ->
                    if (isFileChanged) {
                        isFileChanged = false
                        return@addListener
                    }
                    if (currentEditingFile != null && oldValue.isNotEmpty()) {
                        Files.write(currentEditingFile!!.toPath(), newValue.toByteArray())
                    }
                }
                multiPlainChanges()
                        .successionEnds(ofMillis(100))
                        .subscribe {
                            setStyleSpans(0, updateHighlighting())
                        }
            }


            val popup = Popup()
            val popupMsg = Label()
            popupMsg.style = "-fx-background-color: black;" +
                    "-fx-text-fill: white;" +
                    "-fx-padding: 5;"
            popup.content.add(popupMsg)

            editorArea.mouseOverTextDelay = Duration.ofSeconds(1)
            editorArea.addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_BEGIN) { e ->
                val chIdx = e.characterIndex
                val pos = e.screenPosition
                val pre = editorArea.text.substring(0, chIdx).lastIndexOf(" ")
                val aft = editorArea.text.substring(chIdx).indexOf(" ") + chIdx
                val sub = editorArea.text.substring(pre + 1, aft)
                val pat = Pattern.compile("(?<LOCAL>v\\d+)")
                val mt = pat.matcher(sub)
                if (mt.find()) {

                    val txt = mt.group("LOCAL").substring(1).toInt()
                    val loc = editorArea.text.substring(0, chIdx).lastIndexOf("locals") + "locals ".length
                    val t = editorArea.text.substring(loc).indexOf("\n") + loc
                    val num = editorArea.text.substring(loc, t).toInt()
                    popupMsg.text = "Found local " + mt.group("LOCAL") + ".\n"
                    if (txt < num)
                        popupMsg.text += "Total available locals : $num"
                    else
                        popupMsg.text += "ERROR! Total available locals $num, but current is $txt!"
                    popup.show(editorArea, pos.x, pos.y + 10)
                }
            }
            editorArea.addEventHandler(MouseOverTextEvent.MOUSE_OVER_TEXT_END) { popup.hide() }
        }

        private fun setupMenu() {
            // Menu/File
            home.parentMenu.isMnemonicParsing = true
            home.onAction = EventHandler {
                editorStage.close()
                Launcher().start(Stage())
            }
            settings.onAction = EventHandler {
                Settings(editorStage).show()
            }
            recompile.accelerator = KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN)
            recompile.onAction = EventHandler {
                val dialog = Dialog<Unit>()
                val pane = DialogPane()
                val cont = FXMLLoader(
                        javaClass.getResource(LoadUtils.getLayout("recompile_options.fxml"))).load<VBox>()
                val group = ToggleGroup()
                val builtinFramework = cont.lookup("#builtinFrame") as RadioButton
                val customFramework = cont.lookup("#customFrame") as RadioButton
                val customFrameworkPath = cont.lookup("#-p") as TextField
                val frameworkPathBox = cont.lookup("#framePathBox") as Pane
                builtinFramework.toggleGroup = group
                customFramework.toggleGroup = group
                builtinFramework.selectedProperty().addListener { _, _, newValue ->
                    run {
                        frameworkPathBox.isDisable = newValue
                    }
                }
                builtinFramework.isSelected = true
                val folderPath = cont.lookup("#path") as TextField
                val folderPathSelector = cont.lookup("#pathSelector") as Button
                val framePathSelector = cont.lookup("#customFramePathSelector") as Button
                folderPathSelector.onAction = EventHandler {
                    DirectoryChooser()
                            .showDialog(dialog.owner)
                            ?.absolutePath
                            ?.also {
                                folderPath.text = it
                                folderPath.tooltip.text = it
                            }
                }
                framePathSelector.onAction = EventHandler {
                    DirectoryChooser()
                            .showDialog(dialog.owner)
                            ?.absolutePath
                            ?.also {
                                customFrameworkPath.text = it
                                customFrameworkPath.tooltip.text = it
                            }
                }

                folderPath.text = baseFolder.parent
                folderPath.tooltip = Tooltip(folderPath.text)
                val fileName = cont.lookup("#fileName") as TextField
                fileName.text = baseFolder.name
                fileName.tooltip = Tooltip(fileName.text)
                pane.content = cont
                pane.padding = Insets(10.0, 10.0, 0.0, 10.0)
                dialog.title = "Recompile options"
                dialog.initOwner(editorStage)
                dialog.dialogPane = pane
                val recompileButton = ButtonType("Recompile", ButtonBar.ButtonData.OK_DONE)
                pane.buttonTypes.addAll(
                        recompileButton,
                        ButtonType.CANCEL)
                val selectedOptions = ArrayList<ApktoolCommand>()
                dialog.setResultConverter {
                    if (it == recompileButton) {
                        cont.goThrough(selectedOptions)
                        selectedOptions.add(ApktoolCommand(
                                ApktoolCommand.General.OUTPUT, folderPath.text + "/" +
                                if (fileName.text.endsWith(".apk")) fileName.text else fileName.text + ".apk"))
                        if (customFramework.isSelected)
                            selectedOptions.add(ApktoolCommand(
                                    ApktoolCommand.General.FRAMEWORK_FOLDER_PATH,
                                    customFrameworkPath.text))
                        for (cmd in selectedOptions) {
                            println(cmd.tag)
                        }
                    }
                }
                dialog.showAndWait()
                if (selectedOptions.isNotEmpty()) {
                    val apk = ApkToolUtils.recompile(baseFolder, *selectedOptions.toTypedArray())
                    if (apk == null) {
                        val errorDialog = Dialog<Unit>()
                        errorDialog.initOwner(editorStage)
                        errorDialog.contentText = "An error occurred while recompiling"
                        errorDialog.dialogPane.buttonTypes.add(ButtonType.OK)
                        errorDialog.show()
                    }
                }
            }

            // Menu/Search
            searchMenu.isMnemonicParsing = true
            searchMenu.onShown = EventHandler {
                searchField.requestFocus()
                searchField.positionCaret(searchField.text.length)
                searchField.deselect()
            }
            search.apply {
                isHideOnClick = false
                styleClass.add("custom-menu-item")
                val mainBox = VBox()
                searchField = TextField()
                searchField.textProperty().addListener { _, _, now ->
                    currentSearch = now
                    editorArea.setStyleSpans(0, updateHighlighting())
                    currentSearchCursor = 0
                }
                searchField.onKeyPressed = EventHandler {
                    if (it.code == KeyCode.ENTER) {
                        currentSearchCursor = (currentSearchCursor + 1) % searchSpanList.size
                        editorArea.displaceCaret(searchSpanList[currentSearchCursor].last)
                    }
                }
                searchField.promptText = "Type here..."
                val subBox = HBox()
                mainBox.spacing = 5.0
                subBox.alignment = Pos.BASELINE_CENTER
                subBox.spacing = 2.0
                subBox.background = Background(BackgroundFill(Paint.valueOf("#dedede"), CornerRadii.EMPTY, Insets.EMPTY))
                val prev = Button("Prev")
                prev.prefWidth = 100.0
                prev.onAction = EventHandler {
                    val size = searchSpanList.size
                    if (size == 0) {
                        currentSearchCursor = -1
                        return@EventHandler
                    }
                    currentSearchCursor = (size + currentSearchCursor - 1) % size
                    editorArea.displaceCaret(searchSpanList[currentSearchCursor].last)
                }
                val next = Button("Next")
                next.prefWidth = 100.0
                next.onAction = EventHandler {
                    if (searchSpanList.size == 0) {
                        currentSearchCursor = -1
                        return@EventHandler
                    }
                    currentSearchCursor = (currentSearchCursor + 1) % searchSpanList.size
                    editorArea.displaceCaret(searchSpanList[currentSearchCursor].last)
                }
                subBox.children.addAll(prev, next)
                mainBox.children.addAll(searchField, subBox)
                content = mainBox
                content.hoverProperty().addListener { _, _, now ->
                    if (now) {
                        searchField.requestFocus()
                        searchField.positionCaret(searchField.text.length)
                        searchField.deselect()
                    }
                }
            }

            // Menu/Misc
            examineFile.parentMenu.isMnemonicParsing = true
            examineFile.onAction = EventHandler {
                createFileInfoDialog()
            }
        }

        private fun setupFileExplorerView() {
            if (filesTreeList.root != null)
                return

            filesTreeList.setCellFactory { FileManagerTreeListItem() }

            filesTreeList.onMouseClicked = EventHandler {
                if (it.button === MouseButton.PRIMARY
                        && it.clickCount == 2) {
                    onTreeItemClick(filesTreeList.selectionModel.selectedItem, true)
                }
            }

            filesTreeList.addEventHandler(KeyEvent.KEY_PRESSED) {
                if (it.code == KeyCode.SPACE) {
                    onTreeItemClick(filesTreeList.selectionModel.selectedItem)
                    it.consume()
                }
            }

            filesTreeList.onKeyPressed = EventHandler {
                @Suppress("NON_EXHAUSTIVE_WHEN")
                when (it.code) {
                    KeyCode.ENTER -> onTreeItemClick(filesTreeList.selectionModel.selectedItem)
                    KeyCode.DELETE -> onTreeItemDelete(filesTreeList.selectionModel.selectedItem)
                    KeyCode.LEFT -> {
                        if (it.isAltDown)
                            filesTreeList.selectionModel.selectedItem?.parent?.isExpanded = false
                    }
                    KeyCode.RIGHT -> {
                        if (it.isAltDown)
                            filesTreeList.selectionModel.selectedItem?.isExpanded = true
                    }
                }
            }

            filesTreeList.root = TreeItem(baseFolder)
            addFileExplorerTreeItemChildren(filesTreeList.root)
            filesTreeList.selectionModel.select(0)
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
}

package ru.art2000.androraider

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.geometry.Insets
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.scene.layout.HBox
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import javafx.stage.Window
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory
import org.fxmisc.richtext.model.StyleSpans
import org.fxmisc.richtext.model.StyleSpansBuilder
import java.io.File
import java.io.IOException
import java.nio.file.Files
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
        editorStage.icons.add(LoadUtils.getDrawable("logo.png"))
        editorStage.title = "${baseFolder.name} - Project Editor"
        editorStage.scene = Scene(root, 900.0, 600.0)
        editorStage.scene.stylesheets.add(javaClass.getResource(LoadUtils.getStyle("code.css")).toExternalForm())
    }

    public override fun show() {
        editorStage.show()
    }

    inner class EditorLayoutController {
        @FXML
        lateinit var filesList: ListView<File>
        @FXML
        lateinit var homeButton: Button
        @FXML
        lateinit var upButton: Button
        @FXML
        lateinit var editorArea: CodeArea
        @FXML
        lateinit var upBar: HBox
        @FXML
        lateinit var recompile: MenuItem
        @FXML
        lateinit var home: MenuItem
        @FXML
        lateinit var settings: MenuItem
        @FXML
        lateinit var searchMenu: Menu
        @FXML
        lateinit var search: CustomMenuItem

        private var currentFolder = baseFolder

        private var currentEditingFile: File? = null
            set(value) {
                if (value != null)
                    editorStage.title = "${value.absolutePath.removePrefix(baseFolder.parent + "\\")} - Project Editor"
                isFileChanged = true
                field = value
            }

        private var isFileChanged = false

        private var currentSearch: String? = null

        @Suppress("unused")
        fun initialize() {

            val searchField = TextField()
            searchField.textProperty().addListener { _, _, now ->
                currentSearch = now
                editorArea.setStyleSpans(0, updateHighlighting(currentSearch))
            }
            search.isHideOnClick = false
            search.content = searchField
            searchMenu.onShown = EventHandler {
                search.content.requestFocus()
            }
            editorArea.onKeyPressed = EventHandler {
                if (it.isControlDown && it.code == KeyCode.F) {
                    searchMenu.show()
                    search.content.requestFocus()
                }
            }

            settings.onAction = EventHandler {
                Settings(editorStage).show()
            }
            editorStage.title = "${baseFolder.name} - Project Editor"
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
                    val pathChooser = DirectoryChooser()
                    val chosen = pathChooser.showDialog(dialog.owner)
                    folderPath.text = chosen.absolutePath
                    folderPath.tooltip = Tooltip(folderPath.text)
                }
                framePathSelector.onAction = EventHandler {
                    val pathChooser = DirectoryChooser()
                    val chosen = pathChooser.showDialog(dialog.owner)
                    customFrameworkPath.text = chosen.absolutePath
                    customFrameworkPath.tooltip = Tooltip(customFrameworkPath.text)
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
                        errorDialog.contentText = "An error occurred while decompiling"
                        errorDialog.dialogPane.buttonTypes.add(ButtonType.OK)
                        errorDialog.show()
                    }
                }
            }
            home.onAction = EventHandler {
                editorStage.close()
                Launcher().start(Stage())
            }
            updateDirContent(currentFolder)
            homeButton.onAction = EventHandler {
                updateDirContent(baseFolder)
            }
            upButton.onAction = EventHandler {
                if (currentFolder != baseFolder)
                    updateDirContent(currentFolder.parentFile)
            }
            filesList.prefHeightProperty().bind(editorStage.heightProperty().multiply(1.0))
            filesList.prefWidthProperty().bind(upBar.widthProperty().multiply(1.0))

            editorArea
                    .multiPlainChanges()
                    .successionEnds(ofMillis(100))
                    .subscribe {
                        val spans = updateHighlighting(currentSearch)
                        editorArea.setStyleSpans(0, spans)
                    }

            editorArea.prefWidthProperty().bind(editorStage.widthProperty().subtract(upBar.widthProperty()))
            editorArea.paragraphGraphicFactory = LineNumberFactory.get(editorArea)

            filesList.setCellFactory { FileManagerListItem() }
            filesList.onMouseClicked = EventHandler {
                val newFile = filesList.selectionModel.selectedItem
                if (it.button === MouseButton.PRIMARY
                        && it.clickCount == 2) {
                    if (newFile.isDirectory) {
                        filesList.items.clear()
                        updateDirContent(newFile)
                    } else {
                        if (TypeDetector.isTextFile(newFile.name)) {
                            currentEditingFile = newFile
                            editorArea.replaceText(String(Files.readAllBytes(newFile.toPath())))
                        }
                    }
                }
            }
            filesList.onKeyPressed = EventHandler {
                if (it.code == KeyCode.ENTER) {
                    val newFile = filesList.selectionModel.selectedItem
                    if (newFile.isDirectory) {
                        filesList.items.clear()
                        updateDirContent(newFile)
                    } else {
                        if (TypeDetector.isTextFile(newFile.name)) {
                            currentEditingFile = newFile
                            editorArea.replaceText(String(Files.readAllBytes(newFile.toPath())))
                        }
                    }
                }
                if (it.code == KeyCode.ESCAPE) {
                    if (currentFolder != baseFolder)
                        updateDirContent(currentFolder.parentFile)
                }
            }
            editorArea.textProperty().addListener { _, oldValue, newValue ->

                if (isFileChanged) {
                    isFileChanged = false
                    return@addListener
                }
                if (currentEditingFile != null && oldValue.isNotEmpty()) {
                    Files.write(currentEditingFile!!.toPath(), newValue.toByteArray())
                }
            }
        }

        private fun updateHighlighting(searchString: String?): StyleSpans<Collection<String>> {
            editorArea.clearStyle(0, editorArea.text.length)
            var p = TypeDetector.getPatternForExtension(currentEditingFile?.extension)
            if (searchString != null && searchString.isNotEmpty()) {
                p += if (p.isNotEmpty()) "|" else ""
                p += "(?<SEARCH>$searchString)"
            }
            val pattern = Pattern.compile(p, Pattern.MULTILINE)
            println(pattern.pattern())
            return when (currentEditingFile?.extension) {
                "smali" -> getSmaliHighlighting(pattern)
                else -> getSimpleHighlighting(pattern)
            }.create()
        }

        private fun getSimpleHighlighting(pattern: Pattern): StyleSpansBuilder<Collection<String>> {
            val builder = StyleSpansBuilder<Collection<String>>()
            val matcher = pattern.matcher(editorArea.text)
            var lastKwEnd = 0
            if (pattern.pattern().isNotEmpty()) {
                while (matcher.find()) {
                    val styleClass = (when {
                        matcher.contains("SEARCH") -> "search"
                        else -> return builder
                    })
                    builder.add(emptyList(), matcher.start() - lastKwEnd)
                    builder.add(Collections.singleton(styleClass), matcher.end() - matcher.start())
                    lastKwEnd = matcher.end()
                }
            }
            builder.add(emptyList(), editorArea.text.length - lastKwEnd)
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
                    matcher.contains("SEARCH") -> "search"
                    else -> return builder
                })
                builder.add(emptyList(), matcher.start() - lastKwEnd)
                builder.add(Collections.singleton(styleClass), matcher.end() - matcher.start())
                lastKwEnd = matcher.end()
            }
            builder.add(emptyList(), editorArea.text.length - lastKwEnd)
            return builder
        }

        private fun updateDirContent(file: File) {
            filesList.items.clear()
            val files = file.listFiles()
            if (files == null) {
                println("Error loading files list")
                return
            }
            for (f in files.filter { item -> item.isDirectory && !item.isHidden })
                filesList.items.add(f)
            for (f in files.filter { item -> item.isFile && !item.isHidden })
                filesList.items.add(f)
            currentFolder = file
            filesList.selectionModel.select(0)
        }

    }
}

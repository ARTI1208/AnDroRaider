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
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.time.Duration
import java.util.regex.Pattern
import java.time.Duration.ofMillis
import java.awt.SystemColor.text
import java.util.Collections.emptyList
import org.fxmisc.richtext.model.StyleSpansBuilder
import java.util.*
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
        lateinit var main: HBox

        private var currentFolder = baseFolder

        private var currentEditingFile: File? = null
            set(value) {
                isFileChanged = true
                field = value
            }

        private var isFileChanged = false

        @Suppress("unused")
        fun initialize() {
            settings.onAction = EventHandler {
                Settings(editorStage).show()
            }
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
                    .subscribe { _ -> editorArea.setStyleSpans(0, updateHighlighting() )}


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

        private fun updateHighlighting() : StyleSpans<Collection<String>> {

            editorArea.clearStyle(0, editorArea.text.length)

            val pattern = Pattern.compile("\\b(?<local>v\\d+)\\b|\\b(?<param>p\\d+)\\b")
            val matcher = pattern.matcher(editorArea.text)
            var lastKwEnd = 0
            val spansBuilder = StyleSpansBuilder<Collection<String>>()
            while (matcher.find()) {
                val styleClass = (when {
                    matcher.group("local") != null -> "local"
                    matcher.group("param") != null -> "param"
                    matcher.group("BRACE") != null -> "brace"
                    matcher.group("BRACKET") != null -> "bracket"
                    matcher.group("SEMICOLON") != null -> "semicolon"
                    matcher.group("STRING") != null -> "string"
                    matcher.group("COMMENT") != null -> "comment"
                    else -> null
                })!! /* never happens */
                spansBuilder.add(emptyList(), matcher.start() - lastKwEnd)
                spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start())
                lastKwEnd = matcher.end()
            }
            spansBuilder.add(emptyList(), editorArea.text.length - lastKwEnd)

            return spansBuilder.create()

//            var pattern = Pattern.compile("v\\d+")
//            var matcher = pattern.matcher(newValue)
//
//            while (matcher.find()) {
//                editorArea.setStyleClass(matcher.start(), matcher.end(), "local")
//            }
//
//            pattern = Pattern.compile("p\\d+")
//            matcher = pattern.matcher(newValue)
//            while (matcher.find()) {
//                editorArea.setStyleClass(matcher.start(), matcher.end(), "param")
//            }
        }

        private fun updateDirContent(file: File) {
            filesList.items.clear()
            val files = file.listFiles()
            if (files == null){
                println("Error loading files list")
                return
            }
            for (f in files.filter { item -> item.isDirectory && !item.isHidden })
                filesList.items.add(f)
            for (f in files.filter { item -> item.isFile && !item.isHidden })
                filesList.items.add(f)
            currentFolder = file
            filesList.selectionModel.select(0)
            editorStage.title = "${currentFolder.absolutePath.removePrefix(baseFolder.parent + "\\")} - Project Editor"
        }

    }
}

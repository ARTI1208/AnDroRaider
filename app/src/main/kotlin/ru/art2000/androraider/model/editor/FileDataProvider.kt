package ru.art2000.androraider.model.editor

import javafx.beans.property.Property
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ChangeListener
import ru.art2000.androraider.model.io.DirectoryFileObserver
import ru.art2000.androraider.model.io.FileObserver
import ru.art2000.androraider.utils.getValue
import ru.art2000.androraider.utils.setValue
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardWatchEventKinds
import kotlin.concurrent.thread

class FileDataProvider(val file: File, override val offset: Int = 0) : CodeDataProvider {

    private val fileObserver: FileObserver = DirectoryFileObserver(file).apply { start() }

    override val textProperty: Property<String> = SimpleStringProperty("")
    override var text: String by textProperty

    override val langProperty: Property<String> = SimpleStringProperty(file.extension)
    override var lang: String by langProperty

    init {

        val listener = ChangeListener<String> { _, _, newValue ->
            Files.write(file.toPath(), newValue.toByteArray())
        }

        fun applyNewText(newText: String) {
            textProperty.removeListener(listener)
            text = newText
            textProperty.addListener(listener)
        }

        fun readFileTextAsync() {
            thread {
                val txt = file.readText()
                applyNewText(txt)
            }
        }

        fileObserver.connect {
            if (it != StandardWatchEventKinds.ENTRY_DELETE) {
                readFileTextAsync()
            } else {
                applyNewText("")
            }
        }
    }

    override fun dispose() {
        fileObserver.stop()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileDataProvider

        if (file != other.file) return false

        return true
    }

    override fun hashCode(): Int {
        return file.hashCode()
    }


}
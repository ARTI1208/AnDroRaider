package ru.art2000.androraider.model.editor.project

import java.io.File
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchEvent

@Suppress("RedundantVisibilityModifier")
class DirectoryObserver(private val directory: File) : FileObserver.FileEvent {

    private val fileObservers: MutableMap<String, FileObserver> = mutableMapOf()

    public val onChangeListeners: MutableList<FileObserver.FileEvent> = mutableListOf()

    public fun addListener(func: (File, WatchEvent.Kind<*>) -> Unit) {
        onChangeListeners.add(object : FileObserver.FileEvent {
            override fun onFileEvent(file: File, kind: WatchEvent.Kind<*>) {
                func(file, kind)
            }
        })
    }

    private fun registerObserverOnFile(file: File) {
        if (file.isDirectory) {
            val fileObserver = FileObserver(file)
            fileObserver.onChangeListeners.add(this)
            fileObservers[file.absolutePath] = fileObserver
            fileObserver.start()
        }
    }

    private fun unregisterObserverOnFile(file: File) {
        val filePath = file.absolutePath
        fileObservers[filePath]?.stop()
        fileObservers.remove(filePath)
    }

    public fun start() {
        stop()
        fileObservers.clear()
        directory.walk().forEach {
            registerObserverOnFile(it)
        }
    }

    public fun resume() {
        directory.walk().forEach {
            registerObserverOnFile(it)
        }
    }

    public fun stop() {
        fileObservers.forEach {
            it.value.stop()
        }
    }

    override fun onFileEvent(file: File, kind: WatchEvent.Kind<*>) {
        if (!(file.isDirectory && kind == StandardWatchEventKinds.ENTRY_MODIFY)) {
            onChangeListeners.forEach {
                it.onFileEvent(file, kind)
            }
        }
        when (kind) {
            StandardWatchEventKinds.ENTRY_DELETE -> unregisterObserverOnFile(file)
            StandardWatchEventKinds.ENTRY_CREATE -> registerObserverOnFile(file)
        }
    }
}
package ru.art2000.androraider.model.editor.project

import javafx.application.Platform
import java.io.File
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchEvent
import java.util.concurrent.atomic.AtomicBoolean

class FileObserver(private val file: File) {

    @FunctionalInterface
    interface FileEvent {
        fun onFileEvent(file: File, kind: WatchEvent.Kind<*>)
    }

    private var shouldStop = AtomicBoolean(false)

    private var isRunning = AtomicBoolean(false)

    val onChangeListeners: MutableList<FileEvent> = mutableListOf()

    fun addListener(func: (File, WatchEvent.Kind<*>) -> Unit) {
        onChangeListeners.add(object : FileEvent {
            override fun onFileEvent(file: File, kind: WatchEvent.Kind<*>) {
                func(file, kind)
            }
        })
    }

    fun start() {
        if (isRunning.get())
            return

        val thread = Thread {
            val path = file.toPath()
            val watcher = FileSystems.getDefault().newWatchService()
            val watchKey = path.register(watcher,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE)

            isRunning.set(true)
            while (true) {
                val key = watcher.take()

                if (shouldStop.get())
                    break

                for (event in key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
                        continue
                    }
                    val context = event.context()
                    if (context is Path) {
                        Platform.runLater {
                            onChangeListeners.forEach {
                                it.onFileEvent(path.resolve(context).toFile(), event.kind())
                            }
                        }
                    }

                }

                if (!key.reset()) {
                    break
                }
            }
            shouldStop.set(false)
            isRunning.set(false)
            watchKey.cancel()
        }

        thread.isDaemon = true
        thread.start()
    }

    fun stop() {
        if (isRunning.get()) {
            shouldStop.set(true)
        }
    }
}
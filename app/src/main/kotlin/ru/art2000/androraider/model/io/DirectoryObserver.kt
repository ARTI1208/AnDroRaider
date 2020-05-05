package ru.art2000.androraider.model.io

import javafx.application.Platform
import java.io.File
import java.lang.Exception
import java.nio.file.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

@Suppress("RedundantVisibilityModifier")
class DirectoryObserver(directory: File) {

    private val onChangeListeners: MutableList<(File, WatchEvent.Kind<*>) -> Unit> = mutableListOf()

    private var shouldStop = AtomicBoolean(false)

    private var isRunning = AtomicBoolean(false)

    private val watcher = FileSystems.getDefault().newWatchService()

    private val keys = mutableListOf<WatchKey>()

    init {
        directory.walk().forEach {
            if (it.isDirectory) {
                keys.add(register(it))
            }
        }
    }

    public fun addListener(func: (File, WatchEvent.Kind<*>) -> Unit) {
        onChangeListeners.add(func)
    }

    public fun start() {
        if (isRunning.get()) {
            return
        }

        thread {
            isRunning.set(true)
            while (true) {
                val key = try {
                    watcher.take()
                } catch (_: Exception) {
                    break
                }
                if (shouldStop.get()) {
                    key.reset()
                    break
                }

                Thread.sleep(50)

                for (event in key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.OVERFLOW) {
                        key.reset()
                        continue
                    }
                    val dirPath = key.watchable()
                    val filePath = event.context()
                    if (dirPath is Path && filePath is Path) {
                        Platform.runLater {
                            handleFileEvent(dirPath.resolve(filePath).toAbsolutePath().toFile(), event.kind(), key)
                        }
                    }

                }

                key.reset()
            }
            shouldStop.set(false)
            isRunning.set(false)
        }

    }

    private fun register(file: File): WatchKey {
        return file.toPath().register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE)
    }

    public fun stop() {
        shouldStop.set(true)
        keys.forEach { it.cancel() }
        watcher.close()
    }

    private fun handleFileEvent(file: File, kind: WatchEvent.Kind<*>, key: WatchKey) {
        if (!(file.isDirectory && kind == StandardWatchEventKinds.ENTRY_MODIFY)) {
            onChangeListeners.forEach {
                it(file, kind)
            }
        }
        when {
            file.isDirectory && kind == StandardWatchEventKinds.ENTRY_DELETE -> keys.removeIf { it == key }
            file.isDirectory && kind == StandardWatchEventKinds.ENTRY_CREATE -> keys.add(register(file))
        }
    }
}
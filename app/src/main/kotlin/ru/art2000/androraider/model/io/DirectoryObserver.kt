package ru.art2000.androraider.model.io

import javafx.application.Platform
import org.reactfx.Subscription
import java.io.File
import java.lang.Exception
import java.nio.file.*
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

@Suppress("RedundantVisibilityModifier")
class DirectoryObserver(val directory: File) {

    private val directoryEventListeners: MutableList<(File, WatchEvent.Kind<*>) -> Unit> = mutableListOf()

    private val fileEventListeners: MutableMap<File, MutableSet<DirectoryBasedFileObserver>> = mutableMapOf()

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

    public fun observe(func: (File, WatchEvent.Kind<*>) -> Unit): Subscription {
        addListener(func)
        return Subscription { removeListener(func) }
    }

    public fun addListener(func: (File, WatchEvent.Kind<*>) -> Unit) {
        directoryEventListeners.add(func)
    }

    public fun removeListener(func: (File, WatchEvent.Kind<*>) -> Unit) {
        directoryEventListeners.remove(func)
    }

    private val hasListeners: Boolean
        get() = directoryEventListeners.isNotEmpty() || fileEventListeners.flatMap { it.value }.isNotEmpty()

    public fun start() {
        if (isRunning.get() || !hasListeners) {
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
            fileEventListeners[file]?.forEach { it.notifyListeners(kind) }
            directoryEventListeners.forEach {
                it(file, kind)
            }
        }
        when {
            file.isDirectory && kind == StandardWatchEventKinds.ENTRY_DELETE -> keys.removeIf { it == key }
            file.isDirectory && kind == StandardWatchEventKinds.ENTRY_CREATE -> keys.add(register(file))
        }
    }

    public fun getFileObserver(file: File): FileObserver {
        return DirectoryBasedFileObserver(file).also {
            fileEventListeners.getOrPut(file) { mutableSetOf() }.add(it)
        }
    }

    private inner class DirectoryBasedFileObserver(file: File): FileObserver(file) {

        private val listeners = mutableSetOf<(WatchEvent.Kind<*>) -> Unit>()

        fun notifyListeners(event: WatchEvent.Kind<*>) {
            listeners.forEach { it(event) }
        }

        override fun addListener(func: (WatchEvent.Kind<*>) -> Unit) {
            listeners.add(func)
        }

        override fun removeListener(func: (WatchEvent.Kind<*>) -> Unit) {
            listeners.remove(func)
        }

        override fun start() {
            fileEventListeners.getOrPut(file) { mutableSetOf() }.add(this)
            this@DirectoryObserver.start()
        }

        override fun stop() {
            fileEventListeners[file]?.remove(this)
            if (!this@DirectoryObserver.hasListeners) {
                this@DirectoryObserver.stop()
            }
        }

    }
}
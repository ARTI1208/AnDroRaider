package ru.art2000.androraider.model.io

import java.io.File
import java.nio.file.WatchEvent

class DirectoryFileObserver(file: File) : FileObserver(file) {

    private val fileObserverImpl = DirectoryObserver(file.parentFile).getFileObserver(file)

    override fun addListener(func: (WatchEvent.Kind<*>) -> Unit) {
        fileObserverImpl.addListener(func)
    }

    override fun removeListener(func: (WatchEvent.Kind<*>) -> Unit) {
        fileObserverImpl.removeListener(func)
    }

    override fun start() {
        fileObserverImpl.start()
    }

    override fun stop() {
        fileObserverImpl.stop()
    }

}
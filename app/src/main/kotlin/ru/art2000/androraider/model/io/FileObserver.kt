package ru.art2000.androraider.model.io

import org.reactfx.Subscription
import java.io.File
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchEvent

abstract class FileObserver(val file: File) {

    public abstract fun start()

    public abstract fun stop()

    public abstract fun addListener(func: (WatchEvent.Kind<*>) -> Unit)

    public abstract fun removeListener(func: (WatchEvent.Kind<*>) -> Unit)

    public fun observe(func: (WatchEvent.Kind<*>) -> Unit): Subscription {
        addListener(func)
        return Subscription { removeListener(func) }
    }

    public fun connect(func: (WatchEvent.Kind<*>) -> Unit): Subscription {
        val kind = if (file.exists())
            StandardWatchEventKinds.ENTRY_CREATE
        else
            StandardWatchEventKinds.ENTRY_DELETE

        func(kind)
        return observe(func)
    }
}
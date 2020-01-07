package ru.art2000.androraider

import javafx.application.Platform

class ThreadHelper {

    private val unitedRunnables = mutableListOf<Pair<Runnable, Boolean>>()

    public fun runOnWorkerThread(runnable: Runnable): ThreadHelper {
        unitedRunnables.add(runnable to true)
        return this
    }

    public fun runOnFxThread(runnable: Runnable): ThreadHelper {
        unitedRunnables.add(runnable to false)
        return this
    }

    public fun start() {
        val unitedCopy = unitedRunnables.toList()
        unitedRunnables.clear()
        Thread {
            unitedCopy.forEach {
                if (it.second) {
                    it.first.run()
                } else {
                    Platform.runLater {
                        it.first.run()
                    }
                }
            }

        }.start()
    }

}
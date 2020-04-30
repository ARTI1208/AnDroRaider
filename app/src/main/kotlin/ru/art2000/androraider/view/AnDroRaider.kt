package ru.art2000.androraider.view

import javafx.application.Application
import javafx.stage.Stage
import ru.art2000.androraider.view.launcher.Launcher
import kotlin.system.measureTimeMillis

class AnDroRaider : Application() {

    companion object {
        fun main(args: Array<String>) {
            launch(AnDroRaider::class.java, *args)
        }
    }

    override fun start(primaryStage: Stage) {
        Launcher().show()
    }
}
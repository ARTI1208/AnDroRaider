package ru.art2000.androraider.view

import javafx.application.Application
import javafx.stage.Stage
import org.fxmisc.richtext.StyleClassedTextField
import ru.art2000.androraider.utils.getStyle
import ru.art2000.androraider.view.launcher.Launcher

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
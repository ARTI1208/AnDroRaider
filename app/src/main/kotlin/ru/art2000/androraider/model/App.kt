package ru.art2000.androraider.model

import javafx.scene.Node
import javafx.stage.Window
import ru.art2000.androraider.model.io.DefaultStreamOutput
import ru.art2000.androraider.model.io.StreamOutput
import ru.art2000.androraider.utils.getDrawable

object App {
    const val VERSION = "0.1"
    const val NAME = "AnDroRaider"
    const val RELEASE_TYPE = "BETA"
    val LOGO = javaClass.getDrawable("logo.png")

    var currentStreamOutput: StreamOutput = DefaultStreamOutput
}
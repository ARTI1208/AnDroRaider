package ru.art2000.androraider.windows.editor

import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import ru.art2000.androraider.StreamOutput
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class ConsoleView : ScrollPane(), StreamOutput {

    private val text = Label()

    init {
        text.isWrapText = true
        children.add(text)
        content = text
        padding = Insets(5.0)
    }

    override fun writeln(tag: String, string: String) {
        if (Platform.isFxApplicationThread()) {
            text.text += "$tag: $string\n"
        } else {
            Platform.runLater {
                text.text += "$tag: $string\n"
            }
        }
    }

    override fun startOutput(tag: String, vararg inputStream: InputStream) {
        inputStream.forEach {
            Thread {
                try {
                    BufferedReader(InputStreamReader(it)).use { r ->
                        r.lineSequence().forEach {
                            writeln(tag, it)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }

    override fun stopOutput(vararg inputStream: InputStream) {

    }

//    override fun totalWidthEstimateProperty(): Val<Double> {
//
//    }
//
//    override fun scrollYBy(deltaY: Double) {
//
//    }
//
//    override fun estimatedScrollYProperty(): Var<Double> {
//
//    }
//
//    override fun scrollXBy(deltaX: Double) {
//
//    }
//
//    override fun scrollXToPixel(pixel: Double) {
//
//    }
//
//    override fun totalHeightEstimateProperty(): Val<Double> {
//
//    }
//
//    override fun scrollYToPixel(pixel: Double) {
//
//    }
//
//    override fun estimatedScrollXProperty(): Var<Double> {
//
//    }

}
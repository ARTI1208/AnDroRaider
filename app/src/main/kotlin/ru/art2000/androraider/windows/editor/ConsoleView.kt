package ru.art2000.androraider.windows.editor

import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import ru.art2000.androraider.StreamOutput
import java.io.*
import java.lang.StringBuilder

class ConsoleView : ScrollPane(), StreamOutput {

    private val text = Label()

    private val outStream: OutputStream

    private val stringBuilder = StringBuilder()

    init {
        text.isWrapText = true
        children.add(text)
        content = text
        padding = Insets(5.0)
        outStream = object : OutputStream() {
            override fun write(b: Int) {
                val c = b.toChar()
                stringBuilder.append(c)
                if (c == '\n' || stringBuilder.length >= 100) {
                    if (Platform.isFxApplicationThread()) {
                        text.text += stringBuilder.toString()
                        stringBuilder.clear()
                    } else {
                        Platform.runLater {
                            text.text += stringBuilder.toString()
                            stringBuilder.clear()
                        }
                    }
                }
            }
        }
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

    override fun getOutputStream(): OutputStream {
        return outStream
    }

    override fun getErrorStream(): OutputStream {
        return outStream
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
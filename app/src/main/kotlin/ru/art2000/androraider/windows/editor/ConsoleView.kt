package ru.art2000.androraider.windows.editor

import javafx.application.Platform
import javafx.geometry.Insets
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextArea
import ru.art2000.androraider.StreamOutput
import java.io.*
import java.lang.StringBuilder

class ConsoleView : ScrollPane(), StreamOutput {

    private val text = TextArea().apply { isEditable = false }

    private val outStream: OutputStream

    private val bufferedOutStream: BufferedOutputStream

    private val stringBuilder = StringBuilder()

    init {
        children.add(text)
        content = text
        padding = Insets(5.0)
        isFitToHeight = true
        isFitToWidth = true
        outStream = object : OutputStream() {
            override fun write(b: Int) {
                val c = b.toChar()
                stringBuilder.append(c)
                if (c == '\n' || stringBuilder.length >= 100) {
                    if (Platform.isFxApplicationThread()) {
                        text.appendText(stringBuilder.toString())
                        stringBuilder.clear()
                    } else {
                        Platform.runLater {
                            text.appendText(stringBuilder.toString())
                            stringBuilder.clear()
                        }
                    }
                }
            }

            override fun write(b: ByteArray) {
                stringBuilder.append(b.map { it.toChar() }.toCharArray())
                if (stringBuilder.length >= 100) {
                    if (Platform.isFxApplicationThread()) {
                        text.appendText(stringBuilder.toString())
                        stringBuilder.clear()
                    } else {
                        Platform.runLater {
                            text.appendText(stringBuilder.toString())
                            stringBuilder.clear()
                        }
                    }
                }
            }

            override fun write(b: ByteArray, off: Int, len: Int) {
                val array = b.drop(off).take(len).map { it.toChar() }.joinToString(separator = "")
                if (Platform.isFxApplicationThread()) {
                    text.appendText(array)
                } else {
                    Platform.runLater {
                        text.appendText(array)
                    }
                }
            }

            override fun flush() {
                if (Platform.isFxApplicationThread()) {
                    text.appendText("flush\n")
                    text.appendText(stringBuilder.toString())
                    stringBuilder.clear()
                } else {
                    Platform.runLater {
                        text.appendText("flush\n")
                        text.appendText(stringBuilder.toString())
                        stringBuilder.clear()
                    }
                }
            }
        }
        bufferedOutStream = BufferedOutputStream(outStream)
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
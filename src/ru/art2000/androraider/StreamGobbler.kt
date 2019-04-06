package ru.art2000.androraider

import javafx.application.Platform
import javafx.scene.text.Text
import java.io.*

class StreamGobbler(private val process : Process, private val text: Text) : Thread() {

    private val builder = StringBuilder(text.text)

    private fun Text.appendText(s : String){
        this.text = builder.append("\n").append(s).toString()
    }

    override fun run() {
        val pOut = process.inputStream
        val pErr = process.errorStream
        Thread{
            try {
                BufferedReader(InputStreamReader(pOut)).use { r ->
                    r.lineSequence().forEach {
                        Platform.runLater {
                            text.appendText(it)
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()

        Thread{
            try {
                BufferedReader(InputStreamReader(pErr)).use { r ->
                    r.lineSequence().forEach {
                        Platform.runLater {
                            text.appendText(it)
                        }
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()

    }
}

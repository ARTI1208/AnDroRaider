package ru.art2000.androraider.model.io

import java.io.*

object DefaultStreamOutput : StreamOutput {
    private val systemErrorStream: PrintStream = System.err
    private val systemOutputStream: PrintStream = System.out

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

    override fun writeln(tag: String, string: String) {
        systemOutputStream.println("$tag: $string")
    }

    override fun getOutputStream() = systemOutputStream

    override fun getErrorStream() = systemErrorStream
}
package ru.art2000.androraider.model.io

import java.io.InputStream
import java.io.OutputStream

interface StreamOutput {

    fun startOutput(tag: String, vararg inputStream: InputStream)

    fun stopOutput(vararg inputStream: InputStream)

    fun writeln(tag: String, string: String)
}
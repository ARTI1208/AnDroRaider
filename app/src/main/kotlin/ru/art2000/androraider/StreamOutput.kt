package ru.art2000.androraider

import java.io.InputStream

interface StreamOutput {

    fun startOutput(tag: String, vararg inputStream: InputStream)

    fun stopOutput(vararg inputStream: InputStream)

    fun writeln(tag: String, string: String)
}
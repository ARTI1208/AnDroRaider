package ru.art2000.androraider.utils

import javafx.fxml.FXMLLoader
import javafx.scene.image.Image
import java.io.File

fun File.isSubFile(folder: File, canMatch: Boolean = false): Boolean {
    if (!folder.isDirectory || !folder.exists() || !exists())
        return false

    val canonical = canonicalFile
    val base = folder.canonicalFile

    if (!canMatch && this == folder) {
        return false
    }

    var parentFile: File? = if (canMatch) canonical else canonical.parentFile
    while (parentFile != null) {
        if (base == parentFile) {
            return true
        }

        parentFile = parentFile.parentFile
    }

    return false
}

fun Class<*>.getDrawable(name: String): Image? {
    var image: Image? = null
    try {
        image = Image(getResource(DRAWABLE_PATH + name).toString())
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        return image
    }
}

fun Class<*>.getLayout(name: String): FXMLLoader {
    return FXMLLoader(getResource(LAYOUT_PATH + name))
}

fun Class<*>.getStyle(name: String): String {
    return getResource(STYLE_PATH + name).toString()
}

fun Class<*>.getRawContent(name: String): ByteArray {
    val stream = getResource(RAW_PATH + name)?.openStream() ?: return ByteArray(0)
    val res = stream.readAllBytes()
    stream.close()
    return res
}

const val DRAWABLE_PATH = "/drawable/"
const val LAYOUT_PATH = "/layout/"
const val STYLE_PATH = "/style/"
const val RAW_PATH = "/raw/"
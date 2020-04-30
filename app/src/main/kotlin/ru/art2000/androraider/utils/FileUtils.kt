package ru.art2000.androraider.utils

import javafx.fxml.FXMLLoader
import javafx.scene.image.Image
import java.io.File

fun File?.relativeTo(folder: File?): String? {
    if (this == null || folder == null)
        return null

    return absolutePath.removePrefix(folder.parent + File.separator)
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

const val DRAWABLE_PATH = "/drawable/"
const val LAYOUT_PATH = "/layout/"
const val STYLE_PATH = "/style/"
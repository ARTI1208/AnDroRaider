package ru.art2000.androraider

import javafx.fxml.FXMLLoader
import javafx.scene.image.Image


fun Class<*>.getDrawable(name: String): Image? {
    var image: Image? = null
    try {
        image = Image(getResource(LoadUtils.DRAWABLE_PATH + name).toString())
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        return image
    }
}

fun Class<*>.getLayout(name: String): FXMLLoader {
    return FXMLLoader(getResource(LoadUtils.LAYOUT_PATH + name))
}

fun Class<*>.getStyle(name: String): String {
    return getResource(LoadUtils.STYLE_PATH + name).toString()
}

class LoadUtils {

    companion object {

        @JvmStatic
        val DRAWABLE_PATH = "/drawable/"

        @JvmStatic
        val LAYOUT_PATH = "/layout/"

        @JvmStatic
        val STYLE_PATH = "/style/"
    }
}
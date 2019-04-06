package ru.art2000.androraider

import javafx.scene.image.Image

class LoadUtils {

    companion object {

        @JvmStatic
        private val DRAWABLE_PATH = "/resources/drawable/"

        @JvmStatic
        private val LAYOUT_PATH = "/resources/layout/"

        @JvmStatic
        fun getDrawable(_name : String): Image {
            return Image(DRAWABLE_PATH + _name)
        }

        @JvmStatic
        fun getLayout(_name : String): String {
            return LAYOUT_PATH + _name
        }

    }


}
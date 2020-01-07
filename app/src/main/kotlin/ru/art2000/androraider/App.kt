package ru.art2000.androraider

import javafx.fxml.FXMLLoader
import javafx.scene.image.Image
import ru.art2000.androraider.utils.getDrawable
import ru.art2000.androraider.utils.getLayout
import ru.art2000.androraider.utils.getStyle

class App {
    companion object {
        public const val VERSION = "0.1"
        public const val NAME = "AnDroRaider"
        public const val RELEASE_TYPE = "BETA"
        public val LOGO = getDrawable("logo.png")

        public fun getDrawable(name: String): Image? = ::App.javaClass.getDrawable(name)

        public fun getLayout(name: String): FXMLLoader = ::App.javaClass.getLayout(name)

        public fun getStyle(name: String): String = ::App.javaClass.getStyle(name)
    }
}
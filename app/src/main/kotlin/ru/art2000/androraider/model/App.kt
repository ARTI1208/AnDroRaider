package ru.art2000.androraider.model

import ru.art2000.androraider.utils.getDrawable

object App {
    const val VERSION = "0.1"
    const val NAME = "AnDroRaider"
    const val RELEASE_TYPE = "BETA"
    val LOGO = javaClass.getDrawable("logo.png")
}
package ru.art2000.androraider.model

import ru.art2000.androraider.utils.getDrawable
import java.util.*
import ru.art2000.properties.*

object App {

    private val appProperties: Properties by lazy {

        val properties = Properties()

        javaClass.getResourceAsStream("/property/app.properties")?.use {
            properties.load(it)
        }

        properties
    }

    val VERSION = fullVersion(appProperties)
    val NAME: String = appProperties.getProperty("name")
    val RELEASE_TYPE: String = (appProperties.getProperty("type") ?: "").toUpperCase()
    val LOGO = javaClass.getDrawable("logo.png")
}
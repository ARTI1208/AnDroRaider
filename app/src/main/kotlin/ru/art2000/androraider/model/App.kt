package ru.art2000.androraider.model

import ru.art2000.androraider.utils.getDrawable
import java.util.*

object App {

    private val appProperties: Properties by lazy {

        val properties = Properties()

        javaClass.getResourceAsStream("/property/app.properties")?.use {
            properties.load(it)
        }

        properties
    }

    private fun Properties.intProperty(prop: String): Int {
        val value = getProperty(prop) ?: throw IllegalStateException("missing property '$prop'")
        return value.toIntOrNull() ?: throw IllegalStateException("not integer property '$prop'")
    }

    private fun Properties.shortVersion(): String {
        val major = intProperty("major")
        val minor = intProperty("minor")
        val patch = intProperty("patch")

        return if (patch == 0) "$major.$minor" else "$major.$minor.$patch"
    }

    val VERSION = appProperties.shortVersion()
    val NAME: String = appProperties.getProperty("name")
    val RELEASE_TYPE: String = (appProperties.getProperty("type") ?: "").toUpperCase()
    val LOGO = javaClass.getDrawable("logo.png")
}
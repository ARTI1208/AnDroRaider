package ru.art2000.packaging

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.util.*

object PropertiesHelper {

    inline val propertiesFileName: String
        get() = "build.properties"

    private inline val configDirName: String
        get() = "config"

    private fun File.properties(): Properties = Properties().also {
        it.load(FileInputStream(this))
    }

    private fun File.properties(defaultProperties: Properties): Properties = Properties(defaultProperties).also {
        it.load(FileInputStream(this))
    }

    fun loadBaseProperties(project: Project): Properties {
        val configDir = project.rootDir.resolve(configDirName)

        return configDir.resolve(propertiesFileName).properties()
    }

    fun loadBuildProperties(project: Project, distro: Distro?): Properties {
        val configDir = project.rootDir.resolve(configDirName)

        var properties = configDir.resolve(propertiesFileName).properties()

        if (distro == null) return properties

        val osConfigDir = configDir.resolve(distro.operatingSystem.configDirName)
        return if (osConfigDir.exists()) {
            properties = osConfigDir.resolve(propertiesFileName).properties(properties)
            loadDistroProperties(osConfigDir, properties, distro)
        } else {
            properties
        }
    }

    private fun loadDistroProperties(osConfigDir: File, currentProperties: Properties, distro: Distro): Properties {

        val distroDirName = distro.configDirName ?: return currentProperties

        val distroPropertiesFile = osConfigDir.resolve(distroDirName).resolve(propertiesFileName)
        return if (distroPropertiesFile.exists()) {
            distroPropertiesFile.properties(currentProperties)
        } else {
            currentProperties
        }
    }

}
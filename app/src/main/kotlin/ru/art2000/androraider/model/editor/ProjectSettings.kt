package ru.art2000.androraider.model.editor

import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.model.settings.SettingsManager
import java.io.File
import java.io.FileInputStream
import java.io.FileWriter
import java.util.*


class ProjectSettings(projectFolder: File, private val globalSettings: PreferenceManager): PreferenceManager {

    private val settingsFile = File(projectFolder, SETTINGS_PATH)

    private val properties = Properties()

    companion object {
        private val SETTINGS_PATH = ".androraider" + File.separator + "settings"
    }

    init {
        if (settingsFile.exists()) {
            FileInputStream(settingsFile).use {
                properties.load(it)
            }
        } else {
            settingsFile.parentFile.mkdirs()
        }
    }

    private fun saveToFile() {
        FileWriter(settingsFile).use {
            properties.store(it, null)
        }
    }

    override fun remove(key: String) {
        properties.remove(key)
        saveToFile()
    }

    override fun getString(key: String, default: String): String {
        return properties.getProperty(key, null) ?: globalSettings.getString(key, default)
    }

    override fun putBoolean(key: String, value: Boolean) {
        if (getBoolean(key) != value) {
            properties.setProperty(key, value.toString())
            saveToFile()
        }
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return properties.getProperty(key)?.toBoolean() ?: default
    }

    public override fun putString(key: String, value: String) {
        if (getString(key) != value) {
            properties.setProperty(key, value)
            saveToFile()
        }
    }

    override fun putStringArray(key: String, array: Collection<String>) {
        require(array.all { !it.contains("|") }) { "Can't save strings with \"|\"" }
        properties.setProperty(key, array.joinToString("|"))
        saveToFile()
    }

    override fun getStringArray(key: String, defaultArray: List<String>): List<String> {
        return properties.getProperty(key)?.split("|") ?: globalSettings.getStringArray(key, defaultArray)
    }
}
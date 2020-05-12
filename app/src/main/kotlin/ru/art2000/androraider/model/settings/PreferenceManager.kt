package ru.art2000.androraider.model.settings

interface PreferenceManager {

    fun remove(key: String)

    fun putString(key: String, value: String)

    fun getString(key: String, default: String = ""): String

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, default: Boolean = false): Boolean

    fun putStringArray(key: String, array: Collection<String>)

    fun getStringArray(key: String, defaultArray: List<String> = emptyList()): List<String>

    fun addRemoveListener(key: String, listener: Runnable)
}
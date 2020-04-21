package ru.art2000.androraider.model.settings

interface PreferenceManager {

    public fun remove(key: String)

    fun putString(key: String, value: String)

    fun getString(key: String, default: String? = null): String?


    fun putStringArray(key: String, array: Collection<String>)

    fun getStringArray(key: String, defaultArray: List<String> = emptyList()): List<String>
}
package ru.art2000.androraider.model.settings

import java.util.prefs.Preferences

public class SettingsManager(forClass: Class<*>) : PreferenceManager {

    private val preferences = Preferences.userNodeForPackage(forClass)

    public override fun remove(key: String) {
        preferences.remove(key)
    }

    override fun putString(key: String, value: String) {
        preferences.put(key, value)
    }

    override fun getString(key: String, default: String?): String? {
        return preferences.get(key, default)
    }


    override fun putStringArray(key: String, array: Collection<String>) {
        val stringBuilder = StringBuilder()
        for ((i, s) in array.withIndex()) {
            stringBuilder.append(s)
            if (i != array.size - 1)
                stringBuilder.append("|")
        }
        preferences.put(key, stringBuilder.toString())
    }

    override fun getStringArray(key: String, defaultArray: List<String>): List<String> {
        val array = preferences.get(key, "")
        return if (array != "") array.split("|") else defaultArray
    }


}
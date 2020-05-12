package ru.art2000.androraider.presenter.settings

import javafx.beans.property.StringProperty
import javafx.beans.property.StringPropertyBase
import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.model.settings.SettingsManager
import ru.art2000.androraider.mvp.IPresenter
import ru.art2000.androraider.presenter.launcher.LauncherPresenter
import ru.art2000.androraider.view.settings.Settings

class SettingsPresenter : IPresenter, PreferenceManager by prefs {

    companion object {
        const val KEY_APKTOOL_PATH = "apktool_path"
        const val KEY_FRAMEWORK_PATH = "framework_path"

        val prefs = SettingsManager(Settings::class.java)
    }

    val apktoolPathProperty : StringProperty = object : StringPropertyBase() {
        init {
            set(prefs.getString(name))
        }

        override fun getName(): String {
            return KEY_APKTOOL_PATH
        }

        override fun getBean(): Any {
            return this
        }

        override fun set(newValue: String) {
            super.set(newValue)
            prefs.putString(name, newValue)
        }
    }

    val frameworkPathProperty: StringProperty = object : StringPropertyBase() {
        init {
            set(prefs.getString(name))
        }

        override fun getName(): String {
            return KEY_FRAMEWORK_PATH
        }

        override fun getBean(): Any {
            return this
        }

        override fun set(newValue: String) {
            super.set(newValue)
            prefs.putString(name, newValue)
        }
    }

    fun clearData() {
        prefs.remove(LauncherPresenter.KEY_RECENTS)
        prefs.remove(KEY_APKTOOL_PATH)
        prefs.remove(KEY_FRAMEWORK_PATH)
    }
}
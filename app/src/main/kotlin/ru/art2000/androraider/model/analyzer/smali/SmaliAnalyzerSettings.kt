package ru.art2000.androraider.model.analyzer.smali

import ru.art2000.androraider.model.analyzer.AnalyzeMode
import ru.art2000.androraider.model.analyzer.AnalyzerSettings
import ru.art2000.androraider.model.settings.PreferenceManager

class SmaliAnalyzerSettings(override val mode: AnalyzeMode = AnalyzeMode.FULL) : AnalyzerSettings {

    var childrenBeforeAction = true

    companion object {
        private const val UPDATE_REGISTER_VALUE_ON_NEXT_USAGE = "update_register_value_next_time"

        fun fromPreferenceManager(preferenceManager: PreferenceManager): SmaliAnalyzerSettings {
            val settings = SmaliAnalyzerSettings()

            settings.childrenBeforeAction = preferenceManager.getBoolean(UPDATE_REGISTER_VALUE_ON_NEXT_USAGE, true)

            return settings
        }
    }

}
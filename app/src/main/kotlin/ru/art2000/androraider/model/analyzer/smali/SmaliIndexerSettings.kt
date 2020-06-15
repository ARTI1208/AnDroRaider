package ru.art2000.androraider.model.analyzer.smali

import ru.art2000.androraider.model.analyzer.IndexerSettings
import ru.art2000.androraider.model.settings.PreferenceManager

class SmaliIndexerSettings: IndexerSettings {
    var withRanges = false

    var childrenBeforeAction = true

    companion object {
        private const val UPDATE_REGISTER_VALUE_ON_NEXT_USAGE = "update_register_value_next_time"

        fun fromPreferenceManager(preferenceManager: PreferenceManager): SmaliIndexerSettings {
            val settings = SmaliIndexerSettings()

            settings.childrenBeforeAction = preferenceManager.getBoolean(UPDATE_REGISTER_VALUE_ON_NEXT_USAGE, true)

            return settings
        }
    }
}
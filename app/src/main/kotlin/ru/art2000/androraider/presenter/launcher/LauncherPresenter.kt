package ru.art2000.androraider.presenter.launcher

import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import ru.art2000.androraider.model.launcher.RecentProject
import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.arch.IPresenter
import ru.art2000.androraider.presenter.settings.SettingsPresenter

class LauncherPresenter : IPresenter, PreferenceManager by prefs {

    companion object {
        const val KEY_RECENTS = "recent_projects"

        private val prefs = SettingsPresenter.prefs
    }

    val recentsItems: ObservableList<RecentProject> = FXCollections.observableArrayList()

    init {
        recentsItems.addAll(prefs.getStringArray(KEY_RECENTS).map { RecentProject(it) })
        recentsItems.addListener(ListChangeListener {
            prefs.putStringArray(KEY_RECENTS, recentsItems.map { it.projectLocation })
        })
        prefs.addRemoveListener(KEY_RECENTS, Runnable {
            recentsItems.clear()
        })
    }

    fun openProject(project: RecentProject) {
        if (project in recentsItems) {
            recentsItems.remove(project)
        }

        recentsItems.add(0, project)
    }
}
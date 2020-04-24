package ru.art2000.androraider.presenter.launcher

import javafx.collections.FXCollections
import javafx.collections.ListChangeListener
import javafx.collections.ObservableList
import ru.art2000.androraider.model.launcher.RecentProject
import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.model.settings.SettingsManager
import ru.art2000.androraider.mvp.IPresenter
import ru.art2000.androraider.view.launcher.Launcher

class LauncherPresenter : IPresenter, PreferenceManager by prefs {

    companion object {
        const val KEY_RECENTS = "recent_projects"

        private val prefs = SettingsManager(Launcher::class.java)
    }

    val recentsItems: ObservableList<RecentProject> = FXCollections.observableArrayList()

    init {
        recentsItems.addAll(prefs.getStringArray(KEY_RECENTS).map { RecentProject(it) })
        recentsItems.addListener(ListChangeListener {
            prefs.putStringArray(KEY_RECENTS, recentsItems.map { it.projectLocation })
        })
    }

    fun openProject(project: RecentProject) {
        if (project in recentsItems) {
            recentsItems.remove(project)
        }

        recentsItems.add(0, project)
    }
}
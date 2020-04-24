package ru.art2000.androraider.view.settings

import ru.art2000.androraider.mvp.IView
import ru.art2000.androraider.presenter.settings.SettingsPresenter

interface ISettingsView : IView {
    override val presenter: SettingsPresenter
}
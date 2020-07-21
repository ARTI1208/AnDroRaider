package ru.art2000.androraider.view.settings

import ru.art2000.androraider.arch.IView
import ru.art2000.androraider.presenter.settings.SettingsPresenter

interface ISettingsView : IView {
    override val presenter: SettingsPresenter
}
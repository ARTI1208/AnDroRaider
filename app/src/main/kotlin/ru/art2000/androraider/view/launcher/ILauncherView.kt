package ru.art2000.androraider.view.launcher

import ru.art2000.androraider.mvp.IView
import ru.art2000.androraider.presenter.launcher.LauncherPresenter

interface ILauncherView : IView {
    override val presenter: LauncherPresenter
}
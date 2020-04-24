package ru.art2000.androraider.view.launcher

import javafx.scene.control.Hyperlink
import javafx.scene.control.ListView
import javafx.scene.image.ImageView
import javafx.scene.text.Text
import ru.art2000.androraider.model.launcher.RecentProject
import ru.art2000.androraider.mvp.IController

interface ILauncherController : IController {

    var appNameText: Text
    var appInfoText: Text
    var recentsListView: ListView<RecentProject>
    var appLogoImageView: ImageView
    var newProjectButton: Hyperlink
    var openProjectButton: Hyperlink
    var settingsButton: Hyperlink
}
package ru.art2000.androraider.view.launcher

import javafx.fxml.FXML
import javafx.scene.control.Hyperlink
import javafx.scene.control.ListView
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import ru.art2000.androraider.model.launcher.RecentProject

class LauncherController {

    @FXML
    lateinit var appNameText: Text
    @FXML
    lateinit var appInfoText: Text
    @FXML
    lateinit var recentsListView: ListView<RecentProject>
    @FXML
    lateinit var appLogoImageView: ImageView
    @FXML
    lateinit var newProjectButton: Hyperlink
    @FXML
    lateinit var openProjectButton: Hyperlink
    @FXML
    lateinit var settingsButton: Hyperlink
    @FXML
    lateinit var root: HBox
}
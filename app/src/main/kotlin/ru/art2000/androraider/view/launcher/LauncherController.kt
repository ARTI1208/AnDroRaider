package ru.art2000.androraider.view.launcher

import javafx.fxml.FXML
import javafx.scene.control.Hyperlink
import javafx.scene.control.ListView
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import ru.art2000.androraider.model.launcher.RecentProject
import ru.art2000.androraider.utils.getLayout

class LauncherController : ILauncherController {

    @FXML
    override lateinit var appNameText: Text
    @FXML
    override lateinit var appInfoText: Text
    @FXML
    override lateinit var recentsListView: ListView<RecentProject>
    @FXML
    override lateinit var appLogoImageView: ImageView
    @FXML
    override lateinit var newProjectButton: Hyperlink
    @FXML
    override lateinit var openProjectButton: Hyperlink
    @FXML
    override lateinit var settingsButton: Hyperlink

    override val root: HBox

    override val layoutFile = "launcher.fxml"

    init {
        val loader = javaClass.getLayout(layoutFile)
        loader.setController(this)
        root = loader.load()
    }
}
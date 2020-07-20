package ru.art2000.androraider.view.editor.codearea

import javafx.scene.control.Label
import javafx.scene.control.TextField
import ru.art2000.androraider.mvp.IController

interface IGoToLineController : IController {

    val hintLabel: Label

    val textField: TextField

}
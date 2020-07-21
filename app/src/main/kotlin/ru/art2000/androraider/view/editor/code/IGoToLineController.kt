package ru.art2000.androraider.view.editor.code

import javafx.scene.control.Label
import javafx.scene.control.TextField
import ru.art2000.androraider.arch.IController

interface IGoToLineController : IController {

    val hintLabel: Label

    val textField: TextField

}
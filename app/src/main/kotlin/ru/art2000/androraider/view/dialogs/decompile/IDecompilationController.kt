package ru.art2000.androraider.view.dialogs.decompile

import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import ru.art2000.androraider.mvp.IController

interface IDecompilationController : IController {

    val apktoolPathField: TextField
    val apktoolPathFileSelector: Button

    val noCodeDecompile: CheckBox
    val noResDecompile: CheckBox

    val builtinFrameRadio: RadioButton

    val customFrameRadio: RadioButton
    val customFramePathField: TextField
    val customFramePathSelector: Button
    val customFramePathFileSelector: Button

    val folderNameField: TextField
    val projectPathField: TextField
    val projectPathFieldSelector: Button

    val overrideIfExistsCheckBox: CheckBox
}
package ru.art2000.androraider.view.dialogs.recompile

import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.RadioButton
import javafx.scene.control.TextField
import ru.art2000.androraider.arch.IController

interface IRecompilationController : IController {

    val apktoolPathField: TextField
    val apktoolPathFileSelector: Button

    val builtinFrameRadio: RadioButton

    val customFrameRadio: RadioButton
    val customFramePathField: TextField
    val customFramePathSelector: Button
    val customFramePathFileSelector: Button

    val fileNameField: TextField
    val filePathField: TextField
    val filePathFieldSelector: Button

    val forceRebuildCheckBox: CheckBox
}
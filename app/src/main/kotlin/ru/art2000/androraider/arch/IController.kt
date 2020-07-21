package ru.art2000.androraider.arch

import javafx.scene.Parent

interface IController {

    val root: Parent

    val layoutFile: String
}
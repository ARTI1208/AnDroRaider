package ru.art2000.androraider.view

import javafx.beans.NamedArg
import javafx.scene.Parent
import javafx.scene.Scene
import ru.art2000.androraider.utils.getStyle

class BaseScene(@NamedArg("root") root: Parent,
                @NamedArg(value = "width", defaultValue = "-1") width: Double,
                @NamedArg(value = "height", defaultValue = "-1") height: Double)
    : Scene(root, width, height) {

    init {
        stylesheets.add(javaClass.getStyle("application.css"))
    }
}
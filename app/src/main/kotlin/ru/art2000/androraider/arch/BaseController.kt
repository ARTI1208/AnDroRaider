package ru.art2000.androraider.arch

import javafx.scene.Parent
import ru.art2000.androraider.utils.getLayout

abstract class BaseController : IController {

    override val root: Parent by lazy {
        val loader = javaClass.getLayout(layoutFile)
        loader.setController(this)
        loader.load<Parent>()
    }
}
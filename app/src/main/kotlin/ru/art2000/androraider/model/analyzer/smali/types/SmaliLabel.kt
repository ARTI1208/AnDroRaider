package ru.art2000.androraider.model.analyzer.smali.types

import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent.Companion.EMPTY_RANGE
import java.io.File

class SmaliLabel(override val fullname: String, val method: SmaliMethod): SmaliComponent {

    override val file: File?
        get() {
            return method.file
        }

    override var textRange = EMPTY_RANGE

    override fun markAsNotExisting() {
        textRange = EMPTY_RANGE
    }

    override fun exists(): Boolean {
        return textRange.first >= 0
    }

    override fun toString(): String {
        return "Label $fullname at $textRange"
    }
}
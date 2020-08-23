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

    override fun toSmaliString(): String {
        return ":$fullname"
    }

    override fun toString(): String {
        return "Label $fullname at $textRange"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SmaliLabel

        if (fullname != other.fullname) return false
        if (method != other.method) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fullname.hashCode()
        result = 31 * result + method.hashCode()
        return result
    }


}
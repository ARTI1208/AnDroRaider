package ru.art2000.androraider.model.analyzer.smali.types

import java.io.File

class SmaliField : SmaliComponent {

    var name: String = ""
    var type: SmaliClass = SmaliClass.Primitives.INT
    var parentClass: SmaliClass? = null
        set(value) {
            if (value != field) {
                field?.fields?.remove(this)
                value?.fields?.add(this)
                field = value
            }
        }

    var modifier = 0
        private set

    override val file: File?
        get() = parentClass?.associatedFile

    override var textRange: IntRange = SmaliComponent.EMPTY_RANGE
    override val fullname: String
        get() = name

    override fun markAsNotExisting() {
        textRange = SmaliComponent.EMPTY_RANGE
    }

    override fun exists(): Boolean {
        return textRange.first >= 0
    }

    fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    override fun toString(): String {
        return "SmaliField(name='$name', type=${type.fullname}, parentClass=${parentClass?.fullname}, modifier=$modifier)"
    }
}
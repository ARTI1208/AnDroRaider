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
    override val file: File?
        get() = parentClass?.associatedFile

    override var textRange: IntRange = -1..0
    override val fullname: String
        get() = name

    override fun recheck(): SmaliComponent? {
        var parent = parentClass
        var field: SmaliField? = this
        while (parent != null) {
            if (field != null && field.textRange.first >= 0)
                break

            field = parent.findField(name, type)
            parent = parent.parentClass
        }

        if (field != null && field != this) {
            parentClass?.removeField(this)
            return field
        }

        return field ?: this
    }

    override fun exists(): Boolean {
        return textRange.first >= 0
    }

    public fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    override fun toString(): String {
        return "SmaliField(name='$name', type=${type.fullname}, parentClass=${parentClass?.fullname}, modifier=$modifier)"
    }


}
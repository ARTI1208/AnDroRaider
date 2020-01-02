package ru.art2000.androraider.analyzer.types

class SmaliField {

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

    public fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    override fun toString(): String {
        return "SmaliField(name='$name', type=${type.fullname}, parentClass=${parentClass?.fullname}, modifier=$modifier)"
    }


}
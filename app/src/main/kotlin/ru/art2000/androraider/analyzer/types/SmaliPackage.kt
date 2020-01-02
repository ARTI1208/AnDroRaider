package ru.art2000.androraider.analyzer.types

class SmaliPackage(val name: String, var parentPackage: SmaliPackage? = null) {

    init {
        parentPackage?.addSubPackage(this)
    }

    val subpackages = mutableListOf<SmaliPackage>()

    val classes = mutableListOf<SmaliClass>()

    val fullname: String
        get() {
            var result = name
            var parent = parentPackage
            while (parent != null) {
                result = parent.name + "." + result
                parent = parent.parentPackage
            }
            return result
        }

    public fun addSubPackage(smaliPackage: SmaliPackage) {
        smaliPackage.parentPackage = this
        subpackages.add(smaliPackage)
    }

    public fun removeSubPackage(smaliPackage: SmaliPackage?) {
        smaliPackage?.parentPackage = null
        subpackages.remove(smaliPackage)
    }

    public fun addClass(smaliClass: SmaliClass) {
        smaliClass.parentPackage = this
        classes.add(smaliClass)
    }

    public fun removeClass(smaliClass: SmaliClass) {
        smaliClass.parentPackage = null
        classes.remove(smaliClass)
    }

    override fun toString(): String {
        return "SmaliPackage($fullname)"
    }
}
package ru.art2000.androraider.model.analyzer.smali.types

import ru.art2000.androraider.model.analyzer.result.AndroidAppProject
import java.io.File
import java.util.*

class SmaliPackage(val project: AndroidAppProject, val name: String, var parentPackage: SmaliPackage? = null, val packageDelimiter: String = "."):SmaliComponent {

    init {
        parentPackage?.addSubPackage(this)
    }

    val subpackages = LinkedList<SmaliPackage>()

    val classes = LinkedList<SmaliClass>()

    var folder = File("")

    val rootPackage: Boolean
        get() = packageDelimiter.isEmpty()

    override val fullname: String
        get() {
            var result = name
            var parent = parentPackage
            while (parent != null) {
                result = parent.name + parent.packageDelimiter + result
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

    override val file: File?
        get() = null

    override val textRange: IntRange
        get() = SmaliComponent.EMPTY_RANGE

    override fun markAsNotExisting() {

    }

    override fun exists(): Boolean {
        return true
    }

    override fun toSmaliString(): String {
        var result = name
        var parent = parentPackage
        while (parent != null && !parent.rootPackage) {
            result = parent.name + "/" + result
            parent = parent.parentPackage
        }
        return "L$result"
    }

    override fun toString(): String {
        return "SmaliPackage($fullname)"
    }
}
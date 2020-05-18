package ru.art2000.androraider.model.analyzer.smali.types

import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import java.io.File

class SmaliPackage(val project: ProjectAnalyzeResult, val name: String, var parentPackage: SmaliPackage? = null) : SmaliComponent {

    init {
        parentPackage?.addSubPackage(this)
    }

    val subpackages = mutableListOf<SmaliPackage>()

    val classes = mutableListOf<SmaliClass>()

    var folder = File("")

    override val fullname: String
        get() {
            var result = name
            var parent = parentPackage
            while (parent != null) {
                result = parent.name + "." + result
                parent = parent.parentPackage
            }
            return result
        }

    fun addSubPackage(smaliPackage: SmaliPackage) {
        smaliPackage.parentPackage = this
        subpackages.add(smaliPackage)
    }

    fun removeSubPackage(smaliPackage: SmaliPackage?) {
        smaliPackage?.parentPackage = null
        subpackages.remove(smaliPackage)
    }

    fun addClass(smaliClass: SmaliClass) {
        smaliClass.parentPackage = this
        classes.add(smaliClass)
    }

    fun removeClass(smaliClass: SmaliClass) {
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

    override fun toString(): String {
        return "SmaliPackage($fullname)"
    }
}
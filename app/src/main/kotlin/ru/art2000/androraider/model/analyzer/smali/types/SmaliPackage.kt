package ru.art2000.androraider.model.analyzer.smali.types

import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import java.io.File

class SmaliPackage(val project: ProjectAnalyzeResult, val name: String, var parentPackage: SmaliPackage? = null):SmaliComponent {

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
        val rem = classes.removeIf {
            it.fullname == smaliClass.fullname
        }
//        if (rem) {
//            println("Removed prev version of: ${smaliClass.fullname}")
//        }
    }

    public fun removeClass(smaliClass: SmaliClass) {
        smaliClass.parentPackage = null
        classes.remove(smaliClass)
    }

    override val file: File?
        get() = null
    override val textRange: IntRange
        get() = -1..0

    override fun recheck(): SmaliComponent? {
        return this
    }

    override fun markAsNotExisting() {

    }

    override fun exists(): Boolean {
        return true
    }

    override fun toString(): String {
        return "SmaliPackage($fullname)"
    }
}
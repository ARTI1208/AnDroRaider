package ru.art2000.androraider.model.analyzer.smali

import javafx.collections.ObservableMap
import ru.art2000.androraider.model.analyzer.result.FileLink
import ru.art2000.androraider.model.analyzer.result.Project
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.model.analyzer.smali.types.SmaliPackage
import java.io.File

interface SmaliProject : Project {

    val smaliDirectories: List<File>

    val smaliComponentToFile: ObservableMap<SmaliComponent, FileLink>

    fun getSmaliPackageForClassName(className: String): SmaliPackage?

    fun findOrCreateSmaliPackage(packageName: String): SmaliPackage {
        return findOrTryCreateSmaliPackage(packageName)
                ?: throw IllegalArgumentException("Package name '$packageName' is invalid")
    }

    fun findOrCreateSmaliClass(className: String): SmaliClass {
        return findOrTryCreateSmaliClass(className)
                ?: throw IllegalArgumentException("Class name '$className' is invalid")
    }

    fun findOrTryCreateSmaliPackage(packageName: String): SmaliPackage?

    fun findOrTryCreateSmaliClass(className: String): SmaliClass?

    fun findSmaliPackage(packageName: String): SmaliPackage?

    fun findSmaliClass(className: String): SmaliClass?
}
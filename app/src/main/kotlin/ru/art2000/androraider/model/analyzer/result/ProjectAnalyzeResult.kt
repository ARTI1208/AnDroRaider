package ru.art2000.androraider.model.analyzer.result

import io.reactivex.Observable
import ru.art2000.androraider.model.analyzer.smali.SmaliIndexer
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliPackage
import ru.art2000.androraider.model.editor.ProjectSettings
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import java.io.File

class ProjectAnalyzeResult(val baseFolder: File) {

    private val projectFolders = mutableListOf<File>()

    private val packages = mutableListOf<SmaliPackage>()

    val fileToClassMapping = mutableMapOf<File, SmaliClass>()

    val projectSettings = ProjectSettings(baseFolder, SettingsPresenter.prefs)

    val smaliFolders = mutableListOf<File>()

    private val smaliFolderNameRegex = Regex("^((smali)|(smali_classes\\d+))\$")

    init {
        addProjectFolder(baseFolder)
    }

    fun addProjectFolder(file: File) {
        require(file.isDirectory) { "ProjectAnalyzeResult requires a folder as constructor argument" }

        smaliFolders.addAll(file.listFiles { _, name ->
            name.matches(smaliFolderNameRegex)
        }?.toList() ?: emptyList())

        projectFolders.add(file)
    }

    private fun getOrCreatePackage(name: String): SmaliPackage {
        val packageToReturn = findPackage(packages, name)
        if (packageToReturn != null)
            return packageToReturn

        val lastDot = name.lastIndexOf('.')
        return if (lastDot == -1) {
            val rootPackage = SmaliPackage(this, name)
            packages.add(rootPackage)
            rootPackage
        } else {
            val parentPackageName = name.substring(0, lastDot)
            val packageName = name.substring(lastDot + 1)
            SmaliPackage(this, packageName, getOrCreatePackage(parentPackageName))
        }
    }

    fun canAnalyzeFile(file: File): Boolean {
        return fileToClassMapping[file] != null
    }

    fun getPackageForClassName(name: String): SmaliPackage? {
        // Reference type
        if (name.startsWith('L')) {
            var referenceClassName = name.substring(1).replace('/', '.')
            if (referenceClassName.endsWith(';'))
                referenceClassName = referenceClassName.substring(0, referenceClassName.lastIndex)

            val classToReturn = findClass(packages, referenceClassName)
            if (classToReturn != null)
                return classToReturn.parentPackage

            val lastDot = referenceClassName.lastIndexOf('.')
            return if (lastDot == -1) {
                null
            } else {
                val parentPackageName = referenceClassName.substring(0, lastDot)
                getOrCreatePackage(parentPackageName)
            }
        } else {
            return null
        }
    }

    fun getOrCreateClass(name: String): SmaliClass? {

        // Primitive type or void
        when (name) {
            "V" -> return SmaliClass.Primitives.VOID
            "I" -> return SmaliClass.Primitives.INT
            "J" -> return SmaliClass.Primitives.LONG
            "S" -> return SmaliClass.Primitives.SHORT
            "B" -> return SmaliClass.Primitives.BYTE
            "Z" -> return SmaliClass.Primitives.BOOLEAN
            "C" -> return SmaliClass.Primitives.CHAR
            "F" -> return SmaliClass.Primitives.FLOAT
            "D" -> return SmaliClass.Primitives.DOUBLE
        }

        // Reference type
        if (name.startsWith('L')) {
            var referenceClassName = name.substring(1).replace('/', '.')
            if (referenceClassName.endsWith(';'))
                referenceClassName = referenceClassName.substring(0, referenceClassName.lastIndex)

            val classToReturn = findClass(packages, referenceClassName)
            if (classToReturn != null)
                return classToReturn

            val lastDot = referenceClassName.lastIndexOf('.')
            return if (lastDot == -1) {
                null
            } else {
                val parentPackageName = referenceClassName.substring(0, lastDot)
                val className = referenceClassName.substring(lastDot + 1)
                SmaliClass(className, getOrCreatePackage(parentPackageName))
            }
        } else if (name.startsWith('[')) { // Array
            val smaliClass = SmaliClass()
            for (c in name) {
                if (c == '[')
                    smaliClass.arrayCount++
                else
                    break
            }

            smaliClass.parentClass = getOrCreateClass(name.substring(smaliClass.arrayCount))

            return smaliClass
        } else {
            return null
        }
    }

    private fun findPackage(packages: List<SmaliPackage>, relativeName: String): SmaliPackage? {
        for (pack in packages) {
            val full = pack.fullname
            if (full == relativeName)
                return pack
            else if (relativeName.startsWith("$full.")) {
                return findPackage(pack.subpackages, relativeName)
            }
        }

        return null
    }

    private fun findClass(packages: List<SmaliPackage>, relativeName: String): SmaliClass? {
        for (pack in packages) {
            for (cl in pack.classes) {
                if (cl.fullname == relativeName)
                    return cl
            }
            if (relativeName.startsWith("${pack.fullname}.")) {
                return findClass(pack.subpackages, relativeName)
            }
        }

        return null
    }

    fun indexProject(): Observable<out FileAnalyzeResult> {
        return SmaliIndexer.indexProject(this)
    }

    fun analyzeFile(file: File, withRanges: Boolean = true): FileAnalyzeResult {
        SmaliIndexer.withRanges = withRanges
        return SmaliIndexer.analyzeFile(this, file)
    }
}
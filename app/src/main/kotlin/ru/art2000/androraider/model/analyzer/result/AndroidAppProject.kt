package ru.art2000.androraider.model.analyzer.result

import io.reactivex.Observable
import org.reactfx.collection.LiveArrayList
import ru.art2000.androraider.model.analyzer.smali.SmaliIndexer
import ru.art2000.androraider.model.analyzer.smali.SmaliIndexerSettings
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliPackage
import ru.art2000.androraider.model.analyzer.xml.EmptyXMLSettings
import ru.art2000.androraider.model.analyzer.xml.XMLIndexer
import ru.art2000.androraider.model.analyzer.xml.types.Document
import ru.art2000.androraider.model.editor.ProjectSettings
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import java.io.File

class AndroidAppProject(override val projectFolder: File): Project {

    private val projectFolders = mutableListOf<File>()

    private val rootPackage = SmaliPackage(this, "", packageDelimiter = "")

    val fileToClassMapping = mutableMapOf<File, SmaliClass>()

    val fileToXMLDocMapping = mutableMapOf<File, Document>()

    val projectSettings = ProjectSettings(projectFolder, SettingsPresenter.prefs)

    val smaliFolders = mutableListOf<File>()

    val errorList = LiveArrayList<FileSegment>()

    private val smaliFolderNameRegex = Regex("^((smali)|(smali_classes\\d+))\$")

    init {
        addProjectFolder(projectFolder)
        errorList.addChangeObserver {
            it.forEach { it.addedSubList.forEach { println(it.declaringFile.toString()) } }
        }
    }

    fun addProjectFolder(file: File) {
        require(file.isDirectory) { "ProjectAnalyzeResult requires a folder as constructor argument" }

        smaliFolders.addAll(file.listFiles { _, name ->
            name.matches(smaliFolderNameRegex)
        }?.toList() ?: emptyList())

        projectFolders.add(file)
    }

    private fun getOrCreatePackage(name: String): SmaliPackage {
        val packageToReturn = findPackage(name)
        if (packageToReturn != null)
            return packageToReturn

        val lastDot = name.lastIndexOf('.')
        return if (lastDot == -1) {
            SmaliPackage(this, name, rootPackage)
        } else {
            val parentPackageName = name.substring(0, lastDot)
            val packageName = name.substring(lastDot + 1)
            SmaliPackage(this, packageName, getOrCreatePackage(parentPackageName)).also {
                if (parentPackageName != it.parentPackage?.fullname) {
                    println("$parentPackageName vs ${it.parentPackage?.fullname}")
                }
            }
        }
    }

    override fun canAnalyzeFile(file: File): Boolean {
        return fileToClassMapping[file] != null || file.extension == "xml"
    }

    fun getPackageForClassName(name: String): SmaliPackage? {
        // Reference type
        if (name.startsWith('L')) {
            var referenceClassName = name.substring(1).replace('/', '.')
            if (referenceClassName.endsWith(';'))
                referenceClassName = referenceClassName.substring(0, referenceClassName.lastIndex)

            val classToReturn = findClass(referenceClassName)
            if (classToReturn != null)
                return classToReturn.parentPackage

            val lastDot = referenceClassName.lastIndexOf('.')
            return if (lastDot == -1) {
                rootPackage
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

            val classToReturn = findClass(referenceClassName)
            if (classToReturn != null)
                return classToReturn

            val lastDot = referenceClassName.lastIndexOf('.')
            return if (lastDot == -1) {
                SmaliClass(referenceClassName, rootPackage)
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

    private fun findPackage(relativeName: String): SmaliPackage? {
        return if (relativeName == rootPackage.fullname)
            rootPackage
        else
            findPackage(rootPackage.subpackages, relativeName)
    }

    private fun findPackage(packages: List<SmaliPackage>, relativeName: String): SmaliPackage? {
        for (pack in packages) {
            val full = pack.fullname
            if (full == relativeName)
                return pack
            else if (relativeName.startsWith("$full${pack.packageDelimiter}")) {
                return findPackage(pack.subpackages, relativeName)
            }
        }

        return null
    }

    private fun findClass(relativeName: String): SmaliClass? {
        for (cl in rootPackage.classes) {
            if (cl.fullname == relativeName)
                return cl
        }

        return findClass(rootPackage.subpackages, relativeName)
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

    override fun indexProject(): Observable<out FileAnalyzeResult> {
        return SmaliIndexer.indexProject(this, SmaliIndexerSettings())
    }

    override fun analyzeFile(file: File, withRanges: Boolean): FileAnalyzeResult {
        return when (file.extension) {
            "xml" -> XMLIndexer.analyzeFile(this, file, EmptyXMLSettings)
            else -> {
                val settings = SmaliIndexerSettings().also { it.withRanges = withRanges }
                SmaliIndexer.analyzeFile(this, file, settings)
            }
        }
    }

    override fun getAnalyzeResult(file: File): FileAnalyzeResult? {
        return when (file.extension) {
            "xml" -> fileToXMLDocMapping[file]
            "smali" -> fileToClassMapping[file]
            else -> null
        }
    }
}
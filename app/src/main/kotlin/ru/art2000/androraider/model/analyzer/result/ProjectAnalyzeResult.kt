package ru.art2000.androraider.model.analyzer.result

import io.reactivex.Observable
import ru.art2000.androraider.model.analyzer.smali.SmaliIndexer
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliPackage
import java.io.File

class ProjectAnalyzeResult(val baseFolder: File) {

    val packages = mutableListOf<SmaliPackage>()

    public val smaliFolders : List<File>

    val smaliFolderNameRegex = Regex("^((smali)|(smali_classes\\d+))\$")

    init {
        require(baseFolder.isDirectory) { "ProjectAnalyzeResult requires a folder as constructor argument" }

        smaliFolders = baseFolder.listFiles { _, name ->
            name.matches(smaliFolderNameRegex)
        }?.toList() ?: emptyList()
    }

    fun getOrCreatePackage(name: String): SmaliPackage {
        val packageToReturn = findPackage(packages, name)
        if (packageToReturn != null)
            return packageToReturn

        val lastDot = name.lastIndexOf('.')
        return if (lastDot == -1) {
            val rootPackage = SmaliPackage(name)
            packages.add(rootPackage)
            rootPackage
        } else {
            val parentPackageName = name.substring(0, lastDot)
            val packageName = name.substring(lastDot + 1)
            SmaliPackage(packageName, getOrCreatePackage(parentPackageName))
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
            if (lastDot == -1) {
//                throw IllegalStateException("Class $name must be in package")


                return null
            } else {
                val parentPackageName = referenceClassName.substring(0, lastDot)
                val className = referenceClassName.substring(lastDot + 1)

                return SmaliClass(className, getOrCreatePackage(parentPackageName))
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
//            throw IllegalStateException("Unknown class type for $name")
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

    public fun indexProject(): Observable<out FileAnalyzeResult> {
        return SmaliIndexer.indexProject(this)
    }

    public fun analyzeFile(file: File): FileAnalyzeResult{
        return SmaliIndexer.analyzeFile(this, file)
    }
}
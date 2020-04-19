package ru.art2000.androraider.analyzer.smali

import io.reactivex.Observable
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.analyzer.SyntaxAnalyzer
import ru.art2000.androraider.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.analyzer.smali.types.SmaliPackage
import java.io.File


class SmaliAnalyzer(projectBaseFolder: File) : SyntaxAnalyzer<SmaliClass>() {

    private val packages = mutableListOf<SmaliPackage>()

    private val smaliFolder: File

    override val filesRootDir: File
        get() = smaliFolder

    init {
        require(projectBaseFolder.isDirectory) { "SmaliAnalyzer requires a folder as constructor argument" }
        smaliFolder = projectBaseFolder.resolve("smali")
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

    fun getOrCreateClass(name: String): SmaliClass {

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
                throw IllegalStateException("Class $name must be in package")
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
            throw IllegalStateException("Unknown class type for $name")
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

    override fun analyzeFile(file: File): SmaliClass {
        require(!file.isDirectory) { "Method argument must be a file" }


        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokens = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokens as TokenStream)
        val tree = parser.parse()

        val smaliClass = SmaliClass()
        println("In new: ${smaliClass.errors.size}")


        val visitor = SmaliShallowScanner(smaliClass, this)
        visitor.visit(tree as ParseTree)
        visitor.onlyClass = false
        visitor.visit(tree as ParseTree)

        return visitor.smaliClass.apply { associatedFile = file }
    }

    override fun analyzeFilesInDir(directory: File): Observable<SmaliClass> {
        return Observable
                .fromIterable(directory.walk().asIterable().filter { !it.isDirectory })
                .subscribeOn(Schedulers.io())
                .map {
                    analyzeFile(it)
                }.observeOn(JavaFxScheduler.platform())
    }
}
package ru.art2000.androraider.analyzer

//import ru.art2000.androraider.analyzer.SmaliParserBaseListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import ru.art2000.androraider.analyzer.antlr.SmaliLexer
import ru.art2000.androraider.analyzer.antlr.SmaliParser
import ru.art2000.androraider.analyzer.types.SmaliClass
import ru.art2000.androraider.analyzer.types.SmaliField
import ru.art2000.androraider.analyzer.types.SmaliMethod
import ru.art2000.androraider.analyzer.types.SmaliPackage
import java.io.File
import java.io.FileWriter


class SmaliAnalyzer(val projectBaseFolder: File) {

    val packages = mutableListOf<SmaliPackage>()

    init {
        require(projectBaseFolder.isDirectory) { "SmaliAnalyzer requires a folder as constructor argument" }
        val smaliFolder = File(projectBaseFolder.absolutePath + File.separator + "smali")
        if (smaliFolder.exists() && smaliFolder.isDirectory) {
            generateMap(smaliFolder)
            writeMapToFile(projectBaseFolder)
        }
    }

    private fun writeMapToFile(fileFolder: File) {
        Thread {
            FileWriter(File(fileFolder, "map.txt")).use {
                it.write(forPackages(packages, StringBuilder(), 0).toString())
            }
        }.start()
    }

    private fun forPackages(packages: List<SmaliPackage>, builder: StringBuilder, level: Int): StringBuilder {
        packages.forEach {
            repeat(level) {
                builder.append("\t")
            }
            builder.appendln("====Package $it=====")
            forPackages(it.subpackages, builder, level + 1)
            forClasses(it.classes, builder, level + 1)
        }
        return builder
    }

    private fun forClasses(classes: List<SmaliClass>, builder: StringBuilder, level: Int): StringBuilder {
        classes.forEach {
            repeat(level) {
                builder.append("\t")
            }
            builder.appendln("==Class $it==")
            forMethods(it.methods, builder, level + 1)
        }
        return builder
    }

    private fun forMethods(methods: List<SmaliMethod>, builder: StringBuilder, level: Int): StringBuilder {
        methods.forEach {
            repeat(level) {
                builder.append("\t")
            }
            builder.appendln("--Method $it--")
        }
        return builder
    }

    public fun getOrCreatePackage(name: String): SmaliPackage {
        val packageToReturn = findInPackages(packages, name)
        if (packageToReturn != null)
            return packageToReturn

        val lastDot = name.lastIndexOf('.')
        if (lastDot == -1) {
            val rootPackage = SmaliPackage(name)
            packages.add(rootPackage)
            return rootPackage
        } else {
            val parentPackageName = name.substring(0, lastDot)
            val packageName = name.substring(lastDot + 1)
            return SmaliPackage(packageName, getOrCreatePackage(parentPackageName))
        }
    }

    public fun getOrCreateClass(name: String): SmaliClass {

        println("getOrCrClass $name")

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
               referenceClassName =  referenceClassName.substring(0, referenceClassName.lastIndex)

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

    private fun getPackageByName(name: String, smaliFolder: File): SmaliPackage? {
        val usefulPart =
                name.substring(smaliFolder.absolutePath.length + 1).replace(File.separatorChar, '.')

        return findInPackages(packages, usefulPart)
    }

    private fun findInPackages(packages: List<SmaliPackage>, relativeName: String): SmaliPackage? {
        for (pack in packages) {
            val full = pack.fullname
            if (full == relativeName)
                return pack
            else if (relativeName.startsWith("$full.")) {
                return findInPackages(pack.subpackages, relativeName)
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

    private fun analyzePackages(smaliFolder: File) {
        smaliFolder.walk().forEach {

            if (!it.isDirectory) {
                analyzeFile(it)
            }

//            if (it.absolutePath == smaliFolder.absolutePath) {
//
//            } else if (it.parent == smaliFolder.absolutePath) {
//                if (it.isDirectory) {
//                    packages.add(SmaliPackage(it.name))
//                } else {
//                    throw IllegalStateException("File ${it.absolutePath} must be in package!")
//                }
//            } else {
//                val parentPackage = getPackageByName(it.parent, smaliFolder)
//                if (parentPackage == null) {
//
//                } else {
//                    if (it.isDirectory) {
//                        parentPackage.addSubPackage(SmaliPackage(it.name))
//                    } else {
//                        parentPackage.addClass(analyzeFile(it))
//                    }
//                }
//            }
        }

        println("==================")
        printPackages(packages, 0)
    }

    private fun printPackages(packages: List<SmaliPackage>, level: Int) {
        packages.forEach {
            repeat(level) { print(' ') }
            println(it.fullname)
            printPackages(it.subpackages, level + 1)
            printClasses(it.classes, level + 1)
        }
    }

    private fun printClasses(classes: List<SmaliClass>, level: Int) {
        classes.forEach {
            repeat(level) { print(' ') }
            println("==Class  $it")
            printFields(it.fields, level + 1)
            printMethods(it.methods, level + 1)
        }
    }

    private fun printFields(classes: List<SmaliField>, level: Int) {
        classes.forEach {
            repeat(level) { print(' ') }
            println("==Field  ${it}")
        }
    }

    private fun printMethods(classes: List<SmaliMethod>, level: Int) {
        classes.forEach {
            repeat(level) { print(' ') }
            println("==Method  ${it}")
        }
    }

    public fun analyzeFile(file: File): SmaliClass {
        require(!file.isDirectory) { "Method argument must be a file" }


        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokens = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokens as TokenStream)
        val tree = parser.parse()

        val pastwk = ParseTreeWalker()
        val listener = SmaliParserBaseListener(this)
        listener.clear()
        pastwk.walk(listener, tree as ParseTree)
//
        return listener.parsedSmaliClass
//        return SmaliClass(file.name)
    }


    public fun scanFile(file: File, writeInfo: Boolean = false): SmaliClass {
        require(!file.isDirectory) { "Method argument must be a file" }


        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokens = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokens as TokenStream)
        val tree = parser.parse()

        val smaliClass = SmaliClass()

        val visitor = SmaliShallowScanner(smaliClass, this)
        visitor.visit(tree as ParseTree)
        visitor.onlyClass = false
        visitor.visit(tree as ParseTree)


        if (writeInfo) {
            printClasses(listOf(visitor.smaliClass), 0)
        }
//        listener.clear()
//        pastwk.walk(listener, tree as ParseTree)
//


//        return listener.parsedSmaliClass
        return visitor.smaliClass
    }

    fun generateMap(smaliFolder: File) {
        smaliFolder.walk().forEach {
            if (!it.isDirectory) {
                scanFile(it)
            }
        }
    }
}
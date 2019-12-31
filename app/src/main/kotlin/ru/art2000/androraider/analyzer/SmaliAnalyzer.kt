package ru.art2000.androraider.analyzer

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import ru.art2000.androraider.TypeDetector
import ru.art2000.androraider.analyzer.antlr.SmaliLexer
import ru.art2000.androraider.analyzer.antlr.SmaliParser
//import ru.art2000.androraider.analyzer.SmaliParserBaseListener
import java.io.File
import java.util.regex.Matcher
import java.util.regex.Pattern


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
}

data class SmaliMethod(val name: String,
                       val returnType: String,
                       val accessModifier: AccessModifiers = AccessModifiers.PACKAGE_PRIVATE,
                       val finalModifier: FinalModifiers = FinalModifiers.NO,
                       val staticModifier: StaticModifiers = StaticModifiers.NO,
                       var parentClass: SmaliClass? = null) {}

class SmaliConstructor(val returnType: String,
                       val accessModifier: AccessModifiers = AccessModifiers.PACKAGE_PRIVATE,
                       val finalModifier: FinalModifiers = FinalModifiers.NO,
                       val staticModifier: StaticModifiers = StaticModifiers.NO,
                       val parentClass: SmaliClass? = null) {}

class SmaliField(val name: String,
                 val type: String,
                 val accessModifier: AccessModifiers = AccessModifiers.PACKAGE_PRIVATE,
                 val finalModifier: FinalModifiers = FinalModifiers.NO,
                 val staticModifier: StaticModifiers = StaticModifiers.NO,
                 var parentClass: SmaliClass? = null) {}

class SmaliClass() {

    constructor(name: String,
                accessModifier: AccessModifiers = AccessModifiers.PACKAGE_PRIVATE,
                finalModifier: FinalModifiers = FinalModifiers.NO,
                staticModifier: StaticModifiers = StaticModifiers.NO,
                parentPackage: SmaliPackage? = null) : this() {
        this.name = name
        this.accessModifier = accessModifier
        this.finalModifier = finalModifier
        this.staticModifier = staticModifier
        this.parentPackage = parentPackage
    }

    var modifier = 0

    public fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    val fullname: String
        get() {
            var result = "$name.smali"
            var parent = parentPackage
            while (parent != null) {
                result = parent.name + "." + result
                parent = parent.parentPackage
            }
            return result
        }

    var name: String = ""
    var accessModifier: AccessModifiers = AccessModifiers.PACKAGE_PRIVATE
    var finalModifier: FinalModifiers = FinalModifiers.NO
    var staticModifier: StaticModifiers = StaticModifiers.NO
    var parentPackage: SmaliPackage? = null
        set(value) {
            if (field != value) {
                field?.classes?.remove(this)
                value?.classes?.add(this)
                field = value
            }
        }

    val fields = mutableListOf<SmaliField>()
    val methods = mutableListOf<SmaliMethod>()

    public fun addField(smaliField: SmaliField) {
        smaliField.parentClass = this
        fields.add(smaliField)
    }

    public fun removeField(smaliField: SmaliField) {
        smaliField.parentClass = null
        fields.remove(smaliField)
    }

    public fun addMethod(smaliMethod: SmaliMethod) {
        smaliMethod.parentClass = this
        methods.add(smaliMethod)
    }

    public fun removeMethod(smaliMethod: SmaliMethod) {
        smaliMethod.parentClass = null
        methods.remove(smaliMethod)

    }

}

enum class AccessModifiers(name: String) {
    PUBLIC("public"),
    PRIVATE("private"),
    PROTECTED("protected"),
    PACKAGE_PRIVATE("")
}

enum class StaticModifiers(name: String) {
    STATIC("static"),
    NO("")
}

enum class FinalModifiers(name: String) {
    FINAL("final"),
    NO("")
}

class SmaliAnalyzer(projectBaseFolder: File) {

    val packages = mutableListOf<SmaliPackage>()

    init {
        require(projectBaseFolder.isDirectory) { "SmaliAnalyzer requires a folder as constructor argument" }
        val smaliFolder = File(projectBaseFolder.absolutePath + File.separator + "smali")
        if (smaliFolder.exists() && smaliFolder.isDirectory) {
            analyzePackages(smaliFolder)
        }
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
            println(it.fullname)
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

    fun getAccessibleMethods(file: File): List<SmaliMethod> {
        val methods = mutableListOf<SmaliMethod>()
        val pattern = Pattern.compile(TypeDetector.SMALI_METHOD_DECLARATION)
        val fileText = file.readText()
        val matcher = pattern.matcher(fileText)
        while (matcher.find()) {
            methods.add(extractToMethod(matcher, fileText))
        }
        return methods
    }

    private fun extractToMethod(matcher: Matcher, string: String): SmaliMethod {

        val method = SmaliMethod(
                matcher.group("NAME"),
                "w",
                getAccessModifierByName(matcher.group("ACCESS")),
                getFinalModifierByName(matcher.group("FINAL")),
                getStaticModifierByName(matcher.group("STATIC")))

        return method
    }

    private fun getAccessModifierByName(string: String?): AccessModifiers {
        if (string == null || string.isEmpty())
            return AccessModifiers.PACKAGE_PRIVATE

        return when (string) {
            "public" -> AccessModifiers.PUBLIC
            "protected" -> AccessModifiers.PROTECTED
            "private" -> AccessModifiers.PRIVATE
            else -> throw IllegalArgumentException("Unknown access modifier $string")
        }
    }

    private fun getStaticModifierByName(string: String?): StaticModifiers {
        if (string == null || string.isEmpty())
            return StaticModifiers.NO

        return when (string) {
            "static" -> StaticModifiers.STATIC
            else -> throw IllegalArgumentException("Unknown static modifier $string")
        }
    }

    private fun getFinalModifierByName(string: String?): FinalModifiers {
        if (string == null || string.isEmpty())
            return FinalModifiers.NO

        return when (string) {
            "final" -> FinalModifiers.FINAL
            else -> throw IllegalArgumentException("Unknown final modifier $string")
        }
    }
}
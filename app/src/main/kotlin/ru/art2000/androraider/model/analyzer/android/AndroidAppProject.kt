package ru.art2000.androraider.model.analyzer.android

import io.reactivex.Observable
import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.ObservableMap
import javafx.collections.ObservableSet
import org.reactfx.Subscription
import ru.art2000.androraider.model.analyzer.*
import ru.art2000.androraider.model.analyzer.result.*
import ru.art2000.androraider.model.analyzer.smali.*
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.model.analyzer.smali.types.SmaliPackage
import ru.art2000.androraider.model.analyzer.xml.XMLProject
import ru.art2000.androraider.model.analyzer.xml.types.Document
import ru.art2000.androraider.model.editor.ProjectSettings
import ru.art2000.androraider.model.io.DirectoryObserver
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import ru.art2000.androraider.utils.connect
import ru.art2000.androraider.utils.observe
import ru.art2000.androraider.utils.plus
import tornadofx.*
import java.io.File
import java.nio.file.StandardWatchEventKinds
import java.nio.file.WatchEvent
import kotlin.concurrent.thread

class AndroidAppProject(override val projectFolder: File) : Project, SmaliProject, XMLProject {

    // Project implementation

    // XMLProject implementation

    // SmaliProject implementation

    companion object {

        val resourceTypes = listOf(
            "drawable",
            "plurals",
            "array",
            "string-array",
            "integer-array",
            "mipmap",
            "string",
            "style",
            "dimen",
            "bool",
            "attr",
            "color",
            "id",
            "integer",
            "xml",
            "fraction"
        )
    }

    val projectDependencies: List<File>
        get() = deps

    private val deps = mutableListOf<File>()

    private val rootPackage = SmaliPackage("", packageDelimiter = "")

    override val smaliDirectories: List<File>
        get() = smaliFolders

    override val fileToXMLDocMapping: ObservableMap<File, Document> = FXCollections.observableHashMap()

    val projectSettings = ProjectSettings(projectFolder, SettingsPresenter.prefs)

    val smaliFolders = mutableListOf<File>()

    override val errorMap: ObservableMap<File, List<TextSegment>> = FXCollections.observableHashMap()

    private val smaliFolderNameRegex = Regex("^((smali)|(smali_classes\\d+))\$")

    override val smaliComponentToFile: ObservableMap<SmaliComponent, FileLink> = FXCollections.observableHashMap()

    @Deprecated("Use links", ReplaceWith("links"))
    val resources: MutableMap<AndroidResource, ObservableSet<AndroidResourceLink>> = hashMapOf()

    val public: MutableMap<Int, AndroidResource> = hashMapOf()

    val fileDependencies: ObservableMap<File, ObservableSet<File>> = FXCollections.observableHashMap()

    private val directoryObservers = mutableListOf<DirectoryObserver>()

    private val stateProperty = SimpleObjectProperty(State.INIT)
    private var state by stateProperty

    override val links: MutableMap<Any, ObservableSet<FileLink>> = hashMapOf()

    private val fileToLinkables = hashMapOf<File, MutableList<Any>>()

//    private val isIndexing = Var

    enum class State {
        INIT,
        INDEXING,
        ANALYZING,
        IDLE,
    }

    init {
        validateFolder(projectFolder)
    }

    fun addDependencyFolder(file: File) {
        require(file.isDirectory) { "ProjectAnalyzeResult requires a folder as constructor argument" }

        validateFolder(file)

        deps.add(file)
    }

    private fun validateFolder(file: File) {
        val observer = DirectoryObserver(file).apply { start() }
        directoryObservers += observer
        collectSmaliFolders(file, observer)
        observeResources(file, observer)

        observer.addListener { eventFile, kind ->
            if (kind == StandardWatchEventKinds.ENTRY_DELETE || kind == StandardWatchEventKinds.ENTRY_MODIFY) {

                fileToLinkables[eventFile]?.forEach {
                    val removed = links[it]?.removeIf { link ->
                        link.file == eventFile
                    }

                    if (removed == true) {
                        println("Removed resource links to $eventFile")
                    }
                }
            }

            if (kind != StandardWatchEventKinds.ENTRY_DELETE) {
                thread {
                    try {
                        val indexingResult = indexFile(eventFile)

                        if (indexingResult == null) {
                            println("error reindexing $eventFile")
                            return@thread
                        }

                        println("$eventFile got ${indexingResult.links.size} links")

                        indexingResult.links.forEach { (obj, link) ->
                            println(obj)
                            getLinksFor(obj).also {

                                if (it.isNotEmpty()) {
                                    println("lcount: ${it.size + 1}")
                                }
                            } += link
                            fileToLinkables.getOrPut(link.file) { mutableListOf() }.add(obj)
                        }
                    } catch (_: Exception) {
                    }
                }
            }
        }
    }

    private fun observeResources(file: File, directoryObserver: DirectoryObserver) {

    }

    private fun collectSmaliFolders(file: File, directoryObserver: DirectoryObserver) {
        smaliFolders.addAll(file.listFiles { childFile ->
            childFile.isDirectory && childFile.name.matches(smaliFolderNameRegex)
        }?.toList() ?: emptyList())

        directoryObserver.addListener { childFile, kind ->

            if (!(childFile.parentFile == file
                        && childFile.isDirectory
                        && childFile.name.matches(smaliFolderNameRegex))
            )
                return@addListener

            when (kind) {
                StandardWatchEventKinds.ENTRY_CREATE -> smaliFolders.add(childFile)
                StandardWatchEventKinds.ENTRY_DELETE -> smaliFolders.remove(childFile)
            }
        }
    }

    override fun findOrTryCreateSmaliPackage(packageName: String): SmaliPackage {
        val packageToReturn = findSmaliPackage(packageName)
        if (packageToReturn != null)
            return packageToReturn

        val lastDot = packageName.lastIndexOf('.')
        return if (lastDot == -1) {
            SmaliPackage(packageName, rootPackage)
        } else {
            val parentPackageName = packageName.substring(0, lastDot)
            val simpleName = packageName.substring(lastDot + 1)
            SmaliPackage(simpleName, findOrTryCreateSmaliPackage(parentPackageName))
        }
    }

    override fun getSmaliPackageForClassName(className: String): SmaliPackage? {
        // Reference type
        if (className.startsWith('L')) {
            var referenceClassName = className.substring(1).replace('/', '.')
            if (referenceClassName.endsWith(';'))
                referenceClassName = referenceClassName.substring(0, referenceClassName.lastIndex)

            val classToReturn = findSmaliClass(referenceClassName)
            if (classToReturn != null)
                return classToReturn.parentPackage

            val lastDot = referenceClassName.lastIndexOf('.')
            return if (lastDot == -1) {
                rootPackage
            } else {
                val parentPackageName = referenceClassName.substring(0, lastDot)
                findOrTryCreateSmaliPackage(parentPackageName)
            }
        } else {
            return null
        }
    }

    override fun findOrTryCreateSmaliClass(className: String): SmaliClass? {

        // Primitive type or void
        when (className) {
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
        if (className.startsWith('L')) {
            var referenceClassName = className.substring(1).replace('/', '.')
            if (referenceClassName.endsWith(';'))
                referenceClassName = referenceClassName.substring(0, referenceClassName.lastIndex)

            val classToReturn = findSmaliClass(referenceClassName)
            if (classToReturn != null)
                return classToReturn

            val lastDot = referenceClassName.lastIndexOf('.')
            return if (lastDot == -1) {
                SmaliClass(referenceClassName, rootPackage)
            } else {
                val parentPackageName = referenceClassName.substring(0, lastDot)
                val simpleClassName = referenceClassName.substring(lastDot + 1)
                SmaliClass(simpleClassName, findOrTryCreateSmaliPackage(parentPackageName))
            }
        } else if (className.startsWith('[')) { // Array
            val smaliClass = SmaliClass()
            for (c in className) {
                if (c == '[')
                    smaliClass.arrayCount++
                else
                    break
            }

            smaliClass.parentClass = findOrTryCreateSmaliClass(className.substring(smaliClass.arrayCount))

            return smaliClass
        } else {
            return null
        }
    }

    override fun findSmaliPackage(packageName: String): SmaliPackage? {
        return if (packageName == rootPackage.fullname)
            rootPackage
        else
            findPackage(rootPackage.subpackages, packageName)
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

    override fun findSmaliClass(className: String): SmaliClass? {
        for (cl in rootPackage.classes) {
            if (cl.fullname == className)
                return cl
        }

        return findClass(rootPackage.subpackages, className)
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

    override fun indexProject(): Observable<out FileIndexingResult> {

        val indexing = listOf(
            AndroidResourceIndexer.indexProject(this),
            SmaliIndexer.indexProject(this)
        )

        return Observable.merge(indexing)
            .doOnNext {
                it.links.forEach { (obj, link) ->
                    getLinksFor(obj) += link

                    fileToLinkables.getOrPut(link.file) { mutableListOf() }.add(obj)
                }
            }.doOnSubscribe {
                println("Indexing")
                state = State.INDEXING
            }
    }

    override fun analyzeProject(): Observable<out FileAnalyzeResult> {
        val analyzing = listOf(
            AndroidResourceAnalyzer.analyzeProject(this, AndroidResourceAnalyzerSettings(AnalyzeMode.ERRORS)),
            SmaliAnalyzer.analyzeProject(this, SmaliAnalyzerSettings(AnalyzeMode.ERRORS))
        )

        return Observable.merge(analyzing)
            .doOnSubscribe {
                println("Analyzing")
                state = State.ANALYZING
            }
    }

    override fun setupProject(): Observable<*> {
        return Observable.concat(indexProject(), analyzeProject())
            .doOnTerminate {
                println("IDLE")
                state = State.IDLE
            }
    }

    override fun requestFileAnalyze(
        file: File,
        mode: AnalyzeMode,
        callback: (FileAnalyzeResult?) -> Unit
    ): Subscription {

        val observer = directoryObservers.find {
            file.startsWith(it.directory)
        }

        checkNotNull(observer) { "Cannot request segments of not project file" }

        println("Found observer for file $file, dir: ${observer.directory}")
        val subs = FXCollections.observableSet(HashSet<Subscription>())

        fun runAnalyzeAsync() {
            thread {
                val analyzeResult = analyzeFile(file, mode)

                Platform.runLater {
                    callback(analyzeResult)
                }
            }
        }

        val listener = { eventFile: File, kind: WatchEvent.Kind<*> ->
            if ((eventFile == file && kind == StandardWatchEventKinds.ENTRY_MODIFY)
                || fileDependencies[file]?.contains(eventFile) == true
            ) {
                runAnalyzeAsync()
            }
        }

        subs += observer.observe(listener)

        runAnalyzeAsync()

        return Subscription.dynamic(subs) { it }
    }

    private val resourceDirectories: List<File>
        get() = (projectDependencies + projectFolder).map { it.resolve("res") }

    private fun indexFile(file: File): FileIndexingResult? {

        return when {
            file.extension == "xml" && resourceDirectories.any { file.startsWith(it) } ->
                AndroidResourceIndexer.indexFile(this, file)
            file.extension == "smali" ->
                SmaliIndexer.indexFile(this, file)
            else ->
                null
        }
    }

    private fun analyzeFile(file: File, mode: AnalyzeMode): FileAnalyzeResult? {
        return when (file.extension) {
            "xml" -> AndroidResourceAnalyzer.analyzeFile(this, AndroidResourceAnalyzerSettings(mode), file)
            "smali" -> SmaliAnalyzer.analyzeFile(this, SmaliAnalyzerSettings(mode), file)
            else -> null
        }
    }

    private fun analyzeText(text: String, lang: String, mode: AnalyzeMode): TextAnalyzeResult? {
        return when (lang) {
            "xml" -> AndroidResourceAnalyzer.analyzeText(this, AndroidResourceAnalyzerSettings(mode), text)
            "smali" -> SmaliAnalyzer.analyzeText(this, SmaliAnalyzerSettings(mode), text)
            else -> null
        }
    }

    override fun requestTextAnalyze(
        text: ObservableValue<String>,
        lang: String,
        mode: AnalyzeMode,
        callback: (TextAnalyzeResult?) -> Unit
    ): Subscription {

        val dependenciesSet = FXCollections.observableSet<javafx.beans.Observable>()

        fun runAnalyzeAsync(textToAnalyze: String) {
            thread {
                val analyzeResult = analyzeText(textToAnalyze, lang, mode)

                val newDependencies = analyzeResult?.dependencies ?: emptyList()

                try {
                    dependenciesSet.retainAll(newDependencies)
                } catch (e: Exception) {
                    println("Error clearing dependencies: ${e.message}")
                }

                try {
                    dependenciesSet.addAll(newDependencies)
                } catch (e: Exception) {
                    println("Error adding dependencies: ${e.message}")
                }

                Platform.runLater {
                    callback(analyzeResult)
                }
            }
        }

        val textSubscription = text.connect { _, _, newValue ->
            println("text change")
            runAnalyzeAsync(newValue)
        }

        val dependencySubscription = Subscription.dynamic(dependenciesSet) {
            it.observe {
                println("dependency change ${it.hashCode()}")
                runAnalyzeAsync(text.value ?: "")
            }
        }

        return textSubscription + dependencySubscription
    }
}
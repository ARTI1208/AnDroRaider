package ru.art2000.androraider.model.analyzer.android

import javafx.collections.FXCollections
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.atn.PredictionMode
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.antlr.XMLLexer
import ru.art2000.androraider.antlr.XMLParser
import ru.art2000.androraider.model.analyzer.AnalyzeMode
import ru.art2000.androraider.model.analyzer.extensions.SimplifiedAnalyzer
import ru.art2000.androraider.model.analyzer.result.*
import ru.art2000.androraider.model.analyzer.xml.XMLScanner
import ru.art2000.androraider.model.analyzer.xml.types.Tag
import ru.art2000.androraider.model.apktool.addAll
import java.io.File

object AndroidResourceAnalyzer : SimplifiedAnalyzer<AndroidAppProject, AndroidResourceAnalyzerSettings> {

    override fun analyzeText(project: AndroidAppProject, settings: AndroidResourceAnalyzerSettings, text: String): TextAnalyzeResult {
        val lexer = XMLLexer(CharStreams.fromString(text))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = XMLParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        val tree = parser.document()

        return when (settings.mode) {
            AnalyzeMode.ERRORS -> errorAnalyze(project, tree)
            AnalyzeMode.FULL -> fullAnalyze(project, tree)
        }
    }

    override fun analyzeFile(project: AndroidAppProject, settings: AndroidResourceAnalyzerSettings, file: File): FileAnalyzeResult {
        val lexer = XMLLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = XMLParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        parser.interpreter.predictionMode = PredictionMode.SLL
        val tree = parser.document()

        project.errorMap.remove(file)

        val textAnalyzeResult = when (settings.mode) {
            AnalyzeMode.ERRORS -> {
                errorAnalyze(project, tree).also {
                    project.errorMap[file] = it.textSegments
                }
            }
            AnalyzeMode.FULL -> fullAnalyze(project, tree)
        }

        return SimpleFileAnalyzeResult(file, textAnalyzeResult.textSegments, textAnalyzeResult.dependencies)
    }

    private fun errorAnalyze(project: AndroidAppProject, tree: ParseTree): TextAnalyzeResult {
        val scanner = AndroidResourceErrorScanner {
            !project.links[it].isNullOrEmpty()
        }

        val segments = scanner.visit(tree)

        return SimpleTextAnalyzeResult(segments)
    }

    private fun fullAnalyze(project: AndroidAppProject, tree: ParseTree): TextAnalyzeResult {
        val scanner = XMLScanner()

        val document = scanner.visit(tree)

        val errors = scanner.errors

        val observables = mutableListOf<javafx.beans.Observable>()

        document.tagsFlatten.forEach { tag ->
            tag.attributes.forEach { attr ->
                attr.value.apply {
                    if (text.startsWith("@")) {
                        val scope = if (text.startsWith("@android:")) {
                            ResourceScope.FRAMEWORK
                        } else {
                            ResourceScope.LOCAL
                        }

                        val data = text.split("/")
                        if (data.size != 2) {
                            return@apply
                        }

                        val type = data.first().let {
                            if (scope == ResourceScope.FRAMEWORK) {
                                it.drop(9)
                            } else {
                                it.drop(1)
                            }
                        }
                        val name = data[1]

                        val linkedResource = SimpleAndroidResource(name, type, scope)
                        val links = project.getLinksFor(linkedResource)

                        observables.add(links)

                        if (links.isEmpty()) {
                            attr.value.isErrorLink = true
                            attr.value.description = "Linked resource $linkedResource not found"
                            errors.add(attr.value)
                        } else {
                            attr.value.isErrorLink = false
                            attr.value.description = ""
                            fileLinkDetails.addAll(links)
                        }
                    }
                }
            }
        }

        val textSegments = FXCollections.observableList(mutableListOf<TextSegment>().apply {
            document.tags.forEach { tag ->
                getTagSegments(tag, this)
            }
        })

        return SimpleTextAnalyzeResult(textSegments, observables)
    }

    private fun getTagSegments(tag: Tag, list: MutableList<TextSegment>) {
        list.addAll(tag.styleSegments)
        list.addAll(tag.nameSegments)
        tag.attributes.forEach { attr ->
            attr.schema?.also { list.add(it) }
            list.addAll(attr.name, attr.valueWithQuotes, attr.value)
        }
        tag.subTags.forEach { getTagSegments(it, list) }
    }

    override fun isSuitableFile(file: File): Boolean = !file.isDirectory && file.extension == "xml"

    @ExperimentalStdlibApi
    override val AndroidAppProject.examinedDirectories: Iterable<File>
        get() = buildList {
            add(projectFolder.resolve("AndroidManifest.xml"))
            addAll(projectFolder.resolve("res").listFiles() ?: emptyArray())
        }
}
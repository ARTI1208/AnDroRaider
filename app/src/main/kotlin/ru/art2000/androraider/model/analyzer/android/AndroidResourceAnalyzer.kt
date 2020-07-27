package ru.art2000.androraider.model.analyzer.android

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.atn.PredictionMode
import ru.art2000.androraider.model.analyzer.Analyzer
import ru.art2000.androraider.model.analyzer.xml.XMLLexer
import ru.art2000.androraider.model.analyzer.xml.XMLParser
import ru.art2000.androraider.model.analyzer.xml.XMLScanner
import ru.art2000.androraider.model.analyzer.xml.types.Document
import java.io.File

object AndroidResourceAnalyzer : Analyzer<AndroidAppProject, AndroidResourceAnalyzeSettings, Document> {

    override fun analyzeFile(project: AndroidAppProject, settings: AndroidResourceAnalyzeSettings, file: File): Document {
        val lexer = XMLLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = XMLParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        parser.interpreter.predictionMode = PredictionMode.SLL
        val tree = parser.document()

        var doc = project.fileToXMLDocMapping[file]
        val isNull = doc == null

        if (doc == null) {
            doc = Document(file)
        }

        doc.tags.clear()

        XMLScanner(doc).visit(tree)

        doc.tagsFlatten.forEach { tag ->
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
                        val files = project.resources[linkedResource]

                        if (files == null || files.isEmpty()) {
                            attr.value.isErrorLink = true
                            attr.value.description = "Linked resource $linkedResource not found"
                        } else {
                            attr.value.isErrorLink = false
                            attr.value.description = ""
                            fileLinkDetails.addAll(files)
                        }
                    }
                }
            }
        }


        if (isNull) {
            project.fileToXMLDocMapping[file] = doc
        }

        return doc
    }

    override fun analyzeDirectory(project: AndroidAppProject, settings: AndroidResourceAnalyzeSettings, directory: File): Observable<out Document> {
        return Observable
                .fromIterable(directory.walk().asIterable())
                .subscribeOn(Schedulers.io())
                .filter {
                    !it.isDirectory && it.extension == "xml"
                }.map { file ->
                    analyzeFile(project, settings, file)
                }
    }

    override fun analyzeProject(project: AndroidAppProject, settings: AndroidResourceAnalyzeSettings): Observable<out Document> {
        val resFolder = project.projectFolder.resolve("res")

        val obs = Observable.fromIterable(emptyList<Document>())

        return resFolder.listFiles()?.fold(obs) { acc, folder ->
            acc.concatWith(analyzeDirectory(project, settings, folder))
        } ?: obs
    }


}
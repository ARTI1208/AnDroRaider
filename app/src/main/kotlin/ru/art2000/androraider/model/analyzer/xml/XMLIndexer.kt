package ru.art2000.androraider.model.analyzer.xml

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.atn.PredictionMode
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.model.analyzer.Indexer
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.analyzer.smali.SmaliIndexer
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.xml.types.Document
import java.io.File

object XMLIndexer: Indexer<Document, XMLSettings> {

    override fun analyzeFile(project: ProjectAnalyzeResult, file: File, settings: XMLSettings): Document {
        val lexer = XMLLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = XMLParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        parser.interpreter.predictionMode = PredictionMode.SLL
        val tree = parser.document()

        return XMLScanner(file).visit(tree as ParseTree)
    }

    override fun analyzeFilesInDir(project: ProjectAnalyzeResult, directory: File, settings: XMLSettings): Observable<Document> {
        return Observable
                .fromIterable(directory.walk().asIterable())
                .subscribeOn(Schedulers.io())
                .filter {
                    !it.isDirectory && it.extension == "xml"
                }.map { file ->
                    analyzeFile(project, file, settings)
                }
    }

    override fun indexProject(project: ProjectAnalyzeResult, settings: XMLSettings): Observable<Document> {
        return Observable.empty()
    }

}
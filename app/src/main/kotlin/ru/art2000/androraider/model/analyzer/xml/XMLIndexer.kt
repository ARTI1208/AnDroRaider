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
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.SimpleFileIndexingResult
import java.io.File

object XMLIndexer: Indexer<XMLProject> {

    override fun indexFile(project: XMLProject, file: File): FileIndexingResult {
        val lexer = XMLLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = XMLParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        parser.interpreter.predictionMode = PredictionMode.SLL
        val tree = parser.document()

        val document = XMLScanner().visit(tree as ParseTree)

        return SimpleFileIndexingResult(file)
    }

    override fun indexDirectory(project: XMLProject, directory: File): Observable<FileIndexingResult> {
        return Observable
                .fromIterable(directory.walk().asIterable())
                .subscribeOn(Schedulers.io())
                .filter {
                    !it.isDirectory && it.extension == "xml"
                }.map { file ->
                    indexFile(project, file)
                }
    }

    override fun indexProject(project: XMLProject,): Observable<FileIndexingResult> {
        return Observable.empty()
    }

}
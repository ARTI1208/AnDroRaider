package ru.art2000.androraider.model.analyzer.smali

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.atn.PredictionMode
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.model.analyzer.Indexer
import ru.art2000.androraider.model.analyzer.android.AndroidAppProject
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.FileLink
import ru.art2000.androraider.model.analyzer.result.SimpleFileIndexingResult
import ru.art2000.androraider.model.analyzer.result.SimpleFileLink
import java.io.File

object SmaliIndexer : Indexer<AndroidAppProject> {

    override fun indexFile(project: AndroidAppProject, file: File): FileIndexingResult {
        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        parser.interpreter.predictionMode = PredictionMode.SLL
        val tree = parser.parse()

        val clazz = SmaliShallowScanner(project, file).visit(tree as ParseTree)

        val links = mutableListOf<Pair<Any, FileLink>>()

        links += clazz to SimpleFileLink(file, clazz.textRange.last, clazz.fullname)

        clazz.fields.forEach {
            val description = (it.parentClass?.fullname?.plus("/") ?: "") + it.name
            links += it to SimpleFileLink(file, it.textRange.last, description)
        }

        clazz.methods.forEach {
            val description = (it.parentClass?.fullname?.plus("/") ?: "") + it.name
            links += it to SimpleFileLink(file, it.textRange.last, description)
        }

        clazz.associatedFile = file

        return SimpleFileIndexingResult(file, links)
    }

    override fun indexDirectory(project: AndroidAppProject, directory: File): Observable<FileIndexingResult> {
        return Observable
                .fromIterable(directory.walk().asIterable())
                .subscribeOn(Schedulers.io())
                .filter {
                    !it.isDirectory && it.extension == "smali"
                }.map { file ->
                    indexFile(project, file)
                }
    }

    override fun indexProject(project: AndroidAppProject,): Observable<out FileIndexingResult> {
        return Observable.concat(project.smaliFolders.map {
            indexDirectory(project, it)
        })
    }
}
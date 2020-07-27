package ru.art2000.androraider.model.analyzer.smali

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.PredictionMode
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.model.analyzer.Indexer
import ru.art2000.androraider.model.analyzer.android.AndroidAppProject
import ru.art2000.androraider.model.analyzer.result.SimpleFileAnalysisSegment
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.utils.textRange
import java.io.File

object SmaliIndexer : Indexer<AndroidAppProject, SmaliIndexerSettings, SmaliClass> {

    private fun generateFileIndex(project: AndroidAppProject, file: File): SmaliClass {
        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        parser.interpreter.predictionMode = PredictionMode.SLL
        val tree = parser.parse()

        return SmaliShallowScanner(project, file).visit(tree as ParseTree).also { it.associatedFile = file }
    }

    override fun indexFile(project: AndroidAppProject, settings: SmaliIndexerSettings, file: File): SmaliClass {
        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokenStream as TokenStream)

        parser.removeErrorListeners()
//        parser.errorHandler = SmaliErrorStrategy()

        val tree = parser.parse()

        var smaliClass = project.fileToClassMapping[file] ?: throw IllegalStateException("ClassNotFound")

        smaliClass.ranges.clear()
        smaliClass.fields.forEach { it.markAsNotExisting() }
        smaliClass.methods.forEach { it.markAsNotExisting() }
        smaliClass.interfaces.clear()

        smaliClass = SmaliAllInOneAnalyzer(project, smaliClass, settings).visit(tree as ParseTree)
        project.fileToClassMapping[file] = smaliClass.apply { associatedFile = file }

        tokenStream.tokens.forEach {
            if (it.channel == 1) { // hidden channel
                smaliClass.ranges.add(SimpleFileAnalysisSegment(file, it.textRange, "comment"))
            }
        }

        return smaliClass
    }

    override fun indexDirectory(project: AndroidAppProject, settings: SmaliIndexerSettings, directory: File): Observable<SmaliClass> {
        return Observable
                .fromIterable(directory.walk().asIterable())
                .subscribeOn(Schedulers.io())
                .filter {
                    !it.isDirectory && it.extension == "smali"
                }.map { file ->
                    generateFileIndex(project, file).also { project.fileToClassMapping[file] = it  }
                }
    }

    override fun indexProject(project: AndroidAppProject, settings: SmaliIndexerSettings): Observable<out SmaliClass> {
        return project.smaliFolders.fold(Observable.fromIterable(emptyList<SmaliClass>())) { acc, folder ->
            acc.concatWith(indexDirectory(project, settings, folder))
        }
    }
}
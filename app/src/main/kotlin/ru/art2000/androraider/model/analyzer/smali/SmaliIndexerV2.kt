package ru.art2000.androraider.model.analyzer.smali

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.model.analyzer.Indexer
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.analyzer.result.RangeStatusBase
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.utils.textRange
import java.io.File

object SmaliIndexerV2 : Indexer<SmaliClass> {

    public fun readSmaliClassNameAndSuper(project: ProjectAnalyzeResult, file: File): SmaliClass {
        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        val tree = parser.classDirective()

        return ClassAndSuperReader(project, SmaliClass(file)).visit(tree as ParseTree).also { it.associatedFile = file }
    }

    override fun analyzeFile(project: ProjectAnalyzeResult, file: File): SmaliClass {
        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokenStream as TokenStream)
        parser.removeErrorListeners()
        val tree = parser.parse()

        println("==========================$file")
        val smaliClass = project.fileToClassMapping[file] ?: throw IllegalStateException("ClassNotFound")

        smaliClass.ranges.clear()

        SmaliAllInOneAnalyzer(project, smaliClass).visit(tree as ParseTree)

//        smaliClass.ranges.forEach {
//            println("${it.range}:${it.style}:${it.description}")
//        }

        tokenStream.tokens.forEach {
            if (it.channel == 1) { // hidden channel
                smaliClass.ranges.add(RangeStatusBase(it.textRange, "Comment", listOf("comment")))
            }
        }

        return smaliClass
    }

    override fun analyzeFilesInDir(project: ProjectAnalyzeResult, directory: File): Observable<SmaliClass> {
        return Observable
                .fromIterable(directory.walk().asIterable())
                .subscribeOn(Schedulers.io())
                .filter { !it.isDirectory }
                .map { file ->
                    readSmaliClassNameAndSuper(project, file).also { project.fileToClassMapping[file] = it  }
                }
    }

    override fun indexProject(project: ProjectAnalyzeResult): Observable<SmaliClass> {
        return project.smaliFolders.fold(Observable.fromIterable(emptyList<SmaliClass>())) { acc, folder ->
            acc.concatWith(analyzeFilesInDir(project, folder))
        }.map { analyzeFile(project, it.associatedFile!!) }
    }
}
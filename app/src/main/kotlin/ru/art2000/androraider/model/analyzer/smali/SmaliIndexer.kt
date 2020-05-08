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

object SmaliIndexer : Indexer<SmaliClass> {
    override fun analyzeFile(project: ProjectAnalyzeResult, file: File): SmaliClass {
        require(!file.isDirectory) { "Method argument must be a file" }


        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokenStream as TokenStream)

        val tree = parser.parse()

        var smaliClass = project.fileToClassMapping[file] ?: SmaliClass(file)
        smaliClass = ClassAndSuperReader(project).visit(tree as ParseTree)

        smaliClass.associatedFile = file
        project.fileToClassMapping[file] = smaliClass
        smaliClass.ranges.clear()

        SmaliFileScanner(project, smaliClass).visit(tree as ParseTree)

        tokenStream.tokens.forEach {
            if (it.channel == 1) { // hidden channel
                smaliClass.ranges.add(RangeStatusBase(it.textRange, "Comment", listOf("comment")))
            }
        }

        return smaliClass
    }

    override fun analyzeFilesInDir(project: ProjectAnalyzeResult, directory: File): Observable<SmaliClass> {
        return Observable
                .fromIterable(directory.walk().asIterable().filter { !it.isDirectory })
                .subscribeOn(Schedulers.io())
                .map {
                    analyzeFile(project, it)
                }
    }

    override fun indexProject(project: ProjectAnalyzeResult): Observable<SmaliClass> {
        return project.smaliFolders.fold(Observable.fromIterable(emptyList<SmaliClass>())) { acc, folder ->
            acc.concatWith(analyzeFilesInDir(project, folder))
        }
    }


}
package ru.art2000.androraider.model.analyzer.smali

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.model.analyzer.Indexer
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import java.io.File

object SmaliIndexer : Indexer<SmaliClass> {
    override fun analyzeFile(project: ProjectAnalyzeResult, file: File): SmaliClass {
        require(!file.isDirectory) { "Method argument must be a file" }


        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokens = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokens as TokenStream)
        parser.removeErrorListeners()

        val tree = parser.parse()

        var smaliClass = project.fileToClassMapping[file] ?: SmaliClass(file)
        smaliClass = ClassAndSuperReader(project, smaliClass).visit(tree as ParseTree)

        smaliClass.associatedFile = file
        project.fileToClassMapping[file] = smaliClass
        smaliClass.ranges.clear()

        SmaliFileScanner(project, smaliClass).visit(tree as ParseTree)

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
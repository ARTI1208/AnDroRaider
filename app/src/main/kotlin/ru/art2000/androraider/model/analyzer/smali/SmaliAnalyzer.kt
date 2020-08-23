package ru.art2000.androraider.model.analyzer.smali

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.model.analyzer.AnalyzeMode
import ru.art2000.androraider.model.analyzer.Analyzer
import ru.art2000.androraider.model.analyzer.result.*
import ru.art2000.androraider.utils.textRange
import java.io.File

object SmaliAnalyzer : Analyzer<SmaliProject, SmaliAnalyzerSettings> {

    override fun analyzeText(project: SmaliProject, settings: SmaliAnalyzerSettings, text: String): TextAnalyzeResult {
        val lexer = SmaliLexer(CharStreams.fromString(text))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokenStream as TokenStream)

        parser.removeErrorListeners()

        val tree = parser.parse()

        val scanner = SmaliAllInOneAnalyzer(project, settings)

        scanner.visit(tree as ParseTree)

        if (settings.mode == AnalyzeMode.FULL) {
            tokenStream.tokens.forEach {
                if (it.channel == 1) { // hidden channel
                    scanner.ranges.add(SimpleStyledSegment(it.textRange, "comment"))
                }
            }
        }

        println("dependencies count: ${scanner.dependencies.size}")

        return SimpleTextAnalyzeResult(scanner.ranges, scanner.dependencies)
    }

    override fun analyzeFile(project: SmaliProject, settings: SmaliAnalyzerSettings, file: File): FileAnalyzeResult {

        val lexer = SmaliLexer(CharStreams.fromFileName(file.absolutePath))
        val tokenStream = CommonTokenStream(lexer as TokenSource)
        val parser = SmaliParser(tokenStream as TokenStream)

        parser.removeErrorListeners()
//        parser.errorHandler = SmaliErrorStrategy()

        val tree = parser.parse()

        val scanner = SmaliAllInOneAnalyzer(project, settings)

        scanner.visit(tree as ParseTree)

        project.errorMap[file] = ArrayList(project.errorMap[file] ?: listOf()).apply {
            addAll(scanner.errors)
        }

        if (settings.mode == AnalyzeMode.FULL) {
            tokenStream.tokens.forEach {
                if (it.channel == 1) { // hidden channel
                    scanner.ranges.add(SimpleStyledSegment(it.textRange, "comment"))
                }
            }
        }

        return SimpleFileAnalyzeResult(file, scanner.ranges, scanner.dependencies)
    }

    override fun analyzeDirectory(project: SmaliProject, settings: SmaliAnalyzerSettings, directory: File): Observable<out FileAnalyzeResult> {
        return Observable
                .fromIterable(directory.walk().asIterable())
                .subscribeOn(Schedulers.io())
                .filter {
                    !it.isDirectory && it.extension == "smali"
                }.map { file ->
                    analyzeFile(project, settings, file)
                }
    }

    override fun analyzeProject(project: SmaliProject, settings: SmaliAnalyzerSettings): Observable<out FileAnalyzeResult> {
        return Observable.concat(project.smaliDirectories.map {
            analyzeDirectory(project, settings, it)
        })
    }
}
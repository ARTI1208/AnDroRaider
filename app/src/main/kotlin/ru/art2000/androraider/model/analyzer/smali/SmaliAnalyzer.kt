package ru.art2000.androraider.model.analyzer.smali

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.antlr.SmaliLexer
import ru.art2000.androraider.antlr.SmaliParser
import ru.art2000.androraider.model.analyzer.AnalyzeMode
import ru.art2000.androraider.model.analyzer.extensions.SimplifiedAnalyzer
import ru.art2000.androraider.model.analyzer.result.*
import ru.art2000.androraider.utils.textRange
import java.io.File

object SmaliAnalyzer : SimplifiedAnalyzer<SmaliProject, SmaliAnalyzerSettings> {

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

    override fun isSuitableFile(file: File): Boolean = !file.isDirectory && file.extension == "smali"

    override val SmaliProject.examinedDirectories: Iterable<File>
        get() = smaliDirectories
}
package ru.art2000.androraider.model.analyzer.xml

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.atn.PredictionMode
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.antlr.XMLLexer
import ru.art2000.androraider.antlr.XMLParser
import ru.art2000.androraider.model.analyzer.extensions.FilteringIndexer
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.SimpleFileIndexingResult
import java.io.File

object XMLIndexer: FilteringIndexer<XMLProject> {

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

    override fun isSuitableFile(file: File): Boolean = !file.isDirectory && file.extension == "xml"

    override fun indexProject(project: XMLProject,): Flow<FileIndexingResult> {
        return emptyFlow()
    }

}
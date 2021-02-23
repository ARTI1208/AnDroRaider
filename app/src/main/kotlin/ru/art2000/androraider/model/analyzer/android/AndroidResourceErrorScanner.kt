package ru.art2000.androraider.model.analyzer.android

import org.antlr.v4.runtime.tree.ErrorNode
import ru.art2000.androraider.antlr.XMLParser
import ru.art2000.androraider.antlr.XMLParserBaseVisitor
import ru.art2000.androraider.model.analyzer.result.Error
import ru.art2000.androraider.model.analyzer.result.TextSegment

class AndroidResourceErrorScanner(val resourceChecker: (AndroidResource) -> Boolean) :
    XMLParserBaseVisitor<List<TextSegment>>() {

    private val errors = mutableListOf<TextSegment>()

    override fun defaultResult(): List<TextSegment> {
        return errors
    }

    override fun visitErrorNode(node: ErrorNode): List<TextSegment> {

        errors.add(Error.from(node))

        return super.visitErrorNode(node)
    }

    override fun visitAttribute(ctx: XMLParser.AttributeContext): List<TextSegment> {

        val valueText = ctx.STRING().text.drop(1).dropLast(1)

        if (valueText.startsWith("@")) {
            val scope = if (valueText.startsWith("@android:")) {
                ResourceScope.FRAMEWORK
            } else {
                ResourceScope.LOCAL
            }

            val data = valueText.split("/")
            if (data.size != 2) {
                return errors
            }

            val type = data.first().let {
                if (scope == ResourceScope.FRAMEWORK) {
                    it.drop(9)
                } else {
                    it.drop(1)
                }
            }
            val name = data[1]

            val linkedResource = SimpleAndroidResource(name, type, scope)
            if (!resourceChecker(linkedResource)) {
                errors.add(Error(IntRange.EMPTY, ""))
            }
        }

        return super.visitAttribute(ctx)
    }
}
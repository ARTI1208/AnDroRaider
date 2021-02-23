package ru.art2000.androraider.model.analyzer.xml

import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import ru.art2000.androraider.antlr.XMLParser
import ru.art2000.androraider.antlr.XMLParserBaseVisitor
import ru.art2000.androraider.model.analyzer.result.Error
import ru.art2000.androraider.model.analyzer.result.TextSegment
import ru.art2000.androraider.model.analyzer.xml.types.Document
import ru.art2000.androraider.model.analyzer.xml.types.Tag
import ru.art2000.androraider.utils.firstPos
import ru.art2000.androraider.utils.lastPos
import ru.art2000.androraider.utils.textRange

class XMLScanner : XMLParserBaseVisitor<Document>() {

    private val document = Document()

    val errors = mutableListOf<TextSegment>()

    override fun visitErrorNode(node: ErrorNode): Document {

        errors.add(Error.from(node))

        return super.visitErrorNode(node)
    }

    override fun defaultResult() = document

    override fun visitProlog(ctx: XMLParser.PrologContext): Document {
        val name = "xml"

        val tagRanges = mutableListOf<IntRange>()
        val tagNameRanges = mutableListOf<IntRange>()

        val start = ctx.XMLDeclOpen().firstPos
        val end = start + 5 // plus <?xml length
        tagRanges.add(start..end)
        tagNameRanges.add((start + 2)..end)

        ctx.SPECIAL_CLOSE().also {
            tagRanges.add(it.textRange)
        }

        val tag = document.createTag(name, tagRanges, tagNameRanges)

        ctx.attribute().forEach {
            val fullname = it.Name().text

            val delimiterPos = fullname.indexOf(':')

            val schema = if (delimiterPos >= 0) fullname.substring(0, delimiterPos) else null
            val attrName = if (delimiterPos >= 0) fullname.substring(delimiterPos) else fullname

            tag.createAttribute(it.firstPos, schema, attrName, it.STRING().text)
        }

        return document
    }

    private val ruleToTag = hashMapOf<RuleContext, Tag>()

    override fun visitElement(ctx: XMLParser.ElementContext): Document {
        val name = ctx.Name(0).text

        val tagRanges = mutableListOf<IntRange>()
        val tagNameRanges = mutableListOf<IntRange>()

        tagNameRanges.addAll(ctx.Name().map { it.textRange })

        tagRanges.add(ctx.firstPos..ctx.Name(0).lastPos)

        ctx.SLASH_CLOSE()?.also {
            tagRanges.add(it.textRange)
        }

        ctx.CLOSE(0)?.also {
            tagRanges.add(it.textRange)
        }

        ctx.OPEN(1)?.also { open ->
            ctx.CLOSE(1)?.also { close ->
                tagRanges.add(open.firstPos..close.lastPos)
            }
        }

        val tagValue = ctx.content()?.chardata(0)?.TEXT()?.text ?: ""

        val parentTag = ruleToTag[ctx.parent]

        val tag = parentTag?.createTag(name, tagRanges, tagNameRanges, tagValue)
            ?: document.createTag(name, tagRanges, tagNameRanges, tagValue)

        ctx.attribute().forEach {
            val fullname = it.Name().text

            val delimiterPos = fullname.indexOf(':')

            val schema = if (delimiterPos >= 0) fullname.substring(0, delimiterPos) else null
            val attrName = if (delimiterPos >= 0) fullname.substring(delimiterPos) else fullname

            tag.createAttribute(it.firstPos, schema, attrName, it.STRING().text)
        }

        val content = ctx.content()
        return if (content == null)
            document
        else {
            ruleToTag[content] = tag
            visitContent(content).also {
                ruleToTag.remove(content)
            }
        }
    }
}
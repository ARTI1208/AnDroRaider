package ru.art2000.androraider.model.analyzer.xml

import ru.art2000.androraider.model.analyzer.xml.types.Document
import ru.art2000.androraider.utils.firstPos
import ru.art2000.androraider.utils.lastPos
import ru.art2000.androraider.utils.textRange
import java.io.File

class XMLScanner(val file: File) : XMLParserBaseVisitor<Document>() {

    private val document = Document(file)

    override fun defaultResult(): Document {
        return document
    }

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

        val tag = document.createTag(name, tagRanges, tagNameRanges)

        ctx.attribute().forEach {
            val fullname = it.Name().text

            val delimiterPos = fullname.indexOf(':')

            val schema = if (delimiterPos >= 0) fullname.substring(0, delimiterPos) else null
            val attrName = if (delimiterPos >= 0) fullname.substring(delimiterPos) else fullname

            tag.createAttribute(it.firstPos, schema, attrName, it.STRING().text)
        }

        return if (ctx.content() == null)
            document
        else
            visitContent(ctx.content())
    }
}
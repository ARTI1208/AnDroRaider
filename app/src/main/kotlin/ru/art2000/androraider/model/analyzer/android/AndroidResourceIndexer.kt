package ru.art2000.androraider.model.analyzer.android

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.atn.PredictionMode
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.antlr.XMLLexer
import ru.art2000.androraider.antlr.XMLParser
import ru.art2000.androraider.model.analyzer.extensions.SimplifiedIndexer
import ru.art2000.androraider.model.analyzer.result.FileIndexingResult
import ru.art2000.androraider.model.analyzer.result.FileLink
import ru.art2000.androraider.model.analyzer.result.SimpleFileIndexingResult
import ru.art2000.androraider.model.analyzer.xml.XMLScanner
import ru.art2000.androraider.model.analyzer.xml.types.Tag
import java.io.File

object AndroidResourceIndexer : SimplifiedIndexer<AndroidAppProject> {

    override fun indexFile(project: AndroidAppProject, file: File): FileIndexingResult {

        val resourceScope = if (file.startsWith(project.projectFolder))
            ResourceScope.LOCAL
        else
            ResourceScope.FRAMEWORK

        val directoryNameParts = file.parentFile.name.split('-')
        val directoryResourcesType = directoryNameParts.first()

        val links = mutableListOf<Pair<Any, FileLink>>()

        if (directoryResourcesType == "values") {
            val lexer = XMLLexer(CharStreams.fromFileName(file.absolutePath))
            val tokenStream = CommonTokenStream(lexer as TokenSource)
            val parser = XMLParser(tokenStream as TokenStream)
            parser.removeErrorListeners()
            parser.interpreter.predictionMode = PredictionMode.SLL
            val tree = parser.document()

            val doc = XMLScanner().visit(tree as ParseTree)

            doc.tags.forEach { rootTag ->
                // ignore <?xml and <resources tags and tags with level > 1
                rootTag.subTags.forEach sub@{
                    val resourceName = it.attributes.find { attr ->
                        attr.name.text == "name"
                    }?.value?.text

                    if (resourceName == null) {
                        println("Cannot find resource name in $file for tag ${it.name}")
                        return@sub
                    }

                    val tagResourceType = if (it.name == "item") {
                        it.attributes.find { attr ->
                            attr.name.text == "type"
                        }?.value?.text
                    } else {
                        it.name
                    }

                    if (tagResourceType == null) {
                        println("Cannot determine resource type")
                    } else {
                        val link = indexTag(project, file, it, resourceName, tagResourceType, resourceScope)

                        if (link != null) {
                            links += link
                        }
                    }
                }
            }
        } else {
            val resource = SimpleAndroidResource(file.nameWithoutExtension, directoryResourcesType, resourceScope)
            links += resource to AndroidResourceLink(resource, file)
        }

        return SimpleFileIndexingResult(file, links)
    }

    override fun isSuitableFile(file: File): Boolean = !file.isDirectory

    @ExperimentalStdlibApi
    override val AndroidAppProject.examinedDirectories: Iterable<File>
        get() = buildList {
            addAll(projectFolder.resolve("res").listFiles() ?: emptyArray())

            projectDependencies.forEach { frameDirectory ->
                frameDirectory.resolve("res").listFiles()?.forEach {
                    this += it
                }
            }
        }

    private fun indexPublicTag(project: AndroidAppProject, public: Tag, resourceName: String, resourceScope: ResourceScope) {
        val resourceTypeAttr = public.attributes.find { attr ->
            attr.name.text == "type"
        }

        if (resourceTypeAttr == null) {
            println("Cannot find public resource type for tag $public")
            return
        }

        val resourceId = public.attributes.find { attr ->
            attr.name.text == "id"
        }?.value?.text

        if (resourceId == null) {
            println("Cannot find public resource id for tag $public")
            return
        }

        if (!resourceId.startsWith("0x")) {
            println("Invalid public resource id for tag $public")
            return
        }

        val intResourceId = resourceId.drop(2).toInt(16)

        val resource = SimpleAndroidResource(resourceName, resourceTypeAttr.value.text, resourceScope)
        project.public[intResourceId] = resource
    }

    private fun indexTag(
        project: AndroidAppProject,
        file: File,
        tag: Tag,
        resourceName: String,
        resourceType: String,
        resourceScope: ResourceScope
    ) : Pair<Any, FileLink>? {
        if (resourceType == "public") {
            indexPublicTag(project, tag, resourceName, resourceScope)
            return null
        }

        if (resourceType !in AndroidAppProject.resourceTypes) {
            println("Unknown resource type $resourceType, tag = $tag")
            return null
        }

        val tagStart = tag.styleSegments.firstOrNull()?.segmentRange?.first ?: 0

        val resource = SimpleAndroidResource(resourceName, resourceType, resourceScope)

        return resource to AndroidResourceLink(resource, file, tagStart)
    }
}
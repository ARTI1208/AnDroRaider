package ru.art2000.androraider.model.analyzer.android

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenSource
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.atn.PredictionMode
import org.antlr.v4.runtime.tree.ParseTree
import ru.art2000.androraider.model.analyzer.Indexer
import ru.art2000.androraider.model.analyzer.xml.XMLLexer
import ru.art2000.androraider.model.analyzer.xml.XMLParser
import ru.art2000.androraider.model.analyzer.xml.XMLScanner
import ru.art2000.androraider.model.analyzer.xml.types.Tag
import java.io.File

object AndroidResourceIndexer : Indexer<AndroidAppProject, AndroidIndexerSettings, AndroidResourceHolder> {

    override fun indexFile(project: AndroidAppProject, settings: AndroidIndexerSettings, file: File): AndroidResourceHolder {

        val resourceScope = if (file.startsWith(project.projectFolder))
            ResourceScope.LOCAL
        else
            ResourceScope.FRAMEWORK

        val directoryNameParts = file.parentFile.name.split('-')
        val directoryResourcesType = directoryNameParts.first()

        val result = AndroidResourceHolder(file)

        if (directoryResourcesType == "values") {
            val lexer = XMLLexer(CharStreams.fromFileName(file.absolutePath))
            val tokenStream = CommonTokenStream(lexer as TokenSource)
            val parser = XMLParser(tokenStream as TokenStream)
            parser.removeErrorListeners()
            parser.interpreter.predictionMode = PredictionMode.SLL
            val tree = parser.document()

            val doc = XMLScanner(file).visit(tree as ParseTree).apply {
                project.fileToXMLDocMapping[file] = this
            }

            doc.tags.forEach { rootTag ->
                // ignore <?xml and <resources tags and tags with level > 1
                rootTag.subTags.forEach sub@ {
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
                        indexTag(project, it, resourceName, tagResourceType, resourceScope)
                    }
                }
            }
        } else {
            val resource = SimpleAndroidResource(file.nameWithoutExtension, directoryResourcesType, resourceScope)

            project.resources[resource] =
                    project.resources.getOrDefault(resource, mutableListOf()).apply {
                        add(AndroidResourceLink(resource, file))
                    }
        }

        return result
    }

    override fun indexDirectory(project: AndroidAppProject, settings: AndroidIndexerSettings, directory: File): Observable<AndroidResourceHolder> {
        return Observable
                .fromIterable(directory.walk().asIterable())
                .subscribeOn(Schedulers.io())
                .filter {
                    !it.isDirectory
                }.map { file ->
                    indexFile(project, settings, file)
                }
    }

    override fun indexProject(project: AndroidAppProject, settings: AndroidIndexerSettings): Observable<AndroidResourceHolder> {
        val projectResFolder = project.projectFolder.resolve("res")

        val observables = mutableListOf<Observable<AndroidResourceHolder>>()

        projectResFolder.listFiles()?.forEach {
            observables.add(indexDirectory(project, settings, it))
        }

        project.dependencies.forEach { frameDirectory ->
            frameDirectory.resolve("res").listFiles()?.forEach {
                observables.add(indexDirectory(project, settings, it))
            }
        }

        return Observable.concat(observables)
    }

    private fun indexPublicTag(project: AndroidAppProject, public: Tag, resourceName: String, resourceScope: ResourceScope) {
        val resourceTypeAttr = public.attributes.find { attr ->
            attr.name.text == "type"
        }

        if (resourceTypeAttr == null) {
            println("Cannot find public resource type in ${public.document.file} for tag $public")
            return
        }

        val resourceId = public.attributes.find { attr ->
            attr.name.text == "id"
        }?.value?.text

        if (resourceId == null) {
            println("Cannot find public resource id in ${public.document.file} for tag $public")
            return
        }

        if (!resourceId.startsWith("0x")) {
            println("Invalid public resource id in ${public.document.file} for tag $public")
            return
        }

        val intResourceId = resourceId.drop(2).toInt(16)

        val resource = SimpleAndroidResource(resourceName, resourceTypeAttr.value.text, resourceScope)
        project.public[intResourceId] = resource
    }

    private fun indexTag(project: AndroidAppProject, tag: Tag, resourceName: String, resourceType: String, resourceScope: ResourceScope) {
        if (resourceType == "public") {
            indexPublicTag(project, tag, resourceName, resourceScope)
            return
        }

        if (resourceType !in AndroidAppProject.resourceTypes) {
            println("Unknown resource type $resourceType in file ${tag.document.file}, tag = $tag")
            return
        }

        val tagStart = tag.styleSegments.firstOrNull()?.segmentRange?.first ?: 0

        val resource = SimpleAndroidResource(resourceName, resourceType, resourceScope)
        project.resources[resource] =
                project.resources.getOrDefault(resource, mutableListOf()).apply {
                    add(AndroidResourceLink(resource, tag.document.file, tagStart))
                }
    }
}
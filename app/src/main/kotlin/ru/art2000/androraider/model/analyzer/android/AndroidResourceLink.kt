package ru.art2000.androraider.model.analyzer.android

import ru.art2000.androraider.model.analyzer.result.FileLink
import java.io.File

class AndroidResourceLink(val resource: AndroidResource, override val file: File, override val offset: Int = 0) : FileLink {

    val modifiers: List<String>

    override val description: String
        get() = "${resource.name} (${modifiers.joinToString("-")}) : ${resource.type}"

    init {
        require(file.parent != null) { "Android resource file must have a parent" }

        val folderNameParts = file.parentFile.name.split('-')
        modifiers = folderNameParts.drop(1)
    }
}
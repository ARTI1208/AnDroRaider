package ru.art2000.androraider.model.analyzer.android

import ru.art2000.androraider.model.analyzer.result.FileLink
import ru.art2000.androraider.model.editor.CodeDataProvider
import ru.art2000.androraider.model.editor.FileDataProvider
import java.io.File

class AndroidResourceLink(val resource: AndroidResource, override val file: File, override val offset: Int = 0) : FileLink {

    val modifiers: List<String>

    override val data: CodeDataProvider by lazy { FileDataProvider(file, offset) }

    override val description: String
        get() = "${resource.name} (${modifiers.joinToString("-")}) : ${resource.type}"

    init {
        require(file.parent != null) { "Android resource file must have a parent" }

        val folderNameParts = file.parentFile.name.split('-')
        modifiers = folderNameParts.drop(1)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AndroidResourceLink

        if (resource != other.resource) return false
        if (file != other.file) return false
        if (offset != other.offset) return false

        return true
    }

    override fun hashCode(): Int {
        var result = resource.hashCode()
        result = 31 * result + file.hashCode()
        result = 31 * result + offset
        return result
    }

    override fun toString(): String {
        return "ResLink: resource=$resource, file=$file"
    }
}
package ru.art2000.androraider.utils

import javafx.fxml.FXMLLoader
import javafx.scene.image.Image
import javafx.stage.FileChooser
import org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.DirectoryNotEmptyException
import java.nio.file.Files

public val FILE_CHOOSER_APK_FILTER = FileChooser.ExtensionFilter("Android app package", "*.apk")
public val FILE_CHOOSER_JAR_FILTER = FileChooser.ExtensionFilter("Java archive", "*.jar")

fun File.isSubFile(folder: File, canMatch: Boolean = false): Boolean {
    if (!folder.isDirectory || !folder.exists() || !exists())
        return false

    val canonical = canonicalFile
    val base = folder.canonicalFile

    if (!canMatch && this == folder) {
        return false
    }

    var parentFile: File? = if (canMatch) canonical else canonical.parentFile
    while (parentFile != null) {
        if (base == parentFile) {
            return true
        }

        parentFile = parentFile.parentFile
    }

    return false
}

fun File.compareTo(file: File, directoriesBefore: Boolean): Int {
    return if (directoriesBefore && (isDirectory != file.isDirectory)) {
        if (isDirectory) -1 else 1
    } else {
        compareTo(file)
    }
}

fun File.moveOrCopyDelete(dest: File): Boolean {
    try {
        Files.move(this.toPath(), dest.toPath())
        return true
    } catch (e: Exception) {
        try {
            if (isDirectory) {
                FileUtils.copyDirectory(this, dest)
                deleteRecursively()
            } else {
                FileUtils.copyFile(this, dest)
                delete()
            }
        } catch (copyDeleteException: Exception) {
            return false
        }
    }
    return true
}

fun Class<*>.getDrawable(name: String): Image? {
    var image: Image? = null
    try {
        image = Image(getResource(DRAWABLE_PATH + name).toString())
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        return image
    }
}

fun Class<*>.getLayout(name: String): FXMLLoader {
    return FXMLLoader(getResource(LAYOUT_PATH + name))
}

fun Class<*>.getStyle(name: String): String {
    return getResource(STYLE_PATH + name).toExternalForm()
}

fun Class<*>.getRawContent(name: String): ByteArray {
    val stream = getResource(RAW_PATH + name)?.openStream() ?: return ByteArray(0)
    val res = stream.readAllBytes()
    stream.close()
    return res
}

const val DRAWABLE_PATH = "/drawable/"
const val LAYOUT_PATH = "/layout/"
const val STYLE_PATH = "/style/"
const val RAW_PATH = "/raw/"
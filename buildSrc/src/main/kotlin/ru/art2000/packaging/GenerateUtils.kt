package ru.art2000.packaging

import org.gradle.api.Project
import org.gradle.internal.os.OperatingSystem
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.PosixFileAttributes
import java.util.*
import ru.art2000.packaging.linux.LinuxPackager

val Project.linuxExtraResourcesBuilt
    get() = project.buildDir.resolve("linux-extra")

fun insertProperties(text: String, properties: Properties): String {

    return properties.stringPropertyNames().fold(text) { currentText, propertyKey ->
        val value = properties.getProperty(propertyKey)?.toString() ?: return@fold currentText

        currentText.replace("{@$propertyKey}", value)
    }
}

fun generateLinuxResources(project: Project, currentOs : OperatingSystem) {
    if (!currentOs.isLinux) return
    val packager = LinuxPackager.getCurrentOsPackager()

    val configDir = project.rootDir.resolve("config")
    val resourcesDir = configDir.resolve("linux").resolve("resources")

    val buildProperties = PropertiesHelper.loadBuildProperties(project, packager)

    project.linuxExtraResourcesBuilt.deleteRecursively()
    project.linuxExtraResourcesBuilt.mkdirs()

    resourcesDir.walk().forEach {
        val fsPath = it.relativeTo(resourcesDir)

        if (it.extension == "template") {
            val parent = fsPath.parentFile
            val newName = fsPath.nameWithoutExtension
            val targetFile = project.linuxExtraResourcesBuilt.resolve(parent).resolve(newName)

            val templateContent = it.readText()
            val fileContent = insertProperties(templateContent, buildProperties)

            val targetFilePath = targetFile.toPath()

            Files.writeString(targetFilePath, fileContent)

            val permissions = Files.readAttributes(it.toPath(), PosixFileAttributes::class.java).permissions()
            Files.setPosixFilePermissions(targetFilePath, permissions)

        } else {
            val targetFile = project.linuxExtraResourcesBuilt.resolve(fsPath)
            Files.copy(
                it.toPath(), targetFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES
            )
        }
    }
}
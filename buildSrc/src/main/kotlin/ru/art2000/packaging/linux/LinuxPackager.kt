package ru.art2000.packaging.linux

import org.gradle.api.Project
import org.gradle.internal.os.OperatingSystem
import ru.art2000.packaging.*
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.PosixFileAttributes
import java.util.*

enum class LinuxPackager(
    val type: String,
    val testCommand: List<String>,
) : Distro {

    PACMAN("pacman", listOf("makepkg", "--help")) {
        override val outputFileFormat: String
            get() = "pkg.tar.zst"

        override fun getSourcesDir(workingDir: File, buildProperties: Properties): File {
            return workingDir.resolve("src").resolve(buildProperties.getProperty("package"))
        }

        override fun displayVersion(buildProperties: Properties): String {
            val mainPart = shortVersion(buildProperties)

            val verType = buildProperties.getProperty("version.type")

            return if (verType.isNullOrEmpty())
                mainPart
            else
                "$mainPart+$verType"
        }

        override fun packagingCommand(outputFile: File): List<String> {
            return listOf("makepkg", "-f")
        }

        override fun packageBuildDirectory(workDir: File, targetOutputFile: File): File {
            return workDir
        }
    },
    DEB("deb", listOf("dpkg-deb", "--help")) {
        override fun insertSpecificData(text: String, workingDir: File, buildProperties: Properties): String {

            val sourcesDir = getSourcesDir(workingDir, buildProperties)
            val sourcesSize = sourcesDir.walk().filter { it.isFile }.map { it.length() }.sum()

            val withSize = text.replace("{%size}", (sourcesSize / 1024).toString())

            return super.insertSpecificData(withSize, workingDir, buildProperties)
        }

        override fun packagingCommand(outputFile: File): List<String> {
            return listOf("dpkg-deb", "-b", ".", outputFile.absolutePath)
        }
    },
    RPM("rpm", listOf("rpmbuild", "--help")) {
        override fun getSourcesDir(workingDir: File, buildProperties: Properties): File {
            return workingDir.resolve("src")
        }

        override fun getPackagingDir(workingDir: File, buildProperties: Properties): File {
            return workingDir.resolve("pkg")
        }

        override fun insertSpecificData(text: String, workingDir: File, buildProperties: Properties): String {

            val sourcesDir = getSourcesDir(workingDir, buildProperties)
            val files = sourcesDir.walk().mapNotNull {
                if (it.isDirectory) return@mapNotNull null

                "/" + it.relativeTo(sourcesDir).path
            }.toList()

            val packageListFile = workingDir.resolve("filelist.txt")



            Files.write(packageListFile.toPath(), files)

            val withFileList = text.replace("{%fileList}", packageListFile.absolutePath)

            val verBuild = buildProperties.intProperty("build")
            val release = buildProperties.getProperty("version.type").let {
                if (it.isNullOrEmpty()) {
                    verBuild.toString()
                } else {
                    "$it.$verBuild"
                }
            }
            val withRelease = withFileList.replace("{%release}", release)

            return super.insertSpecificData(withRelease, workingDir, buildProperties)
        }

        override fun packagingCommand(outputFile: File): List<String> {
            return listOf("rpmbuild", "-bb", "spec", "--define", "_rpmdir ${outputFile.parentFile.absolutePath}")
        }

        override fun displayVersion(buildProperties: Properties): String = shortVersion(buildProperties)

    };

    override val configDirName: String
        get() = type

    @Suppress("INACCESSIBLE_TYPE")
    override val operatingSystem: OperatingSystem
        get() = OperatingSystem.LINUX

    protected open val outputFileFormat: String get() = type

    open fun getSourcesDir(workingDir: File, buildProperties: Properties): File = workingDir

    open fun getPackagingDir(workingDir: File, buildProperties: Properties): File = workingDir

    open fun getOutputFileName(buildProperties: Properties): String {

        val pkg = buildProperties.getProperty("package")
        val version = fullVersion(buildProperties)
        val arch = buildProperties.getProperty("arch")

        return "$pkg-$version-$arch.$outputFileFormat"
    }

    protected fun Properties.intProperty(prop: String): Int {
        val value = getProperty(prop) ?: throw IllegalStateException("missing property '$prop'")
        return value.toIntOrNull() ?: throw IllegalStateException("not integer property '$prop'")
    }

    open fun displayVersion(buildProperties: Properties): String = fullVersion(buildProperties)

    open fun insertSpecificData(text: String, workingDir: File, buildProperties: Properties): String {
        return text.replace("{%version}", displayVersion(buildProperties))
    }

    protected abstract fun packagingCommand(outputFile: File): List<String>

    protected open fun packageBuildDirectory(workDir: File, targetOutputFile: File): File {
        return targetOutputFile.parentFile
    }

    private fun Project.copyPkgFiles(targetRootDir: File, properties: Properties) {
        val sourceFilesDir = project.buildDir.resolve("image")
        val targetFilesDir = targetRootDir
            .resolve(properties.getProperty("installDir"))
            .resolve(properties.getProperty("package"))


        val sourceDirs = mapOf(
            sourceFilesDir to targetFilesDir,
            linuxExtraResourcesBuilt to targetRootDir
        )

        sourceDirs.forEach { (sourceDirectory, targetDirectory) ->
            sourceDirectory.walk().forEach {
                val fsPath = it.relativeTo(sourceDirectory)
                val targetFile = targetDirectory.resolve(fsPath)

                if (it.isDirectory) {
                    targetFile.mkdirs()
                } else {
                    Files.copy(
                        it.toPath(), targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES
                    )
                }
            }
        }
    }

    private fun installLinuxFiles(project: Project): Pair<File, File> {

        val packageBuildDir = project.buildDir.resolve("pkg").resolve(type)
        packageBuildDir.deleteRecursively()

        val workingDir = packageBuildDir.resolve("work")
        val outputDir = packageBuildDir.resolve("output")

        val configDir = project.rootDir.resolve("config")

        val buildProperties = PropertiesHelper.loadBuildProperties(project, this)

        val sourcesDir = getSourcesDir(workingDir, buildProperties)
        val packagingDir = getPackagingDir(workingDir, buildProperties)

        project.copyPkgFiles(sourcesDir, buildProperties)

        val packageSpecificConfigDir = configDir.resolve("linux").resolve(type)
        val packageSpecificControlsDir = packageSpecificConfigDir.resolve("controls")

        val packageFileName = getOutputFileName(buildProperties)
        val packageFile = outputDir.resolve(packageFileName)

        val locationsMap = mapOf(
            "linuxExtraResources" to project.linuxExtraResourcesBuilt,
            "projectRoot" to project.rootDir,
            "projectBuild" to project.buildDir,
            "sourcesDir" to sourcesDir,
            "workingDir" to workingDir,
            "packagingDir" to packagingDir,
            "outputFile" to packageFile
        )

        packageSpecificControlsDir.walk().forEach {
            insertPropertiesAndCopy(buildProperties, it, packageSpecificControlsDir, workingDir, locationsMap)
        }

        return workingDir to packageFile
    }

    private fun insertLocations(text: String, locations: Map<String, File>): String {
        return locations.entries.fold(text) { currentText, entry ->
            currentText.replace("{%${entry.key}}", entry.value.absolutePath)
        }
    }

    private fun insertPropertiesAndCopy(
        properties: Properties,
        sourceFile: File,
        sourceDir: File,
        targetDir: File,
        locations: Map<String, File> = emptyMap()
    ) {

        val fsPath = sourceFile.relativeTo(sourceDir)

        if (sourceFile.extension == "template") {
            val parent = fsPath.parentFile
            val newName = fsPath.nameWithoutExtension
            val targetFile = (parent?.let { targetDir.resolve(parent) } ?: targetDir).resolve(newName)

            val templateContent = sourceFile.readText()
            val fileContent = insertProperties(templateContent, properties).let {
                insertLocations(it, locations)
            }.let {
                insertSpecificData(it, targetDir, properties)
            }

            val targetFilePath = targetFile.toPath()

            Files.writeString(targetFilePath, fileContent)

            val permissions = Files.readAttributes(sourceFile.toPath(), PosixFileAttributes::class.java).permissions()
            Files.setPosixFilePermissions(targetFilePath, permissions)

        } else {
            val targetFile = targetDir.resolve(fsPath)

            if (sourceFile.isDirectory) {
                targetFile.mkdir()
            } else {
                Files.copy(
                    sourceFile.toPath(), targetFile.toPath(),
                    StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES
                )
            }
        }
    }

    fun runPackaging(project: Project) {
        val (workDir, outputFile) = installLinuxFiles(project)
        packageFiles(workDir, outputFile)
    }

    open fun packageFiles(workDir: File, outputFile: File) {
        val processBuilder = ProcessBuilder(
            packagingCommand(outputFile)
        ).directory(workDir)

        val logDir = workDir.parentFile.resolve("logs").apply {
            mkdirs()
        }

        processBuilder.redirectOutput(File(logDir, "output.txt"))
        processBuilder.redirectError(File(logDir, "error.txt"))

        outputFile.parentFile.deleteRecursively()
        outputFile.parentFile.mkdirs()

        val buildSuccessful = try {
            val process = processBuilder.start()

            val result = process.waitFor()
            if (result != 0) {
                System.err.println("Packaging failed. Exit code: $result")
            }
            result == 0
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

        val builtFileDir = packageBuildDirectory(workDir, outputFile)
        val builtFile = builtFileDir.walk().find {
            !it.isDirectory && it.name.endsWith(outputFileFormat)
        }

        if (builtFile == null) {
            System.err.println("Cannot locate built file. Try to find it yourself..")
        } else {
            println("Original built file location: ${builtFile.absolutePath}")
            builtFile.renameTo(outputFile)
        }

        outputFile.parentFile.listFiles()?.forEach {
            if (it.isDirectory || it != outputFile) {
                it.deleteRecursively()
            }
        }

        if (buildSuccessful) {
            workDir.deleteRecursively()
            println("Build finished. Output file: ${outputFile.absolutePath}")
        } else {
            System.err.println("Build failed. Build files are not deleted. Logs directory: ${logDir.absolutePath}")
        }
    }

    companion object {

        private fun execute(command: List<String>): Int {
            val processBuilder = ProcessBuilder(command)
            try {
                val process = processBuilder.start()
                return process.waitFor()
            } catch (_: Exception) {
            }

            return Int.MIN_VALUE
        }

        private fun executesWithoutError(command: List<String>): Boolean {
            return execute(command) == 0
        }

        fun getCurrentOsPackager(): LinuxPackager {
            return values().find { executesWithoutError(it.testCommand) }
                ?: throw IllegalStateException("Unsupported package manager or build tools are missing. " +
                        "Currently supported package managers: ${values().joinToString { it.type }}")
        }
    }
}
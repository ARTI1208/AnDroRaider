import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties
import java.io.FileInputStream
import java.nio.file.*
import java.nio.file.attribute.PosixFileAttributes
import java.util.Date
import ru.art2000.build.*

plugins {

    application
    java
    antlr

    kotlin("jvm") version "1.4.31"

    id("org.beryx.jlink") version "2.23.3"
}

fun File.properties(): Properties = Properties().also {
    it.load(FileInputStream(this))
}

fun File.properties(defaultProperties: Properties): Properties = Properties(defaultProperties).also {
    it.load(FileInputStream(this))
}

fun readVersion(properties: Properties): String {

    fun Properties.intProperty(prop: String): Int {
        val value = getProperty(prop) ?: throw IllegalStateException("missing property '$prop'")
        return value.toIntOrNull() ?: throw IllegalStateException("not integer property '$prop'")
    }

    val major = properties.intProperty("major")
    val minor = properties.intProperty("minor")
    val patch = properties.intProperty("patch")

    val shortVersion = if (patch == 0) "$major.$minor" else "$major.$minor.$patch"

    val os = org.gradle.internal.os.OperatingSystem.current()
    if (os.isMacOsX || isRpmDistro()) return shortVersion

    val type = properties.getProperty("type")
    val build = properties.intProperty("build")

    return if (type.isNullOrEmpty()) {
        shortVersion
    } else {
        "$shortVersion-$type.$build"
    }
}

val appPropertiesFile = file("src/main/resources/property/app.properties")
val appProperties = appPropertiesFile.properties()

group = "ru.art2000"
version = readVersion(appProperties)

application {
    mainModule.set("app")
    mainClass.set("ru.art2000.androraider.view.AnDroRaider")
}

dependencies {

    fun <T : ModuleDependency> T.removeJavaFxDependencies(): T {
        return exclude("org.openjfx")
    }

    antlr("org.antlr", "antlr4", "4.9")

    implementation("io.reactivex.rxjava2", "rxjava", "2.2.20")
    implementation("io.reactivex.rxjava2", "rxjavafx", "2.11.0-RC34")
        .removeJavaFxDependencies()

    implementation("commons-io", "commons-io", "2.8.0")

    // Use custom builds of ReactFX, Flowless and UndoFX that support modularity
    implementation("org.fxmisc.richtext", "richtextfx", "0.10.5")
        .removeJavaFxDependencies()
        .exclude("org.reactfx", "reactfx")
        .exclude("org.fxmisc.flowless", "flowless")
        .exclude("org.fxmisc.undo", "undofx")

    implementation("com.github.ARTI1208", "ReactFX", "v3.0.1-modularity")
        .removeJavaFxDependencies()
    implementation("com.github.ARTI1208", "Flowless", "v1.0-modularity")
        .removeJavaFxDependencies()
    implementation("com.github.ARTI1208", "UndoFX", "v3.0.1-modularity")
        .removeJavaFxDependencies()

//    GRADLE 6.4+ javafx workaround
    val jfxOptions = object {
        val group = "org.openjfx"
        val version = "15.0.1"
        val fxModules = listOf("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics")
    }
    jfxOptions.run {
        val osName = System.getProperty("os.name")
        val platform = when {
            osName.startsWith("Mac", ignoreCase = true) -> "mac"
            osName.startsWith("Windows", ignoreCase = true) -> "win"
            osName.startsWith("Linux", ignoreCase = true) -> "linux"
            else -> ""
        }
        fxModules.forEach {
            implementation(group, it.replace('.', '-'), version, classifier = platform)
        }
    }

}

val compileJava: JavaCompile by tasks
val compileKotlin: KotlinCompile by tasks
val jpackage: org.beryx.jlink.JPackageTask by tasks
jpackage.onlyIf { !os.isLinux }


compileJava.destinationDir = compileKotlin.destinationDir

compileKotlin.kotlinOptions {
    jvmTarget = "11"
}

val generateGrammarSource: AntlrTask by tasks
generateGrammarSource.apply {
    outputDirectory = File("${project.buildDir}/generated-src/antlr/main/ru/art2000/androraider/antlr")
    arguments.plusAssign(listOf("-visitor", "-long-messages", "-package", "ru.art2000.androraider.antlr"))
}

compileKotlin.dependsOn(generateGrammarSource.name)

java {
//    GRADLE 6.4+
    modularity.inferModulePath.set(true)
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes(
            "Main-Class" to "ru.art2000.androraider.view.AnDroRaider"
        )
    }
}

val os = org.gradle.internal.os.OperatingSystem.current()
val linuxExtraResourcesBuilt = project.buildDir.resolve("linux-extra")
val archBuildDir = project.buildDir.resolve("pkg-arch")
val debBuildDir = project.buildDir.resolve("pkg-deb")

jlink {
    launcher {
        name = appProperties.getProperty("name")
    }

    mergedModuleName.set("androraider")

    mergedModule {
        requires("javafx.graphics")
        requires("javafx.controls")
    }

    addExtraDependencies("javafx")
    forceMerge("Flowless", "ReactFX", "UndoFX", "kotlin")

    jpackage {

        val configDir = project.rootDir.resolve("config")
        val buildProperties = configDir.resolve("build.properties").properties()

        val osConfigDir = when {
            os.isWindows -> "win"
            os.isMacOsX -> "mac"
            else -> "linux"
        }

        val iconFormat = when {
            os.isWindows -> "ico"
            os.isMacOsX -> "icns"
            else -> "png"
        }

        val logoRelativePath = "$osConfigDir/AnDroRaider.$iconFormat"
        val logoAbsolutePath = configDir.resolve(logoRelativePath)
        icon = logoAbsolutePath.absolutePath

        if (os.isWindows) {
            installerOptions = listOf(
                "--win-dir-chooser",
                "--win-menu",
                "--win-menu-group", buildProperties.getProperty("winMenuDir")
            )
        } else if (os.isLinux) {
            installerType = os.getLinuxInstallerType() ?: return@jpackage

            installerOptions = listOf(
                "--linux-shortcut",
                "--resource-dir", linuxExtraResourcesBuilt.absolutePath,
                "--linux-deb-maintainer", buildProperties.getProperty("email"),
                "--description", buildProperties.getProperty("description"),
                "--vendor", buildProperties.getProperty("developer"),
                "--verbose"
            )
        }
    }
}

fun executesWithoutError(command: List<String>): Boolean {
    val processBuilder = ProcessBuilder(command)
    try {
        val process = processBuilder.start()
        return process.waitFor() == 0
    } catch (_: Exception) {
    }

    return false
}

fun org.gradle.internal.os.OperatingSystem.getLinuxInstallerType(): String? {
    if (!isLinux) return null

    data class LinuxInstaller(val type: String, val testCommand: List<String>)

    val installers = listOf(
        LinuxInstaller("deb", listOf("dpkg-deb", "--help")),
        LinuxInstaller("rpm", listOf("rpmbuild", "--help")),
        LinuxInstaller("arch", listOf("makepkg", "--help"))
    )

    return installers.firstOrNull { executesWithoutError(it.testCommand) }?.type
}

fun Task.checkJPackageAvailable() {
    doFirst {
        if (JavaVersion.current() < JavaVersion.VERSION_14) {
            throw GradleException("JPackage is only available staring with jdk14. Current version: ${JavaVersion.current()}")
        }
    }
}

tasks.withType(org.beryx.jlink.JPackageTask::class) {
    checkJPackageAvailable()
}

tasks.withType(org.beryx.jlink.JPackageImageTask::class) {
    checkJPackageAvailable()
}

fun insertProperties(text: String, properties: Properties): String {
    var result = text

    properties.stringPropertyNames().forEach { key ->
        val value = properties.getProperty(key)?.toString() ?: return@forEach

        result = result.replace("{@$key}", value)
    }

    return result
}

val generateLinuxResources = task("generateLinuxResources") {
    val configDir = project.rootDir.resolve("config")
    val resourcesDir = configDir.resolve("linux").resolve("resources")

    val buildProperties = os.loadBuildProperties()

    linuxExtraResourcesBuilt.deleteRecursively()
    linuxExtraResourcesBuilt.mkdirs()

    resourcesDir.walk().forEach {
        val fsPath = it.relativeTo(resourcesDir)

        if (it.extension == "template") {
            val parent = fsPath.parentFile
            val newName = fsPath.nameWithoutExtension
            val targetFile = linuxExtraResourcesBuilt.resolve(parent).resolve(newName)

            val templateContent = it.readText()
            val fileContent = insertProperties(templateContent, buildProperties)

            val targetFilePath = targetFile.toPath()

            Files.writeString(targetFilePath, fileContent)

            val permissions = Files.readAttributes(it.toPath(), PosixFileAttributes::class.java).permissions()
            Files.setPosixFilePermissions(targetFilePath, permissions)

        } else {
            val targetFile = linuxExtraResourcesBuilt.resolve(fsPath)
            Files.copy(
                it.toPath(), targetFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES
            )
        }
    }

}

fun isRpmDistro(): Boolean {
    return executesWithoutError(listOf("rpmbuild", "--help"))
}

fun isArch(): Boolean {
    return executesWithoutError(listOf("makepkg", "--help"))
}

fun org.gradle.internal.os.OperatingSystem.loadBuildProperties(): Properties {
    val configDir = project.rootDir.resolve("config")

    var properties = configDir.resolve("build.properties").properties()

//    if (isLinux) {
//        val linuxConfigDir = configDir.resolve("linux")
//        if (isArch()) {
//            val archConfigDir = linuxConfigDir.resolve("arch")
//            val archProperties = archConfigDir.resolve("arch_build.properties").properties(properties)
//            properties = archProperties
//        }
//    }

    return properties
}

fun copyPkgFiles(targetRootDir: File, properties: Properties) {
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

fun installArchFiles(): File {
    linuxExtraResourcesBuilt.mkdirs()

    val configDir = project.rootDir.resolve("config")
    val archConfigDir = configDir.resolve("linux").resolve("arch")

    val buildProperties = configDir.resolve("build.properties").properties()

    val pkgbuildTemplateContent = archConfigDir.resolve("PKGBUILD.template").readText()
    val pkgbuildFileContent = insertProperties(pkgbuildTemplateContent, buildProperties)

    val pkgDir = archBuildDir.resolve("pkg")

    pkgDir.mkdirs()

    copyPkgFiles(pkgDir, buildProperties)

    val pkgbuildFile = archBuildDir.resolve("PKGBUILD")
    Files.writeString(pkgbuildFile.toPath(), pkgbuildFileContent)

    return archBuildDir
}

val packageArch = task("packageArch") {
    onlyIf { isArch() }
    val jlink: org.beryx.jlink.JlinkTask by tasks
    dependsOn(generateLinuxResources, jlink)
    doLast {
        val pkgbuildDir = installArchFiles()

        val processBuilder = ProcessBuilder(
            listOf("makepkg", "-f")
        ).directory(pkgbuildDir)

        val logDir = pkgbuildDir.resolve("logs").apply {
            mkdirs()
        }

        processBuilder.redirectOutput(File(logDir, "output.txt"))
        processBuilder.redirectError(File(logDir, "error.txt"))

        try {
            val process = processBuilder.start()

            val result = process.waitFor()
            System.err.println("MakePkgRes: $result")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun installDebFiles(): Pair<File, File> {
    debBuildDir.deleteRecursively()

    val configDir = project.rootDir.resolve("config")
    val debConfigDir = configDir.resolve("linux").resolve("deb")

    val buildProperties = configDir.resolve("build.properties").properties()

    val controlTemplateContent = debConfigDir.resolve("control.template").readText()
    val controlFileContent = insertProperties(controlTemplateContent, buildProperties)

    val debPkgDir = debBuildDir.resolve("pkg")

    val controlParentDir = debPkgDir.resolve("DEBIAN")
    controlParentDir.mkdirs()

    val controlFile = controlParentDir.resolve("control")
    Files.writeString(controlFile.toPath(), controlFileContent)

    copyPkgFiles(debPkgDir, buildProperties)

    val pkg = buildProperties.getProperty("package")
    val verMajor = buildProperties.getProperty("version.major")
    val verMinor = buildProperties.getProperty("version.minor")
    val verPatch = buildProperties.getProperty("version.patch")
    val verType = buildProperties.getProperty("version.type")
    val verBuild = buildProperties.getProperty("build")
    val arch = buildProperties.getProperty("arch")

    val debFileName = "$pkg-$verMajor.$verMinor.$verPatch-$verType.$verBuild-$arch.deb"
    val debFile = debBuildDir.resolve(debFileName)

    return debPkgDir to debFile
}

val packageDeb = task("packageDeb") {

    val jlink: org.beryx.jlink.JlinkTask by tasks
    dependsOn(generateLinuxResources, jlink)
    doLast {
        val (workDir, debFile) = installDebFiles()

        val processBuilder = ProcessBuilder(
            listOf("dpkg-deb", "-b", ".", debFile.absolutePath)
        ).directory(workDir)

        val logDir = project.buildDir.resolve("logs").resolve("deb").apply {
            mkdirs()
        }

        processBuilder.redirectOutput(File(logDir, "output.txt"))
        processBuilder.redirectError(File(logDir, "error.txt"))

        try {
            val process = processBuilder.start()

            val result = process.waitFor()
            System.err.println("MakePkgRes: $result")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

val linuxPackageTasks: Map<String, Any> = mapOf(
    "arch" to packageArch,
    "deb" to packageDeb
)


fun getLinuxProperties(configDir: File, packageType: String): Properties {

    var properties = configDir.resolve("build.properties").properties()

    val linuxConfigDir = configDir.resolve("linux")
    val packageSpecificPropertiesFile = linuxConfigDir.resolve(packageType).resolve("build.properties")
    if (packageSpecificPropertiesFile.exists()) {
        val packageSpecificProperties = packageSpecificPropertiesFile.properties(properties)
        properties = packageSpecificProperties
    }

    return properties
}

fun insertLocations(text: String, locations: Map<String, File>): String {
    return locations.entries.fold(text) { currentText, entry ->
        currentText.replace("{%${entry.key}}", entry.value.absolutePath)
    }
}

fun LinuxPackager.insertPropertiesAndCopy(
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

fun installLinuxFiles(packager: LinuxPackager): Pair<File, File> {
    val packageType = packager.type

    val packageBuildDir = project.buildDir.resolve("pkg").resolve(packageType)
    packageBuildDir.deleteRecursively()

    val workingDir = packageBuildDir.resolve("work")
    val outputDir = packageBuildDir.resolve("output")

    val configDir = project.rootDir.resolve("config")

    val buildProperties = getLinuxProperties(configDir, packageType)

    val sourcesDir = packager.getSourcesDir(workingDir, buildProperties)
    val packagingDir = packager.getPackagingDir(workingDir, buildProperties)

    copyPkgFiles(sourcesDir, buildProperties)

    val packageSpecificConfigDir = configDir.resolve("linux").resolve(packageType)
    val packageSpecificControlsDir = packageSpecificConfigDir.resolve("controls")

    val packageFileName = packager.getOutputFileName(buildProperties)
    val packageFile = outputDir.resolve(packageFileName)

    val locationsMap = mapOf(
        "linuxExtraResources" to linuxExtraResourcesBuilt,
        "projectRoot" to project.rootDir,
        "projectBuild" to project.buildDir,
        "sourcesDir" to sourcesDir,
        "workingDir" to workingDir,
        "packagingDir" to packagingDir,
        "outputFile" to packageFile
    )

    packageSpecificControlsDir.walk().forEach {
        packager.insertPropertiesAndCopy(buildProperties, it, packageSpecificControlsDir, workingDir, locationsMap)
    }

    return workingDir to packageFile
}

enum class LinuxPackager(
    val type: String,
    val testCommand: List<String>,
    val packageCommand: List<String> = emptyList()
) {

    ARCH("arch", listOf("makepkg", "--help"), listOf("makepkg", "-f")) {

        override val outputFileFormat: String
            get() = "pkg.tar.zst"

        override fun getSourcesDir(workingDir: File, buildProperties: Properties): File {
            return workingDir.resolve("src").resolve(buildProperties.getProperty("package"))
        }

    },
    DEB("deb", listOf("dpkg-deb", "--help"), listOf("dpkg-deb", "-b", ".")) {

        override fun insertSpecificData(text: String, workingDir: File, buildProperties: Properties): String {

            val sourcesDir = getSourcesDir(workingDir, buildProperties)
            val sourcesSize = sourcesDir.walk().filter { it.isFile }.map { it.length() }.sum()

            val withSize = text.replace("{%size}", (sourcesSize / 1024).toString())

            return super.insertSpecificData(withSize, workingDir, buildProperties)
        }

    },
    RPM("rpm", listOf("rpmbuild", "--help"), listOf("rpmbuild", "-bb", "spec", "--define")) {

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

        override fun actualPackageCommand(outputFile: File): List<String> {
            return packageCommand + "_rpmdir ${outputFile.parentFile.absolutePath}"
        }

        override fun displayVersion(buildProperties: Properties): String {
            val verMajor = buildProperties.intProperty("version.major")
            val verMinor = buildProperties.intProperty("version.minor")
            val verPatch = buildProperties.intProperty("version.patch")

            return if (verPatch == 0) {
                "$verMajor.$verMinor"
            } else {
                "$verMajor.$verMinor.$verPatch"
            }
        }
    };

    protected open inline val outputFileFormat: String get() = type

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

    private fun fullVersion(buildProperties: Properties): String {
        val verMajor = buildProperties.intProperty("version.major")
        val verMinor = buildProperties.intProperty("version.minor")
        val verPatch = buildProperties.intProperty("version.patch")
        val verType = buildProperties.getProperty("version.type")
        val verBuild = buildProperties.intProperty("build")

        val mainPart = if (verPatch == 0) {
            "$verMajor.$verMinor"
        } else {
            "$verMajor.$verMinor.$verPatch"
        }

        return if (verType.isNullOrEmpty()) {
            mainPart
        } else {
            "$mainPart-$verType.$verBuild"
        }
    }

    protected open fun displayVersion(buildProperties: Properties): String = fullVersion(buildProperties)

    open fun insertSpecificData(text: String, workingDir: File, buildProperties: Properties): String {
        return text.replace("{%version}", displayVersion(buildProperties))
    }

    protected open fun actualPackageCommand(outputFile: File): List<String> = packageCommand + outputFile.absolutePath

    open fun packageFiles(workDir: File, outputFile: File) {
        val processBuilder = ProcessBuilder(
            actualPackageCommand(outputFile)
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

        val builtFile = outputFile.parentFile.walk().find {
            !it.isDirectory && it.name.endsWith(outputFileFormat)
        }

        builtFile?.apply { renameTo(outputFile) }

        outputFile.parentFile.listFiles()?.forEach {
            if (it.isDirectory || it != outputFile) {
                it.deleteRecursively()
            }
        }

        workDir.deleteRecursively()

        if (buildSuccessful) {
            println("Build finished. Output file: ${outputFile.absolutePath}")
        } else {
            System.err.println("Build failed. Logs directory: ${logDir.absolutePath}")
        }
    }
}

val packageLinux = task("packageLinux") {
    dependsOn("jlink", generateLinuxResources)

    val linuxPackager = LinuxPackager.values().firstOrNull { executesWithoutError(it.testCommand) }

    onlyIf { os.isLinux && linuxPackager != null }

    linuxPackager ?: return@task

    doLast {
        val (workDir, outputFile) = installLinuxFiles(linuxPackager)
        linuxPackager.packageFiles(workDir, outputFile)
    }
}

val universalPackage = task("universalPackage") {
    BuildUtils.someFunction()
    if (os.isLinux) {
        dependsOn(packageLinux)
    } else {
        dependsOn(jpackage)
    }
}

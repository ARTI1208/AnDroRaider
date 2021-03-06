import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.Properties
import java.io.FileInputStream
import java.nio.file.*

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
    if (os.isMacOsX) return shortVersion

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
val linuxExtraResources = project.buildDir.resolve("linux-extra")
val archBuildDir = project.buildDir.resolve("arch-pkg")

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
                "--resource-dir", linuxExtraResources.absolutePath,
                "--linux-deb-maintainer", buildProperties.getProperty("email"),
                "--description", buildProperties.getProperty("description"),
                "--vendor", buildProperties.getProperty("developer"),
                "--verbose"
            )
        }
    }
}

fun executeNoError(command: List<String>): Boolean {
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
        LinuxInstaller("rpm", listOf("rpmbuild", "--help"))
    )

    return installers.firstOrNull { executeNoError(it.testCommand) }?.type
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
    properties.entries.forEach {
        val key = it.key as? String ?: return@forEach
        val value = it.value?.toString() ?: return@forEach

        result = result.replace("{@$key}", value)
    }

    return result
}

val generateLinuxResources = task("generateLinuxResources") {
    val configDir = project.rootDir.resolve("config")
    val linuxConfigDir = configDir.resolve("linux")

    val buildProperties = configDir.resolve("build.properties").properties()

    val appName = buildProperties.getProperty("name")
    val appPackage = buildProperties.getProperty("package")

    val desktopTemplateContent = linuxConfigDir.resolve("template.desktop").readText()
    val desktopFileContent = insertProperties(desktopTemplateContent, buildProperties)

    linuxExtraResources.mkdirs()

    val desktopFile = linuxExtraResources.resolve("$appName.desktop")
    Files.writeString(desktopFile.toPath(), desktopFileContent)

    val sourceIcon = project.rootDir.resolve(buildProperties.getProperty("pngIcon"))
    val targetIcon = linuxExtraResources.resolve("$appName.png")
    Files.copy(sourceIcon.toPath(), targetIcon.toPath(), StandardCopyOption.REPLACE_EXISTING)

    val execTemplateContent = linuxConfigDir.resolve("template.exec.sh").readText()
    val execFileContent = insertProperties(execTemplateContent, buildProperties)

    val execScriptFile = linuxExtraResources.resolve("$appPackage.sh")
    Files.writeString(execScriptFile.toPath(), execFileContent)
}

val generatePKGBUILD = task("generatePKGBUILD") {
    generatePkgbuild()
}

fun generatePkgbuild(): File {
    linuxExtraResources.mkdirs()

    val configDir = project.rootDir.resolve("config")
    val archConfigDir = configDir.resolve("linux").resolve("arch")

    val buildProperties = configDir.resolve("build.properties").properties()

    val pkgbuildTemplateContent = archConfigDir.resolve("template.PKGBUILD").readText()
    val pkgbuildFileContent = insertProperties(pkgbuildTemplateContent, buildProperties)
        .replace("{%linuxExtraResources}", linuxExtraResources.absolutePath)
        .replace("{%projectRoot}", project.rootDir.absolutePath)
        .replace("{%projectBuild}", project.buildDir.absolutePath)

    archBuildDir.mkdirs()

    val pkgbuildFile = archBuildDir.resolve("PKGBUILD")
    Files.writeString(pkgbuildFile.toPath(), pkgbuildFileContent)

    return archBuildDir
}

task("packageArch") {
    onlyIf { executeNoError(listOf("makepkg", "--help")) }
    dependsOn(generateLinuxResources)
    doLast {
        val pkgbuildDir = generatePkgbuild()
        val processBuilder = ProcessBuilder(listOf("makepkg", "-f", pkgbuildDir.absolutePath))
        try {
            val process = processBuilder.start()
            process.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

if (os.isLinux) {
    val jpackage: org.beryx.jlink.JPackageTask by tasks
    jpackage.dependsOn(generateLinuxResources)
}
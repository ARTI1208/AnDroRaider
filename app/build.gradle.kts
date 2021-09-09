import org.gradle.internal.os.OperatingSystem
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.art2000.packaging.*

plugins {

    application
    java
    antlr

    kotlin("jvm")

    id("org.beryx.jlink") version "2.24.1"
    id("de.jjohannes.extra-java-module-info") version "0.9"
}

val baseAppProperties = PropertiesHelper.loadBaseProperties(project)

group = "ru.art2000"
version = shortVersion(baseAppProperties)

application {
    mainModule.set("app")
    mainClass.set("ru.art2000.androraider.view.AnDroRaider")
}

val flowlessVersion = "0.6.5"
val reactfxVersion = "2.0-M5"
val coroutinesVersion = "1.5.2"

dependencies {

    antlr("org.antlr", "antlr4", "4.9.2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:$coroutinesVersion")

//    implementation("io.reactivex.rxjava2", "rxjava", "2.2.20")
//    implementation("io.reactivex.rxjava2", "rxjavafx", "2.11.0-RC34")

    implementation("commons-io", "commons-io", "2.11.0")

    // Explicit implementation of ReactFX, Flowless and UndoFX for using concrete versions
    implementation("org.fxmisc.richtext", "richtextfx", "0.10.6")
        .exclude("org.reactfx", "reactfx")
        .exclude("org.fxmisc.flowless", "flowless")
        .exclude("org.fxmisc.undo", "undofx")


    implementation("org.fxmisc.flowless", "flowless", flowlessVersion) // Declare module via plugin
    implementation("org.reactfx", "reactfx", reactfxVersion) // Declare module via plugin
    implementation("org.fxmisc.undo", "undofx", "2.1.1") // Has automatic module name

    implementation(project(":common"))

//    GRADLE 6.4+ javafx workaround
    val jfxOptions = object {
        val group = "org.openjfx"
        val version = "16"
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

val compileJava by tasks.getting(JavaCompile::class) {
    doFirst {
        val baseBuildProps = project.rootDir.resolve("config").resolve(PropertiesHelper.propertiesFileName)
        val appProperties = project.projectDir
            .resolve("src")
            .resolve("main")
            .resolve("resources")
            .resolve("property")
            .resolve("app.properties")

        baseBuildProps.copyTo(appProperties, overwrite = true)
    }
}

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

jlink {
    launcher {
        name = baseAppProperties.getProperty("name")
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

        val currentOs = OperatingSystem.current()

        val logoRelativePath = "${currentOs.configDirName}/AnDroRaider.${currentOs.iconFormat}"
        val logoAbsolutePath = configDir.resolve(logoRelativePath)
        icon = logoAbsolutePath.absolutePath

        val buildProperties = PropertiesHelper.loadBuildProperties(project, currentOs.distro)

        if (currentOs.isWindows) {

            installerOptions.plusAssign(
                listOf(
                    "--win-dir-chooser",
                    "--win-menu",
                    "--win-menu-group", buildProperties.getProperty("winMenuDir")
                )
            )
        }
    }
}

fun Task.checkJPackageAvailable() {
    doFirst {
        if (JavaVersion.current() < JavaVersion.VERSION_14) {
            throw GradleException("JPackage is only available staring with jdk14. Current version: ${JavaVersion.current()}")
        }
    }
}

tasks.withType(org.beryx.jlink.JPackageTask::class) {
    onlyIf { !OperatingSystem.current().isLinux }
    checkJPackageAvailable()
}

tasks.withType(org.beryx.jlink.JPackageImageTask::class) {
    onlyIf { !OperatingSystem.current().isLinux }
    checkJPackageAvailable()
}

val generateLinuxResources = task("generateLinuxResources") {
    onlyIf { OperatingSystem.current().isLinux }
    doLast {
        generateLinuxResources(project, OperatingSystem.current())
    }
}

val packageLinux = task("packageLinux") {
    dependsOn("jlink", generateLinuxResources)
    onlyIf { OperatingSystem.current().isLinux}
    doLast {
        packageAppForLinux(project)
    }
}

val universalPackage = task("universalPackage") {
    if (OperatingSystem.current().isLinux) {
        dependsOn(packageLinux)
    } else {
        val jpackage: org.beryx.jlink.JPackageTask by tasks
        dependsOn(jpackage)
    }
}


extraJavaModuleInfo {

    failOnMissingModuleInfo.set(false)

    automaticModule("flowless-$flowlessVersion.jar", "org.fxmisc.flowless")
    automaticModule("reactfx-$reactfxVersion.jar", "reactfx")
    automaticModule("kotlinx-coroutines-core-jvm-$coroutinesVersion.jar", "kotlinx.coroutines.core.jvm")
    automaticModule("kotlinx-coroutines-javafx-$coroutinesVersion.jar", "kotlinx.coroutines.javafx")
}
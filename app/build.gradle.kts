import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    application
    java
    antlr

    kotlin("jvm") version "1.4.31"

    id("org.beryx.jlink") version "2.23.3"
}

group = "ru.art2000"
version = "0.1"

application {
    mainModule.set("app")
    mainClass.set("ru.art2000.androraider.view.AnDroRaider")
}

dependencies {
    antlr("org.antlr", "antlr4", "4.9")
    implementation("io.reactivex.rxjava2", "rxjava", "2.2.20")
    implementation("io.reactivex.rxjava2", "rxjavafx", "2.11.0-RC34")
    implementation("commons-io", "commons-io", "2.8.0")

    // Use custom builds of ReactFX, Flowless and UndoFX that support modularity
    implementation("org.fxmisc.richtext", "richtextfx", "0.10.5")
        .exclude("org.reactfx", "reactfx")
        .exclude("org.fxmisc.flowless", "flowless")
        .exclude("org.fxmisc.undo", "undofx")

    implementation("com.github.ARTI1208:ReactFX:v3.0.1-modularity")
    implementation("com.github.ARTI1208:Flowless:v1.0-modularity")
    implementation("com.github.ARTI1208:UndoFX:v3.0.1-modularity")

//    GRADLE 6.4+ javafx workaround
    val jfxOptions = object {
        val group = "org.openjfx"
        val version = "15.0.1"
        val fxModules = listOf("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics", "javafx.swing")
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

jlink {
    launcher {
        name = "AnDroRaider"
    }

    mergedModuleName.set("androraider")

    mergedModule {
        requires("javafx.graphics")
        requires("javafx.controls")
    }

    forceMerge("Flowless", "ReactFX", "UndoFX", "kotlin")

    jpackage {

        val os = org.gradle.internal.os.OperatingSystem.current()

        if (os.isUnix && !os.isMacOsX) {
            val logoInModule = File("src/main/resources/drawable/logo.png")
            val logoAbsolute = project.projectDir.resolve(logoInModule)
            icon = logoAbsolute.absolutePath
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
    checkJPackageAvailable()
}

tasks.withType(org.beryx.jlink.JPackageImageTask::class) {
    checkJPackageAvailable()
}
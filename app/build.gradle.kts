import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

    application
    java
    antlr

    kotlin("jvm") version "1.4.20"

//  not working properly with GRADLE 6.4+
    id("org.openjfx.javafxplugin") version "0.0.9"
    id("org.beryx.jlink") version "2.23.3"
}

group = "ru.art2000"
version = "0.1"

application {
//    GRADLE 6.4+
//    mainModule.set("app")
//    mainClass.set("ru.art2000.androraider.view.AnDroRaider")

//    GRADLE <6.4
    mainClassName = "app/ru.art2000.androraider.view.AnDroRaider"
}

dependencies {
    antlr("org.antlr", "antlr4", "4.9")
    implementation("org.jetbrains.kotlin", "kotlin-stdlib-jdk8")
    implementation("org.fxmisc.richtext", "richtextfx", "0.10.5")
    implementation("io.reactivex.rxjava2", "rxjava", "2.2.20")
    implementation("io.reactivex.rxjava2", "rxjavafx", "2.2.2")
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.4.2")
    implementation("commons-io", "commons-io", "2.8.0")

    implementation("no.tornado", "tornadofx", "1.7.20")

//    GRADLE 6.4+ javafx workaround
//    val jfxOptions = object {
//        val group = "org.openjfx"
//        val version = "15.0.1"
//        val fxModules = listOf("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics")
//    }
//    jfxOptions.run {
//        val osName = System.getProperty("os.name")
//        val platform = when {
//            osName.startsWith("Mac", ignoreCase = true) -> "mac"
//            osName.startsWith("Windows", ignoreCase = true) -> "win"
//            osName.startsWith("Linux", ignoreCase = true) -> "linux"
//            else -> ""
//        }
//        fxModules.forEach {
//            implementation(group, it.replace('.', '-'), version, classifier = platform)
//        }
//    }

}

val compileJava: JavaCompile by tasks
val compileKotlin: KotlinCompile by tasks


compileJava.destinationDir = compileKotlin.destinationDir

compileKotlin.kotlinOptions {
    languageVersion = "1.4"
    jvmTarget = "11"
    freeCompilerArgs = listOf("-Xjvm-default=compatibility")
}

project.gradle.startParameter.excludedTaskNames.add("generateGrammarSource")

java {
//    GRADLE 6.4+
//    modularity.inferModulePath.set(true)
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes(
            "Main-Class" to "ru.art2000.androraider.view.AnDroRaider"
        )
    }
}

javafx {
    version = "15"
    modules = listOf("javafx.base", "javafx.controls", "javafx.fxml", "javafx.graphics")
}

jlink {
    launcher {
        name = "AnDroRaider"
    }

    addExtraDependencies("javafx")
}
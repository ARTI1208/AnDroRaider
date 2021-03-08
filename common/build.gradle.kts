plugins {
    java
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

java {
//    GRADLE 6.4+
    modularity.inferModulePath.set(true)
}

val compileJava: JavaCompile by tasks
val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks


compileJava.destinationDir = compileKotlin.destinationDir

compileKotlin.kotlinOptions {
    jvmTarget = "11"
}
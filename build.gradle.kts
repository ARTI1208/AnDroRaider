plugins {
    kotlin("jvm") version "1.4.31" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://dl.bintray.com/reactivex/RxJava/")
        maven("https://jitpack.io")
    }
}
plugins {
    kotlin("jvm") version "1.5.21" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://dl.bintray.com/reactivex/RxJava/")
    }
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "7.0"
}
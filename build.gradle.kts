plugins {
    kotlin("jvm") version "1.8.0" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

tasks.named<Wrapper>("wrapper") {
    distributionType = Wrapper.DistributionType.ALL
    gradleVersion = "7.2"
}
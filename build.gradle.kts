plugins {
    kotlin("jvm") version "1.6.21" apply false
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
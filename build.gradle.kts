plugins {
    kotlin("jvm") version "1.5.31" apply false
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
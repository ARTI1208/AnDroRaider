package ru.art2000.packaging

import java.util.*

fun Properties.intProperty(prop: String): Int {
    val value = getProperty(prop) ?: throw IllegalStateException("missing property '$prop'")
    return value.toIntOrNull() ?: throw IllegalStateException("not integer property '$prop'")
}

fun shortVersion(buildProperties: Properties): String {
    val verMajor = buildProperties.intProperty("version.major")
    val verMinor = buildProperties.intProperty("version.minor")
    val verPatch = buildProperties.intProperty("version.patch")

    return if (verPatch == 0) {
        "$verMajor.$verMinor"
    } else {
        "$verMajor.$verMinor.$verPatch"
    }
}

fun fullVersion(buildProperties: Properties): String {
    val verType = buildProperties.getProperty("version.type")
    val verBuild = buildProperties.intProperty("build")

    val mainPart = shortVersion(buildProperties)

    return if (verType.isNullOrEmpty()) {
        mainPart
    } else {
        "$mainPart-$verType.$verBuild"
    }
}
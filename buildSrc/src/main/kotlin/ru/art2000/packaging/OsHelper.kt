package ru.art2000.packaging

import org.gradle.internal.os.OperatingSystem

val OperatingSystem.configDirName: String
    get() = when {
        isWindows -> "win"
        isMacOsX -> "mac"
        isLinux -> "linux"
        else -> "unix"
    }

val OperatingSystem.iconFormat: String
    get() = when {
        isWindows -> "ico"
        isMacOsX -> "icns"
        else -> "png"
    }

val OperatingSystem.distro: Distro?
    get() = when {
        isWindows -> WindowsDistro
        isMacOsX -> MacDistro
        else -> null
    }
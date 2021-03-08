package ru.art2000.packaging

import org.gradle.internal.os.OperatingSystem
import ru.art2000.packaging.linux.LinuxPackager
import java.util.*

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

fun versionForOs(os: OperatingSystem, properties: Properties): String {
    return when {
        os.isWindows -> fullVersion(properties)
        os.isMacOsX -> shortVersion(properties)
        os.isLinux -> LinuxPackager.getCurrentOsPackager().displayVersion(properties)
        else -> throw IllegalArgumentException("This os ($os) is not supported")
    }
}
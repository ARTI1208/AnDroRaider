package ru.art2000.packaging

import org.gradle.internal.os.OperatingSystem

interface Distro {

    val configDirName: String?

    val operatingSystem: OperatingSystem

}

@Suppress("INACCESSIBLE_TYPE")
object WindowsDistro : Distro {

    override val configDirName: String?
        get() = null

    override val operatingSystem: OperatingSystem
        get() = OperatingSystem.WINDOWS
}

@Suppress("INACCESSIBLE_TYPE")
object MacDistro : Distro {

    override val configDirName: String?
        get() = null

    override val operatingSystem: OperatingSystem
        get() = OperatingSystem.MAC_OS
}
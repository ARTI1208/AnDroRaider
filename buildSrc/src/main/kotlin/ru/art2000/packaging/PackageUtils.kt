package ru.art2000.packaging

import org.gradle.api.Project
import org.gradle.internal.os.OperatingSystem
import ru.art2000.packaging.linux.LinuxPackager

fun packageAppForLinux(project: Project) {
    if (!OperatingSystem.current().isLinux) return

    val linuxPackager = LinuxPackager.getCurrentOsPackager()

    linuxPackager.runPackaging(project)
}
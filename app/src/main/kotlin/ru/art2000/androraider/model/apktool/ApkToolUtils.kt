package ru.art2000.androraider.model.apktool

import org.apache.commons.io.IOUtils
import ru.art2000.androraider.model.io.StreamOutput
import ru.art2000.androraider.model.settings.SettingsManager
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import ru.art2000.androraider.view.settings.Settings
import java.io.File
import java.io.StringWriter
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.concurrent.thread


fun <E> MutableList<E>.addAll(vararg els: E) {
    els.forEach {
        this.add(it)
    }
}

typealias ApktoolCommandVerifier = (ApktoolCommand) -> Boolean

class ApkToolUtils {

    companion object {

        private val apktool: File?
            get() {
                val tmpPath = SettingsManager(Settings::class.java).getString(SettingsPresenter.KEY_APKTOOL_PATH)
                return if (tmpPath == null || !File(tmpPath).exists()) null else File(tmpPath)
            }

        @JvmStatic
        fun decompile(apk: File, vararg options: ApktoolCommand, output: StreamOutput? = null): File? {
            var appDecompileFolder: File? = null
            val commands = ArrayList<String>(listOf(ApktoolCommand.Decompiler.TAG))
            commands.addAll(optionsAsStringList(options.toList(), verifier = {
                if (it.tag == ApktoolCommand.General.FRAMEWORK_FOLDER_PATH) {
                    installFramework(File(it.value!!))
                    return@optionsAsStringList false
                } else if (it.tag == ApktoolCommand.General.OUTPUT) {
                    appDecompileFolder = File(it.value!!)
                }

                return@optionsAsStringList true
            }))

            if (appDecompileFolder == null) {
                appDecompileFolder = File(apk.parentFile, apk.nameWithoutExtension)
            }

            commands.add(apk.absolutePath)

            basicApktoolQuery(commands, output, onStart = {
                output?.writeln("ApkTool", "Decompilation of ${apk.absolutePath} started!")
            }, onEnd = {
                if (it == 0) {
                    output?.writeln("ApkTool", "${apk.absolutePath} decompiled!")
                } else {
                    output?.writeln("ApkTool", "Error decompiling ${apk.absolutePath}!")
                }
            })

            return appDecompileFolder
        }

        @JvmStatic
        fun recompile(projectFolder: File, vararg options: ApktoolCommand, output: StreamOutput? = null): File? {
            var apk: File? = null
            val commands = ArrayList<String>(listOf(ApktoolCommand.Compiler.TAG))
            commands.addAll(optionsAsStringList(options.toList(), verifier = {
                if (it.tag == ApktoolCommand.General.FRAMEWORK_FOLDER_PATH) {
                    installFramework(File(it.value!!))
                    return@optionsAsStringList false
                } else if (it.tag == ApktoolCommand.General.OUTPUT) {
                    apk = File(it.value!!)
                }

                return@optionsAsStringList true
            }))
            commands.add(projectFolder.absolutePath)

            if (apk == null) {
                val parentFolder = File(projectFolder, "build").apply { mkdirs() }
                apk = File(parentFolder, projectFolder.name + ".apk")
            }

            basicApktoolQuery(commands, output, onStart = {
                output?.writeln("ApkTool", "Recompilation of ${projectFolder.absolutePath} started!")
            }, onEnd = {
                if (it == 0) {
                    output?.writeln("ApkTool", "${apk?.absolutePath} recompiled!")
                } else {
                    output?.writeln("ApkTool", "Error recompiling ${apk?.absolutePath}!")
                }
            })

            return apk
        }

        /**
         * @param
         *
         */
        @Suppress("unused")
        @JvmStatic
        fun installFramework(framework: File, path: String? = null, output: StreamOutput? = null) {
            val frameworkFiles = if (framework.isDirectory)
                framework.listFiles { file -> file.extension == "apk" }?.mapNotNull { it }?.toList() ?: emptyList()
            else
                listOf(framework)

            frameworkFiles.forEach {
                val commands = ArrayList<String>()
                commands.add(ApktoolCommand.General.INSTALL_FRAME_TAG)
                if (path != null)
                    commands.addAll(ApktoolCommand.General.FRAMEWORK_FOLDER_PATH, path)
                commands.add(it.absolutePath)
                basicApktoolQuery(commands, onStart = {
                    output?.writeln("ApkTool", "Installing framework ${it.absolutePath}...")
                }, onEnd = { exitCode ->
                    if (exitCode == 0)
                        output?.writeln("ApkTool", "Framework ${it.absolutePath} installed!")
                    else
                        output?.writeln("ApkTool", "Error installing framework ${it.absolutePath}!")
                })
            }
        }

        private fun optionsAsStringList(options: List<ApktoolCommand>, verifier: ApktoolCommandVerifier): List<String> {
            val commands = mutableListOf<String>()
            for (apkCommand in options) {
                if (verifier(apkCommand)) {
                    commands.addAll(apkCommand.toStringList())
                }
            }

            return commands
        }

        private fun basicApktoolQuery(options: List<String>,
                                      output: StreamOutput? = null,
                                      onStart: (() -> Unit) = {},
                                      onEnd: ((Int) -> Unit) = {}) {
            val apktoolFile = apktool
            if (apktoolFile == null || !apktoolFile.exists()) {
                output?.writeln("ApkToolCheck", "Apktool not found! Checkout its path in settings")
                return
            }
            val commands = ArrayList<String>(listOf("java", "-jar", apktoolFile.absolutePath))
            for (apkCommand in options) {
                commands.add(apkCommand)
            }

            onStart.invoke()
            val processBuilder = ProcessBuilder(commands)
            val process = processBuilder.start()
            output?.startOutput("ApkTool", process.inputStream, process.errorStream)
            process.waitFor()
            output?.stopOutput(process.inputStream, process.errorStream)
            onEnd.invoke(process.exitValue())
        }
    }

}
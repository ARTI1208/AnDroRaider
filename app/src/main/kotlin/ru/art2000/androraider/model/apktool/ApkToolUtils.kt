package ru.art2000.androraider.model.apktool

import ru.art2000.androraider.model.io.StreamOutput
import ru.art2000.androraider.model.settings.PreferenceManager
import ru.art2000.androraider.presenter.settings.SettingsPresenter
import java.io.File


fun <E> MutableList<E>.addAll(vararg els: E) {
    els.forEach {
        this.add(it)
    }
}

typealias ApktoolCommandVerifier = (ApktoolCommand) -> Boolean

class ApkToolUtils {

    companion object {

        @JvmStatic
        fun decompile(settings: PreferenceManager, apk: File, vararg options: ApktoolCommand, output: StreamOutput? = null): File? {
            var appDecompileFolder: File? = null
            val commands = ArrayList<String>(listOf(ApktoolCommand.Decompiler.TAG))
            commands.addAll(optionsAsStringList(options.toList(), verifier = {
                if (it.tag == ApktoolCommand.General.FRAMEWORK_FOLDER_PATH) {
                    installFramework(settings, File(it.value!!), output = output)
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

            basicApktoolQuery(settings, commands, output, onStart = {
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
        fun recompile(settings: PreferenceManager, projectFolder: File, vararg options: ApktoolCommand, output: StreamOutput? = null): File? {
            var apk: File? = null
            val commands = ArrayList<String>(listOf(ApktoolCommand.Compiler.TAG))
            commands.addAll(optionsAsStringList(options.toList(), verifier = {
                if (it.tag == ApktoolCommand.General.FRAMEWORK_FOLDER_PATH) {
                    installFramework(settings, File(it.value!!), output = output)
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

            basicApktoolQuery(settings, commands, output, onStart = {
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

        @Suppress("unused")
        @JvmStatic
        fun installFramework(settings: PreferenceManager, framework: File, path: String? = null, output: StreamOutput? = null) {
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
                basicApktoolQuery(settings, commands, onStart = {
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

        private fun basicApktoolQuery(settings: PreferenceManager,
                                      options: List<String>,
                                      output: StreamOutput? = null,
                                      onStart: (() -> Unit) = {},
                                      onEnd: ((Int) -> Unit) = {}) {
            val apktool = File(settings.getString(SettingsPresenter.KEY_APKTOOL_PATH))
            if (!apktool.exists()) {
                output?.writeln("ApkToolCheck", "Apktool not found! Checkout its path in settings")
                return
            }
            val commands = ArrayList<String>(listOf("java", "-jar", apktool.absolutePath))
            for (apkCommand in options) {
                commands.add(apkCommand)
            }

            onStart.invoke()
            val processBuilder = ProcessBuilder(commands)
            processBuilder.redirectErrorStream(true)
            val process = processBuilder.start()
            output?.startOutput("ApkTool", process.inputStream)
            process.waitFor()
            output?.stopOutput(process.inputStream, process.errorStream)
            onEnd.invoke(process.exitValue())
        }
    }

}
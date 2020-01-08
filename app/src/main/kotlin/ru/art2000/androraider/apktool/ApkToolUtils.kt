package ru.art2000.androraider.apktool

import javafx.application.Platform
import ru.art2000.androraider.App
import ru.art2000.androraider.windows.Settings
import java.io.File

fun <E> ArrayList<E>.addAll(vararg els: E) {
    els.forEach {
        this.add(it)
    }
}

class ApkToolUtils {

    companion object {

        private val apktool: File?
            get() {
                val tmpPath = Settings.getString(Settings.APKTOOL_PATH)
                return if (tmpPath == null || !File(tmpPath).exists()) null else File(tmpPath)
            }

        @JvmStatic
        fun decompile(apk: File, vararg options: ApktoolCommand): File? {
            val apktoolFile = apktool
            if (apktoolFile == null || !apktoolFile.exists()) {
                App.instance.currentStreamOutput?.writeln("ApkToolCheck",
                        "Apktool not found! Checkout its path in settings")
                return null
            }
            var appDecompileFolder: File? = null
            val cmds = ArrayList<String>(listOf("java", "-jar", apktoolFile.absolutePath, ApktoolCommand.Decompiler.TAG))
            for (apkCommand in options) {
                cmds.add(apkCommand.tag)
                if (apkCommand.value != null) {
                    cmds.add(apkCommand.value)
                    if (apkCommand.tag == ApktoolCommand.General.OUTPUT) {
                        appDecompileFolder = File(apkCommand.value)
                        if (appDecompileFolder.exists() && appDecompileFolder.list().isNullOrEmpty()) {
                            cmds.add(ApktoolCommand.Decompiler.OVERRIDE_FOLDER)
                        }
                    }
                }
            }
            if (appDecompileFolder == null) {
                appDecompileFolder = File(apk.parentFile.absolutePath + "/${apk.name}_dec")
                cmds.addAll(ApktoolCommand.General.OUTPUT, appDecompileFolder.absolutePath)
            }
            cmds.add(apk.absolutePath)

            App.instance.currentStreamOutput?.writeln("ApkTool",
                    "Decompilation of ${apk.absolutePath} started!")
            val process = ProcessBuilder(cmds).start()
            App.instance.currentStreamOutput?.startOutput("ApkTool", process.inputStream, process.errorStream)
            process.waitFor()
            App.instance.currentStreamOutput?.writeln("ApkTool",
                    "${apk.absolutePath} decompiled in ${appDecompileFolder.absolutePath}!")

            return appDecompileFolder
        }

        @JvmStatic
        fun recompile(projectFolder: File, vararg options: ApktoolCommand): File? {
            val apktoolFile = apktool
            if (apktoolFile == null || !apktoolFile.exists()) {
                App.instance.currentStreamOutput?.writeln("ApkToolCheck",
                        "Apktool not found! Checkout its path in settings")
                return null
            }
            var apk: File? = null
            val commands = ArrayList<String>(listOf("java", "-jar", apktoolFile.absolutePath, ApktoolCommand.Compiler.TAG))
            for (apkCommand in options) {
                commands.add(apkCommand.tag)
                if (apkCommand.value != null) {
                    commands.add(apkCommand.value)
                    if (apkCommand.tag == ApktoolCommand.General.OUTPUT)
                        apk = File(apkCommand.value)
                }
            }
            commands.add(projectFolder.absolutePath)
            val builder = ProcessBuilder(commands)
            val myThread = Thread {
                Platform.runLater {
                    App.instance.currentStreamOutput?.writeln("ApkTool",
                            "Recompilation of ${projectFolder.absolutePath} started!")
                }
                val process = builder.start()
                App.instance.currentStreamOutput?.startOutput("ApkTool", process.inputStream, process.errorStream)
                process.waitFor()
                Platform.runLater {
                    App.instance.currentStreamOutput?.writeln("ApkTool", "${apk?.absolutePath} recompiled!")
                    App.instance.currentStreamOutput?.stopOutput(process.inputStream, process.errorStream)
                }
            }
            myThread.start()
            return apk
        }

        /**
         * @param
         *
         */
        @Suppress("unused")
        @JvmStatic
        fun installFramework(framework: File, path: String?, tag: String?) {
            val builder = ProcessBuilder()
            val commands = ArrayList<String>()
            commands.addAll(listOf("java", "-jar", apktool!!.absolutePath, ApktoolCommand.General.INSTALL_FRAME_TAG))
            if (path != null)
                commands.addAll(ApktoolCommand.General.FRAMEWORK_FOLDER_PATH, path)
            if (tag != null)
                commands.addAll(ApktoolCommand.General.FRAMEWORK_TAG, tag)
            commands.add(framework.absolutePath)
            builder.command(commands)
            builder.start().waitFor()
        }

        @Suppress("unused")
        private fun framesInFolder(folder: String): Int {
            val list = File(folder).listFiles { f -> f.extension == "apk" }
            return list?.size ?: 0
        }
    }

}
package ru.art2000.androraider.apktool

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage
import ru.art2000.androraider.windows.Settings
import ru.art2000.androraider.StreamGobbler
import ru.art2000.androraider.windows.editor.Editor
import java.io.File
import kotlin.collections.ArrayList

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
            if (apktool == null) {
                println("Apktool not found! Checkout its path in settings")
                return null
            }
            val wind = Stage()
            val message = Text("Decompiling ${apk.name}...")
            val box = VBox()
            box.children.add(message)
            wind.scene = Scene(box, 900.0, 600.0)
            var appDecompileFolder: File? = null
            val cmds = ArrayList<String>(listOf("java", "-jar", apktool!!.absolutePath, ApktoolCommand.Decompiler.TAG))
            for (apkCommand in options) {
                cmds.add(apkCommand.tag)
                if (apkCommand.value != null) {
                    cmds.add(apkCommand.value)
                    if (apkCommand.tag == ApktoolCommand.General.OUTPUT)
                        appDecompileFolder = File(apkCommand.value)
                }
            }
            if (appDecompileFolder == null) {
                appDecompileFolder = File(apk.parentFile.absolutePath + "/${apk.name}_dec")
                cmds.addAll(ApktoolCommand.General.OUTPUT, appDecompileFolder.absolutePath)
            }
            cmds.add(apk.absolutePath)
            wind.show()
            val t = Thread {
                val process = ProcessBuilder(cmds).start()
                val gobbler = StreamGobbler(process, message)
                gobbler.start()
                process.waitFor()
                Platform.runLater {
                    println("Decompilation ended")
                    wind.hide()
                    wind.close()
                    Editor(appDecompileFolder).show()
                }
            }
            t.start()
            return appDecompileFolder
        }

        @JvmStatic
        fun recompile(projectFolder: File, vararg options: ApktoolCommand): File? {
            if (apktool == null) {
                println("Apktool not found! Checkout its path in settings")
                return null
            }
            val dialog = Dialog<String>()
            val pane = DialogPane()
            val message = Text("Recompiling $projectFolder...")
            pane.content = message
            pane.padding = Insets(10.0, 10.0, 0.0, 10.0)
            dialog.dialogPane = pane
            var apk: File? = null
            val commands = ArrayList<String>(listOf("java", "-jar", apktool!!.absolutePath, ApktoolCommand.Compiler.TAG))
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
            val window = dialog.dialogPane.scene.window
            window.onCloseRequest = EventHandler {
                window.hide()
            }
            dialog.show()
            val myThread = Thread {
                builder.start().waitFor()
                Platform.runLater {
                    window.hide()
                    println("Recompiled!")
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
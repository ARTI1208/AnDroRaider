package ru.art2000.androraider

import javafx.application.Platform
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.Dialog
import javafx.scene.control.DialogPane
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

fun <E> ArrayList<E>.addAndReturn(el: E): ArrayList<E> {
    this.add(el)
    return this
}

fun <E> ArrayList<E>.addAll(vararg els: E) {
    els.forEach {
        this.add(it)
    }
}

class ApkToolUtils {

    companion object {

        private var path = Settings.getString(Settings.APKTOOL_PATH)

        private var apktool: File? = if (path == null) null else File(path)

        @JvmStatic
        fun decompile(apk: File, vararg options: ApktoolCommand): File? {
            val path = Settings.getString(Settings.APKTOOL_PATH)
            if (path == null || !File(path).exists()) {
                System.out.println("Apktool not found! Checkout its path in settings")
                return null
            }
            apktool = File(path)
            val wind = Stage()
            val message = Text("Decompiling ${apk.name}...")
            val box = VBox()
            box.children.add(message)
            wind.scene = Scene(box, 900.0, 600.0)
            var appDecompileFolder: File? = null
            val cmds = ArrayList<String>(Arrays.asList("java", "-jar", apktool!!.absolutePath,
                    ApktoolCommand.Decompiler.TAG))
            for (apkCommand in options) {
                cmds.add(apkCommand.tag)
                if (apkCommand.value != null)
                    cmds.add(apkCommand.value)
                if (apkCommand.tag == ApktoolCommand.General.OUTPUT)
                    appDecompileFolder = File(apkCommand.value)
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
                    System.out.println("Decompilation ended")
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
            apktool = File(Settings.getString(Settings.APKTOOL_PATH))
            if (!apktool!!.exists()) {
                System.out.println("Apktool not found! Checkout its path in settings")
                return null
            }
            val dialog = Dialog<String>()
            val pane = DialogPane()
            val message = Text("Recompiling $projectFolder...")
            pane.content = message
            pane.padding = Insets(10.0, 10.0, 0.0, 10.0)
            dialog.dialogPane = pane
            var apk: File? = null
            val cmds = ArrayList<String>(Arrays.asList("java", "-jar", apktool!!.absolutePath,
                    ApktoolCommand.Compiler.TAG))
            for (apkCommand in options) {
                cmds.add(apkCommand.tag)
                if (apkCommand.value != null)
                    cmds.add(apkCommand.value)
                if (apkCommand.tag == ApktoolCommand.General.OUTPUT)
                    apk = File(apkCommand.value)
            }
            cmds.add(projectFolder.absolutePath)
            val builder = ProcessBuilder(cmds)
            val window = dialog.dialogPane.scene.window
            window.onCloseRequest = EventHandler {
                window.hide()
            }
            dialog.show()
            val myThread = Thread {
                builder.start().waitFor()
                Platform.runLater {
                    window.hide()
                    System.out.println("Recompiled!")
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
            commands.addAll(Arrays.asList("java", "-jar", apktool!!.absolutePath,
                    ApktoolCommand.General.INSTALL_FRAME_TAG))
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
            return File(folder).listFiles { f -> f.name.endsWith(".apk") }.size
        }
    }


}
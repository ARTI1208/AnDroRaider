package ru.art2000.androraider.model.apktool

@Suppress("MemberVisibilityCanBePrivate", "unused")
class ApktoolCommand(val tag: String, val value: String? = null) {

    class General {

        companion object {

            const val INSTALL_FRAME_TAG = "if"
            const val OUTPUT = "-o"
            const val FRAMEWORK_FOLDER_PATH = "-p"
            const val FRAMEWORK_TAG = "-t"

            fun installFramework() = ApktoolCommand(INSTALL_FRAME_TAG)
            fun output(outputPath: Any) = ApktoolCommand(OUTPUT, outputPath.toString())
            fun frameworkFolder(frameworkFolderPath: Any) = ApktoolCommand(FRAMEWORK_FOLDER_PATH, frameworkFolderPath.toString())
            fun frameworkTag() = ApktoolCommand(FRAMEWORK_TAG)
        }
    }

    class Decompiler {

        companion object {

            const val TAG = "d"
            const val OVERRIDE_FOLDER = "-f"
            const val DO_NOT_DECODE_RES = "-r"
            const val DO_NOT_DECODE_CODE = "-s"

            fun tag() = ApktoolCommand(TAG)
            fun override() = ApktoolCommand(OVERRIDE_FOLDER)
            fun noRes() = ApktoolCommand(DO_NOT_DECODE_RES)
            fun noCode() = ApktoolCommand(DO_NOT_DECODE_CODE)
        }
    }

    class Compiler {

        companion object {

            const val TAG = "b"
            const val SKIP_LOOK_FOR_CHANGES = "-f"

            fun tag() = ApktoolCommand(TAG)
            fun forceRebuild() = ApktoolCommand(SKIP_LOOK_FOR_CHANGES)
        }
    }

    public fun toStringList(): List<String> {
        return if (value == null)
            listOf(tag)
        else
            listOf(tag, value)
    }
}
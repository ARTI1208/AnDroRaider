package ru.art2000.androraider.apktool

class ApktoolCommand(val tag: String, val value: String? = null) {
    class General {
        companion object {
            @JvmStatic
            val INSTALL_FRAME_TAG = "if"
            @JvmStatic
            val OUTPUT = "-o"
            @JvmStatic
            val FRAMEWORK_FOLDER_PATH = "-p"
            @JvmStatic
            val FRAMEWORK_TAG = "-t"
        }
    }

    @Suppress("unused")
    class Decompiler {
        companion object {
            @JvmStatic
            val TAG = "d"
            @JvmStatic
            val OVERRIDE_FOLDER = "-f"
            @JvmStatic
            val DO_NOT_DECODE_RES = "-r"
            @JvmStatic
            val DO_NOT_DECODE_CODE = "-s"
        }
    }

    @Suppress("unused")
    class Compiler {
        companion object {
            @JvmStatic
            val TAG = "b"
            @JvmStatic
            val SKIP_LOOK_FOR_CHANGES = "-f"
        }
    }
}
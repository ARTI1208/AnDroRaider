package ru.art2000.androraider

class TypeDetector {

    class Text {
        companion object {
            private val knownTypes = ArrayList<String>()

            init {
                knownTypes.addAll(
                        "txt", "xml",
                        "yml", "smali",
                        "svg")
            }

            fun listContains(extension: String): Boolean {
                return knownTypes.contains(extension)
            }
        }
    }

    companion object {
        fun isTextFile(fileName: String): Boolean {
            if (!fileName.contains('.'))
                return false
            return Text.listContains(fileName.substring(fileName.lastIndexOf('.') + 1))
        }
    }
}
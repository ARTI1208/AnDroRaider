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

        val SMALI_PATTERN = "\\b(?<LOCAL>v\\d+)\\b|\\b(?<PARAM>p\\d+)\\b|(?<BRACKET>[()])|(?<COMMENT>#[\\S \\t]*\$)|(?<STRING>\"[^\"]*\")"

        fun isTextFile(fileName: String): Boolean {
            if (!fileName.contains('.'))
                return false
            return Text.listContains(fileName.substring(fileName.lastIndexOf('.') + 1))
        }

        fun getPatternForExtension(extension : String?) : String {
            return when (extension){
                "smali" -> SMALI_PATTERN
                else -> ""
            }
        }
    }
}
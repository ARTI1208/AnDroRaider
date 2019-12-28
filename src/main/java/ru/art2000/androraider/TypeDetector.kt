package ru.art2000.androraider

import java.io.File

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

    class Image {
        companion object {
            private val knownRasterTypes = ArrayList<String>()
            private val knownVectorTypes = ArrayList<String>()

            init {
                knownRasterTypes.addAll(
                        "png", "jpg",
                        "jpeg", "webp")
                knownVectorTypes.add("svg")
            }

            fun isVectorDrawable(file: File?) : Boolean {
                if (file == null)
                    return false
                if (knownVectorTypes.contains(file.extension))
                    return true
                println("ext is ${file.extension}, parent ${file.parent}")
                if (file.extension == "xml" && file.parentFile.name.startsWith("drawable"))
                    return true

                return false
            }

        }
    }

    companion object {

        private val SMALI_STARTING_KEYWORDS = arrayOf("class", "super", "field", "end method", "method",
                "packed-switch", "sparse-switch", "locals")

        private val OTHER_SMALI_KEYWORDS = arrayOf(
                "public", "private", "protected", "abstract", "static", "synthetic", "final", "enum",
                "if-((eq)|(ne)|(lt)|(ge)|(gt)|(le)|(eqz)|(nez))", "return((-void)|(-object)|(-wide))?")

        private val SMALI_METHOD_CALL = arrayOf(
                "invoke-static", "invoke-virtual", "invoke-direct", "invoke-super", "invoke-interface")

        private val SMALI_CALL_CHARS = arrayOf(
                "\\{", "\\}", "->", "\\.\\.")

        private val SMALI_PATTERN =
                "\\b(?<LOCAL>v\\d+)\\b|" +
                        "\\b(?<PARAM>p\\d+)\\b|" +
                        "(?<BRACKET>[()])|" +
                        "\\b(?<NUMBER>(0x[\\da-fA-F]+)|\\d+)\\b|" +
                        "(?<COMMENT>#[\\S \\t]*\$)|" +
                        "(?<STRING>(\"[^\"]*\")|const-string(/jumbo)?)|" +
                        "(?<CALL>(" + SMALI_METHOD_CALL.joinToString("|") { "\\b$it\\b(/range)?" } + "|" +
                        SMALI_CALL_CHARS.joinToString("|")+"))|" +
                        "(?<KEYWORD>(" + SMALI_STARTING_KEYWORDS.joinToString("|") { "^[\\s]*\\.$it" } + "|" +
                        OTHER_SMALI_KEYWORDS.joinToString("|") { "\\b$it" } + ")[^\\w\\S-])"

        fun isTextFile(fileName: String): Boolean {
            if (!fileName.contains('.'))
                return false
            return Text.listContains(fileName.substring(fileName.lastIndexOf('.') + 1))
        }

        fun getPatternForExtension(extension: String?): String {
            return when (extension) {
                "smali" -> SMALI_PATTERN
                else -> ""
            }
        }
    }
}
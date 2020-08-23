package ru.art2000.androraider.utils

import ru.art2000.androraider.model.apktool.addAll
import ru.art2000.androraider.model.refactoring.DummyRefactoringRule
import ru.art2000.androraider.model.refactoring.RefactoringRule
import ru.art2000.androraider.model.refactoring.SmaliRefactoringRule
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

                if (file.extension == "xml" && file.parentFile.name.startsWith("drawable"))
                    return true

                return false
            }

        }
    }

    companion object {

        fun isTextFile(fileName: String): Boolean {
            if (!fileName.contains('.'))
                return false
            return Text.listContains(fileName.substring(fileName.lastIndexOf('.') + 1))
        }

        fun getRefactoringRule(lang: String): RefactoringRule {
            return when (lang) {
                "smali" -> SmaliRefactoringRule
                else -> DummyRefactoringRule
            }
        }
    }
}
package ru.art2000.androraider.model.analyzer.smali.types

import java.io.File

interface SmaliComponent {

    val file: File?
    val textRange: IntRange
    val fullname: String

    fun markAsNotExisting()
    fun exists(): Boolean

    fun toSmaliString(): String

    companion object {
        internal val EMPTY_RANGE = -1..0

        public fun packageForName(name: String): SmaliPackage {
            val lastDot = name.lastIndexOf('.')
            return if (lastDot == -1) {
                SmaliPackage(name, SmaliPackage("", packageDelimiter = ""))
            } else {
                val parentPackageName = name.substring(0, lastDot)
                val packageName = name.substring(lastDot + 1)
                SmaliPackage(packageName, packageForName(parentPackageName))
            }
        }

        public fun classFromName(smaliFullName: String): SmaliClass? {
            when (smaliFullName) {
                "V" -> return SmaliClass.Primitives.VOID
                "I" -> return SmaliClass.Primitives.INT
                "J" -> return SmaliClass.Primitives.LONG
                "S" -> return SmaliClass.Primitives.SHORT
                "B" -> return SmaliClass.Primitives.BYTE
                "Z" -> return SmaliClass.Primitives.BOOLEAN
                "C" -> return SmaliClass.Primitives.CHAR
                "F" -> return SmaliClass.Primitives.FLOAT
                "D" -> return SmaliClass.Primitives.DOUBLE
            }

            // Reference type
            if (smaliFullName.startsWith('L')) {
                var referenceClassName = smaliFullName.substring(1).replace('/', '.')
                if (referenceClassName.endsWith(';'))
                    referenceClassName = referenceClassName.substring(0, referenceClassName.lastIndex)

                val lastDot = referenceClassName.lastIndexOf('.')
                return if (lastDot == -1) {
                    SmaliClass(referenceClassName, SmaliPackage("", packageDelimiter = ""))
                } else {
                    val parentPackageName = referenceClassName.substring(0, lastDot)
                    val simpleClassName = referenceClassName.substring(lastDot + 1)
                    SmaliClass(simpleClassName, packageForName(parentPackageName))
                }
            } else if (smaliFullName.startsWith('[')) { // Array
                val smaliClass = SmaliClass()
                for (c in smaliFullName) {
                    if (c == '[')
                        smaliClass.arrayCount++
                    else
                        break
                }

                smaliClass.parentClass = classFromName(smaliFullName.substring(smaliClass.arrayCount))

                return smaliClass
            } else {
                return null
            }
        }
    }
}
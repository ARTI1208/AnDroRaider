package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import java.io.File

class RegisterRangeStatus(override val range: IntRange,
                          register: Register,
                          private val clazz: SmaliClass?,
                          override val declaringFile: File)
    : RangeAnalyzeStatus {

    override val description: String = "Value in register: ${clazz?.fullname}"

    override val style = when (register) {
        Register.LOCAL -> listOf("local")
        Register.PARAM -> listOf("param")
    }
}

enum class Register {
    LOCAL,
    PARAM;
}
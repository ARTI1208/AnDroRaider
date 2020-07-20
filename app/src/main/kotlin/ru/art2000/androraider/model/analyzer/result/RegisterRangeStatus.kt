package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import java.io.File

class RegisterRangeStatus(range: IntRange,
                          register: Register,
                          private val clazz: SmaliClass?,
                          override val declaringFile: File)
    : RangeAnalyzeStatus {

    override val description: String = "Value in register: ${clazz?.fullname}"

    override val rangeToStyle = listOf(range to register.style)
}

enum class Register(val style: String) {
    LOCAL("local"),
    PARAM("param");
}
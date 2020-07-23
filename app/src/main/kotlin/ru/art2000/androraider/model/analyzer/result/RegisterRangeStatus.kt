package ru.art2000.androraider.model.analyzer.result

import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliMethod
import java.io.File

class RegisterRangeStatus(override val segmentRange: IntRange,
                          private val register: Register,
                          private val num: Int,
                          private val smaliMethod: SmaliMethod,
                          private val clazz: SmaliClass?,
                          override val declaringFile: File
) : StyledSegment, DescriptiveSegment, FileSegment, HighlightableSegment {

    override val style: String = register.style

    override val description: String = "Value in register: ${clazz?.fullname}"

    override fun highlightOther(other: HighlightableSegment): Boolean {
        if (other !is RegisterRangeStatus) {
            return false
        }

        return num == other.num
                && smaliMethod === other.smaliMethod
                && register === other.register
    }
}

enum class Register(val style: String) {
    LOCAL("local"),
    PARAM("param");
}
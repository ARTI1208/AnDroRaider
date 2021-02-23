package ru.art2000.androraider.model.analyzer.smali

import ru.art2000.androraider.model.analyzer.result.DescriptiveSegment
import ru.art2000.androraider.model.analyzer.result.HighlightableSegment
import ru.art2000.androraider.model.analyzer.result.StyledSegment
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliMethod

class RegisterSegment(
    override val segmentRange: IntRange,
    private val register: Register,
    private val num: Int,
    private val smaliMethod: SmaliMethod,
    private val clazz: SmaliClass?
) : StyledSegment, DescriptiveSegment, HighlightableSegment {

    override val style: String = register.style

    override val description: String = "Value in register: ${clazz?.fullname}"

    override fun highlightOther(other: HighlightableSegment): Boolean {
        if (other !is RegisterSegment) {
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
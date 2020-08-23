package ru.art2000.androraider.model.analyzer.result

import org.antlr.v4.runtime.tree.ErrorNode
import ru.art2000.androraider.utils.textRange
import java.io.File

class Error(
        override val segmentRange: IntRange,
        override val description: String
) : StyledSegment, DescriptiveSegment {

    override val style: String = "error"

    companion object {
        fun from(errorNode: ErrorNode, description: String = "Error")
                = Error(errorNode.textRange, description)
    }

}
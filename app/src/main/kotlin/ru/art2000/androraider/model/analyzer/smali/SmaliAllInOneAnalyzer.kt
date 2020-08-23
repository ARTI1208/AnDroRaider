package ru.art2000.androraider.model.analyzer.smali

import javafx.beans.Observable
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import ru.art2000.androraider.model.analyzer.AnalyzeMode
import ru.art2000.androraider.model.analyzer.android.*
import ru.art2000.androraider.model.analyzer.result.*
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliComponent
import ru.art2000.androraider.model.analyzer.smali.types.SmaliField
import ru.art2000.androraider.model.analyzer.smali.types.SmaliMethod
import ru.art2000.androraider.utils.parseCompound
import ru.art2000.androraider.utils.textRange
import java.lang.Exception
import java.lang.reflect.Modifier
import kotlin.properties.Delegates

class SmaliAllInOneAnalyzer(val project: SmaliProject, val settings: SmaliAnalyzerSettings) :
        SmaliParserBaseVisitor<SmaliClass>() {

    var smaliClass = SmaliClass()

    val dependencies = mutableListOf<Observable>()
    
    val ranges = mutableListOf<TextSegment>()

    val errors = mutableListOf<TextSegment>()

    private fun tunableVisitChildren(ctx: ParserRuleContext, action: () -> Unit): SmaliClass {
        return if (settings.childrenBeforeAction) {
            val res = visitChildren(ctx)
            action.invoke()
            res
        } else {
            action.invoke()
            visitChildren(ctx)
        }
    }

    override fun visitClassDirective(ctx: SmaliParser.ClassDirectiveContext): SmaliClass {
        val className = ctx.className()

        if (className == null) {
            println("classnmae null, txt='${ctx.text}'")
            return visitChildren(ctx)
        }

        SmaliComponent.classFromName(className.text)?.also { 
            smaliClass = it
        }

        smaliClass.textRange = className.textRange

        if (ctx.CLASS_DIRECTIVE() == null || settings.mode != AnalyzeMode.FULL) {
            return visitChildren(ctx)
        }

        ranges.add(SimpleStyledSegment(ctx.CLASS_DIRECTIVE().textRange, "keyword"))
        return visitChildren(ctx)
    }

    override fun visitMethodReturnType(ctx: SmaliParser.MethodReturnTypeContext): SmaliClass {
        val method = findMethod(ctx)
        if (method != null) {
            val returnType = SmaliComponent.classFromName(ctx.text)
            if (returnType == null) {
                if (settings.mode == AnalyzeMode.FULL) {
                    val message = if (ctx.text.isNullOrEmpty())
                        "No return type provided"
                    else
                        "Unknown return type \"${ctx.text}\""

                    val error = Error(ctx.textRange, message)
                    ranges.add(error)
                    errors.add(error)
                }
            } else {
                method.returnType = returnType
            }
        }

        return visitChildren(ctx)
    }

    inner class FieldDeclarationContextWrapper(ctx: SmaliParser.FieldDirectiveContext) :
            SmaliParser.FieldDirectiveContext(ctx.getParent(), ctx.invokingState) {

        val smaliField = smaliClass.findOrCreateField(
                ctx.fieldNameAndType()?.fieldName()?.text ?: "Dummy",
                ctx.fieldNameAndType()?.fieldType()?.text ?: "I"
        )!!

        init {
            children = ctx.children
            children.forEach {
                it.setParent(this)
            }
            smaliField.parentClass = smaliClass
            smaliField.textRange = ctx.fieldNameAndType()?.fieldName()?.textRange ?: -1..0
        }

    }

    override fun visitFieldDirective(ctx: SmaliParser.FieldDirectiveContext): SmaliClass {
        if (settings.mode == AnalyzeMode.FULL)
            ranges.add(SimpleStyledSegment(ctx.FIELD_DIRECTIVE().textRange, "keyword"))

        return visitChildren(FieldDeclarationContextWrapper(ctx))
    }

    override fun visitNewArrayInstruction(ctx: SmaliParser.NewArrayInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val clazz = SmaliComponent.classFromName(ctx.arrayElementType().text) ?: return@tunableVisitChildren
            currentMethod.registerToClassMap[ctx.targetRegister().text] = clazz
        }
    }

    override fun visitAgetInstruction(ctx: SmaliParser.AgetInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren
            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitMethodDirective(ctx: SmaliParser.MethodDirectiveContext): SmaliClass {
        if (settings.mode == AnalyzeMode.FULL) {
            ranges.add(SimpleStyledSegment(ctx.METHOD_DIRECTIVE().textRange, "keyword"))
            ranges.add(SimpleStyledSegment(ctx.METHOD_END_DIRECTIVE().textRange, "keyword"))
        }

        return visitChildren(MethodDirectiveContextWrapper(ctx))
    }

    override fun visitMoveResultInstruction(ctx: SmaliParser.MoveResultInstructionContext): SmaliClass {

        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            var parent: RuleContext = ctx.parent
            var currentHolder: RuleContext = ctx

            while (parent.childCount == 1) {
                currentHolder = parent
                parent = parent.parent
            }

            if (parent is ParserRuleContext) {
                val i = parent.children.indexOf(currentHolder)
                if (i > 0) {
                    var previousInstruction = parent.children[i - 1]
                    while (previousInstruction.childCount == 1) {
                        previousInstruction = previousInstruction.getChild(0)
                    }

                    val classNameContext = when (previousInstruction) {
                        is SmaliParser.InvokeVirtualInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeSuperInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeDirectInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeStaticInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeInterfaceInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.FilledNewArrayInstructionContext ->
                            previousInstruction.arrayElementType()

                        is SmaliParser.FilledNewArrayRangeInstructionContext ->
                            previousInstruction.arrayElementType()

                        else -> return@tunableVisitChildren
                    }

                    val clazz = SmaliComponent.classFromName(classNameContext.text) ?: return@tunableVisitChildren
                    currentMethod.registerToClassMap[ctx.registerIdentifier().text] = clazz
                }
            }
        }
    }

    override fun visitIgetBooleanInstruction(ctx: SmaliParser.IgetBooleanInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && fieldClass !== SmaliClass.Primitives.BOOLEAN) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitIgetCharInstruction(ctx: SmaliParser.IgetCharInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && fieldClass !== SmaliClass.Primitives.CHAR) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitGotoInstruction(ctx: SmaliParser.GotoInstructionContext): SmaliClass {

        if (settings.mode == AnalyzeMode.FULL) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.label().labelName()

            val label = currentMethod.getOrCreateLabel(labelNameContext.text)

            ranges.add(SmaliAnalysisSegment(
                    labelNameContext.textRange,
                    label,
                    project.getLinksFor(label).firstOrNull()
            ))
        }
//        else if (!smaliField.exists()) {
//            errors.add(Error(IntRange.EMPTY, "", file))
//        }

        return visitChildren(ctx)
    }

    override fun visitAnnotationDirective(ctx: SmaliParser.AnnotationDirectiveContext): SmaliClass {
        if (settings.mode == AnalyzeMode.FULL) {
            ranges.add(SimpleStyledSegment(ctx.ANNOTATION_DIRECTIVE().textRange, "keyword"))
            ranges.add(SimpleStyledSegment(ctx.ANNOTATION_END_DIRECTIVE().textRange, "keyword"))
        }
        return visitChildren(ctx)
    }

    override fun visitConst16Instruction(ctx: SmaliParser.Const16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitNumericLiteral(ctx: SmaliParser.NumericLiteralContext): SmaliClass {
        var description = "Number"

        val isNegative = ctx.negativeNumericLiteral() != null
        var radix = 10
        var type = 0
        var offsetBefore = 0
        var offsetAfter = 0
        val meaningful = if (isNegative)
            ctx.negativeNumericLiteral().positiveNumericLiteral()
        else
            ctx.positiveNumericLiteral()

        var err = false
        try {
            val last = meaningful.text.last()

            when {
                last in listOf('l', 'L') -> {
                    offsetAfter = 1
                    type = 1
                }
                last in listOf('f', 'F') && (type == 3) -> {
                    offsetAfter = 1
                    type = 2
                }
                last in listOf('d', 'D') && (type == 3) -> {
                    offsetAfter = 1
                    type = 3
                }
            }
        } catch (e: Exception) {
            println("Error: ${ctx.text} in ${smaliClass.associatedFile}")
            e.printStackTrace()
            err = true
        }

        when {
            meaningful.decimalNumericLiteral() != null -> {
                radix = 10
                offsetBefore = 0
                if (err) println("decNum")
            }
            meaningful.hexNumericLiteral() != null -> {
                radix = 16
                offsetBefore = 2
                if (err) println("hexNum")
            }
            meaningful.hexFloatLiteral() != null -> {
                radix = 16
                offsetBefore = 2
                type = 3
                if (err) println("hexFloatNum")
            }
            meaningful.binaryNumericLiteral() != null -> {
                radix = 2
                offsetBefore = 2
                if (err) println("bin")
            }
            meaningful.octNumericLiteral() != null -> {
                radix = 8

                for (c in meaningful.text.drop(1)) {
                    if (c == '_') offsetBefore++ else break
                }
                offsetBefore++
                if (err) println("oct")
            }
            meaningful.floatNumericLiteral() != null -> {
                type = 3
                if (err) println("float")
            }
            meaningful.INFINITY() != null || meaningful.FLOAT_INFINITY() != null -> {
                type = 4
                if (err) println("inf")
            }
            meaningful.NAN() != null || meaningful.FLOAT_NAN() != null -> {
                type = 5
                if (err) println("nan")
            }
            else -> {
                type = 666
                println(ctx.text + "||"+meaningful.text + "||" + meaningful.childCount)
            }
        }

        if (err) println("Err in type^ $type")

        var numberString = meaningful.text.drop(offsetBefore).dropLast(offsetAfter)
        if (isNegative)
            numberString = "-$numberString"

        try {
            var hex: String
            val decimal: Number = when (type) {
                0 -> numberString.toInt(radix).also {
                    hex = it.toString(16)
                }
                1 -> numberString.toLong(radix).also {
                    hex = it.toString(16)
                }
                2 -> numberString.toFloat().also {
                    hex = it.toBits().toString(16)
                }
                3 -> numberString.toDouble().also {
                    hex = it.toBits().toString(16)
                }
                4 -> (if (isNegative) Double.NEGATIVE_INFINITY else Double.POSITIVE_INFINITY).also {
                    hex = "${if (isNegative) "-" else ""}Inf"
                }
                5 -> Double.NaN.also {
                    hex = "NaN"
                }
                else -> 666.also {
                    hex = it.toString(16)
                }
            }

            var isResource = false
            if (project is AndroidAppProject && radix == 16) {
                val androidResource = project.public[decimal.toInt()]

                androidResource?.also { res ->
                    isResource = true
                    description = "Resource: type=${res.type} name=${res.name}"

                    if (settings.mode == AnalyzeMode.FULL) {
                        val navigateOptions = project.getLinksFor(res).toList()
                        ranges.add(AndroidResourceSegment(res, navigateOptions, ctx.textRange))
                    }

                }
            }

            if (!isResource) {
                description += "; hex: 0x${hex}; dec: $decimal"
            }

            if (settings.mode == AnalyzeMode.FULL) {
                ranges.add(DescriptiveAnalysisSegment(ctx.textRange, "number", description))
            }
        } catch (e: Exception) {
            println("Error: ${ctx.text} in ${smaliClass.associatedFile}")
            e.printStackTrace()
        }
        return visitChildren(ctx)
    }

    override fun visitSuperDirective(ctx: SmaliParser.SuperDirectiveContext): SmaliClass {
        if (ctx.SUPER_DIRECTIVE() != null && settings.mode == AnalyzeMode.FULL) {
            ranges.add(SimpleStyledSegment(ctx.SUPER_DIRECTIVE().textRange, "keyword"))
        }

        smaliClass.parentClass = SmaliComponent.classFromName(ctx.superName().text)

        return visitChildren(ctx)
    }

    override fun visitMoveResultObjectInstruction(ctx: SmaliParser.MoveResultObjectInstructionContext): SmaliClass {

        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            var parent: RuleContext = ctx.parent
            var currentHolder: RuleContext = ctx

            while (parent.childCount == 1) {
                currentHolder = parent
                parent = parent.parent
            }

            if (parent is ParserRuleContext) {
                val i = parent.children.indexOf(currentHolder)
                if (i > 0) {
                    var previousInstruction = parent.children[i - 1]
                    while (previousInstruction.childCount == 1) {
                        previousInstruction = previousInstruction.getChild(0)
                    }

                    val classNameContext = when (previousInstruction) {
                        is SmaliParser.InvokeVirtualInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeSuperInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeDirectInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeStaticInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeInterfaceInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.FilledNewArrayInstructionContext ->
                            previousInstruction.arrayElementType()

                        is SmaliParser.FilledNewArrayRangeInstructionContext ->
                            previousInstruction.arrayElementType()

                        else -> return@tunableVisitChildren
                    }

                    val clazz = SmaliComponent.classFromName(classNameContext.text) ?: return@tunableVisitChildren
                    currentMethod.registerToClassMap[ctx.registerIdentifier().text] = clazz
                }
            }
        }
    }

    override fun visitFieldType(ctx: SmaliParser.FieldTypeContext): SmaliClass {
        val grandfather = ctx.parent.parent
        if (grandfather is FieldDeclarationContextWrapper) {
            val fieldType = SmaliComponent.classFromName(ctx.text)

            if (fieldType == null) {
                if (settings.mode == AnalyzeMode.FULL) {
                    val message = if (ctx.text.isNullOrEmpty())
                        "No field type provided"
                    else
                        "Unknown field type \"${ctx.text}\""

                    val error = Error(ctx.textRange, message)
                    ranges.add(error)
                    errors.add(error)
                }
            } else {
                grandfather.smaliField.type = fieldType
            }
        }

        return visitChildren(ctx)
    }

    override fun visitMethodParameterType(ctx: SmaliParser.MethodParameterTypeContext): SmaliClass {
            var offset = 0
            parseCompound(ctx.text).forEach { parameterText ->
                SmaliComponent.classFromName(parameterText)?.also {
                    if (settings.mode == AnalyzeMode.FULL) {
                        val links = project.getLinksFor(it)
                        dependencies.add(links)

                        ranges.add(SmaliAnalysisSegment(
                                (ctx.start.startIndex + offset)..(ctx.start.startIndex + offset + parameterText.lastIndex),
                                it,
                                links.firstOrNull())
                        )
                    } else if (!it.exists()) {
                        errors.add(Error(IntRange.EMPTY, ""))
                    }
                offset += parameterText.length
            }
        }
        return smaliClass
    }

    override fun visitReferenceType(ctx: SmaliParser.ReferenceTypeContext): SmaliClass {
        val clazz = SmaliComponent.classFromName(ctx.text) ?: return visitChildren(ctx)
        if (settings.mode == AnalyzeMode.FULL) {
            val links = project.getLinksFor(clazz)
            dependencies.add(links)

//            println("link count for $clazz / ${clazz.hashCode()} is ${links.size}")

            ranges.add(SmaliAnalysisSegment(ctx.textRange, clazz, links.firstOrNull()))
        }
//        else if (!clazz.exists()) {
//            errors.add(Error(IntRange.EMPTY, ""))
//        }

        return visitChildren(ctx)
    }

    override fun visitInstanceOfInstruction(ctx: SmaliParser.InstanceOfInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val method = findMethod(ctx) ?: return@tunableVisitChildren

            method.registerToClassMap[ctx.targetRegister().text] = SmaliClass.Primitives.INT
        }
    }

    override fun visitIgetWideInstruction(ctx: SmaliParser.IgetWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitIgetObjectInstruction(ctx: SmaliParser.IgetObjectInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    private tailrec fun findMethod(ctx: RuleContext): SmaliMethod? {
        if (ctx is MethodDirectiveContextWrapper) {
            return ctx.smaliMethod
        }

        if (ctx.parent == null)
            return null

        return findMethod(ctx.parent)
    }

    override fun visitRegisterIdentifier(ctx: SmaliParser.RegisterIdentifierContext): SmaliClass {
        if (settings.mode != AnalyzeMode.FULL) return visitChildren(ctx)

        val txt = ctx.text
        val method = findMethod(ctx)

        when {
            txt.matches(Regex("p\\d+")) -> {
                val num = txt.substring(1).toInt()

                when {
                    method == null -> {
                        ranges.add(DescriptiveAnalysisSegment(
                                ctx.textRange,
                                "param",
                                "Param $num, failed to get max possible value"
                            )
                        )
                    }
                    num >= method.parameters.size -> {

                        val error = Error(
                                ctx.textRange,
                                "Invalid param index $num: must be in range 0..${method.parameters.size - 1}"
                        )
                        ranges.add(error)
                        errors.add(error)
                    }
                    else -> {
                        ranges.add(RegisterSegment(
                                ctx.textRange,
                                Register.PARAM,
                                num,
                                method,
                                method.registerToClassMap[txt])
                        )
                    }
                }
            }

            txt.matches(Regex("v\\d+")) -> {
                val num = txt.substring(1).toInt()

                when {
                    method == null -> {
                        ranges.add(DescriptiveAnalysisSegment(
                                ctx.textRange,
                                "local",
                                "Local $num, failed to get max possible value"
                            )
                        )
                    }
                    num >= method.locals -> {

                        val error = Error(
                                ctx.textRange,
                                "Invalid local index $num: must be in range 0..${method.locals - 1}"
                        )
                        ranges.add(error)
                        errors.add(error)
                    }
                    else -> {
                        ranges.add(RegisterSegment(
                                ctx.textRange,
                                Register.LOCAL,
                                num,
                                method,
                                method.registerToClassMap[txt])
                        )
                    }
                }
            }

            else -> {

                val error = Error(ctx.textRange, "Invalid register identifier")
                ranges.add(error)
                errors.add(error)
            }
        }

        return visitChildren(ctx)
    }

    override fun visitMoveWide16Instruction(ctx: SmaliParser.MoveWide16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitAgetObjectInstruction(ctx: SmaliParser.AgetObjectInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren
            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitConstWideHigh16Instruction(ctx: SmaliParser.ConstWideHigh16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitSuperName(ctx: SmaliParser.SuperNameContext): SmaliClass {
        smaliClass.parentClass = SmaliComponent.classFromName(ctx.text)
        return visitChildren(ctx)
    }

    override fun visitFillArrayDataInstruction(ctx: SmaliParser.FillArrayDataInstructionContext): SmaliClass {

        if (settings.mode == AnalyzeMode.FULL) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.filledArrayDataLabel().label().labelName()

            val label = currentMethod.getOrCreateLabel(labelNameContext.text)

            ranges.add(SmaliAnalysisSegment(
                    labelNameContext.textRange,
                    label,
                    project.getLinksFor(label).firstOrNull())
            )
        }

        return visitChildren(ctx)
    }

    override fun visitLocalsDirective(ctx: SmaliParser.LocalsDirectiveContext): SmaliClass {
        findMethod(ctx)?.locals = ctx.numericLiteral().text.toInt()
        return visitChildren(ctx)
    }

    override fun visitConstHigh16Instruction(ctx: SmaliParser.ConstHigh16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitIgetShortInstruction(ctx: SmaliParser.IgetShortInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && fieldClass !== SmaliClass.Primitives.SHORT) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    // TODO implement synthetic, bridge, synchronized, strictfp, varargs
    override fun visitMethodModifier(ctx: SmaliParser.MethodModifierContext): SmaliClass {

        if (settings.mode == AnalyzeMode.FULL)
            ranges.add(SimpleStyledSegment(ctx.textRange, "keyword"))

        val method = findMethod(ctx) ?: return visitChildren(ctx)

        when (ctx.text) {
            "public" -> method.setModifierBit(Modifier.PUBLIC)
            "protected" -> method.setModifierBit(Modifier.PROTECTED)
            "private" -> method.setModifierBit(Modifier.PRIVATE)
            "final" -> method.setModifierBit(Modifier.FINAL)
            "static" -> method.setModifierBit(Modifier.STATIC)
            "abstract" -> method.setModifierBit(Modifier.ABSTRACT)
            "native" -> method.setModifierBit(Modifier.NATIVE)
            "constructor" -> method.setModifierBit(SmaliMethod.Modifier.CONSTRUCTOR)
        }

        method.parameters.forEachIndexed { index, smaliClass ->
            method.registerToClassMap["p$index"] = smaliClass
        }

        return visitChildren(ctx)
    }

    override fun visitMoveFrom16Instruction(ctx: SmaliParser.MoveFrom16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitIgetByteInstruction(ctx: SmaliParser.IgetByteInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && fieldClass !== SmaliClass.Primitives.BYTE) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitLineLabel(ctx: SmaliParser.LineLabelContext): SmaliClass {
        val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

        val labelNameContext = ctx.label().labelName()

        currentMethod.getOrCreateLabel(labelNameContext.text).apply { textRange = labelNameContext.textRange }

        return visitChildren(ctx)
    }

    override fun visitConstInstruction(ctx: SmaliParser.ConstInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitMethodInvocationTarget(ctx: SmaliParser.MethodInvocationTargetContext): SmaliClass {
        smaliMethodFromInvocationContext(ctx)?.also {
            if (settings.mode == AnalyzeMode.FULL) {
                val links = project.getLinksFor(it)
//                dependencies.add(links)

                println("link count for $it / ${it.hashCode()} is ${links.size}")
                if (links.size > 1) {
                    links.forEach {
                        println("---${it.file}/${it.offset}/${it.description}")
                    }
                }

                ranges.add(SmaliAnalysisSegment(
                        ctx.methodSignature().methodIdentifier().textRange,
                        it,
                        links.firstOrNull()))
            } else if (!it.exists()) {
                errors.add(Error(IntRange.EMPTY, ""))
            }
        }

        return visitChildren(ctx)
    }

    private fun smaliFieldFromInvocationContext(ctx: SmaliParser.FieldInvocationTargetContext): SmaliField? {
        val targetClass = SmaliComponent.classFromName(ctx.referenceOrArrayType().text)
        val fieldName = ctx.fieldNameAndType().fieldName().text
        val fieldType = ctx.fieldNameAndType().fieldType().text
        return targetClass?.findOrCreateField(fieldName, fieldType)
    }

    private fun smaliMethodFromInvocationContext(ctx: SmaliParser.MethodInvocationTargetContext): SmaliMethod? {
        val targetClass = SmaliComponent.classFromName(ctx.referenceOrArrayType().text)
        val methodName = ctx.methodSignature().methodIdentifier().text
        // TODO optimize double invocation (here and in visitMethodArguments)
        val methodParameters = parseCompound(ctx.methodSignature().methodArguments()?.text)
        val methodReturnType = ctx.methodSignature().methodReturnType().text
        return targetClass?.findOrCreateMethod(methodName, methodParameters, methodReturnType)
    }

    private fun smaliMethodFromDeclarationContext(ctx: SmaliParser.MethodDeclarationContext): SmaliMethod {
        val name = ctx.methodSignature()?.methodIdentifier()?.text ?: "DummyMethod"
        val params = parseCompound(ctx.methodSignature()?.methodArguments()?.text)
        val returnType = ctx.methodSignature()?.methodReturnType()?.text ?: "V"
        return smaliClass.findOrCreateMethod(name, params, returnType, 0)!!
    }

    inner class MethodDirectiveContextWrapper(ctx: SmaliParser.MethodDirectiveContext) :
            SmaliParser.MethodDirectiveContext(ctx.getParent(), ctx.invokingState) {

        val smaliMethod = smaliMethodFromDeclarationContext(ctx.methodDeclaration())

        init {
            children = ctx.children
            children.forEach {
                it.setParent(this)
            }

            smaliMethod.registerToClassMap.clear()
            smaliMethod.textRange = ctx.methodDeclaration()?.methodSignature()?.methodIdentifier()?.textRange ?: -1..0
        }

    }

    override fun visitMethodDeclaration(ctx: SmaliParser.MethodDeclarationContext): SmaliClass {
        val arguments = ctx.methodSignature()?.methodArguments()

        if (arguments != null) {
            var offset = arguments.start.startIndex

            parseCompound(arguments.text)
                    .mapNotNull {
                        val parameterType = SmaliComponent.classFromName(it)
                        if (parameterType == null && settings.mode == AnalyzeMode.FULL) {

                            val error = Error(offset until offset + it.length, "Unknown type \"$it\"")
                            ranges.add(error)
                            errors.add(error)
                        }
                        offset += it.length
                        parameterType
                    }
        }

        return visitChildren(ctx)
    }

    override fun visitIgetInstruction(ctx: SmaliParser.IgetInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    // TODO implement synthetic, transient, volatile, enum
    override fun visitFieldModifier(ctx: SmaliParser.FieldModifierContext): SmaliClass {
        val parent = ctx.parent
        if (parent is FieldDeclarationContextWrapper) {
            when (ctx.text) {
                "public" -> smaliClass.setModifierBit(Modifier.PUBLIC)
                "protected" -> smaliClass.setModifierBit(Modifier.PROTECTED)
                "private" -> smaliClass.setModifierBit(Modifier.PRIVATE)
                "final" -> smaliClass.setModifierBit(Modifier.FINAL)
                "static" -> smaliClass.setModifierBit(Modifier.STATIC)
            }
        }

        if (settings.mode == AnalyzeMode.FULL)
            ranges.add(SimpleStyledSegment(ctx.textRange, "keyword"))

        return visitChildren(ctx)
    }

    override fun visitMove16Instruction(ctx: SmaliParser.Move16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitAgetShortInstruction(ctx: SmaliParser.AgetShortInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && arrayElementType !== SmaliClass.Primitives.SHORT) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitMoveWideFrom16Instruction(ctx: SmaliParser.MoveWideFrom16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitSGetBooleanInstruction(ctx: SmaliParser.SGetBooleanInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && fieldClass !== SmaliClass.Primitives.BOOLEAN) {
                //TODO: highlight error
            }


            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitSGetInstruction(ctx: SmaliParser.SGetInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitIfLabel(ctx: SmaliParser.IfLabelContext): SmaliClass {

        if (settings.mode == AnalyzeMode.FULL) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.label().labelName()
            val label = currentMethod.getOrCreateLabel(labelNameContext.text)

            ranges.add(SmaliAnalysisSegment(
                    labelNameContext.textRange,
                    label,
                    project.getLinksFor(label).firstOrNull())
            )
        }

        return visitChildren(ctx)
    }

    override fun visitSGetWideInstruction(ctx: SmaliParser.SGetWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitMoveObjectFrom16Instruction(ctx: SmaliParser.MoveObjectFrom16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitAgetWideInstruction(ctx: SmaliParser.AgetWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren
            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitConst4Instruction(ctx: SmaliParser.Const4InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitConstString(ctx: SmaliParser.ConstStringContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val clazz = SmaliComponent.classFromName("Ljava/lang/String;") ?: return@tunableVisitChildren
            findMethod(ctx)?.registerToClassMap?.set(ctx.registerIdentifier().text, clazz)
        }
    }

    override fun visitSGetCharInstruction(ctx: SmaliParser.SGetCharInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && fieldClass !== SmaliClass.Primitives.CHAR) {
                //TODO: highlight error
            }


            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitConstStringJumbo(ctx: SmaliParser.ConstStringJumboContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val clazz = SmaliComponent.classFromName("Ljava/lang/String;") ?: return@tunableVisitChildren
            findMethod(ctx)?.registerToClassMap?.set(ctx.registerIdentifier().text, clazz)
        }
    }

    override fun visitStringLiteral(ctx: SmaliParser.StringLiteralContext): SmaliClass {
        if (settings.mode == AnalyzeMode.FULL)
            ranges.add(SimpleStyledSegment(ctx.textRange, "string"))

        return visitChildren(ctx)
    }

    override fun visitSGetObjectInstruction(ctx: SmaliParser.SGetObjectInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitSGetShortInstruction(ctx: SmaliParser.SGetShortInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && fieldClass !== SmaliClass.Primitives.SHORT) {
                //TODO: highlight error
            }


            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitSourceDirective(ctx: SmaliParser.SourceDirectiveContext): SmaliClass {
        if (settings.mode == AnalyzeMode.FULL)
            ranges.add(SimpleStyledSegment(ctx.SOURCE_DIRECTIVE().textRange, "keyword"))

        return visitChildren(ctx)
    }

    override fun defaultResult(): SmaliClass {
        return smaliClass
    }

    // TODO implement synthetic, annotation, enum
    override fun visitClassModifier(ctx: SmaliParser.ClassModifierContext): SmaliClass {
        when (ctx.text) {
            "public" -> smaliClass.setModifierBit(Modifier.PUBLIC)
            "protected" -> smaliClass.setModifierBit(Modifier.PROTECTED)
            "private" -> smaliClass.setModifierBit(Modifier.PRIVATE)
            "final" -> smaliClass.setModifierBit(Modifier.FINAL)
            "static" -> smaliClass.setModifierBit(Modifier.STATIC)
            "abstract" -> smaliClass.setModifierBit(Modifier.ABSTRACT)
            "interface" -> smaliClass.setModifierBit(Modifier.INTERFACE)
        }

        if (settings.mode == AnalyzeMode.FULL)
            ranges.add(SimpleStyledSegment(ctx.textRange, "keyword"))

        return visitChildren(ctx)
    }

    override fun visitErrorNode(node: ErrorNode): SmaliClass {

        val f = smaliClass.file

        if (f != null) {
            val error = Error.from(node)
            errors.add(error)

            if (settings.mode == AnalyzeMode.FULL)
                ranges.add(error)
        }

        return super.visitErrorNode(node)
    }

    override fun visitClassName(ctx: SmaliParser.ClassNameContext): SmaliClass {
//        smaliClass.parentPackage = project.getSmaliPackageForClassName(ctx.text) ?: kotlin.run {
//            return smaliClass
//        }

        return smaliClass
    }

    override fun visitAgetCharInstruction(ctx: SmaliParser.AgetCharInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && arrayElementType !== SmaliClass.Primitives.CHAR) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitAgetBooleanInstruction(ctx: SmaliParser.AgetBooleanInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && arrayElementType !== SmaliClass.Primitives.BOOLEAN) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitMoveResultWideInstruction(ctx: SmaliParser.MoveResultWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            var parent: RuleContext = ctx.parent
            var currentHolder: RuleContext = ctx

            while (parent.childCount == 1) {
                currentHolder = parent
                parent = parent.parent
            }

            if (parent is ParserRuleContext) {
                val i = parent.children.indexOf(currentHolder)
                if (i > 0) {
                    var previousInstruction = parent.children[i - 1]
                    while (previousInstruction.childCount == 1) {
                        previousInstruction = previousInstruction.getChild(0)
                    }

                    val classNameContext = when (previousInstruction) {
                        is SmaliParser.InvokeVirtualInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeSuperInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeDirectInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeStaticInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.InvokeInterfaceInstructionContext ->
                            previousInstruction.methodInvocationTarget().methodSignature().methodReturnType()

                        is SmaliParser.FilledNewArrayInstructionContext ->
                            previousInstruction.arrayElementType()

                        is SmaliParser.FilledNewArrayRangeInstructionContext ->
                            previousInstruction.arrayElementType()

                        else -> return@tunableVisitChildren
                    }

                    val clazz = SmaliComponent.classFromName(classNameContext.text) ?: return@tunableVisitChildren
                    currentMethod.registerToClassMap[ctx.registerIdentifier().text] = clazz
                }
            }
        }
    }

    override fun visitRegistersDirective(ctx: SmaliParser.RegistersDirectiveContext): SmaliClass {
        findMethod(ctx)?.registers = ctx.numericLiteral().text.toInt()
        return visitChildren(ctx)
    }

    override fun visitSGetByteInstruction(ctx: SmaliParser.SGetByteInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliComponent.classFromName(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && fieldClass !== SmaliClass.Primitives.BYTE) {
                //TODO: highlight error
            }


            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitConstWideInstruction(ctx: SmaliParser.ConstWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitGoto32Instruction(ctx: SmaliParser.Goto32InstructionContext): SmaliClass {

        if (settings.mode == AnalyzeMode.FULL) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.label().labelName()
            val label = currentMethod.getOrCreateLabel(labelNameContext.text)

            ranges.add(SmaliAnalysisSegment(
                    labelNameContext.textRange,
                    label,
                    project.getLinksFor(label).firstOrNull())
            )
        }

        return visitChildren(ctx)
    }

    override fun visitMoveObjectInstruction(ctx: SmaliParser.MoveObjectInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitMoveWideInstruction(ctx: SmaliParser.MoveWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitMoveInstruction(ctx: SmaliParser.MoveInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitMethodBodyStatement(ctx: SmaliParser.MethodBodyStatementContext): SmaliClass {
//        println(ctx.textWithSeparator(" "))
//        println("inv: "+ ctx.invokingState)
        return visitChildren(ctx)
    }

    override fun visitCheckCastInstruction(ctx: SmaliParser.CheckCastInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val method = findMethod(ctx) ?: return@tunableVisitChildren
            val clazz = SmaliComponent.classFromName(ctx.checkCastType().text) ?: return@tunableVisitChildren

            method.registerToClassMap[ctx.targetRegister().text] = clazz
        }
    }

    override fun visitArrayLengthInstruction(ctx: SmaliParser.ArrayLengthInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.targetRegister().text] = SmaliClass.Primitives.INT
        }
    }

    override fun visitConstWide32Instruction(ctx: SmaliParser.ConstWide32InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitNewInstanceInstruction(ctx: SmaliParser.NewInstanceInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val clazz = SmaliComponent.classFromName(ctx.newInstanceType().text) ?: return@tunableVisitChildren
            currentMethod.registerToClassMap[ctx.targetRegister().text] = clazz
        }
    }

    override fun visitMoveObject16Instruction(ctx: SmaliParser.MoveObject16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitGoto16Instruction(ctx: SmaliParser.Goto16InstructionContext): SmaliClass {

        if (settings.mode == AnalyzeMode.FULL) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.label().labelName()
            val label = currentMethod.getOrCreateLabel(labelNameContext.text)

            ranges.add(SmaliAnalysisSegment(
                    labelNameContext.textRange,
                    label,
                    project.getLinksFor(label).firstOrNull())
            )
        }

        return visitChildren(ctx)
    }

    override fun visitAgetByteInstruction(ctx: SmaliParser.AgetByteInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren

            if (settings.mode == AnalyzeMode.FULL && arrayElementType !== SmaliClass.Primitives.BYTE) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitFieldInvocationTarget(ctx: SmaliParser.FieldInvocationTargetContext): SmaliClass {

        val targetClass = SmaliComponent.classFromName(ctx.referenceOrArrayType().text)
        val targetField = targetClass?.findOrCreateField(ctx.fieldNameAndType().fieldName().text,
                ctx.fieldNameAndType().fieldType().text) ?: return visitChildren(ctx)

        if (settings.mode == AnalyzeMode.FULL) {

            val links = project.getLinksFor(targetField)
//            dependencies.add(links)

            ranges.add(SmaliAnalysisSegment(
                    ctx.fieldNameAndType().fieldName().textRange,
                    targetField,
                    links.firstOrNull()))
        } else if (!targetField.exists()) {
            errors.add(Error(IntRange.EMPTY, ""))
        }

        return visitChildren(ctx)
    }

    override fun visitConstWide16Instruction(ctx: SmaliParser.ConstWide16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitNullLiteral(ctx: SmaliParser.NullLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitImplementsDirective(ctx: SmaliParser.ImplementsDirectiveContext): SmaliClass {
        if (settings.mode == AnalyzeMode.FULL)
            ranges.add(SimpleStyledSegment(ctx.IMPLEMENTS_DIRECTIVE().textRange, "keyword"))

        SmaliComponent.classFromName(ctx.referenceType().text)?.also {
            smaliClass.interfaces.add(it)
        }

        return visitChildren(ctx)
    }

    override fun visitSubannotationDirective(ctx: SmaliParser.SubannotationDirectiveContext): SmaliClass {
        if (settings.mode == AnalyzeMode.FULL) {
            ranges.add(SimpleStyledSegment(ctx.SUBANNOTATION_DIRECTIVE().textRange, "keyword"))
            ranges.add(SimpleStyledSegment(ctx.SUBANNOTATION_END_DIRECTIVE().textRange, "keyword"))
        }

        return visitChildren(ctx)
    }

}
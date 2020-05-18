package ru.art2000.androraider.model.analyzer.smali

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor
import org.antlr.v4.runtime.tree.ErrorNode
import ru.art2000.androraider.model.analyzer.result.*
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliField
import ru.art2000.androraider.model.analyzer.smali.types.SmaliMethod
import ru.art2000.androraider.utils.parseCompound
import ru.art2000.androraider.utils.textRange
import java.lang.Exception
import java.lang.reflect.Modifier

class SmaliAllInOneAnalyzer(val project: ProjectAnalyzeResult, var smaliClass: SmaliClass, val withRanges: Boolean, val settings: SmaliVisitorSettings) :
        AbstractParseTreeVisitor<SmaliClass>(), SmaliParserVisitor<SmaliClass> {


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

        val clazz = project.getOrCreateClass(ctx.className().text)
        if (clazz !== smaliClass) {
            smaliClass.markAsNotExisting()
            if (clazz != null) {
                smaliClass = clazz
            }
        }

        smaliClass.textRange = ctx.className().textRange

        if (ctx.CLASS_DIRECTIVE() == null || !withRanges) {
            return visitChildren(ctx)
        }

        smaliClass.ranges.add(RangeStatusBase(ctx.CLASS_DIRECTIVE().textRange, "Class", listOf("keyword")))
        return visitChildren(ctx)
    }

    override fun visitNegFloatInstruction(ctx: SmaliParser.NegFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrLongInstruction(ctx: SmaliParser.ShrLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodReturnType(ctx: SmaliParser.MethodReturnTypeContext): SmaliClass {
        val method = findMethod(ctx)
        if (method != null) {
            val returnType = project.getOrCreateClass(ctx.text)
            if (returnType == null) {
                if (withRanges) {
                    val message = if (ctx.text.isNullOrEmpty())
                        "No return type provided"
                    else
                        "Unknown return type \"${ctx.text}\""

                    smaliClass.ranges.add(Error(ctx.textRange, message))
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
            if (withRanges)
                smaliClass.ranges.add(DynamicRangeStatus(smaliField.textRange, smaliField))
        }

    }

    override fun visitFieldDirective(ctx: SmaliParser.FieldDirectiveContext): SmaliClass {
        if (withRanges)
            smaliClass.ranges.add(RangeStatusBase(ctx.FIELD_DIRECTIVE().textRange, "Field", listOf("keyword")))

        return visitChildren(FieldDeclarationContextWrapper(ctx))
    }

    override fun visitNewArrayInstruction(ctx: SmaliParser.NewArrayInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val clazz = project.getOrCreateClass(ctx.arrayElementType().text) ?: return@tunableVisitChildren
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
        if (withRanges) {
            smaliClass.ranges.add(RangeStatusBase(ctx.METHOD_DIRECTIVE().textRange, "Method", listOf("keyword")))
            smaliClass.ranges.add(RangeStatusBase(ctx.METHOD_END_DIRECTIVE().textRange, "End method", listOf("keyword")))
        }

        return visitChildren(MethodDirectiveContextWrapper(ctx))
    }

    override fun visitThrowInstruction(ctx: SmaliParser.ThrowInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputBooleanInstruction(ctx: SmaliParser.IputBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToDoubleInstruction(ctx: SmaliParser.IntToDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
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

                    val clazz = project.getOrCreateClass(classNameContext.text) ?: return@tunableVisitChildren
                    currentMethod.registerToClassMap[ctx.registerIdentifier().text] = clazz
                }
            }
        }
    }

    override fun visitPackedSwitchDirective(ctx: SmaliParser.PackedSwitchDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetBooleanInstruction(ctx: SmaliParser.IgetBooleanInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (withRanges && fieldClass !== SmaliClass.Primitives.BOOLEAN) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitIgetCharInstruction(ctx: SmaliParser.IgetCharInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (withRanges && fieldClass !== SmaliClass.Primitives.CHAR) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitGotoInstruction(ctx: SmaliParser.GotoInstructionContext): SmaliClass {

        if (withRanges) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.label().labelName()

            smaliClass.ranges.add(DynamicRangeStatus(
                    labelNameContext.textRange,
                    currentMethod.getOrCreateLabel(labelNameContext.text))
            )
        }

        return visitChildren(ctx)
    }

    override fun visitRemFloat2addrInstruction(ctx: SmaliParser.RemFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBinaryInstruction(ctx: SmaliParser.BinaryInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRsubIntInstruction(ctx: SmaliParser.RsubIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationDirective(ctx: SmaliParser.AnnotationDirectiveContext): SmaliClass {
        if (withRanges) {
            smaliClass.ranges.add(RangeStatusBase(ctx.ANNOTATION_DIRECTIVE().textRange, "Annotation", listOf("keyword")))
            smaliClass.ranges.add(RangeStatusBase(ctx.ANNOTATION_END_DIRECTIVE().textRange, "Annotation", listOf("keyword")))
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

            description += "; hex: 0x${hex}; dec: $decimal"
        } catch (e: Exception) {
            println("Error: ${ctx.text} in ${smaliClass.associatedFile}")
            e.printStackTrace()
        }

        if (withRanges) {
            smaliClass.ranges.add(RangeStatusBase(ctx.textRange, description, listOf("number")))
        }
        return visitChildren(ctx)
    }

    override fun visitCharType(ctx: SmaliParser.CharTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationField(ctx: SmaliParser.AnnotationFieldContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayType(ctx: SmaliParser.ArrayTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputByteInstruction(ctx: SmaliParser.AputByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSuperDirective(ctx: SmaliParser.SuperDirectiveContext): SmaliClass {
        if (ctx.SUPER_DIRECTIVE() != null && withRanges) {
            smaliClass.ranges.add(RangeStatusBase(ctx.SUPER_DIRECTIVE().textRange, "Super", listOf("keyword")))
        }
        return visitChildren(ctx)
    }

    override fun visitSubDoubleInstruction(ctx: SmaliParser.SubDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDoubleToFloatInstruction(ctx: SmaliParser.DoubleToFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndLongInstruction(ctx: SmaliParser.AndLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShlLongInstruction(ctx: SmaliParser.ShlLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchGotoLabel(ctx: SmaliParser.CatchGotoLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReturnWideInstruction(ctx: SmaliParser.ReturnWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFloatToIntInstruction(ctx: SmaliParser.FloatToIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutByteInstruction(ctx: SmaliParser.SPutByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfEqzInstruction(ctx: SmaliParser.IfEqzInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    private inline fun <reified T: SmaliParser.InstructionContext> getFromBodyStatement(ctx: SmaliParser.MethodBodyStatementContext): T? {
        return ctx.instruction()?.children?.find { it is T } as? T
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

                    val clazz = project.getOrCreateClass(classNameContext.text) ?: return@tunableVisitChildren
                    currentMethod.registerToClassMap[ctx.registerIdentifier().text] = clazz
                }
            }
        }
    }

    override fun visitIfLtzInstruction(ctx: SmaliParser.IfLtzInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivIntLit16Instruction(ctx: SmaliParser.DivIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddIntLit16Instruction(ctx: SmaliParser.AddIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegisterRange(ctx: SmaliParser.RegisterRangeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldType(ctx: SmaliParser.FieldTypeContext): SmaliClass {
        val grandfather = ctx.parent.parent
        if (grandfather is FieldDeclarationContextWrapper) {
            val fieldType = project.getOrCreateClass(ctx.text)

            if (fieldType == null) {
                if (withRanges) {
                    val message = if (ctx.text.isNullOrEmpty())
                        "No field type provided"
                    else
                        "Unknown field type \"${ctx.text}\""

                    smaliClass.ranges.add(Error(ctx.textRange, message))
                }
            } else {
                grandfather.smaliField.type = fieldType
            }
        }

        return visitChildren(ctx)
    }

    override fun visitMethodParameterType(ctx: SmaliParser.MethodParameterTypeContext): SmaliClass {
        var offset = 0
        if (withRanges) {
            parseCompound(ctx.text).forEach { parameterText ->
                val parameterClass = project.getOrCreateClass(parameterText)
                smaliClass.ranges.add(DynamicRangeStatus(
                        (ctx.start.startIndex + offset)..(ctx.start.startIndex + offset + parameterText.lastIndex),
                        parameterClass)
                )
                offset += parameterText.length
            }
        }
        return smaliClass
    }

    override fun visitCheckCastType(ctx: SmaliParser.CheckCastTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSourceRegister(ctx: SmaliParser.SourceRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReferenceType(ctx: SmaliParser.ReferenceTypeContext): SmaliClass {
        val clazz = project.getOrCreateClass(ctx.text)
        if (withRanges)
            smaliClass.ranges.add(DynamicRangeStatus(ctx.textRange, clazz))

        return visitChildren(ctx)
    }

    override fun visitInvokeStaticInstruction(ctx: SmaliParser.InvokeStaticInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstanceOfInstruction(ctx: SmaliParser.InstanceOfInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val method = findMethod(ctx) ?: return@tunableVisitChildren

            method.registerToClassMap[ctx.targetRegister().text] = SmaliClass.Primitives.INT
        }
    }

    override fun visitAputInstruction(ctx: SmaliParser.AputInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetWideInstruction(ctx: SmaliParser.IgetWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitFloatNumericLiteral(ctx: SmaliParser.FloatNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetObjectInstruction(ctx: SmaliParser.IgetObjectInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitXorIntInstruction(ctx: SmaliParser.XorIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLabelName(ctx: SmaliParser.LabelNameContext): SmaliClass {
        return visitChildren(ctx)
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
        val txt = ctx.text
        if (!withRanges) return visitChildren(ctx)
        val method = findMethod(ctx)

        when {
            txt.matches(Regex("p\\d+")) -> {
                val num = txt.substring(1).toInt()

                when {
                    method == null -> {
                        smaliClass.ranges.add(RangeStatusBase(
                                ctx.textRange,
                                "Param $num, failed to get max possible value",
                                listOf("param"))
                        )
                    }
                    num >= method.parameters.size -> {
                        smaliClass.ranges.add(Error(
                                ctx.textRange,
                                "Invalid param index $num: must be in range 0..${method.parameters.size - 1}")
                        )
                    }
                    else -> {
//                        smaliClass.ranges.add(RangeStatusBase(
//                                ctx.textRange,
//                                "Param $num, max ${method.parameters.size - 1}",
//                                listOf("param"))
//                        )

                        smaliClass.ranges.add(RegisterRangeStatus(
                                ctx.textRange,
                                Register.PARAM,
                                method.registerToClassMap[txt])
                        )
                    }
                }
            }

            txt.matches(Regex("v\\d+")) -> {
                val num = txt.substring(1).toInt()

                when {
                    method == null -> {
                        smaliClass.ranges.add(RangeStatusBase(
                                ctx.textRange,
                                "Local $num, failed to get max possible value",
                                listOf("local"))
                        )
                    }
                    num >= method.locals -> {
                        smaliClass.ranges.add(Error(
                                ctx.textRange,
                                "Invalid local index $num: must be in range 0..${method.locals - 1}")
                        )
                    }
                    else -> {
//                        smaliClass.ranges.add(RangeStatusBase(
//                                ctx.textRange,
//                                "Local $num, max ${method.locals - 1}",
//                                listOf("local"))
//                        )

                        smaliClass.ranges.add(RegisterRangeStatus(
                                ctx.textRange,
                                Register.LOCAL,
                                method.registerToClassMap[txt])
                        )
                    }
                }
            }

            else -> {
                smaliClass.ranges.add(Error(ctx.textRange, "Invalid register identifier"))
            }
        }

        return visitChildren(ctx)
    }

    override fun visitFloatToLongInstruction(ctx: SmaliParser.FloatToLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationFieldValue(ctx: SmaliParser.AnnotationFieldValueContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulLong2addrInstruction(ctx: SmaliParser.MulLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayElementRegisters(ctx: SmaliParser.ArrayElementRegistersContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveWide16Instruction(ctx: SmaliParser.MoveWide16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitRemIntLit8Instruction(ctx: SmaliParser.RemIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutWideInstruction(ctx: SmaliParser.SPutWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetObjectInstruction(ctx: SmaliParser.AgetObjectInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren
            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitAddDoubleInstruction(ctx: SmaliParser.AddDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodIdentifier(ctx: SmaliParser.MethodIdentifierContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPositiveNumericLiteral(ctx: SmaliParser.PositiveNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToCharInstruction(ctx: SmaliParser.IntToCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstWideHigh16Instruction(ctx: SmaliParser.ConstWideHigh16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitUshrLong2addrInstruction(ctx: SmaliParser.UshrLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeSuperInstruction(ctx: SmaliParser.InvokeSuperInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputWideInstruction(ctx: SmaliParser.AputWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeCustomInstruction(ctx: SmaliParser.InvokeCustomInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSuperName(ctx: SmaliParser.SuperNameContext): SmaliClass {
        smaliClass.parentClass = project.getOrCreateClass(ctx.text)
        return visitChildren(ctx)
    }

    override fun visitAddLongInstruction(ctx: SmaliParser.AddLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnyType(ctx: SmaliParser.AnyTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulIntLit16Instruction(ctx: SmaliParser.MulIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitExtendedParamDirective(ctx: SmaliParser.ExtendedParamDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFillArrayDataInstruction(ctx: SmaliParser.FillArrayDataInstructionContext): SmaliClass {

        if (withRanges) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.filledArrayDataLabel().label().labelName()

            smaliClass.ranges.add(DynamicRangeStatus(
                    labelNameContext.textRange,
                    currentMethod.getOrCreateLabel(labelNameContext.text))
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

    override fun visitReferenceOrArrayType(ctx: SmaliParser.ReferenceOrArrayTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchDirective(ctx: SmaliParser.SparseSwitchDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrIntInstruction(ctx: SmaliParser.ShrIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToShortInstruction(ctx: SmaliParser.IntToShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGezInstruction(ctx: SmaliParser.IfGezInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubLong2addrInstruction(ctx: SmaliParser.SubLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubFloatInstruction(ctx: SmaliParser.SubFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeConstMethodHandleInstruction(ctx: SmaliParser.InvokeConstMethodHandleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrLong2addrInstruction(ctx: SmaliParser.OrLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivFloatInstruction(ctx: SmaliParser.DivFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchDirective(ctx: SmaliParser.CatchDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetShortInstruction(ctx: SmaliParser.IgetShortInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (withRanges && fieldClass !== SmaliClass.Primitives.SHORT) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitVoidType(ctx: SmaliParser.VoidTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBooleanLiteral(ctx: SmaliParser.BooleanLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    // TODO implement synthetic, bridge, synchronized, strictfp, varargs
    override fun visitMethodModifier(ctx: SmaliParser.MethodModifierContext): SmaliClass {

        if (withRanges)
            smaliClass.ranges.add(RangeStatusBase(ctx.textRange,"MethodModifier", listOf("keyword")))

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

    override fun visitInvokeDirectRangeInstruction(ctx: SmaliParser.InvokeDirectRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGeInstruction(ctx: SmaliParser.IfGeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeSuperRangeInstruction(ctx: SmaliParser.InvokeSuperRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveFrom16Instruction(ctx: SmaliParser.MoveFrom16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitTargetRegister(ctx: SmaliParser.TargetRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitTernaryInstruction(ctx: SmaliParser.TernaryInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorIntLit16Instruction(ctx: SmaliParser.XorIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNewInstanceType(ctx: SmaliParser.NewInstanceTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivIntInstruction(ctx: SmaliParser.DivIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemDouble2addrInstruction(ctx: SmaliParser.RemDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchFromLabel(ctx: SmaliParser.CatchFromLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegisterListRegisters(ctx: SmaliParser.RegisterListRegistersContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLongToFloatInstruction(ctx: SmaliParser.LongToFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetByteInstruction(ctx: SmaliParser.IgetByteInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (withRanges && fieldClass !== SmaliClass.Primitives.BYTE) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitIndexRegister(ctx: SmaliParser.IndexRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndLong2addrInstruction(ctx: SmaliParser.AndLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeDirectInstruction(ctx: SmaliParser.InvokeDirectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayDataEntry(ctx: SmaliParser.ArrayDataEntryContext): SmaliClass {
        return visitChildren(ctx)
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

    override fun visitFilledArrayDataLabel(ctx: SmaliParser.FilledArrayDataLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDoubleType(ctx: SmaliParser.DoubleTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemIntLit16Instruction(ctx: SmaliParser.RemIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchDirectiveLabels(ctx: SmaliParser.PackedSwitchDirectiveLabelsContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodInvocationTarget(ctx: SmaliParser.MethodInvocationTargetContext): SmaliClass {
        val targetMethod = smaliMethodFromInvocationContext(ctx)

        if (withRanges)
            smaliClass.ranges.add(DynamicRangeStatus(ctx.methodSignature().methodIdentifier().textRange, targetMethod))

        return visitChildren(ctx)
    }

    private fun smaliFieldFromInvocationContext(ctx: SmaliParser.FieldInvocationTargetContext): SmaliField? {
        val targetClass = project.getOrCreateClass(ctx.referenceOrArrayType().text)
        val fieldName = ctx.fieldNameAndType().fieldName().text
        val fieldType = ctx.fieldNameAndType().fieldType().text
        return targetClass?.findOrCreateField(fieldName, fieldType)
    }

    private fun smaliMethodFromInvocationContext(ctx: SmaliParser.MethodInvocationTargetContext): SmaliMethod? {
        val targetClass = project.getOrCreateClass(ctx.referenceOrArrayType().text)
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

            if (withRanges) {
                smaliClass.ranges.add(DynamicRangeStatus(smaliMethod.textRange, smaliMethod))
            }
        }

    }

    override fun visitMethodDeclaration(ctx: SmaliParser.MethodDeclarationContext): SmaliClass {
        val arguments = ctx.methodSignature()?.methodArguments()

        if (arguments != null) {
            var offset = arguments.start.startIndex

            parseCompound(arguments.text)
                    .mapNotNull {
                        val parameterType = project.getOrCreateClass(it)
                        if (parameterType == null && withRanges) {
                            smaliClass.ranges.add(Error(offset until offset + it.length, "Unknown type \"$it\""))
                        }
                        offset += it.length
                        parameterType
                    }
        }

        return visitChildren(ctx)
    }

    override fun visitMethodBody(ctx: SmaliParser.MethodBodyContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputObjectInstruction(ctx: SmaliParser.IputObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmplDoubleInstruction(ctx: SmaliParser.CmplDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShortType(ctx: SmaliParser.ShortTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivInt2addrInstruction(ctx: SmaliParser.DivInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFilledNewArrayInstruction(ctx: SmaliParser.FilledNewArrayInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivDouble2addrInstruction(ctx: SmaliParser.DivDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitByteType(ctx: SmaliParser.ByteTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetInstruction(ctx: SmaliParser.IgetInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.instanceField().fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.targetRegister().text] = fieldClass
        }
    }

    override fun visitPackedSwitchRegister(ctx: SmaliParser.PackedSwitchRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeStaticRangeInstruction(ctx: SmaliParser.InvokeStaticRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLineDirective(ctx: SmaliParser.LineDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodParameterLiteral(ctx: SmaliParser.MethodParameterLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGtInstruction(ctx: SmaliParser.IfGtInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrIntLit8Instruction(ctx: SmaliParser.OrIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayElementType(ctx: SmaliParser.ArrayElementTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToByteInstruction(ctx: SmaliParser.IntToByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
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

        if (withRanges)
            smaliClass.ranges.add(RangeStatusBase(ctx.textRange, "FieldModifier", listOf("keyword")))

        return visitChildren(ctx)
    }

    override fun visitMove16Instruction(ctx: SmaliParser.Move16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitDivIntLit8Instruction(ctx: SmaliParser.DivIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorLong2addrInstruction(ctx: SmaliParser.XorLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetShortInstruction(ctx: SmaliParser.AgetShortInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren

            if (withRanges && arrayElementType !== SmaliClass.Primitives.SHORT) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitAndIntLit8Instruction(ctx: SmaliParser.AndIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmpgFloatInstruction(ctx: SmaliParser.CmpgFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveWideFrom16Instruction(ctx: SmaliParser.MoveWideFrom16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitSubInt2addrInstruction(ctx: SmaliParser.SubInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrInt2addrInstruction(ctx: SmaliParser.OrInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLabel(ctx: SmaliParser.LabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitParse(ctx: SmaliParser.ParseContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddDouble2addrInstruction(ctx: SmaliParser.AddDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalDirective(ctx: SmaliParser.LocalDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocaDirectiveVariableName(ctx: SmaliParser.LocaDirectiveVariableNameContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchLabel(ctx: SmaliParser.PackedSwitchLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchDirectiveValue(ctx: SmaliParser.SparseSwitchDirectiveValueContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputShortInstruction(ctx: SmaliParser.AputShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetBooleanInstruction(ctx: SmaliParser.SGetBooleanInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (withRanges && fieldClass !== SmaliClass.Primitives.BOOLEAN) {
                //TODO: highlight error
            }


            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitIfNezInstruction(ctx: SmaliParser.IfNezInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulIntInstruction(ctx: SmaliParser.MulIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputShortInstruction(ctx: SmaliParser.IputShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSourceName(ctx: SmaliParser.SourceNameContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddIntLit8Instruction(ctx: SmaliParser.AddIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulDoubleInstruction(ctx: SmaliParser.MulDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokePolymorphicInstruction(ctx: SmaliParser.InvokePolymorphicInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchDirectiveLabel(ctx: SmaliParser.PackedSwitchDirectiveLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNonVoidType(ctx: SmaliParser.NonVoidTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNopInstruction(ctx: SmaliParser.NopInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchAllDirective(ctx: SmaliParser.CatchAllDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetInstruction(ctx: SmaliParser.SGetInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitIfLabel(ctx: SmaliParser.IfLabelContext): SmaliClass {

        if (withRanges) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.label().labelName()

            smaliClass.ranges.add(DynamicRangeStatus(
                    labelNameContext.textRange,
                    currentMethod.getOrCreateLabel(labelNameContext.text))
            )
        }

        return visitChildren(ctx)
    }

    override fun visitSGetWideInstruction(ctx: SmaliParser.SGetWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitRightRegister(ctx: SmaliParser.RightRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodSignature(ctx: SmaliParser.MethodSignatureContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNegLongInstruction(ctx: SmaliParser.NegLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveObjectFrom16Instruction(ctx: SmaliParser.MoveObjectFrom16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitMethodArguments(ctx: SmaliParser.MethodArgumentsContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubIntInstruction(ctx: SmaliParser.SubIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchToLabel(ctx: SmaliParser.CatchToLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrLongInstruction(ctx: SmaliParser.OrLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIdentifier(ctx: SmaliParser.IdentifierContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNegativeNumericLiteral(ctx: SmaliParser.NegativeNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBooleanType(ctx: SmaliParser.BooleanTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulIntLit8Instruction(ctx: SmaliParser.MulIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddInt2addrInstruction(ctx: SmaliParser.AddInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrInt2addrInstruction(ctx: SmaliParser.ShrInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetWideInstruction(ctx: SmaliParser.AgetWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren
            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitAnnotationType(ctx: SmaliParser.AnnotationTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConst4Instruction(ctx: SmaliParser.Const4InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitIputByteInstruction(ctx: SmaliParser.IputByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemDoubleInstruction(ctx: SmaliParser.RemDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstString(ctx: SmaliParser.ConstStringContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val clazz = project.getOrCreateClass("Ljava/lang/String;") ?: return@tunableVisitChildren
            findMethod(ctx)?.registerToClassMap?.set(ctx.registerIdentifier().text, clazz)
        }
    }

    override fun visitSGetCharInstruction(ctx: SmaliParser.SGetCharInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (withRanges && fieldClass !== SmaliClass.Primitives.CHAR) {
                //TODO: highlight error
            }


            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitUshrInt2addrInstruction(ctx: SmaliParser.UshrInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstStringJumbo(ctx: SmaliParser.ConstStringJumboContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val clazz = project.getOrCreateClass("Ljava/lang/String;") ?: return@tunableVisitChildren
            findMethod(ctx)?.registerToClassMap?.set(ctx.registerIdentifier().text, clazz)
        }
    }

    override fun visitField(ctx: SmaliParser.FieldContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitStringLiteral(ctx: SmaliParser.StringLiteralContext): SmaliClass {
        if (withRanges)
            smaliClass.ranges.add(RangeStatusBase(ctx.textRange, "String", listOf("string")))
        return visitChildren(ctx)
    }

    override fun visitReturnInstruction(ctx: SmaliParser.ReturnInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeVirtualInstruction(ctx: SmaliParser.InvokeVirtualInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShlLong2addrInstruction(ctx: SmaliParser.ShlLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShlIntInstruction(ctx: SmaliParser.ShlIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrIntInstruction(ctx: SmaliParser.UshrIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetObjectInstruction(ctx: SmaliParser.SGetObjectInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitFilledNewArrayRangeInstruction(ctx: SmaliParser.FilledNewArrayRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetShortInstruction(ctx: SmaliParser.SGetShortInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (withRanges && fieldClass !== SmaliClass.Primitives.SHORT) {
                //TODO: highlight error
            }


            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitAndIntLit16Instruction(ctx: SmaliParser.AndIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorIntLit8Instruction(ctx: SmaliParser.XorIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSourceDirective(ctx: SmaliParser.SourceDirectiveContext): SmaliClass {
        if (withRanges)
            smaliClass.ranges.add(RangeStatusBase(ctx.SOURCE_DIRECTIVE().textRange, "Class", listOf("keyword")))
        return visitChildren(ctx)
    }

    override fun visitOrIntLit16Instruction(ctx: SmaliParser.OrIntLit16InstructionContext): SmaliClass {
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

        if (withRanges)
            smaliClass.ranges.add(RangeStatusBase(ctx.textRange, "ClassModifier", listOf("keyword")))

        return visitChildren(ctx)
    }

    override fun visitLocalEndDirective(ctx: SmaliParser.LocalEndDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalDirectiveGenericHint(ctx: SmaliParser.LocalDirectiveGenericHintContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrIntLit8Instruction(ctx: SmaliParser.ShrIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutShortInstruction(ctx: SmaliParser.SPutShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputBooleanInstruction(ctx: SmaliParser.AputBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeInterfaceInstruction(ctx: SmaliParser.InvokeInterfaceInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmpLongInstruction(ctx: SmaliParser.CmpLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitErrorNode(node: ErrorNode): SmaliClass {
        if (withRanges)
            smaliClass.ranges.find { it.range.first == node.symbol.startIndex } ?: smaliClass.ranges.add(Error.from(node))
        return super.visitErrorNode(node)
    }

    override fun visitClassName(ctx: SmaliParser.ClassNameContext): SmaliClass {
        smaliClass.parentPackage = project.getPackageForClassName(ctx.text) ?: kotlin.run {
            return smaliClass
        }

        return smaliClass
    }

    override fun visitAgetCharInstruction(ctx: SmaliParser.AgetCharInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren

            if (withRanges && arrayElementType !== SmaliClass.Primitives.CHAR) {
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

            if (withRanges && arrayElementType !== SmaliClass.Primitives.BOOLEAN) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitSparseSwitchRegister(ctx: SmaliParser.SparseSwitchRegisterContext): SmaliClass {
        return visitChildren(ctx)
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

                    val clazz = project.getOrCreateClass(classNameContext.text) ?: return@tunableVisitChildren
                    currentMethod.registerToClassMap[ctx.registerIdentifier().text] = clazz
                }
            }
        }
    }

    override fun visitRsubIntLit8Instruction(ctx: SmaliParser.RsubIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDecimalNumericLiteral(ctx: SmaliParser.DecimalNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemIntInstruction(ctx: SmaliParser.RemIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstanceField(ctx: SmaliParser.InstanceFieldContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegistersDirective(ctx: SmaliParser.RegistersDirectiveContext): SmaliClass {
        findMethod(ctx)?.registers = ctx.numericLiteral().text.toInt()
        return visitChildren(ctx)
    }

    override fun visitSGetByteInstruction(ctx: SmaliParser.SGetByteInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = project.getOrCreateClass(
                    ctx.fieldInvocationTarget().fieldNameAndType().fieldType().text
            ) ?: return@tunableVisitChildren

            if (withRanges && fieldClass !== SmaliClass.Primitives.BYTE) {
                //TODO: highlight error
            }


            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitIntToFloatInstruction(ctx: SmaliParser.IntToFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddFloatInstruction(ctx: SmaliParser.AddFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddIntInstruction(ctx: SmaliParser.AddIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmplFloatInstruction(ctx: SmaliParser.CmplFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfEqInstruction(ctx: SmaliParser.IfEqInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputInstruction(ctx: SmaliParser.IputInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstWideInstruction(ctx: SmaliParser.ConstWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitIntType(ctx: SmaliParser.IntTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayRegister(ctx: SmaliParser.ArrayRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokePolymorphicRangeInstruction(ctx: SmaliParser.InvokePolymorphicRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputCharInstruction(ctx: SmaliParser.IputCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitHexFloatLiteral(ctx: SmaliParser.HexFloatLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubLongInstruction(ctx: SmaliParser.SubLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToLongInstruction(ctx: SmaliParser.IntToLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulFloatInstruction(ctx: SmaliParser.MulFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitGoto32Instruction(ctx: SmaliParser.Goto32InstructionContext): SmaliClass {

        if (withRanges) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.label().labelName()

            smaliClass.ranges.add(DynamicRangeStatus(
                    labelNameContext.textRange,
                    currentMethod.getOrCreateLabel(labelNameContext.text))
            )
        }

        return visitChildren(ctx)
    }

    override fun visitSubDouble2addrInstruction(ctx: SmaliParser.SubDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitHexNumericLiteral(ctx: SmaliParser.HexNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveObjectInstruction(ctx: SmaliParser.MoveObjectInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitIputWideInstruction(ctx: SmaliParser.IputWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNonArrayType(ctx: SmaliParser.NonArrayTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstruction(ctx: SmaliParser.InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivFloat2addrInstruction(ctx: SmaliParser.DivFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCheckInstanceType(ctx: SmaliParser.CheckInstanceTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemLong2addrInstruction(ctx: SmaliParser.RemLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrIntLit8Instruction(ctx: SmaliParser.UshrIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayElementRegisterRange(ctx: SmaliParser.ArrayElementRegisterRangeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveWideInstruction(ctx: SmaliParser.MoveWideInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitFloatToDoubleInstruction(ctx: SmaliParser.FloatToDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfLeInstruction(ctx: SmaliParser.IfLeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddLong2addrInstruction(ctx: SmaliParser.AddLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrIntInstruction(ctx: SmaliParser.OrIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReturnVoidInstruction(ctx: SmaliParser.ReturnVoidInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmpgDoubleInstruction(ctx: SmaliParser.CmpgDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputObjectInstruction(ctx: SmaliParser.AputObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMonitorEnterInstruction(ctx: SmaliParser.MonitorEnterInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivLong2addrInstruction(ctx: SmaliParser.DivLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveInstruction(ctx: SmaliParser.MoveInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val newClassInRegister = currentMethod.registerToClassMap[ctx.rightRegister().text] ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.leftRegister().text] = newClassInRegister
        }
    }

    override fun visitShlInt2addrInstruction(ctx: SmaliParser.ShlInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitParamDirective(ctx: SmaliParser.ParamDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulFloat2addrInstruction(ctx: SmaliParser.MulFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeInterfaceRangeInstruction(ctx: SmaliParser.InvokeInterfaceRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLongToIntInstruction(ctx: SmaliParser.LongToIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNegDoubleInstruction(ctx: SmaliParser.NegDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLongType(ctx: SmaliParser.LongTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayDataDirective(ctx: SmaliParser.ArrayDataDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBinaryNumericLiteral(ctx: SmaliParser.BinaryNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFloatType(ctx: SmaliParser.FloatTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLongToDoubleInstruction(ctx: SmaliParser.LongToDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodBodyStatement(ctx: SmaliParser.MethodBodyStatementContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstanceRegister(ctx: SmaliParser.InstanceRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutCharInstruction(ctx: SmaliParser.SPutCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemInt2addrInstruction(ctx: SmaliParser.RemInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulDouble2addrInstruction(ctx: SmaliParser.MulDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeCustomRangeInstruction(ctx: SmaliParser.InvokeCustomRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCheckCastInstruction(ctx: SmaliParser.CheckCastInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val method = findMethod(ctx) ?: return@tunableVisitChildren
            val clazz = project.getOrCreateClass(ctx.checkCastType().text) ?: return@tunableVisitChildren

            method.registerToClassMap[ctx.targetRegister().text] = clazz
        }
    }

    override fun visitInvokeConstMethodTypeInstruction(ctx: SmaliParser.InvokeConstMethodTypeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayLengthInstruction(ctx: SmaliParser.ArrayLengthInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            currentMethod.registerToClassMap[ctx.targetRegister().text] = SmaliClass.Primitives.INT
        }
    }

    override fun visitArraySizeRegister(ctx: SmaliParser.ArraySizeRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReturnObjectInstruction(ctx: SmaliParser.ReturnObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemFloatInstruction(ctx: SmaliParser.RemFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputCharInstruction(ctx: SmaliParser.AputCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstWide32Instruction(ctx: SmaliParser.ConstWide32InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitLocalDirectiveType(ctx: SmaliParser.LocalDirectiveTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGtzInstruction(ctx: SmaliParser.IfGtzInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNewInstanceInstruction(ctx: SmaliParser.NewInstanceInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val clazz = project.getOrCreateClass(ctx.newInstanceType().text) ?: return@tunableVisitChildren
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

    override fun visitMulInt2addrInstruction(ctx: SmaliParser.MulInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationScope(ctx: SmaliParser.AnnotationScopeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitGoto16Instruction(ctx: SmaliParser.Goto16InstructionContext): SmaliClass {

        if (withRanges) {
            val currentMethod = findMethod(ctx) ?: return visitChildren(ctx)

            val labelNameContext = ctx.label().labelName()

            smaliClass.ranges.add(DynamicRangeStatus(
                    labelNameContext.textRange,
                    currentMethod.getOrCreateLabel(labelNameContext.text))
            )
        }

        return visitChildren(ctx)
    }

    override fun visitDoubleToLongInstruction(ctx: SmaliParser.DoubleToLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAssignableValue(ctx: SmaliParser.AssignableValueContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeVirtualRangeInstruction(ctx: SmaliParser.InvokeVirtualRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorInt2addrInstruction(ctx: SmaliParser.XorInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstClass(ctx: SmaliParser.ConstClassContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldName(ctx: SmaliParser.FieldNameContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNotIntInstruction(ctx: SmaliParser.NotIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMonitorExitInstruction(ctx: SmaliParser.MonitorExitInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivDoubleInstruction(ctx: SmaliParser.DivDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndInt2addrInstruction(ctx: SmaliParser.AndInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetByteInstruction(ctx: SmaliParser.AgetByteInstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {
            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren
            val arrayType = currentMethod.registerToClassMap[ctx.arrayRegister().text] ?: return@tunableVisitChildren
            val arrayElementType = arrayType.underlyingArrayType ?: return@tunableVisitChildren

            if (withRanges && arrayElementType !== SmaliClass.Primitives.BYTE) {
                //TODO: highlight error
            }

            currentMethod.registerToClassMap[ctx.targetRegister().text] = arrayElementType
        }
    }

    override fun visitShlIntLit8Instruction(ctx: SmaliParser.ShlIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulLongInstruction(ctx: SmaliParser.MulLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegisterList(ctx: SmaliParser.RegisterListContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldNameAndType(ctx: SmaliParser.FieldNameAndTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchInstruction(ctx: SmaliParser.PackedSwitchInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchIdent(ctx: SmaliParser.PackedSwitchIdentContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDoubleToIntInstruction(ctx: SmaliParser.DoubleToIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrLong2addrInstruction(ctx: SmaliParser.ShrLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchLabel(ctx: SmaliParser.SparseSwitchLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldInvocationTarget(ctx: SmaliParser.FieldInvocationTargetContext): SmaliClass {
        if (!withRanges) return visitChildren(ctx)

        val targetClass = project.getOrCreateClass(ctx.referenceOrArrayType().text)
        val targetField = targetClass?.findOrCreateField(ctx.fieldNameAndType().fieldName().text,
                ctx.fieldNameAndType().fieldType().text)

        smaliClass.ranges.add(DynamicRangeStatus(ctx.fieldNameAndType().fieldName().textRange, targetField))

        return visitChildren(ctx)
    }

    override fun visitLocalRestartDirective(ctx: SmaliParser.LocalRestartDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPrimitiveType(ctx: SmaliParser.PrimitiveTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubFloat2addrInstruction(ctx: SmaliParser.SubFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOctNumericLiteral(ctx: SmaliParser.OctNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveExceptionInstruction(ctx: SmaliParser.MoveExceptionInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLeftRegister(ctx: SmaliParser.LeftRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorLongInstruction(ctx: SmaliParser.XorLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfLtInstruction(ctx: SmaliParser.IfLtInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutInstruction(ctx: SmaliParser.SPutInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNegIntInstruction(ctx: SmaliParser.NegIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchExceptionType(ctx: SmaliParser.CatchExceptionTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemLongInstruction(ctx: SmaliParser.RemLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationValueScoped(ctx: SmaliParser.AnnotationValueScopedContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalDirectiveRegister(ctx: SmaliParser.LocalDirectiveRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNotLongInstruction(ctx: SmaliParser.NotLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndIntInstruction(ctx: SmaliParser.AndIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivLongInstruction(ctx: SmaliParser.DivLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchInstruction(ctx: SmaliParser.SparseSwitchInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfNeInstruction(ctx: SmaliParser.IfNeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddFloat2addrInstruction(ctx: SmaliParser.AddFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutBooleanInstruction(ctx: SmaliParser.SPutBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstWide16Instruction(ctx: SmaliParser.ConstWide16InstructionContext): SmaliClass {
        return tunableVisitChildren(ctx) {

            val currentMethod = findMethod(ctx) ?: return@tunableVisitChildren

            val fieldClass = SmaliClass.Primitives.INT

            currentMethod.registerToClassMap[ctx.registerIdentifier().text] = fieldClass
        }
    }

    override fun visitSPutObjectInstruction(ctx: SmaliParser.SPutObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfLezInstruction(ctx: SmaliParser.IfLezInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrLongInstruction(ctx: SmaliParser.UshrLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNullLiteral(ctx: SmaliParser.NullLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitImplementsDirective(ctx: SmaliParser.ImplementsDirectiveContext): SmaliClass {
        if (withRanges)
            smaliClass.ranges.add(RangeStatusBase(ctx.IMPLEMENTS_DIRECTIVE().textRange, "Implements interface", listOf("keyword")))

        project.getOrCreateClass(ctx.referenceType().text)?.also {
            smaliClass.interfaces.add(it)
        }

        return visitChildren(ctx)
    }

    override fun visitEnumType(ctx: SmaliParser.EnumTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitEnumDirective(ctx: SmaliParser.EnumDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubannotationDirective(ctx: SmaliParser.SubannotationDirectiveContext): SmaliClass {
        if (withRanges) {
            smaliClass.ranges.add(RangeStatusBase(ctx.SUBANNOTATION_DIRECTIVE().textRange, "Subannotation", listOf("keyword")))
            smaliClass.ranges.add(RangeStatusBase(ctx.SUBANNOTATION_END_DIRECTIVE().textRange, "Subannotation", listOf("keyword")))
        }
        return visitChildren(ctx)
    }

    override fun visitCharLiteral(ctx: SmaliParser.CharLiteralContext?): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAbstarctMethodBodyStatement(ctx: SmaliParser.AbstarctMethodBodyStatementContext?): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFullParamDirective(ctx: SmaliParser.FullParamDirectiveContext?): SmaliClass {
        return visitChildren(ctx)
    }
}
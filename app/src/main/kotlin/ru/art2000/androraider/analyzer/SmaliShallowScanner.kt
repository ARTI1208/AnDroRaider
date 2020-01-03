package ru.art2000.androraider.analyzer

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor
import ru.art2000.androraider.analyzer.antlr.SmaliParser.*
import ru.art2000.androraider.analyzer.antlr.SmaliParserVisitor
import ru.art2000.androraider.analyzer.types.SmaliClass
import ru.art2000.androraider.analyzer.types.SmaliField
import ru.art2000.androraider.analyzer.types.SmaliMethod
import java.lang.reflect.Modifier

// Generated from D:\Coding\Antlr\SmaliParser.g4 by ANTLR 4.1
/**
 * This class provides an empty implementation of [SmaliParserVisitor],
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use [Void] for
 * operations with no return type.
</T> */
class SmaliShallowScanner(var smaliClass: SmaliClass, val analyzer: SmaliAnalyzer, var onlyClass: Boolean = true) :
        AbstractParseTreeVisitor<SmaliClass>(), SmaliParserVisitor<SmaliClass> {

    override fun visitClassDirective(ctx: ClassDirectiveContext): SmaliClass {
        if(!onlyClass)
            return smaliClass

        return visitChildren(ctx)
    }

    override fun visitNegFloatInstruction(ctx: NegFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrLongInstruction(ctx: ShrLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodReturnType(ctx: MethodReturnTypeContext): SmaliClass {

        val grandfather = ctx.parent.parent
        if (grandfather is MethodDeclarationContextWrapper) {
            grandfather.smaliMethod.returnType = analyzer.getOrCreateClass(ctx.text)
        }

        return visitChildren(ctx)
    }

    inner class FieldDeclarationContextWrapper(ctx: FieldDirectiveContext) :
            FieldDirectiveContext(ctx.getParent(), ctx.invokingState) {

        val smaliField = SmaliField()

        init {
            children = ctx.children
            children.forEach {
                it.setParent(this)
            }
            smaliField.parentClass = smaliClass
        }

    }

    override fun visitFieldDirective(ctx: FieldDirectiveContext): SmaliClass {
        if (onlyClass)
            return smaliClass

        return visitChildren(FieldDeclarationContextWrapper(ctx))
    }

    override fun visitNewArrayInstruction(ctx: NewArrayInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetInstruction(ctx: AgetInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodDirective(ctx: MethodDirectiveContext): SmaliClass {
        if (onlyClass)
            return smaliClass

        return visitChildren(ctx)
    }

    override fun visitThrowInstruction(ctx: ThrowInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputBooleanInstruction(ctx: IputBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToDoubleInstruction(ctx: IntToDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveResultInstruction(ctx: MoveResultInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchDirective(ctx: PackedSwitchDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetBooleanInstruction(ctx: IgetBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetCharInstruction(ctx: IgetCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitGotoInstruction(ctx: GotoInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemFloat2addrInstruction(ctx: RemFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBinaryInstruction(ctx: BinaryInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRsubIntInstruction(ctx: RsubIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationDirective(ctx: AnnotationDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConst16Instruction(ctx: Const16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNumericLiteral(ctx: NumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCharType(ctx: CharTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationField(ctx: AnnotationFieldContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayType(ctx: ArrayTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputByteInstruction(ctx: AputByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSuperDirective(ctx: SuperDirectiveContext): SmaliClass {
        if(!onlyClass)
            return smaliClass

        return visitChildren(ctx)
    }

    override fun visitSubDoubleInstruction(ctx: SubDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDoubleToFloatInstruction(ctx: DoubleToFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndLongInstruction(ctx: AndLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShlLongInstruction(ctx: ShlLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchGotoLabel(ctx: CatchGotoLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReturnWideInstruction(ctx: ReturnWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFloatToIntInstruction(ctx: FloatToIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutByteInstruction(ctx: SPutByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfEqzInstruction(ctx: IfEqzInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveResultObjectInstruction(ctx: MoveResultObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfLtzInstruction(ctx: IfLtzInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivIntLit16Instruction(ctx: DivIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddIntLit16Instruction(ctx: AddIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegisterRange(ctx: RegisterRangeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldType(ctx: FieldTypeContext): SmaliClass {
        val grandfather = ctx.parent.parent
        if (grandfather is FieldDeclarationContextWrapper) {
            grandfather.smaliField.type = analyzer.getOrCreateClass(ctx.text)
        }

        return visitChildren(ctx)
    }

    fun parseCompound(string: String): List<String> {
        val list = mutableListOf<String>()
        var tmpString = string
        while (tmpString.isNotEmpty()) {
            if (tmpString.startsWith('L')) {
                val i = tmpString.indexOf(';')

                list.add(tmpString.substring(0, i + 1))
                tmpString = tmpString.substring(i + 1)
            } else if (tmpString.startsWith('[')) {
                var i = 0
                f@ for (c in tmpString) {
                    when (c) {
                        'Z', 'B', 'C', 'D', 'F', 'I', 'J', 'S' -> {
                            break@f
                        }
                        'L' -> {
                            i = tmpString.indexOf(';')
                            break@f
                        }
                        else -> i++
                    }
                }
                list.add(tmpString.substring(0, i + 1))
                tmpString = tmpString.substring(i + 1)
            } else {
                list.add(tmpString[0].toString())
                tmpString = tmpString.substring(1)
            }

        }

        return list
    }

    override fun visitMethodParameterType(ctx: MethodParameterTypeContext): SmaliClass {

        val ancestor = ctx.parent.parent.parent

        if (ancestor is MethodDeclarationContextWrapper) {
            ancestor.smaliMethod.parametersInternal.addAll(parseCompound(ctx.text).also {
//                if (it.size > 1) {
//                    println("WWWWWWWWWWWWk")
//                    it.forEach { println(it) }
//                }
            }.map { analyzer.getOrCreateClass(it) })
        }

        return smaliClass
    }

    override fun visitCheckCastType(ctx: CheckCastTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSourceRegister(ctx: SourceRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReferenceType(ctx: ReferenceTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeStaticInstruction(ctx: InvokeStaticInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstanceOfInstruction(ctx: InstanceOfInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputInstruction(ctx: AputInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetWideInstruction(ctx: IgetWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFloatNumericLiteral(ctx: FloatNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetObjectInstruction(ctx: IgetObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorIntInstruction(ctx: XorIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLabelName(ctx: LabelNameContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegisterIdentifier(ctx: RegisterIdentifierContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFloatToLongInstruction(ctx: FloatToLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationFieldValue(ctx: AnnotationFieldValueContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulLong2addrInstruction(ctx: MulLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayElementRegisters(ctx: ArrayElementRegistersContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveWide16Instruction(ctx: MoveWide16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemIntLit8Instruction(ctx: RemIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutWideInstruction(ctx: SPutWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetObjectInstruction(ctx: AgetObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddDoubleInstruction(ctx: AddDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodIdentifier(ctx: MethodIdentifierContext): SmaliClass {
        val grandfather = ctx.parent.parent
        if (grandfather is MethodDeclarationContextWrapper) {
            grandfather.smaliMethod.name = ctx.text
        }

        return visitChildren(ctx)
    }

    override fun visitPositiveNumericLiteral(ctx: PositiveNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToCharInstruction(ctx: IntToCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstWideHigh16Instruction(ctx: ConstWideHigh16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrLong2addrInstruction(ctx: UshrLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeSuperInstruction(ctx: InvokeSuperInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputWideInstruction(ctx: AputWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeCustomInstruction(ctx: InvokeCustomInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSuperName(ctx: SuperNameContext): SmaliClass {

        smaliClass.parentClass = analyzer.getOrCreateClass(ctx.text)

        return visitChildren(ctx)
    }

    override fun visitAddLongInstruction(ctx: AddLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnyType(ctx: AnyTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulIntLit16Instruction(ctx: MulIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitExtendedParamDirective(ctx: ExtendedParamDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFillArrayDataInstruction(ctx: FillArrayDataInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalsDirective(ctx: LocalsDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstHigh16Instruction(ctx: ConstHigh16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReferenceOrArrayType(ctx: ReferenceOrArrayTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchDirective(ctx: SparseSwitchDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrIntInstruction(ctx: ShrIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToShortInstruction(ctx: IntToShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGezInstruction(ctx: IfGezInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubLong2addrInstruction(ctx: SubLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubFloatInstruction(ctx: SubFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeConstMethodHandleInstruction(ctx: InvokeConstMethodHandleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrLong2addrInstruction(ctx: OrLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivFloatInstruction(ctx: DivFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchDirective(ctx: CatchDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetShortInstruction(ctx: IgetShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitVoidType(ctx: VoidTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBooleanLiteral(ctx: BooleanLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    // TODO implement synthetic, bridge, synchronized, strictfp, varargs
    override fun visitMethodModifier(ctx: MethodModifierContext): SmaliClass {
        val parent = ctx.getParent()
        if (parent is MethodDeclarationContextWrapper) {
            when (ctx.text) {
                "public" -> parent.smaliMethod.setModifierBit(Modifier.PUBLIC)
                "protected" -> parent.smaliMethod.setModifierBit(Modifier.PROTECTED)
                "private" -> parent.smaliMethod.setModifierBit(Modifier.PRIVATE)
                "final" -> parent.smaliMethod.setModifierBit(Modifier.FINAL)
                "static" -> parent.smaliMethod.setModifierBit(Modifier.STATIC)
                "abstract" -> parent.smaliMethod.setModifierBit(Modifier.ABSTRACT)
                "native" -> parent.smaliMethod.setModifierBit(Modifier.NATIVE)
                "constructor" -> parent.smaliMethod.setModifierBit(SmaliMethod.Modifier.CONSTRUCTOR)
            }
        }
        return visitChildren(ctx)
    }

    override fun visitInvokeDirectRangeInstruction(ctx: InvokeDirectRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGeInstruction(ctx: IfGeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeSuperRangeInstruction(ctx: InvokeSuperRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveFrom16Instruction(ctx: MoveFrom16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitTargetRegister(ctx: TargetRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitTernaryInstruction(ctx: TernaryInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorIntLit16Instruction(ctx: XorIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNewInstanceType(ctx: NewInstanceTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivIntInstruction(ctx: DivIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemDouble2addrInstruction(ctx: RemDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchFromLabel(ctx: CatchFromLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegisterListRegisters(ctx: RegisterListRegistersContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLongToFloatInstruction(ctx: LongToFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetByteInstruction(ctx: IgetByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIndexRegister(ctx: IndexRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSimpleParamDirective(ctx: SimpleParamDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndLong2addrInstruction(ctx: AndLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeDirectInstruction(ctx: InvokeDirectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayDataEntry(ctx: ArrayDataEntryContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLineLabel(ctx: LineLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstInstruction(ctx: ConstInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFilledArrayDataLabel(ctx: FilledArrayDataLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDoubleType(ctx: DoubleTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemIntLit16Instruction(ctx: RemIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchDirectiveLabels(ctx: PackedSwitchDirectiveLabelsContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodInvocationTarget(ctx: MethodInvocationTargetContext): SmaliClass {
        return visitChildren(ctx)
    }

    inner class MethodDeclarationContextWrapper(ctx: MethodDeclarationContext) :
            MethodDeclarationContext(ctx.getParent(), ctx.invokingState) {

        val smaliMethod = SmaliMethod()

        init {
            children = ctx.children
            children.forEach {
                it.setParent(this)
            }
            smaliMethod.parentClass = smaliClass
        }

    }

    override fun visitMethodDeclaration(ctx: MethodDeclarationContext): SmaliClass {
        return visitChildren(MethodDeclarationContextWrapper(ctx))
    }

    override fun visitMethodBody(ctx: MethodBodyContext): SmaliClass {
        return smaliClass
    }

    override fun visitIputObjectInstruction(ctx: IputObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmplDoubleInstruction(ctx: CmplDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShortType(ctx: ShortTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivInt2addrInstruction(ctx: DivInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFilledNewArrayInstruction(ctx: FilledNewArrayInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivDouble2addrInstruction(ctx: DivDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitByteType(ctx: ByteTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetInstruction(ctx: IgetInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchRegister(ctx: PackedSwitchRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeStaticRangeInstruction(ctx: InvokeStaticRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLineDirective(ctx: LineDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodParameterLiteral(ctx: MethodParameterLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGtInstruction(ctx: IfGtInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrIntLit8Instruction(ctx: OrIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayElementType(ctx: ArrayElementTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToByteInstruction(ctx: IntToByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitStatement(ctx: StatementContext): SmaliClass {
        return visitChildren(ctx)
    }

    // TODO implement synthetic, transient, volatile, enum
    override fun visitFieldModifier(ctx: FieldModifierContext): SmaliClass {
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

        return visitChildren(ctx)
    }

    override fun visitMove16Instruction(ctx: Move16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivIntLit8Instruction(ctx: DivIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorLong2addrInstruction(ctx: XorLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetShortInstruction(ctx: AgetShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndIntLit8Instruction(ctx: AndIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmpgFloatInstruction(ctx: CmpgFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveWideFrom16Instruction(ctx: MoveWideFrom16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubInt2addrInstruction(ctx: SubInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrInt2addrInstruction(ctx: OrInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLabel(ctx: LabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitParse(ctx: ParseContext): SmaliClass {
//        ctx.statement()
        return visitChildren(ctx)
    }

    override fun visitAddDouble2addrInstruction(ctx: AddDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalDirective(ctx: LocalDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocaDirectiveVariableName(ctx: LocaDirectiveVariableNameContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchLabel(ctx: PackedSwitchLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchDirectiveValue(ctx: SparseSwitchDirectiveValueContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputShortInstruction(ctx: AputShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetBooleanInstruction(ctx: SGetBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfNezInstruction(ctx: IfNezInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulIntInstruction(ctx: MulIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputShortInstruction(ctx: IputShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSourceName(ctx: SourceNameContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddIntLit8Instruction(ctx: AddIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulDoubleInstruction(ctx: MulDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokePolymorphicInstruction(ctx: InvokePolymorphicInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchDirectiveLabel(ctx: PackedSwitchDirectiveLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNonVoidType(ctx: NonVoidTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNopInstruction(ctx: NopInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchAllDirective(ctx: CatchAllDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetInstruction(ctx: SGetInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfLabel(ctx: IfLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetWideInstruction(ctx: SGetWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRightRegister(ctx: RightRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodSignature(ctx: MethodSignatureContext): SmaliClass {


        return visitChildren(ctx)
    }

    override fun visitNegLongInstruction(ctx: NegLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveObjectFrom16Instruction(ctx: MoveObjectFrom16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodArguments(ctx: MethodArgumentsContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubIntInstruction(ctx: SubIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchToLabel(ctx: CatchToLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrLongInstruction(ctx: OrLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIdentifier(ctx: IdentifierContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNegativeNumericLiteral(ctx: NegativeNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBooleanType(ctx: BooleanTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulIntLit8Instruction(ctx: MulIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddInt2addrInstruction(ctx: AddInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrInt2addrInstruction(ctx: ShrInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetWideInstruction(ctx: AgetWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationType(ctx: AnnotationTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConst4Instruction(ctx: Const4InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputByteInstruction(ctx: IputByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemDoubleInstruction(ctx: RemDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstString(ctx: ConstStringContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetCharInstruction(ctx: SGetCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrInt2addrInstruction(ctx: UshrInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstStringJumbo(ctx: ConstStringJumboContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitField(ctx: FieldContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitStringLiteral(ctx: StringLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReturnInstruction(ctx: ReturnInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeVirtualInstruction(ctx: InvokeVirtualInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShlLong2addrInstruction(ctx: ShlLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShlIntInstruction(ctx: ShlIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrIntInstruction(ctx: UshrIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetObjectInstruction(ctx: SGetObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFilledNewArrayRangeInstruction(ctx: FilledNewArrayRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetShortInstruction(ctx: SGetShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndIntLit16Instruction(ctx: AndIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorIntLit8Instruction(ctx: XorIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSourceDirective(ctx: SourceDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrIntLit16Instruction(ctx: OrIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun defaultResult(): SmaliClass {
        return smaliClass
    }

    // TODO implement synthetic, annotation, enum
    override fun visitClassModifier(ctx: ClassModifierContext): SmaliClass {
        when (ctx.text) {
            "public" -> smaliClass.setModifierBit(Modifier.PUBLIC)
            "protected" -> smaliClass.setModifierBit(Modifier.PROTECTED)
            "private" -> smaliClass.setModifierBit(Modifier.PRIVATE)
            "final" -> smaliClass.setModifierBit(Modifier.FINAL)
            "static" -> smaliClass.setModifierBit(Modifier.STATIC)
            "abstract" -> smaliClass.setModifierBit(Modifier.ABSTRACT)
            "interface" -> smaliClass.setModifierBit(Modifier.INTERFACE)
        }

        return visitChildren(ctx)
    }

    override fun visitLocalEndDirective(ctx: LocalEndDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalDirectiveGenericHint(ctx: LocalDirectiveGenericHintContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrIntLit8Instruction(ctx: ShrIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutShortInstruction(ctx: SPutShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputBooleanInstruction(ctx: AputBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeInterfaceInstruction(ctx: InvokeInterfaceInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmpLongInstruction(ctx: CmpLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitClassName(ctx: ClassNameContext): SmaliClass {
        smaliClass = analyzer.getOrCreateClass(ctx.text)
        return smaliClass
    }

    override fun visitAgetCharInstruction(ctx: AgetCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetBooleanInstruction(ctx: AgetBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchRegister(ctx: SparseSwitchRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveResultWideInstruction(ctx: MoveResultWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRsubIntLit8Instruction(ctx: RsubIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDecimalNumericLiteral(ctx: DecimalNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemIntInstruction(ctx: RemIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstanceField(ctx: InstanceFieldContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegistersDirective(ctx: RegistersDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetByteInstruction(ctx: SGetByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToFloatInstruction(ctx: IntToFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddFloatInstruction(ctx: AddFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddIntInstruction(ctx: AddIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmplFloatInstruction(ctx: CmplFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfEqInstruction(ctx: IfEqInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputInstruction(ctx: IputInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstWideInstruction(ctx: ConstWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntType(ctx: IntTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayRegister(ctx: ArrayRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokePolymorphicRangeInstruction(ctx: InvokePolymorphicRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputCharInstruction(ctx: IputCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitHexFloatLiteral(ctx: HexFloatLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubLongInstruction(ctx: SubLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIntToLongInstruction(ctx: IntToLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulFloatInstruction(ctx: MulFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitGoto32Instruction(ctx: Goto32InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubDouble2addrInstruction(ctx: SubDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitHexNumericLiteral(ctx: HexNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveObjectInstruction(ctx: MoveObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputWideInstruction(ctx: IputWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNonArrayType(ctx: NonArrayTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstruction(ctx: InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivFloat2addrInstruction(ctx: DivFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCheckInstanceType(ctx: CheckInstanceTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemLong2addrInstruction(ctx: RemLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrIntLit8Instruction(ctx: UshrIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayElementRegisterRange(ctx: ArrayElementRegisterRangeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveWideInstruction(ctx: MoveWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFloatToDoubleInstruction(ctx: FloatToDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfLeInstruction(ctx: IfLeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddLong2addrInstruction(ctx: AddLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrIntInstruction(ctx: OrIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReturnVoidInstruction(ctx: ReturnVoidInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmpgDoubleInstruction(ctx: CmpgDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputObjectInstruction(ctx: AputObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMonitorEnterInstruction(ctx: MonitorEnterInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivLong2addrInstruction(ctx: DivLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveInstruction(ctx: MoveInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShlInt2addrInstruction(ctx: ShlInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitParamDirective(ctx: ParamDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulFloat2addrInstruction(ctx: MulFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeInterfaceRangeInstruction(ctx: InvokeInterfaceRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLongToIntInstruction(ctx: LongToIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNegDoubleInstruction(ctx: NegDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLongType(ctx: LongTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayDataDirective(ctx: ArrayDataDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBinaryNumericLiteral(ctx: BinaryNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFloatType(ctx: FloatTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLongToDoubleInstruction(ctx: LongToDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodBodyStatement(ctx: MethodBodyStatementContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstanceRegister(ctx: InstanceRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutCharInstruction(ctx: SPutCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemInt2addrInstruction(ctx: RemInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulDouble2addrInstruction(ctx: MulDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeCustomRangeInstruction(ctx: InvokeCustomRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCheckCastInstruction(ctx: CheckCastInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeConstMethodTypeInstruction(ctx: InvokeConstMethodTypeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayLengthInstruction(ctx: ArrayLengthInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArraySizeRegister(ctx: ArraySizeRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitReturnObjectInstruction(ctx: ReturnObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemFloatInstruction(ctx: RemFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputCharInstruction(ctx: AputCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstWide32Instruction(ctx: ConstWide32InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalDirectiveType(ctx: LocalDirectiveTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGtzInstruction(ctx: IfGtzInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNewInstanceInstruction(ctx: NewInstanceInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveObject16Instruction(ctx: MoveObject16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulInt2addrInstruction(ctx: MulInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationScope(ctx: AnnotationScopeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitGoto16Instruction(ctx: Goto16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDoubleToLongInstruction(ctx: DoubleToLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAssignableValue(ctx: AssignableValueContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInvokeVirtualRangeInstruction(ctx: InvokeVirtualRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorInt2addrInstruction(ctx: XorInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstClass(ctx: ConstClassContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldName(ctx: FieldNameContext): SmaliClass {
        val ancestor = ctx.parent.parent
        if (ancestor is FieldDeclarationContextWrapper) {
            ancestor.smaliField.name = ctx.text
        }

        return visitChildren(ctx)
    }

    override fun visitNotIntInstruction(ctx: NotIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMonitorExitInstruction(ctx: MonitorExitInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivDoubleInstruction(ctx: DivDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndInt2addrInstruction(ctx: AndInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetByteInstruction(ctx: AgetByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShlIntLit8Instruction(ctx: ShlIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulLongInstruction(ctx: MulLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegisterList(ctx: RegisterListContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldNameAndType(ctx: FieldNameAndTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchInstruction(ctx: PackedSwitchInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchIdent(ctx: PackedSwitchIdentContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDoubleToIntInstruction(ctx: DoubleToIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrLong2addrInstruction(ctx: ShrLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchLabel(ctx: SparseSwitchLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldInvocationTarget(ctx: FieldInvocationTargetContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalRestartDirective(ctx: LocalRestartDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitPrimitiveType(ctx: PrimitiveTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSubFloat2addrInstruction(ctx: SubFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOctNumericLiteral(ctx: OctNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveExceptionInstruction(ctx: MoveExceptionInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLeftRegister(ctx: LeftRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorLongInstruction(ctx: XorLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfLtInstruction(ctx: IfLtInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutInstruction(ctx: SPutInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNegIntInstruction(ctx: NegIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCatchExceptionType(ctx: CatchExceptionTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemLongInstruction(ctx: RemLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationValueScoped(ctx: AnnotationValueScopedContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLocalDirectiveRegister(ctx: LocalDirectiveRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNotLongInstruction(ctx: NotLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndIntInstruction(ctx: AndIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivLongInstruction(ctx: DivLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchInstruction(ctx: SparseSwitchInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfNeInstruction(ctx: IfNeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAddFloat2addrInstruction(ctx: AddFloat2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutBooleanInstruction(ctx: SPutBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstWide16Instruction(ctx: ConstWide16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutObjectInstruction(ctx: SPutObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfLezInstruction(ctx: IfLezInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrLongInstruction(ctx: UshrLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNullLiteral(ctx: NullLiteralContext): SmaliClass {
        return smaliClass
    }

}
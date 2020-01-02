package ru.art2000.androraider.analyzer

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode
import ru.art2000.androraider.analyzer.antlr.SmaliParser
import ru.art2000.androraider.analyzer.antlr.SmaliParser.*
import ru.art2000.androraider.analyzer.antlr.SmaliParserListener
import ru.art2000.androraider.analyzer.types.SmaliClass
import java.io.File
import java.lang.reflect.Modifier

// Generated from D:\Coding\Antlr\SmaliParser.g4 by ANTLR 4.1
/**
 * This class provides an empty implementation of [SmaliParserListener],
 * which can be extended to create a listener which only needs to handle a subset
 * of the available methods.
 */
class SmaliParserBaseListener(private val analyzer: SmaliAnalyzer) : SmaliParserListener {
    var parsedSmaliClass = SmaliClass()
    fun clear() {
        parsedSmaliClass = SmaliClass()
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNegFloatInstruction(ctx: NegFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNegFloatInstruction(ctx: NegFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShrLongInstruction(ctx: ShrLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShrLongInstruction(ctx: ShrLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodReturnType(ctx: MethodReturnTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodReturnType(ctx: MethodReturnTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFieldDirective(ctx: FieldDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFieldDirective(ctx: FieldDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNewArrayInstruction(ctx: NewArrayInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNewArrayInstruction(ctx: NewArrayInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAgetInstruction(ctx: AgetInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAgetInstruction(ctx: AgetInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodDirective(ctx: MethodDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodDirective(ctx: MethodDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterThrowInstruction(ctx: ThrowInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitThrowInstruction(ctx: ThrowInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIputBooleanInstruction(ctx: IputBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIputBooleanInstruction(ctx: IputBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIntToDoubleInstruction(ctx: IntToDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIntToDoubleInstruction(ctx: IntToDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveResultInstruction(ctx: MoveResultInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveResultInstruction(ctx: MoveResultInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPackedSwitchDirective(ctx: PackedSwitchDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPackedSwitchDirective(ctx: PackedSwitchDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIgetBooleanInstruction(ctx: IgetBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIgetBooleanInstruction(ctx: IgetBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIgetCharInstruction(ctx: IgetCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIgetCharInstruction(ctx: IgetCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterGotoInstruction(ctx: GotoInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitGotoInstruction(ctx: GotoInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemFloat2addrInstruction(ctx: RemFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemFloat2addrInstruction(ctx: RemFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterBinaryInstruction(ctx: BinaryInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitBinaryInstruction(ctx: BinaryInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRsubIntInstruction(ctx: RsubIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRsubIntInstruction(ctx: RsubIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAnnotationDirective(ctx: AnnotationDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAnnotationDirective(ctx: AnnotationDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConst16Instruction(ctx: Const16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConst16Instruction(ctx: Const16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNumericLiteral(ctx: NumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNumericLiteral(ctx: NumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCharType(ctx: CharTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCharType(ctx: CharTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAnnotationField(ctx: AnnotationFieldContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAnnotationField(ctx: AnnotationFieldContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArrayType(ctx: ArrayTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArrayType(ctx: ArrayTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAputByteInstruction(ctx: AputByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAputByteInstruction(ctx: AputByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSuperDirective(ctx: SuperDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSuperDirective(ctx: SuperDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSubDoubleInstruction(ctx: SubDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSubDoubleInstruction(ctx: SubDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDoubleToFloatInstruction(ctx: DoubleToFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDoubleToFloatInstruction(ctx: DoubleToFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAndLongInstruction(ctx: AndLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAndLongInstruction(ctx: AndLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShlLongInstruction(ctx: ShlLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShlLongInstruction(ctx: ShlLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCatchGotoLabel(ctx: CatchGotoLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCatchGotoLabel(ctx: CatchGotoLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterReturnWideInstruction(ctx: ReturnWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitReturnWideInstruction(ctx: ReturnWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFloatToIntInstruction(ctx: FloatToIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFloatToIntInstruction(ctx: FloatToIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSPutByteInstruction(ctx: SPutByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSPutByteInstruction(ctx: SPutByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfEqzInstruction(ctx: IfEqzInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfEqzInstruction(ctx: IfEqzInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveResultObjectInstruction(ctx: MoveResultObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveResultObjectInstruction(ctx: MoveResultObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfLtzInstruction(ctx: IfLtzInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfLtzInstruction(ctx: IfLtzInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivIntLit16Instruction(ctx: DivIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivIntLit16Instruction(ctx: DivIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddIntLit16Instruction(ctx: AddIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddIntLit16Instruction(ctx: AddIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRegisterRange(ctx: RegisterRangeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRegisterRange(ctx: RegisterRangeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFieldType(ctx: FieldTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFieldType(ctx: FieldTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodParameterType(ctx: MethodParameterTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodParameterType(ctx: MethodParameterTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCheckCastType(ctx: CheckCastTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCheckCastType(ctx: CheckCastTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSourceRegister(ctx: SourceRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSourceRegister(ctx: SourceRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterReferenceType(ctx: ReferenceTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitReferenceType(ctx: ReferenceTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeStaticInstruction(ctx: InvokeStaticInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeStaticInstruction(ctx: InvokeStaticInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInstanceOfInstruction(ctx: InstanceOfInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInstanceOfInstruction(ctx: InstanceOfInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAputInstruction(ctx: AputInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAputInstruction(ctx: AputInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIgetWideInstruction(ctx: IgetWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIgetWideInstruction(ctx: IgetWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFloatNumericLiteral(ctx: FloatNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFloatNumericLiteral(ctx: FloatNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIgetObjectInstruction(ctx: IgetObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIgetObjectInstruction(ctx: IgetObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterXorIntInstruction(ctx: XorIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitXorIntInstruction(ctx: XorIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLabelName(ctx: LabelNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLabelName(ctx: LabelNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRegisterIdentifier(ctx: RegisterIdentifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRegisterIdentifier(ctx: RegisterIdentifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFloatToLongInstruction(ctx: FloatToLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFloatToLongInstruction(ctx: FloatToLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAnnotationFieldValue(ctx: AnnotationFieldValueContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAnnotationFieldValue(ctx: AnnotationFieldValueContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulLong2addrInstruction(ctx: MulLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulLong2addrInstruction(ctx: MulLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArrayElementRegisters(ctx: ArrayElementRegistersContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArrayElementRegisters(ctx: ArrayElementRegistersContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveWide16Instruction(ctx: MoveWide16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveWide16Instruction(ctx: MoveWide16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemIntLit8Instruction(ctx: RemIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemIntLit8Instruction(ctx: RemIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSPutWideInstruction(ctx: SPutWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSPutWideInstruction(ctx: SPutWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAgetObjectInstruction(ctx: AgetObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAgetObjectInstruction(ctx: AgetObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddDoubleInstruction(ctx: AddDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddDoubleInstruction(ctx: AddDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodIdentifier(ctx: MethodIdentifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodIdentifier(ctx: MethodIdentifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPositiveNumericLiteral(ctx: PositiveNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPositiveNumericLiteral(ctx: PositiveNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIntToCharInstruction(ctx: IntToCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIntToCharInstruction(ctx: IntToCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstWideHigh16Instruction(ctx: ConstWideHigh16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstWideHigh16Instruction(ctx: ConstWideHigh16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterUshrLong2addrInstruction(ctx: UshrLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitUshrLong2addrInstruction(ctx: UshrLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeSuperInstruction(ctx: InvokeSuperInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeSuperInstruction(ctx: InvokeSuperInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAputWideInstruction(ctx: AputWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAputWideInstruction(ctx: AputWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeCustomInstruction(ctx: InvokeCustomInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeCustomInstruction(ctx: InvokeCustomInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSuperName(ctx: SuperNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSuperName(ctx: SuperNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddLongInstruction(ctx: AddLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddLongInstruction(ctx: AddLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAnyType(ctx: AnyTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAnyType(ctx: AnyTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulIntLit16Instruction(ctx: MulIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulIntLit16Instruction(ctx: MulIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterExtendedParamDirective(ctx: ExtendedParamDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitExtendedParamDirective(ctx: ExtendedParamDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFillArrayDataInstruction(ctx: FillArrayDataInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFillArrayDataInstruction(ctx: FillArrayDataInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLocalsDirective(ctx: LocalsDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLocalsDirective(ctx: LocalsDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstHigh16Instruction(ctx: ConstHigh16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstHigh16Instruction(ctx: ConstHigh16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterReferenceOrArrayType(ctx: ReferenceOrArrayTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitReferenceOrArrayType(ctx: ReferenceOrArrayTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSparseSwitchDirective(ctx: SparseSwitchDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSparseSwitchDirective(ctx: SparseSwitchDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShrIntInstruction(ctx: ShrIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShrIntInstruction(ctx: ShrIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIntToShortInstruction(ctx: IntToShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIntToShortInstruction(ctx: IntToShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfGezInstruction(ctx: IfGezInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfGezInstruction(ctx: IfGezInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSubLong2addrInstruction(ctx: SubLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSubLong2addrInstruction(ctx: SubLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSubFloatInstruction(ctx: SubFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSubFloatInstruction(ctx: SubFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeConstMethodHandleInstruction(ctx: InvokeConstMethodHandleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeConstMethodHandleInstruction(ctx: InvokeConstMethodHandleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterOrLong2addrInstruction(ctx: OrLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitOrLong2addrInstruction(ctx: OrLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivFloatInstruction(ctx: DivFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivFloatInstruction(ctx: DivFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCatchDirective(ctx: CatchDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCatchDirective(ctx: CatchDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIgetShortInstruction(ctx: IgetShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIgetShortInstruction(ctx: IgetShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterVoidType(ctx: VoidTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitVoidType(ctx: VoidTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterBooleanLiteral(ctx: BooleanLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitBooleanLiteral(ctx: BooleanLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodModifier(ctx: MethodModifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodModifier(ctx: MethodModifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeDirectRangeInstruction(ctx: InvokeDirectRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeDirectRangeInstruction(ctx: InvokeDirectRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfGeInstruction(ctx: IfGeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfGeInstruction(ctx: IfGeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeSuperRangeInstruction(ctx: InvokeSuperRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeSuperRangeInstruction(ctx: InvokeSuperRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveFrom16Instruction(ctx: MoveFrom16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveFrom16Instruction(ctx: MoveFrom16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterTargetRegister(ctx: TargetRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitTargetRegister(ctx: TargetRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterTernaryInstruction(ctx: TernaryInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitTernaryInstruction(ctx: TernaryInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterXorIntLit16Instruction(ctx: XorIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitXorIntLit16Instruction(ctx: XorIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNewInstanceType(ctx: NewInstanceTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNewInstanceType(ctx: NewInstanceTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivIntInstruction(ctx: DivIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivIntInstruction(ctx: DivIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemDouble2addrInstruction(ctx: RemDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemDouble2addrInstruction(ctx: RemDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCatchFromLabel(ctx: CatchFromLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCatchFromLabel(ctx: CatchFromLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRegisterListRegisters(ctx: RegisterListRegistersContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRegisterListRegisters(ctx: RegisterListRegistersContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLongToFloatInstruction(ctx: LongToFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLongToFloatInstruction(ctx: LongToFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIgetByteInstruction(ctx: IgetByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIgetByteInstruction(ctx: IgetByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIndexRegister(ctx: IndexRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIndexRegister(ctx: IndexRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSimpleParamDirective(ctx: SimpleParamDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSimpleParamDirective(ctx: SimpleParamDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAndLong2addrInstruction(ctx: AndLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAndLong2addrInstruction(ctx: AndLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeDirectInstruction(ctx: InvokeDirectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeDirectInstruction(ctx: InvokeDirectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArrayDataEntry(ctx: ArrayDataEntryContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArrayDataEntry(ctx: ArrayDataEntryContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLineLabel(ctx: LineLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLineLabel(ctx: LineLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstInstruction(ctx: ConstInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstInstruction(ctx: ConstInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFilledArrayDataLabel(ctx: FilledArrayDataLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFilledArrayDataLabel(ctx: FilledArrayDataLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDoubleType(ctx: DoubleTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDoubleType(ctx: DoubleTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemIntLit16Instruction(ctx: RemIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemIntLit16Instruction(ctx: RemIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPackedSwitchDirectiveLabels(ctx: PackedSwitchDirectiveLabelsContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPackedSwitchDirectiveLabels(ctx: PackedSwitchDirectiveLabelsContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodInvocationTarget(ctx: MethodInvocationTargetContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodInvocationTarget(ctx: MethodInvocationTargetContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodDeclaration(ctx: MethodDeclarationContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodDeclaration(ctx: MethodDeclarationContext) {
        println(ctx.text)
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodBody(ctx: MethodBodyContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodBody(ctx: MethodBodyContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIputObjectInstruction(ctx: IputObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIputObjectInstruction(ctx: IputObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCmplDoubleInstruction(ctx: CmplDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCmplDoubleInstruction(ctx: CmplDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShortType(ctx: ShortTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShortType(ctx: ShortTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivInt2addrInstruction(ctx: DivInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivInt2addrInstruction(ctx: DivInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFilledNewArrayInstruction(ctx: FilledNewArrayInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFilledNewArrayInstruction(ctx: FilledNewArrayInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivDouble2addrInstruction(ctx: DivDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivDouble2addrInstruction(ctx: DivDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterByteType(ctx: ByteTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitByteType(ctx: ByteTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIgetInstruction(ctx: IgetInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIgetInstruction(ctx: IgetInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPackedSwitchRegister(ctx: PackedSwitchRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPackedSwitchRegister(ctx: PackedSwitchRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeStaticRangeInstruction(ctx: InvokeStaticRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeStaticRangeInstruction(ctx: InvokeStaticRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLineDirective(ctx: LineDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLineDirective(ctx: LineDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodParameterLiteral(ctx: MethodParameterLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodParameterLiteral(ctx: MethodParameterLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfGtInstruction(ctx: IfGtInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfGtInstruction(ctx: IfGtInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterOrIntLit8Instruction(ctx: OrIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitOrIntLit8Instruction(ctx: OrIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArrayElementType(ctx: ArrayElementTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArrayElementType(ctx: ArrayElementTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIntToByteInstruction(ctx: IntToByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIntToByteInstruction(ctx: IntToByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterStatement(ctx: StatementContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitStatement(ctx: StatementContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFieldModifier(ctx: FieldModifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFieldModifier(ctx: FieldModifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMove16Instruction(ctx: Move16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMove16Instruction(ctx: Move16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivIntLit8Instruction(ctx: DivIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivIntLit8Instruction(ctx: DivIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterXorLong2addrInstruction(ctx: XorLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitXorLong2addrInstruction(ctx: XorLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAgetShortInstruction(ctx: AgetShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAgetShortInstruction(ctx: AgetShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAndIntLit8Instruction(ctx: AndIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAndIntLit8Instruction(ctx: AndIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCmpgFloatInstruction(ctx: CmpgFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCmpgFloatInstruction(ctx: CmpgFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNullLiteral(ctx: NullLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNullLiteral(ctx: NullLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveWideFrom16Instruction(ctx: MoveWideFrom16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveWideFrom16Instruction(ctx: MoveWideFrom16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSubInt2addrInstruction(ctx: SubInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSubInt2addrInstruction(ctx: SubInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterOrInt2addrInstruction(ctx: OrInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitOrInt2addrInstruction(ctx: OrInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLabel(ctx: LabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLabel(ctx: LabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterParse(ctx: ParseContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitParse(ctx: ParseContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddDouble2addrInstruction(ctx: AddDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddDouble2addrInstruction(ctx: AddDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLocalDirective(ctx: LocalDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLocalDirective(ctx: LocalDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLocaDirectiveVariableName(ctx: LocaDirectiveVariableNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLocaDirectiveVariableName(ctx: LocaDirectiveVariableNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPackedSwitchLabel(ctx: PackedSwitchLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPackedSwitchLabel(ctx: PackedSwitchLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSparseSwitchDirectiveValue(ctx: SparseSwitchDirectiveValueContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSparseSwitchDirectiveValue(ctx: SparseSwitchDirectiveValueContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAputShortInstruction(ctx: AputShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAputShortInstruction(ctx: AputShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSGetBooleanInstruction(ctx: SGetBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSGetBooleanInstruction(ctx: SGetBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfNezInstruction(ctx: IfNezInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfNezInstruction(ctx: IfNezInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulIntInstruction(ctx: MulIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulIntInstruction(ctx: MulIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIputShortInstruction(ctx: IputShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIputShortInstruction(ctx: IputShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSourceName(ctx: SourceNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSourceName(ctx: SourceNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddIntLit8Instruction(ctx: AddIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddIntLit8Instruction(ctx: AddIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulDoubleInstruction(ctx: MulDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulDoubleInstruction(ctx: MulDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokePolymorphicInstruction(ctx: InvokePolymorphicInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokePolymorphicInstruction(ctx: InvokePolymorphicInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPackedSwitchDirectiveLabel(ctx: PackedSwitchDirectiveLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPackedSwitchDirectiveLabel(ctx: PackedSwitchDirectiveLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNonVoidType(ctx: NonVoidTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNonVoidType(ctx: NonVoidTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNopInstruction(ctx: NopInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNopInstruction(ctx: NopInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCatchAllDirective(ctx: CatchAllDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCatchAllDirective(ctx: CatchAllDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSGetInstruction(ctx: SGetInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSGetInstruction(ctx: SGetInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfLabel(ctx: IfLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfLabel(ctx: IfLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSGetWideInstruction(ctx: SGetWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSGetWideInstruction(ctx: SGetWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRightRegister(ctx: RightRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRightRegister(ctx: RightRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodSignature(ctx: MethodSignatureContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodSignature(ctx: MethodSignatureContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNegLongInstruction(ctx: NegLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNegLongInstruction(ctx: NegLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveObjectFrom16Instruction(ctx: MoveObjectFrom16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveObjectFrom16Instruction(ctx: MoveObjectFrom16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodArguments(ctx: MethodArgumentsContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodArguments(ctx: MethodArgumentsContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSubIntInstruction(ctx: SubIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSubIntInstruction(ctx: SubIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCatchToLabel(ctx: CatchToLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCatchToLabel(ctx: CatchToLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterOrLongInstruction(ctx: OrLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitOrLongInstruction(ctx: OrLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIdentifier(ctx: IdentifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIdentifier(ctx: IdentifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNegativeNumericLiteral(ctx: NegativeNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNegativeNumericLiteral(ctx: NegativeNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterBooleanType(ctx: BooleanTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitBooleanType(ctx: BooleanTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulIntLit8Instruction(ctx: MulIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulIntLit8Instruction(ctx: MulIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddInt2addrInstruction(ctx: AddInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddInt2addrInstruction(ctx: AddInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShrInt2addrInstruction(ctx: ShrInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShrInt2addrInstruction(ctx: ShrInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAgetWideInstruction(ctx: AgetWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAgetWideInstruction(ctx: AgetWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAnnotationType(ctx: AnnotationTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAnnotationType(ctx: AnnotationTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConst4Instruction(ctx: Const4InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConst4Instruction(ctx: Const4InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIputByteInstruction(ctx: IputByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIputByteInstruction(ctx: IputByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemDoubleInstruction(ctx: RemDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemDoubleInstruction(ctx: RemDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstString(ctx: ConstStringContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstString(ctx: ConstStringContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSGetCharInstruction(ctx: SGetCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSGetCharInstruction(ctx: SGetCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterUshrInt2addrInstruction(ctx: UshrInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitUshrInt2addrInstruction(ctx: UshrInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstStringJumbo(ctx: ConstStringJumboContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstStringJumbo(ctx: ConstStringJumboContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterField(ctx: FieldContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitField(ctx: FieldContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterStringLiteral(ctx: StringLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitStringLiteral(ctx: StringLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterReturnInstruction(ctx: ReturnInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitReturnInstruction(ctx: ReturnInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeVirtualInstruction(ctx: InvokeVirtualInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeVirtualInstruction(ctx: InvokeVirtualInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShlLong2addrInstruction(ctx: ShlLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShlLong2addrInstruction(ctx: ShlLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShlIntInstruction(ctx: ShlIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShlIntInstruction(ctx: ShlIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterUshrIntInstruction(ctx: UshrIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitUshrIntInstruction(ctx: UshrIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSGetObjectInstruction(ctx: SGetObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSGetObjectInstruction(ctx: SGetObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFilledNewArrayRangeInstruction(ctx: FilledNewArrayRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFilledNewArrayRangeInstruction(ctx: FilledNewArrayRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSGetShortInstruction(ctx: SGetShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSGetShortInstruction(ctx: SGetShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAndIntLit16Instruction(ctx: AndIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAndIntLit16Instruction(ctx: AndIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterXorIntLit8Instruction(ctx: XorIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitXorIntLit8Instruction(ctx: XorIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSourceDirective(ctx: SourceDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSourceDirective(ctx: SourceDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterOrIntLit16Instruction(ctx: OrIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitOrIntLit16Instruction(ctx: OrIntLit16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterClassModifier(ctx: ClassModifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitClassModifier(ctx: ClassModifierContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLocalEndDirective(ctx: LocalEndDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLocalEndDirective(ctx: LocalEndDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLocalDirectiveGenericHint(ctx: LocalDirectiveGenericHintContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLocalDirectiveGenericHint(ctx: LocalDirectiveGenericHintContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShrIntLit8Instruction(ctx: ShrIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShrIntLit8Instruction(ctx: ShrIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSPutShortInstruction(ctx: SPutShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSPutShortInstruction(ctx: SPutShortInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAputBooleanInstruction(ctx: AputBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAputBooleanInstruction(ctx: AputBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeInterfaceInstruction(ctx: InvokeInterfaceInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeInterfaceInstruction(ctx: InvokeInterfaceInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCmpLongInstruction(ctx: CmpLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCmpLongInstruction(ctx: CmpLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterClassName(ctx: ClassNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitClassName(ctx: ClassNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAgetCharInstruction(ctx: AgetCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAgetCharInstruction(ctx: AgetCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAgetBooleanInstruction(ctx: AgetBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAgetBooleanInstruction(ctx: AgetBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSparseSwitchRegister(ctx: SparseSwitchRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSparseSwitchRegister(ctx: SparseSwitchRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveResultWideInstruction(ctx: MoveResultWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveResultWideInstruction(ctx: MoveResultWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRsubIntLit8Instruction(ctx: RsubIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRsubIntLit8Instruction(ctx: RsubIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDecimalNumericLiteral(ctx: DecimalNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDecimalNumericLiteral(ctx: DecimalNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemIntInstruction(ctx: RemIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemIntInstruction(ctx: RemIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInstanceField(ctx: InstanceFieldContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInstanceField(ctx: InstanceFieldContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRegistersDirective(ctx: RegistersDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRegistersDirective(ctx: RegistersDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSGetByteInstruction(ctx: SGetByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSGetByteInstruction(ctx: SGetByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIntToFloatInstruction(ctx: IntToFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIntToFloatInstruction(ctx: IntToFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddFloatInstruction(ctx: AddFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddFloatInstruction(ctx: AddFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddIntInstruction(ctx: AddIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddIntInstruction(ctx: AddIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCmplFloatInstruction(ctx: CmplFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCmplFloatInstruction(ctx: CmplFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfEqInstruction(ctx: IfEqInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfEqInstruction(ctx: IfEqInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIputInstruction(ctx: IputInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIputInstruction(ctx: IputInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstWideInstruction(ctx: ConstWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstWideInstruction(ctx: ConstWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIntType(ctx: IntTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIntType(ctx: IntTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArrayRegister(ctx: ArrayRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArrayRegister(ctx: ArrayRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokePolymorphicRangeInstruction(ctx: InvokePolymorphicRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokePolymorphicRangeInstruction(ctx: InvokePolymorphicRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIputCharInstruction(ctx: IputCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIputCharInstruction(ctx: IputCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterHexFloatLiteral(ctx: HexFloatLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitHexFloatLiteral(ctx: HexFloatLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSubLongInstruction(ctx: SubLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSubLongInstruction(ctx: SubLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIntToLongInstruction(ctx: IntToLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIntToLongInstruction(ctx: IntToLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulFloatInstruction(ctx: MulFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulFloatInstruction(ctx: MulFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterGoto32Instruction(ctx: Goto32InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitGoto32Instruction(ctx: Goto32InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSubDouble2addrInstruction(ctx: SubDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSubDouble2addrInstruction(ctx: SubDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterHexNumericLiteral(ctx: HexNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitHexNumericLiteral(ctx: HexNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveObjectInstruction(ctx: MoveObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveObjectInstruction(ctx: MoveObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIputWideInstruction(ctx: IputWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIputWideInstruction(ctx: IputWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNonArrayType(ctx: NonArrayTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNonArrayType(ctx: NonArrayTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInstruction(ctx: InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInstruction(ctx: InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivFloat2addrInstruction(ctx: DivFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivFloat2addrInstruction(ctx: DivFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCheckInstanceType(ctx: CheckInstanceTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCheckInstanceType(ctx: CheckInstanceTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemLong2addrInstruction(ctx: RemLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemLong2addrInstruction(ctx: RemLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterUshrIntLit8Instruction(ctx: UshrIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitUshrIntLit8Instruction(ctx: UshrIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArrayElementRegisterRange(ctx: ArrayElementRegisterRangeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArrayElementRegisterRange(ctx: ArrayElementRegisterRangeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveWideInstruction(ctx: MoveWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveWideInstruction(ctx: MoveWideInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFloatToDoubleInstruction(ctx: FloatToDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFloatToDoubleInstruction(ctx: FloatToDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfLeInstruction(ctx: IfLeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfLeInstruction(ctx: IfLeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddLong2addrInstruction(ctx: AddLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddLong2addrInstruction(ctx: AddLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterOrIntInstruction(ctx: OrIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitOrIntInstruction(ctx: OrIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterReturnVoidInstruction(ctx: ReturnVoidInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitReturnVoidInstruction(ctx: ReturnVoidInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCmpgDoubleInstruction(ctx: CmpgDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCmpgDoubleInstruction(ctx: CmpgDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAputObjectInstruction(ctx: AputObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAputObjectInstruction(ctx: AputObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMonitorEnterInstruction(ctx: MonitorEnterInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMonitorEnterInstruction(ctx: MonitorEnterInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivLong2addrInstruction(ctx: DivLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivLong2addrInstruction(ctx: DivLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveInstruction(ctx: MoveInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveInstruction(ctx: MoveInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShlInt2addrInstruction(ctx: ShlInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShlInt2addrInstruction(ctx: ShlInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterParamDirective(ctx: ParamDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitParamDirective(ctx: ParamDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulFloat2addrInstruction(ctx: MulFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulFloat2addrInstruction(ctx: MulFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeInterfaceRangeInstruction(ctx: InvokeInterfaceRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeInterfaceRangeInstruction(ctx: InvokeInterfaceRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLongToIntInstruction(ctx: LongToIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLongToIntInstruction(ctx: LongToIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNegDoubleInstruction(ctx: NegDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNegDoubleInstruction(ctx: NegDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLongType(ctx: LongTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLongType(ctx: LongTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterClassDirective(ctx: ClassDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitClassDirective(ctx: ClassDirectiveContext) {
        val classFullName = ctx.className().text
        val packageName = classFullName.substring(1, classFullName.lastIndexOf('/')).replace('/', '.')
        val className = classFullName.substring(packageName.length + 2, classFullName.length - 1)
        parsedSmaliClass.name = className
        parsedSmaliClass.parentPackage = analyzer.getOrCreatePackage(packageName)
        for (t in ctx.classModifier()) {
            if (t.text == t.STATIC()?.text) parsedSmaliClass.setModifierBit(Modifier.STATIC)
            if (t.text == t.FINAL()?.text) parsedSmaliClass.setModifierBit(Modifier.FINAL)
            if (t.text == t.INTERFACE()?.text) parsedSmaliClass.setModifierBit(Modifier.INTERFACE)
            if (t.text == t.ABSTRACT()?.text) parsedSmaliClass.setModifierBit(Modifier.ABSTRACT)
            if (t.text == t.PUBLIC()?.text) parsedSmaliClass.setModifierBit(Modifier.PUBLIC)
            if (t.text == t.PROTECTED()?.text) parsedSmaliClass.setModifierBit(Modifier.PROTECTED)
            if (t.text == t.PRIVATE()?.text) parsedSmaliClass.setModifierBit(Modifier.PRIVATE)
        }
    }

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArrayDataDirective(ctx: ArrayDataDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArrayDataDirective(ctx: ArrayDataDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterBinaryNumericLiteral(ctx: BinaryNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitBinaryNumericLiteral(ctx: BinaryNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFloatType(ctx: FloatTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFloatType(ctx: FloatTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLongToDoubleInstruction(ctx: LongToDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLongToDoubleInstruction(ctx: LongToDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMethodBodyStatement(ctx: MethodBodyStatementContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMethodBodyStatement(ctx: MethodBodyStatementContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInstanceRegister(ctx: InstanceRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInstanceRegister(ctx: InstanceRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSPutCharInstruction(ctx: SPutCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSPutCharInstruction(ctx: SPutCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemInt2addrInstruction(ctx: RemInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemInt2addrInstruction(ctx: RemInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulDouble2addrInstruction(ctx: MulDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulDouble2addrInstruction(ctx: MulDouble2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeCustomRangeInstruction(ctx: InvokeCustomRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeCustomRangeInstruction(ctx: InvokeCustomRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCheckCastInstruction(ctx: CheckCastInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCheckCastInstruction(ctx: CheckCastInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeConstMethodTypeInstruction(ctx: InvokeConstMethodTypeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeConstMethodTypeInstruction(ctx: InvokeConstMethodTypeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArrayLengthInstruction(ctx: ArrayLengthInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArrayLengthInstruction(ctx: ArrayLengthInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterArraySizeRegister(ctx: ArraySizeRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitArraySizeRegister(ctx: ArraySizeRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterReturnObjectInstruction(ctx: ReturnObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitReturnObjectInstruction(ctx: ReturnObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemFloatInstruction(ctx: RemFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemFloatInstruction(ctx: RemFloatInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAputCharInstruction(ctx: AputCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAputCharInstruction(ctx: AputCharInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstWide32Instruction(ctx: ConstWide32InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstWide32Instruction(ctx: ConstWide32InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLocalDirectiveType(ctx: LocalDirectiveTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLocalDirectiveType(ctx: LocalDirectiveTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfGtzInstruction(ctx: IfGtzInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfGtzInstruction(ctx: IfGtzInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNewInstanceInstruction(ctx: NewInstanceInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNewInstanceInstruction(ctx: NewInstanceInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveObject16Instruction(ctx: MoveObject16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveObject16Instruction(ctx: MoveObject16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulInt2addrInstruction(ctx: MulInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulInt2addrInstruction(ctx: MulInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAnnotationScope(ctx: AnnotationScopeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAnnotationScope(ctx: AnnotationScopeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterGoto16Instruction(ctx: Goto16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitGoto16Instruction(ctx: Goto16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDoubleToLongInstruction(ctx: DoubleToLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDoubleToLongInstruction(ctx: DoubleToLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAssignableValue(ctx: AssignableValueContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAssignableValue(ctx: AssignableValueContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterInvokeVirtualRangeInstruction(ctx: InvokeVirtualRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitInvokeVirtualRangeInstruction(ctx: InvokeVirtualRangeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterXorInt2addrInstruction(ctx: XorInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitXorInt2addrInstruction(ctx: XorInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstClass(ctx: ConstClassContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstClass(ctx: ConstClassContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFieldName(ctx: FieldNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFieldName(ctx: FieldNameContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNotIntInstruction(ctx: NotIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNotIntInstruction(ctx: NotIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMonitorExitInstruction(ctx: MonitorExitInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMonitorExitInstruction(ctx: MonitorExitInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivDoubleInstruction(ctx: DivDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivDoubleInstruction(ctx: DivDoubleInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAndInt2addrInstruction(ctx: AndInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAndInt2addrInstruction(ctx: AndInt2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAgetByteInstruction(ctx: AgetByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAgetByteInstruction(ctx: AgetByteInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShlIntLit8Instruction(ctx: ShlIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShlIntLit8Instruction(ctx: ShlIntLit8InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMulLongInstruction(ctx: MulLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMulLongInstruction(ctx: MulLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRegisterList(ctx: RegisterListContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRegisterList(ctx: RegisterListContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFieldNameAndType(ctx: FieldNameAndTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFieldNameAndType(ctx: FieldNameAndTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPackedSwitchInstruction(ctx: PackedSwitchInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPackedSwitchInstruction(ctx: PackedSwitchInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPackedSwitchIdent(ctx: PackedSwitchIdentContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPackedSwitchIdent(ctx: PackedSwitchIdentContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDoubleToIntInstruction(ctx: DoubleToIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDoubleToIntInstruction(ctx: DoubleToIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterShrLong2addrInstruction(ctx: ShrLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitShrLong2addrInstruction(ctx: ShrLong2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSparseSwitchLabel(ctx: SparseSwitchLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSparseSwitchLabel(ctx: SparseSwitchLabelContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterFieldInvocationTarget(ctx: FieldInvocationTargetContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitFieldInvocationTarget(ctx: FieldInvocationTargetContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLocalRestartDirective(ctx: LocalRestartDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLocalRestartDirective(ctx: LocalRestartDirectiveContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterPrimitiveType(ctx: PrimitiveTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitPrimitiveType(ctx: PrimitiveTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSubFloat2addrInstruction(ctx: SubFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSubFloat2addrInstruction(ctx: SubFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterOctNumericLiteral(ctx: OctNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitOctNumericLiteral(ctx: OctNumericLiteralContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterMoveExceptionInstruction(ctx: MoveExceptionInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitMoveExceptionInstruction(ctx: MoveExceptionInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLeftRegister(ctx: LeftRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLeftRegister(ctx: LeftRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterXorLongInstruction(ctx: XorLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitXorLongInstruction(ctx: XorLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfLtInstruction(ctx: IfLtInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfLtInstruction(ctx: IfLtInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSPutInstruction(ctx: SPutInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSPutInstruction(ctx: SPutInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNegIntInstruction(ctx: NegIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNegIntInstruction(ctx: NegIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterCatchExceptionType(ctx: CatchExceptionTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitCatchExceptionType(ctx: CatchExceptionTypeContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterRemLongInstruction(ctx: RemLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitRemLongInstruction(ctx: RemLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAnnotationValueScoped(ctx: AnnotationValueScopedContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAnnotationValueScoped(ctx: AnnotationValueScopedContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterLocalDirectiveRegister(ctx: LocalDirectiveRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitLocalDirectiveRegister(ctx: LocalDirectiveRegisterContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterNotLongInstruction(ctx: NotLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitNotLongInstruction(ctx: NotLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAndIntInstruction(ctx: AndIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAndIntInstruction(ctx: AndIntInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterDivLongInstruction(ctx: DivLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitDivLongInstruction(ctx: DivLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSparseSwitchInstruction(ctx: SparseSwitchInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSparseSwitchInstruction(ctx: SparseSwitchInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfNeInstruction(ctx: IfNeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfNeInstruction(ctx: IfNeInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterAddFloat2addrInstruction(ctx: AddFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitAddFloat2addrInstruction(ctx: AddFloat2addrInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSPutBooleanInstruction(ctx: SPutBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSPutBooleanInstruction(ctx: SPutBooleanInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterConstWide16Instruction(ctx: ConstWide16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitConstWide16Instruction(ctx: ConstWide16InstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterSPutObjectInstruction(ctx: SPutObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitSPutObjectInstruction(ctx: SPutObjectInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterIfLezInstruction(ctx: IfLezInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitIfLezInstruction(ctx: IfLezInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterUshrLongInstruction(ctx: UshrLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitUshrLongInstruction(ctx: UshrLongInstructionContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun enterEveryRule(ctx: ParserRuleContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun exitEveryRule(ctx: ParserRuleContext) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun visitTerminal(node: TerminalNode) {}

    /**
     * {@inheritDoc}
     *
     *
     * The default implementation does nothing.
     */
    override fun visitErrorNode(node: ErrorNode) {}

}
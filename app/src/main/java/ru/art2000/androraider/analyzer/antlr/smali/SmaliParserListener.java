package ru.art2000.androraider.analyzer.antlr.smali;// Generated from D:\Coding\Antlr\SmaliParser.g4 by ANTLR 4.1

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SmaliParser}.
 */
public interface SmaliParserListener extends ParseTreeListener {
    /**
     * Enter a parse tree produced by {@link SmaliParser#negFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNegFloatInstruction(SmaliParser.NegFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#negFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNegFloatInstruction(SmaliParser.NegFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shrLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterShrLongInstruction(SmaliParser.ShrLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shrLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitShrLongInstruction(SmaliParser.ShrLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodReturnType}.
     *
     * @param ctx the parse tree
     */
    void enterMethodReturnType(SmaliParser.MethodReturnTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodReturnType}.
     *
     * @param ctx the parse tree
     */
    void exitMethodReturnType(SmaliParser.MethodReturnTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#fieldDirective}.
     *
     * @param ctx the parse tree
     */
    void enterFieldDirective(SmaliParser.FieldDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#fieldDirective}.
     *
     * @param ctx the parse tree
     */
    void exitFieldDirective(SmaliParser.FieldDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#newArrayInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNewArrayInstruction(SmaliParser.NewArrayInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#newArrayInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNewArrayInstruction(SmaliParser.NewArrayInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#agetInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAgetInstruction(SmaliParser.AgetInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#agetInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAgetInstruction(SmaliParser.AgetInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodDirective}.
     *
     * @param ctx the parse tree
     */
    void enterMethodDirective(SmaliParser.MethodDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodDirective}.
     *
     * @param ctx the parse tree
     */
    void exitMethodDirective(SmaliParser.MethodDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#throwInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterThrowInstruction(SmaliParser.ThrowInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#throwInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitThrowInstruction(SmaliParser.ThrowInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#iputBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIputBooleanInstruction(SmaliParser.IputBooleanInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#iputBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIputBooleanInstruction(SmaliParser.IputBooleanInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#intToDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIntToDoubleInstruction(SmaliParser.IntToDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#intToDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIntToDoubleInstruction(SmaliParser.IntToDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveResultInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveResultInstruction(SmaliParser.MoveResultInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveResultInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveResultInstruction(SmaliParser.MoveResultInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#packedSwitchDirective}.
     *
     * @param ctx the parse tree
     */
    void enterPackedSwitchDirective(SmaliParser.PackedSwitchDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#packedSwitchDirective}.
     *
     * @param ctx the parse tree
     */
    void exitPackedSwitchDirective(SmaliParser.PackedSwitchDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#igetBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIgetBooleanInstruction(SmaliParser.IgetBooleanInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#igetBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIgetBooleanInstruction(SmaliParser.IgetBooleanInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#igetCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIgetCharInstruction(SmaliParser.IgetCharInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#igetCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIgetCharInstruction(SmaliParser.IgetCharInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#gotoInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterGotoInstruction(SmaliParser.GotoInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#gotoInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitGotoInstruction(SmaliParser.GotoInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemFloat2addrInstruction(SmaliParser.RemFloat2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemFloat2addrInstruction(SmaliParser.RemFloat2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#binaryInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterBinaryInstruction(SmaliParser.BinaryInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#binaryInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitBinaryInstruction(SmaliParser.BinaryInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#rsubIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRsubIntInstruction(SmaliParser.RsubIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#rsubIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRsubIntInstruction(SmaliParser.RsubIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#annotationDirective}.
     *
     * @param ctx the parse tree
     */
    void enterAnnotationDirective(SmaliParser.AnnotationDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#annotationDirective}.
     *
     * @param ctx the parse tree
     */
    void exitAnnotationDirective(SmaliParser.AnnotationDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#const16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterConst16Instruction(SmaliParser.Const16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#const16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitConst16Instruction(SmaliParser.Const16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#numericLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterNumericLiteral(SmaliParser.NumericLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#numericLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitNumericLiteral(SmaliParser.NumericLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#charType}.
     *
     * @param ctx the parse tree
     */
    void enterCharType(SmaliParser.CharTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#charType}.
     *
     * @param ctx the parse tree
     */
    void exitCharType(SmaliParser.CharTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#annotationField}.
     *
     * @param ctx the parse tree
     */
    void enterAnnotationField(SmaliParser.AnnotationFieldContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#annotationField}.
     *
     * @param ctx the parse tree
     */
    void exitAnnotationField(SmaliParser.AnnotationFieldContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arrayType}.
     *
     * @param ctx the parse tree
     */
    void enterArrayType(SmaliParser.ArrayTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arrayType}.
     *
     * @param ctx the parse tree
     */
    void exitArrayType(SmaliParser.ArrayTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#aputByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAputByteInstruction(SmaliParser.AputByteInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#aputByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAputByteInstruction(SmaliParser.AputByteInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#superDirective}.
     *
     * @param ctx the parse tree
     */
    void enterSuperDirective(SmaliParser.SuperDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#superDirective}.
     *
     * @param ctx the parse tree
     */
    void exitSuperDirective(SmaliParser.SuperDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#subDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSubDoubleInstruction(SmaliParser.SubDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#subDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSubDoubleInstruction(SmaliParser.SubDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#doubleToFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDoubleToFloatInstruction(SmaliParser.DoubleToFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#doubleToFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDoubleToFloatInstruction(SmaliParser.DoubleToFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#andLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAndLongInstruction(SmaliParser.AndLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#andLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAndLongInstruction(SmaliParser.AndLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shlLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterShlLongInstruction(SmaliParser.ShlLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shlLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitShlLongInstruction(SmaliParser.ShlLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#catchGotoLabel}.
     *
     * @param ctx the parse tree
     */
    void enterCatchGotoLabel(SmaliParser.CatchGotoLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#catchGotoLabel}.
     *
     * @param ctx the parse tree
     */
    void exitCatchGotoLabel(SmaliParser.CatchGotoLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#returnWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterReturnWideInstruction(SmaliParser.ReturnWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#returnWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitReturnWideInstruction(SmaliParser.ReturnWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#floatToIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterFloatToIntInstruction(SmaliParser.FloatToIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#floatToIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitFloatToIntInstruction(SmaliParser.FloatToIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sPutByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSPutByteInstruction(SmaliParser.SPutByteInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sPutByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSPutByteInstruction(SmaliParser.SPutByteInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifEqzInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfEqzInstruction(SmaliParser.IfEqzInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifEqzInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfEqzInstruction(SmaliParser.IfEqzInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveResultObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveResultObjectInstruction(SmaliParser.MoveResultObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveResultObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveResultObjectInstruction(SmaliParser.MoveResultObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifLtzInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfLtzInstruction(SmaliParser.IfLtzInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifLtzInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfLtzInstruction(SmaliParser.IfLtzInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivIntLit16Instruction(SmaliParser.DivIntLit16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivIntLit16Instruction(SmaliParser.DivIntLit16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddIntLit16Instruction(SmaliParser.AddIntLit16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddIntLit16Instruction(SmaliParser.AddIntLit16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#registerRange}.
     *
     * @param ctx the parse tree
     */
    void enterRegisterRange(SmaliParser.RegisterRangeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#registerRange}.
     *
     * @param ctx the parse tree
     */
    void exitRegisterRange(SmaliParser.RegisterRangeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#fieldType}.
     *
     * @param ctx the parse tree
     */
    void enterFieldType(SmaliParser.FieldTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#fieldType}.
     *
     * @param ctx the parse tree
     */
    void exitFieldType(SmaliParser.FieldTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodParameterType}.
     *
     * @param ctx the parse tree
     */
    void enterMethodParameterType(SmaliParser.MethodParameterTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodParameterType}.
     *
     * @param ctx the parse tree
     */
    void exitMethodParameterType(SmaliParser.MethodParameterTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#checkCastType}.
     *
     * @param ctx the parse tree
     */
    void enterCheckCastType(SmaliParser.CheckCastTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#checkCastType}.
     *
     * @param ctx the parse tree
     */
    void exitCheckCastType(SmaliParser.CheckCastTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sourceRegister}.
     *
     * @param ctx the parse tree
     */
    void enterSourceRegister(SmaliParser.SourceRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sourceRegister}.
     *
     * @param ctx the parse tree
     */
    void exitSourceRegister(SmaliParser.SourceRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#referenceType}.
     *
     * @param ctx the parse tree
     */
    void enterReferenceType(SmaliParser.ReferenceTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#referenceType}.
     *
     * @param ctx the parse tree
     */
    void exitReferenceType(SmaliParser.ReferenceTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeStaticInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeStaticInstruction(SmaliParser.InvokeStaticInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeStaticInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeStaticInstruction(SmaliParser.InvokeStaticInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#instanceOfInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInstanceOfInstruction(SmaliParser.InstanceOfInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#instanceOfInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInstanceOfInstruction(SmaliParser.InstanceOfInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#aputInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAputInstruction(SmaliParser.AputInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#aputInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAputInstruction(SmaliParser.AputInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#igetWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIgetWideInstruction(SmaliParser.IgetWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#igetWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIgetWideInstruction(SmaliParser.IgetWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#floatNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterFloatNumericLiteral(SmaliParser.FloatNumericLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#floatNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitFloatNumericLiteral(SmaliParser.FloatNumericLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#igetObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIgetObjectInstruction(SmaliParser.IgetObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#igetObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIgetObjectInstruction(SmaliParser.IgetObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#xorIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterXorIntInstruction(SmaliParser.XorIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#xorIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitXorIntInstruction(SmaliParser.XorIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#labelName}.
     *
     * @param ctx the parse tree
     */
    void enterLabelName(SmaliParser.LabelNameContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#labelName}.
     *
     * @param ctx the parse tree
     */
    void exitLabelName(SmaliParser.LabelNameContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#registerIdentifier}.
     *
     * @param ctx the parse tree
     */
    void enterRegisterIdentifier(SmaliParser.RegisterIdentifierContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#registerIdentifier}.
     *
     * @param ctx the parse tree
     */
    void exitRegisterIdentifier(SmaliParser.RegisterIdentifierContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#floatToLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterFloatToLongInstruction(SmaliParser.FloatToLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#floatToLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitFloatToLongInstruction(SmaliParser.FloatToLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#annotationFieldValue}.
     *
     * @param ctx the parse tree
     */
    void enterAnnotationFieldValue(SmaliParser.AnnotationFieldValueContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#annotationFieldValue}.
     *
     * @param ctx the parse tree
     */
    void exitAnnotationFieldValue(SmaliParser.AnnotationFieldValueContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulLong2addrInstruction(SmaliParser.MulLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulLong2addrInstruction(SmaliParser.MulLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arrayElementRegisters}.
     *
     * @param ctx the parse tree
     */
    void enterArrayElementRegisters(SmaliParser.ArrayElementRegistersContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arrayElementRegisters}.
     *
     * @param ctx the parse tree
     */
    void exitArrayElementRegisters(SmaliParser.ArrayElementRegistersContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveWide16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveWide16Instruction(SmaliParser.MoveWide16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveWide16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveWide16Instruction(SmaliParser.MoveWide16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemIntLit8Instruction(SmaliParser.RemIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemIntLit8Instruction(SmaliParser.RemIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sPutWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSPutWideInstruction(SmaliParser.SPutWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sPutWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSPutWideInstruction(SmaliParser.SPutWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#agetObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAgetObjectInstruction(SmaliParser.AgetObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#agetObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAgetObjectInstruction(SmaliParser.AgetObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddDoubleInstruction(SmaliParser.AddDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddDoubleInstruction(SmaliParser.AddDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodIdentifier}.
     *
     * @param ctx the parse tree
     */
    void enterMethodIdentifier(SmaliParser.MethodIdentifierContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodIdentifier}.
     *
     * @param ctx the parse tree
     */
    void exitMethodIdentifier(SmaliParser.MethodIdentifierContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#positiveNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterPositiveNumericLiteral(SmaliParser.PositiveNumericLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#positiveNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitPositiveNumericLiteral(SmaliParser.PositiveNumericLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#intToCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIntToCharInstruction(SmaliParser.IntToCharInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#intToCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIntToCharInstruction(SmaliParser.IntToCharInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constWideHigh16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterConstWideHigh16Instruction(SmaliParser.ConstWideHigh16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constWideHigh16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitConstWideHigh16Instruction(SmaliParser.ConstWideHigh16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ushrLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterUshrLong2addrInstruction(SmaliParser.UshrLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ushrLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitUshrLong2addrInstruction(SmaliParser.UshrLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeSuperInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeSuperInstruction(SmaliParser.InvokeSuperInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeSuperInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeSuperInstruction(SmaliParser.InvokeSuperInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#aputWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAputWideInstruction(SmaliParser.AputWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#aputWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAputWideInstruction(SmaliParser.AputWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeCustomInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeCustomInstruction(SmaliParser.InvokeCustomInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeCustomInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeCustomInstruction(SmaliParser.InvokeCustomInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#superName}.
     *
     * @param ctx the parse tree
     */
    void enterSuperName(SmaliParser.SuperNameContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#superName}.
     *
     * @param ctx the parse tree
     */
    void exitSuperName(SmaliParser.SuperNameContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddLongInstruction(SmaliParser.AddLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddLongInstruction(SmaliParser.AddLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#anyType}.
     *
     * @param ctx the parse tree
     */
    void enterAnyType(SmaliParser.AnyTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#anyType}.
     *
     * @param ctx the parse tree
     */
    void exitAnyType(SmaliParser.AnyTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulIntLit16Instruction(SmaliParser.MulIntLit16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulIntLit16Instruction(SmaliParser.MulIntLit16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#extendedParamDirective}.
     *
     * @param ctx the parse tree
     */
    void enterExtendedParamDirective(SmaliParser.ExtendedParamDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#extendedParamDirective}.
     *
     * @param ctx the parse tree
     */
    void exitExtendedParamDirective(SmaliParser.ExtendedParamDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#fillArrayDataInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterFillArrayDataInstruction(SmaliParser.FillArrayDataInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#fillArrayDataInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitFillArrayDataInstruction(SmaliParser.FillArrayDataInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#localsDirective}.
     *
     * @param ctx the parse tree
     */
    void enterLocalsDirective(SmaliParser.LocalsDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#localsDirective}.
     *
     * @param ctx the parse tree
     */
    void exitLocalsDirective(SmaliParser.LocalsDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constHigh16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterConstHigh16Instruction(SmaliParser.ConstHigh16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constHigh16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitConstHigh16Instruction(SmaliParser.ConstHigh16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#referenceOrArrayType}.
     *
     * @param ctx the parse tree
     */
    void enterReferenceOrArrayType(SmaliParser.ReferenceOrArrayTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#referenceOrArrayType}.
     *
     * @param ctx the parse tree
     */
    void exitReferenceOrArrayType(SmaliParser.ReferenceOrArrayTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sparseSwitchDirective}.
     *
     * @param ctx the parse tree
     */
    void enterSparseSwitchDirective(SmaliParser.SparseSwitchDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sparseSwitchDirective}.
     *
     * @param ctx the parse tree
     */
    void exitSparseSwitchDirective(SmaliParser.SparseSwitchDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shrIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterShrIntInstruction(SmaliParser.ShrIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shrIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitShrIntInstruction(SmaliParser.ShrIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#intToShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIntToShortInstruction(SmaliParser.IntToShortInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#intToShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIntToShortInstruction(SmaliParser.IntToShortInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifGezInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfGezInstruction(SmaliParser.IfGezInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifGezInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfGezInstruction(SmaliParser.IfGezInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#subLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSubLong2addrInstruction(SmaliParser.SubLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#subLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSubLong2addrInstruction(SmaliParser.SubLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#subFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSubFloatInstruction(SmaliParser.SubFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#subFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSubFloatInstruction(SmaliParser.SubFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeConstMethodHandleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeConstMethodHandleInstruction(SmaliParser.InvokeConstMethodHandleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeConstMethodHandleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeConstMethodHandleInstruction(SmaliParser.InvokeConstMethodHandleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#orLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterOrLong2addrInstruction(SmaliParser.OrLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#orLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitOrLong2addrInstruction(SmaliParser.OrLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivFloatInstruction(SmaliParser.DivFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivFloatInstruction(SmaliParser.DivFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#catchDirective}.
     *
     * @param ctx the parse tree
     */
    void enterCatchDirective(SmaliParser.CatchDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#catchDirective}.
     *
     * @param ctx the parse tree
     */
    void exitCatchDirective(SmaliParser.CatchDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#igetShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIgetShortInstruction(SmaliParser.IgetShortInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#igetShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIgetShortInstruction(SmaliParser.IgetShortInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#voidType}.
     *
     * @param ctx the parse tree
     */
    void enterVoidType(SmaliParser.VoidTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#voidType}.
     *
     * @param ctx the parse tree
     */
    void exitVoidType(SmaliParser.VoidTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#booleanLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterBooleanLiteral(SmaliParser.BooleanLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#booleanLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitBooleanLiteral(SmaliParser.BooleanLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodModifier}.
     *
     * @param ctx the parse tree
     */
    void enterMethodModifier(SmaliParser.MethodModifierContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodModifier}.
     *
     * @param ctx the parse tree
     */
    void exitMethodModifier(SmaliParser.MethodModifierContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeDirectRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeDirectRangeInstruction(SmaliParser.InvokeDirectRangeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeDirectRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeDirectRangeInstruction(SmaliParser.InvokeDirectRangeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifGeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfGeInstruction(SmaliParser.IfGeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifGeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfGeInstruction(SmaliParser.IfGeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeSuperRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeSuperRangeInstruction(SmaliParser.InvokeSuperRangeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeSuperRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeSuperRangeInstruction(SmaliParser.InvokeSuperRangeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveFrom16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveFrom16Instruction(SmaliParser.MoveFrom16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveFrom16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveFrom16Instruction(SmaliParser.MoveFrom16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#targetRegister}.
     *
     * @param ctx the parse tree
     */
    void enterTargetRegister(SmaliParser.TargetRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#targetRegister}.
     *
     * @param ctx the parse tree
     */
    void exitTargetRegister(SmaliParser.TargetRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ternaryInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterTernaryInstruction(SmaliParser.TernaryInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ternaryInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitTernaryInstruction(SmaliParser.TernaryInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#xorIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterXorIntLit16Instruction(SmaliParser.XorIntLit16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#xorIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitXorIntLit16Instruction(SmaliParser.XorIntLit16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#newInstanceType}.
     *
     * @param ctx the parse tree
     */
    void enterNewInstanceType(SmaliParser.NewInstanceTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#newInstanceType}.
     *
     * @param ctx the parse tree
     */
    void exitNewInstanceType(SmaliParser.NewInstanceTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivIntInstruction(SmaliParser.DivIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivIntInstruction(SmaliParser.DivIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemDouble2addrInstruction(SmaliParser.RemDouble2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemDouble2addrInstruction(SmaliParser.RemDouble2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#catchFromLabel}.
     *
     * @param ctx the parse tree
     */
    void enterCatchFromLabel(SmaliParser.CatchFromLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#catchFromLabel}.
     *
     * @param ctx the parse tree
     */
    void exitCatchFromLabel(SmaliParser.CatchFromLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#registerListRegisters}.
     *
     * @param ctx the parse tree
     */
    void enterRegisterListRegisters(SmaliParser.RegisterListRegistersContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#registerListRegisters}.
     *
     * @param ctx the parse tree
     */
    void exitRegisterListRegisters(SmaliParser.RegisterListRegistersContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#longToFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterLongToFloatInstruction(SmaliParser.LongToFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#longToFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitLongToFloatInstruction(SmaliParser.LongToFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#igetByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIgetByteInstruction(SmaliParser.IgetByteInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#igetByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIgetByteInstruction(SmaliParser.IgetByteInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#indexRegister}.
     *
     * @param ctx the parse tree
     */
    void enterIndexRegister(SmaliParser.IndexRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#indexRegister}.
     *
     * @param ctx the parse tree
     */
    void exitIndexRegister(SmaliParser.IndexRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#simpleParamDirective}.
     *
     * @param ctx the parse tree
     */
    void enterSimpleParamDirective(SmaliParser.SimpleParamDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#simpleParamDirective}.
     *
     * @param ctx the parse tree
     */
    void exitSimpleParamDirective(SmaliParser.SimpleParamDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#andLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAndLong2addrInstruction(SmaliParser.AndLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#andLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAndLong2addrInstruction(SmaliParser.AndLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeDirectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeDirectInstruction(SmaliParser.InvokeDirectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeDirectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeDirectInstruction(SmaliParser.InvokeDirectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arrayDataEntry}.
     *
     * @param ctx the parse tree
     */
    void enterArrayDataEntry(SmaliParser.ArrayDataEntryContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arrayDataEntry}.
     *
     * @param ctx the parse tree
     */
    void exitArrayDataEntry(SmaliParser.ArrayDataEntryContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#lineLabel}.
     *
     * @param ctx the parse tree
     */
    void enterLineLabel(SmaliParser.LineLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#lineLabel}.
     *
     * @param ctx the parse tree
     */
    void exitLineLabel(SmaliParser.LineLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterConstInstruction(SmaliParser.ConstInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitConstInstruction(SmaliParser.ConstInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#filledArrayDataLabel}.
     *
     * @param ctx the parse tree
     */
    void enterFilledArrayDataLabel(SmaliParser.FilledArrayDataLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#filledArrayDataLabel}.
     *
     * @param ctx the parse tree
     */
    void exitFilledArrayDataLabel(SmaliParser.FilledArrayDataLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#doubleType}.
     *
     * @param ctx the parse tree
     */
    void enterDoubleType(SmaliParser.DoubleTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#doubleType}.
     *
     * @param ctx the parse tree
     */
    void exitDoubleType(SmaliParser.DoubleTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemIntLit16Instruction(SmaliParser.RemIntLit16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemIntLit16Instruction(SmaliParser.RemIntLit16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabels}.
     *
     * @param ctx the parse tree
     */
    void enterPackedSwitchDirectiveLabels(SmaliParser.PackedSwitchDirectiveLabelsContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabels}.
     *
     * @param ctx the parse tree
     */
    void exitPackedSwitchDirectiveLabels(SmaliParser.PackedSwitchDirectiveLabelsContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodInvocationTarget}.
     *
     * @param ctx the parse tree
     */
    void enterMethodInvocationTarget(SmaliParser.MethodInvocationTargetContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodInvocationTarget}.
     *
     * @param ctx the parse tree
     */
    void exitMethodInvocationTarget(SmaliParser.MethodInvocationTargetContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterMethodDeclaration(SmaliParser.MethodDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitMethodDeclaration(SmaliParser.MethodDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodBody}.
     *
     * @param ctx the parse tree
     */
    void enterMethodBody(SmaliParser.MethodBodyContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodBody}.
     *
     * @param ctx the parse tree
     */
    void exitMethodBody(SmaliParser.MethodBodyContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#iputObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIputObjectInstruction(SmaliParser.IputObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#iputObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIputObjectInstruction(SmaliParser.IputObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#cmplDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterCmplDoubleInstruction(SmaliParser.CmplDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#cmplDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitCmplDoubleInstruction(SmaliParser.CmplDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shortType}.
     *
     * @param ctx the parse tree
     */
    void enterShortType(SmaliParser.ShortTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shortType}.
     *
     * @param ctx the parse tree
     */
    void exitShortType(SmaliParser.ShortTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivInt2addrInstruction(SmaliParser.DivInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivInt2addrInstruction(SmaliParser.DivInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#filledNewArrayInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterFilledNewArrayInstruction(SmaliParser.FilledNewArrayInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#filledNewArrayInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitFilledNewArrayInstruction(SmaliParser.FilledNewArrayInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivDouble2addrInstruction(SmaliParser.DivDouble2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivDouble2addrInstruction(SmaliParser.DivDouble2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#byteType}.
     *
     * @param ctx the parse tree
     */
    void enterByteType(SmaliParser.ByteTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#byteType}.
     *
     * @param ctx the parse tree
     */
    void exitByteType(SmaliParser.ByteTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#igetInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIgetInstruction(SmaliParser.IgetInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#igetInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIgetInstruction(SmaliParser.IgetInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#packedSwitchRegister}.
     *
     * @param ctx the parse tree
     */
    void enterPackedSwitchRegister(SmaliParser.PackedSwitchRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#packedSwitchRegister}.
     *
     * @param ctx the parse tree
     */
    void exitPackedSwitchRegister(SmaliParser.PackedSwitchRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeStaticRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeStaticRangeInstruction(SmaliParser.InvokeStaticRangeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeStaticRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeStaticRangeInstruction(SmaliParser.InvokeStaticRangeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#lineDirective}.
     *
     * @param ctx the parse tree
     */
    void enterLineDirective(SmaliParser.LineDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#lineDirective}.
     *
     * @param ctx the parse tree
     */
    void exitLineDirective(SmaliParser.LineDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodParameterLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterMethodParameterLiteral(SmaliParser.MethodParameterLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodParameterLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitMethodParameterLiteral(SmaliParser.MethodParameterLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifGtInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfGtInstruction(SmaliParser.IfGtInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifGtInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfGtInstruction(SmaliParser.IfGtInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#orIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterOrIntLit8Instruction(SmaliParser.OrIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#orIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitOrIntLit8Instruction(SmaliParser.OrIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arrayElementType}.
     *
     * @param ctx the parse tree
     */
    void enterArrayElementType(SmaliParser.ArrayElementTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arrayElementType}.
     *
     * @param ctx the parse tree
     */
    void exitArrayElementType(SmaliParser.ArrayElementTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#intToByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIntToByteInstruction(SmaliParser.IntToByteInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#intToByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIntToByteInstruction(SmaliParser.IntToByteInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterStatement(SmaliParser.StatementContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitStatement(SmaliParser.StatementContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#fieldModifier}.
     *
     * @param ctx the parse tree
     */
    void enterFieldModifier(SmaliParser.FieldModifierContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#fieldModifier}.
     *
     * @param ctx the parse tree
     */
    void exitFieldModifier(SmaliParser.FieldModifierContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#move16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterMove16Instruction(SmaliParser.Move16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#move16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitMove16Instruction(SmaliParser.Move16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivIntLit8Instruction(SmaliParser.DivIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivIntLit8Instruction(SmaliParser.DivIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#xorLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterXorLong2addrInstruction(SmaliParser.XorLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#xorLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitXorLong2addrInstruction(SmaliParser.XorLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#agetShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAgetShortInstruction(SmaliParser.AgetShortInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#agetShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAgetShortInstruction(SmaliParser.AgetShortInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#andIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterAndIntLit8Instruction(SmaliParser.AndIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#andIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitAndIntLit8Instruction(SmaliParser.AndIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#cmpgFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterCmpgFloatInstruction(SmaliParser.CmpgFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#cmpgFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitCmpgFloatInstruction(SmaliParser.CmpgFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#nullLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterNullLiteral(SmaliParser.NullLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#nullLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitNullLiteral(SmaliParser.NullLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveWideFrom16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveWideFrom16Instruction(SmaliParser.MoveWideFrom16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveWideFrom16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveWideFrom16Instruction(SmaliParser.MoveWideFrom16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#subInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSubInt2addrInstruction(SmaliParser.SubInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#subInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSubInt2addrInstruction(SmaliParser.SubInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#orInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterOrInt2addrInstruction(SmaliParser.OrInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#orInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitOrInt2addrInstruction(SmaliParser.OrInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#label}.
     *
     * @param ctx the parse tree
     */
    void enterLabel(SmaliParser.LabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#label}.
     *
     * @param ctx the parse tree
     */
    void exitLabel(SmaliParser.LabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#parse}.
     *
     * @param ctx the parse tree
     */
    void enterParse(SmaliParser.ParseContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#parse}.
     *
     * @param ctx the parse tree
     */
    void exitParse(SmaliParser.ParseContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddDouble2addrInstruction(SmaliParser.AddDouble2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddDouble2addrInstruction(SmaliParser.AddDouble2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#localDirective}.
     *
     * @param ctx the parse tree
     */
    void enterLocalDirective(SmaliParser.LocalDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#localDirective}.
     *
     * @param ctx the parse tree
     */
    void exitLocalDirective(SmaliParser.LocalDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#locaDirectiveVariableName}.
     *
     * @param ctx the parse tree
     */
    void enterLocaDirectiveVariableName(SmaliParser.LocaDirectiveVariableNameContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#locaDirectiveVariableName}.
     *
     * @param ctx the parse tree
     */
    void exitLocaDirectiveVariableName(SmaliParser.LocaDirectiveVariableNameContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#packedSwitchLabel}.
     *
     * @param ctx the parse tree
     */
    void enterPackedSwitchLabel(SmaliParser.PackedSwitchLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#packedSwitchLabel}.
     *
     * @param ctx the parse tree
     */
    void exitPackedSwitchLabel(SmaliParser.PackedSwitchLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sparseSwitchDirectiveValue}.
     *
     * @param ctx the parse tree
     */
    void enterSparseSwitchDirectiveValue(SmaliParser.SparseSwitchDirectiveValueContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sparseSwitchDirectiveValue}.
     *
     * @param ctx the parse tree
     */
    void exitSparseSwitchDirectiveValue(SmaliParser.SparseSwitchDirectiveValueContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#aputShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAputShortInstruction(SmaliParser.AputShortInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#aputShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAputShortInstruction(SmaliParser.AputShortInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sGetBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSGetBooleanInstruction(SmaliParser.SGetBooleanInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sGetBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSGetBooleanInstruction(SmaliParser.SGetBooleanInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifNezInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfNezInstruction(SmaliParser.IfNezInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifNezInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfNezInstruction(SmaliParser.IfNezInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulIntInstruction(SmaliParser.MulIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulIntInstruction(SmaliParser.MulIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#iputShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIputShortInstruction(SmaliParser.IputShortInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#iputShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIputShortInstruction(SmaliParser.IputShortInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sourceName}.
     *
     * @param ctx the parse tree
     */
    void enterSourceName(SmaliParser.SourceNameContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sourceName}.
     *
     * @param ctx the parse tree
     */
    void exitSourceName(SmaliParser.SourceNameContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddIntLit8Instruction(SmaliParser.AddIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddIntLit8Instruction(SmaliParser.AddIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulDoubleInstruction(SmaliParser.MulDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulDoubleInstruction(SmaliParser.MulDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokePolymorphicInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokePolymorphicInstruction(SmaliParser.InvokePolymorphicInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokePolymorphicInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokePolymorphicInstruction(SmaliParser.InvokePolymorphicInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabel}.
     *
     * @param ctx the parse tree
     */
    void enterPackedSwitchDirectiveLabel(SmaliParser.PackedSwitchDirectiveLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabel}.
     *
     * @param ctx the parse tree
     */
    void exitPackedSwitchDirectiveLabel(SmaliParser.PackedSwitchDirectiveLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#nonVoidType}.
     *
     * @param ctx the parse tree
     */
    void enterNonVoidType(SmaliParser.NonVoidTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#nonVoidType}.
     *
     * @param ctx the parse tree
     */
    void exitNonVoidType(SmaliParser.NonVoidTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#nopInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNopInstruction(SmaliParser.NopInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#nopInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNopInstruction(SmaliParser.NopInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#catchAllDirective}.
     *
     * @param ctx the parse tree
     */
    void enterCatchAllDirective(SmaliParser.CatchAllDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#catchAllDirective}.
     *
     * @param ctx the parse tree
     */
    void exitCatchAllDirective(SmaliParser.CatchAllDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sGetInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSGetInstruction(SmaliParser.SGetInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sGetInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSGetInstruction(SmaliParser.SGetInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifLabel}.
     *
     * @param ctx the parse tree
     */
    void enterIfLabel(SmaliParser.IfLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifLabel}.
     *
     * @param ctx the parse tree
     */
    void exitIfLabel(SmaliParser.IfLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sGetWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSGetWideInstruction(SmaliParser.SGetWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sGetWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSGetWideInstruction(SmaliParser.SGetWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#rightRegister}.
     *
     * @param ctx the parse tree
     */
    void enterRightRegister(SmaliParser.RightRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#rightRegister}.
     *
     * @param ctx the parse tree
     */
    void exitRightRegister(SmaliParser.RightRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodSignature}.
     *
     * @param ctx the parse tree
     */
    void enterMethodSignature(SmaliParser.MethodSignatureContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodSignature}.
     *
     * @param ctx the parse tree
     */
    void exitMethodSignature(SmaliParser.MethodSignatureContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#negLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNegLongInstruction(SmaliParser.NegLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#negLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNegLongInstruction(SmaliParser.NegLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveObjectFrom16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveObjectFrom16Instruction(SmaliParser.MoveObjectFrom16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveObjectFrom16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveObjectFrom16Instruction(SmaliParser.MoveObjectFrom16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodArguments}.
     *
     * @param ctx the parse tree
     */
    void enterMethodArguments(SmaliParser.MethodArgumentsContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodArguments}.
     *
     * @param ctx the parse tree
     */
    void exitMethodArguments(SmaliParser.MethodArgumentsContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#subIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSubIntInstruction(SmaliParser.SubIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#subIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSubIntInstruction(SmaliParser.SubIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#catchToLabel}.
     *
     * @param ctx the parse tree
     */
    void enterCatchToLabel(SmaliParser.CatchToLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#catchToLabel}.
     *
     * @param ctx the parse tree
     */
    void exitCatchToLabel(SmaliParser.CatchToLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#orLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterOrLongInstruction(SmaliParser.OrLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#orLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitOrLongInstruction(SmaliParser.OrLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#identifier}.
     *
     * @param ctx the parse tree
     */
    void enterIdentifier(SmaliParser.IdentifierContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#identifier}.
     *
     * @param ctx the parse tree
     */
    void exitIdentifier(SmaliParser.IdentifierContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#negativeNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterNegativeNumericLiteral(SmaliParser.NegativeNumericLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#negativeNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitNegativeNumericLiteral(SmaliParser.NegativeNumericLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#booleanType}.
     *
     * @param ctx the parse tree
     */
    void enterBooleanType(SmaliParser.BooleanTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#booleanType}.
     *
     * @param ctx the parse tree
     */
    void exitBooleanType(SmaliParser.BooleanTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulIntLit8Instruction(SmaliParser.MulIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulIntLit8Instruction(SmaliParser.MulIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddInt2addrInstruction(SmaliParser.AddInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddInt2addrInstruction(SmaliParser.AddInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shrInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterShrInt2addrInstruction(SmaliParser.ShrInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shrInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitShrInt2addrInstruction(SmaliParser.ShrInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#agetWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAgetWideInstruction(SmaliParser.AgetWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#agetWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAgetWideInstruction(SmaliParser.AgetWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#annotationType}.
     *
     * @param ctx the parse tree
     */
    void enterAnnotationType(SmaliParser.AnnotationTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#annotationType}.
     *
     * @param ctx the parse tree
     */
    void exitAnnotationType(SmaliParser.AnnotationTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#const4Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterConst4Instruction(SmaliParser.Const4InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#const4Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitConst4Instruction(SmaliParser.Const4InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#iputByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIputByteInstruction(SmaliParser.IputByteInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#iputByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIputByteInstruction(SmaliParser.IputByteInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemDoubleInstruction(SmaliParser.RemDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemDoubleInstruction(SmaliParser.RemDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constString}.
     *
     * @param ctx the parse tree
     */
    void enterConstString(SmaliParser.ConstStringContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constString}.
     *
     * @param ctx the parse tree
     */
    void exitConstString(SmaliParser.ConstStringContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sGetCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSGetCharInstruction(SmaliParser.SGetCharInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sGetCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSGetCharInstruction(SmaliParser.SGetCharInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ushrInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterUshrInt2addrInstruction(SmaliParser.UshrInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ushrInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitUshrInt2addrInstruction(SmaliParser.UshrInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constStringJumbo}.
     *
     * @param ctx the parse tree
     */
    void enterConstStringJumbo(SmaliParser.ConstStringJumboContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constStringJumbo}.
     *
     * @param ctx the parse tree
     */
    void exitConstStringJumbo(SmaliParser.ConstStringJumboContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#field}.
     *
     * @param ctx the parse tree
     */
    void enterField(SmaliParser.FieldContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#field}.
     *
     * @param ctx the parse tree
     */
    void exitField(SmaliParser.FieldContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#stringLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterStringLiteral(SmaliParser.StringLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#stringLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitStringLiteral(SmaliParser.StringLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#returnInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterReturnInstruction(SmaliParser.ReturnInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#returnInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitReturnInstruction(SmaliParser.ReturnInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeVirtualInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeVirtualInstruction(SmaliParser.InvokeVirtualInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeVirtualInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeVirtualInstruction(SmaliParser.InvokeVirtualInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shlLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterShlLong2addrInstruction(SmaliParser.ShlLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shlLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitShlLong2addrInstruction(SmaliParser.ShlLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shlIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterShlIntInstruction(SmaliParser.ShlIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shlIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitShlIntInstruction(SmaliParser.ShlIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ushrIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterUshrIntInstruction(SmaliParser.UshrIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ushrIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitUshrIntInstruction(SmaliParser.UshrIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sGetObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSGetObjectInstruction(SmaliParser.SGetObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sGetObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSGetObjectInstruction(SmaliParser.SGetObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#filledNewArrayRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterFilledNewArrayRangeInstruction(SmaliParser.FilledNewArrayRangeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#filledNewArrayRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitFilledNewArrayRangeInstruction(SmaliParser.FilledNewArrayRangeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sGetShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSGetShortInstruction(SmaliParser.SGetShortInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sGetShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSGetShortInstruction(SmaliParser.SGetShortInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#andIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterAndIntLit16Instruction(SmaliParser.AndIntLit16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#andIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitAndIntLit16Instruction(SmaliParser.AndIntLit16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#xorIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterXorIntLit8Instruction(SmaliParser.XorIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#xorIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitXorIntLit8Instruction(SmaliParser.XorIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sourceDirective}.
     *
     * @param ctx the parse tree
     */
    void enterSourceDirective(SmaliParser.SourceDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sourceDirective}.
     *
     * @param ctx the parse tree
     */
    void exitSourceDirective(SmaliParser.SourceDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#orIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterOrIntLit16Instruction(SmaliParser.OrIntLit16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#orIntLit16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitOrIntLit16Instruction(SmaliParser.OrIntLit16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#classModifier}.
     *
     * @param ctx the parse tree
     */
    void enterClassModifier(SmaliParser.ClassModifierContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#classModifier}.
     *
     * @param ctx the parse tree
     */
    void exitClassModifier(SmaliParser.ClassModifierContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#localEndDirective}.
     *
     * @param ctx the parse tree
     */
    void enterLocalEndDirective(SmaliParser.LocalEndDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#localEndDirective}.
     *
     * @param ctx the parse tree
     */
    void exitLocalEndDirective(SmaliParser.LocalEndDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#localDirectiveGenericHint}.
     *
     * @param ctx the parse tree
     */
    void enterLocalDirectiveGenericHint(SmaliParser.LocalDirectiveGenericHintContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#localDirectiveGenericHint}.
     *
     * @param ctx the parse tree
     */
    void exitLocalDirectiveGenericHint(SmaliParser.LocalDirectiveGenericHintContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shrIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterShrIntLit8Instruction(SmaliParser.ShrIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shrIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitShrIntLit8Instruction(SmaliParser.ShrIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sPutShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSPutShortInstruction(SmaliParser.SPutShortInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sPutShortInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSPutShortInstruction(SmaliParser.SPutShortInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#aputBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAputBooleanInstruction(SmaliParser.AputBooleanInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#aputBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAputBooleanInstruction(SmaliParser.AputBooleanInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeInterfaceInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeInterfaceInstruction(SmaliParser.InvokeInterfaceInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeInterfaceInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeInterfaceInstruction(SmaliParser.InvokeInterfaceInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#cmpLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterCmpLongInstruction(SmaliParser.CmpLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#cmpLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitCmpLongInstruction(SmaliParser.CmpLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#className}.
     *
     * @param ctx the parse tree
     */
    void enterClassName(SmaliParser.ClassNameContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#className}.
     *
     * @param ctx the parse tree
     */
    void exitClassName(SmaliParser.ClassNameContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#agetCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAgetCharInstruction(SmaliParser.AgetCharInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#agetCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAgetCharInstruction(SmaliParser.AgetCharInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#agetBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAgetBooleanInstruction(SmaliParser.AgetBooleanInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#agetBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAgetBooleanInstruction(SmaliParser.AgetBooleanInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sparseSwitchRegister}.
     *
     * @param ctx the parse tree
     */
    void enterSparseSwitchRegister(SmaliParser.SparseSwitchRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sparseSwitchRegister}.
     *
     * @param ctx the parse tree
     */
    void exitSparseSwitchRegister(SmaliParser.SparseSwitchRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveResultWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveResultWideInstruction(SmaliParser.MoveResultWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveResultWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveResultWideInstruction(SmaliParser.MoveResultWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#rsubIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterRsubIntLit8Instruction(SmaliParser.RsubIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#rsubIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitRsubIntLit8Instruction(SmaliParser.RsubIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#decimalNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterDecimalNumericLiteral(SmaliParser.DecimalNumericLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#decimalNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitDecimalNumericLiteral(SmaliParser.DecimalNumericLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemIntInstruction(SmaliParser.RemIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemIntInstruction(SmaliParser.RemIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#instanceField}.
     *
     * @param ctx the parse tree
     */
    void enterInstanceField(SmaliParser.InstanceFieldContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#instanceField}.
     *
     * @param ctx the parse tree
     */
    void exitInstanceField(SmaliParser.InstanceFieldContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#registersDirective}.
     *
     * @param ctx the parse tree
     */
    void enterRegistersDirective(SmaliParser.RegistersDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#registersDirective}.
     *
     * @param ctx the parse tree
     */
    void exitRegistersDirective(SmaliParser.RegistersDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sGetByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSGetByteInstruction(SmaliParser.SGetByteInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sGetByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSGetByteInstruction(SmaliParser.SGetByteInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#intToFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIntToFloatInstruction(SmaliParser.IntToFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#intToFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIntToFloatInstruction(SmaliParser.IntToFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddFloatInstruction(SmaliParser.AddFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddFloatInstruction(SmaliParser.AddFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddIntInstruction(SmaliParser.AddIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddIntInstruction(SmaliParser.AddIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#cmplFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterCmplFloatInstruction(SmaliParser.CmplFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#cmplFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitCmplFloatInstruction(SmaliParser.CmplFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifEqInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfEqInstruction(SmaliParser.IfEqInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifEqInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfEqInstruction(SmaliParser.IfEqInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#iputInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIputInstruction(SmaliParser.IputInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#iputInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIputInstruction(SmaliParser.IputInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterConstWideInstruction(SmaliParser.ConstWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitConstWideInstruction(SmaliParser.ConstWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#intType}.
     *
     * @param ctx the parse tree
     */
    void enterIntType(SmaliParser.IntTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#intType}.
     *
     * @param ctx the parse tree
     */
    void exitIntType(SmaliParser.IntTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arrayRegister}.
     *
     * @param ctx the parse tree
     */
    void enterArrayRegister(SmaliParser.ArrayRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arrayRegister}.
     *
     * @param ctx the parse tree
     */
    void exitArrayRegister(SmaliParser.ArrayRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokePolymorphicRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokePolymorphicRangeInstruction(SmaliParser.InvokePolymorphicRangeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokePolymorphicRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokePolymorphicRangeInstruction(SmaliParser.InvokePolymorphicRangeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#iputCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIputCharInstruction(SmaliParser.IputCharInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#iputCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIputCharInstruction(SmaliParser.IputCharInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#hexFloatLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterHexFloatLiteral(SmaliParser.HexFloatLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#hexFloatLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitHexFloatLiteral(SmaliParser.HexFloatLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#subLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSubLongInstruction(SmaliParser.SubLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#subLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSubLongInstruction(SmaliParser.SubLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#intToLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIntToLongInstruction(SmaliParser.IntToLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#intToLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIntToLongInstruction(SmaliParser.IntToLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulFloatInstruction(SmaliParser.MulFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulFloatInstruction(SmaliParser.MulFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#goto32Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterGoto32Instruction(SmaliParser.Goto32InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#goto32Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitGoto32Instruction(SmaliParser.Goto32InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#subDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSubDouble2addrInstruction(SmaliParser.SubDouble2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#subDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSubDouble2addrInstruction(SmaliParser.SubDouble2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#hexNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterHexNumericLiteral(SmaliParser.HexNumericLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#hexNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitHexNumericLiteral(SmaliParser.HexNumericLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveObjectInstruction(SmaliParser.MoveObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveObjectInstruction(SmaliParser.MoveObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#iputWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIputWideInstruction(SmaliParser.IputWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#iputWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIputWideInstruction(SmaliParser.IputWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#nonArrayType}.
     *
     * @param ctx the parse tree
     */
    void enterNonArrayType(SmaliParser.NonArrayTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#nonArrayType}.
     *
     * @param ctx the parse tree
     */
    void exitNonArrayType(SmaliParser.NonArrayTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#instruction}.
     *
     * @param ctx the parse tree
     */
    void enterInstruction(SmaliParser.InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#instruction}.
     *
     * @param ctx the parse tree
     */
    void exitInstruction(SmaliParser.InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivFloat2addrInstruction(SmaliParser.DivFloat2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivFloat2addrInstruction(SmaliParser.DivFloat2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#checkInstanceType}.
     *
     * @param ctx the parse tree
     */
    void enterCheckInstanceType(SmaliParser.CheckInstanceTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#checkInstanceType}.
     *
     * @param ctx the parse tree
     */
    void exitCheckInstanceType(SmaliParser.CheckInstanceTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemLong2addrInstruction(SmaliParser.RemLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemLong2addrInstruction(SmaliParser.RemLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ushrIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterUshrIntLit8Instruction(SmaliParser.UshrIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ushrIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitUshrIntLit8Instruction(SmaliParser.UshrIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arrayElementRegisterRange}.
     *
     * @param ctx the parse tree
     */
    void enterArrayElementRegisterRange(SmaliParser.ArrayElementRegisterRangeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arrayElementRegisterRange}.
     *
     * @param ctx the parse tree
     */
    void exitArrayElementRegisterRange(SmaliParser.ArrayElementRegisterRangeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveWideInstruction(SmaliParser.MoveWideInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveWideInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveWideInstruction(SmaliParser.MoveWideInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#floatToDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterFloatToDoubleInstruction(SmaliParser.FloatToDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#floatToDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitFloatToDoubleInstruction(SmaliParser.FloatToDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifLeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfLeInstruction(SmaliParser.IfLeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifLeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfLeInstruction(SmaliParser.IfLeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddLong2addrInstruction(SmaliParser.AddLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddLong2addrInstruction(SmaliParser.AddLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#orIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterOrIntInstruction(SmaliParser.OrIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#orIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitOrIntInstruction(SmaliParser.OrIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#returnVoidInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterReturnVoidInstruction(SmaliParser.ReturnVoidInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#returnVoidInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitReturnVoidInstruction(SmaliParser.ReturnVoidInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#cmpgDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterCmpgDoubleInstruction(SmaliParser.CmpgDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#cmpgDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitCmpgDoubleInstruction(SmaliParser.CmpgDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#aputObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAputObjectInstruction(SmaliParser.AputObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#aputObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAputObjectInstruction(SmaliParser.AputObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#monitorEnterInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMonitorEnterInstruction(SmaliParser.MonitorEnterInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#monitorEnterInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMonitorEnterInstruction(SmaliParser.MonitorEnterInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivLong2addrInstruction(SmaliParser.DivLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivLong2addrInstruction(SmaliParser.DivLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveInstruction(SmaliParser.MoveInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveInstruction(SmaliParser.MoveInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shlInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterShlInt2addrInstruction(SmaliParser.ShlInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shlInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitShlInt2addrInstruction(SmaliParser.ShlInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#paramDirective}.
     *
     * @param ctx the parse tree
     */
    void enterParamDirective(SmaliParser.ParamDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#paramDirective}.
     *
     * @param ctx the parse tree
     */
    void exitParamDirective(SmaliParser.ParamDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulFloat2addrInstruction(SmaliParser.MulFloat2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulFloat2addrInstruction(SmaliParser.MulFloat2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeInterfaceRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeInterfaceRangeInstruction(SmaliParser.InvokeInterfaceRangeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeInterfaceRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeInterfaceRangeInstruction(SmaliParser.InvokeInterfaceRangeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#longToIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterLongToIntInstruction(SmaliParser.LongToIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#longToIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitLongToIntInstruction(SmaliParser.LongToIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#negDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNegDoubleInstruction(SmaliParser.NegDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#negDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNegDoubleInstruction(SmaliParser.NegDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#longType}.
     *
     * @param ctx the parse tree
     */
    void enterLongType(SmaliParser.LongTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#longType}.
     *
     * @param ctx the parse tree
     */
    void exitLongType(SmaliParser.LongTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#classDirective}.
     *
     * @param ctx the parse tree
     */
    void enterClassDirective(SmaliParser.ClassDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#classDirective}.
     *
     * @param ctx the parse tree
     */
    void exitClassDirective(SmaliParser.ClassDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arrayDataDirective}.
     *
     * @param ctx the parse tree
     */
    void enterArrayDataDirective(SmaliParser.ArrayDataDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arrayDataDirective}.
     *
     * @param ctx the parse tree
     */
    void exitArrayDataDirective(SmaliParser.ArrayDataDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#binaryNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterBinaryNumericLiteral(SmaliParser.BinaryNumericLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#binaryNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitBinaryNumericLiteral(SmaliParser.BinaryNumericLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#floatType}.
     *
     * @param ctx the parse tree
     */
    void enterFloatType(SmaliParser.FloatTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#floatType}.
     *
     * @param ctx the parse tree
     */
    void exitFloatType(SmaliParser.FloatTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#longToDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterLongToDoubleInstruction(SmaliParser.LongToDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#longToDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitLongToDoubleInstruction(SmaliParser.LongToDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#methodBodyStatement}.
     *
     * @param ctx the parse tree
     */
    void enterMethodBodyStatement(SmaliParser.MethodBodyStatementContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#methodBodyStatement}.
     *
     * @param ctx the parse tree
     */
    void exitMethodBodyStatement(SmaliParser.MethodBodyStatementContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#instanceRegister}.
     *
     * @param ctx the parse tree
     */
    void enterInstanceRegister(SmaliParser.InstanceRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#instanceRegister}.
     *
     * @param ctx the parse tree
     */
    void exitInstanceRegister(SmaliParser.InstanceRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sPutCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSPutCharInstruction(SmaliParser.SPutCharInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sPutCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSPutCharInstruction(SmaliParser.SPutCharInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemInt2addrInstruction(SmaliParser.RemInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemInt2addrInstruction(SmaliParser.RemInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulDouble2addrInstruction(SmaliParser.MulDouble2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulDouble2addrInstruction(SmaliParser.MulDouble2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeCustomRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeCustomRangeInstruction(SmaliParser.InvokeCustomRangeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeCustomRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeCustomRangeInstruction(SmaliParser.InvokeCustomRangeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#checkCastInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterCheckCastInstruction(SmaliParser.CheckCastInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#checkCastInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitCheckCastInstruction(SmaliParser.CheckCastInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeConstMethodTypeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeConstMethodTypeInstruction(SmaliParser.InvokeConstMethodTypeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeConstMethodTypeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeConstMethodTypeInstruction(SmaliParser.InvokeConstMethodTypeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arrayLengthInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterArrayLengthInstruction(SmaliParser.ArrayLengthInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arrayLengthInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitArrayLengthInstruction(SmaliParser.ArrayLengthInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#arraySizeRegister}.
     *
     * @param ctx the parse tree
     */
    void enterArraySizeRegister(SmaliParser.ArraySizeRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#arraySizeRegister}.
     *
     * @param ctx the parse tree
     */
    void exitArraySizeRegister(SmaliParser.ArraySizeRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#returnObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterReturnObjectInstruction(SmaliParser.ReturnObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#returnObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitReturnObjectInstruction(SmaliParser.ReturnObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemFloatInstruction(SmaliParser.RemFloatInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remFloatInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemFloatInstruction(SmaliParser.RemFloatInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#aputCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAputCharInstruction(SmaliParser.AputCharInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#aputCharInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAputCharInstruction(SmaliParser.AputCharInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constWide32Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterConstWide32Instruction(SmaliParser.ConstWide32InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constWide32Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitConstWide32Instruction(SmaliParser.ConstWide32InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#localDirectiveType}.
     *
     * @param ctx the parse tree
     */
    void enterLocalDirectiveType(SmaliParser.LocalDirectiveTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#localDirectiveType}.
     *
     * @param ctx the parse tree
     */
    void exitLocalDirectiveType(SmaliParser.LocalDirectiveTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifGtzInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfGtzInstruction(SmaliParser.IfGtzInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifGtzInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfGtzInstruction(SmaliParser.IfGtzInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#newInstanceInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNewInstanceInstruction(SmaliParser.NewInstanceInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#newInstanceInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNewInstanceInstruction(SmaliParser.NewInstanceInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveObject16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveObject16Instruction(SmaliParser.MoveObject16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveObject16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveObject16Instruction(SmaliParser.MoveObject16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulInt2addrInstruction(SmaliParser.MulInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulInt2addrInstruction(SmaliParser.MulInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#annotationScope}.
     *
     * @param ctx the parse tree
     */
    void enterAnnotationScope(SmaliParser.AnnotationScopeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#annotationScope}.
     *
     * @param ctx the parse tree
     */
    void exitAnnotationScope(SmaliParser.AnnotationScopeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#goto16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterGoto16Instruction(SmaliParser.Goto16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#goto16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitGoto16Instruction(SmaliParser.Goto16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#doubleToLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDoubleToLongInstruction(SmaliParser.DoubleToLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#doubleToLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDoubleToLongInstruction(SmaliParser.DoubleToLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#assignableValue}.
     *
     * @param ctx the parse tree
     */
    void enterAssignableValue(SmaliParser.AssignableValueContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#assignableValue}.
     *
     * @param ctx the parse tree
     */
    void exitAssignableValue(SmaliParser.AssignableValueContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#invokeVirtualRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterInvokeVirtualRangeInstruction(SmaliParser.InvokeVirtualRangeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#invokeVirtualRangeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitInvokeVirtualRangeInstruction(SmaliParser.InvokeVirtualRangeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#xorInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterXorInt2addrInstruction(SmaliParser.XorInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#xorInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitXorInt2addrInstruction(SmaliParser.XorInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constClass}.
     *
     * @param ctx the parse tree
     */
    void enterConstClass(SmaliParser.ConstClassContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constClass}.
     *
     * @param ctx the parse tree
     */
    void exitConstClass(SmaliParser.ConstClassContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#fieldName}.
     *
     * @param ctx the parse tree
     */
    void enterFieldName(SmaliParser.FieldNameContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#fieldName}.
     *
     * @param ctx the parse tree
     */
    void exitFieldName(SmaliParser.FieldNameContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#notIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNotIntInstruction(SmaliParser.NotIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#notIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNotIntInstruction(SmaliParser.NotIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#monitorExitInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMonitorExitInstruction(SmaliParser.MonitorExitInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#monitorExitInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMonitorExitInstruction(SmaliParser.MonitorExitInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivDoubleInstruction(SmaliParser.DivDoubleInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divDoubleInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivDoubleInstruction(SmaliParser.DivDoubleInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#andInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAndInt2addrInstruction(SmaliParser.AndInt2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#andInt2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAndInt2addrInstruction(SmaliParser.AndInt2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#agetByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAgetByteInstruction(SmaliParser.AgetByteInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#agetByteInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAgetByteInstruction(SmaliParser.AgetByteInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shlIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterShlIntLit8Instruction(SmaliParser.ShlIntLit8InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shlIntLit8Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitShlIntLit8Instruction(SmaliParser.ShlIntLit8InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#mulLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMulLongInstruction(SmaliParser.MulLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#mulLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMulLongInstruction(SmaliParser.MulLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#registerList}.
     *
     * @param ctx the parse tree
     */
    void enterRegisterList(SmaliParser.RegisterListContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#registerList}.
     *
     * @param ctx the parse tree
     */
    void exitRegisterList(SmaliParser.RegisterListContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#fieldNameAndType}.
     *
     * @param ctx the parse tree
     */
    void enterFieldNameAndType(SmaliParser.FieldNameAndTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#fieldNameAndType}.
     *
     * @param ctx the parse tree
     */
    void exitFieldNameAndType(SmaliParser.FieldNameAndTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#packedSwitchInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterPackedSwitchInstruction(SmaliParser.PackedSwitchInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#packedSwitchInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitPackedSwitchInstruction(SmaliParser.PackedSwitchInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#packedSwitchIdent}.
     *
     * @param ctx the parse tree
     */
    void enterPackedSwitchIdent(SmaliParser.PackedSwitchIdentContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#packedSwitchIdent}.
     *
     * @param ctx the parse tree
     */
    void exitPackedSwitchIdent(SmaliParser.PackedSwitchIdentContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#doubleToIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDoubleToIntInstruction(SmaliParser.DoubleToIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#doubleToIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDoubleToIntInstruction(SmaliParser.DoubleToIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#shrLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterShrLong2addrInstruction(SmaliParser.ShrLong2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#shrLong2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitShrLong2addrInstruction(SmaliParser.ShrLong2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sparseSwitchLabel}.
     *
     * @param ctx the parse tree
     */
    void enterSparseSwitchLabel(SmaliParser.SparseSwitchLabelContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sparseSwitchLabel}.
     *
     * @param ctx the parse tree
     */
    void exitSparseSwitchLabel(SmaliParser.SparseSwitchLabelContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#fieldInvocationTarget}.
     *
     * @param ctx the parse tree
     */
    void enterFieldInvocationTarget(SmaliParser.FieldInvocationTargetContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#fieldInvocationTarget}.
     *
     * @param ctx the parse tree
     */
    void exitFieldInvocationTarget(SmaliParser.FieldInvocationTargetContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#localRestartDirective}.
     *
     * @param ctx the parse tree
     */
    void enterLocalRestartDirective(SmaliParser.LocalRestartDirectiveContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#localRestartDirective}.
     *
     * @param ctx the parse tree
     */
    void exitLocalRestartDirective(SmaliParser.LocalRestartDirectiveContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#primitiveType}.
     *
     * @param ctx the parse tree
     */
    void enterPrimitiveType(SmaliParser.PrimitiveTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#primitiveType}.
     *
     * @param ctx the parse tree
     */
    void exitPrimitiveType(SmaliParser.PrimitiveTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#subFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSubFloat2addrInstruction(SmaliParser.SubFloat2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#subFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSubFloat2addrInstruction(SmaliParser.SubFloat2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#octNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterOctNumericLiteral(SmaliParser.OctNumericLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#octNumericLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitOctNumericLiteral(SmaliParser.OctNumericLiteralContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#moveExceptionInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterMoveExceptionInstruction(SmaliParser.MoveExceptionInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#moveExceptionInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitMoveExceptionInstruction(SmaliParser.MoveExceptionInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#leftRegister}.
     *
     * @param ctx the parse tree
     */
    void enterLeftRegister(SmaliParser.LeftRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#leftRegister}.
     *
     * @param ctx the parse tree
     */
    void exitLeftRegister(SmaliParser.LeftRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#xorLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterXorLongInstruction(SmaliParser.XorLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#xorLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitXorLongInstruction(SmaliParser.XorLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifLtInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfLtInstruction(SmaliParser.IfLtInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifLtInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfLtInstruction(SmaliParser.IfLtInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sPutInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSPutInstruction(SmaliParser.SPutInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sPutInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSPutInstruction(SmaliParser.SPutInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#negIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNegIntInstruction(SmaliParser.NegIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#negIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNegIntInstruction(SmaliParser.NegIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#catchExceptionType}.
     *
     * @param ctx the parse tree
     */
    void enterCatchExceptionType(SmaliParser.CatchExceptionTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#catchExceptionType}.
     *
     * @param ctx the parse tree
     */
    void exitCatchExceptionType(SmaliParser.CatchExceptionTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#remLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterRemLongInstruction(SmaliParser.RemLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#remLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitRemLongInstruction(SmaliParser.RemLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#annotationValueScoped}.
     *
     * @param ctx the parse tree
     */
    void enterAnnotationValueScoped(SmaliParser.AnnotationValueScopedContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#annotationValueScoped}.
     *
     * @param ctx the parse tree
     */
    void exitAnnotationValueScoped(SmaliParser.AnnotationValueScopedContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#localDirectiveRegister}.
     *
     * @param ctx the parse tree
     */
    void enterLocalDirectiveRegister(SmaliParser.LocalDirectiveRegisterContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#localDirectiveRegister}.
     *
     * @param ctx the parse tree
     */
    void exitLocalDirectiveRegister(SmaliParser.LocalDirectiveRegisterContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#notLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterNotLongInstruction(SmaliParser.NotLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#notLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitNotLongInstruction(SmaliParser.NotLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#andIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAndIntInstruction(SmaliParser.AndIntInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#andIntInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAndIntInstruction(SmaliParser.AndIntInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#divLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterDivLongInstruction(SmaliParser.DivLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#divLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitDivLongInstruction(SmaliParser.DivLongInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sparseSwitchInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSparseSwitchInstruction(SmaliParser.SparseSwitchInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sparseSwitchInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSparseSwitchInstruction(SmaliParser.SparseSwitchInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifNeInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfNeInstruction(SmaliParser.IfNeInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifNeInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfNeInstruction(SmaliParser.IfNeInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#addFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterAddFloat2addrInstruction(SmaliParser.AddFloat2addrInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#addFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitAddFloat2addrInstruction(SmaliParser.AddFloat2addrInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sPutBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSPutBooleanInstruction(SmaliParser.SPutBooleanInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sPutBooleanInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSPutBooleanInstruction(SmaliParser.SPutBooleanInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#constWide16Instruction}.
     *
     * @param ctx the parse tree
     */
    void enterConstWide16Instruction(SmaliParser.ConstWide16InstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#constWide16Instruction}.
     *
     * @param ctx the parse tree
     */
    void exitConstWide16Instruction(SmaliParser.ConstWide16InstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#sPutObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterSPutObjectInstruction(SmaliParser.SPutObjectInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#sPutObjectInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitSPutObjectInstruction(SmaliParser.SPutObjectInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ifLezInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterIfLezInstruction(SmaliParser.IfLezInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ifLezInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitIfLezInstruction(SmaliParser.IfLezInstructionContext ctx);

    /**
     * Enter a parse tree produced by {@link SmaliParser#ushrLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void enterUshrLongInstruction(SmaliParser.UshrLongInstructionContext ctx);

    /**
     * Exit a parse tree produced by {@link SmaliParser#ushrLongInstruction}.
     *
     * @param ctx the parse tree
     */
    void exitUshrLongInstruction(SmaliParser.UshrLongInstructionContext ctx);
}
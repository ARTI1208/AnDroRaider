package ru.art2000.androraider.analyzer.antlr;// Generated from D:\Coding\Antlr\SmaliParser.g4 by ANTLR 4.1

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SmaliParser}.
 */
public interface SmaliParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SmaliParser#negFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNegFloatInstruction(SmaliParser.NegFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#negFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNegFloatInstruction(@NotNull SmaliParser.NegFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shrLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterShrLongInstruction(@NotNull SmaliParser.ShrLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shrLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitShrLongInstruction(@NotNull SmaliParser.ShrLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodReturnType}.
	 * @param ctx the parse tree
	 */
	void enterMethodReturnType(@NotNull SmaliParser.MethodReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodReturnType}.
	 * @param ctx the parse tree
	 */
	void exitMethodReturnType(@NotNull SmaliParser.MethodReturnTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#fieldDirective}.
	 * @param ctx the parse tree
	 */
	void enterFieldDirective(@NotNull SmaliParser.FieldDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#fieldDirective}.
	 * @param ctx the parse tree
	 */
	void exitFieldDirective(@NotNull SmaliParser.FieldDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#newArrayInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayInstruction(@NotNull SmaliParser.NewArrayInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#newArrayInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayInstruction(@NotNull SmaliParser.NewArrayInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#agetInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAgetInstruction(@NotNull SmaliParser.AgetInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#agetInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAgetInstruction(@NotNull SmaliParser.AgetInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodDirective}.
	 * @param ctx the parse tree
	 */
	void enterMethodDirective(@NotNull SmaliParser.MethodDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodDirective}.
	 * @param ctx the parse tree
	 */
	void exitMethodDirective(@NotNull SmaliParser.MethodDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#throwInstruction}.
	 * @param ctx the parse tree
	 */
	void enterThrowInstruction(@NotNull SmaliParser.ThrowInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#throwInstruction}.
	 * @param ctx the parse tree
	 */
	void exitThrowInstruction(@NotNull SmaliParser.ThrowInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#iputBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIputBooleanInstruction(@NotNull SmaliParser.IputBooleanInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#iputBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIputBooleanInstruction(@NotNull SmaliParser.IputBooleanInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#intToDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIntToDoubleInstruction(@NotNull SmaliParser.IntToDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#intToDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIntToDoubleInstruction(@NotNull SmaliParser.IntToDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveResultInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveResultInstruction(@NotNull SmaliParser.MoveResultInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveResultInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveResultInstruction(@NotNull SmaliParser.MoveResultInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#packedSwitchDirective}.
	 * @param ctx the parse tree
	 */
	void enterPackedSwitchDirective(@NotNull SmaliParser.PackedSwitchDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#packedSwitchDirective}.
	 * @param ctx the parse tree
	 */
	void exitPackedSwitchDirective(@NotNull SmaliParser.PackedSwitchDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#igetBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIgetBooleanInstruction(@NotNull SmaliParser.IgetBooleanInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#igetBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIgetBooleanInstruction(@NotNull SmaliParser.IgetBooleanInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#igetCharInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIgetCharInstruction(@NotNull SmaliParser.IgetCharInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#igetCharInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIgetCharInstruction(@NotNull SmaliParser.IgetCharInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#gotoInstruction}.
	 * @param ctx the parse tree
	 */
	void enterGotoInstruction(@NotNull SmaliParser.GotoInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#gotoInstruction}.
	 * @param ctx the parse tree
	 */
	void exitGotoInstruction(@NotNull SmaliParser.GotoInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRemFloat2addrInstruction(@NotNull SmaliParser.RemFloat2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRemFloat2addrInstruction(@NotNull SmaliParser.RemFloat2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#binaryInstruction}.
	 * @param ctx the parse tree
	 */
	void enterBinaryInstruction(@NotNull SmaliParser.BinaryInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#binaryInstruction}.
	 * @param ctx the parse tree
	 */
	void exitBinaryInstruction(@NotNull SmaliParser.BinaryInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#rsubIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRsubIntInstruction(@NotNull SmaliParser.RsubIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#rsubIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRsubIntInstruction(@NotNull SmaliParser.RsubIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#annotationDirective}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationDirective(@NotNull SmaliParser.AnnotationDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#annotationDirective}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationDirective(@NotNull SmaliParser.AnnotationDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#const16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterConst16Instruction(@NotNull SmaliParser.Const16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#const16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitConst16Instruction(@NotNull SmaliParser.Const16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumericLiteral(@NotNull SmaliParser.NumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#numericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumericLiteral(@NotNull SmaliParser.NumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#charType}.
	 * @param ctx the parse tree
	 */
	void enterCharType(@NotNull SmaliParser.CharTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#charType}.
	 * @param ctx the parse tree
	 */
	void exitCharType(@NotNull SmaliParser.CharTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#annotationField}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationField(@NotNull SmaliParser.AnnotationFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#annotationField}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationField(@NotNull SmaliParser.AnnotationFieldContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(@NotNull SmaliParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(@NotNull SmaliParser.ArrayTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#aputByteInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAputByteInstruction(@NotNull SmaliParser.AputByteInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#aputByteInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAputByteInstruction(@NotNull SmaliParser.AputByteInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#superDirective}.
	 * @param ctx the parse tree
	 */
	void enterSuperDirective(@NotNull SmaliParser.SuperDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#superDirective}.
	 * @param ctx the parse tree
	 */
	void exitSuperDirective(@NotNull SmaliParser.SuperDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#subDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSubDoubleInstruction(@NotNull SmaliParser.SubDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#subDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSubDoubleInstruction(@NotNull SmaliParser.SubDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#doubleToFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDoubleToFloatInstruction(@NotNull SmaliParser.DoubleToFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#doubleToFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDoubleToFloatInstruction(@NotNull SmaliParser.DoubleToFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#andLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAndLongInstruction(@NotNull SmaliParser.AndLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#andLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAndLongInstruction(@NotNull SmaliParser.AndLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shlLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterShlLongInstruction(@NotNull SmaliParser.ShlLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shlLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitShlLongInstruction(@NotNull SmaliParser.ShlLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#catchGotoLabel}.
	 * @param ctx the parse tree
	 */
	void enterCatchGotoLabel(@NotNull SmaliParser.CatchGotoLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#catchGotoLabel}.
	 * @param ctx the parse tree
	 */
	void exitCatchGotoLabel(@NotNull SmaliParser.CatchGotoLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#returnWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterReturnWideInstruction(@NotNull SmaliParser.ReturnWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#returnWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitReturnWideInstruction(@NotNull SmaliParser.ReturnWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#floatToIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterFloatToIntInstruction(@NotNull SmaliParser.FloatToIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#floatToIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitFloatToIntInstruction(@NotNull SmaliParser.FloatToIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sPutByteInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSPutByteInstruction(@NotNull SmaliParser.SPutByteInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sPutByteInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSPutByteInstruction(@NotNull SmaliParser.SPutByteInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifEqzInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfEqzInstruction(@NotNull SmaliParser.IfEqzInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifEqzInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfEqzInstruction(@NotNull SmaliParser.IfEqzInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveResultObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveResultObjectInstruction(@NotNull SmaliParser.MoveResultObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveResultObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveResultObjectInstruction(@NotNull SmaliParser.MoveResultObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifLtzInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfLtzInstruction(@NotNull SmaliParser.IfLtzInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifLtzInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfLtzInstruction(@NotNull SmaliParser.IfLtzInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterDivIntLit16Instruction(@NotNull SmaliParser.DivIntLit16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitDivIntLit16Instruction(@NotNull SmaliParser.DivIntLit16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterAddIntLit16Instruction(@NotNull SmaliParser.AddIntLit16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitAddIntLit16Instruction(@NotNull SmaliParser.AddIntLit16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#registerRange}.
	 * @param ctx the parse tree
	 */
	void enterRegisterRange(@NotNull SmaliParser.RegisterRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#registerRange}.
	 * @param ctx the parse tree
	 */
	void exitRegisterRange(@NotNull SmaliParser.RegisterRangeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#fieldType}.
	 * @param ctx the parse tree
	 */
	void enterFieldType(@NotNull SmaliParser.FieldTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#fieldType}.
	 * @param ctx the parse tree
	 */
	void exitFieldType(@NotNull SmaliParser.FieldTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodParameterType}.
	 * @param ctx the parse tree
	 */
	void enterMethodParameterType(@NotNull SmaliParser.MethodParameterTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodParameterType}.
	 * @param ctx the parse tree
	 */
	void exitMethodParameterType(@NotNull SmaliParser.MethodParameterTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#checkCastType}.
	 * @param ctx the parse tree
	 */
	void enterCheckCastType(@NotNull SmaliParser.CheckCastTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#checkCastType}.
	 * @param ctx the parse tree
	 */
	void exitCheckCastType(@NotNull SmaliParser.CheckCastTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sourceRegister}.
	 * @param ctx the parse tree
	 */
	void enterSourceRegister(@NotNull SmaliParser.SourceRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sourceRegister}.
	 * @param ctx the parse tree
	 */
	void exitSourceRegister(@NotNull SmaliParser.SourceRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceType(@NotNull SmaliParser.ReferenceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#referenceType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceType(@NotNull SmaliParser.ReferenceTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeStaticInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeStaticInstruction(@NotNull SmaliParser.InvokeStaticInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeStaticInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeStaticInstruction(@NotNull SmaliParser.InvokeStaticInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#instanceOfInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInstanceOfInstruction(@NotNull SmaliParser.InstanceOfInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#instanceOfInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInstanceOfInstruction(@NotNull SmaliParser.InstanceOfInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#aputInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAputInstruction(@NotNull SmaliParser.AputInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#aputInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAputInstruction(@NotNull SmaliParser.AputInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#igetWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIgetWideInstruction(@NotNull SmaliParser.IgetWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#igetWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIgetWideInstruction(@NotNull SmaliParser.IgetWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#floatNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFloatNumericLiteral(@NotNull SmaliParser.FloatNumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#floatNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFloatNumericLiteral(@NotNull SmaliParser.FloatNumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#igetObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIgetObjectInstruction(@NotNull SmaliParser.IgetObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#igetObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIgetObjectInstruction(@NotNull SmaliParser.IgetObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#xorIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterXorIntInstruction(@NotNull SmaliParser.XorIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#xorIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitXorIntInstruction(@NotNull SmaliParser.XorIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#labelName}.
	 * @param ctx the parse tree
	 */
	void enterLabelName(@NotNull SmaliParser.LabelNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#labelName}.
	 * @param ctx the parse tree
	 */
	void exitLabelName(@NotNull SmaliParser.LabelNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#registerIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterRegisterIdentifier(@NotNull SmaliParser.RegisterIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#registerIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitRegisterIdentifier(@NotNull SmaliParser.RegisterIdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#floatToLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterFloatToLongInstruction(@NotNull SmaliParser.FloatToLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#floatToLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitFloatToLongInstruction(@NotNull SmaliParser.FloatToLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#annotationFieldValue}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationFieldValue(@NotNull SmaliParser.AnnotationFieldValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#annotationFieldValue}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationFieldValue(@NotNull SmaliParser.AnnotationFieldValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMulLong2addrInstruction(@NotNull SmaliParser.MulLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMulLong2addrInstruction(@NotNull SmaliParser.MulLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arrayElementRegisters}.
	 * @param ctx the parse tree
	 */
	void enterArrayElementRegisters(@NotNull SmaliParser.ArrayElementRegistersContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arrayElementRegisters}.
	 * @param ctx the parse tree
	 */
	void exitArrayElementRegisters(@NotNull SmaliParser.ArrayElementRegistersContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveWide16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveWide16Instruction(@NotNull SmaliParser.MoveWide16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveWide16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveWide16Instruction(@NotNull SmaliParser.MoveWide16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterRemIntLit8Instruction(@NotNull SmaliParser.RemIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitRemIntLit8Instruction(@NotNull SmaliParser.RemIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sPutWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSPutWideInstruction(@NotNull SmaliParser.SPutWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sPutWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSPutWideInstruction(@NotNull SmaliParser.SPutWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#agetObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAgetObjectInstruction(@NotNull SmaliParser.AgetObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#agetObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAgetObjectInstruction(@NotNull SmaliParser.AgetObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAddDoubleInstruction(@NotNull SmaliParser.AddDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAddDoubleInstruction(@NotNull SmaliParser.AddDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodIdentifier}.
	 * @param ctx the parse tree
	 */
	void enterMethodIdentifier(@NotNull SmaliParser.MethodIdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodIdentifier}.
	 * @param ctx the parse tree
	 */
	void exitMethodIdentifier(@NotNull SmaliParser.MethodIdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#positiveNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterPositiveNumericLiteral(@NotNull SmaliParser.PositiveNumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#positiveNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitPositiveNumericLiteral(@NotNull SmaliParser.PositiveNumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#intToCharInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIntToCharInstruction(@NotNull SmaliParser.IntToCharInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#intToCharInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIntToCharInstruction(@NotNull SmaliParser.IntToCharInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constWideHigh16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterConstWideHigh16Instruction(@NotNull SmaliParser.ConstWideHigh16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constWideHigh16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitConstWideHigh16Instruction(@NotNull SmaliParser.ConstWideHigh16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ushrLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterUshrLong2addrInstruction(@NotNull SmaliParser.UshrLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ushrLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitUshrLong2addrInstruction(@NotNull SmaliParser.UshrLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeSuperInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeSuperInstruction(@NotNull SmaliParser.InvokeSuperInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeSuperInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeSuperInstruction(@NotNull SmaliParser.InvokeSuperInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#aputWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAputWideInstruction(@NotNull SmaliParser.AputWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#aputWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAputWideInstruction(@NotNull SmaliParser.AputWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeCustomInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeCustomInstruction(@NotNull SmaliParser.InvokeCustomInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeCustomInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeCustomInstruction(@NotNull SmaliParser.InvokeCustomInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#superName}.
	 * @param ctx the parse tree
	 */
	void enterSuperName(@NotNull SmaliParser.SuperNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#superName}.
	 * @param ctx the parse tree
	 */
	void exitSuperName(@NotNull SmaliParser.SuperNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAddLongInstruction(@NotNull SmaliParser.AddLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAddLongInstruction(@NotNull SmaliParser.AddLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#anyType}.
	 * @param ctx the parse tree
	 */
	void enterAnyType(@NotNull SmaliParser.AnyTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#anyType}.
	 * @param ctx the parse tree
	 */
	void exitAnyType(@NotNull SmaliParser.AnyTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterMulIntLit16Instruction(@NotNull SmaliParser.MulIntLit16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitMulIntLit16Instruction(@NotNull SmaliParser.MulIntLit16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#extendedParamDirective}.
	 * @param ctx the parse tree
	 */
	void enterExtendedParamDirective(@NotNull SmaliParser.ExtendedParamDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#extendedParamDirective}.
	 * @param ctx the parse tree
	 */
	void exitExtendedParamDirective(@NotNull SmaliParser.ExtendedParamDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#fillArrayDataInstruction}.
	 * @param ctx the parse tree
	 */
	void enterFillArrayDataInstruction(@NotNull SmaliParser.FillArrayDataInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#fillArrayDataInstruction}.
	 * @param ctx the parse tree
	 */
	void exitFillArrayDataInstruction(@NotNull SmaliParser.FillArrayDataInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#localsDirective}.
	 * @param ctx the parse tree
	 */
	void enterLocalsDirective(@NotNull SmaliParser.LocalsDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#localsDirective}.
	 * @param ctx the parse tree
	 */
	void exitLocalsDirective(@NotNull SmaliParser.LocalsDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constHigh16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterConstHigh16Instruction(@NotNull SmaliParser.ConstHigh16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constHigh16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitConstHigh16Instruction(@NotNull SmaliParser.ConstHigh16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#referenceOrArrayType}.
	 * @param ctx the parse tree
	 */
	void enterReferenceOrArrayType(@NotNull SmaliParser.ReferenceOrArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#referenceOrArrayType}.
	 * @param ctx the parse tree
	 */
	void exitReferenceOrArrayType(@NotNull SmaliParser.ReferenceOrArrayTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sparseSwitchDirective}.
	 * @param ctx the parse tree
	 */
	void enterSparseSwitchDirective(@NotNull SmaliParser.SparseSwitchDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sparseSwitchDirective}.
	 * @param ctx the parse tree
	 */
	void exitSparseSwitchDirective(@NotNull SmaliParser.SparseSwitchDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shrIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterShrIntInstruction(@NotNull SmaliParser.ShrIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shrIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitShrIntInstruction(@NotNull SmaliParser.ShrIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#intToShortInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIntToShortInstruction(@NotNull SmaliParser.IntToShortInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#intToShortInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIntToShortInstruction(@NotNull SmaliParser.IntToShortInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifGezInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfGezInstruction(@NotNull SmaliParser.IfGezInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifGezInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfGezInstruction(@NotNull SmaliParser.IfGezInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#subLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSubLong2addrInstruction(@NotNull SmaliParser.SubLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#subLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSubLong2addrInstruction(@NotNull SmaliParser.SubLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#subFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSubFloatInstruction(@NotNull SmaliParser.SubFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#subFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSubFloatInstruction(@NotNull SmaliParser.SubFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeConstMethodHandleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeConstMethodHandleInstruction(@NotNull SmaliParser.InvokeConstMethodHandleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeConstMethodHandleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeConstMethodHandleInstruction(@NotNull SmaliParser.InvokeConstMethodHandleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#orLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterOrLong2addrInstruction(@NotNull SmaliParser.OrLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#orLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitOrLong2addrInstruction(@NotNull SmaliParser.OrLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDivFloatInstruction(@NotNull SmaliParser.DivFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDivFloatInstruction(@NotNull SmaliParser.DivFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#catchDirective}.
	 * @param ctx the parse tree
	 */
	void enterCatchDirective(@NotNull SmaliParser.CatchDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#catchDirective}.
	 * @param ctx the parse tree
	 */
	void exitCatchDirective(@NotNull SmaliParser.CatchDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#igetShortInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIgetShortInstruction(@NotNull SmaliParser.IgetShortInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#igetShortInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIgetShortInstruction(@NotNull SmaliParser.IgetShortInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#voidType}.
	 * @param ctx the parse tree
	 */
	void enterVoidType(@NotNull SmaliParser.VoidTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#voidType}.
	 * @param ctx the parse tree
	 */
	void exitVoidType(@NotNull SmaliParser.VoidTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(@NotNull SmaliParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#booleanLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(@NotNull SmaliParser.BooleanLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodModifier}.
	 * @param ctx the parse tree
	 */
	void enterMethodModifier(@NotNull SmaliParser.MethodModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodModifier}.
	 * @param ctx the parse tree
	 */
	void exitMethodModifier(@NotNull SmaliParser.MethodModifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeDirectRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeDirectRangeInstruction(@NotNull SmaliParser.InvokeDirectRangeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeDirectRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeDirectRangeInstruction(@NotNull SmaliParser.InvokeDirectRangeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifGeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfGeInstruction(@NotNull SmaliParser.IfGeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifGeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfGeInstruction(@NotNull SmaliParser.IfGeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeSuperRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeSuperRangeInstruction(@NotNull SmaliParser.InvokeSuperRangeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeSuperRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeSuperRangeInstruction(@NotNull SmaliParser.InvokeSuperRangeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveFrom16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveFrom16Instruction(@NotNull SmaliParser.MoveFrom16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveFrom16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveFrom16Instruction(@NotNull SmaliParser.MoveFrom16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#targetRegister}.
	 * @param ctx the parse tree
	 */
	void enterTargetRegister(@NotNull SmaliParser.TargetRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#targetRegister}.
	 * @param ctx the parse tree
	 */
	void exitTargetRegister(@NotNull SmaliParser.TargetRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ternaryInstruction}.
	 * @param ctx the parse tree
	 */
	void enterTernaryInstruction(@NotNull SmaliParser.TernaryInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ternaryInstruction}.
	 * @param ctx the parse tree
	 */
	void exitTernaryInstruction(@NotNull SmaliParser.TernaryInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#xorIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterXorIntLit16Instruction(@NotNull SmaliParser.XorIntLit16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#xorIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitXorIntLit16Instruction(@NotNull SmaliParser.XorIntLit16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#newInstanceType}.
	 * @param ctx the parse tree
	 */
	void enterNewInstanceType(@NotNull SmaliParser.NewInstanceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#newInstanceType}.
	 * @param ctx the parse tree
	 */
	void exitNewInstanceType(@NotNull SmaliParser.NewInstanceTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDivIntInstruction(@NotNull SmaliParser.DivIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDivIntInstruction(@NotNull SmaliParser.DivIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRemDouble2addrInstruction(@NotNull SmaliParser.RemDouble2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRemDouble2addrInstruction(@NotNull SmaliParser.RemDouble2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#catchFromLabel}.
	 * @param ctx the parse tree
	 */
	void enterCatchFromLabel(@NotNull SmaliParser.CatchFromLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#catchFromLabel}.
	 * @param ctx the parse tree
	 */
	void exitCatchFromLabel(@NotNull SmaliParser.CatchFromLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#registerListRegisters}.
	 * @param ctx the parse tree
	 */
	void enterRegisterListRegisters(@NotNull SmaliParser.RegisterListRegistersContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#registerListRegisters}.
	 * @param ctx the parse tree
	 */
	void exitRegisterListRegisters(@NotNull SmaliParser.RegisterListRegistersContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#longToFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterLongToFloatInstruction(@NotNull SmaliParser.LongToFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#longToFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitLongToFloatInstruction(@NotNull SmaliParser.LongToFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#igetByteInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIgetByteInstruction(@NotNull SmaliParser.IgetByteInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#igetByteInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIgetByteInstruction(@NotNull SmaliParser.IgetByteInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#indexRegister}.
	 * @param ctx the parse tree
	 */
	void enterIndexRegister(@NotNull SmaliParser.IndexRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#indexRegister}.
	 * @param ctx the parse tree
	 */
	void exitIndexRegister(@NotNull SmaliParser.IndexRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#simpleParamDirective}.
	 * @param ctx the parse tree
	 */
	void enterSimpleParamDirective(@NotNull SmaliParser.SimpleParamDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#simpleParamDirective}.
	 * @param ctx the parse tree
	 */
	void exitSimpleParamDirective(@NotNull SmaliParser.SimpleParamDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#andLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAndLong2addrInstruction(@NotNull SmaliParser.AndLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#andLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAndLong2addrInstruction(@NotNull SmaliParser.AndLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeDirectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeDirectInstruction(@NotNull SmaliParser.InvokeDirectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeDirectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeDirectInstruction(@NotNull SmaliParser.InvokeDirectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arrayDataEntry}.
	 * @param ctx the parse tree
	 */
	void enterArrayDataEntry(@NotNull SmaliParser.ArrayDataEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arrayDataEntry}.
	 * @param ctx the parse tree
	 */
	void exitArrayDataEntry(@NotNull SmaliParser.ArrayDataEntryContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#lineLabel}.
	 * @param ctx the parse tree
	 */
	void enterLineLabel(@NotNull SmaliParser.LineLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#lineLabel}.
	 * @param ctx the parse tree
	 */
	void exitLineLabel(@NotNull SmaliParser.LineLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constInstruction}.
	 * @param ctx the parse tree
	 */
	void enterConstInstruction(@NotNull SmaliParser.ConstInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constInstruction}.
	 * @param ctx the parse tree
	 */
	void exitConstInstruction(@NotNull SmaliParser.ConstInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#filledArrayDataLabel}.
	 * @param ctx the parse tree
	 */
	void enterFilledArrayDataLabel(@NotNull SmaliParser.FilledArrayDataLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#filledArrayDataLabel}.
	 * @param ctx the parse tree
	 */
	void exitFilledArrayDataLabel(@NotNull SmaliParser.FilledArrayDataLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#doubleType}.
	 * @param ctx the parse tree
	 */
	void enterDoubleType(@NotNull SmaliParser.DoubleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#doubleType}.
	 * @param ctx the parse tree
	 */
	void exitDoubleType(@NotNull SmaliParser.DoubleTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterRemIntLit16Instruction(@NotNull SmaliParser.RemIntLit16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitRemIntLit16Instruction(@NotNull SmaliParser.RemIntLit16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabels}.
	 * @param ctx the parse tree
	 */
	void enterPackedSwitchDirectiveLabels(@NotNull SmaliParser.PackedSwitchDirectiveLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabels}.
	 * @param ctx the parse tree
	 */
	void exitPackedSwitchDirectiveLabels(@NotNull SmaliParser.PackedSwitchDirectiveLabelsContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodInvocationTarget}.
	 * @param ctx the parse tree
	 */
	void enterMethodInvocationTarget(@NotNull SmaliParser.MethodInvocationTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodInvocationTarget}.
	 * @param ctx the parse tree
	 */
	void exitMethodInvocationTarget(@NotNull SmaliParser.MethodInvocationTargetContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(@NotNull SmaliParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(@NotNull SmaliParser.MethodDeclarationContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(@NotNull SmaliParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(@NotNull SmaliParser.MethodBodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#iputObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIputObjectInstruction(@NotNull SmaliParser.IputObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#iputObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIputObjectInstruction(@NotNull SmaliParser.IputObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#cmplDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterCmplDoubleInstruction(@NotNull SmaliParser.CmplDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#cmplDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitCmplDoubleInstruction(@NotNull SmaliParser.CmplDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shortType}.
	 * @param ctx the parse tree
	 */
	void enterShortType(@NotNull SmaliParser.ShortTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shortType}.
	 * @param ctx the parse tree
	 */
	void exitShortType(@NotNull SmaliParser.ShortTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDivInt2addrInstruction(@NotNull SmaliParser.DivInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDivInt2addrInstruction(@NotNull SmaliParser.DivInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#filledNewArrayInstruction}.
	 * @param ctx the parse tree
	 */
	void enterFilledNewArrayInstruction(@NotNull SmaliParser.FilledNewArrayInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#filledNewArrayInstruction}.
	 * @param ctx the parse tree
	 */
	void exitFilledNewArrayInstruction(@NotNull SmaliParser.FilledNewArrayInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDivDouble2addrInstruction(@NotNull SmaliParser.DivDouble2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDivDouble2addrInstruction(@NotNull SmaliParser.DivDouble2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#byteType}.
	 * @param ctx the parse tree
	 */
	void enterByteType(@NotNull SmaliParser.ByteTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#byteType}.
	 * @param ctx the parse tree
	 */
	void exitByteType(@NotNull SmaliParser.ByteTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#igetInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIgetInstruction(@NotNull SmaliParser.IgetInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#igetInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIgetInstruction(@NotNull SmaliParser.IgetInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#packedSwitchRegister}.
	 * @param ctx the parse tree
	 */
	void enterPackedSwitchRegister(@NotNull SmaliParser.PackedSwitchRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#packedSwitchRegister}.
	 * @param ctx the parse tree
	 */
	void exitPackedSwitchRegister(@NotNull SmaliParser.PackedSwitchRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeStaticRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeStaticRangeInstruction(@NotNull SmaliParser.InvokeStaticRangeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeStaticRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeStaticRangeInstruction(@NotNull SmaliParser.InvokeStaticRangeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#lineDirective}.
	 * @param ctx the parse tree
	 */
	void enterLineDirective(@NotNull SmaliParser.LineDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#lineDirective}.
	 * @param ctx the parse tree
	 */
	void exitLineDirective(@NotNull SmaliParser.LineDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodParameterLiteral}.
	 * @param ctx the parse tree
	 */
	void enterMethodParameterLiteral(@NotNull SmaliParser.MethodParameterLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodParameterLiteral}.
	 * @param ctx the parse tree
	 */
	void exitMethodParameterLiteral(@NotNull SmaliParser.MethodParameterLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifGtInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfGtInstruction(@NotNull SmaliParser.IfGtInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifGtInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfGtInstruction(@NotNull SmaliParser.IfGtInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#orIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterOrIntLit8Instruction(@NotNull SmaliParser.OrIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#orIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitOrIntLit8Instruction(@NotNull SmaliParser.OrIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arrayElementType}.
	 * @param ctx the parse tree
	 */
	void enterArrayElementType(@NotNull SmaliParser.ArrayElementTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arrayElementType}.
	 * @param ctx the parse tree
	 */
	void exitArrayElementType(@NotNull SmaliParser.ArrayElementTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#intToByteInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIntToByteInstruction(@NotNull SmaliParser.IntToByteInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#intToByteInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIntToByteInstruction(@NotNull SmaliParser.IntToByteInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull SmaliParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull SmaliParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#fieldModifier}.
	 * @param ctx the parse tree
	 */
	void enterFieldModifier(@NotNull SmaliParser.FieldModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#fieldModifier}.
	 * @param ctx the parse tree
	 */
	void exitFieldModifier(@NotNull SmaliParser.FieldModifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#move16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterMove16Instruction(@NotNull SmaliParser.Move16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#move16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitMove16Instruction(@NotNull SmaliParser.Move16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterDivIntLit8Instruction(@NotNull SmaliParser.DivIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitDivIntLit8Instruction(@NotNull SmaliParser.DivIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#xorLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterXorLong2addrInstruction(@NotNull SmaliParser.XorLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#xorLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitXorLong2addrInstruction(@NotNull SmaliParser.XorLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#agetShortInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAgetShortInstruction(@NotNull SmaliParser.AgetShortInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#agetShortInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAgetShortInstruction(@NotNull SmaliParser.AgetShortInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#andIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterAndIntLit8Instruction(@NotNull SmaliParser.AndIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#andIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitAndIntLit8Instruction(@NotNull SmaliParser.AndIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#cmpgFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterCmpgFloatInstruction(@NotNull SmaliParser.CmpgFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#cmpgFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitCmpgFloatInstruction(@NotNull SmaliParser.CmpgFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#nullLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNullLiteral(@NotNull SmaliParser.NullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#nullLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNullLiteral(@NotNull SmaliParser.NullLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveWideFrom16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveWideFrom16Instruction(@NotNull SmaliParser.MoveWideFrom16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveWideFrom16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveWideFrom16Instruction(@NotNull SmaliParser.MoveWideFrom16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#subInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSubInt2addrInstruction(@NotNull SmaliParser.SubInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#subInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSubInt2addrInstruction(@NotNull SmaliParser.SubInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#orInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterOrInt2addrInstruction(@NotNull SmaliParser.OrInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#orInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitOrInt2addrInstruction(@NotNull SmaliParser.OrInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(@NotNull SmaliParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(@NotNull SmaliParser.LabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(@NotNull SmaliParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(@NotNull SmaliParser.ParseContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAddDouble2addrInstruction(@NotNull SmaliParser.AddDouble2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAddDouble2addrInstruction(@NotNull SmaliParser.AddDouble2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#localDirective}.
	 * @param ctx the parse tree
	 */
	void enterLocalDirective(@NotNull SmaliParser.LocalDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#localDirective}.
	 * @param ctx the parse tree
	 */
	void exitLocalDirective(@NotNull SmaliParser.LocalDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#locaDirectiveVariableName}.
	 * @param ctx the parse tree
	 */
	void enterLocaDirectiveVariableName(@NotNull SmaliParser.LocaDirectiveVariableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#locaDirectiveVariableName}.
	 * @param ctx the parse tree
	 */
	void exitLocaDirectiveVariableName(@NotNull SmaliParser.LocaDirectiveVariableNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#packedSwitchLabel}.
	 * @param ctx the parse tree
	 */
	void enterPackedSwitchLabel(@NotNull SmaliParser.PackedSwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#packedSwitchLabel}.
	 * @param ctx the parse tree
	 */
	void exitPackedSwitchLabel(@NotNull SmaliParser.PackedSwitchLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sparseSwitchDirectiveValue}.
	 * @param ctx the parse tree
	 */
	void enterSparseSwitchDirectiveValue(@NotNull SmaliParser.SparseSwitchDirectiveValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sparseSwitchDirectiveValue}.
	 * @param ctx the parse tree
	 */
	void exitSparseSwitchDirectiveValue(@NotNull SmaliParser.SparseSwitchDirectiveValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#aputShortInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAputShortInstruction(@NotNull SmaliParser.AputShortInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#aputShortInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAputShortInstruction(@NotNull SmaliParser.AputShortInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sGetBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSGetBooleanInstruction(@NotNull SmaliParser.SGetBooleanInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sGetBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSGetBooleanInstruction(@NotNull SmaliParser.SGetBooleanInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifNezInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfNezInstruction(@NotNull SmaliParser.IfNezInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifNezInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfNezInstruction(@NotNull SmaliParser.IfNezInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMulIntInstruction(@NotNull SmaliParser.MulIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMulIntInstruction(@NotNull SmaliParser.MulIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#iputShortInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIputShortInstruction(@NotNull SmaliParser.IputShortInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#iputShortInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIputShortInstruction(@NotNull SmaliParser.IputShortInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sourceName}.
	 * @param ctx the parse tree
	 */
	void enterSourceName(@NotNull SmaliParser.SourceNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sourceName}.
	 * @param ctx the parse tree
	 */
	void exitSourceName(@NotNull SmaliParser.SourceNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterAddIntLit8Instruction(@NotNull SmaliParser.AddIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitAddIntLit8Instruction(@NotNull SmaliParser.AddIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMulDoubleInstruction(@NotNull SmaliParser.MulDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMulDoubleInstruction(@NotNull SmaliParser.MulDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokePolymorphicInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokePolymorphicInstruction(@NotNull SmaliParser.InvokePolymorphicInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokePolymorphicInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokePolymorphicInstruction(@NotNull SmaliParser.InvokePolymorphicInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabel}.
	 * @param ctx the parse tree
	 */
	void enterPackedSwitchDirectiveLabel(@NotNull SmaliParser.PackedSwitchDirectiveLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabel}.
	 * @param ctx the parse tree
	 */
	void exitPackedSwitchDirectiveLabel(@NotNull SmaliParser.PackedSwitchDirectiveLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#nonVoidType}.
	 * @param ctx the parse tree
	 */
	void enterNonVoidType(@NotNull SmaliParser.NonVoidTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#nonVoidType}.
	 * @param ctx the parse tree
	 */
	void exitNonVoidType(@NotNull SmaliParser.NonVoidTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#nopInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNopInstruction(@NotNull SmaliParser.NopInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#nopInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNopInstruction(@NotNull SmaliParser.NopInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#catchAllDirective}.
	 * @param ctx the parse tree
	 */
	void enterCatchAllDirective(@NotNull SmaliParser.CatchAllDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#catchAllDirective}.
	 * @param ctx the parse tree
	 */
	void exitCatchAllDirective(@NotNull SmaliParser.CatchAllDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sGetInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSGetInstruction(@NotNull SmaliParser.SGetInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sGetInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSGetInstruction(@NotNull SmaliParser.SGetInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifLabel}.
	 * @param ctx the parse tree
	 */
	void enterIfLabel(@NotNull SmaliParser.IfLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifLabel}.
	 * @param ctx the parse tree
	 */
	void exitIfLabel(@NotNull SmaliParser.IfLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sGetWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSGetWideInstruction(@NotNull SmaliParser.SGetWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sGetWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSGetWideInstruction(@NotNull SmaliParser.SGetWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#rightRegister}.
	 * @param ctx the parse tree
	 */
	void enterRightRegister(@NotNull SmaliParser.RightRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#rightRegister}.
	 * @param ctx the parse tree
	 */
	void exitRightRegister(@NotNull SmaliParser.RightRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodSignature}.
	 * @param ctx the parse tree
	 */
	void enterMethodSignature(@NotNull SmaliParser.MethodSignatureContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodSignature}.
	 * @param ctx the parse tree
	 */
	void exitMethodSignature(@NotNull SmaliParser.MethodSignatureContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#negLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNegLongInstruction(@NotNull SmaliParser.NegLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#negLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNegLongInstruction(@NotNull SmaliParser.NegLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveObjectFrom16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveObjectFrom16Instruction(@NotNull SmaliParser.MoveObjectFrom16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveObjectFrom16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveObjectFrom16Instruction(@NotNull SmaliParser.MoveObjectFrom16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodArguments}.
	 * @param ctx the parse tree
	 */
	void enterMethodArguments(@NotNull SmaliParser.MethodArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodArguments}.
	 * @param ctx the parse tree
	 */
	void exitMethodArguments(@NotNull SmaliParser.MethodArgumentsContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#subIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSubIntInstruction(@NotNull SmaliParser.SubIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#subIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSubIntInstruction(@NotNull SmaliParser.SubIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#catchToLabel}.
	 * @param ctx the parse tree
	 */
	void enterCatchToLabel(@NotNull SmaliParser.CatchToLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#catchToLabel}.
	 * @param ctx the parse tree
	 */
	void exitCatchToLabel(@NotNull SmaliParser.CatchToLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#orLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterOrLongInstruction(@NotNull SmaliParser.OrLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#orLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitOrLongInstruction(@NotNull SmaliParser.OrLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(@NotNull SmaliParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(@NotNull SmaliParser.IdentifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#negativeNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNegativeNumericLiteral(@NotNull SmaliParser.NegativeNumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#negativeNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNegativeNumericLiteral(@NotNull SmaliParser.NegativeNumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#booleanType}.
	 * @param ctx the parse tree
	 */
	void enterBooleanType(@NotNull SmaliParser.BooleanTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#booleanType}.
	 * @param ctx the parse tree
	 */
	void exitBooleanType(@NotNull SmaliParser.BooleanTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterMulIntLit8Instruction(@NotNull SmaliParser.MulIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitMulIntLit8Instruction(@NotNull SmaliParser.MulIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAddInt2addrInstruction(@NotNull SmaliParser.AddInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAddInt2addrInstruction(@NotNull SmaliParser.AddInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shrInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterShrInt2addrInstruction(@NotNull SmaliParser.ShrInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shrInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitShrInt2addrInstruction(@NotNull SmaliParser.ShrInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#agetWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAgetWideInstruction(@NotNull SmaliParser.AgetWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#agetWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAgetWideInstruction(@NotNull SmaliParser.AgetWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#annotationType}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationType(@NotNull SmaliParser.AnnotationTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#annotationType}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationType(@NotNull SmaliParser.AnnotationTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#const4Instruction}.
	 * @param ctx the parse tree
	 */
	void enterConst4Instruction(@NotNull SmaliParser.Const4InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#const4Instruction}.
	 * @param ctx the parse tree
	 */
	void exitConst4Instruction(@NotNull SmaliParser.Const4InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#iputByteInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIputByteInstruction(@NotNull SmaliParser.IputByteInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#iputByteInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIputByteInstruction(@NotNull SmaliParser.IputByteInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRemDoubleInstruction(@NotNull SmaliParser.RemDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRemDoubleInstruction(@NotNull SmaliParser.RemDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constString}.
	 * @param ctx the parse tree
	 */
	void enterConstString(@NotNull SmaliParser.ConstStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constString}.
	 * @param ctx the parse tree
	 */
	void exitConstString(@NotNull SmaliParser.ConstStringContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sGetCharInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSGetCharInstruction(@NotNull SmaliParser.SGetCharInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sGetCharInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSGetCharInstruction(@NotNull SmaliParser.SGetCharInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ushrInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterUshrInt2addrInstruction(@NotNull SmaliParser.UshrInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ushrInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitUshrInt2addrInstruction(@NotNull SmaliParser.UshrInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constStringJumbo}.
	 * @param ctx the parse tree
	 */
	void enterConstStringJumbo(@NotNull SmaliParser.ConstStringJumboContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constStringJumbo}.
	 * @param ctx the parse tree
	 */
	void exitConstStringJumbo(@NotNull SmaliParser.ConstStringJumboContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(@NotNull SmaliParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(@NotNull SmaliParser.FieldContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(@NotNull SmaliParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(@NotNull SmaliParser.StringLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#returnInstruction}.
	 * @param ctx the parse tree
	 */
	void enterReturnInstruction(@NotNull SmaliParser.ReturnInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#returnInstruction}.
	 * @param ctx the parse tree
	 */
	void exitReturnInstruction(@NotNull SmaliParser.ReturnInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeVirtualInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeVirtualInstruction(@NotNull SmaliParser.InvokeVirtualInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeVirtualInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeVirtualInstruction(@NotNull SmaliParser.InvokeVirtualInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shlLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterShlLong2addrInstruction(@NotNull SmaliParser.ShlLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shlLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitShlLong2addrInstruction(@NotNull SmaliParser.ShlLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shlIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterShlIntInstruction(@NotNull SmaliParser.ShlIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shlIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitShlIntInstruction(@NotNull SmaliParser.ShlIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ushrIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterUshrIntInstruction(@NotNull SmaliParser.UshrIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ushrIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitUshrIntInstruction(@NotNull SmaliParser.UshrIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sGetObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSGetObjectInstruction(@NotNull SmaliParser.SGetObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sGetObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSGetObjectInstruction(@NotNull SmaliParser.SGetObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#filledNewArrayRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterFilledNewArrayRangeInstruction(@NotNull SmaliParser.FilledNewArrayRangeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#filledNewArrayRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitFilledNewArrayRangeInstruction(@NotNull SmaliParser.FilledNewArrayRangeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sGetShortInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSGetShortInstruction(@NotNull SmaliParser.SGetShortInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sGetShortInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSGetShortInstruction(@NotNull SmaliParser.SGetShortInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#andIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterAndIntLit16Instruction(@NotNull SmaliParser.AndIntLit16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#andIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitAndIntLit16Instruction(@NotNull SmaliParser.AndIntLit16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#xorIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterXorIntLit8Instruction(@NotNull SmaliParser.XorIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#xorIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitXorIntLit8Instruction(@NotNull SmaliParser.XorIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sourceDirective}.
	 * @param ctx the parse tree
	 */
	void enterSourceDirective(@NotNull SmaliParser.SourceDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sourceDirective}.
	 * @param ctx the parse tree
	 */
	void exitSourceDirective(@NotNull SmaliParser.SourceDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#orIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterOrIntLit16Instruction(@NotNull SmaliParser.OrIntLit16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#orIntLit16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitOrIntLit16Instruction(@NotNull SmaliParser.OrIntLit16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void enterClassModifier(@NotNull SmaliParser.ClassModifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#classModifier}.
	 * @param ctx the parse tree
	 */
	void exitClassModifier(@NotNull SmaliParser.ClassModifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#localEndDirective}.
	 * @param ctx the parse tree
	 */
	void enterLocalEndDirective(@NotNull SmaliParser.LocalEndDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#localEndDirective}.
	 * @param ctx the parse tree
	 */
	void exitLocalEndDirective(@NotNull SmaliParser.LocalEndDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#localDirectiveGenericHint}.
	 * @param ctx the parse tree
	 */
	void enterLocalDirectiveGenericHint(@NotNull SmaliParser.LocalDirectiveGenericHintContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#localDirectiveGenericHint}.
	 * @param ctx the parse tree
	 */
	void exitLocalDirectiveGenericHint(@NotNull SmaliParser.LocalDirectiveGenericHintContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shrIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterShrIntLit8Instruction(@NotNull SmaliParser.ShrIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shrIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitShrIntLit8Instruction(@NotNull SmaliParser.ShrIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sPutShortInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSPutShortInstruction(@NotNull SmaliParser.SPutShortInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sPutShortInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSPutShortInstruction(@NotNull SmaliParser.SPutShortInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#aputBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAputBooleanInstruction(@NotNull SmaliParser.AputBooleanInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#aputBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAputBooleanInstruction(@NotNull SmaliParser.AputBooleanInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeInterfaceInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeInterfaceInstruction(@NotNull SmaliParser.InvokeInterfaceInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeInterfaceInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeInterfaceInstruction(@NotNull SmaliParser.InvokeInterfaceInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#cmpLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterCmpLongInstruction(@NotNull SmaliParser.CmpLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#cmpLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitCmpLongInstruction(@NotNull SmaliParser.CmpLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(@NotNull SmaliParser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(@NotNull SmaliParser.ClassNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#agetCharInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAgetCharInstruction(@NotNull SmaliParser.AgetCharInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#agetCharInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAgetCharInstruction(@NotNull SmaliParser.AgetCharInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#agetBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAgetBooleanInstruction(@NotNull SmaliParser.AgetBooleanInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#agetBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAgetBooleanInstruction(@NotNull SmaliParser.AgetBooleanInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sparseSwitchRegister}.
	 * @param ctx the parse tree
	 */
	void enterSparseSwitchRegister(@NotNull SmaliParser.SparseSwitchRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sparseSwitchRegister}.
	 * @param ctx the parse tree
	 */
	void exitSparseSwitchRegister(@NotNull SmaliParser.SparseSwitchRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveResultWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveResultWideInstruction(@NotNull SmaliParser.MoveResultWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveResultWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveResultWideInstruction(@NotNull SmaliParser.MoveResultWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#rsubIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterRsubIntLit8Instruction(@NotNull SmaliParser.RsubIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#rsubIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitRsubIntLit8Instruction(@NotNull SmaliParser.RsubIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#decimalNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDecimalNumericLiteral(@NotNull SmaliParser.DecimalNumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#decimalNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDecimalNumericLiteral(@NotNull SmaliParser.DecimalNumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRemIntInstruction(@NotNull SmaliParser.RemIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRemIntInstruction(@NotNull SmaliParser.RemIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#instanceField}.
	 * @param ctx the parse tree
	 */
	void enterInstanceField(@NotNull SmaliParser.InstanceFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#instanceField}.
	 * @param ctx the parse tree
	 */
	void exitInstanceField(@NotNull SmaliParser.InstanceFieldContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#registersDirective}.
	 * @param ctx the parse tree
	 */
	void enterRegistersDirective(@NotNull SmaliParser.RegistersDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#registersDirective}.
	 * @param ctx the parse tree
	 */
	void exitRegistersDirective(@NotNull SmaliParser.RegistersDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sGetByteInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSGetByteInstruction(@NotNull SmaliParser.SGetByteInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sGetByteInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSGetByteInstruction(@NotNull SmaliParser.SGetByteInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#intToFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIntToFloatInstruction(@NotNull SmaliParser.IntToFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#intToFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIntToFloatInstruction(@NotNull SmaliParser.IntToFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAddFloatInstruction(@NotNull SmaliParser.AddFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAddFloatInstruction(@NotNull SmaliParser.AddFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAddIntInstruction(@NotNull SmaliParser.AddIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAddIntInstruction(@NotNull SmaliParser.AddIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#cmplFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterCmplFloatInstruction(@NotNull SmaliParser.CmplFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#cmplFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitCmplFloatInstruction(@NotNull SmaliParser.CmplFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifEqInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfEqInstruction(@NotNull SmaliParser.IfEqInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifEqInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfEqInstruction(@NotNull SmaliParser.IfEqInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#iputInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIputInstruction(@NotNull SmaliParser.IputInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#iputInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIputInstruction(@NotNull SmaliParser.IputInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterConstWideInstruction(@NotNull SmaliParser.ConstWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitConstWideInstruction(@NotNull SmaliParser.ConstWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#intType}.
	 * @param ctx the parse tree
	 */
	void enterIntType(@NotNull SmaliParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#intType}.
	 * @param ctx the parse tree
	 */
	void exitIntType(@NotNull SmaliParser.IntTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arrayRegister}.
	 * @param ctx the parse tree
	 */
	void enterArrayRegister(@NotNull SmaliParser.ArrayRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arrayRegister}.
	 * @param ctx the parse tree
	 */
	void exitArrayRegister(@NotNull SmaliParser.ArrayRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokePolymorphicRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokePolymorphicRangeInstruction(@NotNull SmaliParser.InvokePolymorphicRangeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokePolymorphicRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokePolymorphicRangeInstruction(@NotNull SmaliParser.InvokePolymorphicRangeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#iputCharInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIputCharInstruction(@NotNull SmaliParser.IputCharInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#iputCharInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIputCharInstruction(@NotNull SmaliParser.IputCharInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#hexFloatLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHexFloatLiteral(@NotNull SmaliParser.HexFloatLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#hexFloatLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHexFloatLiteral(@NotNull SmaliParser.HexFloatLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#subLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSubLongInstruction(@NotNull SmaliParser.SubLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#subLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSubLongInstruction(@NotNull SmaliParser.SubLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#intToLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIntToLongInstruction(@NotNull SmaliParser.IntToLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#intToLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIntToLongInstruction(@NotNull SmaliParser.IntToLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMulFloatInstruction(@NotNull SmaliParser.MulFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMulFloatInstruction(@NotNull SmaliParser.MulFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#goto32Instruction}.
	 * @param ctx the parse tree
	 */
	void enterGoto32Instruction(@NotNull SmaliParser.Goto32InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#goto32Instruction}.
	 * @param ctx the parse tree
	 */
	void exitGoto32Instruction(@NotNull SmaliParser.Goto32InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#subDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSubDouble2addrInstruction(@NotNull SmaliParser.SubDouble2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#subDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSubDouble2addrInstruction(@NotNull SmaliParser.SubDouble2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#hexNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterHexNumericLiteral(@NotNull SmaliParser.HexNumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#hexNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitHexNumericLiteral(@NotNull SmaliParser.HexNumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveObjectInstruction(@NotNull SmaliParser.MoveObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveObjectInstruction(@NotNull SmaliParser.MoveObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#iputWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIputWideInstruction(@NotNull SmaliParser.IputWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#iputWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIputWideInstruction(@NotNull SmaliParser.IputWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#nonArrayType}.
	 * @param ctx the parse tree
	 */
	void enterNonArrayType(@NotNull SmaliParser.NonArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#nonArrayType}.
	 * @param ctx the parse tree
	 */
	void exitNonArrayType(@NotNull SmaliParser.NonArrayTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(@NotNull SmaliParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(@NotNull SmaliParser.InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDivFloat2addrInstruction(@NotNull SmaliParser.DivFloat2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDivFloat2addrInstruction(@NotNull SmaliParser.DivFloat2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#checkInstanceType}.
	 * @param ctx the parse tree
	 */
	void enterCheckInstanceType(@NotNull SmaliParser.CheckInstanceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#checkInstanceType}.
	 * @param ctx the parse tree
	 */
	void exitCheckInstanceType(@NotNull SmaliParser.CheckInstanceTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRemLong2addrInstruction(@NotNull SmaliParser.RemLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRemLong2addrInstruction(@NotNull SmaliParser.RemLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ushrIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterUshrIntLit8Instruction(@NotNull SmaliParser.UshrIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ushrIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitUshrIntLit8Instruction(@NotNull SmaliParser.UshrIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arrayElementRegisterRange}.
	 * @param ctx the parse tree
	 */
	void enterArrayElementRegisterRange(@NotNull SmaliParser.ArrayElementRegisterRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arrayElementRegisterRange}.
	 * @param ctx the parse tree
	 */
	void exitArrayElementRegisterRange(@NotNull SmaliParser.ArrayElementRegisterRangeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveWideInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveWideInstruction(@NotNull SmaliParser.MoveWideInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveWideInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveWideInstruction(@NotNull SmaliParser.MoveWideInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#floatToDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterFloatToDoubleInstruction(@NotNull SmaliParser.FloatToDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#floatToDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitFloatToDoubleInstruction(@NotNull SmaliParser.FloatToDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifLeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfLeInstruction(@NotNull SmaliParser.IfLeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifLeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfLeInstruction(@NotNull SmaliParser.IfLeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAddLong2addrInstruction(@NotNull SmaliParser.AddLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAddLong2addrInstruction(@NotNull SmaliParser.AddLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#orIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterOrIntInstruction(@NotNull SmaliParser.OrIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#orIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitOrIntInstruction(@NotNull SmaliParser.OrIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#returnVoidInstruction}.
	 * @param ctx the parse tree
	 */
	void enterReturnVoidInstruction(@NotNull SmaliParser.ReturnVoidInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#returnVoidInstruction}.
	 * @param ctx the parse tree
	 */
	void exitReturnVoidInstruction(@NotNull SmaliParser.ReturnVoidInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#cmpgDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterCmpgDoubleInstruction(@NotNull SmaliParser.CmpgDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#cmpgDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitCmpgDoubleInstruction(@NotNull SmaliParser.CmpgDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#aputObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAputObjectInstruction(@NotNull SmaliParser.AputObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#aputObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAputObjectInstruction(@NotNull SmaliParser.AputObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#monitorEnterInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMonitorEnterInstruction(@NotNull SmaliParser.MonitorEnterInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#monitorEnterInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMonitorEnterInstruction(@NotNull SmaliParser.MonitorEnterInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDivLong2addrInstruction(@NotNull SmaliParser.DivLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDivLong2addrInstruction(@NotNull SmaliParser.DivLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveInstruction(@NotNull SmaliParser.MoveInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveInstruction(@NotNull SmaliParser.MoveInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shlInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterShlInt2addrInstruction(@NotNull SmaliParser.ShlInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shlInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitShlInt2addrInstruction(@NotNull SmaliParser.ShlInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#paramDirective}.
	 * @param ctx the parse tree
	 */
	void enterParamDirective(@NotNull SmaliParser.ParamDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#paramDirective}.
	 * @param ctx the parse tree
	 */
	void exitParamDirective(@NotNull SmaliParser.ParamDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMulFloat2addrInstruction(@NotNull SmaliParser.MulFloat2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMulFloat2addrInstruction(@NotNull SmaliParser.MulFloat2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeInterfaceRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeInterfaceRangeInstruction(@NotNull SmaliParser.InvokeInterfaceRangeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeInterfaceRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeInterfaceRangeInstruction(@NotNull SmaliParser.InvokeInterfaceRangeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#longToIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterLongToIntInstruction(@NotNull SmaliParser.LongToIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#longToIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitLongToIntInstruction(@NotNull SmaliParser.LongToIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#negDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNegDoubleInstruction(@NotNull SmaliParser.NegDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#negDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNegDoubleInstruction(@NotNull SmaliParser.NegDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#longType}.
	 * @param ctx the parse tree
	 */
	void enterLongType(@NotNull SmaliParser.LongTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#longType}.
	 * @param ctx the parse tree
	 */
	void exitLongType(@NotNull SmaliParser.LongTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#classDirective}.
	 * @param ctx the parse tree
	 */
	void enterClassDirective(@NotNull SmaliParser.ClassDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#classDirective}.
	 * @param ctx the parse tree
	 */
	void exitClassDirective(@NotNull SmaliParser.ClassDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arrayDataDirective}.
	 * @param ctx the parse tree
	 */
	void enterArrayDataDirective(@NotNull SmaliParser.ArrayDataDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arrayDataDirective}.
	 * @param ctx the parse tree
	 */
	void exitArrayDataDirective(@NotNull SmaliParser.ArrayDataDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#binaryNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterBinaryNumericLiteral(@NotNull SmaliParser.BinaryNumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#binaryNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitBinaryNumericLiteral(@NotNull SmaliParser.BinaryNumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#floatType}.
	 * @param ctx the parse tree
	 */
	void enterFloatType(@NotNull SmaliParser.FloatTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#floatType}.
	 * @param ctx the parse tree
	 */
	void exitFloatType(@NotNull SmaliParser.FloatTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#longToDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterLongToDoubleInstruction(@NotNull SmaliParser.LongToDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#longToDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitLongToDoubleInstruction(@NotNull SmaliParser.LongToDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#methodBodyStatement}.
	 * @param ctx the parse tree
	 */
	void enterMethodBodyStatement(@NotNull SmaliParser.MethodBodyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#methodBodyStatement}.
	 * @param ctx the parse tree
	 */
	void exitMethodBodyStatement(@NotNull SmaliParser.MethodBodyStatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#instanceRegister}.
	 * @param ctx the parse tree
	 */
	void enterInstanceRegister(@NotNull SmaliParser.InstanceRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#instanceRegister}.
	 * @param ctx the parse tree
	 */
	void exitInstanceRegister(@NotNull SmaliParser.InstanceRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sPutCharInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSPutCharInstruction(@NotNull SmaliParser.SPutCharInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sPutCharInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSPutCharInstruction(@NotNull SmaliParser.SPutCharInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRemInt2addrInstruction(@NotNull SmaliParser.RemInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRemInt2addrInstruction(@NotNull SmaliParser.RemInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMulDouble2addrInstruction(@NotNull SmaliParser.MulDouble2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulDouble2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMulDouble2addrInstruction(@NotNull SmaliParser.MulDouble2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeCustomRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeCustomRangeInstruction(@NotNull SmaliParser.InvokeCustomRangeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeCustomRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeCustomRangeInstruction(@NotNull SmaliParser.InvokeCustomRangeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#checkCastInstruction}.
	 * @param ctx the parse tree
	 */
	void enterCheckCastInstruction(@NotNull SmaliParser.CheckCastInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#checkCastInstruction}.
	 * @param ctx the parse tree
	 */
	void exitCheckCastInstruction(@NotNull SmaliParser.CheckCastInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeConstMethodTypeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeConstMethodTypeInstruction(@NotNull SmaliParser.InvokeConstMethodTypeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeConstMethodTypeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeConstMethodTypeInstruction(@NotNull SmaliParser.InvokeConstMethodTypeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arrayLengthInstruction}.
	 * @param ctx the parse tree
	 */
	void enterArrayLengthInstruction(@NotNull SmaliParser.ArrayLengthInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arrayLengthInstruction}.
	 * @param ctx the parse tree
	 */
	void exitArrayLengthInstruction(@NotNull SmaliParser.ArrayLengthInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#arraySizeRegister}.
	 * @param ctx the parse tree
	 */
	void enterArraySizeRegister(@NotNull SmaliParser.ArraySizeRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#arraySizeRegister}.
	 * @param ctx the parse tree
	 */
	void exitArraySizeRegister(@NotNull SmaliParser.ArraySizeRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#returnObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterReturnObjectInstruction(@NotNull SmaliParser.ReturnObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#returnObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitReturnObjectInstruction(@NotNull SmaliParser.ReturnObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRemFloatInstruction(@NotNull SmaliParser.RemFloatInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remFloatInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRemFloatInstruction(@NotNull SmaliParser.RemFloatInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#aputCharInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAputCharInstruction(@NotNull SmaliParser.AputCharInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#aputCharInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAputCharInstruction(@NotNull SmaliParser.AputCharInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constWide32Instruction}.
	 * @param ctx the parse tree
	 */
	void enterConstWide32Instruction(@NotNull SmaliParser.ConstWide32InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constWide32Instruction}.
	 * @param ctx the parse tree
	 */
	void exitConstWide32Instruction(@NotNull SmaliParser.ConstWide32InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#localDirectiveType}.
	 * @param ctx the parse tree
	 */
	void enterLocalDirectiveType(@NotNull SmaliParser.LocalDirectiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#localDirectiveType}.
	 * @param ctx the parse tree
	 */
	void exitLocalDirectiveType(@NotNull SmaliParser.LocalDirectiveTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifGtzInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfGtzInstruction(@NotNull SmaliParser.IfGtzInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifGtzInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfGtzInstruction(@NotNull SmaliParser.IfGtzInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#newInstanceInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNewInstanceInstruction(@NotNull SmaliParser.NewInstanceInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#newInstanceInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNewInstanceInstruction(@NotNull SmaliParser.NewInstanceInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveObject16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveObject16Instruction(@NotNull SmaliParser.MoveObject16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveObject16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveObject16Instruction(@NotNull SmaliParser.MoveObject16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMulInt2addrInstruction(@NotNull SmaliParser.MulInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMulInt2addrInstruction(@NotNull SmaliParser.MulInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#annotationScope}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationScope(@NotNull SmaliParser.AnnotationScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#annotationScope}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationScope(@NotNull SmaliParser.AnnotationScopeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#goto16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterGoto16Instruction(@NotNull SmaliParser.Goto16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#goto16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitGoto16Instruction(@NotNull SmaliParser.Goto16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#doubleToLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDoubleToLongInstruction(@NotNull SmaliParser.DoubleToLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#doubleToLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDoubleToLongInstruction(@NotNull SmaliParser.DoubleToLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#assignableValue}.
	 * @param ctx the parse tree
	 */
	void enterAssignableValue(@NotNull SmaliParser.AssignableValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#assignableValue}.
	 * @param ctx the parse tree
	 */
	void exitAssignableValue(@NotNull SmaliParser.AssignableValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#invokeVirtualRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterInvokeVirtualRangeInstruction(@NotNull SmaliParser.InvokeVirtualRangeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#invokeVirtualRangeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitInvokeVirtualRangeInstruction(@NotNull SmaliParser.InvokeVirtualRangeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#xorInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterXorInt2addrInstruction(@NotNull SmaliParser.XorInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#xorInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitXorInt2addrInstruction(@NotNull SmaliParser.XorInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constClass}.
	 * @param ctx the parse tree
	 */
	void enterConstClass(@NotNull SmaliParser.ConstClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constClass}.
	 * @param ctx the parse tree
	 */
	void exitConstClass(@NotNull SmaliParser.ConstClassContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void enterFieldName(@NotNull SmaliParser.FieldNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#fieldName}.
	 * @param ctx the parse tree
	 */
	void exitFieldName(@NotNull SmaliParser.FieldNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#notIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNotIntInstruction(@NotNull SmaliParser.NotIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#notIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNotIntInstruction(@NotNull SmaliParser.NotIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#monitorExitInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMonitorExitInstruction(@NotNull SmaliParser.MonitorExitInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#monitorExitInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMonitorExitInstruction(@NotNull SmaliParser.MonitorExitInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDivDoubleInstruction(@NotNull SmaliParser.DivDoubleInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divDoubleInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDivDoubleInstruction(@NotNull SmaliParser.DivDoubleInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#andInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAndInt2addrInstruction(@NotNull SmaliParser.AndInt2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#andInt2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAndInt2addrInstruction(@NotNull SmaliParser.AndInt2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#agetByteInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAgetByteInstruction(@NotNull SmaliParser.AgetByteInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#agetByteInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAgetByteInstruction(@NotNull SmaliParser.AgetByteInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shlIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void enterShlIntLit8Instruction(@NotNull SmaliParser.ShlIntLit8InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shlIntLit8Instruction}.
	 * @param ctx the parse tree
	 */
	void exitShlIntLit8Instruction(@NotNull SmaliParser.ShlIntLit8InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#mulLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMulLongInstruction(@NotNull SmaliParser.MulLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#mulLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMulLongInstruction(@NotNull SmaliParser.MulLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#registerList}.
	 * @param ctx the parse tree
	 */
	void enterRegisterList(@NotNull SmaliParser.RegisterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#registerList}.
	 * @param ctx the parse tree
	 */
	void exitRegisterList(@NotNull SmaliParser.RegisterListContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#fieldNameAndType}.
	 * @param ctx the parse tree
	 */
	void enterFieldNameAndType(@NotNull SmaliParser.FieldNameAndTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#fieldNameAndType}.
	 * @param ctx the parse tree
	 */
	void exitFieldNameAndType(@NotNull SmaliParser.FieldNameAndTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#packedSwitchInstruction}.
	 * @param ctx the parse tree
	 */
	void enterPackedSwitchInstruction(@NotNull SmaliParser.PackedSwitchInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#packedSwitchInstruction}.
	 * @param ctx the parse tree
	 */
	void exitPackedSwitchInstruction(@NotNull SmaliParser.PackedSwitchInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#packedSwitchIdent}.
	 * @param ctx the parse tree
	 */
	void enterPackedSwitchIdent(@NotNull SmaliParser.PackedSwitchIdentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#packedSwitchIdent}.
	 * @param ctx the parse tree
	 */
	void exitPackedSwitchIdent(@NotNull SmaliParser.PackedSwitchIdentContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#doubleToIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDoubleToIntInstruction(@NotNull SmaliParser.DoubleToIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#doubleToIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDoubleToIntInstruction(@NotNull SmaliParser.DoubleToIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#shrLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterShrLong2addrInstruction(@NotNull SmaliParser.ShrLong2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#shrLong2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitShrLong2addrInstruction(@NotNull SmaliParser.ShrLong2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sparseSwitchLabel}.
	 * @param ctx the parse tree
	 */
	void enterSparseSwitchLabel(@NotNull SmaliParser.SparseSwitchLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sparseSwitchLabel}.
	 * @param ctx the parse tree
	 */
	void exitSparseSwitchLabel(@NotNull SmaliParser.SparseSwitchLabelContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#fieldInvocationTarget}.
	 * @param ctx the parse tree
	 */
	void enterFieldInvocationTarget(@NotNull SmaliParser.FieldInvocationTargetContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#fieldInvocationTarget}.
	 * @param ctx the parse tree
	 */
	void exitFieldInvocationTarget(@NotNull SmaliParser.FieldInvocationTargetContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#localRestartDirective}.
	 * @param ctx the parse tree
	 */
	void enterLocalRestartDirective(@NotNull SmaliParser.LocalRestartDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#localRestartDirective}.
	 * @param ctx the parse tree
	 */
	void exitLocalRestartDirective(@NotNull SmaliParser.LocalRestartDirectiveContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void enterPrimitiveType(@NotNull SmaliParser.PrimitiveTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#primitiveType}.
	 * @param ctx the parse tree
	 */
	void exitPrimitiveType(@NotNull SmaliParser.PrimitiveTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#subFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSubFloat2addrInstruction(@NotNull SmaliParser.SubFloat2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#subFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSubFloat2addrInstruction(@NotNull SmaliParser.SubFloat2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#octNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void enterOctNumericLiteral(@NotNull SmaliParser.OctNumericLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#octNumericLiteral}.
	 * @param ctx the parse tree
	 */
	void exitOctNumericLiteral(@NotNull SmaliParser.OctNumericLiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#moveExceptionInstruction}.
	 * @param ctx the parse tree
	 */
	void enterMoveExceptionInstruction(@NotNull SmaliParser.MoveExceptionInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#moveExceptionInstruction}.
	 * @param ctx the parse tree
	 */
	void exitMoveExceptionInstruction(@NotNull SmaliParser.MoveExceptionInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#leftRegister}.
	 * @param ctx the parse tree
	 */
	void enterLeftRegister(@NotNull SmaliParser.LeftRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#leftRegister}.
	 * @param ctx the parse tree
	 */
	void exitLeftRegister(@NotNull SmaliParser.LeftRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#xorLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterXorLongInstruction(@NotNull SmaliParser.XorLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#xorLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitXorLongInstruction(@NotNull SmaliParser.XorLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifLtInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfLtInstruction(@NotNull SmaliParser.IfLtInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifLtInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfLtInstruction(@NotNull SmaliParser.IfLtInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sPutInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSPutInstruction(@NotNull SmaliParser.SPutInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sPutInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSPutInstruction(@NotNull SmaliParser.SPutInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#negIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNegIntInstruction(@NotNull SmaliParser.NegIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#negIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNegIntInstruction(@NotNull SmaliParser.NegIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#catchExceptionType}.
	 * @param ctx the parse tree
	 */
	void enterCatchExceptionType(@NotNull SmaliParser.CatchExceptionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#catchExceptionType}.
	 * @param ctx the parse tree
	 */
	void exitCatchExceptionType(@NotNull SmaliParser.CatchExceptionTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#remLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterRemLongInstruction(@NotNull SmaliParser.RemLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#remLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitRemLongInstruction(@NotNull SmaliParser.RemLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#annotationValueScoped}.
	 * @param ctx the parse tree
	 */
	void enterAnnotationValueScoped(@NotNull SmaliParser.AnnotationValueScopedContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#annotationValueScoped}.
	 * @param ctx the parse tree
	 */
	void exitAnnotationValueScoped(@NotNull SmaliParser.AnnotationValueScopedContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#localDirectiveRegister}.
	 * @param ctx the parse tree
	 */
	void enterLocalDirectiveRegister(@NotNull SmaliParser.LocalDirectiveRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#localDirectiveRegister}.
	 * @param ctx the parse tree
	 */
	void exitLocalDirectiveRegister(@NotNull SmaliParser.LocalDirectiveRegisterContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#notLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterNotLongInstruction(@NotNull SmaliParser.NotLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#notLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitNotLongInstruction(@NotNull SmaliParser.NotLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#andIntInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAndIntInstruction(@NotNull SmaliParser.AndIntInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#andIntInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAndIntInstruction(@NotNull SmaliParser.AndIntInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#divLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterDivLongInstruction(@NotNull SmaliParser.DivLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#divLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitDivLongInstruction(@NotNull SmaliParser.DivLongInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sparseSwitchInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSparseSwitchInstruction(@NotNull SmaliParser.SparseSwitchInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sparseSwitchInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSparseSwitchInstruction(@NotNull SmaliParser.SparseSwitchInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifNeInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfNeInstruction(@NotNull SmaliParser.IfNeInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifNeInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfNeInstruction(@NotNull SmaliParser.IfNeInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#addFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void enterAddFloat2addrInstruction(@NotNull SmaliParser.AddFloat2addrInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#addFloat2addrInstruction}.
	 * @param ctx the parse tree
	 */
	void exitAddFloat2addrInstruction(@NotNull SmaliParser.AddFloat2addrInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sPutBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSPutBooleanInstruction(@NotNull SmaliParser.SPutBooleanInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sPutBooleanInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSPutBooleanInstruction(@NotNull SmaliParser.SPutBooleanInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#constWide16Instruction}.
	 * @param ctx the parse tree
	 */
	void enterConstWide16Instruction(@NotNull SmaliParser.ConstWide16InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#constWide16Instruction}.
	 * @param ctx the parse tree
	 */
	void exitConstWide16Instruction(@NotNull SmaliParser.ConstWide16InstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#sPutObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void enterSPutObjectInstruction(@NotNull SmaliParser.SPutObjectInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#sPutObjectInstruction}.
	 * @param ctx the parse tree
	 */
	void exitSPutObjectInstruction(@NotNull SmaliParser.SPutObjectInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ifLezInstruction}.
	 * @param ctx the parse tree
	 */
	void enterIfLezInstruction(@NotNull SmaliParser.IfLezInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ifLezInstruction}.
	 * @param ctx the parse tree
	 */
	void exitIfLezInstruction(@NotNull SmaliParser.IfLezInstructionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SmaliParser#ushrLongInstruction}.
	 * @param ctx the parse tree
	 */
	void enterUshrLongInstruction(@NotNull SmaliParser.UshrLongInstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmaliParser#ushrLongInstruction}.
	 * @param ctx the parse tree
	 */
	void exitUshrLongInstruction(@NotNull SmaliParser.UshrLongInstructionContext ctx);
}
package ru.art2000.androraider.analyzer.antlr;// Generated from D:\Coding\Antlr\SmaliParser.g4 by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SmaliParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SmaliParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SmaliParser#negFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegFloatInstruction(SmaliParser.NegFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shrLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShrLongInstruction(@NotNull SmaliParser.ShrLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodReturnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodReturnType(@NotNull SmaliParser.MethodReturnTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#fieldDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldDirective(@NotNull SmaliParser.FieldDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#newArrayInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArrayInstruction(@NotNull SmaliParser.NewArrayInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#agetInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgetInstruction(@NotNull SmaliParser.AgetInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDirective(@NotNull SmaliParser.MethodDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#throwInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThrowInstruction(@NotNull SmaliParser.ThrowInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#iputBooleanInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIputBooleanInstruction(@NotNull SmaliParser.IputBooleanInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#intToDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntToDoubleInstruction(@NotNull SmaliParser.IntToDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveResultInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveResultInstruction(@NotNull SmaliParser.MoveResultInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#packedSwitchDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackedSwitchDirective(@NotNull SmaliParser.PackedSwitchDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#igetBooleanInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgetBooleanInstruction(@NotNull SmaliParser.IgetBooleanInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#igetCharInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgetCharInstruction(@NotNull SmaliParser.IgetCharInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#gotoInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGotoInstruction(@NotNull SmaliParser.GotoInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remFloat2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemFloat2addrInstruction(@NotNull SmaliParser.RemFloat2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#binaryInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryInstruction(@NotNull SmaliParser.BinaryInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#rsubIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsubIntInstruction(@NotNull SmaliParser.RsubIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#annotationDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationDirective(@NotNull SmaliParser.AnnotationDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#const16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst16Instruction(@NotNull SmaliParser.Const16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#numericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericLiteral(@NotNull SmaliParser.NumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#charType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharType(@NotNull SmaliParser.CharTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#annotationField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationField(@NotNull SmaliParser.AnnotationFieldContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(@NotNull SmaliParser.ArrayTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#aputByteInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAputByteInstruction(@NotNull SmaliParser.AputByteInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#superDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperDirective(@NotNull SmaliParser.SuperDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#subDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubDoubleInstruction(@NotNull SmaliParser.SubDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#doubleToFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleToFloatInstruction(@NotNull SmaliParser.DoubleToFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#andLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndLongInstruction(@NotNull SmaliParser.AndLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shlLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShlLongInstruction(@NotNull SmaliParser.ShlLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#catchGotoLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchGotoLabel(@NotNull SmaliParser.CatchGotoLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#returnWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnWideInstruction(@NotNull SmaliParser.ReturnWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#floatToIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatToIntInstruction(@NotNull SmaliParser.FloatToIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sPutByteInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSPutByteInstruction(@NotNull SmaliParser.SPutByteInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifEqzInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfEqzInstruction(@NotNull SmaliParser.IfEqzInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveResultObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveResultObjectInstruction(@NotNull SmaliParser.MoveResultObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifLtzInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfLtzInstruction(@NotNull SmaliParser.IfLtzInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divIntLit16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivIntLit16Instruction(@NotNull SmaliParser.DivIntLit16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addIntLit16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddIntLit16Instruction(@NotNull SmaliParser.AddIntLit16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#registerRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterRange(@NotNull SmaliParser.RegisterRangeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#fieldType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldType(@NotNull SmaliParser.FieldTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodParameterType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodParameterType(@NotNull SmaliParser.MethodParameterTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#checkCastType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckCastType(@NotNull SmaliParser.CheckCastTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sourceRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceRegister(@NotNull SmaliParser.SourceRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#referenceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceType(@NotNull SmaliParser.ReferenceTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeStaticInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeStaticInstruction(@NotNull SmaliParser.InvokeStaticInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#instanceOfInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceOfInstruction(@NotNull SmaliParser.InstanceOfInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#aputInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAputInstruction(@NotNull SmaliParser.AputInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#igetWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgetWideInstruction(@NotNull SmaliParser.IgetWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#floatNumericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatNumericLiteral(@NotNull SmaliParser.FloatNumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#igetObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgetObjectInstruction(@NotNull SmaliParser.IgetObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#xorIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorIntInstruction(@NotNull SmaliParser.XorIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#labelName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelName(@NotNull SmaliParser.LabelNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#registerIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterIdentifier(@NotNull SmaliParser.RegisterIdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#floatToLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatToLongInstruction(@NotNull SmaliParser.FloatToLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#annotationFieldValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationFieldValue(@NotNull SmaliParser.AnnotationFieldValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulLong2addrInstruction(@NotNull SmaliParser.MulLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arrayElementRegisters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElementRegisters(@NotNull SmaliParser.ArrayElementRegistersContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveWide16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveWide16Instruction(@NotNull SmaliParser.MoveWide16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemIntLit8Instruction(@NotNull SmaliParser.RemIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sPutWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSPutWideInstruction(@NotNull SmaliParser.SPutWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#agetObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgetObjectInstruction(@NotNull SmaliParser.AgetObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddDoubleInstruction(@NotNull SmaliParser.AddDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodIdentifier(@NotNull SmaliParser.MethodIdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#positiveNumericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPositiveNumericLiteral(@NotNull SmaliParser.PositiveNumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#intToCharInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntToCharInstruction(@NotNull SmaliParser.IntToCharInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constWideHigh16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstWideHigh16Instruction(@NotNull SmaliParser.ConstWideHigh16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ushrLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUshrLong2addrInstruction(@NotNull SmaliParser.UshrLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeSuperInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeSuperInstruction(@NotNull SmaliParser.InvokeSuperInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#aputWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAputWideInstruction(@NotNull SmaliParser.AputWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeCustomInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeCustomInstruction(@NotNull SmaliParser.InvokeCustomInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#superName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuperName(@NotNull SmaliParser.SuperNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddLongInstruction(@NotNull SmaliParser.AddLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#anyType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnyType(@NotNull SmaliParser.AnyTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulIntLit16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulIntLit16Instruction(@NotNull SmaliParser.MulIntLit16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#extendedParamDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtendedParamDirective(@NotNull SmaliParser.ExtendedParamDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#fillArrayDataInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFillArrayDataInstruction(@NotNull SmaliParser.FillArrayDataInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#localsDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalsDirective(@NotNull SmaliParser.LocalsDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constHigh16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstHigh16Instruction(@NotNull SmaliParser.ConstHigh16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#referenceOrArrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceOrArrayType(@NotNull SmaliParser.ReferenceOrArrayTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sparseSwitchDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSparseSwitchDirective(@NotNull SmaliParser.SparseSwitchDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shrIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShrIntInstruction(@NotNull SmaliParser.ShrIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#intToShortInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntToShortInstruction(@NotNull SmaliParser.IntToShortInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifGezInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfGezInstruction(@NotNull SmaliParser.IfGezInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#subLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubLong2addrInstruction(@NotNull SmaliParser.SubLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#subFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubFloatInstruction(@NotNull SmaliParser.SubFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeConstMethodHandleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeConstMethodHandleInstruction(@NotNull SmaliParser.InvokeConstMethodHandleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#orLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrLong2addrInstruction(@NotNull SmaliParser.OrLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivFloatInstruction(@NotNull SmaliParser.DivFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#catchDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchDirective(@NotNull SmaliParser.CatchDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#igetShortInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgetShortInstruction(@NotNull SmaliParser.IgetShortInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#voidType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidType(@NotNull SmaliParser.VoidTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#booleanLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(@NotNull SmaliParser.BooleanLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodModifier(@NotNull SmaliParser.MethodModifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeDirectRangeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeDirectRangeInstruction(@NotNull SmaliParser.InvokeDirectRangeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifGeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfGeInstruction(@NotNull SmaliParser.IfGeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeSuperRangeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeSuperRangeInstruction(@NotNull SmaliParser.InvokeSuperRangeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveFrom16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveFrom16Instruction(@NotNull SmaliParser.MoveFrom16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#targetRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTargetRegister(@NotNull SmaliParser.TargetRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ternaryInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryInstruction(@NotNull SmaliParser.TernaryInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#xorIntLit16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorIntLit16Instruction(@NotNull SmaliParser.XorIntLit16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#newInstanceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewInstanceType(@NotNull SmaliParser.NewInstanceTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivIntInstruction(@NotNull SmaliParser.DivIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remDouble2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemDouble2addrInstruction(@NotNull SmaliParser.RemDouble2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#catchFromLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchFromLabel(@NotNull SmaliParser.CatchFromLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#registerListRegisters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterListRegisters(@NotNull SmaliParser.RegisterListRegistersContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#longToFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongToFloatInstruction(@NotNull SmaliParser.LongToFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#igetByteInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgetByteInstruction(@NotNull SmaliParser.IgetByteInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#indexRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexRegister(@NotNull SmaliParser.IndexRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#simpleParamDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleParamDirective(@NotNull SmaliParser.SimpleParamDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#andLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndLong2addrInstruction(@NotNull SmaliParser.AndLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeDirectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeDirectInstruction(@NotNull SmaliParser.InvokeDirectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arrayDataEntry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDataEntry(@NotNull SmaliParser.ArrayDataEntryContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#lineLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineLabel(@NotNull SmaliParser.LineLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstInstruction(@NotNull SmaliParser.ConstInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#filledArrayDataLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilledArrayDataLabel(@NotNull SmaliParser.FilledArrayDataLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#doubleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleType(@NotNull SmaliParser.DoubleTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remIntLit16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemIntLit16Instruction(@NotNull SmaliParser.RemIntLit16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabels}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackedSwitchDirectiveLabels(@NotNull SmaliParser.PackedSwitchDirectiveLabelsContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodInvocationTarget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodInvocationTarget(@NotNull SmaliParser.MethodInvocationTargetContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(@NotNull SmaliParser.MethodDeclarationContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBody(@NotNull SmaliParser.MethodBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#iputObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIputObjectInstruction(@NotNull SmaliParser.IputObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#cmplDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmplDoubleInstruction(@NotNull SmaliParser.CmplDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shortType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortType(@NotNull SmaliParser.ShortTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivInt2addrInstruction(@NotNull SmaliParser.DivInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#filledNewArrayInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilledNewArrayInstruction(@NotNull SmaliParser.FilledNewArrayInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divDouble2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivDouble2addrInstruction(@NotNull SmaliParser.DivDouble2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#byteType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitByteType(@NotNull SmaliParser.ByteTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#igetInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgetInstruction(@NotNull SmaliParser.IgetInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#packedSwitchRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackedSwitchRegister(@NotNull SmaliParser.PackedSwitchRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeStaticRangeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeStaticRangeInstruction(@NotNull SmaliParser.InvokeStaticRangeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#lineDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLineDirective(@NotNull SmaliParser.LineDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodParameterLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodParameterLiteral(@NotNull SmaliParser.MethodParameterLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifGtInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfGtInstruction(@NotNull SmaliParser.IfGtInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#orIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrIntLit8Instruction(@NotNull SmaliParser.OrIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arrayElementType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElementType(@NotNull SmaliParser.ArrayElementTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#intToByteInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntToByteInstruction(@NotNull SmaliParser.IntToByteInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull SmaliParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#fieldModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldModifier(@NotNull SmaliParser.FieldModifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#move16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMove16Instruction(@NotNull SmaliParser.Move16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivIntLit8Instruction(@NotNull SmaliParser.DivIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#xorLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorLong2addrInstruction(@NotNull SmaliParser.XorLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#agetShortInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgetShortInstruction(@NotNull SmaliParser.AgetShortInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#andIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndIntLit8Instruction(@NotNull SmaliParser.AndIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#cmpgFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpgFloatInstruction(@NotNull SmaliParser.CmpgFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#nullLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullLiteral(@NotNull SmaliParser.NullLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveWideFrom16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveWideFrom16Instruction(@NotNull SmaliParser.MoveWideFrom16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#subInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubInt2addrInstruction(@NotNull SmaliParser.SubInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#orInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrInt2addrInstruction(@NotNull SmaliParser.OrInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(@NotNull SmaliParser.LabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(@NotNull SmaliParser.ParseContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addDouble2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddDouble2addrInstruction(@NotNull SmaliParser.AddDouble2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#localDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalDirective(@NotNull SmaliParser.LocalDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#locaDirectiveVariableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocaDirectiveVariableName(@NotNull SmaliParser.LocaDirectiveVariableNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#packedSwitchLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackedSwitchLabel(@NotNull SmaliParser.PackedSwitchLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sparseSwitchDirectiveValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSparseSwitchDirectiveValue(@NotNull SmaliParser.SparseSwitchDirectiveValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#aputShortInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAputShortInstruction(@NotNull SmaliParser.AputShortInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sGetBooleanInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSGetBooleanInstruction(@NotNull SmaliParser.SGetBooleanInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifNezInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNezInstruction(@NotNull SmaliParser.IfNezInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulIntInstruction(@NotNull SmaliParser.MulIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#iputShortInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIputShortInstruction(@NotNull SmaliParser.IputShortInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sourceName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceName(@NotNull SmaliParser.SourceNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddIntLit8Instruction(@NotNull SmaliParser.AddIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDoubleInstruction(@NotNull SmaliParser.MulDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokePolymorphicInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokePolymorphicInstruction(@NotNull SmaliParser.InvokePolymorphicInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackedSwitchDirectiveLabel(@NotNull SmaliParser.PackedSwitchDirectiveLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#nonVoidType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonVoidType(@NotNull SmaliParser.NonVoidTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#nopInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNopInstruction(@NotNull SmaliParser.NopInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#catchAllDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchAllDirective(@NotNull SmaliParser.CatchAllDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sGetInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSGetInstruction(@NotNull SmaliParser.SGetInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfLabel(@NotNull SmaliParser.IfLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sGetWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSGetWideInstruction(@NotNull SmaliParser.SGetWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#rightRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightRegister(@NotNull SmaliParser.RightRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodSignature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodSignature(@NotNull SmaliParser.MethodSignatureContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#negLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegLongInstruction(@NotNull SmaliParser.NegLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveObjectFrom16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveObjectFrom16Instruction(@NotNull SmaliParser.MoveObjectFrom16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodArguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodArguments(@NotNull SmaliParser.MethodArgumentsContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#subIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubIntInstruction(@NotNull SmaliParser.SubIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#catchToLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchToLabel(@NotNull SmaliParser.CatchToLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#orLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrLongInstruction(@NotNull SmaliParser.OrLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(@NotNull SmaliParser.IdentifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#negativeNumericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegativeNumericLiteral(@NotNull SmaliParser.NegativeNumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#booleanType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanType(@NotNull SmaliParser.BooleanTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulIntLit8Instruction(@NotNull SmaliParser.MulIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddInt2addrInstruction(@NotNull SmaliParser.AddInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shrInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShrInt2addrInstruction(@NotNull SmaliParser.ShrInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#agetWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgetWideInstruction(@NotNull SmaliParser.AgetWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#annotationType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationType(@NotNull SmaliParser.AnnotationTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#const4Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConst4Instruction(@NotNull SmaliParser.Const4InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#iputByteInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIputByteInstruction(@NotNull SmaliParser.IputByteInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemDoubleInstruction(@NotNull SmaliParser.RemDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstString(@NotNull SmaliParser.ConstStringContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sGetCharInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSGetCharInstruction(@NotNull SmaliParser.SGetCharInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ushrInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUshrInt2addrInstruction(@NotNull SmaliParser.UshrInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constStringJumbo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstStringJumbo(@NotNull SmaliParser.ConstStringJumboContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(@NotNull SmaliParser.FieldContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#stringLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(@NotNull SmaliParser.StringLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#returnInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnInstruction(@NotNull SmaliParser.ReturnInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeVirtualInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeVirtualInstruction(@NotNull SmaliParser.InvokeVirtualInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shlLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShlLong2addrInstruction(@NotNull SmaliParser.ShlLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shlIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShlIntInstruction(@NotNull SmaliParser.ShlIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ushrIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUshrIntInstruction(@NotNull SmaliParser.UshrIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sGetObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSGetObjectInstruction(@NotNull SmaliParser.SGetObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#filledNewArrayRangeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilledNewArrayRangeInstruction(@NotNull SmaliParser.FilledNewArrayRangeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sGetShortInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSGetShortInstruction(@NotNull SmaliParser.SGetShortInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#andIntLit16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndIntLit16Instruction(@NotNull SmaliParser.AndIntLit16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#xorIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorIntLit8Instruction(@NotNull SmaliParser.XorIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sourceDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSourceDirective(@NotNull SmaliParser.SourceDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#orIntLit16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrIntLit16Instruction(@NotNull SmaliParser.OrIntLit16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#classModifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassModifier(@NotNull SmaliParser.ClassModifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#localEndDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalEndDirective(@NotNull SmaliParser.LocalEndDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#localDirectiveGenericHint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalDirectiveGenericHint(@NotNull SmaliParser.LocalDirectiveGenericHintContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shrIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShrIntLit8Instruction(@NotNull SmaliParser.ShrIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sPutShortInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSPutShortInstruction(@NotNull SmaliParser.SPutShortInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#aputBooleanInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAputBooleanInstruction(@NotNull SmaliParser.AputBooleanInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeInterfaceInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeInterfaceInstruction(@NotNull SmaliParser.InvokeInterfaceInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#cmpLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpLongInstruction(@NotNull SmaliParser.CmpLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#className}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassName(@NotNull SmaliParser.ClassNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#agetCharInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgetCharInstruction(@NotNull SmaliParser.AgetCharInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#agetBooleanInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgetBooleanInstruction(@NotNull SmaliParser.AgetBooleanInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sparseSwitchRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSparseSwitchRegister(@NotNull SmaliParser.SparseSwitchRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveResultWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveResultWideInstruction(@NotNull SmaliParser.MoveResultWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#rsubIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRsubIntLit8Instruction(@NotNull SmaliParser.RsubIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#decimalNumericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalNumericLiteral(@NotNull SmaliParser.DecimalNumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemIntInstruction(@NotNull SmaliParser.RemIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#instanceField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceField(@NotNull SmaliParser.InstanceFieldContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#registersDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegistersDirective(@NotNull SmaliParser.RegistersDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sGetByteInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSGetByteInstruction(@NotNull SmaliParser.SGetByteInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#intToFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntToFloatInstruction(@NotNull SmaliParser.IntToFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddFloatInstruction(@NotNull SmaliParser.AddFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddIntInstruction(@NotNull SmaliParser.AddIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#cmplFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmplFloatInstruction(@NotNull SmaliParser.CmplFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifEqInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfEqInstruction(@NotNull SmaliParser.IfEqInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#iputInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIputInstruction(@NotNull SmaliParser.IputInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstWideInstruction(@NotNull SmaliParser.ConstWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#intType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(@NotNull SmaliParser.IntTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arrayRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayRegister(@NotNull SmaliParser.ArrayRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokePolymorphicRangeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokePolymorphicRangeInstruction(@NotNull SmaliParser.InvokePolymorphicRangeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#iputCharInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIputCharInstruction(@NotNull SmaliParser.IputCharInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#hexFloatLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexFloatLiteral(@NotNull SmaliParser.HexFloatLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#subLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubLongInstruction(@NotNull SmaliParser.SubLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#intToLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntToLongInstruction(@NotNull SmaliParser.IntToLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulFloatInstruction(@NotNull SmaliParser.MulFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#goto32Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoto32Instruction(@NotNull SmaliParser.Goto32InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#subDouble2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubDouble2addrInstruction(@NotNull SmaliParser.SubDouble2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#hexNumericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexNumericLiteral(@NotNull SmaliParser.HexNumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveObjectInstruction(@NotNull SmaliParser.MoveObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#iputWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIputWideInstruction(@NotNull SmaliParser.IputWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#nonArrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonArrayType(@NotNull SmaliParser.NonArrayTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(@NotNull SmaliParser.InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divFloat2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivFloat2addrInstruction(@NotNull SmaliParser.DivFloat2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#checkInstanceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckInstanceType(@NotNull SmaliParser.CheckInstanceTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemLong2addrInstruction(@NotNull SmaliParser.RemLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ushrIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUshrIntLit8Instruction(@NotNull SmaliParser.UshrIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arrayElementRegisterRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElementRegisterRange(@NotNull SmaliParser.ArrayElementRegisterRangeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveWideInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveWideInstruction(@NotNull SmaliParser.MoveWideInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#floatToDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatToDoubleInstruction(@NotNull SmaliParser.FloatToDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifLeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfLeInstruction(@NotNull SmaliParser.IfLeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddLong2addrInstruction(@NotNull SmaliParser.AddLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#orIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrIntInstruction(@NotNull SmaliParser.OrIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#returnVoidInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnVoidInstruction(@NotNull SmaliParser.ReturnVoidInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#cmpgDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpgDoubleInstruction(@NotNull SmaliParser.CmpgDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#aputObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAputObjectInstruction(@NotNull SmaliParser.AputObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#monitorEnterInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonitorEnterInstruction(@NotNull SmaliParser.MonitorEnterInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivLong2addrInstruction(@NotNull SmaliParser.DivLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveInstruction(@NotNull SmaliParser.MoveInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shlInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShlInt2addrInstruction(@NotNull SmaliParser.ShlInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#paramDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamDirective(@NotNull SmaliParser.ParamDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulFloat2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulFloat2addrInstruction(@NotNull SmaliParser.MulFloat2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeInterfaceRangeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeInterfaceRangeInstruction(@NotNull SmaliParser.InvokeInterfaceRangeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#longToIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongToIntInstruction(@NotNull SmaliParser.LongToIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#negDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegDoubleInstruction(@NotNull SmaliParser.NegDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#longType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongType(@NotNull SmaliParser.LongTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#classDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDirective(@NotNull SmaliParser.ClassDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arrayDataDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDataDirective(@NotNull SmaliParser.ArrayDataDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#binaryNumericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryNumericLiteral(@NotNull SmaliParser.BinaryNumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#floatType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatType(@NotNull SmaliParser.FloatTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#longToDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLongToDoubleInstruction(@NotNull SmaliParser.LongToDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#methodBodyStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodBodyStatement(@NotNull SmaliParser.MethodBodyStatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#instanceRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstanceRegister(@NotNull SmaliParser.InstanceRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sPutCharInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSPutCharInstruction(@NotNull SmaliParser.SPutCharInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemInt2addrInstruction(@NotNull SmaliParser.RemInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulDouble2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDouble2addrInstruction(@NotNull SmaliParser.MulDouble2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeCustomRangeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeCustomRangeInstruction(@NotNull SmaliParser.InvokeCustomRangeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#checkCastInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCheckCastInstruction(@NotNull SmaliParser.CheckCastInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeConstMethodTypeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeConstMethodTypeInstruction(@NotNull SmaliParser.InvokeConstMethodTypeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arrayLengthInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLengthInstruction(@NotNull SmaliParser.ArrayLengthInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#arraySizeRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraySizeRegister(@NotNull SmaliParser.ArraySizeRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#returnObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnObjectInstruction(@NotNull SmaliParser.ReturnObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remFloatInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemFloatInstruction(@NotNull SmaliParser.RemFloatInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#aputCharInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAputCharInstruction(@NotNull SmaliParser.AputCharInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constWide32Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstWide32Instruction(@NotNull SmaliParser.ConstWide32InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#localDirectiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalDirectiveType(@NotNull SmaliParser.LocalDirectiveTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifGtzInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfGtzInstruction(@NotNull SmaliParser.IfGtzInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#newInstanceInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewInstanceInstruction(@NotNull SmaliParser.NewInstanceInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveObject16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveObject16Instruction(@NotNull SmaliParser.MoveObject16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulInt2addrInstruction(@NotNull SmaliParser.MulInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#annotationScope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationScope(@NotNull SmaliParser.AnnotationScopeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#goto16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGoto16Instruction(@NotNull SmaliParser.Goto16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#doubleToLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleToLongInstruction(@NotNull SmaliParser.DoubleToLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#assignableValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignableValue(@NotNull SmaliParser.AssignableValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#invokeVirtualRangeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInvokeVirtualRangeInstruction(@NotNull SmaliParser.InvokeVirtualRangeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#xorInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorInt2addrInstruction(@NotNull SmaliParser.XorInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constClass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstClass(@NotNull SmaliParser.ConstClassContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#fieldName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldName(@NotNull SmaliParser.FieldNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#notIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotIntInstruction(@NotNull SmaliParser.NotIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#monitorExitInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonitorExitInstruction(@NotNull SmaliParser.MonitorExitInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divDoubleInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivDoubleInstruction(@NotNull SmaliParser.DivDoubleInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#andInt2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndInt2addrInstruction(@NotNull SmaliParser.AndInt2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#agetByteInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAgetByteInstruction(@NotNull SmaliParser.AgetByteInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shlIntLit8Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShlIntLit8Instruction(@NotNull SmaliParser.ShlIntLit8InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#mulLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulLongInstruction(@NotNull SmaliParser.MulLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#registerList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegisterList(@NotNull SmaliParser.RegisterListContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#fieldNameAndType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldNameAndType(@NotNull SmaliParser.FieldNameAndTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#packedSwitchInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackedSwitchInstruction(@NotNull SmaliParser.PackedSwitchInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#packedSwitchIdent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackedSwitchIdent(@NotNull SmaliParser.PackedSwitchIdentContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#doubleToIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleToIntInstruction(@NotNull SmaliParser.DoubleToIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#shrLong2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShrLong2addrInstruction(@NotNull SmaliParser.ShrLong2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sparseSwitchLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSparseSwitchLabel(@NotNull SmaliParser.SparseSwitchLabelContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#fieldInvocationTarget}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldInvocationTarget(@NotNull SmaliParser.FieldInvocationTargetContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#localRestartDirective}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalRestartDirective(@NotNull SmaliParser.LocalRestartDirectiveContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(@NotNull SmaliParser.PrimitiveTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#subFloat2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubFloat2addrInstruction(@NotNull SmaliParser.SubFloat2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#octNumericLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctNumericLiteral(@NotNull SmaliParser.OctNumericLiteralContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#moveExceptionInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveExceptionInstruction(@NotNull SmaliParser.MoveExceptionInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#leftRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftRegister(@NotNull SmaliParser.LeftRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#xorLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorLongInstruction(@NotNull SmaliParser.XorLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifLtInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfLtInstruction(@NotNull SmaliParser.IfLtInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sPutInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSPutInstruction(@NotNull SmaliParser.SPutInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#negIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegIntInstruction(@NotNull SmaliParser.NegIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#catchExceptionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCatchExceptionType(@NotNull SmaliParser.CatchExceptionTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#remLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemLongInstruction(@NotNull SmaliParser.RemLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#annotationValueScoped}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnnotationValueScoped(@NotNull SmaliParser.AnnotationValueScopedContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#localDirectiveRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocalDirectiveRegister(@NotNull SmaliParser.LocalDirectiveRegisterContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#notLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotLongInstruction(@NotNull SmaliParser.NotLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#andIntInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndIntInstruction(@NotNull SmaliParser.AndIntInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#divLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivLongInstruction(@NotNull SmaliParser.DivLongInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sparseSwitchInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSparseSwitchInstruction(@NotNull SmaliParser.SparseSwitchInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifNeInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNeInstruction(@NotNull SmaliParser.IfNeInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#addFloat2addrInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddFloat2addrInstruction(@NotNull SmaliParser.AddFloat2addrInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sPutBooleanInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSPutBooleanInstruction(@NotNull SmaliParser.SPutBooleanInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#constWide16Instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstWide16Instruction(@NotNull SmaliParser.ConstWide16InstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#sPutObjectInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSPutObjectInstruction(@NotNull SmaliParser.SPutObjectInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ifLezInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfLezInstruction(@NotNull SmaliParser.IfLezInstructionContext ctx);

	/**
	 * Visit a parse tree produced by {@link SmaliParser#ushrLongInstruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUshrLongInstruction(@NotNull SmaliParser.UshrLongInstructionContext ctx);
}
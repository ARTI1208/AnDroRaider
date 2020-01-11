package ru.art2000.androraider.analyzer.antlr.smali;
// Generated from D:\Coding\Antlr\SmaliParser.g4 by ANTLR 4.1

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SmaliParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *            operations with no return type.
 */
public interface SmaliParserVisitor<T> extends ParseTreeVisitor<T> {
    /**
     * Visit a parse tree produced by {@link SmaliParser#negFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNegFloatInstruction(SmaliParser.NegFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shrLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShrLongInstruction(SmaliParser.ShrLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodReturnType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodReturnType(SmaliParser.MethodReturnTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#fieldDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFieldDirective(SmaliParser.FieldDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#newArrayInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNewArrayInstruction(SmaliParser.NewArrayInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#agetInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAgetInstruction(SmaliParser.AgetInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodDirective(SmaliParser.MethodDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#throwInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitThrowInstruction(SmaliParser.ThrowInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#iputBooleanInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIputBooleanInstruction(SmaliParser.IputBooleanInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#intToDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntToDoubleInstruction(SmaliParser.IntToDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveResultInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveResultInstruction(SmaliParser.MoveResultInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#packedSwitchDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPackedSwitchDirective(SmaliParser.PackedSwitchDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#igetBooleanInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIgetBooleanInstruction(SmaliParser.IgetBooleanInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#igetCharInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIgetCharInstruction(SmaliParser.IgetCharInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#gotoInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitGotoInstruction(SmaliParser.GotoInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemFloat2addrInstruction(SmaliParser.RemFloat2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#binaryInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBinaryInstruction(SmaliParser.BinaryInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#rsubIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRsubIntInstruction(SmaliParser.RsubIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#annotationDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnnotationDirective(SmaliParser.AnnotationDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#const16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConst16Instruction(SmaliParser.Const16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#numericLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNumericLiteral(SmaliParser.NumericLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#charType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCharType(SmaliParser.CharTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#annotationField}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnnotationField(SmaliParser.AnnotationFieldContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arrayType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayType(SmaliParser.ArrayTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#aputByteInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAputByteInstruction(SmaliParser.AputByteInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#superDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSuperDirective(SmaliParser.SuperDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#subDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubDoubleInstruction(SmaliParser.SubDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#doubleToFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDoubleToFloatInstruction(SmaliParser.DoubleToFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#andLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAndLongInstruction(SmaliParser.AndLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shlLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShlLongInstruction(SmaliParser.ShlLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#catchGotoLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCatchGotoLabel(SmaliParser.CatchGotoLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#returnWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReturnWideInstruction(SmaliParser.ReturnWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#floatToIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFloatToIntInstruction(SmaliParser.FloatToIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sPutByteInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSPutByteInstruction(SmaliParser.SPutByteInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifEqzInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfEqzInstruction(SmaliParser.IfEqzInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveResultObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveResultObjectInstruction(SmaliParser.MoveResultObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifLtzInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfLtzInstruction(SmaliParser.IfLtzInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divIntLit16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivIntLit16Instruction(SmaliParser.DivIntLit16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addIntLit16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddIntLit16Instruction(SmaliParser.AddIntLit16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#registerRange}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRegisterRange(SmaliParser.RegisterRangeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#fieldType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFieldType(SmaliParser.FieldTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodParameterType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodParameterType(SmaliParser.MethodParameterTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#checkCastType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCheckCastType(SmaliParser.CheckCastTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sourceRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSourceRegister(SmaliParser.SourceRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#referenceType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReferenceType(SmaliParser.ReferenceTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeStaticInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeStaticInstruction(SmaliParser.InvokeStaticInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#instanceOfInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInstanceOfInstruction(SmaliParser.InstanceOfInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#aputInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAputInstruction(SmaliParser.AputInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#igetWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIgetWideInstruction(SmaliParser.IgetWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#floatNumericLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFloatNumericLiteral(SmaliParser.FloatNumericLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#igetObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIgetObjectInstruction(SmaliParser.IgetObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#xorIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitXorIntInstruction(SmaliParser.XorIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#labelName}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLabelName(SmaliParser.LabelNameContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#registerIdentifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRegisterIdentifier(SmaliParser.RegisterIdentifierContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#floatToLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFloatToLongInstruction(SmaliParser.FloatToLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#annotationFieldValue}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnnotationFieldValue(SmaliParser.AnnotationFieldValueContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulLong2addrInstruction(SmaliParser.MulLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arrayElementRegisters}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayElementRegisters(SmaliParser.ArrayElementRegistersContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveWide16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveWide16Instruction(SmaliParser.MoveWide16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemIntLit8Instruction(SmaliParser.RemIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sPutWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSPutWideInstruction(SmaliParser.SPutWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#agetObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAgetObjectInstruction(SmaliParser.AgetObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddDoubleInstruction(SmaliParser.AddDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodIdentifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodIdentifier(SmaliParser.MethodIdentifierContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#positiveNumericLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPositiveNumericLiteral(SmaliParser.PositiveNumericLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#intToCharInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntToCharInstruction(SmaliParser.IntToCharInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constWideHigh16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstWideHigh16Instruction(SmaliParser.ConstWideHigh16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ushrLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitUshrLong2addrInstruction(SmaliParser.UshrLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeSuperInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeSuperInstruction(SmaliParser.InvokeSuperInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#aputWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAputWideInstruction(SmaliParser.AputWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeCustomInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeCustomInstruction(SmaliParser.InvokeCustomInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#superName}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSuperName(SmaliParser.SuperNameContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddLongInstruction(SmaliParser.AddLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#anyType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnyType(SmaliParser.AnyTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulIntLit16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulIntLit16Instruction(SmaliParser.MulIntLit16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#extendedParamDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitExtendedParamDirective(SmaliParser.ExtendedParamDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#fillArrayDataInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFillArrayDataInstruction(SmaliParser.FillArrayDataInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#localsDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLocalsDirective(SmaliParser.LocalsDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constHigh16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstHigh16Instruction(SmaliParser.ConstHigh16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#referenceOrArrayType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReferenceOrArrayType(SmaliParser.ReferenceOrArrayTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sparseSwitchDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSparseSwitchDirective(SmaliParser.SparseSwitchDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shrIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShrIntInstruction(SmaliParser.ShrIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#intToShortInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntToShortInstruction(SmaliParser.IntToShortInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifGezInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfGezInstruction(SmaliParser.IfGezInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#subLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubLong2addrInstruction(SmaliParser.SubLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#subFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubFloatInstruction(SmaliParser.SubFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeConstMethodHandleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeConstMethodHandleInstruction(SmaliParser.InvokeConstMethodHandleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#orLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOrLong2addrInstruction(SmaliParser.OrLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivFloatInstruction(SmaliParser.DivFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#catchDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCatchDirective(SmaliParser.CatchDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#igetShortInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIgetShortInstruction(SmaliParser.IgetShortInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#voidType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitVoidType(SmaliParser.VoidTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#booleanLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBooleanLiteral(SmaliParser.BooleanLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodModifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodModifier(SmaliParser.MethodModifierContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeDirectRangeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeDirectRangeInstruction(SmaliParser.InvokeDirectRangeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifGeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfGeInstruction(SmaliParser.IfGeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeSuperRangeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeSuperRangeInstruction(SmaliParser.InvokeSuperRangeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveFrom16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveFrom16Instruction(SmaliParser.MoveFrom16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#targetRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitTargetRegister(SmaliParser.TargetRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ternaryInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitTernaryInstruction(SmaliParser.TernaryInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#xorIntLit16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitXorIntLit16Instruction(SmaliParser.XorIntLit16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#newInstanceType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNewInstanceType(SmaliParser.NewInstanceTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivIntInstruction(SmaliParser.DivIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemDouble2addrInstruction(SmaliParser.RemDouble2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#catchFromLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCatchFromLabel(SmaliParser.CatchFromLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#registerListRegisters}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRegisterListRegisters(SmaliParser.RegisterListRegistersContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#longToFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLongToFloatInstruction(SmaliParser.LongToFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#igetByteInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIgetByteInstruction(SmaliParser.IgetByteInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#indexRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIndexRegister(SmaliParser.IndexRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#simpleParamDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSimpleParamDirective(SmaliParser.SimpleParamDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#andLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAndLong2addrInstruction(SmaliParser.AndLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeDirectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeDirectInstruction(SmaliParser.InvokeDirectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arrayDataEntry}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayDataEntry(SmaliParser.ArrayDataEntryContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#lineLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLineLabel(SmaliParser.LineLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstInstruction(SmaliParser.ConstInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#filledArrayDataLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFilledArrayDataLabel(SmaliParser.FilledArrayDataLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#doubleType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDoubleType(SmaliParser.DoubleTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remIntLit16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemIntLit16Instruction(SmaliParser.RemIntLit16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabels}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPackedSwitchDirectiveLabels(SmaliParser.PackedSwitchDirectiveLabelsContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodInvocationTarget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodInvocationTarget(SmaliParser.MethodInvocationTargetContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodDeclaration}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodDeclaration(SmaliParser.MethodDeclarationContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodBody}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodBody(SmaliParser.MethodBodyContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#iputObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIputObjectInstruction(SmaliParser.IputObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#cmplDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCmplDoubleInstruction(SmaliParser.CmplDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shortType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShortType(SmaliParser.ShortTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivInt2addrInstruction(SmaliParser.DivInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#filledNewArrayInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFilledNewArrayInstruction(SmaliParser.FilledNewArrayInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivDouble2addrInstruction(SmaliParser.DivDouble2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#byteType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitByteType(SmaliParser.ByteTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#igetInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIgetInstruction(SmaliParser.IgetInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#packedSwitchRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPackedSwitchRegister(SmaliParser.PackedSwitchRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeStaticRangeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeStaticRangeInstruction(SmaliParser.InvokeStaticRangeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#lineDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLineDirective(SmaliParser.LineDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodParameterLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodParameterLiteral(SmaliParser.MethodParameterLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifGtInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfGtInstruction(SmaliParser.IfGtInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#orIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOrIntLit8Instruction(SmaliParser.OrIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arrayElementType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayElementType(SmaliParser.ArrayElementTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#intToByteInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntToByteInstruction(SmaliParser.IntToByteInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#statement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStatement(SmaliParser.StatementContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#fieldModifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFieldModifier(SmaliParser.FieldModifierContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#move16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMove16Instruction(SmaliParser.Move16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivIntLit8Instruction(SmaliParser.DivIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#xorLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitXorLong2addrInstruction(SmaliParser.XorLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#agetShortInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAgetShortInstruction(SmaliParser.AgetShortInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#andIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAndIntLit8Instruction(SmaliParser.AndIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#cmpgFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCmpgFloatInstruction(SmaliParser.CmpgFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#nullLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNullLiteral(SmaliParser.NullLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveWideFrom16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveWideFrom16Instruction(SmaliParser.MoveWideFrom16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#subInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubInt2addrInstruction(SmaliParser.SubInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#orInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOrInt2addrInstruction(SmaliParser.OrInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#label}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLabel(SmaliParser.LabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#parse}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitParse(SmaliParser.ParseContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddDouble2addrInstruction(SmaliParser.AddDouble2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#localDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLocalDirective(SmaliParser.LocalDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#locaDirectiveVariableName}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLocaDirectiveVariableName(SmaliParser.LocaDirectiveVariableNameContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#packedSwitchLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPackedSwitchLabel(SmaliParser.PackedSwitchLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sparseSwitchDirectiveValue}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSparseSwitchDirectiveValue(SmaliParser.SparseSwitchDirectiveValueContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#aputShortInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAputShortInstruction(SmaliParser.AputShortInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sGetBooleanInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSGetBooleanInstruction(SmaliParser.SGetBooleanInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifNezInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfNezInstruction(SmaliParser.IfNezInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulIntInstruction(SmaliParser.MulIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#iputShortInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIputShortInstruction(SmaliParser.IputShortInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sourceName}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSourceName(SmaliParser.SourceNameContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddIntLit8Instruction(SmaliParser.AddIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulDoubleInstruction(SmaliParser.MulDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokePolymorphicInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokePolymorphicInstruction(SmaliParser.InvokePolymorphicInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#packedSwitchDirectiveLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPackedSwitchDirectiveLabel(SmaliParser.PackedSwitchDirectiveLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#nonVoidType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNonVoidType(SmaliParser.NonVoidTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#nopInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNopInstruction(SmaliParser.NopInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#catchAllDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCatchAllDirective(SmaliParser.CatchAllDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sGetInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSGetInstruction(SmaliParser.SGetInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfLabel(SmaliParser.IfLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sGetWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSGetWideInstruction(SmaliParser.SGetWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#rightRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRightRegister(SmaliParser.RightRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodSignature}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodSignature(SmaliParser.MethodSignatureContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#negLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNegLongInstruction(SmaliParser.NegLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveObjectFrom16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveObjectFrom16Instruction(SmaliParser.MoveObjectFrom16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodArguments}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodArguments(SmaliParser.MethodArgumentsContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#subIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubIntInstruction(SmaliParser.SubIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#catchToLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCatchToLabel(SmaliParser.CatchToLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#orLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOrLongInstruction(SmaliParser.OrLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#identifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIdentifier(SmaliParser.IdentifierContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#negativeNumericLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNegativeNumericLiteral(SmaliParser.NegativeNumericLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#booleanType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBooleanType(SmaliParser.BooleanTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulIntLit8Instruction(SmaliParser.MulIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddInt2addrInstruction(SmaliParser.AddInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shrInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShrInt2addrInstruction(SmaliParser.ShrInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#agetWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAgetWideInstruction(SmaliParser.AgetWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#annotationType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnnotationType(SmaliParser.AnnotationTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#const4Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConst4Instruction(SmaliParser.Const4InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#iputByteInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIputByteInstruction(SmaliParser.IputByteInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemDoubleInstruction(SmaliParser.RemDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constString}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstString(SmaliParser.ConstStringContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sGetCharInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSGetCharInstruction(SmaliParser.SGetCharInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ushrInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitUshrInt2addrInstruction(SmaliParser.UshrInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constStringJumbo}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstStringJumbo(SmaliParser.ConstStringJumboContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#field}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitField(SmaliParser.FieldContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#stringLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStringLiteral(SmaliParser.StringLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#returnInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReturnInstruction(SmaliParser.ReturnInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeVirtualInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeVirtualInstruction(SmaliParser.InvokeVirtualInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shlLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShlLong2addrInstruction(SmaliParser.ShlLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shlIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShlIntInstruction(SmaliParser.ShlIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ushrIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitUshrIntInstruction(SmaliParser.UshrIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sGetObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSGetObjectInstruction(SmaliParser.SGetObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#filledNewArrayRangeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFilledNewArrayRangeInstruction(SmaliParser.FilledNewArrayRangeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sGetShortInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSGetShortInstruction(SmaliParser.SGetShortInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#andIntLit16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAndIntLit16Instruction(SmaliParser.AndIntLit16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#xorIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitXorIntLit8Instruction(SmaliParser.XorIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sourceDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSourceDirective(SmaliParser.SourceDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#orIntLit16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOrIntLit16Instruction(SmaliParser.OrIntLit16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#classModifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitClassModifier(SmaliParser.ClassModifierContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#localEndDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLocalEndDirective(SmaliParser.LocalEndDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#localDirectiveGenericHint}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLocalDirectiveGenericHint(SmaliParser.LocalDirectiveGenericHintContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shrIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShrIntLit8Instruction(SmaliParser.ShrIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sPutShortInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSPutShortInstruction(SmaliParser.SPutShortInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#aputBooleanInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAputBooleanInstruction(SmaliParser.AputBooleanInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeInterfaceInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeInterfaceInstruction(SmaliParser.InvokeInterfaceInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#cmpLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCmpLongInstruction(SmaliParser.CmpLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#className}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitClassName(SmaliParser.ClassNameContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#agetCharInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAgetCharInstruction(SmaliParser.AgetCharInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#agetBooleanInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAgetBooleanInstruction(SmaliParser.AgetBooleanInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sparseSwitchRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSparseSwitchRegister(SmaliParser.SparseSwitchRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveResultWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveResultWideInstruction(SmaliParser.MoveResultWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#rsubIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRsubIntLit8Instruction(SmaliParser.RsubIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#decimalNumericLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDecimalNumericLiteral(SmaliParser.DecimalNumericLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemIntInstruction(SmaliParser.RemIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#instanceField}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInstanceField(SmaliParser.InstanceFieldContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#registersDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRegistersDirective(SmaliParser.RegistersDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sGetByteInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSGetByteInstruction(SmaliParser.SGetByteInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#intToFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntToFloatInstruction(SmaliParser.IntToFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddFloatInstruction(SmaliParser.AddFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddIntInstruction(SmaliParser.AddIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#cmplFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCmplFloatInstruction(SmaliParser.CmplFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifEqInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfEqInstruction(SmaliParser.IfEqInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#iputInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIputInstruction(SmaliParser.IputInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstWideInstruction(SmaliParser.ConstWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#intType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntType(SmaliParser.IntTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arrayRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayRegister(SmaliParser.ArrayRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokePolymorphicRangeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokePolymorphicRangeInstruction(SmaliParser.InvokePolymorphicRangeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#iputCharInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIputCharInstruction(SmaliParser.IputCharInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#hexFloatLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitHexFloatLiteral(SmaliParser.HexFloatLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#subLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubLongInstruction(SmaliParser.SubLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#intToLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIntToLongInstruction(SmaliParser.IntToLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulFloatInstruction(SmaliParser.MulFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#goto32Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitGoto32Instruction(SmaliParser.Goto32InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#subDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubDouble2addrInstruction(SmaliParser.SubDouble2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#hexNumericLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitHexNumericLiteral(SmaliParser.HexNumericLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveObjectInstruction(SmaliParser.MoveObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#iputWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIputWideInstruction(SmaliParser.IputWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#nonArrayType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNonArrayType(SmaliParser.NonArrayTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInstruction(SmaliParser.InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivFloat2addrInstruction(SmaliParser.DivFloat2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#checkInstanceType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCheckInstanceType(SmaliParser.CheckInstanceTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemLong2addrInstruction(SmaliParser.RemLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ushrIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitUshrIntLit8Instruction(SmaliParser.UshrIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arrayElementRegisterRange}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayElementRegisterRange(SmaliParser.ArrayElementRegisterRangeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveWideInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveWideInstruction(SmaliParser.MoveWideInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#floatToDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFloatToDoubleInstruction(SmaliParser.FloatToDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifLeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfLeInstruction(SmaliParser.IfLeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddLong2addrInstruction(SmaliParser.AddLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#orIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOrIntInstruction(SmaliParser.OrIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#returnVoidInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReturnVoidInstruction(SmaliParser.ReturnVoidInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#cmpgDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCmpgDoubleInstruction(SmaliParser.CmpgDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#aputObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAputObjectInstruction(SmaliParser.AputObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#monitorEnterInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMonitorEnterInstruction(SmaliParser.MonitorEnterInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivLong2addrInstruction(SmaliParser.DivLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveInstruction(SmaliParser.MoveInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shlInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShlInt2addrInstruction(SmaliParser.ShlInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#paramDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitParamDirective(SmaliParser.ParamDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulFloat2addrInstruction(SmaliParser.MulFloat2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeInterfaceRangeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeInterfaceRangeInstruction(SmaliParser.InvokeInterfaceRangeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#longToIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLongToIntInstruction(SmaliParser.LongToIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#negDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNegDoubleInstruction(SmaliParser.NegDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#longType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLongType(SmaliParser.LongTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#classDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitClassDirective(SmaliParser.ClassDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arrayDataDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayDataDirective(SmaliParser.ArrayDataDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#binaryNumericLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitBinaryNumericLiteral(SmaliParser.BinaryNumericLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#floatType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFloatType(SmaliParser.FloatTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#longToDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLongToDoubleInstruction(SmaliParser.LongToDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#methodBodyStatement}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMethodBodyStatement(SmaliParser.MethodBodyStatementContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#instanceRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInstanceRegister(SmaliParser.InstanceRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sPutCharInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSPutCharInstruction(SmaliParser.SPutCharInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemInt2addrInstruction(SmaliParser.RemInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulDouble2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulDouble2addrInstruction(SmaliParser.MulDouble2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeCustomRangeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeCustomRangeInstruction(SmaliParser.InvokeCustomRangeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#checkCastInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCheckCastInstruction(SmaliParser.CheckCastInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeConstMethodTypeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeConstMethodTypeInstruction(SmaliParser.InvokeConstMethodTypeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arrayLengthInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArrayLengthInstruction(SmaliParser.ArrayLengthInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#arraySizeRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitArraySizeRegister(SmaliParser.ArraySizeRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#returnObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitReturnObjectInstruction(SmaliParser.ReturnObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remFloatInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemFloatInstruction(SmaliParser.RemFloatInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#aputCharInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAputCharInstruction(SmaliParser.AputCharInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constWide32Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstWide32Instruction(SmaliParser.ConstWide32InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#localDirectiveType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLocalDirectiveType(SmaliParser.LocalDirectiveTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifGtzInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfGtzInstruction(SmaliParser.IfGtzInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#newInstanceInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNewInstanceInstruction(SmaliParser.NewInstanceInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveObject16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveObject16Instruction(SmaliParser.MoveObject16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulInt2addrInstruction(SmaliParser.MulInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#annotationScope}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnnotationScope(SmaliParser.AnnotationScopeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#goto16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitGoto16Instruction(SmaliParser.Goto16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#doubleToLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDoubleToLongInstruction(SmaliParser.DoubleToLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#assignableValue}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAssignableValue(SmaliParser.AssignableValueContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#invokeVirtualRangeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitInvokeVirtualRangeInstruction(SmaliParser.InvokeVirtualRangeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#xorInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitXorInt2addrInstruction(SmaliParser.XorInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constClass}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstClass(SmaliParser.ConstClassContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#fieldName}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFieldName(SmaliParser.FieldNameContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#notIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNotIntInstruction(SmaliParser.NotIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#monitorExitInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMonitorExitInstruction(SmaliParser.MonitorExitInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divDoubleInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivDoubleInstruction(SmaliParser.DivDoubleInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#andInt2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAndInt2addrInstruction(SmaliParser.AndInt2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#agetByteInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAgetByteInstruction(SmaliParser.AgetByteInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shlIntLit8Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShlIntLit8Instruction(SmaliParser.ShlIntLit8InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#mulLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMulLongInstruction(SmaliParser.MulLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#registerList}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRegisterList(SmaliParser.RegisterListContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#fieldNameAndType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFieldNameAndType(SmaliParser.FieldNameAndTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#packedSwitchInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPackedSwitchInstruction(SmaliParser.PackedSwitchInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#packedSwitchIdent}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPackedSwitchIdent(SmaliParser.PackedSwitchIdentContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#doubleToIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDoubleToIntInstruction(SmaliParser.DoubleToIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#shrLong2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitShrLong2addrInstruction(SmaliParser.ShrLong2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sparseSwitchLabel}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSparseSwitchLabel(SmaliParser.SparseSwitchLabelContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#fieldInvocationTarget}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFieldInvocationTarget(SmaliParser.FieldInvocationTargetContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#localRestartDirective}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLocalRestartDirective(SmaliParser.LocalRestartDirectiveContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#primitiveType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPrimitiveType(SmaliParser.PrimitiveTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#subFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSubFloat2addrInstruction(SmaliParser.SubFloat2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#octNumericLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOctNumericLiteral(SmaliParser.OctNumericLiteralContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#moveExceptionInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitMoveExceptionInstruction(SmaliParser.MoveExceptionInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#leftRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLeftRegister(SmaliParser.LeftRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#xorLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitXorLongInstruction(SmaliParser.XorLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifLtInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfLtInstruction(SmaliParser.IfLtInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sPutInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSPutInstruction(SmaliParser.SPutInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#negIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNegIntInstruction(SmaliParser.NegIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#catchExceptionType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitCatchExceptionType(SmaliParser.CatchExceptionTypeContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#remLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRemLongInstruction(SmaliParser.RemLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#annotationValueScoped}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAnnotationValueScoped(SmaliParser.AnnotationValueScopedContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#localDirectiveRegister}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitLocalDirectiveRegister(SmaliParser.LocalDirectiveRegisterContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#notLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitNotLongInstruction(SmaliParser.NotLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#andIntInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAndIntInstruction(SmaliParser.AndIntInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#divLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitDivLongInstruction(SmaliParser.DivLongInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sparseSwitchInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSparseSwitchInstruction(SmaliParser.SparseSwitchInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifNeInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfNeInstruction(SmaliParser.IfNeInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#addFloat2addrInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitAddFloat2addrInstruction(SmaliParser.AddFloat2addrInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sPutBooleanInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSPutBooleanInstruction(SmaliParser.SPutBooleanInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#constWide16Instruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitConstWide16Instruction(SmaliParser.ConstWide16InstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#sPutObjectInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSPutObjectInstruction(SmaliParser.SPutObjectInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ifLezInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIfLezInstruction(SmaliParser.IfLezInstructionContext ctx);

    /**
     * Visit a parse tree produced by {@link SmaliParser#ushrLongInstruction}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitUshrLongInstruction(SmaliParser.UshrLongInstructionContext ctx);
}
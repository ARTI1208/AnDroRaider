package ru.art2000.androraider.model.analyzer.smali

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor
import org.antlr.v4.runtime.tree.ErrorNode
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliMethod
import ru.art2000.androraider.utils.parseCompound
import ru.art2000.androraider.utils.textRange
import java.io.File
import java.lang.Exception

class SmaliShallowScanner(val project: ProjectAnalyzeResult, val file: File) :
        AbstractParseTreeVisitor<SmaliClass>(), SmaliParserVisitor<SmaliClass> {

    private lateinit var smaliClass: SmaliClass

    override fun visitClassDirective(ctx: SmaliParser.ClassDirectiveContext): SmaliClass {
        project.getOrCreateClass(ctx.className().text)?.also {
            smaliClass = it
        }

        smaliClass.associatedFile = file

        smaliClass.textRange = ctx.className().textRange

        return smaliClass
    }

    override fun visitNegFloatInstruction(ctx: SmaliParser.NegFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShrLongInstruction(ctx: SmaliParser.ShrLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodReturnType(ctx: SmaliParser.MethodReturnTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitFieldDirective(ctx: SmaliParser.FieldDirectiveContext): SmaliClass {
        val smaliField = smaliClass.findOrCreateField(
                ctx.fieldNameAndType()?.fieldName()?.text ?: "Dummy",
                ctx.fieldNameAndType()?.fieldType()?.text ?: "I"
        )!!
        smaliField.parentClass = smaliClass
        smaliField.textRange = ctx.fieldNameAndType()?.fieldName()?.textRange ?: -1..0
        return smaliClass
    }

    override fun visitNewArrayInstruction(ctx: SmaliParser.NewArrayInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAgetInstruction(ctx: SmaliParser.AgetInstructionContext): SmaliClass {
        return smaliClass
    }

    private fun smaliMethodFromDeclarationContext(ctx: SmaliParser.MethodDeclarationContext): SmaliMethod {
        val name = ctx.methodSignature()?.methodIdentifier()?.text ?: "DummyMethod"
        val params = parseCompound(ctx.methodSignature()?.methodArguments()?.text)
        val returnType = ctx.methodSignature()?.methodReturnType()?.text ?: "V"
        return smaliClass.findOrCreateMethod(name, params, returnType, 0)!!
    }

    override fun visitMethodDirective(ctx: SmaliParser.MethodDirectiveContext): SmaliClass {
        val smaliMethod = smaliMethodFromDeclarationContext(ctx.methodDeclaration())
        smaliMethod.textRange = ctx.methodDeclaration()?.methodSignature()?.methodIdentifier()?.textRange ?: -1..0
        return smaliClass
    }

    override fun visitThrowInstruction(ctx: SmaliParser.ThrowInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIputBooleanInstruction(ctx: SmaliParser.IputBooleanInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIntToDoubleInstruction(ctx: SmaliParser.IntToDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveResultInstruction(ctx: SmaliParser.MoveResultInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitPackedSwitchDirective(ctx: SmaliParser.PackedSwitchDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitIgetBooleanInstruction(ctx: SmaliParser.IgetBooleanInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIgetCharInstruction(ctx: SmaliParser.IgetCharInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitGotoInstruction(ctx: SmaliParser.GotoInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemFloat2addrInstruction(ctx: SmaliParser.RemFloat2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitBinaryInstruction(ctx: SmaliParser.BinaryInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRsubIntInstruction(ctx: SmaliParser.RsubIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAnnotationDirective(ctx: SmaliParser.AnnotationDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitConst16Instruction(ctx: SmaliParser.Const16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitNumericLiteral(ctx: SmaliParser.NumericLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitCharType(ctx: SmaliParser.CharTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitAnnotationField(ctx: SmaliParser.AnnotationFieldContext): SmaliClass {
        return smaliClass
    }

    override fun visitArrayType(ctx: SmaliParser.ArrayTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitAputByteInstruction(ctx: SmaliParser.AputByteInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSuperDirective(ctx: SmaliParser.SuperDirectiveContext): SmaliClass {
        val superName = ctx.superName()?.text ?: return smaliClass

        project.getOrCreateClass(superName)?.also {
            smaliClass.parentClass = it
        }

        return smaliClass
    }

    override fun visitSubDoubleInstruction(ctx: SmaliParser.SubDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDoubleToFloatInstruction(ctx: SmaliParser.DoubleToFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAndLongInstruction(ctx: SmaliParser.AndLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShlLongInstruction(ctx: SmaliParser.ShlLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCatchGotoLabel(ctx: SmaliParser.CatchGotoLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitReturnWideInstruction(ctx: SmaliParser.ReturnWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitFloatToIntInstruction(ctx: SmaliParser.FloatToIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSPutByteInstruction(ctx: SmaliParser.SPutByteInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfEqzInstruction(ctx: SmaliParser.IfEqzInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveResultObjectInstruction(ctx: SmaliParser.MoveResultObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfLtzInstruction(ctx: SmaliParser.IfLtzInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivIntLit16Instruction(ctx: SmaliParser.DivIntLit16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAddIntLit16Instruction(ctx: SmaliParser.AddIntLit16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRegisterRange(ctx: SmaliParser.RegisterRangeContext): SmaliClass {
        return smaliClass
    }

    override fun visitFieldType(ctx: SmaliParser.FieldTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodParameterType(ctx: SmaliParser.MethodParameterTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitCheckCastType(ctx: SmaliParser.CheckCastTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitSourceRegister(ctx: SmaliParser.SourceRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitReferenceType(ctx: SmaliParser.ReferenceTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeStaticInstruction(ctx: SmaliParser.InvokeStaticInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInstanceOfInstruction(ctx: SmaliParser.InstanceOfInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAputInstruction(ctx: SmaliParser.AputInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIgetWideInstruction(ctx: SmaliParser.IgetWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitFloatNumericLiteral(ctx: SmaliParser.FloatNumericLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitIgetObjectInstruction(ctx: SmaliParser.IgetObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitXorIntInstruction(ctx: SmaliParser.XorIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLabelName(ctx: SmaliParser.LabelNameContext): SmaliClass {
        return smaliClass
    }

    override fun visitRegisterIdentifier(ctx: SmaliParser.RegisterIdentifierContext): SmaliClass {
        return smaliClass
    }

    override fun visitFloatToLongInstruction(ctx: SmaliParser.FloatToLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAnnotationFieldValue(ctx: SmaliParser.AnnotationFieldValueContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulLong2addrInstruction(ctx: SmaliParser.MulLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitArrayElementRegisters(ctx: SmaliParser.ArrayElementRegistersContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveWide16Instruction(ctx: SmaliParser.MoveWide16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemIntLit8Instruction(ctx: SmaliParser.RemIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSPutWideInstruction(ctx: SmaliParser.SPutWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAgetObjectInstruction(ctx: SmaliParser.AgetObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAddDoubleInstruction(ctx: SmaliParser.AddDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodIdentifier(ctx: SmaliParser.MethodIdentifierContext): SmaliClass {
        return smaliClass
    }

    override fun visitPositiveNumericLiteral(ctx: SmaliParser.PositiveNumericLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitIntToCharInstruction(ctx: SmaliParser.IntToCharInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstWideHigh16Instruction(ctx: SmaliParser.ConstWideHigh16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitUshrLong2addrInstruction(ctx: SmaliParser.UshrLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeSuperInstruction(ctx: SmaliParser.InvokeSuperInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAputWideInstruction(ctx: SmaliParser.AputWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeCustomInstruction(ctx: SmaliParser.InvokeCustomInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSuperName(ctx: SmaliParser.SuperNameContext): SmaliClass {

        smaliClass.parentClass = project.getOrCreateClass(ctx.text)

        return smaliClass
    }

    override fun visitAddLongInstruction(ctx: SmaliParser.AddLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAnyType(ctx: SmaliParser.AnyTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulIntLit16Instruction(ctx: SmaliParser.MulIntLit16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitExtendedParamDirective(ctx: SmaliParser.ExtendedParamDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitFillArrayDataInstruction(ctx: SmaliParser.FillArrayDataInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLocalsDirective(ctx: SmaliParser.LocalsDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstHigh16Instruction(ctx: SmaliParser.ConstHigh16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitReferenceOrArrayType(ctx: SmaliParser.ReferenceOrArrayTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitSparseSwitchDirective(ctx: SmaliParser.SparseSwitchDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitShrIntInstruction(ctx: SmaliParser.ShrIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIntToShortInstruction(ctx: SmaliParser.IntToShortInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfGezInstruction(ctx: SmaliParser.IfGezInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSubLong2addrInstruction(ctx: SmaliParser.SubLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSubFloatInstruction(ctx: SmaliParser.SubFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeConstMethodHandleInstruction(ctx: SmaliParser.InvokeConstMethodHandleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitOrLong2addrInstruction(ctx: SmaliParser.OrLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivFloatInstruction(ctx: SmaliParser.DivFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCatchDirective(ctx: SmaliParser.CatchDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitIgetShortInstruction(ctx: SmaliParser.IgetShortInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitVoidType(ctx: SmaliParser.VoidTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitBooleanLiteral(ctx: SmaliParser.BooleanLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodModifier(ctx: SmaliParser.MethodModifierContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeDirectRangeInstruction(ctx: SmaliParser.InvokeDirectRangeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfGeInstruction(ctx: SmaliParser.IfGeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeSuperRangeInstruction(ctx: SmaliParser.InvokeSuperRangeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveFrom16Instruction(ctx: SmaliParser.MoveFrom16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitTargetRegister(ctx: SmaliParser.TargetRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitTernaryInstruction(ctx: SmaliParser.TernaryInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitXorIntLit16Instruction(ctx: SmaliParser.XorIntLit16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitNewInstanceType(ctx: SmaliParser.NewInstanceTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivIntInstruction(ctx: SmaliParser.DivIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemDouble2addrInstruction(ctx: SmaliParser.RemDouble2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCatchFromLabel(ctx: SmaliParser.CatchFromLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitRegisterListRegisters(ctx: SmaliParser.RegisterListRegistersContext): SmaliClass {
        return smaliClass
    }

    override fun visitLongToFloatInstruction(ctx: SmaliParser.LongToFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIgetByteInstruction(ctx: SmaliParser.IgetByteInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIndexRegister(ctx: SmaliParser.IndexRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitAndLong2addrInstruction(ctx: SmaliParser.AndLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeDirectInstruction(ctx: SmaliParser.InvokeDirectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitArrayDataEntry(ctx: SmaliParser.ArrayDataEntryContext): SmaliClass {
        return smaliClass
    }

    override fun visitLineLabel(ctx: SmaliParser.LineLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstInstruction(ctx: SmaliParser.ConstInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitFilledArrayDataLabel(ctx: SmaliParser.FilledArrayDataLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitDoubleType(ctx: SmaliParser.DoubleTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemIntLit16Instruction(ctx: SmaliParser.RemIntLit16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitPackedSwitchDirectiveLabels(ctx: SmaliParser.PackedSwitchDirectiveLabelsContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodInvocationTarget(ctx: SmaliParser.MethodInvocationTargetContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodDeclaration(ctx: SmaliParser.MethodDeclarationContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodBody(ctx: SmaliParser.MethodBodyContext): SmaliClass {
        return smaliClass
    }

    override fun visitIputObjectInstruction(ctx: SmaliParser.IputObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCmplDoubleInstruction(ctx: SmaliParser.CmplDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShortType(ctx: SmaliParser.ShortTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivInt2addrInstruction(ctx: SmaliParser.DivInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitFilledNewArrayInstruction(ctx: SmaliParser.FilledNewArrayInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivDouble2addrInstruction(ctx: SmaliParser.DivDouble2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitByteType(ctx: SmaliParser.ByteTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitIgetInstruction(ctx: SmaliParser.IgetInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitPackedSwitchRegister(ctx: SmaliParser.PackedSwitchRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeStaticRangeInstruction(ctx: SmaliParser.InvokeStaticRangeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLineDirective(ctx: SmaliParser.LineDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodParameterLiteral(ctx: SmaliParser.MethodParameterLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfGtInstruction(ctx: SmaliParser.IfGtInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitOrIntLit8Instruction(ctx: SmaliParser.OrIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitArrayElementType(ctx: SmaliParser.ArrayElementTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitIntToByteInstruction(ctx: SmaliParser.IntToByteInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitFieldModifier(ctx: SmaliParser.FieldModifierContext): SmaliClass {
        return smaliClass
    }

    override fun visitMove16Instruction(ctx: SmaliParser.Move16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivIntLit8Instruction(ctx: SmaliParser.DivIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitXorLong2addrInstruction(ctx: SmaliParser.XorLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAgetShortInstruction(ctx: SmaliParser.AgetShortInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAndIntLit8Instruction(ctx: SmaliParser.AndIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCmpgFloatInstruction(ctx: SmaliParser.CmpgFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveWideFrom16Instruction(ctx: SmaliParser.MoveWideFrom16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSubInt2addrInstruction(ctx: SmaliParser.SubInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitOrInt2addrInstruction(ctx: SmaliParser.OrInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLabel(ctx: SmaliParser.LabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitParse(ctx: SmaliParser.ParseContext): SmaliClass {
        visitClassDirective(ctx.classDirective())
        visitSuperDirective(ctx.superDirective())
        ctx.implementsDirective()?.forEach { visitImplementsDirective(it) }
        ctx.fieldDirective()?.forEach { visitFieldDirective(it) }
        ctx.methodDirective()?.forEach { visitMethodDirective(it) }
        return smaliClass
    }

    override fun visitAddDouble2addrInstruction(ctx: SmaliParser.AddDouble2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLocalDirective(ctx: SmaliParser.LocalDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitLocaDirectiveVariableName(ctx: SmaliParser.LocaDirectiveVariableNameContext): SmaliClass {
        return smaliClass
    }

    override fun visitPackedSwitchLabel(ctx: SmaliParser.PackedSwitchLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitSparseSwitchDirectiveValue(ctx: SmaliParser.SparseSwitchDirectiveValueContext): SmaliClass {
        return smaliClass
    }

    override fun visitAputShortInstruction(ctx: SmaliParser.AputShortInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSGetBooleanInstruction(ctx: SmaliParser.SGetBooleanInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfNezInstruction(ctx: SmaliParser.IfNezInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulIntInstruction(ctx: SmaliParser.MulIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIputShortInstruction(ctx: SmaliParser.IputShortInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSourceName(ctx: SmaliParser.SourceNameContext): SmaliClass {
        return smaliClass
    }

    override fun visitAddIntLit8Instruction(ctx: SmaliParser.AddIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulDoubleInstruction(ctx: SmaliParser.MulDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokePolymorphicInstruction(ctx: SmaliParser.InvokePolymorphicInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitPackedSwitchDirectiveLabel(ctx: SmaliParser.PackedSwitchDirectiveLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitNonVoidType(ctx: SmaliParser.NonVoidTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitNopInstruction(ctx: SmaliParser.NopInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCatchAllDirective(ctx: SmaliParser.CatchAllDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitSGetInstruction(ctx: SmaliParser.SGetInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfLabel(ctx: SmaliParser.IfLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitSGetWideInstruction(ctx: SmaliParser.SGetWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRightRegister(ctx: SmaliParser.RightRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodSignature(ctx: SmaliParser.MethodSignatureContext): SmaliClass {


        return smaliClass
    }

    override fun visitNegLongInstruction(ctx: SmaliParser.NegLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveObjectFrom16Instruction(ctx: SmaliParser.MoveObjectFrom16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodArguments(ctx: SmaliParser.MethodArgumentsContext): SmaliClass {
        return smaliClass
    }

    override fun visitSubIntInstruction(ctx: SmaliParser.SubIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCatchToLabel(ctx: SmaliParser.CatchToLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitOrLongInstruction(ctx: SmaliParser.OrLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIdentifier(ctx: SmaliParser.IdentifierContext): SmaliClass {
        return smaliClass
    }

    override fun visitNegativeNumericLiteral(ctx: SmaliParser.NegativeNumericLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitBooleanType(ctx: SmaliParser.BooleanTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulIntLit8Instruction(ctx: SmaliParser.MulIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAddInt2addrInstruction(ctx: SmaliParser.AddInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShrInt2addrInstruction(ctx: SmaliParser.ShrInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAgetWideInstruction(ctx: SmaliParser.AgetWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAnnotationType(ctx: SmaliParser.AnnotationTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitConst4Instruction(ctx: SmaliParser.Const4InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIputByteInstruction(ctx: SmaliParser.IputByteInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemDoubleInstruction(ctx: SmaliParser.RemDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstString(ctx: SmaliParser.ConstStringContext): SmaliClass {
        return smaliClass
    }

    override fun visitSGetCharInstruction(ctx: SmaliParser.SGetCharInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitUshrInt2addrInstruction(ctx: SmaliParser.UshrInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstStringJumbo(ctx: SmaliParser.ConstStringJumboContext): SmaliClass {
        return smaliClass
    }

    override fun visitField(ctx: SmaliParser.FieldContext): SmaliClass {
        return smaliClass
    }

    override fun visitStringLiteral(ctx: SmaliParser.StringLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitReturnInstruction(ctx: SmaliParser.ReturnInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeVirtualInstruction(ctx: SmaliParser.InvokeVirtualInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShlLong2addrInstruction(ctx: SmaliParser.ShlLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShlIntInstruction(ctx: SmaliParser.ShlIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitUshrIntInstruction(ctx: SmaliParser.UshrIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSGetObjectInstruction(ctx: SmaliParser.SGetObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitFilledNewArrayRangeInstruction(ctx: SmaliParser.FilledNewArrayRangeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSGetShortInstruction(ctx: SmaliParser.SGetShortInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAndIntLit16Instruction(ctx: SmaliParser.AndIntLit16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitXorIntLit8Instruction(ctx: SmaliParser.XorIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSourceDirective(ctx: SmaliParser.SourceDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitOrIntLit16Instruction(ctx: SmaliParser.OrIntLit16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun defaultResult(): SmaliClass {
        return smaliClass
    }

    override fun visitClassModifier(ctx: SmaliParser.ClassModifierContext): SmaliClass {
        return smaliClass
    }

    override fun visitLocalEndDirective(ctx: SmaliParser.LocalEndDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitLocalDirectiveGenericHint(ctx: SmaliParser.LocalDirectiveGenericHintContext): SmaliClass {
        return smaliClass
    }

    override fun visitShrIntLit8Instruction(ctx: SmaliParser.ShrIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSPutShortInstruction(ctx: SmaliParser.SPutShortInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAputBooleanInstruction(ctx: SmaliParser.AputBooleanInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeInterfaceInstruction(ctx: SmaliParser.InvokeInterfaceInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCmpLongInstruction(ctx: SmaliParser.CmpLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitErrorNode(node: ErrorNode): SmaliClass {
        return super.visitErrorNode(node)
    }

    override fun visitClassName(ctx: SmaliParser.ClassNameContext): SmaliClass {
//        smaliClass.parentPackage = project.getPackageForClassName(ctx.text) ?: return smaliClass

        return smaliClass
    }

    override fun visitAgetCharInstruction(ctx: SmaliParser.AgetCharInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAgetBooleanInstruction(ctx: SmaliParser.AgetBooleanInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSparseSwitchRegister(ctx: SmaliParser.SparseSwitchRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveResultWideInstruction(ctx: SmaliParser.MoveResultWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRsubIntLit8Instruction(ctx: SmaliParser.RsubIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDecimalNumericLiteral(ctx: SmaliParser.DecimalNumericLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemIntInstruction(ctx: SmaliParser.RemIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInstanceField(ctx: SmaliParser.InstanceFieldContext): SmaliClass {
        return smaliClass
    }

    override fun visitRegistersDirective(ctx: SmaliParser.RegistersDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitSGetByteInstruction(ctx: SmaliParser.SGetByteInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIntToFloatInstruction(ctx: SmaliParser.IntToFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAddFloatInstruction(ctx: SmaliParser.AddFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAddIntInstruction(ctx: SmaliParser.AddIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCmplFloatInstruction(ctx: SmaliParser.CmplFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfEqInstruction(ctx: SmaliParser.IfEqInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIputInstruction(ctx: SmaliParser.IputInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstWideInstruction(ctx: SmaliParser.ConstWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIntType(ctx: SmaliParser.IntTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitArrayRegister(ctx: SmaliParser.ArrayRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokePolymorphicRangeInstruction(ctx: SmaliParser.InvokePolymorphicRangeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIputCharInstruction(ctx: SmaliParser.IputCharInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitHexFloatLiteral(ctx: SmaliParser.HexFloatLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitSubLongInstruction(ctx: SmaliParser.SubLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIntToLongInstruction(ctx: SmaliParser.IntToLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulFloatInstruction(ctx: SmaliParser.MulFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitGoto32Instruction(ctx: SmaliParser.Goto32InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSubDouble2addrInstruction(ctx: SmaliParser.SubDouble2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitHexNumericLiteral(ctx: SmaliParser.HexNumericLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveObjectInstruction(ctx: SmaliParser.MoveObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIputWideInstruction(ctx: SmaliParser.IputWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitNonArrayType(ctx: SmaliParser.NonArrayTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitInstruction(ctx: SmaliParser.InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivFloat2addrInstruction(ctx: SmaliParser.DivFloat2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCheckInstanceType(ctx: SmaliParser.CheckInstanceTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemLong2addrInstruction(ctx: SmaliParser.RemLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitUshrIntLit8Instruction(ctx: SmaliParser.UshrIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitArrayElementRegisterRange(ctx: SmaliParser.ArrayElementRegisterRangeContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveWideInstruction(ctx: SmaliParser.MoveWideInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitFloatToDoubleInstruction(ctx: SmaliParser.FloatToDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfLeInstruction(ctx: SmaliParser.IfLeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAddLong2addrInstruction(ctx: SmaliParser.AddLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitOrIntInstruction(ctx: SmaliParser.OrIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitReturnVoidInstruction(ctx: SmaliParser.ReturnVoidInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCmpgDoubleInstruction(ctx: SmaliParser.CmpgDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAputObjectInstruction(ctx: SmaliParser.AputObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMonitorEnterInstruction(ctx: SmaliParser.MonitorEnterInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivLong2addrInstruction(ctx: SmaliParser.DivLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveInstruction(ctx: SmaliParser.MoveInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShlInt2addrInstruction(ctx: SmaliParser.ShlInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitParamDirective(ctx: SmaliParser.ParamDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulFloat2addrInstruction(ctx: SmaliParser.MulFloat2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeInterfaceRangeInstruction(ctx: SmaliParser.InvokeInterfaceRangeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLongToIntInstruction(ctx: SmaliParser.LongToIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitNegDoubleInstruction(ctx: SmaliParser.NegDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLongType(ctx: SmaliParser.LongTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitArrayDataDirective(ctx: SmaliParser.ArrayDataDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitBinaryNumericLiteral(ctx: SmaliParser.BinaryNumericLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitFloatType(ctx: SmaliParser.FloatTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitLongToDoubleInstruction(ctx: SmaliParser.LongToDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMethodBodyStatement(ctx: SmaliParser.MethodBodyStatementContext): SmaliClass {
        return smaliClass
    }

    override fun visitInstanceRegister(ctx: SmaliParser.InstanceRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitSPutCharInstruction(ctx: SmaliParser.SPutCharInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemInt2addrInstruction(ctx: SmaliParser.RemInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulDouble2addrInstruction(ctx: SmaliParser.MulDouble2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeCustomRangeInstruction(ctx: SmaliParser.InvokeCustomRangeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCheckCastInstruction(ctx: SmaliParser.CheckCastInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeConstMethodTypeInstruction(ctx: SmaliParser.InvokeConstMethodTypeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitArrayLengthInstruction(ctx: SmaliParser.ArrayLengthInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitArraySizeRegister(ctx: SmaliParser.ArraySizeRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitReturnObjectInstruction(ctx: SmaliParser.ReturnObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemFloatInstruction(ctx: SmaliParser.RemFloatInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAputCharInstruction(ctx: SmaliParser.AputCharInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstWide32Instruction(ctx: SmaliParser.ConstWide32InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLocalDirectiveType(ctx: SmaliParser.LocalDirectiveTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfGtzInstruction(ctx: SmaliParser.IfGtzInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitNewInstanceInstruction(ctx: SmaliParser.NewInstanceInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveObject16Instruction(ctx: SmaliParser.MoveObject16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulInt2addrInstruction(ctx: SmaliParser.MulInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAnnotationScope(ctx: SmaliParser.AnnotationScopeContext): SmaliClass {
        return smaliClass
    }

    override fun visitGoto16Instruction(ctx: SmaliParser.Goto16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDoubleToLongInstruction(ctx: SmaliParser.DoubleToLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAssignableValue(ctx: SmaliParser.AssignableValueContext): SmaliClass {
        return smaliClass
    }

    override fun visitInvokeVirtualRangeInstruction(ctx: SmaliParser.InvokeVirtualRangeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitXorInt2addrInstruction(ctx: SmaliParser.XorInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstClass(ctx: SmaliParser.ConstClassContext): SmaliClass {
        return smaliClass
    }

    override fun visitFieldName(ctx: SmaliParser.FieldNameContext): SmaliClass {
        return smaliClass
    }

    override fun visitNotIntInstruction(ctx: SmaliParser.NotIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMonitorExitInstruction(ctx: SmaliParser.MonitorExitInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivDoubleInstruction(ctx: SmaliParser.DivDoubleInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAndInt2addrInstruction(ctx: SmaliParser.AndInt2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAgetByteInstruction(ctx: SmaliParser.AgetByteInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShlIntLit8Instruction(ctx: SmaliParser.ShlIntLit8InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitMulLongInstruction(ctx: SmaliParser.MulLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitRegisterList(ctx: SmaliParser.RegisterListContext): SmaliClass {
        return smaliClass
    }

    override fun visitFieldNameAndType(ctx: SmaliParser.FieldNameAndTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitPackedSwitchInstruction(ctx: SmaliParser.PackedSwitchInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitPackedSwitchIdent(ctx: SmaliParser.PackedSwitchIdentContext): SmaliClass {
        return smaliClass
    }

    override fun visitDoubleToIntInstruction(ctx: SmaliParser.DoubleToIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitShrLong2addrInstruction(ctx: SmaliParser.ShrLong2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSparseSwitchLabel(ctx: SmaliParser.SparseSwitchLabelContext): SmaliClass {
        return smaliClass
    }

    override fun visitFieldInvocationTarget(ctx: SmaliParser.FieldInvocationTargetContext): SmaliClass {
        return smaliClass
    }

    override fun visitLocalRestartDirective(ctx: SmaliParser.LocalRestartDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitPrimitiveType(ctx: SmaliParser.PrimitiveTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitSubFloat2addrInstruction(ctx: SmaliParser.SubFloat2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitOctNumericLiteral(ctx: SmaliParser.OctNumericLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitMoveExceptionInstruction(ctx: SmaliParser.MoveExceptionInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitLeftRegister(ctx: SmaliParser.LeftRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitXorLongInstruction(ctx: SmaliParser.XorLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfLtInstruction(ctx: SmaliParser.IfLtInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSPutInstruction(ctx: SmaliParser.SPutInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitNegIntInstruction(ctx: SmaliParser.NegIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitCatchExceptionType(ctx: SmaliParser.CatchExceptionTypeContext): SmaliClass {
        return smaliClass
    }

    override fun visitRemLongInstruction(ctx: SmaliParser.RemLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAnnotationValueScoped(ctx: SmaliParser.AnnotationValueScopedContext): SmaliClass {
        return smaliClass
    }

    override fun visitLocalDirectiveRegister(ctx: SmaliParser.LocalDirectiveRegisterContext): SmaliClass {
        return smaliClass
    }

    override fun visitNotLongInstruction(ctx: SmaliParser.NotLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAndIntInstruction(ctx: SmaliParser.AndIntInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitDivLongInstruction(ctx: SmaliParser.DivLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSparseSwitchInstruction(ctx: SmaliParser.SparseSwitchInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfNeInstruction(ctx: SmaliParser.IfNeInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitAddFloat2addrInstruction(ctx: SmaliParser.AddFloat2addrInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSPutBooleanInstruction(ctx: SmaliParser.SPutBooleanInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitConstWide16Instruction(ctx: SmaliParser.ConstWide16InstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitSPutObjectInstruction(ctx: SmaliParser.SPutObjectInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitIfLezInstruction(ctx: SmaliParser.IfLezInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitUshrLongInstruction(ctx: SmaliParser.UshrLongInstructionContext): SmaliClass {
        return smaliClass
    }

    override fun visitNullLiteral(ctx: SmaliParser.NullLiteralContext): SmaliClass {
        return smaliClass
    }

    override fun visitImplementsDirective(ctx: SmaliParser.ImplementsDirectiveContext): SmaliClass {
        project.getOrCreateClass(ctx.referenceType().text)?.also {
            smaliClass.interfaces.add(it)
        }
        return smaliClass
    }

    override fun visitEnumType(ctx: SmaliParser.EnumTypeContext?): SmaliClass {
        return smaliClass
    }

    override fun visitEnumDirective(ctx: SmaliParser.EnumDirectiveContext?): SmaliClass {
        return smaliClass
    }

    override fun visitSubannotationDirective(ctx: SmaliParser.SubannotationDirectiveContext?): SmaliClass {
        return smaliClass
    }

    override fun visitCharLiteral(ctx: SmaliParser.CharLiteralContext?): SmaliClass {
        return smaliClass
    }

    override fun visitAbstarctMethodBodyStatement(ctx: SmaliParser.AbstarctMethodBodyStatementContext?): SmaliClass {
        return smaliClass
    }

    override fun visitFullParamDirective(ctx: SmaliParser.FullParamDirectiveContext?): SmaliClass {
        return smaliClass
    }
}
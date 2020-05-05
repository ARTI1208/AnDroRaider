package ru.art2000.androraider.model.analyzer.smali

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor
import org.antlr.v4.runtime.tree.ErrorNode
import ru.art2000.androraider.model.analyzer.result.Error
import ru.art2000.androraider.model.analyzer.result.ProjectAnalyzeResult
import ru.art2000.androraider.model.analyzer.smali.types.SmaliClass
import ru.art2000.androraider.model.analyzer.smali.types.SmaliMethod
import ru.art2000.androraider.utils.parseCompound
import ru.art2000.androraider.utils.textRange
import java.lang.Exception

class SmaliDependencyAnalyzer(val project: ProjectAnalyzeResult, var smaliClass: SmaliClass) :
        AbstractParseTreeVisitor<SmaliClass>(), SmaliParserVisitor<SmaliClass> {

    override fun visitClassDirective(ctx: SmaliParser.ClassDirectiveContext): SmaliClass {
        return smaliClass
    }

    override fun visitNegFloatInstruction(ctx: SmaliParser.NegFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitShrLongInstruction(ctx: SmaliParser.ShrLongInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodReturnType(ctx: SmaliParser.MethodReturnTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFieldDirective(ctx: SmaliParser.FieldDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNewArrayInstruction(ctx: SmaliParser.NewArrayInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetInstruction(ctx: SmaliParser.AgetInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodDirective(ctx: SmaliParser.MethodDirectiveContext): SmaliClass {
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
        return visitChildren(ctx)
    }

    override fun visitPackedSwitchDirective(ctx: SmaliParser.PackedSwitchDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetBooleanInstruction(ctx: SmaliParser.IgetBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetCharInstruction(ctx: SmaliParser.IgetCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitGotoInstruction(ctx: SmaliParser.GotoInstructionContext): SmaliClass {
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
        return visitChildren(ctx)
    }

    override fun visitConst16Instruction(ctx: SmaliParser.Const16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNumericLiteral(ctx: SmaliParser.NumericLiteralContext): SmaliClass {
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

    override fun visitMoveResultObjectInstruction(ctx: SmaliParser.MoveResultObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitMethodParameterType(ctx: SmaliParser.MethodParameterTypeContext): SmaliClass {
        var offset = 0
        parseCompound(ctx.text).forEach {
            if (!project.exists(project.classByName(ctx.text.substring(offset, offset + it.lastIndex))))
                smaliClass.ranges.add(Error((ctx.start.startIndex + offset)..(ctx.start.startIndex + offset + it.lastIndex), "Class ${it} not found"))

            offset += it.length
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
        val clazz = project.classByName(ctx.text)
        if (!project.exists(clazz)) {
            smaliClass.ranges.add(Error(ctx.textRange, "Class ${ctx.text} not found"))
        }

        return visitChildren(ctx)
    }

    override fun visitInvokeStaticInstruction(ctx: SmaliParser.InvokeStaticInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitInstanceOfInstruction(ctx: SmaliParser.InstanceOfInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAputInstruction(ctx: SmaliParser.AputInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetWideInstruction(ctx: SmaliParser.IgetWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitFloatNumericLiteral(ctx: SmaliParser.FloatNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIgetObjectInstruction(ctx: SmaliParser.IgetObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorIntInstruction(ctx: SmaliParser.XorIntInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitLabelName(ctx: SmaliParser.LabelNameContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRegisterIdentifier(ctx: SmaliParser.RegisterIdentifierContext): SmaliClass {
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
        return visitChildren(ctx)
    }

    override fun visitRemIntLit8Instruction(ctx: SmaliParser.RemIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSPutWideInstruction(ctx: SmaliParser.SPutWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetObjectInstruction(ctx: SmaliParser.AgetObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        return visitChildren(ctx)
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

//        smaliClass.parentClass = project.getOrCreateClass(ctx.text)

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
        return visitChildren(ctx)
    }

    override fun visitLocalsDirective(ctx: SmaliParser.LocalsDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstHigh16Instruction(ctx: SmaliParser.ConstHigh16InstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitVoidType(ctx: SmaliParser.VoidTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitBooleanLiteral(ctx: SmaliParser.BooleanLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMethodModifier(ctx: SmaliParser.MethodModifierContext): SmaliClass {
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
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitIndexRegister(ctx: SmaliParser.IndexRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSimpleParamDirective(ctx: SmaliParser.SimpleParamDirectiveContext): SmaliClass {
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
        return visitChildren(ctx)
    }

    override fun visitConstInstruction(ctx: SmaliParser.ConstInstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        val targetClass = project.classByName(ctx.referenceOrArrayType().text)
        val targetMethod = targetClass?.methods?.find { it.name == ctx.methodSignature().methodIdentifier().text }
        if (targetClass == null || targetMethod == null) {
            smaliClass.ranges.add(Error(ctx.methodSignature().textRange, "Method not found"))
        }

        return visitChildren(ctx)
    }

    inner class MethodDirectiveContextWrapper(ctx: SmaliParser.MethodDirectiveContext) :
            SmaliParser.MethodDirectiveContext(ctx.getParent(), ctx.invokingState) {

        val smaliMethod = SmaliMethod()

        init {
            children = ctx.children
            children.forEach {
                it.setParent(this)
            }
            smaliMethod.parentClass = smaliClass
        }

    }

    override fun visitMethodDeclaration(ctx: SmaliParser.MethodDeclarationContext): SmaliClass {
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
        return visitChildren(ctx)
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

    override fun visitFieldModifier(ctx: SmaliParser.FieldModifierContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMove16Instruction(ctx: SmaliParser.Move16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitDivIntLit8Instruction(ctx: SmaliParser.DivIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorLong2addrInstruction(ctx: SmaliParser.XorLong2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetShortInstruction(ctx: SmaliParser.AgetShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndIntLit8Instruction(ctx: SmaliParser.AndIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitCmpgFloatInstruction(ctx: SmaliParser.CmpgFloatInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveWideFrom16Instruction(ctx: SmaliParser.MoveWideFrom16InstructionContext): SmaliClass {
        return visitChildren(ctx)
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
//        ctx.statement()
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
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitIfLabel(ctx: SmaliParser.IfLabelContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetWideInstruction(ctx: SmaliParser.SGetWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitAnnotationType(ctx: SmaliParser.AnnotationTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConst4Instruction(ctx: SmaliParser.Const4InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIputByteInstruction(ctx: SmaliParser.IputByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitRemDoubleInstruction(ctx: SmaliParser.RemDoubleInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstString(ctx: SmaliParser.ConstStringContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetCharInstruction(ctx: SmaliParser.SGetCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitUshrInt2addrInstruction(ctx: SmaliParser.UshrInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitConstStringJumbo(ctx: SmaliParser.ConstStringJumboContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitField(ctx: SmaliParser.FieldContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitStringLiteral(ctx: SmaliParser.StringLiteralContext): SmaliClass {
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
        return visitChildren(ctx)
    }

    override fun visitFilledNewArrayRangeInstruction(ctx: SmaliParser.FilledNewArrayRangeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSGetShortInstruction(ctx: SmaliParser.SGetShortInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAndIntLit16Instruction(ctx: SmaliParser.AndIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitXorIntLit8Instruction(ctx: SmaliParser.XorIntLit8InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSourceDirective(ctx: SmaliParser.SourceDirectiveContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitOrIntLit16Instruction(ctx: SmaliParser.OrIntLit16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun defaultResult(): SmaliClass {
        return smaliClass
    }

    override fun visitClassModifier(ctx: SmaliParser.ClassModifierContext): SmaliClass {
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
        return super.visitErrorNode(node)
    }

    override fun visitClassName(ctx: SmaliParser.ClassNameContext): SmaliClass {
//        smaliClass.parentPackage = project.getPackageForClassName(ctx.text) ?: return smaliClass

        return smaliClass
    }

    override fun visitAgetCharInstruction(ctx: SmaliParser.AgetCharInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAgetBooleanInstruction(ctx: SmaliParser.AgetBooleanInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitSparseSwitchRegister(ctx: SmaliParser.SparseSwitchRegisterContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveResultWideInstruction(ctx: SmaliParser.MoveResultWideInstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitSGetByteInstruction(ctx: SmaliParser.SGetByteInstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitSubDouble2addrInstruction(ctx: SmaliParser.SubDouble2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitHexNumericLiteral(ctx: SmaliParser.HexNumericLiteralContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveObjectInstruction(ctx: SmaliParser.MoveObjectInstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        return visitChildren(ctx)
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
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitInvokeConstMethodTypeInstruction(ctx: SmaliParser.InvokeConstMethodTypeInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitArrayLengthInstruction(ctx: SmaliParser.ArrayLengthInstructionContext): SmaliClass {
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

    override fun visitLocalDirectiveType(ctx: SmaliParser.LocalDirectiveTypeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitIfGtzInstruction(ctx: SmaliParser.IfGtzInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitNewInstanceInstruction(ctx: SmaliParser.NewInstanceInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMoveObject16Instruction(ctx: SmaliParser.MoveObject16InstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitMulInt2addrInstruction(ctx: SmaliParser.MulInt2addrInstructionContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitAnnotationScope(ctx: SmaliParser.AnnotationScopeContext): SmaliClass {
        return visitChildren(ctx)
    }

    override fun visitGoto16Instruction(ctx: SmaliParser.Goto16InstructionContext): SmaliClass {
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
        return visitChildren(ctx)
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
        val targetClass = project.classByName(ctx.referenceOrArrayType().text)
        val targetMethod = targetClass?.fields?.find { it.name == ctx.fieldNameAndType().fieldName().text }
        if (targetClass == null || targetMethod == null) {
            smaliClass.ranges.add(Error(ctx.fieldNameAndType().textRange, "Field not found"))
        }

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
        return visitChildren(ctx)
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
        return visitChildren(ctx)
    }

//    override fun visitComment(ctx: SmaliParser.CommentContext): SmaliClass {
//
//        smaliClass.ranges.add(RangeStatusBase(ctx.textRange, "Comment", listOf("comment")))
//
//        return smaliClass
//    }

}
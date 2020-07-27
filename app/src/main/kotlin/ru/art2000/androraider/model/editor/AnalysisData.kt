package ru.art2000.androraider.model.editor

import org.reactfx.value.Val
import org.reactfx.value.Var
import ru.art2000.androraider.model.analyzer.result.Project
import tornadofx.getValue
import tornadofx.setValue

class AnalysisData(project: Project) : StatusBarDataProvider {

    override val dataList: List<Val<out StatusBarElement>>

    val errorIndicatorProperty = Var.newSimpleVar(ErrorIndicator(project.errorList))

    var errorIndicator by errorIndicatorProperty

    init {
        dataList = listOf(errorIndicatorProperty)
    }
}
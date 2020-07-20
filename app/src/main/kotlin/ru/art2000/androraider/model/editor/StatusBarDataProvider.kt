package ru.art2000.androraider.model.editor

import org.reactfx.value.Var

interface StatusBarDataProvider {

    val dataList: List<Var<out StatusBarElement>>
}
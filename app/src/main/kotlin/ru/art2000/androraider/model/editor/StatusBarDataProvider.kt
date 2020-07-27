package ru.art2000.androraider.model.editor

import org.reactfx.value.Val

interface StatusBarDataProvider {

    val dataList: List<Val<out StatusBarElement>>
}
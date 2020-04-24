package ru.art2000.androraider.view.editor.menu.search

import javafx.beans.property.Property
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import ru.art2000.androraider.mvp.IController
import ru.art2000.androraider.view.editor.Searchable

interface ISearchItemController : IController {

    val searchScopeLabel : Label
    val searchField : TextField
    val findPreviousButton : Button
    val findNextButton : Button

    val currentSearchableProperty: Property<Searchable<String>?>
    var currentSearchable: Searchable<String>?
}
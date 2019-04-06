package ru.art2000.androraider

import javafx.scene.control.CheckBox
import javafx.scene.layout.Pane

fun Pane.goThrough(list : ArrayList<ApktoolCommand>){
    for (ch in this.childrenUnmodifiable){
        if (ch is Pane) {
            ch.goThrough(list)
        } else if (ch is CheckBox && ch.id.startsWith('-') && ch.isSelected) {
            list.add(ApktoolCommand(ch.id))
            System.out.println("Found")
        }
    }
}
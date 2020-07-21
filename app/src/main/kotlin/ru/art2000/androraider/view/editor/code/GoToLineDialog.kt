package ru.art2000.androraider.view.editor.code

import javafx.event.EventHandler
import javafx.scene.control.ButtonType
import javafx.scene.control.Dialog
import javafx.util.Callback
import ru.art2000.androraider.model.App
import ru.art2000.androraider.model.editor.file.CaretPosition
import ru.art2000.androraider.view.dialogs.icons

class GoToLineDialog(caretPosition: CaretPosition) : Dialog<Pair<Int, Int>>(),
        IGoToLineController by GoToLineController() {

    init {
        title = "Go to line/column"
        icons.add(App.LOGO)

        dialogPane.content = root
        dialogPane.buttonTypes.addAll(ButtonType.OK, ButtonType.CLOSE)

        val line = caretPosition.line
        val column = caretPosition.column

        hintLabel.text = "[Line][:column]:"
        textField.text = "${line + 1}:${column + 1}"


        var result: Pair<Int, Int> = -1 to 0

        resultConverter = Callback { button ->
            if (button == ButtonType.OK) {
                val txt = textField.text
                val strNums = txt.split(":")
                val nums = strNums.mapNotNull { it.toIntOrNull() }

                if (nums.size != strNums.size)
                    return@Callback -1 to 0

                when (nums.size) {
                    1 -> nums.first() - 1 to 0
                    2 -> nums[0] - 1 to nums[1] - 1
                    else -> -1 to 0
                }.also {
                    result = it
                }
            } else {
                (line to column).also {
                    result = it
                }
            }
        }

        onCloseRequest = EventHandler { event ->
            if (result.first < 0 || result.second < 0)
                event.consume()
        }

        // TODO: focus text field
//        onShowing = EventHandler {
//            textField.requestFocus()
//            textField.selectAll()
//        }
//
//        onShown = EventHandler {
//            textField.requestFocus()
//            textField.selectAll()
//        }
//
//        textField.selectAll()
    }


}
@file:Suppress("unused")

package ru.art2000.androraider.model.io

import javafx.scene.Node
import javafx.stage.Window

private val outputMutableMap = mutableMapOf<Int, StreamOutput>()

public fun registerStreamOutput(window: Window, output: StreamOutput) {
    outputMutableMap[window.hashCode()] = output
}

public fun unregisterStreamOutput(window: Window) {
    outputMutableMap.remove(window.hashCode())
}

public fun getStreamOutput(window: Window): StreamOutput {
    return outputMutableMap.getOrDefault(window.hashCode(), DefaultStreamOutput)
}

public fun getStreamOutput(node: Node): StreamOutput {
    if (node.scene == null)
        return DefaultStreamOutput

    return outputMutableMap.getOrDefault(node.scene.window.hashCode(), DefaultStreamOutput)
}

public fun println(window: Window, tag: Any, string: Any) {
    getStreamOutput(window).writeln(tag.toString(), string.toString())
}

public fun println(node: Node, tag: Any, string: Any) {
    getStreamOutput(node).writeln(tag.toString(), string.toString())
}
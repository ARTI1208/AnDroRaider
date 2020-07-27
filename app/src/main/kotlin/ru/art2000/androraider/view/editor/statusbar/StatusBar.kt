package ru.art2000.androraider.view.editor.statusbar

import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.Tooltip
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.text.Text
import javafx.util.Duration
import org.reactfx.Subscription
import ru.art2000.androraider.model.editor.StatusBarDataProvider
import ru.art2000.androraider.model.editor.StatusBarElement
import ru.art2000.androraider.utils.bind
import ru.art2000.androraider.utils.bindOnFXThread
import ru.art2000.androraider.utils.checkedRunLater

class StatusBar: BorderPane() {

    private val subscripions = hashMapOf<Class<*>, List<Subscription>>()

    private val texts = hashMapOf<Class<*>, List<Node>>()

    private val statusText = Text()

    init {
        left = statusText
        setMargin(left, Insets(.0, .0, .0, 5.0))
    }

    public fun setStatus(status: String) {
        statusText.text = status
    }

    public fun addDataProvider(dataProvider: StatusBarDataProvider) {
        removeDataProviderInternal(dataProvider.javaClass)

        val newTexts = mutableListOf<Node>()

        val newSubscriptions = dataProvider.dataList.map {
            val t = Button().apply {
                styleClass.add("status-button")
            }
            newTexts.add(t)

            bindElement(it.value, t)
            it.observeChanges { _, _, newValue ->
                checkedRunLater {
                    bindElement(newValue, t)
                }
            }
        }

        texts[dataProvider.javaClass] = newTexts
        subscripions[dataProvider.javaClass] = newSubscriptions

        updateEditData()
    }

    private fun bindElement(element: StatusBarElement, button: Button) {
        button.textProperty().bindOnFXThread(element.displayedValue)
        button.graphicProperty().bindOnFXThread(element.icon) {
            ImageView(it)
        }
        button.tooltip = Tooltip(element.description).apply {
            showDuration = Duration.INDEFINITE
        }
        button.setOnAction {
            element.action?.accept(button)
        }
        button.disableProperty().unbind()
        button.disableProperty().bindOnFXThread(element.active.not())
    }

    private fun removeDataProviderInternal(dataProviderClass: Class<*>) {
        subscripions.remove(dataProviderClass)?.also { list ->
            list.forEach { it.unsubscribe() }
        }

        texts.remove(dataProviderClass)
    }

    public fun removeDataProvider(dataProviderClass: Class<*>) {
        removeDataProviderInternal(dataProviderClass)
        updateEditData()
    }

    private fun updateEditData() {
        val hBox = HBox()
        hBox.children += texts.values.flatten()
        right = hBox
    }

}
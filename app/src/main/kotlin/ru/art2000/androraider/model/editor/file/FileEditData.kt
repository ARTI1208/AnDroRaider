package ru.art2000.androraider.model.editor.file

import org.reactfx.value.Var
import ru.art2000.androraider.model.analyzer.result.AndroidAppProject
import ru.art2000.androraider.model.editor.SearchData
import ru.art2000.androraider.model.editor.StatusBarDataProvider
import ru.art2000.androraider.model.editor.StatusBarElement
import ru.art2000.androraider.model.editor.StatusBarElementBase
import ru.art2000.androraider.utils.bind
import ru.art2000.androraider.view.editor.statusbar.FileEditActions
import tornadofx.getValue
import tornadofx.setValue
import java.io.File
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.function.Consumer

class FileEditData(val file: File, val project: AndroidAppProject? = null): StatusBarDataProvider {

    val searchData = SearchData()

    val positionProperty: Var<CaretPosition>
            = Var.newSimpleVar(CaretPosition(0, 0))

    val lineSeparatorProperty: Var<LineSeparator> = Var.newSimpleVar(LineSeparator.LF)

    val charsetProperty: Var<StatusBarElementBase<Charset>> =
            Var.newSimpleVar(StatusBarElementBase(StandardCharsets.UTF_8))

    val indentConfigurationProperty: Var<IndentConfiguration> =
            Var.newSimpleVar(IndentConfiguration(IndentConfiguration.IndentType.SPACE, 4))

    var position: CaretPosition by positionProperty

    var lineSeparator: LineSeparator by lineSeparatorProperty

    var charset: Charset
        get() = charsetProperty.value.element
        set(value) {
            charsetProperty.value = StatusBarElementBase(value)
        }

    var indentConfiguration: IndentConfiguration by indentConfigurationProperty

    private val lineSeparatorElementProperty: Var<LineSeparatorElement>
            = Var.newSimpleVar(null)

    private val fileLockProperty: Var<FileLockElement> =
            Var.newSimpleVar(null)

    public val isEditableProperty: Var<Boolean> = Var.newSimpleVar(true)

    public val isEditable: Boolean by isEditableProperty

    override val dataList: List<Var<out StatusBarElement>>

    init {
        lineSeparatorElementProperty.bind(lineSeparatorProperty) {
            createLineSeparatorElement(it)
        }

        fileLockProperty.value = FileLockElement(!isReadOnly(), isProjectPart(), Consumer {
            FileEditActions.toggleReadOnly(file, fileLockProperty)
        })

        isEditableProperty.bind(fileLockProperty) {
            it.canWrite
        }

        val readOnlyDependents = listOf(
                lineSeparatorElementProperty,
                charsetProperty,
                indentConfigurationProperty
        )

        readOnlyDependents.forEach { property ->
            registerDependant(property.value)
            property.addListener { _, _, newElement ->
                registerDependant(newElement)
            }
        }


        dataList = listOf(
                positionProperty,
                lineSeparatorElementProperty,
                charsetProperty,
                indentConfigurationProperty,
                fileLockProperty
        )
    }

    private fun registerDependant(dependence: StatusBarElement) {
        dependence.active.unbind()
        dependence.active.bind(fileLockProperty) {
            it.canWrite
        }
    }

    private fun isProjectPart(): Boolean {
        if (project == null)
            return false

        val relativeFile = file.relativeToOrNull(project.projectFolder)
        return relativeFile != null
    }

    private fun isReadOnly(): Boolean {
        return !isProjectPart() || !file.canWrite()
    }

    private fun createLineSeparatorElement(lineSeparator: LineSeparator): LineSeparatorElement {
        return LineSeparatorElement(lineSeparator,
                FileEditActions.getLineSeparatorAction(lineSeparatorProperty, lineSeparator))
    }
}
package ru.art2000.androraider.model.analyzer.smali.types

import java.io.File

class SmaliMethod() : SmaliComponent {

    object Modifier {

        // In [Modifier] it is *static final int BRIDGE*
        const val CONSTRUCTOR = 0x00000040
    }

    var name: String = ""
    lateinit var returnType: SmaliClass
    var parentClass: SmaliClass? = null
        set(value) {
            if (value != field) {
                val fcp = field
                field = value

                fcp?.removeMethod(this)
                value?.methods?.add(this)
            }
        }

    constructor(name: String,
                returnType: SmaliClass,
                parentClass: SmaliClass? = null) : this() {
        this.name = name
        this.returnType = returnType
        this.parentClass = parentClass
    }

    var modifier = 0

    fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    val labels = mutableListOf<SmaliLabel>()

    override val file: File?
        get() = parentClass?.associatedFile

    override var textRange = SmaliComponent.EMPTY_RANGE
    override val fullname: String
        get() = name

    override fun markAsNotExisting() {
        textRange = SmaliComponent.EMPTY_RANGE
    }

    override fun exists(): Boolean {
        return textRange.first >= 0
    }

    override fun toSmaliString(): String {
        return "$name(${parametersInternal.joinToString { it.toSmaliString() }})${returnType.toSmaliString()}"
    }

    override fun toString(): String {
        return "SmaliMethod(name='$name', args='${parametersInternal.joinToString { it.fullname }}', returnType='${returnType.fullname}', parentClass=${parentClass?.fullname}, modifier=$modifier)"
    }

    val parametersInternal = mutableListOf<SmaliClass>()

    fun addParameter(smaliClass: SmaliClass) {
        parametersInternal.add(smaliClass)
    }

    fun addParameters(vararg smaliClass: SmaliClass) {
        parametersInternal.addAll(smaliClass)
    }

    fun addParameters(smaliClasses: List<SmaliClass>) {
        parametersInternal.addAll(smaliClasses)
    }

    fun getOrCreateLabel(name: String): SmaliLabel {
        val label = labels.find { it.fullname == name }
        if (label != null)
            return label

        return SmaliLabel(name, this).also { labels.add(it) }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SmaliMethod

        if (name != other.name) return false
        if (returnType != other.returnType) return false
        if (parentClass != other.parentClass) return false
        if (parametersInternal != other.parametersInternal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + returnType.hashCode()
        result = 31 * result + (parentClass?.hashCode() ?: 0)
        result = 31 * result + parametersInternal.hashCode()
        return result
    }


    val registerToClassMap = mutableMapOf<String, SmaliClass>()

    val parameters: List<SmaliClass>
        get() = if (java.lang.reflect.Modifier.isStatic(modifier))
            parametersInternal.toList()
        else
            mutableListOf(parentClass!!).also { it.addAll(parametersInternal) }

    var locals = 0
        set(value) {
            if (value == field)
                return

            field = value
            registers = value + parametersInternal.size + if (java.lang.reflect.Modifier.isStatic(modifier)) 0 else 1
        }

    var registers = 0
        set(value) {
            if (value == field)
                return

            field = value
            locals = value - (parametersInternal.size + if (java.lang.reflect.Modifier.isStatic(modifier)) 0 else 1)
        }


}
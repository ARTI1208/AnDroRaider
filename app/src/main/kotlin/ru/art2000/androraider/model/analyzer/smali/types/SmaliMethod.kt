package ru.art2000.androraider.model.analyzer.smali.types

import java.io.File

class SmaliMethod(): SmaliComponent {

    object Modifier {

        // In [Modifier] it is *static final int BRIDGE*
        public const val CONSTRUCTOR = 0x00000040
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

    public fun setModifierBit(modifierBit: Int) {
        modifier = modifier or modifierBit
    }

    override val file: File?
        get() = parentClass?.associatedFile

    override var textRange = -1..0
    override val fullname: String
        get() = name

    override fun recheck(): SmaliComponent? {
        var parent = parentClass
        var method: SmaliMethod? = this
        while (parent != null) {
            if (method != null)
                break

            method = parent.findMethod(name, parametersInternal, returnType.fullname("L", "/"))
            parent = parent.parentClass
        }

        if (method != null && method != this) {
//            parentClass?.removeMethod(this)
            return method
        }

        return method ?: this
    }

    override fun markAsNotExisting() {
        textRange = -1..0
    }

    override fun exists(): Boolean {
        return textRange.first >= 0
    }

    override fun toString(): String {
        return "SmaliMethod(name='$name', args='${parametersInternal.joinToString { it.fullname }}', returnType='${returnType.fullname}', parentClass=${parentClass?.fullname}, modifier=$modifier)"
    }

    val parametersInternal = mutableListOf<SmaliClass>()

    public fun addParameter(smaliClass: SmaliClass) {
        parametersInternal.add(smaliClass)
    }

    public fun addParameters(vararg smaliClass: SmaliClass) {
        parametersInternal.addAll(smaliClass)
    }

    public fun addParameters(smaliClasses: List<SmaliClass>) {
        parametersInternal.addAll(smaliClasses)
    }

    public val parameters: List<SmaliClass>
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
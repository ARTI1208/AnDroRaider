package ru.art2000.androraider.model.analyzer.smali.types

class SmaliMethod() {

    object Modifier {

        // In [Modifier] it is *static final int BRIDGE*
        public const val CONSTRUCTOR = 0x00000040
    }

    var name: String = ""
    lateinit var returnType: SmaliClass
    var parentClass: SmaliClass? = null
        set(value) {
            if (value != field) {
                field?.methods?.remove(this)
                value?.methods?.add(this)
                field = value
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

    override fun toString(): String {
        return "SmaliMethod(name='$name', args='${parametersInternal.joinToString { it.fullname }}', returnType='${returnType.fullname}', parentClass=${parentClass?.fullname}, modifier=$modifier)"
    }

    val parametersInternal = mutableListOf<SmaliClass>()

    public val parameters: List<SmaliClass>
        get() = if (java.lang.reflect.Modifier.isStatic(modifier))
            parametersInternal.toList()
        else
            mutableListOf(parentClass!!).also { it.addAll(parametersInternal) }
}
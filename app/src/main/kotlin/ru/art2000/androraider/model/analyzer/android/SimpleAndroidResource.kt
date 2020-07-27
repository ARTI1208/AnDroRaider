package ru.art2000.androraider.model.analyzer.android

class SimpleAndroidResource(
        override val name: String,
        override val type: String,
        override val scope: ResourceScope = ResourceScope.LOCAL
) : AndroidResource {

    private val str = "${if (scope == ResourceScope.FRAMEWORK) "android:" else ""}$type/$name"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SimpleAndroidResource

        if (name != other.name) return false
        if (type != other.type) return false
        if (scope != other.scope) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + scope.hashCode()
        return result
    }

    override fun toString() = str
}
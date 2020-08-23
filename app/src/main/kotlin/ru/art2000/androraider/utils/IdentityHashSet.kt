package ru.art2000.androraider.utils

import java.util.HashSet

class IdentityHashSet<E> : HashSet<E>() {

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
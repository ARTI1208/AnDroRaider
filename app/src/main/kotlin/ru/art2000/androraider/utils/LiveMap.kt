package ru.art2000.androraider.utils

import javafx.collections.MapChangeListener
import javafx.collections.ObservableMap
import org.reactfx.Subscription
import org.reactfx.value.Val
import java.util.function.Supplier

public interface LiveMap<K, V> : ObservableMap<K, V> {

    public val sizeProperty: Val<Int>

    public fun observeChanges(listener: MapChangeListener<in K, in V>): Subscription {
        addListener(listener)
        return Subscription { removeListener(listener) }
    }

    public fun observeValueChanges(key: K): Val<V?> {
        return Val.create(Supplier { get(key) }, this)
    }
}
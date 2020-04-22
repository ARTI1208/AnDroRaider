package ru.art2000.androraider.model.editor

class SearchSpanList() : ArrayList<IntRange>() {

    var searchString: String? = null
        set(value) {
            if (field != value) {
                clear()
                field = value
            }
        }

    override fun clear() {
        super.clear()
        searchString = null
    }

}
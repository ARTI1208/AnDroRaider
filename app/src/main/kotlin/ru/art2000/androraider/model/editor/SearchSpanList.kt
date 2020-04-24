package ru.art2000.androraider.model.editor

class SearchSpanList() : ArrayList<IntRange>() {

    var searchString: String = ""
        set(value) {
            if (field != value) {
                super.clear()
                field = value
            }
        }

    override fun clear() {
        searchString = ""
    }

}
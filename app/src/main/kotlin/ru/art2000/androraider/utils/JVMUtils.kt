package ru.art2000.androraider.utils

fun String.count(substring: String, from: Int = 0, to: Int = length): Int {
    if (substring.isEmpty())
        return 0

    var k = 0
    var match = 0
    for (i in from until to) {

        if (substring[match] == this[i]) {
            ++match
            if (match == substring.length) {
                ++k
                match = 0
            }
        } else if (substring[0] == this[i]) {
            match = 1
            if (match == substring.length) {
                ++k
                match = 0
            }
        }
    }
    return k
}

fun String.count(c: Char, from: Int = 0, to: Int = length): Int {
    var k = 0
    for (i in from until to) {
        if (this[i] == c)
            ++k
    }
    return k
}
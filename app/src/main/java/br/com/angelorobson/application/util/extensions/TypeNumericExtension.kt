package br.com.angelorobson.application.util.extensions

import java.text.NumberFormat
import java.util.*

fun Int.notEqual(comparator: Int): Boolean {
    return this != comparator
}

fun Int.isEqual(comparator: Int): Boolean {
    return this == comparator
}

fun Int.getNumberFormat(): String {
    return NumberFormat.getNumberInstance(Locale("PT", "br")).format(this)
}
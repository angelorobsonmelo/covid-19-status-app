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
    return when (Locale.getDefault().language) {
        "en" -> {
            NumberFormat.getNumberInstance(Locale.ENGLISH).format(this)
        }
        else -> {
            return NumberFormat.getNumberInstance(Locale("PT", "br")).format(this)
        }
    }
}
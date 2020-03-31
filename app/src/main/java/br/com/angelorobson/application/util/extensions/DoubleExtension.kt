package br.com.angelorobson.application.util.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.getDecimals(decimalFormat: String = "#.##"): String {
    val dFormat =
        DecimalFormat(decimalFormat, DecimalFormatSymbols(Locale("pt", "BR")))

    return dFormat.format(this)
}
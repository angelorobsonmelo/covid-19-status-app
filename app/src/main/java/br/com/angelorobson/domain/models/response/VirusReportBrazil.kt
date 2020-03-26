package br.com.angelorobson.domain.models.response

import java.util.Date

data class VirusReportBrazil(
    val uid: Int,
    val uf: String,
    val state: String,
    var stateImageResId: Int,
    val cases: Int,
    val deaths: Int,
    val datetime: Date
)
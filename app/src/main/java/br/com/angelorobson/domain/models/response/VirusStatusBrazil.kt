package br.com.angelorobson.domain.models.response

import java.util.*

data class VirusStatusBrazil(
    val country: String,
    val cases: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    val updatedAt: Date
)
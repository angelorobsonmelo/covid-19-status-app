package br.com.angelorobson.domain.models.response

data class VirusStatusBrazil(
    val country: String,
    val cases: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int
)
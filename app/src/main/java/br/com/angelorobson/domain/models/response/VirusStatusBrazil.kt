package br.com.angelorobson.domain.models.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class VirusStatusBrazil(
    val country: String,
    val cases: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    @SerializedName("updated_at")
    val updatedAt: Date
)
package br.com.angelorobson.domain.models.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class VirusReportCountry(
    val country: String,
    val cases: Int,
    val confirmed: Int,
    val deaths: Int,
    val recovered: Int,
    @SerializedName("updated_at")
    val updatedAt: Date
)
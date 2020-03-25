package br.com.angelorobson.domain.models.response

import org.threeten.bp.LocalDateTime

data class StateStatusResponse(
    val uid: Int,
    val uf: String,
    val state: String,
    var stateImageResId: Int,
    val cases: Int,
    val deaths: Int,
    val datetime: LocalDateTime
)
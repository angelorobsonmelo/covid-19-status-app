package br.com.angelorobson.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey
    val id: Int,
    val title: String,
    val url: String,
    @ColumnInfo(name = "thumbnail_url")
    val thumbnailUrl: String
)
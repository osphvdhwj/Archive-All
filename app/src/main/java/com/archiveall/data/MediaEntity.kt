package com.archiveall.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_items")
data class MediaEntity(
    @PrimaryKey val id: String,
    val mediaType: String,
    val source: String,
    val sourcePath: String,
    val mimeType: String,

    val title: String?,
    val description: String?,
    val thumbnailPath: String?,

    val tags: String,
    val rating: Int?,
    val nsfw: Boolean,

    val createdAt: Long,
    val updatedAt: Long
)

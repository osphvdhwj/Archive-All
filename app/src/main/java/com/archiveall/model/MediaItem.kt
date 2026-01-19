package com.archiveall.model

import java.util.UUID

enum class MediaType {
    PHOTO,
    VIDEO,
    AUDIO
}

enum class MediaSource {
    LOCAL,
    GOOGLE_DRIVE,
    JELLYFIN,
    NAS
}

data class MediaItem(
    val id: String = UUID.randomUUID().toString(),
    val mediaType: MediaType,
    val source: MediaSource,
    val sourcePath: String,
    val mimeType: String,

    val title: String? = null,
    val description: String? = null,
    val thumbnailPath: String? = null,

    val tags: List<String> = emptyList(),
    val rating: Int? = null,
    val nsfw: Boolean = false,

    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

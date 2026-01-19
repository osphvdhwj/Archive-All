package com.archiveall.core

import android.content.ContentResolver
import android.provider.MediaStore
import com.archiveall.data.MediaDao
import com.archiveall.data.MediaEntity
import com.archiveall.model.MediaSource
import com.archiveall.model.MediaType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaScanner(
    private val contentResolver: ContentResolver,
    private val mediaDao: MediaDao
) {

    suspend fun scanAll() {
        scanImages()
        scanVideos()
        scanAudio()
    }

    private suspend fun scanImages() = scan(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        MediaType.PHOTO
    )

    private suspend fun scanVideos() = scan(
        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
        MediaType.VIDEO
    )

    private suspend fun scanAudio() = scan(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        MediaType.AUDIO
    )

    private suspend fun scan(uri: android.net.Uri, type: MediaType) {
        withContext(Dispatchers.IO) {
            val projection = arrayOf(
                MediaStore.MediaColumns._ID,
                MediaStore.MediaColumns.DATA,
                MediaStore.MediaColumns.MIME_TYPE,
                MediaStore.MediaColumns.DATE_ADDED
            )

            contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
                val pathIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
                val mimeIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.MIME_TYPE)
                val dateIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED)

                while (cursor.moveToNext()) {
                    val entity = MediaEntity(
                        id = cursor.getString(pathIndex),
                        mediaType = type.name,
                        source = MediaSource.LOCAL.name,
                        sourcePath = cursor.getString(pathIndex),
                        mimeType = cursor.getString(mimeIndex),
                        title = null,
                        description = null,
                        thumbnailPath = null,
                        tags = "",
                        rating = null,
                        nsfw = false,
                        createdAt = cursor.getLong(dateIndex) * 1000,
                        updatedAt = System.currentTimeMillis()
                    )
                    mediaDao.insert(entity)
                }
            }
        }
    }
}

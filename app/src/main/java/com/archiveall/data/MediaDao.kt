package com.archiveall.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MediaEntity)

    @Query("SELECT * FROM media_items ORDER BY createdAt DESC")
    fun getAll(): Flow<List<MediaEntity>>

    @Query("SELECT * FROM media_items WHERE nsfw = 0")
    fun getSafeItems(): Flow<List<MediaEntity>>

    @Query("DELETE FROM media_items")
    suspend fun clearAll()
}

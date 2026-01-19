package com.archiveall.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MediaEntity::class],
    version = 1
)
abstract class ArchiveDatabase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao
}

package com.archiveall

import android.app.Application
import androidx.room.Room
import com.archiveall.core.MediaScanner
import com.archiveall.data.ArchiveDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArchiveAllApp : Application() {

    lateinit var database: ArchiveDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            ArchiveDatabase::class.java,
            "archive_all_db"
        ).build()

        CoroutineScope(Dispatchers.IO).launch {
            MediaScanner(
                contentResolver,
                database.mediaDao()
            ).scanAll()
        }
    }
}

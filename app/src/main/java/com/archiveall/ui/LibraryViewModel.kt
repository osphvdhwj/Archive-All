package com.archiveall.ui

import androidx.lifecycle.ViewModel
import com.archiveall.data.MediaDao

class LibraryViewModel(
    mediaDao: MediaDao
) : ViewModel() {
    val items = mediaDao.getAll()
}

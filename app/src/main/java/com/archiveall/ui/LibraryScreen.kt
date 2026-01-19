package com.archiveall.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.archiveall.data.MediaEntity

@Composable
fun LibraryScreen(viewModel: LibraryViewModel) {
    val items = viewModel.items.collectAsState(initial = emptyList())

    LazyColumn {
        items(items.value) { item ->
            Text(
                text = item.sourcePath,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

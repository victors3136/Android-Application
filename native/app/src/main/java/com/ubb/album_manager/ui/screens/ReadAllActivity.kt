package com.ubb.album_manager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ubb.album_manager.service.AlbumService
import com.ubb.album_manager.service.Theme
import com.ubb.album_manager.ui.AlbumViewModel
import com.ubb.album_manager.ui.components.AddButton
import com.ubb.album_manager.ui.components.AlbumItem
import com.ubb.album_manager.ui.components.DeleteConfirmationDialog
import com.ubb.album_manager.ui.components.Header


@Composable
fun ReadAllActivity(
    navigator: NavController,
    viewModel: AlbumViewModel = AlbumViewModel(AlbumService(LocalContext.current.applicationContext))
) {
    val albums by viewModel.albums.observeAsState(emptyList())
    var deleteRequestId by remember { mutableStateOf<Int?>(null) }
    if (deleteRequestId != null) {
        return DeleteConfirmationDialog(
            submit = { viewModel.delete(deleteRequestId!!) },
            cleanup = { deleteRequestId = null }
        )
    }
    return Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.ultimateBackground)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.ultimateBackground)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Header("My Albums")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(innerPadding)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(albums) { album ->
                            AlbumItem(
                                album = album,
                                onViewButtonClick = { navigator.navigate("read/${album.id}") },
                                onDeleteButtonClick = { deleteRequestId = album.id },
                                onEditButtonClick = { navigator.navigate("edit/${album.id}") }
                            )
                        }
                    }
                }
                AddButton { navigator.navigate("create") }
            }
        }
    }

}
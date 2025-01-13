package com.ubb.album_manager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ubb.album_manager.service.AlbumService
import com.ubb.album_manager.service.Theme
import com.ubb.album_manager.ui.AlbumViewModel
import com.ubb.album_manager.ui.components.AlbumForm
import com.ubb.album_manager.ui.components.Header

@Composable
fun EditActivity(
    subjectId: Int,
    navigator: NavController,
    viewModel: AlbumViewModel =
        AlbumViewModel(AlbumService(LocalContext.current.applicationContext))
) {
    val albums by viewModel.albums.observeAsState(emptyList())
    val album = albums.find { it.id == subjectId }
    if (album == null) {
        return ErrorDisplay("Album is not part of the list") {
            navigator.popBackStack("read", inclusive = false)
        }
    }
    return Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.ultimateBackground)
    ) { innerPadding ->
        Column(modifier = Modifier.background(Theme.ultimateBackground)) {
            Header("Edit")
            AlbumForm(
                default = album,
                onSubmit = {
                    viewModel.edit(it)
                    navigator.popBackStack("read", inclusive = false)
                },
                onCancel = {
                    navigator.popBackStack("read", inclusive = false)
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

}
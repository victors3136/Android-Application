package com.ubb.album_manager.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ubb.album_manager.service.AlbumService
import com.ubb.album_manager.service.Theme
import com.ubb.album_manager.ui.AlbumViewModel
import com.ubb.album_manager.ui.components.BackButton
import com.ubb.album_manager.ui.components.DeleteButton
import com.ubb.album_manager.ui.components.DeleteConfirmationDialog
import com.ubb.album_manager.ui.components.EditButton
import com.ubb.album_manager.ui.components.Header

@Composable
fun ReadOneActivity(
    subjectId: Int,
    navigator: NavController,
    viewModel: AlbumViewModel = AlbumViewModel(AlbumService(LocalContext.current.applicationContext))
) {
    val albums by viewModel.albums.observeAsState(emptyList())
    var deleteRequestId by remember { mutableStateOf<Int?>(null) }
    val album = albums.find { it.id == subjectId }
    val configuration = LocalConfiguration.current
    val screenSize = min(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
    val imageSize = screenSize * 0.5f

    if (album == null) {
        return ErrorDisplay("Album is not part of the list") {
            navigator.popBackStack("read", inclusive = false)
        }
    }

    if (deleteRequestId != null) {
        return DeleteConfirmationDialog(
            submit = { viewModel.delete(deleteRequestId!!) },
            cleanup = { deleteRequestId = null; navigator.popBackStack("read", inclusive = false) }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.ultimateBackground)
    ) {
        Header(album.name)

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = ColorPainter(Theme.secondaryBackground),
                contentDescription = "Album Cover",
                modifier = Modifier
                    .width(imageSize)
                    .aspectRatio(1f)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Text(
                text = album.artist,
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Released in ${album.releaseYear}",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Genre: ${album.genre}",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp)
        ) {
            BackButton { navigator.popBackStack() }
            EditButton { navigator.navigate("edit/${subjectId}") }
            DeleteButton { deleteRequestId = subjectId }
        }
    }
}

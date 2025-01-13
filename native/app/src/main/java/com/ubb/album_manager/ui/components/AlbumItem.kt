package com.ubb.album_manager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ubb.album_manager.domain.Album
import com.ubb.album_manager.service.Theme


@Composable
fun AlbumItem(
    album: Album,
    onViewButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(color = Theme.primaryBackground, shape = RoundedCornerShape(16.dp))
            .border(width = 2.dp, color = Theme.primaryAccent, shape = RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = ColorPainter(Theme.secondaryBackground),
            contentDescription = "Album Cover",
            modifier = Modifier.size(64.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = album.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Theme.primaryText,
                    modifier = Modifier.widthIn(max = 164.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${album.releaseYear}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Theme.primaryText,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "by ${album.artist}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Theme.primaryText,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Text(
                    text = album.genre,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Theme.primaryText
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ViewButton(onViewButtonClick)
                EditButton(onEditButtonClick)
                DeleteButton(onDeleteButtonClick)
            }
        }
    }
}

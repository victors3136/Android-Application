package com.ubb.album_manager.ui.components

import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ubb.album_manager.domain.Album
import java.util.Random
import java.util.UUID

@Composable
fun CustomTextField(value: TextFieldValue, label: String, onValueChange: (TextFieldValue) -> Unit) {
    TextField(value = value, onValueChange = {
        onValueChange(it)
    }, label = { Text(text = label) }, singleLine = true, modifier = Modifier.padding(5.dp))
}

fun isTitleValid(title: String): Boolean = title.isNotEmpty()
fun isArtistValid(artist: String): Boolean = artist.isNotEmpty()
fun isReleaseYearValid(year: String): Boolean = year.toIntOrNull() != null && year.toInt() > 1900
fun isGenreValid(genre: String): Boolean = genre.isNotEmpty()
fun isUrlValid(url: String): Boolean = url.isEmpty() || Patterns.WEB_URL.matcher(url).matches()

@Composable
fun AlbumForm(
    default: Album,
    onSubmit: (subject: Album) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier.Companion
) {
    val title = remember { mutableStateOf(TextFieldValue(default.name)) }
    val artist = remember { mutableStateOf(TextFieldValue(default.artist)) }
    val releaseYear = remember { mutableStateOf(TextFieldValue(default.releaseYear.toString())) }
    val genre = remember { mutableStateOf(TextFieldValue(default.genre)) }
    val url = remember { mutableStateOf(TextFieldValue(default.url)) }

    fun isValid(): Boolean {
        return isTitleValid(title.value.text) &&
                isArtistValid(artist.value.text) &&
                isReleaseYearValid(releaseYear.value.text) &&
                isGenreValid(genre.value.text) &&
                isUrlValid(url.value.text)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        CustomTextField(value = title.value, label = "title") {
            title.value = it
        }

        CustomTextField(value = artist.value, label = "artist") {
            artist.value = it
        }

        CustomTextField(value = releaseYear.value, label = "release year") {
            releaseYear.value = it
        }

        CustomTextField(value = genre.value, label = "genre") {
            genre.value = it
        }

        CustomTextField(value = url.value, label = "image link") {
            url.value = it
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackButton(onCancel)
            SubmitButton(
                {
                    val albumTitle = title.value.text
                    val albumArtist = artist.value.text
                    val albumYear = releaseYear.value.text.toIntOrNull() ?: 0
                    val albumGenre = genre.value.text
                    val albumURL = url.value.text
                    val albumId =
                        if (default.id == Album.INVALID_ID)
                            Random().nextInt() else default.id
                    onSubmit(
                        Album(
                            name = albumTitle,
                            artist = albumArtist,
                            releaseYear = albumYear,
                            genre = albumGenre,
                            url = albumURL,
                            id = albumId
                        )
                    )
                },
                enabled = isValid()
            )
        }
    }
}

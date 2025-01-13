package com.ubb.album_manager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LibraryAdd
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    onclick: () -> Unit, icon: ImageVector, description: String?,
    enabled: Boolean = true
) {
    Button(
        onClick = onclick, enabled = enabled
    ) {
        Image(
            imageVector = icon,
            contentDescription = description ?: ""
        )
    }
}

@Composable
fun AddButton(onclick: () -> Unit) {
    Button(
        onClick = onclick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 64.dp, top = 0.dp, start = 32.dp, end = 32.dp)
    ) {
        Image(
            imageVector = Icons.Filled.LibraryAdd,
            contentDescription = "Add New Album"
        )
    }
}

@Composable
fun DeleteButton(action: () -> Unit) {
    IconButton(action, Icons.Filled.Delete, "Delete")
}

@Composable
fun EditButton(action: () -> Unit) {
    IconButton(action, Icons.Filled.Edit, "Edit")
}

@Composable
fun ViewButton(action: () -> Unit) {
    IconButton(action, Icons.Filled.OpenInFull, "View")
}

@Composable
fun BackButton(action: () -> Unit) {
    IconButton(action, Icons.Filled.ArrowBackIosNew, "Back")
}

@Composable
fun SubmitButton(action: () -> Unit, enabled: Boolean) {
    IconButton(action, Icons.Filled.Check, "Submit", enabled = enabled)
}

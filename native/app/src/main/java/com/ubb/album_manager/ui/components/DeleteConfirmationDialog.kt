package com.ubb.album_manager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.ubb.album_manager.service.Theme

@Composable
fun DeleteConfirmationDialog(
    cleanup: () -> Unit,
    submit: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { cleanup() },
        title = {
            Text(
                text = "Confirm Deletion",
                color = Theme.secondaryText
            )
        },
        text = {
            Text(
                text = "Are you sure you want to delete this album? This action cannot be undone.",
                color = Theme.secondaryText
            )
        },
        confirmButton = { DeleteButton { submit(); cleanup() } },
        dismissButton = { BackButton { cleanup() } }
    )
}


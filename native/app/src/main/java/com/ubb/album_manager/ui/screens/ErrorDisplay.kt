package com.ubb.album_manager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ubb.album_manager.service.Theme
import com.ubb.album_manager.ui.components.BackButton


@Composable
fun ErrorDisplay(message: String = "", confirmAction: () -> Boolean) {
    Column(
        Modifier
            .background(Theme.ultimateBackground)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            color = Theme.primaryText, text = message,
            style = MaterialTheme.typography.titleLarge
        )
        BackButton { confirmAction }
    }
}

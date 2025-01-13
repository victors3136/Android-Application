package com.ubb.album_manager.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ubb.album_manager.domain.Album
import com.ubb.album_manager.service.AlbumService
import com.ubb.album_manager.ui.AlbumViewModel

@Composable
fun Main() {
    val viewModel =
        AlbumViewModel(AlbumService(LocalContext.current.applicationContext))
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "read") {
        composable("read/{albumId}") { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId")
            ReadOneActivity(
                subjectId = albumId?.toIntOrNull() ?: Album.INVALID_ID,
                navigator = navController,
                viewModel = viewModel
            )
        }
        composable("read") { ReadAllActivity(navController, viewModel) }
        composable("create") { CreateActivity(navController, viewModel) }
        composable("edit/{albumId}") { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString("albumId")
            EditActivity(
                subjectId = albumId?.toIntOrNull() ?: Album.INVALID_ID,
                navigator = navController,
                viewModel = viewModel
            )
        }
    }
}
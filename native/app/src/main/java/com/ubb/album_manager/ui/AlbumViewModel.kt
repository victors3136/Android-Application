package com.ubb.album_manager.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ubb.album_manager.domain.Album
import com.ubb.album_manager.service.AlbumService
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumService: AlbumService) : ViewModel() {
    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> get() = _albums

    init {
        loadAlbums()
    }

    private fun loadAlbums() {
        viewModelScope.launch {
            albumService.getAll().collect { albumList ->
                _albums.postValue(albumList)
            }
        }
    }

    fun add(album: Album) {
        viewModelScope.launch {
            if (albumService.add(album)) {
                Log.d("AlbumViewModel", "Album added: $album")
            } else {
                Log.e("AlbumViewModel", "Error adding album")
            }
        }
    }

    fun edit(updatedAlbum: Album) {
        viewModelScope.launch {
            if (albumService.edit(updatedAlbum)) {
                Log.d("AlbumViewModel", "Album edited: $updatedAlbum")
            } else {
                Log.e("AlbumViewModel", "Error updating album")
            }
        }
    }

    fun delete(albumId: Int) {
        viewModelScope.launch {
            if (albumService.delete(albumId)) {
                Log.d("AlbumViewModel", "Album deleted was number $albumId")
            } else {
                Log.e("AlbumViewModel", "Error deleting album")
            }
        }
    }
}

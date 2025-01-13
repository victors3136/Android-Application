package com.ubb.album_manager.service

import android.app.Application
import android.content.Context
import android.util.Log
import com.ubb.album_manager.domain.Album
import com.ubb.album_manager.service.persistence.MixedAlbumRepository
import kotlinx.coroutines.flow.Flow

class AlbumService(context: Context) : Application() {

    private val data: AlbumRepository by lazy {
        Log.d("[Service]", "Create repo")
        MixedAlbumRepository(context)
    }

    suspend fun add(album: Album?): Boolean {
        return if (album == null) {
            false
        } else try {
            data.insert(album)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun edit(album: Album?): Boolean {
        return if (album == null)
            false
        else try {
            data.update(album)
            true
        } catch (e: Exception) {
            false
        }
    }


    suspend fun delete(album: Album?): Boolean {
        return if (album == null)
            false
        else try {
            data.delete(album.id)
            true
        } catch (e: Exception) {
            false
        }
    }

    suspend fun delete(albumId: Int): Boolean {
        return try {
            data.delete(albumId)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun get(index: Int): Flow<Album?> {
        return data.get(index)
    }

    fun getAll(): Flow<List<Album>> {
        return data.all()
    }

}
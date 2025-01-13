package com.ubb.album_manager.service.persistence.local

import android.content.Context
import android.util.Log
import com.ubb.album_manager.domain.Album
import com.ubb.album_manager.service.AlbumRepository
import kotlinx.coroutines.flow.Flow

class LocalAlbumRepository(private val albumDao: AlbumDao) : AlbumRepository {
    constructor(context: Context) : this(AlbumsDatabase.getDatabase(context).albumDao())

    override fun all(): Flow<List<Album>> {
        Log.i("[Local repo]", "Get all")
        return albumDao.getAll()
    }

    override fun get(id: Int): Flow<Album?> {
        Log.i("[Local repo]", "Get $id")
        return albumDao.getAlbum(id)
    }

    override suspend fun insert(album: Album) {
        Log.i("[Local repo]", "Insert $album")
        albumDao.insert(album)
    }

    override suspend fun delete(album: Album) {
        Log.i("[Local repo]", "Delete $album")
        albumDao.delete(album)
    }

    override suspend fun delete(id: Int) {
        Log.i("[Local repo]", "Delete $id")
        albumDao.delete(id)
    }

    override suspend fun update(album: Album) {
        Log.i("[Local repo]", "Update $album")
        albumDao.update(album)
    }

    suspend fun clear() {
        Log.i("[Local repo]", "Clear")
        albumDao.clearAll()
    }
}
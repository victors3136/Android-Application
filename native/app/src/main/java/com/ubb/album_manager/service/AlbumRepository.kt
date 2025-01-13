package com.ubb.album_manager.service

import com.ubb.album_manager.domain.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    /**
     * Retrieve all the albums from the the data source.
     */
    fun all(): Flow<List<Album>>

    /**
     * Retrieve an album from the given data source that matches with the [id].
     */
    fun get(id: Int): Flow<Album?>

    /**
     * Insert item in the data source
     */
    suspend fun insert(album: Album)

    /**
     * Delete item from the data source
     */
    suspend fun delete(album: Album)

    /**
     * Update item in the data source
     */
    suspend fun update(album: Album)

    suspend fun delete(id: Int)
}


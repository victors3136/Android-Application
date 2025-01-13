package com.ubb.album_manager.service.persistence.remote

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.ubb.album_manager.domain.Album
import com.ubb.album_manager.service.AlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteAlbumRepository(private val context: Context, private val api: AlbumApi) :
    AlbumRepository {
    constructor(context: Context) : this(context, RetrofitClient.albumApi)

    override fun all(): Flow<List<Album>> = flow {
        Log.i("[Remote repo]", "Get all")
        val response = api.getAll()
        if (response.isSuccessful) {
            response.body()?.let { emit(it) } ?: emit(emptyList())
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Couldn't retrieve the list of albums.\nDetails: " + response.message(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Log.e("[Remote repo]", "Failed to fetch albums: ${response.errorBody()?.string()}")
        }
    }

    override fun get(id: Int): Flow<Album?> = flow {
        Log.i("[Remote repo]", "Get $id")
        val response = api.get(id)
        if (response.isSuccessful) {
            emit(response.body())
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Couldn't retrieve the requested album.\nDetails: " + response.message(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Log.e(
                "[Remote repo]",
                "Failed to fetch album with id $id: ${response.errorBody()?.string()}"
            )
        }
    }

    override suspend fun insert(album: Album) {
        Log.i("[Remote repo]", "Insert $album")
        val response = api.add(album)
        if (!response.isSuccessful) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context, "Error when inserting the album\nDetails: " + response.message(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Log.e("[Remote repo]", "Failed to insert album: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun delete(album: Album) {
        Log.i("[Remote repo]", "Delete $album")
        delete(album.id)
    }

    override suspend fun delete(id: Int) {
        Log.i("[Remote repo]", "Delete $id")
        val response = api.delete(id)
        if (!response.isSuccessful) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context, "Couldn't delete the requested album.\nDetails: " + response.message(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Log.e(
                "[Remote repo]",
                "Failed to delete album with id $id: ${response.errorBody()?.string()}"
            )
        }
    }

    override suspend fun update(album: Album) {
        Log.i("[Remote repo]", "Update $album")
        val response = api.update(album)
        if (!response.isSuccessful) {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context, "Couldn't edit the requested album.\nDetails: " + response.message(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            Log.e("[Remote repo]", "Failed to update album: ${response.errorBody()?.string()}")
        }
    }
}

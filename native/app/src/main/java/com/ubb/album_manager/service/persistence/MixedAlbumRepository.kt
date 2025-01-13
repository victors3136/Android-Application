package com.ubb.album_manager.service.persistence

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.content.ContextCompat
import com.ubb.album_manager.domain.Album
import com.ubb.album_manager.service.AlbumRepository
import com.ubb.album_manager.service.persistence.PersistableOperationType.DELETE
import com.ubb.album_manager.service.persistence.PersistableOperationType.EDIT
import com.ubb.album_manager.service.persistence.PersistableOperationType.INSERT
import com.ubb.album_manager.service.persistence.local.LocalAlbumRepository
import com.ubb.album_manager.service.persistence.remote.NetworkUtils
import com.ubb.album_manager.service.persistence.remote.RemoteAlbumRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MixedAlbumRepository(private val context: Context) : AlbumRepository {
    private val localRepo: LocalAlbumRepository = LocalAlbumRepository(context)
    private val remoteRepo: RemoteAlbumRepository = RemoteAlbumRepository(context)
    private val network: NetworkUtils = NetworkUtils(context)
    private val queue: UnpersistedOperationQueue = UnpersistedOperationQueue(context)
    private val networkReceiver: NetworkStateReceiver = NetworkStateReceiver {
        CoroutineScope(Dispatchers.IO).launch {
            processQueue()
        }
    }

    init {
        @Suppress("DEPRECATION")
        ContextCompat.registerReceiver(
            context,
            networkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION),
            ContextCompat.RECEIVER_NOT_EXPORTED
        )
        remoteRepo.all().onEach { remoteData ->
            localRepo.clear()
            remoteData.forEach { album ->
                localRepo.insert(album)
            }
        }
    }


    private fun isOnline(): Boolean = network.isOnline()

    override fun all(): Flow<List<Album>> {
        Log.i("[Mixed repo]", "Get all")
        return localRepo.all()
    }

    override fun get(id: Int): Flow<Album?> {
        Log.i("[Mixed repo]", "Get $id")
        return localRepo.get(id)
    }

    override suspend fun insert(album: Album) {
        Log.i("[Mixed repo]", "Insert $album")
        localRepo.insert(album)
        if (isOnline()) {
            remoteRepo.insert(album)
        } else {
            queue.enqueue(PersistableOperation(INSERT, album))
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Could not send new album to the server.\nWe'll try again as soon as internet connection is available",
                    LENGTH_LONG
                ).show()
            }
        }
    }

    override suspend fun delete(album: Album) {
        Log.i("[Mixed repo]", "Delete $album")
        localRepo.delete(album)
        if (isOnline()) {
            remoteRepo.delete(album)
        } else {
            queue.enqueue(PersistableOperation(DELETE, album))
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Could not send the delete request to the server.\nWe'll try again as soon as internet connection is available",
                    LENGTH_LONG
                ).show()
            }
        }
    }

    override suspend fun delete(id: Int) {
        Log.i("[Mixed repo]", "Delete $id")
        localRepo.delete(id)
        if (isOnline()) {
            remoteRepo.delete(id)
        } else {
            queue.enqueue(PersistableOperation(DELETE, id = id))
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Could not send the delete request to the server.\nWe'll try again as soon as internet connection is available",
                    LENGTH_LONG
                ).show()
            }
        }
    }

    override suspend fun update(album: Album) {
        Log.i("[Mixed repo]", "Update $album")
        localRepo.update(album)
        if (isOnline()) {
            remoteRepo.update(album)
        } else {
            queue.enqueue(PersistableOperation(EDIT, album))
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    context,
                    "Could not send the update request to the server.\nWe'll try again as soon as internet connection is available",
                    LENGTH_LONG
                ).show()
            }
        }
    }

    private suspend fun processQueue() {
        if (!isOnline()) {
            return
        }

        withContext(Dispatchers.Main) {
            Toast.makeText(context, "Back online!", LENGTH_LONG).show()
        }
//        Log.i("[Mixed repo]", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        while (!queue.isEmpty()) {
            val operation = queue.dequeue()
            try {
                Log.i("[Mixed repo]", "Running ")
                when (operation.type) {
                    INSERT -> remoteRepo.insert(operation.album!!)
                    EDIT -> remoteRepo.update(operation.album!!)
                    DELETE -> if (operation.album != null) {
                        remoteRepo.delete(operation.album)
                    } else {
                        remoteRepo.delete(operation.id!!)
                    }
                }
            } catch (e: Exception) {
                Log.e("[Mixed repo]", "Failed to process operation: $e")
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, e.message, LENGTH_LONG).show()
                }

                break
            }
        }

    }
}

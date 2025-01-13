package com.ubb.album_manager.service.persistence.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ubb.album_manager.domain.Album
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Album)


    @Update
    suspend fun update(item: Album)

    @Delete
    suspend fun delete(item: Album)

    @Query("select * from albums where id = :id")
    fun getAlbum(id: Int): Flow<Album>


    @Query("delete from albums where id = :id")
    suspend fun delete(id: Int)

    @Query("select * from albums")
    fun getAll(): Flow<List<Album>>

    @Query("delete from albums")
    suspend fun clearAll()
}
package com.ubb.album_manager.service.persistence.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ubb.album_manager.domain.Album

@Database(entities = [Album::class], version = 1, exportSchema = true)
abstract class AlbumsDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        @Volatile
        private var Instance: AlbumsDatabase? = null
        fun getDatabase(context: Context): AlbumsDatabase {
            Instance =
                if (Instance != null) Instance
                else synchronized(this) {
                    Room.databaseBuilder(context, AlbumsDatabase::class.java, "albums_database")
                        .fallbackToDestructiveMigration()
                        .build().also { Instance = it }
                }
            return Instance!!
        }

    }
}
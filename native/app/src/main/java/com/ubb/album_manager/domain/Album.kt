package com.ubb.album_manager.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "albums")
data class Album(
    val name: String = "",
    val artist: String = "",
    val releaseYear: Int = 2000,
    val genre: String = "",
    val url: String = DEFAULT_URL,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    companion object {
        const val DEFAULT_URL = "https://media.istockphoto.com/id/" +
                "92889831/photo/cd-dvd-top.jpg?s=612x612&w=0&k=20" +
                "&c=OMRPZLp3frbSFyoi_FRsLiSay3XM12aTF-fraidqcxE="

        const val INVALID_ID = -1

        val DEFAULT_ALBUM = Album(
            name = "Unknown",
            artist = "Unknown Artist",
            releaseYear = 0,
            genre = "Unknown Genre",
            url = "",
            id = INVALID_ID
        )
    }

    override fun toString(): String {
        return """album: {
                    id: $id
                    name: $name
                    artist: $artist
                    genre: $genre
                    release_year: $releaseYear
                    url: $url
                  }"""
    }
}
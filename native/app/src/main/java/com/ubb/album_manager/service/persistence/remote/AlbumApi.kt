package com.ubb.album_manager.service.persistence.remote

import com.ubb.album_manager.domain.Album
import retrofit2.Response
import retrofit2.http.*

interface AlbumApi {

    @POST("albums/")
    suspend fun add(@Body album: Album): Response<Album?>

    @GET("albums/")
    suspend fun getAll(): Response<List<Album>>

    @GET("albums/{id}")
    suspend fun get(@Path("id") id: Int): Response<Album?>

    @DELETE("albums/{id}")
    suspend fun delete(@Path("id") id: Int): Response<Unit>

    @PUT("albums/")
    suspend fun update(@Body album: Album): Response<Album?>
}

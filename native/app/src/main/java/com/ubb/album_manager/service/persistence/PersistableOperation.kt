package com.ubb.album_manager.service.persistence

import com.ubb.album_manager.domain.Album

data class PersistableOperation(
    val type: PersistableOperationType,
    val album: Album? = null,
    val id: Int? = null,
)
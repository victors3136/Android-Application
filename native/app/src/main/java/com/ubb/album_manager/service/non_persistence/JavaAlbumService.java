package com.ubb.album_manager.service.non_persistence;

import com.ubb.album_manager.domain.Album;

import java.util.List;
import java.util.Optional;

@Deprecated
public class JavaAlbumService {
    private final JavaAlbumRepository repo = new JavaAlbumRepository();
    private static final Album DEFAULT_ALBUM = new Album(
            "",
            "",
            0,
            "",
            "",
            Album.INVALID_ID);
    static private JavaAlbumService instance = null;

    private JavaAlbumService() {
    }

    static public JavaAlbumService instance() {
        if (instance == null) {
            instance = new JavaAlbumService();
        }
        return instance;
    }

    public void add(Album album) {
        repo.add(album);
    }

    public boolean edit(Album album) {
        return repo.edit(album);
    }

    public boolean delete(Album album) {
        return repo.delete(album);
    }

    public boolean delete(int albumId) {
        return repo.delete(albumId);
    }

    public Album get(int index) {
        return repo.get(index).orElse(DEFAULT_ALBUM);
    }

    public List<Album> getAll() {
        return repo.getAll();
    }

    public Optional<Album> getById(int id) {
        return repo.getById(id);
    }
}

package ubb.server.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LocalMapRepository implements IRepository {
    private final Map<Integer, Album> albums;

    @Autowired
    public LocalMapRepository(Map<Integer, Album> albums) {
        this.albums = albums;
    }

    @Override
    public Collection<Album> all() {
        return albums.values();
    }

    @Override
    public Optional<Album> put(Album album) {
        assert album != null;
        if (albums.containsKey(album.id())) {
            return Optional.empty();
        }
        return Optional.ofNullable(albums.put(album.id(), album));
    }

    @Override
    public Optional<Album> get(int id) {
        return Optional.of(albums.get(id));
    }

    @Override
    public boolean delete(int id) {
        return albums.remove(id) != null;
    }

    @Override
    public boolean edit(Album album) {
        if (!albums.containsKey(album.id())) {
            return false;
        }
        albums.put(album.id(), album);
        return true;
    }
}

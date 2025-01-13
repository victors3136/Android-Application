package ubb.server.data;

import java.util.Collection;
import java.util.Optional;

public interface IRepository {
    Collection<Album> all();

    Optional<Album> put(Album album);

    Optional<Album> get(int id);

    boolean delete(int id);

    boolean edit(Album album);
}

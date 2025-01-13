package ubb.server.service;

import ubb.server.data.Album;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.Future;

public interface IDataService {
    Future<Collection<Album>> all();

    Future<Optional<Album>> put(Album album);

    Future<Optional<Album>> get(int id);

    Future<Boolean> delete(int id);

    Future<Boolean> edit(Album album);
}

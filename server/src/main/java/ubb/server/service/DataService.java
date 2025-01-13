package ubb.server.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;
import ubb.server.data.Album;
import ubb.server.data.IRepository;

@Service
public class DataService implements IDataService {
    private final IRepository data;
    private final ExecutorService executor;

    @Autowired
    public DataService(IRepository data) {
        this.data = data;
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Override
    public Future<Collection<Album>> all() {
        return executor.submit(data::all);
    }

    @Override
    public Future<Optional<Album>> put(Album album) {
        return executor.submit(() -> data.put(album));
    }

    @Override
    public Future<Optional<Album>> get(int id) {
        return executor.submit(() -> data.get(id));
    }

    @Override
    public Future<Boolean> delete(int id) {
        return executor.submit(() -> data.delete(id));
    }

    @Override
    public Future<Boolean> edit(Album album) {
        return executor.submit(() -> data.edit(album));
    }
}

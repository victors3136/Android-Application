package ubb.server.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ubb.server.service.LoggingService;
import ubb.server.data.Album;
import ubb.server.service.DataService;
import ubb.server.service.IDataService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("albums")
public class ApiController implements IController {
    private final IDataService service;
    private final LoggingService logger;

    @Autowired
    public ApiController(DataService service, LoggingService logger) {
        this.service = service;
        this.logger = logger;
        logger.inform("API Controller initialized");
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Optional<Album>> add(@RequestBody Album album) {
        logger.inform("Adding album: " + album);
        Optional<Album> result;
        try {
            result = service.put(album).get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            result = Optional.empty();
        }
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<Collection<Album>> get() {
        logger.inform("Reading albums");
        Collection<Album> result;
        try {
            result = service.all().get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            result = List.of();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<Optional<Album>> get(@PathVariable int id) {
        logger.inform("Reading album " + id);
        Optional<Album> result;
        try {
            result = service.get(id).get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            result = Optional.empty();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Integer> delete(@PathVariable int id) {
        logger.inform("Deleting album " + id);
        boolean result;
        try {
            result = service.delete(id).get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            result = false;
        }
        if (result) {
            logger.inform("Deleted album " + id);
            return ResponseEntity.ok(id);
        }
        logger.warn("Album " + id + " not found");
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/")
    @Override
    public ResponseEntity<Album> edit(@RequestBody Album album) {
        logger.inform("Editing album " + album);
        assert album != null;
        boolean result;
        try {
            result = service.edit(album).get();
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
            result = false;
        }
        if (result) {
            logger.inform("Album updated");
            return ResponseEntity.ok(album);
        }
        logger.warn("Album to update not found");
        return ResponseEntity.notFound().build();

    }

}


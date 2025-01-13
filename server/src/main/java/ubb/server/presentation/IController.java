package ubb.server.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ubb.server.data.Album;

import java.util.Collection;
import java.util.Optional;

public interface IController {
    @PostMapping("/")
    ResponseEntity<Optional<Album>> add(@RequestBody Album album);

    @GetMapping("/")
    ResponseEntity<Collection<Album>> get();

    @GetMapping("/{id}")
    ResponseEntity<Optional<Album>> get(@PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity<Integer> delete(@PathVariable int id);

    @PutMapping("/")
    ResponseEntity<Album> edit(@RequestBody Album album);
}

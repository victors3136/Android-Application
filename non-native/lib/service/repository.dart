import 'package:flutter/foundation.dart';
import 'package:uuid/uuid.dart';
import '../model/album.dart';

class AlbumRepository {
  static final List<Album?> _albums = [
    Album(
      name: "Abbey Road",
      artist: "The Beatles",
      releaseYear: 1969,
      genre: "Rock",
      url: "https://example.com/abbey_road.jpg",
    ),
    Album(
      name: "Dark Side of the Moon",
      artist: "Pink Floyd",
      releaseYear: 1973,
      genre: "Progressive Rock",
      url: "https://example.com/dark_side.jpg",
    ),
    Album(
      name: "Thriller",
      artist: "Michael Jackson",
      releaseYear: 1982,
      genre: "Pop",
      url: "https://example.com/thriller.jpg",
    ),
    Album(
      name: "Back in Black",
      artist: "AC/DC",
      releaseYear: 1980,
      genre: "Hard Rock",
      url: "https://example.com/back_in_black.jpg",
    ),
    Album(
      name: "Rumours",
      artist: "Fleetwood Mac",
      releaseYear: 1977,
      genre: "Rock",
      url: "https://example.com/rumours.jpg",
    ),
    Album(
      name: "The Wall",
      artist: "Pink Floyd",
      releaseYear: 1979,
      genre: "Progressive Rock",
      url: "https://example.com/the_wall.jpg",
    ),
    Album(
      name: "Hotel California",
      artist: "Eagles",
      releaseYear: 1976,
      genre: "Rock",
      url: "https://example.com/hotel_california.jpg",
    ),
    Album(
      name: "21",
      artist: "Adele",
      releaseYear: 2011,
      genre: "Pop",
      url: "https://example.com/21_adele.jpg",
    ),
    Album(
      name: "Good Kid, M.A.A.D City",
      artist: "Kendrick Lamar",
      releaseYear: 2012,
      genre: "Hip-Hop",
      url: "https://example.com/good_kid.jpg",
    ),
    Album(
      name: "Nevermind",
      artist: "Nirvana",
      releaseYear: 1991,
      genre: "Grunge",
      url: "https://example.com/nevermind.jpg",
    ),
  ];
  static const Uuid _uuid = Uuid();

  static List<Album> getAll() {
    return List.unmodifiable(_albums);
  }

  static Album? getById(String id) {
    return _albums.firstWhere(
      (album) => (album != null) && (album.id == id),
      orElse: () => null,
    );
  }

  static void add(Album album) {
    final newAlbum = Album(
      id: album.id != defaultId ? album.id : _uuid.v4(),
      name: album.name,
      artist: album.artist,
      releaseYear: album.releaseYear,
      genre: album.genre,
      url: album.url,
    );
    _albums.add(newAlbum);
    if (kDebugMode) {
      print("Album added: $newAlbum");
    }
  }

  static bool edit(String id, Album updatedAlbum) {
    final index =
        _albums.indexWhere((album) => (album != null) && (album.id == id));
    if (index != -1) {
      _albums[index] = updatedAlbum.copyWith(id: id);
      if (kDebugMode) {
        print("Album updated: ${_albums[index]}");
      }
      return true;
    }
    return false;
  }

  static bool delete(String id) {
    final Album? albumToRemove = getById(id);
    if (albumToRemove != null) {
      _albums.remove(albumToRemove);
      if (kDebugMode) {
        print("Album deleted: $albumToRemove");
      }
      return true;
    }
    return false;
  }
}

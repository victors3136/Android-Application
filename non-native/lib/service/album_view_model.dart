import 'package:album_manager/service/repository.dart';
import 'package:flutter/material.dart' show ChangeNotifier;
import '../model/album.dart';

class AlbumViewModel with ChangeNotifier {
  final List<Album> _albums = List.from(AlbumRepository.getAll());

  List<Album> get albums => List.unmodifiable(_albums);

  void add(Album album) {
    AlbumRepository.add(album);
    _albums.add(album);
    notifyListeners();
  }

  void edit(Album updatedAlbum) {
    if (AlbumRepository.edit(updatedAlbum.id, updatedAlbum)) {
      final index = _albums.indexWhere((album) => album.id == updatedAlbum.id);
      _albums[index] = updatedAlbum;
      notifyListeners();
    }
  }

  void delete(String albumId) {
    if (AlbumRepository.delete(albumId)) {
      _albums.removeWhere((album) => album.id == albumId);
      notifyListeners();
    }
  }

  Album? findById(String albumId) {
    return _albums.firstWhere((album) => album.id == albumId);
  }
}

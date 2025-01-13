import 'package:uuid/uuid.dart';

const String defaultUrl = "";
const String defaultId = "00000000-0000-0000-0000-000000000000";
class Album {
  final String name;
  final String artist;
  final int releaseYear;
  final String genre;
  final String url;
  final String id;

  Album({
    this.name = "",
    this.artist = "",
    this.releaseYear = 0,
    this.genre = "",
    this.url = defaultUrl,
    String? id,
  }) : id = id ?? const Uuid().v4();

  @override
  String toString() {
    return '''Album: {
                id: $id,
                name: $name,
                artist: $artist,
                genre: $genre,
                release_year: $releaseYear,
                url: $url
              }''';
  }
  Album copyWith({
    String? name,
    String? artist,
    int? releaseYear,
    String? genre,
    String? url,
    String? id,
  }) {
    return Album(
      name: name ?? this.name,
      artist: artist ?? this.artist,
      releaseYear: releaseYear ?? this.releaseYear,
      genre: genre ?? this.genre,
      url: url ?? this.url,
      id: id ?? this.id,
    );
  }
}

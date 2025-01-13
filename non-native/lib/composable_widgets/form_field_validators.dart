bool isTitleValid(String title) => title.isNotEmpty;
bool isArtistValid(String artist) => artist.isNotEmpty;
bool isReleaseYearValid(String year) {
  final parsedYear = int.tryParse(year);
  return parsedYear != null && parsedYear > 1900 && parsedYear < 2500;
}
bool isGenreValid(String genre) => genre.isNotEmpty;
bool isUrlValid(String url) {
  final urlPattern = RegExp(r'^(http|https):\/\/[^\s]+$');
  return url.isEmpty || urlPattern.hasMatch(url);
}

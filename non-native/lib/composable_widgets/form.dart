import 'package:album_manager/composable_widgets/buttons.dart';
import 'package:flutter/material.dart';

import '../model/album.dart';
import 'form_field_validators.dart';
import 'form_text_field.dart';

class AlbumForm extends StatefulWidget {
  final Album defaultAlbum;
  final void Function(Album) onSubmit;
  final VoidCallback onCancel;

  const AlbumForm({
    super.key,
    required this.defaultAlbum,
    required this.onSubmit,
    required this.onCancel,
  });

  @override
  AlbumFormState createState() => AlbumFormState();
}

class AlbumFormState extends State<AlbumForm> {
  late TextEditingController titleController;
  late TextEditingController artistController;
  late TextEditingController releaseYearController;
  late TextEditingController genreController;
  late TextEditingController urlController;

  @override
  void initState() {
    super.initState();
    titleController = TextEditingController(text: widget.defaultAlbum.name);
    artistController = TextEditingController(text: widget.defaultAlbum.artist);
    releaseYearController =
        TextEditingController(text: widget.defaultAlbum.releaseYear.toString());
    genreController = TextEditingController(text: widget.defaultAlbum.genre);
    urlController = TextEditingController(text: widget.defaultAlbum.url);
    titleController.addListener(_onFieldChange);
    artistController.addListener(_onFieldChange);
    releaseYearController.addListener(_onFieldChange);
    genreController.addListener(_onFieldChange);
    urlController.addListener(_onFieldChange);
  }

  void _onFieldChange() => setState(() {});

  bool isValid() =>
      isTitleValid(titleController.text) &&
      isArtistValid(artistController.text) &&
      isReleaseYearValid(releaseYearController.text) &&
      isGenreValid(genreController.text) &&
      isUrlValid(urlController.text);

  void handleSubmit() {
    final album = Album(
      name: titleController.text,
      artist: artistController.text,
      releaseYear: int.parse(releaseYearController.text),
      genre: genreController.text,
      url: urlController.text,
      id: widget.defaultAlbum.id == defaultId
          ? UniqueKey().toString()
          : widget.defaultAlbum.id,
    );
    widget.onSubmit(album);
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          CustomTextField(controller: titleController, label: "Title"),
          CustomTextField(controller: artistController, label: "Artist"),
          CustomTextField(
              controller: releaseYearController,
              label: "Release Year",
              isNumeric: true),
          CustomTextField(controller: genreController, label: "Genre"),
          CustomTextField(controller: urlController, label: "Image Link"),
          const Spacer(),
          Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              SubmitButton(onPressed: handleSubmit, enabled: isValid()),
            ],
          ),
        ],
      ),
    );
  }
}

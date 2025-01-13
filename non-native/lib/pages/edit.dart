import 'package:album_manager/composable_widgets/header.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'error.dart';
import '../service/theme.dart';
import '../service/album_view_model.dart';
import '../composable_widgets/form.dart';

class EditPage extends StatelessWidget {
  final String albumId;
  final VoidCallback onBack;

  const EditPage({super.key, required this.albumId, required this.onBack});

  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<AlbumViewModel>(context);
    final album = viewModel.findById(albumId);

    if (album == null) {
      return ErrorPage(
        message: 'Album not found :(',
        onBack: () {
          onBack();
        },
      );
    }
    return Scaffold(
      appBar: const Header(text: 'Edit Album'),
      backgroundColor: AppTheme.ultimateBackground,
      body: AlbumForm(
        defaultAlbum: album,
        onSubmit: (updatedAlbum) {
          viewModel.edit(updatedAlbum);
          onBack();
        },
        onCancel: onBack,
      ),
    );
  }
}

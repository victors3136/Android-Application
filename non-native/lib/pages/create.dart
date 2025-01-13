import 'package:album_manager/composable_widgets/header.dart';
import 'package:album_manager/service/theme.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../model/album.dart';
import '../service/album_view_model.dart';
import '../composable_widgets/form.dart';

class CreatePage extends StatelessWidget {
  final VoidCallback onNavigateBack;

  const CreatePage({super.key, required this.onNavigateBack});

  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<AlbumViewModel>(context, listen: false);

    return Scaffold(
      appBar: const Header(text: 'Add a New Album'),
      backgroundColor: AppTheme.ultimateBackground,
      body: AlbumForm(
        defaultAlbum: Album(),
        onSubmit: (album) {
          viewModel.add(album);
          onNavigateBack();
        },
        onCancel: onNavigateBack,
      ),
    );
  }
}


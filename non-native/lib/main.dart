import 'package:album_manager/pages/read_all.dart';
import 'package:album_manager/pages/read_one.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'pages/create.dart';
import 'pages/edit.dart';
import 'service/album_view_model.dart';

void main() {
  runApp(
    ChangeNotifierProvider(
      create: (context) => AlbumViewModel(),
      child: const AlbumManager(),
    ),
  );
}

class AlbumManager extends StatelessWidget {
  const AlbumManager({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Album Manager',
      initialRoute: '/read',
      routes: {
        '/read': (context) => const ReadAllPage(),
        '/create': (context) => CreatePage(
              onNavigateBack: () => Navigator.of(context).pop(),
            ),
      },
      onGenerateRoute: (settings) {
        if (settings.name?.startsWith('/edit/') == true) {
          final parts = settings.name!.split('/');
          assert(parts.length > 2);
          final albumId = parts[2];
          return MaterialPageRoute(
            builder: (context) => EditPage(
              albumId: albumId,
              onBack: () => Navigator.of(context).pop(),
            ),
          );
        }
        if (settings.name?.startsWith('/read/') == true) {
          final parts = settings.name!.split('/');
          assert(parts.length > 2);
          final albumId = parts[2];
          return MaterialPageRoute(
            builder: (context) => ReadOnePage(albumId: albumId),
          );
        }
        return null; // Fallback for unknown routes
      },
    );
  }
}

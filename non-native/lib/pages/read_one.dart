import 'package:album_manager/composable_widgets/header.dart';
import 'package:flutter/material.dart';
import 'package:album_manager/composable_widgets/buttons.dart';
import 'package:album_manager/service/album_view_model.dart';
import 'package:provider/provider.dart';

import '../service/theme.dart';
import 'delete_confirmation.dart';

class ReadOnePage extends StatefulWidget {
  final String albumId;

  const ReadOnePage({super.key, required this.albumId});

  @override
  ReadOnePageState createState() => ReadOnePageState();
}

class ReadOnePageState extends State<ReadOnePage> {
  @override
  Widget build(BuildContext context) {
    final viewModel = Provider.of<AlbumViewModel>(context);
    final album = viewModel.findById(widget.albumId);

    if (album == null) {
      return Scaffold(
        backgroundColor: AppTheme.ultimateBackground,
        appBar: AppBar(
          title: const Text("Album Details"),
          backgroundColor: AppTheme.primaryBackground,
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Text(
                "Album is not part of the list",
                style: TextStyle(fontSize: 18, color: AppTheme.primaryText),
              ),
              const SizedBox(height: 16),
              BackButtonCustom(onPressed: () {
                Navigator.pop(context);
              }),
            ],
          ),
        ),
      );
    }

    return Scaffold(
      backgroundColor: AppTheme.ultimateBackground,
      appBar: Header(text: album.name),
      body: Container(
        decoration: const BoxDecoration(
          color: AppTheme.ultimateBackground,
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 16.0),
              child: Container(
                width: MediaQuery.of(context).size.width * 0.6,
                height: MediaQuery.of(context).size.width * 0.6,
                decoration: BoxDecoration(
                    color: AppTheme.primaryBackground,
                    shape: BoxShape.rectangle,
                    borderRadius: BorderRadius.circular(6)),
                child: Icon(
                    size: MediaQuery.of(context).size.width * 0.4,
                    Icons.album,
                    color: AppTheme.secondaryAccent),
              ),
            ),
            Text(
              album.artist,
              style: Theme.of(context)
                  .textTheme
                  .headlineSmall
                  ?.copyWith(color: Colors.white, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Text(
              "Released in ${album.releaseYear}",
              style: Theme.of(context)
                  .textTheme
                  .titleMedium
                  ?.copyWith(color: Colors.white70),
            ),
            const SizedBox(height: 8),
            Text(
              "Genre: ${album.genre}",
              style: Theme.of(context)
                  .textTheme
                  .titleMedium
                  ?.copyWith(color: Colors.white70),
            ),
            const Spacer(),
            Padding(
              padding:
                  const EdgeInsets.symmetric(vertical: 32.0, horizontal: 16.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  EditButton(
                    onPressed: () {
                      Navigator.pushNamed(context, '/edit/${album.id}');
                    },
                  ),
                  DeleteButton(
                    onPressed: () {
                      showDeleteConfirmationDialog(
                        context: context,
                        submit: () {
                          viewModel.delete(album.id);
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                                content:
                                    Text("${album.name} has been deleted.")),
                          );
                          Navigator.pop(context);
                        },
                        cleanup: () {
                          Navigator.pop(context);
                        },
                      );
                    },
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

import 'package:album_manager/composable_widgets/buttons.dart';
import 'package:album_manager/composable_widgets/header.dart';
import 'package:album_manager/pages/create.dart';
import 'package:album_manager/pages/delete_confirmation.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:album_manager/service/album_view_model.dart';
import '../service/theme.dart';
import '../composable_widgets/album_item.dart';

class ReadAllPage extends StatelessWidget {
  const ReadAllPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppTheme.ultimateBackground,
      appBar: const Header(text: "Album List"),
      body: Consumer<AlbumViewModel>(
        builder: (context, viewModel, child) {
          final albums = viewModel.albums;

          return albums.isEmpty
              ? const Center(
                  child: Text(
                  "No albums available.",
                  style: TextStyle(color: AppTheme.primaryText),
                ))
              : ListView.builder(
                  itemCount: albums.length,
                  itemBuilder: (context, index) {
                    final album = albums[index];
                    return AlbumItem(
                      album: album,
                      onViewButtonClick: () {
                        Navigator.pushNamed(context, '/read/${album.id}');
                      },
                      onEditButtonClick: () {
                        Navigator.pushNamed(context, '/edit/${album.id}');
                      },
                      onDeleteButtonClick: () {
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
                          cleanup: () {},
                        );
                      },
                    );
                  },
                );
        },
      ),
      bottomNavigationBar: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 8.0),
        child: AddButton(onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => CreatePage(
                onNavigateBack: () => {Navigator.pop(context)},
              ),
            ),
          );
        }),
      ),
    );
  }
}

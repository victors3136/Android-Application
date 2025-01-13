import 'package:flutter/material.dart';
import '../service/theme.dart';
import '../model/album.dart';
import 'buttons.dart';

class AlbumItem extends StatelessWidget {
  final Album album;
  final VoidCallback onViewButtonClick;
  final VoidCallback onEditButtonClick;
  final VoidCallback onDeleteButtonClick;

  const AlbumItem({
    super.key,
    required this.album,
    required this.onViewButtonClick,
    required this.onEditButtonClick,
    required this.onDeleteButtonClick,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.all(12),
      padding: const EdgeInsets.all(12),
      decoration: BoxDecoration(
        color: AppTheme.primaryBackground,
        borderRadius: BorderRadius.circular(16),
        border: Border.all(
          color: AppTheme.primaryAccent,
          width: 2,
        ),
      ),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Container(
            width: 64,
            height: 64,
            decoration: BoxDecoration(
              color: AppTheme.primaryBackground,
              borderRadius: BorderRadius.circular(8),
            ),
            child: const Icon(Icons.album, size: 40, color: AppTheme.secondaryAccent),
          ),
          const SizedBox(width: 16),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Expanded(
                      child: Text(
                        album.name,
                        style: const TextStyle(
                          fontSize: 18,
                          color: AppTheme.primaryText,
                        ),
                        maxLines: 1,
                        overflow: TextOverflow.ellipsis,
                      ),
                    ),
                    Text(
                      '${album.releaseYear}',
                      style: const TextStyle(
                        fontSize: 14,
                        color: AppTheme.primaryText,
                      ),
                    ),
                  ],
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      'by ${album.artist}',
                      style: const TextStyle(
                        fontSize: 14,
                        color: AppTheme.primaryText,
                      ),
                    ),
                    Text(
                      album.genre,
                      style: const TextStyle(
                        fontSize: 14,
                        color: AppTheme.primaryText,
                      ),
                    ),
                  ],
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    ViewButton(onPressed: onViewButtonClick),
                    EditButton(onPressed: onEditButtonClick),
                    DeleteButton(onPressed: onDeleteButtonClick)
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

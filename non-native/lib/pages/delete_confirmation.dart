import 'package:album_manager/composable_widgets/buttons.dart';
import 'package:flutter/material.dart';

import '../service/theme.dart';


Future<void> showDeleteConfirmationDialog({
  required BuildContext context,
  required VoidCallback cleanup,
  required VoidCallback submit,
}) {
  return showDialog<void>(
    context: context,
    barrierDismissible: true,
    builder: (BuildContext context) {
      return AlertDialog(
        backgroundColor: AppTheme.primaryBackground,
        title: const Text(
          'Confirm Deletion',
          style: TextStyle(color: AppTheme.primaryText),
        ),
        content: const Text(
          'Are you sure you want to delete this album? This action cannot be undone.',
          style: TextStyle(color: AppTheme.primaryText),
        ),
        actions: <Widget>[
          BackButtonCustom(
            onPressed: () {
              cleanup();
              Navigator.of(context).pop();
            },
          ),
          SubmitButton(
            onPressed: () {
              submit();
              cleanup();
            },
            enabled: true,
          ),
        ],
      );
    },
  );
}

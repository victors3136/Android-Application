import 'package:flutter/material.dart';

import '../service/theme.dart';

class CustomIconButton extends StatelessWidget {
  final VoidCallback onPressed;
  final IconData icon;
  final String? description;
  final bool enabled;

  const CustomIconButton({
    super.key,
    required this.onPressed,
    required this.icon,
    this.description,
    this.enabled = true,
  });

  @override
  Widget build(BuildContext context) {
    return IconButton(
      icon: Icon(icon),
      tooltip: description,
      color: enabled ? AppTheme.primaryAccent : AppTheme.secondaryAccent,
      onPressed: enabled ? onPressed : null,
    );
  }
}

class AddButton extends StatelessWidget {
  final VoidCallback onPressed;

  const AddButton({super.key, required this.onPressed});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.fromLTRB(32, 0, 32, 64),
      child: IconButton(
        onPressed: onPressed,
        icon: const Icon(Icons.library_add),
        color: AppTheme.secondaryAccent,
      ),
    );
  }
}

class DeleteButton extends StatelessWidget {
  final VoidCallback onPressed;

  const DeleteButton({super.key, required this.onPressed});

  @override
  Widget build(BuildContext context) {
    return CustomIconButton(
      onPressed: onPressed,
      icon: Icons.delete,
      description: "Delete",
    );
  }
}

class EditButton extends StatelessWidget {
  final VoidCallback onPressed;

  const EditButton({super.key, required this.onPressed});

  @override
  Widget build(BuildContext context) {
    return CustomIconButton(
      onPressed: onPressed,
      icon: Icons.edit,
      description: "Edit",
    );
  }
}

class ViewButton extends StatelessWidget {
  final VoidCallback onPressed;

  const ViewButton({super.key, required this.onPressed});

  @override
  Widget build(BuildContext context) {
    return CustomIconButton(
      onPressed: onPressed,
      icon: Icons.open_in_full,
      description: "View",
    );
  }
}

class BackButtonCustom extends StatelessWidget {
  final VoidCallback onPressed;

  const BackButtonCustom({super.key, required this.onPressed});

  @override
  Widget build(BuildContext context) {
    return CustomIconButton(
      onPressed: onPressed,
      icon: Icons.arrow_back_ios_new,
      description: "Back",
    );
  }
}

class SubmitButton extends StatelessWidget {
  final VoidCallback onPressed;
  final bool enabled;

  const SubmitButton({super.key, required this.onPressed, this.enabled = true});

  @override
  Widget build(BuildContext context) {
    return CustomIconButton(
      onPressed: onPressed,
      icon: Icons.check,
      description: "Submit",
      enabled: enabled,
    );
  }
}

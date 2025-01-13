import 'package:flutter/material.dart';

import '../service/theme.dart';

class Header extends StatelessWidget implements PreferredSizeWidget {
  final String text;

  const Header({super.key, required this.text});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Text(
        text,
        style: const TextStyle(
          color: AppTheme.primaryText,
          fontWeight: FontWeight.bold,
        ),
      ),
      backgroundColor: AppTheme.ultimateBackground,
      iconTheme: const IconThemeData(color: AppTheme.primaryText),
      elevation: 4,
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}

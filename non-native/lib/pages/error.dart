import 'package:flutter/material.dart';

import '../composable_widgets/buttons.dart';
import '../service/theme.dart';

class ErrorPage extends StatelessWidget {
  final String message;
  final VoidCallback onBack;

  const ErrorPage({super.key, required this.message, required this.onBack});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: AppTheme.ultimateBackground,
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
              message,
              style: const TextStyle(fontSize: 20, color: AppTheme.primaryText),
            ),
            const SizedBox(height: 20),
            BackButtonCustom(onPressed: onBack),
          ],
        ),
      ),
    );
  }
}

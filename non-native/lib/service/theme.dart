import 'package:flutter/material.dart'
    show Brightness, Color, ColorScheme, Colors, ThemeData;

class AppTheme {
  static const Color primaryBackground = Color(0xFF212121);
  static const Color secondaryBackground = Color(0xFFCFD8DC);
  static const Color primaryAccent = Color(0xFF00796B);
  static const Color secondaryAccent = Color(0xFF3B7893);
  static const Color primaryText = Color(0xFFCFD8DC);
  static const Color secondaryText = Color(0xFF212121);
  static const Color ultimateBackground = Color(0xBF364F57);

  static ThemeData get themeData {
    return ThemeData(
      scaffoldBackgroundColor: primaryBackground,
      primaryColor: primaryAccent,
      colorScheme: const ColorScheme(
        primary: primaryAccent,
        secondary: secondaryAccent,
        surface: primaryBackground,
        error: Colors.redAccent,
        onPrimary: primaryText,
        onSecondary: secondaryText,
        onSurface: primaryText,
        onError: Colors.white,
        brightness: Brightness.dark,
      ),
    );
  }
}

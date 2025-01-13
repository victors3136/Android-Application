import 'package:flutter/material.dart';

import '../service/theme.dart';


class CustomTextField extends StatelessWidget {
  final TextEditingController controller;
  final String label;
  final bool isNumeric;

  const CustomTextField({
    super.key,
    required this.controller,
    required this.label,
    this.isNumeric = false,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child:TextField(
        controller: controller,
        keyboardType: isNumeric ? TextInputType.number : TextInputType.text,
        style: const TextStyle(color: AppTheme.primaryText), // Custom text color
        decoration: InputDecoration(
          labelText: label,
          labelStyle: const TextStyle(color: AppTheme.secondaryAccent),
          enabledBorder: OutlineInputBorder(
            borderSide: const BorderSide(color: AppTheme.secondaryAccent),
            borderRadius: BorderRadius.circular(10.0),
          ),
          focusedBorder: OutlineInputBorder(
            borderSide: const BorderSide(color: AppTheme.primaryAccent),
            borderRadius: BorderRadius.circular(10.0),
          ),
        ),
      )
    );
  }
}

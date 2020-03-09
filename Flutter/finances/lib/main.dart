import 'package:finances/services/DB.dart';
import 'package:finances/ui/HomeWidget.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await DB.init();
  runApp(FinancesApp());
}

class FinancesApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Finances',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: HomeWidget(),
    );
  }
}

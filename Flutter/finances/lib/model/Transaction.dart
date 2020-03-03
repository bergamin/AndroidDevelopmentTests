import 'package:finances/model/Type.dart';

class Transaction {
  int id;
  double value;
  String category = "Undefined";
  DateTime date = DateTime.now();
  Type type;

  Transaction({int id, double value, String category, Type type}) {
    this.id = id;
    this.value = value;
    this.category = category;
    this.type = type;
  }
}

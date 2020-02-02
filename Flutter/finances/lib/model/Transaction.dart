import 'package:finances/model/Type.dart';

class Transaction {
  int id;
  double value;
  String category = "Undefined";
  DateTime date = DateTime.now();
  Type type;

  Transaction(double value, String category, Type type) {
    this.value = value;
    this.category = category;
    this.type = type;
  }
}

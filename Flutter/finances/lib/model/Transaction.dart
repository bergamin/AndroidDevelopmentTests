import 'package:finances/model/DataModel.dart';
import 'package:finances/model/Type.dart';
import 'package:flutter/foundation.dart';
import 'package:intl/intl.dart';

class Transaction extends DataModel {
  static String tableName = "transactions";

  int id;
  double value;
  String category = "Undefined";
  DateTime date = DateTime.now();
  Type type;

  Transaction({
    this.value,
    this.category,
    this.type,
  });

  Transaction._full({
    this.id,
    this.value,
    this.category,
    this.date,
    this.type,
  });

  Map<String, dynamic> toMap() {
    Map<String, dynamic> map = {
      "value": value,
      "category": category,
      "date": DateFormat("yyyy-MM-dd").format(date),
      "type": describeEnum(type),
    };

    if (id != null) {
      map["id"] = id;
    }
    return map;
  }

  static Transaction fromMap(Map<String, dynamic> map) {
    return Transaction._full(
      id: map["id"],
      value: map["value"],
      category: map["category"],
      date: DateFormat("yyyy-MM-dd").parse(map["date"]),
      type: Type.values.firstWhere((type) => describeEnum(type) == map["type"]),
    );
  }
}

import 'package:finances/model/Transaction.dart';
import 'package:finances/model/Type.dart';
import 'package:finances/services/DB.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:intl/intl.dart';

class DialogWidget extends StatefulWidget {
  Transaction transaction;

  DialogWidget(Transaction transaction) {
    this.transaction = transaction;
  }

  @override
  DialogWidgetState createState() => DialogWidgetState(transaction);
}

class DialogWidgetState extends State<DialogWidget> {
  Transaction transaction;

  DialogWidgetState(Transaction transaction) {
    this.transaction = transaction;
  }

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: Text(buildTitle()),
      content: Column(
        children: <Widget>[
          TextFormField(
            initialValue: transaction.value != null ? transaction.value.toString() : "",
            onChanged: (value) {
              transaction.value = double.parse(value);
            },
            decoration: const InputDecoration(
              icon: Icon(Icons.attach_money),
              labelText: "Value",
            ),
            keyboardType: TextInputType.number,
            autofocus: true,
          ),
          TextFormField(
            initialValue: transaction.date != null ? DateFormat("dd/MM/yyyy").format(transaction.date) : DateTime.now(),
            onChanged: (value) {
              transaction.date = DateFormat("dd/MM/yyyy").parse(value);
            },
            decoration: const InputDecoration(
              icon: Icon(Icons.event),
              labelText: "Date",
            ),
            keyboardType: TextInputType.datetime,
          ),
          TextFormField(
            initialValue: transaction.category != null ? transaction.category : "",
            onChanged: (value) {
              transaction.category = value;
            },
            decoration: const InputDecoration(
              icon: Icon(Icons.category),
              labelText: "Category",
            ),
            keyboardType: TextInputType.text,
          ),
        ],
      ),
      actions: <Widget>[
        FlatButton(
          child: Text("Cancel"),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
        FlatButton(
          child: Text(transaction.id == null ? "Add" : "Update"),
          onPressed: () {
            Navigator.of(context).pop();
            if(transaction.id == null) {
              _insert();
            } else {
              _update();
            }
          },
        ),
      ],
    );
  }

  String buildTitle() {
    String title;
    if (transaction.id == null) {
      title = "Add";
    } else {
      title = "Update";
    }
    switch (transaction.type) {
      case Type.REVENUE:
        title = "$title revenue";
        break;
      case Type.EXPENSE:
        title = "$title expense";
        break;
    }
    return title;
  }

  void _insert() async {
    await DB.insert(Transaction.tableName, transaction);
  }

  void _update() async {
    await DB.update(Transaction.tableName, transaction);
  }
}

import 'package:finances/model/Transaction.dart';
import 'package:finances/model/Type.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

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
            keyboardType: TextInputType.number,
          ),
          TextFormField(
            keyboardType: TextInputType.datetime,
          ),
          TextFormField(
            keyboardType: TextInputType.text,
          ),
        ],
      ),
      actions: <Widget>[
        FlatButton(
          child: Text("Cancel"),
          onPressed: () {
            // cancel action
          },
        ),
        FlatButton(
          child: Text(transaction.id == null ? "Add" : "Update"),
          onPressed: () {
            // confirm action
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
}

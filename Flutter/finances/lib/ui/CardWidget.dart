import 'package:finances/model/Transaction.dart';
import 'package:finances/model/Type.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:intl/intl.dart';

class CardWidget extends StatefulWidget {
  Transaction transaction;

  CardWidget(Transaction transaction) {
    this.transaction = transaction;
  }

  @override
  CardWidgetState createState() => CardWidgetState(transaction);
}

class CardWidgetState extends State<CardWidget> {
  Transaction transaction;
  ClipOval icon;

  CardWidgetState(Transaction transaction) {
    this.transaction = transaction;
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(
        left: 8.0,
        top: 8.0,
        right: 8.0,
      ),
      child: Container(
        color: Colors.white,
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: ClipOval(
                child: Material(
                  color: transaction.type == Type.REVENUE ? Colors.blueGrey : Colors.red,
                  child: Icon(
                    transaction.type == Type.REVENUE ? Icons.arrow_upward : Icons.arrow_downward,
                    color: Colors.white,
                    size: 50,
                  ),
                ),
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Padding(
                    padding: const EdgeInsets.all(4.0),
                    child: Text(
                      transaction.category,
                      style: TextStyle(
                        color: Colors.grey,
                        fontWeight: FontWeight.bold,
                        fontSize: 20,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(4.0),
                    child: Text(
                      DateFormat("dd/MM/yyyy").format(transaction.date),
                      style: TextStyle(
                        color: Colors.grey,
                        fontSize: 15,
                      ),
                    ),
                  ),
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(
                "\$ ${transaction.value}",
                style: TextStyle(
                  color: transaction.type == Type.REVENUE ? Colors.blueGrey : Colors.red,
                  fontWeight: FontWeight.bold,
                  fontSize: 20,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

import 'package:finances/model/Transaction.dart';
import 'package:finances/model/Type.dart';
import 'package:finances/ui/CardWidget.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class HomeWidget extends StatefulWidget {
  @override
  HomeWidgetState createState() => HomeWidgetState();
}

class HomeWidgetState extends State<HomeWidget> {
  var revenue = 10.00;
  var expense = 35.00;

  List<CardWidget> generateCardsList() {
    return [
      CardWidget(Transaction(10, "Savings", Type.REVENUE)),
      CardWidget(Transaction(30, "Home", Type.EXPENSE)),
      CardWidget(Transaction(5, "Food", Type.EXPENSE)),
      CardWidget(Transaction(10, "Savings", Type.REVENUE)),
      CardWidget(Transaction(30, "Home", Type.EXPENSE)),
      CardWidget(Transaction(5, "Food", Type.EXPENSE)),
      CardWidget(Transaction(10, "Savings", Type.REVENUE)),
      CardWidget(Transaction(30, "Home", Type.EXPENSE)),
      CardWidget(Transaction(5, "Food", Type.EXPENSE)),
      CardWidget(Transaction(10, "Savings", Type.REVENUE)),
      CardWidget(Transaction(30, "Home", Type.EXPENSE)),
      CardWidget(Transaction(5, "Food", Type.EXPENSE)),
      CardWidget(Transaction(10, "Savings", Type.REVENUE)),
      CardWidget(Transaction(30, "Home", Type.EXPENSE)),
      CardWidget(Transaction(5, "Food", Type.EXPENSE)),
      CardWidget(Transaction(10, "Savings", Type.REVENUE)),
      CardWidget(Transaction(30, "Home", Type.EXPENSE)),
      CardWidget(Transaction(5, "Food", Type.EXPENSE)),
    ];
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey,
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // O que fazer quando presisonar o botão
        },
        backgroundColor: Colors.green,
        foregroundColor: Colors.white,
        child: Icon(Icons.add),
      ),
      appBar: AppBar(
        title: Text("Finances"),
      ),
      body: Column(
        children: <Widget>[
          Container(
            color: Colors.white,
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  Column(
                    children: <Widget>[
                      Text(
                        "Revenue",
                        style: TextStyle(
                          color: Colors.grey,
                          fontSize: 20,
                        ),
                      ),
                      Text(
                        "Expense",
                        style: TextStyle(
                          color: Colors.grey,
                          fontSize: 20,
                        ),
                      ),
                      Text(
                        "Total",
                        style: TextStyle(
                          color: Colors.grey,
                          fontSize: 20,
                        ),
                      ),
                    ],
                    crossAxisAlignment: CrossAxisAlignment.start,
                  ),
                  Column(
                    children: <Widget>[
                      Text(
                        "\$ $revenue",
                        style: TextStyle(
                          color: Colors.blueGrey,
                          fontSize: 20,
                        ),
                      ),
                      Text(
                        "\$ $expense",
                        style: TextStyle(
                          color: Colors.red,
                          fontSize: 20,
                        ),
                      ),
                      Text(
                        "\$ ${revenue - expense}",
                        style: TextStyle(
                          color: revenue - expense < 0 ? Colors.red : Colors.blueGrey,
                          fontSize: 20,
                        ),
                      ),
                    ],
                    crossAxisAlignment: CrossAxisAlignment.end,
                  ),
                ],
              ),
            ),
          ),
          Expanded(
            child: ListView(
              children: generateCardsList(),
            ),
          ),
        ],
      ),
    );
  }
}

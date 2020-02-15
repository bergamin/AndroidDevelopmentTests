import 'package:finances/model/Transaction.dart';
import 'package:finances/model/Type.dart';
import 'package:finances/ui/CardWidget.dart';
import 'package:finances/ui/basic/SpeedDial.dart';
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
      backgroundColor: Colors.grey[300],
      appBar: AppBar(
        title: Text("Finances"),
      ),
      body: Column(
        children: <Widget>[
          Card(
            elevation: 4,
            margin: EdgeInsets.zero,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.only(
                bottomRight: Radius.circular(5),
                bottomLeft: Radius.circular(5),
              ),
            ),
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
            child: Padding(
              padding: const EdgeInsets.only(
                top: 0.5,
              ),
              child: ListView(
                padding: const EdgeInsets.only(
                  bottom: 80,
                ),
                children: generateCardsList(),
              ),
            ),
          ),
        ],
      ),
      floatingActionButton: SpeedDial(
        backgroundColor: Colors.green,
        foregroundColor: Colors.white,
        icon: Icon(Icons.add),
        children: [
          FloatingActionButton(
            onPressed: () {
              // revenue
            },
            backgroundColor: Colors.blueGrey,
            foregroundColor: Colors.white,
            child: Icon(Icons.arrow_upward),
            mini: true,
            tooltip: "Add Revenue",
          ),
          FloatingActionButton(
            onPressed: () {
              // expense
            },
            backgroundColor: Colors.red,
            foregroundColor: Colors.white,
            child: Icon(Icons.arrow_downward),
            mini: true,
            tooltip: "Add Expense",
          ),
        ],
      ),
    );
  }
}

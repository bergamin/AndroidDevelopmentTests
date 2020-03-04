import 'package:finances/model/Transaction.dart';
import 'package:finances/model/Type.dart';
import 'package:finances/services/DB.dart';
import 'package:finances/ui/CardWidget.dart';
import 'package:finances/ui/DialogWidget.dart';
import 'package:finances/ui/basic/SpeedDial.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class HomeWidget extends StatefulWidget {
  @override
  HomeWidgetState createState() => HomeWidgetState();
}

class HomeWidgetState extends State<HomeWidget> {
  List<Transaction> _transactions = [];
  List<CardWidget> get _cards => _transactions.map((transaction) => CardWidget(transaction)).toList();

  @override
  Widget build(BuildContext context) {
    SpeedDial speedDial;

    FloatingActionButton fabRevenue = FloatingActionButton(
      onPressed: () {
        // revenue
        showDialog<void>(
          context: context,
          builder: (BuildContext context) {
            return DialogWidget(Transaction(type: Type.REVENUE));
          },
        );
        speedDial.switchChildrenVisibility();
      },
      backgroundColor: Colors.blueGrey,
      foregroundColor: Colors.white,
      child: Icon(Icons.arrow_upward),
      mini: true,
      tooltip: "Add Revenue",
      splashColor: Colors.green,
    );

    FloatingActionButton fabExpense = FloatingActionButton(
      onPressed: () {
        // expense
        showDialog<void>(
          context: context,
          builder: (BuildContext context) {
            return DialogWidget(Transaction(type: Type.EXPENSE));
          },
        );
        speedDial.switchChildrenVisibility();
      },
      backgroundColor: Colors.red,
      foregroundColor: Colors.white,
      child: Icon(Icons.arrow_downward),
      mini: true,
      tooltip: "Add Expense",
      splashColor: Colors.amber,
    );

    speedDial = SpeedDial(
      backgroundColor: Colors.green,
      foregroundColor: Colors.white,
      icon: Icon(Icons.add),
      children: [
        fabRevenue,
        fabExpense,
      ],
      tooltip: "Add an entry",
      splashColor: Colors.amber,
    );

    double revenue = 0;
    double expense = 0;
    for (Transaction transaction in _transactions) {
      if (transaction.type == Type.EXPENSE) {
        expense += transaction.value;
      } else {
        revenue += transaction.value;
      }
    }

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
                          color: revenue - expense < 0
                              ? Colors.red
                              : Colors.blueGrey,
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
                children: _cards,
              ),
            ),
          ),
        ],
      ),
      floatingActionButton: speedDial,
    );
  }

  @override
  void initState() {
    refresh();
    super.initState();
  }

  void refresh() async {
    List<Map<String, dynamic>> _results = await DB.query(Transaction.tableName);
    _transactions = _results.map((mapTransaction) => Transaction.fromMap(mapTransaction)).toList();
    setState(() {});
  }
}

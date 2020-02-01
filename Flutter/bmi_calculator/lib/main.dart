import 'package:flutter/material.dart';

void main() {
    runApp(MaterialApp(
        home: Home(),
    ));
}

class Home extends StatefulWidget {
    @override
    _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(
                title: Text("BMI Calculator"),
                centerTitle: true,
                backgroundColor: Colors.green,
                actions: <Widget>[
                    IconButton(
                        icon: Icon(Icons.refresh),
                        onPressed: () {},
                    )
                ],
            ),
            backgroundColor: Colors.white,
            body: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: <Widget>[
                    Icon(Icons.person_outline, size: 120, color: Colors.green),
                    TextField(
                        keyboardType: TextInputType.number,
                        textAlign: TextAlign.center,
                        style: TextStyle(color: Colors.green, fontSize: 25),
                        decoration: InputDecoration(
                            labelText: "Weight (kg)",
                            labelStyle: TextStyle(color: Colors.green)
                        ),
                    ),
                    TextField(
                        keyboardType: TextInputType.number,
                        textAlign: TextAlign.center,
                        style: TextStyle(color: Colors.green, fontSize: 25),
                        decoration: InputDecoration(
                            labelText: "Height (cm)",
                            labelStyle: TextStyle(color: Colors.green)
                        ),
                    )
                ],
            ),
        );
    }
}

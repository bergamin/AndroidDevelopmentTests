import 'package:flutter/material.dart';

void main() {
    runApp(MaterialApp(
        title: "People Counter",
        home: Home()
    ));
}

class Home extends StatefulWidget {
    @override
    _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
    int _people = 0;
    String _infoText = "Welcome";

    void changePeople(int delta) {
        setState(() {
            _people += delta;
            if(_people < 0) {
                _infoText = "Inverted World!";
            } else if(_people < 10) {
                _infoText = "Welcome";
            } else {
                _infoText = "Full";
            }
        });
    }

    @override
    Widget build(BuildContext context) {
        var backgroundImage = Image.asset(
            "images/restaurant.jpg",
            fit: BoxFit.cover,
            height: 1000);
        var peopleText = Text(
            "People: $_people",
            style: TextStyle(
                color: Colors.white,
                fontWeight: FontWeight.bold
            ));
        var plusButton = Padding(
            padding: EdgeInsets.all(10),
            child: FlatButton(
                child: Text(
                    "+1",
                    style: TextStyle(
                        fontSize: 40,
                        color: Colors.white
                    ),
                ),
                onPressed: () { changePeople(1); }
            ));
        var minusButton = Padding(
            padding: EdgeInsets.all(10),
            child: FlatButton(
                child: Text(
                    "-1",
                    style: TextStyle(
                        fontSize: 40,
                        color: Colors.white
                    ),
                ),
                onPressed: () { changePeople(-1); }
            ));
        var infoText = Text(
            _infoText,
            style: TextStyle(
                color: Colors.white,
                fontStyle: FontStyle.italic,
                fontSize: 30
            ));
        
        return Stack(
            children: <Widget>[
                backgroundImage,
                Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                        peopleText,
                        Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: <Widget>[
                                plusButton,
                                minusButton
                            ],
                        ),
                        infoText
                    ],
                )
            ]
        );
    }
}

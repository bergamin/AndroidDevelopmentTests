import 'package:flutter/material.dart';

class SpeedDial extends StatelessWidget {
  final Icon icon;
  final String label;
  final Color foregroundColor;
  final Color backgroundColor;
  final List<FloatingActionButton> children;

  @override
  Widget build(BuildContext context) {
    FloatingActionButton mainFAB;
    if(label == null || label.isEmpty) {
      mainFAB = FloatingActionButton(
        backgroundColor: backgroundColor,
        foregroundColor: foregroundColor,
        child: icon,
        onPressed: () {

        },
      );
    } else {
      mainFAB = FloatingActionButton.extended(
        backgroundColor: backgroundColor,
        foregroundColor: foregroundColor,
        icon: icon,
        label: Text(label),
        onPressed: () {

        },
      );
    }

    var finalList = List<Widget>();
    finalList.add(
        Padding(
          padding: const EdgeInsets.only(top: 8.0),
          child: mainFAB,
        )
    );
    for(FloatingActionButton child in children) {
      finalList.add(
          Padding(
            padding: const EdgeInsets.only(top: 8.0),
            child: child,
          )
      );
    }

    Widget result = Column(
      children: finalList,
      verticalDirection: VerticalDirection.up,
    );

    return result;
  }

  const SpeedDial({
    this.icon,
    this.label,
    this.foregroundColor,
    this.backgroundColor,
    @required this.children,
  }) : assert(icon != null || (label != null && label != ""));
}

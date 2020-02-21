import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class SpeedDial extends StatefulWidget {
  final List<FloatingActionButton> children;
  final Icon icon;
  final String label;
  final Color foregroundColor;
  final Color backgroundColor;
  final String tooltip;
  final double elevation;
  final Color splashColor;

  SpeedDialState _speedDialState;

  SpeedDial({
    this.icon,
    this.label,
    this.foregroundColor,
    this.backgroundColor,
    this.tooltip,
    this.elevation = 6,
    this.splashColor,
    @required this.children,
  }) : assert(icon != null || (label != null && label.trim().isNotEmpty));

  @override
  State<StatefulWidget> createState() {
    _speedDialState = SpeedDialState(
      icon: icon,
      label: label,
      foregroundColor: foregroundColor,
      backgroundColor: backgroundColor,
      tooltip: tooltip,
      elevation: elevation,
      splashColor: splashColor,
      children: children,
    );
    return _speedDialState;
  }

  void switchChildrenVisibility() {
    _speedDialState.switchChildrenVisibility();
  }
}

class SpeedDialState extends State<SpeedDial> {
  final List<FloatingActionButton> children;
  final Icon icon;
  final String label;
  final Color foregroundColor;
  final Color backgroundColor;
  final String tooltip;
  final double elevation;
  final Color splashColor;

  bool areChildrenVisible = false;

  void switchChildrenVisibility() {
    setState(() {
      areChildrenVisible = !areChildrenVisible;
    });
  }

  @override
  Widget build(BuildContext context) {
    FloatingActionButton mainFAB;
    if (label == null || label.trim().isEmpty) {
      mainFAB = FloatingActionButton(
        backgroundColor: backgroundColor,
        foregroundColor: foregroundColor,
        child: icon,
        tooltip: tooltip,
        elevation: elevation,
        splashColor: splashColor,
        onPressed: () {
          switchChildrenVisibility();
        },
      );
    } else {
      mainFAB = FloatingActionButton.extended(
        backgroundColor: backgroundColor,
        foregroundColor: foregroundColor,
        icon: icon,
        label: Text(label),
        tooltip: tooltip,
        elevation: elevation,
        splashColor: splashColor,
        onPressed: () {
          switchChildrenVisibility();
        },
      );
    }

    var childrenColumn = List<Widget>();

    for (FloatingActionButton child in children) {
      List<Widget> rowItems = List<Widget>();

      if (child.tooltip != null && child.tooltip.isNotEmpty) {
        rowItems.add(
          Card(
            elevation: 2,
            child: Padding(
              padding: const EdgeInsets.all(5.0),
              child: Text(child.tooltip),
            ),
          ),
        );
      }
      rowItems.add(child);

      childrenColumn.add(Padding(
        padding: const EdgeInsets.all(8.0),
        child: Row(
          children: rowItems,
          mainAxisAlignment: MainAxisAlignment.end,
        ),
      ));
    }

    var childrenVisibility = Visibility(
      visible: areChildrenVisible,
      child: Column(
        children: childrenColumn,
        crossAxisAlignment: CrossAxisAlignment.end,
      ),
    );

    var finalColumn = Column(
      children: <Widget>[
        Padding(
          padding: const EdgeInsets.only(
            right: 8,
            top: 8,
          ),
          child: mainFAB,
        ),
        childrenVisibility,
      ],
      verticalDirection: VerticalDirection.up,
      crossAxisAlignment: CrossAxisAlignment.end,
    );

    return finalColumn;
  }

  SpeedDialState({
    this.icon,
    this.label,
    this.foregroundColor,
    this.backgroundColor,
    this.tooltip,
    this.elevation,
    this.splashColor,
    @required this.children,
  });
}

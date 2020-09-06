import 'package:animations/animations.dart';
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

class SpeedDialState extends State<SpeedDial> with SingleTickerProviderStateMixin {
  final List<FloatingActionButton> children;
  final Icon icon;
  final String label;
  final Color foregroundColor;
  final Color backgroundColor;
  final String tooltip;
  final double elevation;
  final Color splashColor;

  bool get _isAnimationRunningForwardsOrComplete {
    switch(_animationController.status) {
      case AnimationStatus.forward:
      case AnimationStatus.completed:
        return true;
      case AnimationStatus.reverse:
      case AnimationStatus.dismissed:
        return false;
    }
    assert(false);
    return null;
  }

  AnimationController _animationController;

  void switchChildrenVisibility() {
    if (_isAnimationRunningForwardsOrComplete) {
      _animationController.reverse();
    } else {
      _animationController.forward();
    }
  }

  @override
  void initState() {
    _animationController = AnimationController(
      value: 0,
      duration: Duration(milliseconds: 400),
      reverseDuration: Duration(milliseconds: 200),
      vsync: this,
    )..addStatusListener((AnimationStatus status) {
      setState(() {

      });
    });
    super.initState();
  }

  @override
  void dispose() {
    _animationController.dispose();
    super.dispose();
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

    var childrenList = List<Widget>();

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

      childrenList.add(Padding(
        padding: const EdgeInsets.all(8.0),
        child: Row(
          children: rowItems,
          mainAxisAlignment: MainAxisAlignment.end,
        ),
      ));
    }

    var animatedChildren = AnimatedBuilder(
      animation: _animationController,
      builder: (BuildContext context, Widget child) {
        return FadeScaleTransition(
          animation: _animationController,
          child: child,
        );
      },
      child: Visibility(
        visible: _animationController.status != AnimationStatus.dismissed,
        child: Column(children: childrenList),
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
        animatedChildren,
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

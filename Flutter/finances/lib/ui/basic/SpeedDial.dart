import 'package:flutter/material.dart';

class SpeedDial extends FloatingActionButton {
  final List<FloatingActionButton> children;

  static Widget buildFabMenu(List<FloatingActionButton> children) {
    
  }

  SpeedDial({
    key,
    child,
    tooltip,
    foregroundColor,
    backgroundColor,
    focusColor,
    hoverColor,
    splashColor,
    heroTag,
    elevation,
    focusElevation,
    hoverElevation,
    highlightElevation,
    disabledElevation,
    shape,
    clipBehavior = Clip.none,
    focusNode,
    autoFocus = false,
    materialTapTargetSize,
    @required this.children,
    @required Icon icon,
  }) : super(
    key: key,
    child: icon,
    tooltip: tooltip,
    foregroundColor: foregroundColor,
    backgroundColor: backgroundColor,
    focusColor: focusColor,
    hoverColor: hoverColor,
    splashColor: splashColor,
    heroTag: heroTag,
    elevation: elevation,
    focusElevation: focusElevation,
    hoverElevation: hoverElevation,
    highlightElevation: highlightElevation,
    disabledElevation: disabledElevation,
    onPressed: () {
      return buildFabMenu(children);
    },
    mini: false,
    shape: shape,
    clipBehavior: clipBehavior,
    focusNode: focusNode,
    autofocus: autoFocus,
    materialTapTargetSize: materialTapTargetSize,
    isExtended: false,
  );

  SpeedDial.extended({
    key,
    tooltip,
    foregroundColor,
    backgroundColor,
    focusColor,
    hoverColor,
    elevation,
    focusElevation,
    hoverElevation,
    splashColor,
    highlightElevation,
    disabledElevation,
    onPressed,
    shape,
    materialTapTargetSize,
    clipBehavior = Clip.none,
    focusNode,
    autoFocus = false,
    @required this.children,
    Icon icon,
    @required Widget label,
  }) : super.extended(
    key: key,
    tooltip: tooltip,
    foregroundColor: foregroundColor,
    backgroundColor: backgroundColor,
    focusColor: focusColor,
    hoverColor: hoverColor,
    elevation: elevation,
    focusElevation: focusElevation,
    hoverElevation: hoverElevation,
    splashColor: splashColor,
    highlightElevation: highlightElevation,
    disabledElevation: disabledElevation,
    onPressed: () {
      return buildFabMenu(children);
    },
    shape: shape,
    isExtended: true,
    materialTapTargetSize: materialTapTargetSize,
    clipBehavior: clipBehavior,
    focusNode: focusNode,
    autofocus: autoFocus,
    icon: icon,
    label: label,
  );
}

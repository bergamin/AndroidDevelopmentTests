package com.bergamin.tdd.ui.dialog;


import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.bergamin.tdd.R;

public class AlertDialogManager {

    public static void showToastOnSendingFailure(Context context) {
        showDialog(context, context.getString(R.string.message_alert_send_bid_failure));
    }

    public static void showAlertWhenUserExceedsNumberOfBidsLimit(Context context) {
        showDialog(context, context.getString(R.string.message_alert_bid_limit_exceeded));
    }

    public static void showAlertWhenUserBidsTwiceInARow(Context context) {
        showDialog(context, context.getString(R.string.message_alert_two_bids_in_a_row));
    }

    public void showAlertWhenBidLowerThanHighestBid(Context context) {
        showDialog(context, context.getString(R.string.message_alert_bid_should_be_higher_than_last_bid));
    }

    public static void showAlertWhenBidEqualsToHighestBid(Context context) {
        showDialog(context, context.getString(R.string.message_alert_bid_should_be_higher_than_last_bid));
    }

    public static void showAlertWhenInvalidValue(Context context) {
        showDialog(context, context.getString(R.string.message_alert_invalid_value));
    }

    private static void showDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(R.string.ok_button, null)
                .show();
    }
}

package com.bergamin.tdd.ui.dialog;


import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.bergamin.tdd.R;

public class AlertDialogManager {

    private final Context context;

    public AlertDialogManager(Context context) {
        this.context = context;
    }

    public void showToastOnSendingFailure() {
        showDialog(context.getString(R.string.message_alert_send_bid_failure));
    }

    public void showAlertWhenUserExceedsNumberOfBidsLimit() {
        showDialog(context.getString(R.string.message_alert_bid_limit_exceeded));
    }

    public void showAlertWhenUserBidsTwiceInARow() {
        showDialog(context.getString(R.string.message_alert_two_bids_in_a_row));
    }

    public void showAlertWhenBidLowerThanHighestBid() {
        showDialog(context.getString(R.string.message_alert_bid_should_be_higher_than_last_bid));
    }

    public void showAlertWhenBidEqualsToHighestBid() {
        showDialog(context.getString(R.string.message_alert_bid_should_be_higher_than_last_bid));
    }

    public void showAlertWhenInvalidValue() {
        showDialog(context.getString(R.string.message_alert_invalid_value));
    }

    private void showDialog(String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(R.string.ok_button, null)
                .show();
    }
}

package com.bergamin.tdd.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.bergamin.tdd.R;
import com.bergamin.tdd.database.dao.UserDAO;
import com.bergamin.tdd.model.Bid;
import com.bergamin.tdd.model.User;
import com.bergamin.tdd.ui.activity.UsersListActivity;

import java.util.List;

public class NewBidDialog {

    private final Context context;
    private final BidListener listener;
    private final UserDAO dao;

    public NewBidDialog(Context context,
                        BidListener listener,
                        UserDAO dao) {
        this.context = context;
        this.listener = listener;
        this.dao = dao;
    }

    public void show() {
        List<User> users = dao.all();
        if (noUsersRegistered(users)) return;
        configView(users);
    }

    private boolean noUsersRegistered(List<User> users) {
        if (users.isEmpty()) {
            showUserNotRegisteredDialog();
            return true;
        }
        return false;
    }

    private void showUserNotRegisteredDialog() {
        new AlertDialog.Builder(context)
                .setTitle(R.string.dialog_no_users_found)
                .setMessage(R.string.message_dialog_no_users_registered)
                .setPositiveButton(R.string.dialog_add_user, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent toUsersList = new Intent(context, UsersListActivity.class);
                        context.startActivity(toUsersList);
                    }
                }).show();
    }

    private void configView(List<User> users) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.form_bid, null, false);

        Spinner usersField = view.findViewById(R.id.form_bid_user);
        TextInputLayout textInputValue = view.findViewById(R.id.form_bid_value);

        configUsersSpinner(users, usersField);
        EditText valueField = textInputValue.getEditText();
        showNewBidDialog(view, usersField, valueField);
    }

    private void showNewBidDialog(View view, Spinner usersField, EditText valueField) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.alert_title_new_bid)
                .setView(view)
                .setPositiveButton(R.string.bid,
                        createNewBidListener(valueField, usersField))
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @NonNull
    private DialogInterface.OnClickListener createNewBidListener(
            final EditText valueField, final Spinner usersField) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String textValue = valueField.getText().toString();
                User user = (User) usersField.getSelectedItem();
                try {
                    double value = Double.parseDouble(textValue);
                    Bid bid = new Bid(user, value);
                    listener.createdBid(bid);
                } catch (NumberFormatException e) {
                    new AlertDialogManager(context).showAlertWhenInvalidValue();
                }
            }
        };
    }

    private void configUsersSpinner(List<User> users,
                                    Spinner availableUsers) {
        SpinnerAdapter adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                users);
        availableUsers.setAdapter(adapter);
    }

    public interface BidListener {
        void createdBid(Bid lance);
    }
}

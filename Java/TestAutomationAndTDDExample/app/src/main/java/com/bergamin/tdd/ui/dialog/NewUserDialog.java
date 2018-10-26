package com.bergamin.tdd.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.bergamin.tdd.R;
import com.bergamin.tdd.model.User;

public class NewUserDialog {

    private final Context context;
    private final UserListener listener;

    public NewUserDialog(Context context,
                         UserListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void show() {
        final View view = LayoutInflater.from(context)
                .inflate(R.layout.form_user, null, false);
        TextInputLayout textInputName = view.findViewById(R.id.form_user_name);
        EditText nameField = textInputName.getEditText();

        new AlertDialog.Builder(context)
                .setTitle(R.string.alert_title_new_user)
                .setView(view)
                .setPositiveButton(R.string.add,
                        createNewUserListener(nameField))
                .show();
    }

    @NonNull
    private DialogInterface.OnClickListener createNewUserListener(final EditText nameField) {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = nameField.getText().toString();
                User user = new User(name);
                listener.created(user);
            }
        };
    }

    public interface UserListener {
        void created(User user);
    }
}

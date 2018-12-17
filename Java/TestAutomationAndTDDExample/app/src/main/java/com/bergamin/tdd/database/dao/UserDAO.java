package com.bergamin.tdd.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.bergamin.tdd.database.DatabaseHelper;
import com.bergamin.tdd.database.contract.UserContract;
import com.bergamin.tdd.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.bergamin.tdd.database.contract.UserContract.KEY_NAME;
import static com.bergamin.tdd.database.contract.UserContract.TABLE_NAME;

public class UserDAO extends DatabaseHelper {

    public UserDAO(Context context) {
        super(context);
    }

    public User save(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = fillInValues(user);
        long id = db.insert(TABLE_NAME, null, values);
        return new User(id, user.getName());
    }

    public List<User> all() {
        String sql = "SELECT * FROM " + UserContract.TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        return addUserToList(c);
    }

    @NonNull
    private List<User> addUserToList(Cursor c) {
        List<User> users = new ArrayList<>();
        while (c.moveToNext()) {
            User user = fillInUser(c);
            users.add(user);
        }
        return users;
    }

    @NonNull
    private ContentValues fillInValues(User user) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        return values;
    }

    @NonNull
    private User fillInUser(Cursor c) {
        Long id = c.getLong(c.getColumnIndex(UserContract._ID));
        String name = c.getString(c.getColumnIndex(UserContract.KEY_NAME));
        return new User(id, name);
    }
}

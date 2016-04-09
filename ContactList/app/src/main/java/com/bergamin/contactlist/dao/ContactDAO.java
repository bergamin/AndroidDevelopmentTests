package com.bergamin.contactlist.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Guilherme on 02/04/2016.
 */
public class ContactDAO extends SQLiteOpenHelper {

    public ContactDAO(Context context) {
        super(context, "ContactList", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contacts (" +
                " id      INTEGER PRIMARY KEY" +
                ",name    TEXT NOT NULL" +
                ",address TEXT" +
                ",phone   TEXT" +
                ",webSite TEXT" +
                ",rating  REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}

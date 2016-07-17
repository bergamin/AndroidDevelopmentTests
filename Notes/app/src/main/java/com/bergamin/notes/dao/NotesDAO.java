package com.bergamin.notes.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Guilherme on 11/06/2016.
 */
public class NotesDAO extends SQLiteOpenHelper {

    public NotesDAO(Context context) {
        super(context, "Notes", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Lists (" +
                " id INTEGER PRIMARY KEY" +
                ",title TEXT NOT NULL);" +
                "CREATE TABLE ListItens (" +
                "id INTEGER PRIMARY KEY" +
                "id_list INTEGER NOT NULL" +
                "title TEXT NOT NULL" +
                "checked ";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

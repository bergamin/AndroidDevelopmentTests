package com.bergamin.tdd.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bergamin.tdd.database.contract.UserContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "auction-db";
    private static final int VERSION = 1;

    private static final String USER_TABLE = "CREATE TABLE " + UserContract.TABLE_NAME + " ("
            + UserContract._ID + " INTEGER PRIMARY KEY,"
            + UserContract.KEY_NAME + " TEXT)";

    protected DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

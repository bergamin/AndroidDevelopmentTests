package com.bergamin.contactlist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bergamin.contactlist.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 02/04/2016.
 */
public class ContactDAO extends SQLiteOpenHelper {

    public ContactDAO(Context context) {
        super(context, "ContactList", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contacts (" +
                " id      INTEGER PRIMARY KEY" +
                ",name    TEXT NOT NULL" +
                ",address TEXT" +
                ",phone   TEXT" +
                ",webSite TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion){
            case 1:

                /*
                Version 2 removes the rating column from the Contacts table.
                Since SQLite doesn't have DROP COLUMN, a new table is necessary
                */

                sql += "\nPRAGMA foreign_keys = OFF;";

                sql += "\nCREATE TABLE Contacts_Aux (             ";
                sql += "\n             id      INTEGER PRIMARY KEY";
                sql += "\n            ,name    TEXT NOT NULL      ";
                sql += "\n            ,address TEXT               ";
                sql += "\n            ,phone   TEXT               ";
                sql += "\n            ,webSite TEXT);             ";

                sql += "\nINSERT INTO Contacts_Aux (";
                sql += "\n            name          ";
                sql += "\n           ,address       ";
                sql += "\n           ,phone         ";
                sql += "\n           ,website       ";
                sql += "\n   ) SELECT name          ";
                sql += "\n           ,address       ";
                sql += "\n           ,phone         ";
                sql += "\n           ,website       ";
                sql += "\n       FROM Contacts;     ";

                sql += "\nDROP TABLE Contacts;";

                sql += "\nALTER TABLE Contacts_Aux";
                sql += "\n  RENAME TO Contacts;   ";

                sql += "\nPRAGMA foreign_keys = ON;";

                db.execSQL(sql);
        }
    }

    public void insert(Contact contact) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("name",contact.getName());
        data.put("address",contact.getAddress());
        data.put("phone",contact.getPhone());
        data.put("webSite",contact.getWebSite());

        db.insert("Contacts",null,data);

    }

    public List<Contact> getContacts() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Contacts", null);
        List<Contact> contacts = new ArrayList<Contact>();

        while(c.moveToNext()){

            Contact contact = new Contact();

            contact.setId(c.getLong(c.getColumnIndex("id")));
            contact.setName(c.getString(c.getColumnIndex("name")));
            contact.setAddress(c.getString(c.getColumnIndex("address")));
            contact.setPhone(c.getString(c.getColumnIndex("phone")));
            contact.setWebSite(c.getString(c.getColumnIndex("webSite")));

            contacts.add(contact);
        }

        c.close();
        return contacts;

    }

    public void delete(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        String[] parameters = {contact.getId().toString()};
        db.delete("Contacts","id = ?",parameters);
    }
}

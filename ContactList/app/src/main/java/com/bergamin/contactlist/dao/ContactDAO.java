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
        super(context, "ContactList", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Contacts (" +
                " id        INTEGER PRIMARY KEY" +
                ",name      TEXT NOT NULL" +
                ",address   TEXT" +
                ",phone     TEXT" +
                ",webSite   TEXT" +
                ",photoPath TEXT);";
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

            case 2:

                /*
                Version 3 adds the path to the contact's photo
                */

                sql = "ALTER TABLE Contacts ADD COLUMN photoPath";
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
        data.put("photoPath",contact.getPhotoPath());

        db.insert("Contacts",null,data);

    }

    public List<Contact> getContacts() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Contacts", null);
        List<Contact> contacts = new ArrayList<>();

        while(c.moveToNext()){

            Contact contact = new Contact();

            contact.setId(c.getLong(c.getColumnIndex("id")));
            contact.setName(c.getString(c.getColumnIndex("name")));
            contact.setAddress(c.getString(c.getColumnIndex("address")));
            contact.setPhone(c.getString(c.getColumnIndex("phone")));
            contact.setWebSite(c.getString(c.getColumnIndex("webSite")));
            contact.setPhotoPath(c.getString(c.getColumnIndex("photoPath")));

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

    public void update(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        String[] parameters = {contact.getId().toString()};
        db.update("Contacts",getContactData(contact),"id = ?",parameters);
    }

    private ContentValues getContactData(Contact contact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",contact.getName());
        contentValues.put("address",contact.getAddress());
        contentValues.put("phone",contact.getPhone());
        contentValues.put("webSite",contact.getWebSite());
        contentValues.put("photoPath",contact.getPhotoPath());
        return contentValues;
    }

    public boolean isContact(String phone){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM Contacts WHERE phone = ?",new String[]{phone});
        boolean result = cursor.getCount() > 0;
        return result;
    }
}

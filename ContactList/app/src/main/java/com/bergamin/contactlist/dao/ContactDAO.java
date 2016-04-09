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


    public void insert(Contact contact) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues data = new ContentValues();

        data.put("name",contact.getName());
        data.put("address",contact.getAddress());
        data.put("phone",contact.getPhone());
        data.put("webSite",contact.getWebSite());
        data.put("rating",contact.getRating());

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
            contact.setRating(c.getDouble(c.getColumnIndex("rating")));

            contacts.add(contact);
        }

        c.close();
        return contacts;

    }

}

package com.bergamin.contactlist.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.webkit.URLUtil
import com.bergamin.contactlist.R
import com.bergamin.contactlist.model.Contact
import com.bergamin.contactlist.util.FileHelper
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

/**
 * Created by Guilherme Taffarel Bergamin on 27/11/2017.
 */
class ContactDAO(var context: Context): SQLiteOpenHelper(context, "ContactList", null, 3) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(FileHelper().getResourceTextByID(R.raw.create_database, context))
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when(oldVersion){
            // Removes column Contact.rating
            1 -> db?.execSQL(FileHelper().getResourceTextByID(R.raw.db_version_2,context))
            // Adds column Contact.photoPath
            in 1..2 -> db?.execSQL(FileHelper().getResourceTextByID(R.raw.db_version_3,context))
        }
    }
    fun insert(contact: Contact) {
        var data = ContentValues()

        data.put("name",contact.name)
        data.put("address",contact.address)
        data.put("phone",contact.phone)
        data.put("webSite",contact.webSite)
        data.put("photoPath",contact.photoPath)

        writableDatabase.insert("Contacts",null,data)
    }
    fun getContacts(): List<Contact>? {
        var cursor = readableDatabase.rawQuery("SELECT * FROM Contacts",null)
        var contacts: MutableList<Contact>? = null

        while(cursor.moveToNext()){
            var contact = Contact()

            contact.id = cursor.getLong(cursor.getColumnIndex("id"))
            contact.name = cursor.getString(cursor.getColumnIndex("name"))
            contact.address = cursor.getString(cursor.getColumnIndex("address"))
            contact.phone = cursor.getString(cursor.getColumnIndex("phone"))
            contact.webSite = cursor.getString(cursor.getColumnIndex("webSite"))
            contact.photoPath = cursor.getString(cursor.getColumnIndex("photoPath"))

            contacts?.add(contact)
        }
        cursor.close()
        return contacts
    }
    fun delete(contact: Contact){
        var parameters = Array(1, { contact.id.toString() })
        writableDatabase.delete("Contacts","id = ?",parameters)
    }
    fun update(contact: Contact){
        var parameters = Array(1,{contact.id.toString()})
        writableDatabase.update("Contacts",getContactData(contact),"id = ?",parameters)
    }
    private fun getContactData(contact: Contact): ContentValues{
        var contentValues = ContentValues()

        contentValues.put("name",contact.name)
        contentValues.put("address",contact.address)
        contentValues.put("phone",contact.phone)
        contentValues.put("webSite",contact.webSite)
        contentValues.put("photoPath",contact.photoPath)

        return contentValues
    }
    fun isContact(phone: String): Boolean{
        var cursor = readableDatabase.rawQuery("SELECT 1 FROM Contacts WHERE phone = ?",Array(1,{phone}))
        return cursor.count > 0
    }
}

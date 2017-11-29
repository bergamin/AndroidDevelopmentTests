package com.bergamin.contactlist.util

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import com.bergamin.contactlist.dao.ContactDAO
import com.google.gson.Gson

/**
 * Created by gbergamin on 29/11/2017.
 *
 * Generics use in order:
 *
 * - params in doInBackground()
 * - used during execution
 * - response in onPostExecute
 */
class SendContactsTask(
        val context: Context): AsyncTask<Void, Void, String>() {

    var dialog: ProgressDialog? = null

    override fun doInBackground(vararg params: Void?): String {
        var dao = ContactDAO(context)
        var contacts = dao.getContacts()
        dao.close()
        var json = Gson().toJson(contacts)
        return WebClient().post(json)
    }

}
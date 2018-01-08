package com.bergamin.contactlist.util

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import com.bergamin.contactlist.R
import com.bergamin.contactlist.dao.ContactDAO
import com.google.gson.Gson

/**
 * Created by Guilherme Taffarel Bergamin on 29/11/2017.
 *
 * Generics use in order:
 *
 * - params in doInBackground()
 * - used during execution
 * - response in onPostExecute
 */
class SendContactsTask(
        private val context: Context): AsyncTask<Void, Void, String>() {

    //TODO: replace this Progress Dialog by something more user friendly
    var dialog: ProgressDialog? = null

    override fun doInBackground(vararg params: Void?): String? {
        val dao = ContactDAO(context)
        val contacts = dao.getContacts()
        dao.close()
        val json = Gson().toJson(contacts)
        return WebClient().post(json)
    }

    override fun onPreExecute() {
        dialog = ProgressDialog.show(context,context.getString(R.string.please_wait),context.getString(R.string.sending_contacts),true,true)
    }

    override fun onPostExecute(result: String?) {
        dialog?.dismiss()
        Toast.makeText(context,result,Toast.LENGTH_LONG).show()
    }
}

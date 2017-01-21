package com.bergamin.contactlist;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bergamin.contactlist.converter.ContactConverter;
import com.bergamin.contactlist.dao.ContactDAO;
import com.bergamin.contactlist.model.Contact;

import java.util.List;

/**
 * Created by Guilherme on 30/10/2016.
 */

/*
Generics use:
 - params in doInBackground()
 - used during execution
 - response in onPostExecute()
*/
public class SendContactsTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private ProgressDialog dialog;

    public SendContactsTask(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        ContactDAO dao = new ContactDAO(context);
        List<Contact> contacts = dao.getContacts();
        dao.close();
        ContactConverter converter = new ContactConverter();
        String json = converter.convertoToJSON(contacts);
        WebClient client = new WebClient();
        return client.post(json);
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context,"Please wait","Sending Contacts...",true,true);
    }

    @Override
    protected void onPostExecute(String response) {
        dialog.dismiss();
        Toast.makeText(context,response,Toast.LENGTH_LONG).show();
    }
}

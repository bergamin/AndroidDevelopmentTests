package com.bergamin.contactlist;

import android.Manifest;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bergamin.contactlist.adapter.ContactsAdapter;
import com.bergamin.contactlist.dao.ContactDAO;
import com.bergamin.contactlist.model.Contact;

import java.util.List;

/**
 * Created by Guilherme on 28/03/2016.
 */
public class ContactListActivity extends AppCompatActivity {

    public static final int PHONE_CALL_REQUEST = 1;
    public static final int SMS_RECEIVED_REQUEST = 2;
    private ListView contactsLvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list_layout);

        contactsLvw = (ListView) findViewById(R.id.contactsLvw);

        Button newContactBtn = (Button) findViewById(R.id.newContactBtn);
        newContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(contactsLvw);

        contactsLvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contact contact = (Contact) contactsLvw.getItemAtPosition(position);
                Intent intent = new Intent(ContactListActivity.this, FormActivity.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });

        if(ActivityCompat.checkSelfPermission(ContactListActivity.this,Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ContactListActivity.this,new String[]{Manifest.permission.RECEIVE_SMS},SMS_RECEIVED_REQUEST);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Contact contact = (Contact) contactsLvw.getItemAtPosition(info.position);

        MenuItem miCall = menu.add("Call");
        miCall.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (ActivityCompat.checkSelfPermission(ContactListActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ContactListActivity.this,new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_REQUEST);
                } else {
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel:" + contact.getPhone()));
                    startActivity(intentCall);
                }
                return false;
            }
        });

        MenuItem miFindInMap = menu.add("Find in map");
        Intent intentMap = new Intent(Intent.ACTION_VIEW);
        intentMap.setData(Uri.parse("geo:0,0?q=" + contact.getAddress()));
        miFindInMap.setIntent(intentMap);

        MenuItem miSendSMS = menu.add("Send SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:" + contact.getPhone()));
        miSendSMS.setIntent(intentSMS);

        MenuItem miVisitWebSite = menu.add("Visit website");
        String webSite = contact.getWebSite();
        if(!webSite.startsWith("http://")){
            webSite = "http://" + webSite;
        }
        Intent intentWebSite = new Intent(Intent.ACTION_VIEW);
        intentWebSite.setData(Uri.parse(webSite));
        miVisitWebSite.setIntent(intentWebSite);

        MenuItem miDelete = menu.add("Delete");
        miDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                ContactDAO dao = new ContactDAO(ContactListActivity.this);
                dao.delete(contact);
                dao.close();
                loadList();

                Toast.makeText(ContactListActivity.this, contact.getName() + " has been deleted",Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        loadList();
        super.onResume();
    }

    public void loadList(){

        ContactDAO dao = new ContactDAO(this);
        List<Contact> contacts = dao.getContacts();
        dao.close();

        ContactsAdapter adapter = new ContactsAdapter(this, contacts);
        contactsLvw.setAdapter(adapter);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PHONE_CALL_REQUEST){
            // check if the permission was granted
            // perform call
        }
    }
}

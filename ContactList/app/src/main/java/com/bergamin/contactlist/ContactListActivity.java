package com.bergamin.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bergamin.contactlist.dao.ContactDAO;
import com.bergamin.contactlist.model.Contact;

import java.util.List;

/**
 * Created by Guilherme on 28/03/2016.
 */
public class ContactListActivity extends AppCompatActivity {

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
                Intent intent = new Intent(ContactListActivity.this,FormActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(contactsLvw);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem miDelete = menu.add("Delete");

        miDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Contact contact = (Contact) contactsLvw.getItemAtPosition(info.position);

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

        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this,android.R.layout.simple_list_item_1,contacts);
        contactsLvw.setAdapter(adapter);

    }
}

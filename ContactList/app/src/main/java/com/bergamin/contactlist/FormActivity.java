package com.bergamin.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bergamin.contactlist.dao.ContactDAO;
import com.bergamin.contactlist.model.Contact;

import java.math.BigInteger;

/**
 * Created by Guilherme on 28/03/2016.
 */
public class FormActivity extends AppCompatActivity {

    private FormHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
        helper = new FormHelper(this);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        if(contact != null){
            helper.fillForm(contact);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.form_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.saveBtn:

                Contact contact = helper.getContact();
                ContactDAO dao = new ContactDAO(this);

                if(contact.getId() != null){
                    dao.update(contact);
                }else {
                    dao.insert(contact);
                }

                dao.close();

                Toast.makeText(FormActivity.this, "Contact " + contact.getName() + " successfully saved", Toast.LENGTH_SHORT).show();
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

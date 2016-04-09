package com.bergamin.contactlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Guilherme on 28/03/2016.
 */
public class ContactListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list_layout);

        String[] contacts = {"Daniel", "Ronald", "Jefferson", "Phillip"};

        ListView contactsLvw = (ListView) findViewById(R.id.contactsLvw);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        contactsLvw.setAdapter(adapter);

        Button newContactBtn = (Button) findViewById(R.id.newContactBtn);
        newContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this,FormActivity.class);
                startActivity(intent);
            }
        });
    }
}

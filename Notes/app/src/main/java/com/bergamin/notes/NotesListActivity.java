package com.bergamin.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Guilherme on 11/06/2016.
 */
public class NotesListActivity extends AppCompatActivity {

    private ListView notesList;
    private Button addButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list_layout);

        notesList = (ListView) findViewById(R.id.notesList);
        addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}

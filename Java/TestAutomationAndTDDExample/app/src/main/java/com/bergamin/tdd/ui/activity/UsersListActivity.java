package com.bergamin.tdd.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bergamin.tdd.R;
import com.bergamin.tdd.database.dao.UserDAO;
import com.bergamin.tdd.model.User;
import com.bergamin.tdd.ui.UserUpdater;
import com.bergamin.tdd.ui.dialog.NewUserDialog;
import com.bergamin.tdd.ui.recyclerview.adapter.UsersListAdapter;

public class UsersListActivity extends AppCompatActivity {

    private UserDAO dao;
    private UsersListAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().setTitle(R.string.users);
        initializeAttributes();
        configRecyclerView();
        configFab();
    }

    private void initializeAttributes() {
        dao = new UserDAO(this);
        adapter = new UsersListAdapter(this);
    }

    private void configFab() {
        FloatingActionButton fab = findViewById(R.id.users_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                showNewUserDialog();
            }
        });
    }

    private void configRecyclerView() {
        recyclerView = findViewById(R.id.users_list_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.add(dao.all());
    }

    private void showNewUserDialog() {
        NewUserDialog dialog = new NewUserDialog(
                this,
                new NewUserDialog.UserListener() {
                    @Override
                    public void created(User user) {
                        new UserUpdater(
                                dao,
                                adapter,
                                recyclerView)
                                .save(user);
                    }
                });
        dialog.show();
    }
}

package com.bergamin.tdd.ui;

import android.support.v7.widget.RecyclerView;

import com.bergamin.tdd.database.dao.UserDAO;
import com.bergamin.tdd.model.User;
import com.bergamin.tdd.ui.recyclerview.adapter.UsersListAdapter;

public class UserUpdater {

    private final UserDAO dao;
    private final UsersListAdapter adapter;
    private final RecyclerView recyclerView;

    public UserUpdater(UserDAO dao,
                       UsersListAdapter adapter,
                       RecyclerView recyclerView) {
        this.dao = dao;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
    }

    public void save(User user) {
        User savedUser = dao.save(user);
        updateList(savedUser);
    }

    private void updateList(User user) {
        adapter.add(user);
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }
}

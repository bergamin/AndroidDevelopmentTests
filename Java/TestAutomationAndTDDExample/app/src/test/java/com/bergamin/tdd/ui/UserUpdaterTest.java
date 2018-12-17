package com.bergamin.tdd.ui;

import android.support.v7.widget.RecyclerView;

import com.bergamin.tdd.database.dao.UserDAO;
import com.bergamin.tdd.model.User;
import com.bergamin.tdd.ui.recyclerview.adapter.UsersListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserUpdaterTest {
    
    @Mock
    private UserDAO dao;
    @Mock
    private UsersListAdapter adapter;
    @Mock
    private RecyclerView recyclerView;

    @Test
    public void shouldUpdateUsersList_whenUserIsSaved() {
        UserUpdater updater = new UserUpdater(dao, adapter, recyclerView);
        User alex = new User("Alex");

        when(dao.save(alex)).thenReturn(new User(1, "Alex"));
        when(adapter.getItemCount()).thenReturn(1);
        updater.save(alex);

        verify(dao).save(alex);
        verify(adapter).add(new User(1, "Alex"));
        verify(recyclerView).smoothScrollToPosition(0);
    }
}

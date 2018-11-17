package com.bergamin.tdd.ui.activity;

import android.content.Context;

import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.api.retrofit.client.ResponseListener;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.ui.recyclerview.adapter.AuctionsListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AuctionsListActivityTest {

    @Mock
    private Context context;
    @Mock
    private AuctionWebClient client;
    @Spy
    private AuctionsListAdapter adapter = new AuctionsListAdapter(context);

    @Test
    public void shouldUpdateAuctionsList_whenGetsAuctionsFromAPI() {
        AuctionsListActivity activity = new AuctionsListActivity();
        Mockito.doNothing().when(adapter).refreshList();
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                ResponseListener<List<Auction>> argument = invocation.getArgument(0);
                argument.success(new ArrayList<>(Arrays.asList(
                        new Auction("Computer"),
                        new Auction("Car")
                )));
                return null;
            }
        }).when(client).all(ArgumentMatchers.any(ResponseListener.class));

        activity.getAuctions(adapter, client);
        int amount = adapter.getItemCount();

        assertThat(amount, is(2));
    }
}
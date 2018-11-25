package com.bergamin.tdd.ui.activity;

import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.api.retrofit.client.ResponseListener;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.ui.recyclerview.adapter.AuctionsListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuctionsListActivityTest {

    @Mock
    private AuctionWebClient client;
    @Mock
    private AuctionsListAdapter adapter;

    @Test
    public void shouldUpdateAuctionsList_whenGetsAuctionsFromAPI() {
        AuctionsListActivity activity = new AuctionsListActivity();
        final ArrayList<Auction> auctions = new ArrayList<>(Arrays.asList(
                new Auction("Computer"),
                new Auction("Car")
        ));
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                ResponseListener<List<Auction>> argument = invocation.getArgument(0);
                argument.success(auctions);
                return null;
            }
        }).when(client).all(any(ResponseListener.class));

        activity.getAuctions(adapter, client);

        verify(client).all(any(ResponseListener.class));
        verify(adapter).update(auctions);
    }
}

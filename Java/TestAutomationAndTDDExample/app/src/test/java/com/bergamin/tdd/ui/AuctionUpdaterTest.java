package com.bergamin.tdd.ui;

import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.api.retrofit.client.ResponseListener;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.ui.recyclerview.adapter.AuctionsListAdapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuctionUpdaterTest {
    @Mock
    private AuctionWebClient client;
    @Mock
    private AuctionsListAdapter adapter;
    @Mock
    private AuctionUpdater.AuctionsLoadingErrorListener listener;

    @Test
    public void shouldUpdateAuctionsList_whenGetsAuctionsFromAPI() {
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

        AuctionUpdater.getAuctions(adapter, client, listener);

        verify(client).all(any(ResponseListener.class));
        verify(adapter).update(auctions);
    }

    @Test
    public void shouldShowFailingMessage_whenAuctionsSearchFails() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ResponseListener<List<Auction>> argument = invocation.getArgument(0);
                argument.failure(anyString());
                return null;
            }
        }).when(client).all(any(ResponseListener.class));

        AuctionUpdater.getAuctions(adapter, client, listener);

        verify(listener).loadingError(anyString());
    }
}

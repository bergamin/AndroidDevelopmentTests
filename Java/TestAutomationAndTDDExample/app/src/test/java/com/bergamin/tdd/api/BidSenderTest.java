package com.bergamin.tdd.api;

import android.content.Context;

import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.model.Bid;
import com.bergamin.tdd.model.User;
import com.bergamin.tdd.ui.dialog.AlertDialogManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BidSenderTest {

    @Mock
    private AuctionWebClient client;
    @Mock
    private BidSender.ProcessedBidListener listener;
    @Mock
    private Context context;
    @Mock
    private AlertDialogManager manager;

    @Test
    public void shouldShowErrorMessage_whenBidLowerThanCurrentBid() {
        BidSender sender = new BidSender(client, listener, context, manager);
        Auction auction = new Auction("Computer");

        auction.bid(new Bid(new User("Alex"), 200));
        sender.send(auction, new Bid(new User("Fran"), 100));

        Mockito.verify(manager).showAlertWhenBidLowerThanHighestBid(context);
    }
}

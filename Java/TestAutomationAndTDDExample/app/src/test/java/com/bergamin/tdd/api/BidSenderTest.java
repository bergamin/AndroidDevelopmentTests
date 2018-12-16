package com.bergamin.tdd.api;

import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.exception.NewBidLowerThanHighestBidException;
import com.bergamin.tdd.exception.UserExceedsNumberOfBidsLimitException;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.model.Bid;
import com.bergamin.tdd.model.User;
import com.bergamin.tdd.ui.dialog.AlertDialogManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BidSenderTest {

    @Mock
    private AuctionWebClient client;
    @Mock
    private BidSender.ProcessedBidListener listener;
    @Mock
    private AlertDialogManager manager;
    @Mock
    private Auction auction;

    private User alex = new User("Alex");
    private User fran = new User("Fran");

    @Test
    public void shouldShowErrorMessage_whenBidLowerThanCurrentBid() {
        BidSender sender = new BidSender(client, listener, manager);
        doThrow(NewBidLowerThanHighestBidException.class)
                .when(auction).bid(ArgumentMatchers.any(Bid.class));

        sender.send(auction, new Bid(fran, 100));

        verify(manager).showAlertWhenBidLowerThanHighestBid();
    }

    @Test
    public void should_ShowErrorMessage_whenUserExceedsBidLimit() {
        BidSender sender = new BidSender(client, listener, manager);
        doThrow(UserExceedsNumberOfBidsLimitException.class)
                .when(auction).bid(ArgumentMatchers.any(Bid.class));

        sender.send(auction, new Bid(alex, 200));

        verify(manager).showAlertWhenUserExceedsNumberOfBidsLimit();
    }
}

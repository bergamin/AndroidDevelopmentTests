package com.bergamin.tdd.api;

import android.content.Context;

import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.api.retrofit.client.ResponseListener;
import com.bergamin.tdd.exception.NewBidEqualsToHighestBidException;
import com.bergamin.tdd.exception.NewBidLowerThanHighestBidException;
import com.bergamin.tdd.exception.UserBidsTwiceInARowException;
import com.bergamin.tdd.exception.UserExceedsNumberOfBidsLimitException;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.model.Bid;
import com.bergamin.tdd.ui.dialog.AlertDialogManager;

public class BidSender {

    private final AuctionWebClient client;
    private final ProcessedBidListener listener;
    private final AlertDialogManager manager;

    public BidSender(AuctionWebClient client,
                     ProcessedBidListener listener,
                     AlertDialogManager manager) {
        this.client = client;
        this.listener = listener;
        this.manager = manager;
    }

    public void send(final Auction auction, Bid bid) {
        try {
            auction.bid(bid);
            client.bid(bid, auction.getId(), new ResponseListener<Void>() {
                @Override
                public void success(Void response) {
                    listener.processed(auction);
                }

                @Override
                public void failure(String message) {
                    manager.showToastOnSendingFailure();
                }
            });
        } catch (NewBidLowerThanHighestBidException exception) {
            manager.showAlertWhenBidLowerThanHighestBid();
        } catch (NewBidEqualsToHighestBidException exception) {
            manager.showAlertWhenBidEqualsToHighestBid();
        } catch (UserBidsTwiceInARowException exception) {
            manager.showAlertWhenUserBidsTwiceInARow();
        } catch (UserExceedsNumberOfBidsLimitException exception) {
            manager.showAlertWhenUserExceedsNumberOfBidsLimit();
        }
    }

    public interface ProcessedBidListener {
        void processed(Auction auction);
    }
}

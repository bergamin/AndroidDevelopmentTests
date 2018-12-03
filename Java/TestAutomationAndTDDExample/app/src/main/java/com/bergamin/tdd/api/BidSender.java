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

import static com.bergamin.tdd.ui.dialog.AlertDialogManager.showAlertWhenBidEqualsToHighestBid;
import static com.bergamin.tdd.ui.dialog.AlertDialogManager.showAlertWhenUserBidsTwiceInARow;
import static com.bergamin.tdd.ui.dialog.AlertDialogManager.showAlertWhenUserExceedsNumberOfBidsLimit;
import static com.bergamin.tdd.ui.dialog.AlertDialogManager.showToastOnSendingFailure;

public class BidSender {

    private final AuctionWebClient client;
    private final ProcessedBidListener listener;
    private final Context context;
    private final AlertDialogManager manager;

    public BidSender(AuctionWebClient client,
                     ProcessedBidListener listener,
                     Context context,
                     AlertDialogManager manager) {
        this.client = client;
        this.listener = listener;
        this.context = context;
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
                    showToastOnSendingFailure(context);
                }
            });
        } catch (NewBidLowerThanHighestBidException exception) {
            manager.showAlertWhenBidLowerThanHighestBid(context);
        } catch (NewBidEqualsToHighestBidException exception) {
            showAlertWhenBidEqualsToHighestBid(context);
        } catch (UserBidsTwiceInARowException exception) {
            showAlertWhenUserBidsTwiceInARow(context);
        } catch (UserExceedsNumberOfBidsLimitException exception) {
            showAlertWhenUserExceedsNumberOfBidsLimit(context);
        }
    }

    public interface ProcessedBidListener {
        void processed(Auction auction);
    }
}

package com.bergamin.tdd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bergamin.tdd.R;
import com.bergamin.tdd.api.BidSender;
import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.database.dao.UserDAO;
import com.bergamin.tdd.formatter.CurrencyFormatter;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.model.Bid;
import com.bergamin.tdd.ui.dialog.AlertDialogManager;
import com.bergamin.tdd.ui.dialog.NewBidDialog;

import static com.bergamin.tdd.ui.activity.AuctionConstants.KEY_AUCTION;

public class AuctionBidsActivity extends AppCompatActivity {

    private TextView highestBids;
    private TextView lowestBid;
    private TextView highestBid;
    private TextView description;
    private CurrencyFormatter formatter;
    private UserDAO dao;
    private Auction receivedAuction;
    private final AuctionWebClient client = new AuctionWebClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_bids);
        getSupportActionBar().setTitle(R.string.appbar_title_auction_bids);
        loadReceivedAuction();
    }

    private void loadReceivedAuction() {
        Intent receivedData = getIntent();

        if (receivedData.hasExtra(KEY_AUCTION)) {
            receivedAuction = (Auction) receivedData.getSerializableExtra(KEY_AUCTION);
            initializeAttributes();
            initializeViews();
            showData();
            configFab();
        } else {
            finish();
        }
    }

    private void initializeViews() {
        description = findViewById(R.id.auction_bids_description);
        highestBid = findViewById(R.id.auction_bids_highest_bid);
        lowestBid = findViewById(R.id.auction_bids_lowest_bid);
        highestBids = findViewById(R.id.auction_bids_highest_bids);
    }

    private void initializeAttributes() {
        formatter = new CurrencyFormatter();
        dao = new UserDAO(this);
    }

    private void configFab() {
        FloatingActionButton fab = findViewById(R.id.auction_bids_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNewBidDialog();
            }
        });
    }

    private void showNewBidDialog() {
        NewBidDialog dialog = new NewBidDialog(
                this,
                new NewBidDialog.BidListener() {
                    @Override
                    public void createdBid(Bid bid) {
                        sendBid(bid);
                    }
                },
                dao);
        dialog.show();
    }

    private void sendBid(Bid bid) {
        BidSender sender = new BidSender(
                client,
                processedBidListener(),
                new AlertDialogManager(this));
        sender.send(receivedAuction, bid);
    }

    @NonNull
    private BidSender.ProcessedBidListener processedBidListener() {
        return new BidSender.ProcessedBidListener() {
            @Override
            public void processed(Auction auction) {
                receivedAuction = auction;
                showData();
            }
        };
    }

    private void showData() {
        addDescription(receivedAuction);
        addHighestBid(receivedAuction);
        addLowestBid(receivedAuction);
        addHighestBids(receivedAuction);
    }

    private void addHighestBids(Auction auction) {
        StringBuilder sb = new StringBuilder();
        for (Bid bid : auction.getThreeHighestBids()) {
            sb.append(bid.getValue())
                    .append(" - ")
                    .append(bid.getUser())
                    .append("\n");
        }
        String textHighestBids = sb.toString();
        highestBids.setText(textHighestBids);
    }

    private void addLowestBid(Auction auction) {
        lowestBid.setText(formatter.format(auction.getLowestBid()));
    }

    private void addHighestBid(Auction auction) {
        highestBid.setText(formatter.format(auction.getHighestBid()));
    }

    private void addDescription(Auction auction) {
        description.setText(auction.getDescription());
    }
}

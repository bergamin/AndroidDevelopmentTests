package com.bergamin.tdd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bergamin.tdd.R;
import com.bergamin.tdd.formatter.CurrencyFormatter;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.model.Bid;

public class AuctionBidsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_bids);

        Intent intent = getIntent();
        if (intent.hasExtra("auction")) {
            Auction auction = (Auction) intent.getSerializableExtra("auction");
            CurrencyFormatter formatter = new CurrencyFormatter();

            TextView description = findViewById(R.id.auction_bids_description);
            description.setText(auction.getDescription());

            TextView highestBid = findViewById(R.id.auction_bids_highest_bid);
            highestBid.setText(formatter.format(auction.getHighestBid()));

            TextView lowestBid = findViewById(R.id.auction_bids_lowest_bid);
            lowestBid.setText(formatter.format(auction.getLowestBid()));

            TextView highestBids = findViewById(R.id.auction_bids_highest_bids);
            StringBuilder sb = new StringBuilder();
            for (Bid bid : auction.getThreeHighestBids()) {
                sb.append(bid.getValue()).append("\n");
            }
            highestBids.setText(sb.toString());
        }
    }
}

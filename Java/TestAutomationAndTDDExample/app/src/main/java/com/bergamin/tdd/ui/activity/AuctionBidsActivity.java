package com.bergamin.tdd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bergamin.tdd.R;
import com.bergamin.tdd.model.Auction;

public class AuctionBidsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_bids);

        Intent intent = getIntent();
        if (intent.hasExtra("auction")) {
            Auction auction = (Auction) intent.getSerializableExtra("auction");
            TextView description = findViewById(R.id.auction_bids_description);
            description.setText(auction.getDescription());
        }
    }
}

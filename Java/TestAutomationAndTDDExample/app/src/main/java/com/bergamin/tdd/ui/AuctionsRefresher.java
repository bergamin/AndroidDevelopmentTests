package com.bergamin.tdd.ui;

import android.content.Context;
import android.widget.Toast;

import com.bergamin.tdd.R;
import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.api.retrofit.client.ResponseListener;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.ui.recyclerview.adapter.AuctionsListAdapter;

import java.util.List;

public class AuctionsRefresher {
    public void getAuctions(final AuctionsListAdapter adapter, AuctionWebClient client, final Context context) {
        client.all(new ResponseListener<List<Auction>>() {
            @Override
            public void success(List<Auction> auctions) {
                adapter.update(auctions);
            }

            @Override
            public void failure(String message) {
                showFailureMessage(context);
            }
        });
    }

    public void showFailureMessage(Context context) {
        Toast.makeText(context,
                R.string.message_alert_load_auctions_error,
                Toast.LENGTH_SHORT).show();
    }
}

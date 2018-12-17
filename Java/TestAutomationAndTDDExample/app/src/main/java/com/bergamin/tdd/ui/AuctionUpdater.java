package com.bergamin.tdd.ui;

import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.api.retrofit.client.ResponseListener;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.ui.recyclerview.adapter.AuctionsListAdapter;

import java.util.List;

public class AuctionUpdater {

    public static void getAuctions(final AuctionsListAdapter adapter,
                            AuctionWebClient client,
                            final AuctionsLoadingErrorListener errorListener) {
        client.all(new ResponseListener<List<Auction>>() {
            @Override
            public void success(List<Auction> auctions) {
                adapter.update(auctions);
            }

            @Override
            public void failure(String message) {
                errorListener.loadingError(message);
            }
        });
    }

    public interface AuctionsLoadingErrorListener {
        void loadingError(String message);
    }
}

package com.bergamin.tdd.api.retrofit.client;

import com.bergamin.tdd.api.retrofit.RetrofitInitializer;
import com.bergamin.tdd.api.retrofit.service.AuctionService;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.model.Bid;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuctionWebClient {

    private final AuctionService service;

    public AuctionWebClient() {
        service = new RetrofitInitializer().getAuctionService();
    }

    public void bid(Bid bid, Long id, final ResponseListener<Void> listener) {
        Call<Void> call = service.bid(id, bid);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    listener.success(null);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.failure(t.getMessage());
            }
        });
    }

    public void all(final ResponseListener<List<Auction>> listener) {
        Call<List<Auction>> call = service.all();
        call.enqueue(new Callback<List<Auction>>() {
            @Override
            public void onResponse(Call<List<Auction>> call, Response<List<Auction>> response) {
                if (hasData(response)) {
                    listener.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Auction>> call, Throwable t) {
                listener.failure(t.getMessage());
            }
        });
    }

    private boolean hasData(Response<List<Auction>> response) {
        return response.isSuccessful() && response.body() != null;
    }
}

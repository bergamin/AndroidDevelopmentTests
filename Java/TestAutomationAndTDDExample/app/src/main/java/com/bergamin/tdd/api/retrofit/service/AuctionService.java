package com.bergamin.tdd.api.retrofit.service;

import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.model.Bid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AuctionService {

    @GET("auction")
    Call<List<Auction>> all();

    @PUT("auction/{id}/bid")
    Call<Void> bid(@Path("id") Long id, @Body Bid bid);

}

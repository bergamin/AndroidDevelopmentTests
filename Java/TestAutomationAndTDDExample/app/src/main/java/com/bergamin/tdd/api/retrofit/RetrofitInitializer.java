package com.bergamin.tdd.api.retrofit;

import android.support.annotation.NonNull;

import com.bergamin.tdd.api.retrofit.service.AuctionService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class RetrofitInitializer {

    private static final String BASE_URL = "http://api_address/";
    private final Retrofit retrofit;

    public RetrofitInitializer() {
        OkHttpClient client = configHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @NonNull
    private OkHttpClient configHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);
        return new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
    }

    public AuctionService getAuctionService() {
        return retrofit.create(AuctionService.class);
    }
}

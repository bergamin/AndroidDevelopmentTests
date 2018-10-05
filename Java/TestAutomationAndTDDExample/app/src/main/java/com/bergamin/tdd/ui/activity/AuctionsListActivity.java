package com.bergamin.tdd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.bergamin.tdd.R;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.model.Bid;
import com.bergamin.tdd.model.User;
import com.bergamin.tdd.ui.recyclerview.adapter.AuctionsListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuctionsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auctions_list);

        AuctionsListAdapter adapter = new AuctionsListAdapter(this, getSampleAuctions());
        RecyclerView recyclerView = findViewById(R.id.auctions_list_recyclerview);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AuctionsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Auction auction) {
                Intent intent = new Intent(AuctionsListActivity.this, AuctionBidsActivity.class);
                intent.putExtra("auction", auction);
                startActivity(intent);
            }
        });
    }

    private List<Auction> getSampleAuctions() {
        Auction console = new Auction("Console");
        console.bid(new Bid(new User("Alex"), 200));
        console.bid(new Bid(new User("Fran"), 300));
        return new ArrayList<>(Arrays.asList(console));
    }
}

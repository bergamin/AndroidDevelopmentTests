package com.bergamin.tdd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bergamin.tdd.R;
import com.bergamin.tdd.api.retrofit.client.AuctionWebClient;
import com.bergamin.tdd.model.Auction;
import com.bergamin.tdd.ui.AuctionUpdater;
import com.bergamin.tdd.ui.recyclerview.adapter.AuctionsListAdapter;

import static com.bergamin.tdd.ui.activity.AuctionConstants.KEY_AUCTION;

public class AuctionsListActivity extends AppCompatActivity {

    private final AuctionWebClient client = new AuctionWebClient();
    private AuctionsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auctions_list);
        getSupportActionBar().setTitle(R.string.appbar_title_auctions);
        configAuctionsList();
    }

    private void configAuctionsList() {
        configAdapter();
        configRecyclerView();
    }

    private void configRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.auctions_list_recyclerview);
        recyclerView.setAdapter(adapter);
    }

    private void configAdapter() {
        adapter = new AuctionsListAdapter(this);
        adapter.setOnItemClickListener(new AuctionsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Auction auction) {
                showBidsScreen(auction);
            }
        });
    }

    private void showBidsScreen(Auction auction) {
        Intent toAuctionBids = new Intent(
                AuctionsListActivity.this,
                AuctionBidsActivity.class);
        toAuctionBids.putExtra(KEY_AUCTION, auction);
        startActivity(toAuctionBids);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AuctionUpdater.getAuctions(adapter, client, new AuctionUpdater.AuctionsLoadingErrorListener() {
            @Override
            public void loadingError(String message) {
                showFailureMessage();
            }
        });
    }

    public void showFailureMessage() {
        Toast.makeText(this,
                R.string.message_alert_load_auctions_error,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.auctions_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.auctions_list_users_menu) {
            Intent toUsersList = new Intent(this, UsersListActivity.class);
            startActivity(toUsersList);
        }
        return super.onOptionsItemSelected(item);
    }
}

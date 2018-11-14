package com.bergamin.tdd.ui.recyclerview.adapter;

import com.bergamin.tdd.model.Auction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AuctionsListAdapterTest {

    @Test
    public void should_updateAuctionsList_whenReceivesAuctionsList() {
        AuctionsListAdapter adapter = new AuctionsListAdapter(null);
        adapter.update(new ArrayList<>(Arrays.asList(
                new Auction("Console"),
                new Auction("Computer")
        )));
        int amountAuctions = adapter.getItemCount();
        assertThat(amountAuctions, is(2));
    }
}

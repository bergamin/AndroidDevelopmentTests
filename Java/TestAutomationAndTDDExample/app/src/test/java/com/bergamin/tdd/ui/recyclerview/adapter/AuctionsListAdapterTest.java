package com.bergamin.tdd.ui.recyclerview.adapter;

import android.content.Context;

import com.bergamin.tdd.model.Auction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AuctionsListAdapterTest {

    @Mock
    private Context context;
    @Spy
    private AuctionsListAdapter adapter = new AuctionsListAdapter(context);

    @Test
    public void should_updateAuctionsList_whenReceivesAuctionsList() {
        doNothing().when(adapter).refreshList();

        adapter.update(new ArrayList<>(Arrays.asList(
                new Auction("Console"),
                new Auction("Computer")
        )));
        int amountAuctions = adapter.getItemCount();

        verify(adapter).refreshList();
        assertThat(amountAuctions, is(2));
    }
}

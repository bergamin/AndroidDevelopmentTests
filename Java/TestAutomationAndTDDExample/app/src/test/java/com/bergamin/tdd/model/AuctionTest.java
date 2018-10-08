package com.bergamin.tdd.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuctionTest {

    @Test
    public void getDescription() {
        // create test scenario
        String description = "Console";
        Auction console = new Auction(description);
        // run expected action
        String returnedDescription = console.getDescription();
        // test expected result
        assertEquals(description, returnedDescription);
    }

    @Test
    public void getHighestBid() {
        double bidValue = 200.0;
        Auction console = new Auction("Console");

        console.bid(new Bid(new User("Alex"), bidValue));
        double returnedBidValue = console.getHighestBid();

        // Delta means the difference between floating point number.
        // If it is higher, it means that the values are equal.
        // It is used for solving rounding problems
        assertEquals(bidValue, returnedBidValue, 0.0001);
    }
}

package com.bergamin.tdd.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuctionTest {

    // Delta means the difference between floating point numbers.
    // If it is higher, it means that the values are equal.
    // It is used for solving rounding problems
    private String description = "Console";
    private Auction auction = new Auction(description);
    private double delta = 0.0001;
    private double lowestValue = 100.0;
    private double highestValue = 200.0;

    // test methods names should follow the following pattern:
    // should_[expected result]_[test state]
    // or
    // [the name of the method being tested]_[test state]_[expected result]

    @Test
    public void should_returnDescription_whenReceivesDescription() { // or getDescription_receivesDescription_returnsDescription
        assertEquals(description, auction.getDescription());
    }

    @Test
    public void should_returnHighestBid_whenReceivesOneValue() { // or getHighestBid_receivesOneValue_returnsHighest
        auction.bid(new Bid(new User("Alex"), highestValue));
        assertEquals(highestValue, auction.getHighestBid(), delta);
    }

    @Test
    public void should_returnHighestBid_whenReceivesTwoValuesLowestToHighest() { // or getHighestBid_receivesTwoValuesLowestToHighest_returnsHighest
        auction.bid(new Bid(new User("Alex"), lowestValue));
        auction.bid(new Bid(new User("Fran"), highestValue));
        assertEquals(highestValue, auction.getHighestBid(), delta);
    }

    @Test
    public void should_returnHighestBid_whenReceivesTwoValuesHighestToLowest() { // or getHighestBid_receivesTwoValuesHighestToLowest_returnsHighest
        auction.bid(new Bid(new User("Fran"), highestValue));
        auction.bid(new Bid(new User("Alex"), lowestValue));
        assertEquals(highestValue, auction.getHighestBid(), delta);
    }

    @Test
    public void should_returnLowestBid_whenReceivesOneValue() {
        auction.bid(new Bid(new User("Alex"), lowestValue));
        assertEquals(lowestValue, auction.getLowestBid(), delta);
    }

    @Test
    public void should_returnLowestBid_whenReceivesTwoValuesLowestToHighest() { // or getHighestBid_receivesTwoValuesLowestToHighest_returnsHighest
        auction.bid(new Bid(new User("Alex"), lowestValue));
        auction.bid(new Bid(new User("Fran"), highestValue));
        assertEquals(lowestValue, auction.getLowestBid(), delta);
    }

    @Test
    public void should_returnLowestBid_whenReceivesTwoValuesHighestToLowest() { // or getHighestBid_receivesTwoValuesHighestToLowest_returnsHighest
        auction.bid(new Bid(new User("Fran"), highestValue));
        auction.bid(new Bid(new User("Alex"), lowestValue));
        assertEquals(lowestValue, auction.getLowestBid(), delta);
    }
}

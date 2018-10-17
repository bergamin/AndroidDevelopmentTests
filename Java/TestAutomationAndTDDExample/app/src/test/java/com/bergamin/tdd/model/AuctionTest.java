package com.bergamin.tdd.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AuctionTest {

    // Delta means the difference between floating point numbers.
    // If it is higher, it means that the values are equal.
    // It is used for solving rounding problems
    private final String DESCRIPTION = "Console";
    private final Auction AUCTION = new Auction(DESCRIPTION);

    private final User ALEX = new User("Alex");
    private final User FRAN = new User("Fran");

    private final double DELTA = 0.0001;
    private final double LOWEST_VALUE = 100.0;
    private final double MIDDLE_VALUE = 200.0;
    private final double HIGHEST_VALUE = 300.0;

    // test methods names should follow the following pattern:
    // should_[expected result]_[test state]
    // or
    // [the name of the method being tested]_[test state]_[expected result]

    @Test
    public void should_returnDescription_whenReceivesDescription() { // or getDescription_receivesDescription_returnsDescription
        assertEquals(DESCRIPTION, AUCTION.getDescription());
    }

    @Test
    public void should_returnHighestBid_whenReceivesOneValue() { // or getHighestBid_receivesOneValue_returnsHighest
        AUCTION.bid(new Bid(ALEX, HIGHEST_VALUE));
        assertEquals(HIGHEST_VALUE, AUCTION.getHighestBid(), DELTA);
    }

    @Test
    public void should_returnHighestBid_whenReceivesTwoValuesAscending() { // or getHighestBid_receivesTwoValuesLowestToHighest_returnsHighest
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        AUCTION.bid(new Bid(FRAN, HIGHEST_VALUE));
        assertEquals(HIGHEST_VALUE, AUCTION.getHighestBid(), DELTA);
    }

    @Test
    public void should_returnHighestBid_whenReceivesTwoValuesDescending() { // or getHighestBid_receivesTwoValuesHighestToLowest_returnsHighest
        AUCTION.bid(new Bid(FRAN, HIGHEST_VALUE));
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        assertEquals(HIGHEST_VALUE, AUCTION.getHighestBid(), DELTA);
    }

    @Test
    public void should_returnLowestBid_whenReceivesOneValue() {
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        assertEquals(LOWEST_VALUE, AUCTION.getLowestBid(), DELTA);
    }

    @Test
    public void should_returnLowestBid_whenReceivesTwoValuesLowestToHighest() { // or getHighestBid_receivesTwoValuesLowestToHighest_returnsHighest
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        AUCTION.bid(new Bid(FRAN, HIGHEST_VALUE));
        assertEquals(LOWEST_VALUE, AUCTION.getLowestBid(), DELTA);
    }

    @Test
    public void should_returnLowestBid_whenReceivesTwoValuesHighestToLowest() { // or getHighestBid_receivesTwoValuesHighestToLowest_returnsHighest
        AUCTION.bid(new Bid(FRAN, HIGHEST_VALUE));
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        assertEquals(LOWEST_VALUE, AUCTION.getLowestBid(), DELTA);
    }

    @Test
    public void should_returnThreeHighestBids_whenReceivesThreeBids() {
        AUCTION.bid(new Bid(ALEX, MIDDLE_VALUE));
        AUCTION.bid(new Bid(FRAN, LOWEST_VALUE));
        AUCTION.bid(new Bid(ALEX, HIGHEST_VALUE));
        List<Bid> bids = AUCTION.getThreeHighestBids();
        assertEquals(3, bids.size());
        assertEquals(HIGHEST_VALUE, bids.get(0).getValue(), DELTA);
        assertEquals(MIDDLE_VALUE, bids.get(1).getValue(), DELTA);
        assertEquals(LOWEST_VALUE, bids.get(2).getValue(), DELTA);
    }

    @Test
    public void should_returnThreeHighestBids_whenDoesNotReceiveBids() {
        List<Bid> bids = AUCTION.getThreeHighestBids();
        assertEquals(0, bids.size());
    }

    @Test
    public void should_returnThreeHighestBids_whenReceivesOneBid() {
        AUCTION.bid(new Bid(ALEX, HIGHEST_VALUE));
        List<Bid> bids = AUCTION.getThreeHighestBids();
        assertEquals(1, bids.size());
        assertEquals(HIGHEST_VALUE, bids.get(0).getValue(), DELTA);
    }

    @Test
    public void should_returnThreeHighestBids_whenReceivesTwoBids() {
        AUCTION.bid(new Bid(FRAN, MIDDLE_VALUE));
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        List<Bid> bids = AUCTION.getThreeHighestBids();
        assertEquals(2, bids.size());
        assertEquals(HIGHEST_VALUE, bids.get(0).getValue(), DELTA);
        assertEquals(LOWEST_VALUE, bids.get(1).getValue(), DELTA);
    }

    @Test
    public void should_returnThreeHighestBids_whenReceivesFourBids() {
        double newLow = 50d;
        AUCTION.bid(new Bid(FRAN, newLow));
        AUCTION.bid(new Bid(ALEX, MIDDLE_VALUE));
        AUCTION.bid(new Bid(FRAN, HIGHEST_VALUE));
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        List<Bid> bids = AUCTION.getThreeHighestBids();
        assertEquals(3, bids.size());
        assertEquals(HIGHEST_VALUE, bids.get(0).getValue(), DELTA);
        assertEquals(MIDDLE_VALUE, bids.get(1).getValue(), DELTA);
        assertEquals(LOWEST_VALUE, bids.get(2).getValue(), DELTA);
    }
}

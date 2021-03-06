package com.bergamin.tdd.model;

import com.bergamin.tdd.exception.NewBidEqualsToHighestBidException;
import com.bergamin.tdd.exception.NewBidLowerThanHighestBidException;
import com.bergamin.tdd.exception.UserBidsTwiceInARowException;
import com.bergamin.tdd.exception.UserExceedsNumberOfBidsLimitException;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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
        assertThat(AUCTION.getDescription(), is(DESCRIPTION));
    }

    @Test
    public void should_returnHighestBid_whenReceivesOneValue() { // or getHighestBid_receivesOneValue_returnsHighest
        AUCTION.bid(new Bid(ALEX, HIGHEST_VALUE));

        assertThat(AUCTION.getHighestBid(), closeTo(HIGHEST_VALUE, DELTA));
    }

    @Test
    public void should_returnHighestBid_whenReceivesTwoValues() { // or getHighestBid_receivesTwoValuesLowestToHighest_returnsHighest
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        AUCTION.bid(new Bid(FRAN, HIGHEST_VALUE));

        assertEquals(HIGHEST_VALUE, AUCTION.getHighestBid(), DELTA);
    }

    @Test
    public void should_returnLowestBid_whenReceivesOneValue() {
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));

        assertEquals(LOWEST_VALUE, AUCTION.getLowestBid(), DELTA);
    }

    @Test
    public void should_returnLowestBid_whenReceivesTwoValues() { // or getHighestBid_receivesTwoValuesLowestToHighest_returnsHighest
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        AUCTION.bid(new Bid(FRAN, HIGHEST_VALUE));

        assertEquals(LOWEST_VALUE, AUCTION.getLowestBid(), DELTA);
    }

    @Test
    public void should_returnThreeHighestBids_whenReceivesThreeBids() {
        Bid highest = new Bid(ALEX, HIGHEST_VALUE);
        Bid middle = new Bid(FRAN, MIDDLE_VALUE);
        Bid lowest = new Bid(ALEX, LOWEST_VALUE);

        AUCTION.bid(lowest);
        AUCTION.bid(middle);
        AUCTION.bid(highest);

        List<Bid> bids = AUCTION.getThreeHighestBids();

//        assertThat(bids, hasSize(3));
//        assertThat(bids, contains(highest, middle, lowest));
//      OR
        assertThat(bids, both(Matchers.<Bid>hasSize(3))
                .and(contains(highest, middle, lowest)));

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
        AUCTION.bid(new Bid(FRAN, LOWEST_VALUE));
        AUCTION.bid(new Bid(ALEX, HIGHEST_VALUE));

        List<Bid> bids = AUCTION.getThreeHighestBids();

        assertEquals(2, bids.size());
        assertEquals(HIGHEST_VALUE, bids.get(0).getValue(), DELTA);
        assertEquals(LOWEST_VALUE, bids.get(1).getValue(), DELTA);
    }

    @Test
    public void should_returnThreeHighestBids_whenReceivesFourBids() {
        double newLow = 50d;

        AUCTION.bid(new Bid(ALEX, newLow));
        AUCTION.bid(new Bid(FRAN, LOWEST_VALUE));
        AUCTION.bid(new Bid(ALEX, MIDDLE_VALUE));
        AUCTION.bid(new Bid(FRAN, HIGHEST_VALUE));

        List<Bid> bids = AUCTION.getThreeHighestBids();

        assertEquals(3, bids.size());
        assertEquals(HIGHEST_VALUE, bids.get(0).getValue(), DELTA);
        assertEquals(MIDDLE_VALUE, bids.get(1).getValue(), DELTA);
        assertEquals(LOWEST_VALUE, bids.get(2).getValue(), DELTA);
    }

    @Test
    public void should_returnHighestBidValueZero_whenThereAreNoBids() {
        assertEquals(0.0, AUCTION.getHighestBid(), DELTA);
    }

    @Test
    public void should_returnLowestBidValueZero_whenThereAreNoBids() {
        assertEquals(0.0, AUCTION.getLowestBid(), DELTA);
    }

    @Test(expected = NewBidLowerThanHighestBidException.class)
    public void shouldNot_addBid_whenLowerThanHighestBid() {
        AUCTION.bid(new Bid(ALEX, HIGHEST_VALUE));
        AUCTION.bid(new Bid(FRAN, LOWEST_VALUE));
    }

    @Test(expected = UserBidsTwiceInARowException.class)
    public void shouldNot_addBid_whenSameUserAsLastBid() {
        AUCTION.bid(new Bid(ALEX, LOWEST_VALUE));
        AUCTION.bid(new Bid(new User("Alex"), HIGHEST_VALUE));
    }

    @Test(expected = UserExceedsNumberOfBidsLimitException.class)
    public void shouldNot_addBid_whenUserBidsOverFiveTimes() {
        AUCTION.bid(new Bid(ALEX, 100));
        AUCTION.bid(new Bid(FRAN, 200));
        AUCTION.bid(new Bid(ALEX, 300));
        AUCTION.bid(new Bid(FRAN, 400));
        AUCTION.bid(new Bid(ALEX, 500));
        AUCTION.bid(new Bid(FRAN, 600));
        AUCTION.bid(new Bid(ALEX, 700));
        AUCTION.bid(new Bid(FRAN, 800));
        AUCTION.bid(new Bid(ALEX, 900));
        AUCTION.bid(new Bid(FRAN, 1000));
        AUCTION.bid(new Bid(ALEX, 1100));
    }

    @Test(expected = NewBidEqualsToHighestBidException.class)
    public void shouldNot_addBid_whenSameValueAsLastBid() {
        AUCTION.bid(new Bid(ALEX, 100));
        AUCTION.bid(new Bid(FRAN, 100));
    }
}

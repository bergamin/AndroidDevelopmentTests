package com.bergamin.tdd.model;

import com.bergamin.tdd.exception.NewBidEqualsToHighestBidException;
import com.bergamin.tdd.exception.NewBidLowerThanHighestBidException;
import com.bergamin.tdd.exception.UserBidsTwiceInARowException;
import com.bergamin.tdd.exception.UserExceedsNumberOfBidsLimitException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Auction implements Serializable {

    private final String description;
    private final List<Bid> bids;
    private double highestBid = 0.0;
    private double lowestBid = 0.0;

    public Auction(String description) {
        this.description = description;
        this.bids = new ArrayList<>();
    }

    public void bid(Bid bid) {
        checkBidValid(bid);

        double bidValue = bid.getValue();
        bids.add(bid);
        Collections.sort(bids);

        if (bids.size() == 1) {
            highestBid = bidValue;
            lowestBid = bidValue;
            return;
        }
        if (bidValue > highestBid) {
            highestBid = bidValue;
        }
        if (bidValue < lowestBid) {
            lowestBid = bidValue;
        }
    }

    private void checkBidValid(Bid bid) {
        checkLowerOrEqualToHighest(bid);
        checkDoubledBid(bid.getUser());
        checkUserUnderLimit(bid.getUser());
    }

    private void checkUserUnderLimit(User user) {
        if (!bids.isEmpty()) {
            int userBidsCount = 0;
            for (Bid bid : bids) {
                if (user.equals(bid.getUser())) {
                    userBidsCount++;
                    if (userBidsCount == 5) {
                        throw new UserExceedsNumberOfBidsLimitException();
                    }
                }
            }
        }
    }

    private void checkDoubledBid(User user) {
        if (!bids.isEmpty()) {
            User highestBidder = bids.get(0).getUser();
            if (highestBidder.equals(user)) {
                throw new UserBidsTwiceInARowException();
            }
        }
    }

    private void checkLowerOrEqualToHighest(Bid bid) {
        if (highestBid > bid.getValue()) {
            throw new NewBidLowerThanHighestBidException();
        } else if (highestBid == bid.getValue()) {
            throw new NewBidEqualsToHighestBidException();
        }
    }

    public String getDescription() {
        return description;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public double getLowestBid() {
        return lowestBid;
    }

    public List<Bid> getThreeHighestBids() {
        int maxBids = bids.size();
        if (maxBids > 3) {
            maxBids = 3;
        }
        return bids.subList(0, maxBids);
    }

    public int getNumberOfBids() {
        return bids.size();
    }
}

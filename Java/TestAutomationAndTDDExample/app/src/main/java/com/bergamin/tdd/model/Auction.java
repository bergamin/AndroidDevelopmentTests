package com.bergamin.tdd.model;

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
        if (!isBidValid(bid)) {
            return;
        }

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

    private boolean isBidValid(Bid bid) {
        return isHigherThanHighest(bid)
                && !isDoubledBid(bid.getUser())
                && isUserUnderLimit(bid.getUser());
    }

    private boolean isUserUnderLimit(User user) {
        if (!bids.isEmpty()) {
            int userBidsCount = 0;
            for (Bid bid : bids) {
                if (user.equals(bid.getUser())) {
                    userBidsCount++;
                    if (userBidsCount == 5) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean isDoubledBid(User user) {
        if (!bids.isEmpty()) {
            User highestBidder = bids.get(0).getUser();
            return highestBidder.equals(user);
        }
        return false;
    }

    private boolean isHigherThanHighest(Bid bid) {
        return (highestBid < bid.getValue());
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

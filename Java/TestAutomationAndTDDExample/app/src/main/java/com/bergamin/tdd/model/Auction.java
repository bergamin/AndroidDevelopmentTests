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
        double value = bid.getValue();

        if (highestBid > value) {
            return;
        }
        if (!bids.isEmpty()) {
            User highestUser = bids.get(0).getUser();
            if (highestUser.equals(bid.getUser())) {
                return;
            }
        }

        bids.add(bid);
        Collections.sort(bids);
        if (bids.size() == 1) {
            highestBid = value;
            lowestBid = value;
            return;
        }
        if (value > highestBid) {
            highestBid = value;
        }
        if (value < lowestBid) {
            lowestBid = value;
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

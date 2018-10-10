package com.bergamin.tdd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Auction implements Serializable {

    private final String description;
    private final List<Bid> bids;
    private double highestBid = Double.NEGATIVE_INFINITY;
    private double lowestBid = Double.POSITIVE_INFINITY;

    public Auction(String description) {
        this.description = description;
        this.bids = new ArrayList<>();
    }

    public void bid(Bid bid) {
        bids.add(bid);
        if (bid.getValue() > highestBid) {
            highestBid = bid.getValue();
        }
        if (bid.getValue() < lowestBid) {
            lowestBid = bid.getValue();
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
        return bids.subList(0, 3);
    }
}

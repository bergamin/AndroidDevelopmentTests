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
        double bidValue = bid.getValue();
        if (bidValue > highestBid) {
            highestBid = bidValue;
        }
        if (bidValue < lowestBid) {
            lowestBid = bidValue;
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
}

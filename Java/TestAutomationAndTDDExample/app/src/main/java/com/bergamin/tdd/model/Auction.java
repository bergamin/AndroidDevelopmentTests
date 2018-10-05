package com.bergamin.tdd.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Auction implements Serializable {

    private final String description;
    private final List<Bid> bids;

    public Auction(String description) {
        this.description = description;
        this.bids = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }
}

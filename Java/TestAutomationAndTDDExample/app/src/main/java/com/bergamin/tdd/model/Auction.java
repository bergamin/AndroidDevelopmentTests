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

    private final long id;
    private final String description;
    private final List<Bid> bids;

    public Auction(String description) {
        this.id = 0L;
        this.description = description;
        this.bids = new ArrayList<>();
    }

    public void bid(Bid bid) {
        validate(bid);
        bids.add(bid);
        Collections.sort(bids);
    }

    private void validate(Bid bid) {
        checkLowerOrEqualToHighest(bid);
        if (!bids.isEmpty()) {
            checkDoubledBid(bid.getUser());
            checkUserUnderLimit(bid.getUser());
        }
    }

    private void checkUserUnderLimit(User user) {
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

    private void checkDoubledBid(User user) {
        User highestBidder = bids.get(0).getUser();
        if (highestBidder.equals(user)) {
            throw new UserBidsTwiceInARowException();
        }
    }

    private void checkLowerOrEqualToHighest(Bid bid) {
        if (getHighestBid() > bid.getValue()) {
            throw new NewBidLowerThanHighestBidException();
        } else if (getHighestBid() == bid.getValue()) {
            throw new NewBidEqualsToHighestBidException();
        }
    }

    public double getLowestBid() {
        if (bids.isEmpty()) {
            return 0.0;
        }
        return bids.get(bids.size() - 1).getValue();
    }

    public double getHighestBid() {
        if (bids.isEmpty()) {
            return 0.0;
        }
        return bids.get(0).getValue();
    }

    public String getDescription() {
        return description;
    }

    public List<Bid> getThreeHighestBids() {
        int maxBids = bids.size();
        if (maxBids > 3) {
            maxBids = 3;
        }
        return bids.subList(0, maxBids);
    }

    public Long getId() {
        return id;
    }
}

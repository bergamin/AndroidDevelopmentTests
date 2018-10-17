package com.bergamin.tdd.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Bid implements Serializable, Comparable {

    private final User user;
    private final double value;

    public Bid(User user, double value) {
        this.user = user;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Bid bid = (Bid) o;
        if (value > bid.getValue()) {
            return -1;
        } else if (value < bid.getValue()) {
            return 1;
        }
        return 0;
    }
}

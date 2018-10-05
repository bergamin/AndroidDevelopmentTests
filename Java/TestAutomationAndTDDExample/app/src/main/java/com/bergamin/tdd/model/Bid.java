package com.bergamin.tdd.model;

import java.io.Serializable;

public class Bid implements Serializable {

    private final User user;
    private final double value;

    public Bid(User user, double value) {
        this.user = user;
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}

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
        return Double.compare(bid.getValue(), value);
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (Double.compare(bid.value, value) != 0) return false;
        return user.equals(bid.user);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = user.hashCode();
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

package com.bergamin.tdd.api.retrofit.client;

public interface ResponseListener<T> {
    void success(T response);
    void failure(String message);
}

package com.example.videolibrarybe.rest;

import lombok.Getter;

@Getter
public class RestResponseSuccessBody<T> {
    private final Boolean errorExists = false;
    private final T data;

    public RestResponseSuccessBody(T data) {
        this.data = data;
    }
}

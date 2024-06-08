package com.example.videolibrarybe.rest;

import lombok.Getter;

import java.util.List;

@Getter
public class RestResponseErrorBody {
    private final Boolean errorExists = true;
    private final String errorMessage;
    private List<String> errors;

    public RestResponseErrorBody(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RestResponseErrorBody(String errorMessage, List<String> errors) {
        this.errorMessage = errorMessage;
        this.errors = errors;
    }
}

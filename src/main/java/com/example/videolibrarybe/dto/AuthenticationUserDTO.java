package com.example.videolibrarybe.dto;

public record AuthenticationUserDTO (
        int userId,
        String firstName,
        String lastName,
        String emailId,
        String password
) {}


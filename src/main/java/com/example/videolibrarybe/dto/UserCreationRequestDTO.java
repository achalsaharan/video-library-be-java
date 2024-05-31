package com.example.videolibrarybe.dto;

public record UserCreationRequestDTO(
        String firstName,
        String lastName,
        String emailId,
        String password) {}

package com.example.videolibrarybe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserCreationRequestDTO(
        @NotEmpty(message = "firstName should not be empty")
        String firstName,
        String lastName,
        @NotEmpty(message = "emailId should not be empty")
        @Email(message = "emailId should be a valid email")
        String emailId,
        String password) {}

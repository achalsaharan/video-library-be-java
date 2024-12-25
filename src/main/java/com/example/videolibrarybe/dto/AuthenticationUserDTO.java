package com.example.videolibrarybe.dto;

import com.example.videolibrarybe.constants.UserRoles;

public record AuthenticationUserDTO (
        int userId,
        String firstName,
        String lastName,
        String emailId,
        String password,
        UserRoles role
) {}


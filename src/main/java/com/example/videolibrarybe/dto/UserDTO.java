package com.example.videolibrarybe.dto;

import com.example.videolibrarybe.constants.UserRoles;

public record UserDTO(
        int userId,
        String firstName,
        String lastName,
        String emailId,
        UserRoles role) {}

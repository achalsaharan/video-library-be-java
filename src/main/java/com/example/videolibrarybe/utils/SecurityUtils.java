package com.example.videolibrarybe.utils;

import com.example.videolibrarybe.model.User;
import io.jsonwebtoken.Jwt;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static User getUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return (User) principal;
        }
        throw new IllegalStateException("No UserDetails object found in Security Context");
    }
}

package com.example.videolibrarybe.service;

import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User createUser();

    List<UserDTO> getAllUsers();
}

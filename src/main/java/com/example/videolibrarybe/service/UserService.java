package com.example.videolibrarybe.service;

import com.example.videolibrarybe.dto.AuthenticationUserDTO;
import com.example.videolibrarybe.dto.UserCreationRequestDTO;
import com.example.videolibrarybe.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserCreationRequestDTO userCreationRequestDTO);
    UserDTO getUser(Integer userId);
    List<UserDTO> getAllUsers();
    AuthenticationUserDTO getUserByEmailId(String emailId);
}

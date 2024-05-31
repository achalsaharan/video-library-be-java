package com.example.videolibrarybe.controller;

import com.example.videolibrarybe.dto.UserCreationRequestDTO;
import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.mapper.SimpleMapper;
import com.example.videolibrarybe.model.User;
import com.example.videolibrarybe.repository.UserRepository;
import com.example.videolibrarybe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserDTO addUser(@RequestBody UserCreationRequestDTO userCreationRequestDTO) {
        return userService.createUser(userCreationRequestDTO);
    }

    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {
        return userService.getAllUsers();
    }

}

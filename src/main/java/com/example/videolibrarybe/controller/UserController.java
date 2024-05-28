package com.example.videolibrarybe.controller;

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

    private final UserRepository userRepository;

    private final UserService userService;


    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/users")
    public List<UserDTO> findAllUsers() {
//        log.info("achal");
        return userService.getAllUsers();
    }

}

package com.example.videolibrarybe.controller;

import com.example.videolibrarybe.dto.UserCreationRequestDTO;
import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.rest.RestResponseSuccessBody;
import com.example.videolibrarybe.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<RestResponseSuccessBody<UserDTO>> createUser(@Valid @RequestBody UserCreationRequestDTO userCreationRequestDTO) {
        return new ResponseEntity<>(new RestResponseSuccessBody<>(userService.createUser(userCreationRequestDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RestResponseSuccessBody<UserDTO>> getUser(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<>(new RestResponseSuccessBody<>(userService.getUser(userId)), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<RestResponseSuccessBody<List<UserDTO>>> findAllUsers() {
        return new ResponseEntity<>(new RestResponseSuccessBody<>(userService.getAllUsers()), HttpStatus.OK);
    }

}

package com.example.videolibrarybe.controller;

import com.example.videolibrarybe.dto.LoginRequestDTO;
import com.example.videolibrarybe.dto.LoginResponseDTO;
import com.example.videolibrarybe.dto.UserCreationRequestDTO;
import com.example.videolibrarybe.dto.UserDTO;
import com.example.videolibrarybe.model.User;
import com.example.videolibrarybe.rest.RestResponseSuccessBody;
import com.example.videolibrarybe.service.Implementation.AuthenticationService;
import com.example.videolibrarybe.service.Implementation.JwtService;
import com.example.videolibrarybe.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @Autowired
    public AuthController(UserService userService, AuthenticationService authenticationService, JwtService jwtService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<RestResponseSuccessBody<UserDTO>> signUp(@Valid @RequestBody UserCreationRequestDTO userCreationRequestDTO) {
        return new ResponseEntity<>(
                new RestResponseSuccessBody<>(userService.createUser(userCreationRequestDTO)), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponseSuccessBody<LoginResponseDTO>> login(@RequestBody LoginRequestDTO loginRequestDTO)  {
        User authenticatedUser = authenticationService.authenticate(loginRequestDTO);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return new ResponseEntity<>(
                new RestResponseSuccessBody<>(new LoginResponseDTO("test", loginRequestDTO.username(), jwtToken)), HttpStatus.OK
        );
    }
}
